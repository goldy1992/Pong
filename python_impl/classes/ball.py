from constants import Color, Position

DEFAULT_POSITION: Position = (400, 300)

class Ball:
  color: Color = (255, 255, 255)
  radius: float = 0
  dx: float = 0
  dy: float = 0
  position: Position = (400, 300)

  def __init__(self, radius: float, position: Position = DEFAULT_POSITION):
    self.radius = radius
    self.position = position
  

   # // defines the ball's radius and diameter
   # public static final float diameter = 20f;
   # public static final float radius = diameter / 2;
   
   # // the image of the ball
   # public Ellipse2D.Float ballImage;

   # // points to represent the vertices of the rectangle encapsulating the ball
   # public Point<Float, Float> topRightPoint;
   # public Point<Float, Float> topLeftPoint;
   # public Point<Float, Float> bottomRightPoint;
   # public Point<Float, Float> bottomLeftPoint;
   
   # // the co-ordinates of the centre of the ball
   # public Point<Float, Float> centrePoint;

   
   # // determines whether the ball is intersecting one of the paddles
   # public boolean isTouchingPaddle = false;
   
   # /* boolean to determine whether the ball has changed direction during the 
   #    period of it's intersection, we don't want it to change direction more 
   #    than once as it could ultimately be travelling in the same direction 
   #    after the period of intersection */ 
   # private boolean hasChangedDirection = false;
   

   # /**
   #  * Creates a new ball object and moves it to it's initial position in the game area
   #  */
   # public Ball()
   # {
# 	  // creates the points to reference the position of the ball
#       topRightPoint = new Point<>();
#       topLeftPoint = new Point<>();
#       bottomRightPoint = new Point<>();
#       bottomLeftPoint = new Point<>();
#       centrePoint = new Point<>();
      
#       /* sets the centre and top left points in order to draw the initial
#          image of the ball */
#       centrePoint.set( 0.0f, 0.0f);
#       topLeftPoint.set((centrePoint.getX() - radius),
#               (centrePoint.getY() + radius));
      
#       // creates the ball image in the initial position of the game area
#       ballImage = new Ellipse2D.Float(topLeftPoint.getX(),
#                                        topLeftPoint.getY(), 
#                                        diameter, 
#                                        diameter );

#       /* when this method is called the ball is being placed for the first 
#          time so the image of the ball hasn't been made yet therefore the 
#          image is set when the constructor of the ball image is called */
#       moveToInitialPosition();
          
#       /* checks the output from the data file read to see if a colour was set 
#          the last time that the program was run. If the reference to the colour
#          is null the default colour of white it set. */ 
#       if (Controller.initialBallColour != null)
#     	  ballColour = new Color(Controller.initialBallColour); 
# 		else
# 			ballColour = Color.WHITE;
      
#    } // ball constructor
   

  def move_to_initial_position(self): 
    """    
      Moves the ball to the initial position
    """
    # set the ball stationary
    self.dx = 0
    self.dy = 0
    self.position = DEFAULT_POSITION



#       /* sets the point references of the ball to the relevant points using the
#        *  centre point as a guide */
#      /* centrePoint.set( (float)GameDimensions.X_DISTANCE_TO_FIELD_START 
#                         + (GameDimensions.FIELD_WIDTH / 2),
#                         (float)(GameDimensions.FIELD_HEIGHT / 2));*/
#       centrePoint.set(0.0f, 0.0f);
#       topRightPoint.set((centrePoint.getX() + radius),
#                         (centrePoint.getY() + radius));
#       topLeftPoint.set((centrePoint.getX() - radius),
#                        (centrePoint.getY() + radius));
#       bottomRightPoint.set((centrePoint.getX() + radius),
#                           (centrePoint.getY() - radius));
#       bottomLeftPoint.set((centrePoint.getX() - radius),
#                           (centrePoint.getY() - radius));
      
#       // sets the image of the ball
#       ballImage.setFrame(topLeftPoint.getX(),
#         		            topLeftPoint.getY(), 
#         		            diameter, 
#         		            diameter);

#    } // moveInitialPosition
   
   
  def __str__(self):
    """
      Used for debugging purposes outputs the the co-ordinates of each position. 
    """
    return f'Ball({self} with center: {self.position}, radius: {self.radius}, color: {self.color})'

  def move(self, new_pos: Position):
    self.position = new_pos

  def is_out_of_bounds(self):
    return False
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