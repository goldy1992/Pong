from enum import Enum

import pygame
from classes.ball import Ball
from classes.util import WORLD_RECT, random_direction
from constants import WINDOW_HEIGHT, WINDOW_WIDTH, Position
from paddle import PADDLE_WIDTH, Paddle

class BallState(Enum):
  BALL_TOUCHED_PADDLE_CORNER = 0
  BALL_TOUCHED_PADDLE_TOP = 1
  BALL_TOUCHED_PADDLE_SIDE = 2
  BALL_DIDNT_TOUCH_PADDLE = 7

class Model():

    # definitions representing where about's the ball hit the paddle 
  player1 : Paddle = Paddle(20)
  player2 : Paddle = Paddle(WINDOW_WIDTH - 20 - PADDLE_WIDTH)
  
  ball : Ball = Ball(radius=10.0)
  isPaused : bool = False
  waitingToServe: bool = True
  setNewGame = False
	
  def __init__(self):
     pass
  
    #creates player 1 with controls w for up s for down
       

        # /* sets the colour for player 1 if it has been described in the data 
        #  * file as the program loads 
        #  */
        # if (Controller.initialP1Colour != null)
        #    player1.setColor(Controller.initialP1Colour);
        # else
        #         player1.setColour(Color.WHITE);	

        # // creates player 2 with controls up key for up down key for down		
        # player2 = new Paddle(GameDimensions.TL_XPOS_PLAYER2, 
        #                               KeyEvent.VK_UP, 
        #                               KeyEvent.VK_DOWN);

        # /* sets the colour for player 2 if it has been described in the data 
        #  * file as the program loads 
        #  */
        # if (Controller.initialP2Colour != null)
        #    player2.setColor(Controller.initialP2Colour);
        # else
        #         player2.setColour(Color.WHITE);


        # ball = new Ball();

  def __check_for_player_hit(self) -> bool:
    ball = self.ball
    p1 = self.player1
    p2 = self.player2

    #print(f'ball: {ball.rect}, p1: {p1.rect}, p2: {p2.rect}')
    if pygame.Rect.colliderect(ball.rect, p1.rect):
      print(f'hit p1')
      ball.dx = - ball.dx
      return True
    elif pygame.Rect.colliderect(ball.rect, p2.rect):
      print(f'hit p2')
      ball.dx = - ball.dx
      return True
    return False


  def update(self, deltaTimeMs):
    if self.setNewGame:
      self.ball.move_to_initial_position()
      self.setNewGame = False

    # executes when a new game has not been requested
    elif not self.isPaused:
      #pass
      self.__move_paddles(deltaTimeMs)
      self.__move_ball(deltaTimeMs)
  

  def __move_paddles(self, deltaTimeMs: float):
    p1 = self.player1
    if p1.moving_up:
      p1.move_delta(-100 * (deltaTimeMs / 500))
    if self.player1.moving_down:
      p1.move_delta(100 * (deltaTimeMs / 500))

  def __move_ball(self, deltaTimeMs: float):
    hit_player = self.__check_for_player_hit()
    # TODO: finish editing
    if not hit_player:
      self.ball.isTouchingPaddle = True
      self.ball.move_to_initial_position()
      self.waitingToServe = True

    if not self.waitingToServe:
      if not self.__check_out_of_bounds():
        print(f'not out of bounds')
        new_pos = self.__calculate_new_ball_position()
        self.ball.move(new_pos=new_pos)

  def __check_out_of_bounds(self) -> bool:
    ball = self.ball
    if not pygame.Rect.colliderect(ball.rect, WORLD_RECT):
      print(f'ball is out of bounds {ball.rect}')
      self.waitingToServe = True
      self.ball.move_to_initial_position()
      return True
    
    return False
  

  def __calculate_new_ball_position(self) -> Position:
         # if statement added here because if dx and dy are originally set when 
         # the game is paused the AI knows where the ball will go before the game 
         # commences and will therefore move to that position
    ball = self.ball
    if (ball.dx == 0) or (ball.dy == 0):
      ball.dx = random_direction()
      ball.dy = random_direction()
	  
    # if the ball touches the the top wall then change y direction.
    ball_top = ball.position[1] - ball.radius
    ball_bottom = ball.position[1] + ball.radius
    if (ball_top < 0) or (ball_bottom > WINDOW_HEIGHT):
      ball.dy = -ball.dy

    

    # if __is_ball_touching_player():
    #   return False
   
   
    return (ball.position[0] + ball.dx, ball.position[1] + ball.dy)


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
