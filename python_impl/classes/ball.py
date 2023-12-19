import pygame
from classes.util import create_collision_surface
from constants import WINDOW_HEIGHT, WINDOW_WIDTH, Color, Position

DEFAULT_POSITION: Position = (400, 300)

class Ball:
  color: Color = (255, 255, 255)
  radius: float = 0
  dx: float = 0
  dy: float = 0
  position: Position = (400, 300)
  rect: pygame.Rect 

  def __init__(self, radius: float, position: Position = DEFAULT_POSITION):
    self.radius = radius
    self.position = position
    self.rect = pygame.draw.circle(pygame.Surface((WINDOW_WIDTH, WINDOW_HEIGHT)), "white", position, radius)


  def __set_rect(self):
    print(f'ball: {self.rect}')
    self.rect = pygame.draw.circle(create_collision_surface(), "white", self.position, self.radius)
  

  def move_to_initial_position(self): 
    """    
      Moves the ball to the initial position
    """
    # set the ball stationary
    self.dx = 0
    self.dy = 0
    self.position = DEFAULT_POSITION
    self.__set_rect()
   
   
  def __str__(self):
    """
      Used for debugging purposes outputs the the co-ordinates of each position. 
    """
    return f'Ball({self} with center: {self.position}, radius: {self.radius}, color: {self.color})'


  def move(self, new_pos: Position):
    self.position = new_pos
    self.__set_rect()

#    /**
#     * This method is called when the model updates and has to move the ball. 
#     * The logic to the reaction of the ball, if it intersects with other 
#     * objects such as paddles and walls, is placed here.
#     * 
#     * @param changeNo represents the ball has done if it intersects a paddle or
#     * the corner of a paddle etc... For definitions of what each integer 
#     * represents see the model class.
#     */
#     public void move(int changeNo)
#     {  
#         /*
#          * if statement added here because if dx and dy are originally set when 
#          * the game is paused the AI knows where the ball will go before the game 
#          * commences and will therefore move to that position
#          */
#         if (dx == 0 || dy == 0)
#         {
#             dx = randomDirection();
#             dy = randomDirection();
#             System.out.println("dx: " + dx + "\ndy: " + dy );
#         } // if
	  
# 	  // if the ball touches the the top wall then change y direction.
#       if((topLeftPoint.getY() >  GameDimensions.FIELD_AMPLITUDE) 
#     	    ||  (bottomRightPoint.getY() <= GameDimensions.FIELD_AMPLITUDE * -1.0f))
#            dy = -dy;

#       if (isTouchingPaddle)
#       {
#     	 if(!hasChangedDirection)
#          {
#     		 // uses changeNo to decide how to react to the intersections
#              switch (changeNo)
#              {
#                 case 0: dx = -1.1f * dx;
#                          dy = -1.1f * dy;
#                          break;
#                 case 1: dy = -1.05f * dy;
#                          break;
#                 case 2: dx = -1.05f * dx;
#                 default : break;
#              } // switch
#     		 hasChangedDirection = true;
#          } // inner if
#          isTouchingPaddle = false;
#       } // outer if
#       else
#     	  /* This is always set to false to ensure that the first time the ball
#     	   *  intersects the paddle, it hasn't changed direction and is does 
#     	   *  only once after the first point of intersection. */
#     	  hasChangedDirection = false;

#       // updates each of the ball co-ordinates after the ball has been moved.
#       topRightPoint.set((topRightPoint.getX() + dx),
#                         (topRightPoint.getY() + dy));
#       topLeftPoint.set((topLeftPoint.getX() + dx),
#                        (topLeftPoint.getY() + dy));
#       bottomLeftPoint.set((bottomLeftPoint.getX() + dx),
#                           (bottomLeftPoint.getY() + dy));
#       centrePoint.set((centrePoint.getX() + dx),
#                       (centrePoint.getY() + dy));
#       bottomRightPoint.set((bottomRightPoint.getX() + dx),
#     		               (bottomRightPoint.getY() + dy));

#       // updates the ball image after it;s been moved
#       ballImage.setFrame(topLeftPoint.getX(),
#     		             topLeftPoint.getY(),
#     		             diameter, 
#     		             diameter);
      
#    } // move

#    /**
#     * This method allows the user or the program to set the colour of the ball.
#     * @param colour The colour that the ball is to be set.
#     */
#    public void setColour(Color colour)
#    {
# 	   ballColour = colour;
#    } // setColour
   
#    /**
#     * 
#     * @return returns the current colour of the ball
#     */
#    public Color getColour()
#    {
#       return ballColour;
#    } // getColour

#    /**
#     * This method is used to calculate a random direction for the ball to 
#     * initially travel in
#     * @return returns 2 or -2 representing the magnitude and direction of the
#     * ball chosen at random
#     */
#    private float randomDirection()
#    {
#       Random direction = new Random();
#       float magnitude = (float) (direction.nextGaussian()*0.5);
      
#       return (magnitude < 0) ? magnitude - 3f : magnitude + 3f;
#    } // randomDirection

#    /**
#     * This method calculates whether the ball is out of bounds or not, 
#     * i.e. whether the ball has left the screen. If it has the method
#     * will return true as well as incrementing the score of the relevant
#     * player depending on which side of the screen the ball went off at. 
#     * @return returns true if the balls has left the screen and false 
#     * otherwise.
#     */
#    public boolean isOutOfBounds()
#    {
# 	  /* if the top right point of the ball is less than zero it's passed the 
# 	   * left side of the screen, therefore player 2 has scored and the ball is
# 	   * out of bounds */
#       if(topRightPoint.getX() < (GameDimensions.FIELD_WIDTH / 2) * -1.0f) 
#       {
#     	  ScorePanel.incPlayer2Score();
#     	  return true;
    	  
#       } // if
# 	  /* if the bottom (bottom/top doesn't matter) left point of the ball is 
# 	   * greater than the window width it's passed the right side of the 
# 	   * screen, therefore player 2 has scored and the ball is out of bounds */
#       else if(bottomLeftPoint.getX() > (GameDimensions.FIELD_WIDTH / 2))
#       {
#     	  ScorePanel.incPlayer1Score();
#     	  return true;
#       } // else if
          
#       else
#          return false;
#    } // isOutOfBounds
   
# } // class