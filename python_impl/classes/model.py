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

  def __check_for_intersection(self):
    ball = self.ball
    p1 = self.player1
    p2 = self.player2


    if pygame.Rect.colliderect(ball.rect, p1.rect) or pygame.Rect.colliderect(ball.rect, p2.rect):
      ball.dx = - ball.dx


  def checkForIntersection(self) -> BallState:
    return BallState.BALL_DIDNT_TOUCH_PADDLE
#        if ((ballTouchTopP1() && ball.dy > 0) || 
#                (ballTouchTopP2() && ball.dy > 0) ||
#                (ballTouchBottomP1() && ball.dy > 0) || 
#                (ballTouchBottomP2() && ball.dy > 0))
#                ball.dy = 1.5f  * ball.dy;

#        if ( (ballTouchTopP1() && ballTouchSideP1() && ball.dy > 0) ||
#                      (ballTouchBottomP1() && ballTouchSideP1() && ball.dy < 0))
#                return BALL_TOUCHED_PADDLE_CORNER;

#        else if ((ballTouchTopP1() && ball.dy > 0) || 
#                           (ballTouchBottomP1() && ball.dy < 0))
#       return BALL_TOUCHED_PADDLE_TOP;

#        else if (ballTouchSideP1())
#           return BALL_TOUCHED_PADDLE_SIDE;


#        if ( (ballTouchTopP2() && ballTouchSideP2() && ball.dy > 0) ||
#                      (ballTouchBottomP2() && ballTouchSideP2() && ball.dy < 0))
#                return BALL_TOUCHED_PADDLE_CORNER;

#        else if ((ballTouchTopP2() && ball.dy > 0) || 
#                          (ballTouchBottomP2() && ball.dy < 0))
#       return BALL_TOUCHED_PADDLE_TOP;

#        else if (ballTouchSideP2())
#           return BALL_TOUCHED_PADDLE_SIDE;	

#        return BALL_DIDNT_TOUCH_PADDLE;
#     }


#     private void checkForPaddleMovements()
#     {
#        if (Controller.gameMode.equals(Mode.MULTI))
#        {
#           if(player2.isMovingUp())
#                   player2.move(player2.topLeftPoint.getY()+7);
#           else if(player2.isMovingDown())
#                   player2.move(player2.topLeftPoint.getY()-7);

#           if(player1.isMovingUp())
#                   player1.move(player1.topLeftPoint.getY()+7);
#           else if(player1.isMovingDown())
#                   player1.move(player1.topLeftPoint.getY()-7);
#        } // if
#        else if (Controller.gameMode.equals(Mode.SINGLE))
#        {
#                if(player1.isMovingUp())
#                   player1.move(player1.topLeftPoint.getY()+7);
#                else if(player1.isMovingDown())
#                       player1.move(player1.topLeftPoint.getY()-7);
#           AI.moveAI_P2(ball, player2);
#        } // else if
#        else
#        {
#                AI.moveAI_P2(ball, player2);
#                AI.moveAI_P1(ball, player1);
#        } // else

   # } // checkForPaddleMovements

  def update(self, deltaTimeMs):
    if self.setNewGame:
      self.ball.move_to_initial_position()
      self.setNewGame = False

    # executes when a new game has not been requested
    elif not self.isPaused:
      #pass
      self.__move_paddles(deltaTimeMs)
      self.__move_ball(deltaTimeMs)
  

#     // collision check accessory methods

#     public boolean ballTouchTopP1()
#     {
#        return ball.ballImage.intersects(player1.topLeftPoint.getX(), 
#             player1.topLeftPoint.getY(), 
#             Paddle.PADDLE_WIDTH, 1);
#     } // ballTouchTopP1
    
#     public boolean ballTouchSideP1()
#     {   
#         boolean x = ball.ballImage.intersects(player1.topRightPoint.getX()-1, 
#             player1.topRightPoint.getY(), 
#         1, Paddle.PADDLE_HEIGHT);
#         if (x)
#         System.out.print("touched p1");
#         return x;
#     } // ballTouch Side P1
#     public boolean ballTouchBottomP1()
#     {
#         return ball.ballImage.intersects(player1.bottomLeftPoint.getX(), 
#             player1.bottomLeftPoint.getY(), 
#             Paddle.PADDLE_WIDTH, 1);	
#     } // ballTouchBottomP1
#     public boolean ballTouchTopP2()
#     {
#         return ball.ballImage.intersects(player2.topLeftPoint.getX(), 
#                player2.topLeftPoint.getY(), 
#                Paddle.PADDLE_WIDTH, 1);
#     } // ballTouchTopP2
#     public boolean ballTouchSideP2()
#     {
#         boolean x = ball.ballImage.intersects(player2.topLeftPoint.getX(), 
#                        player2.topLeftPoint.getY(), 
#                        1, Paddle.PADDLE_HEIGHT);
#         if (x)
#             System.out.print("touched p2");
#         return x;	
#     }// ballTouchSideP2
#     public boolean ballTouchBottomP2()
#     {
#             return ball.ballImage.intersects(player2.bottomLeftPoint.getX(), 
#                    player2.bottomLeftPoint.getY(), 
#                    Paddle.PADDLE_WIDTH, 1);
#     } // ballTouchBottomP2

  def __move_paddles(self, deltaTimeMs: float):
    p1 = self.player1
    if p1.moving_up:
      p1.y_pos -= 100 * (deltaTimeMs / 500)
    if self.player1.moving_down:
      p1.y_pos += 100 * (deltaTimeMs / 500)

  def __move_ball(self, deltaTimeMs: float):
    self.__check_for_intersection()
    changeNo: BallState = self.checkForIntersection()

    if changeNo != BallState.BALL_DIDNT_TOUCH_PADDLE:
      self.ball.isTouchingPaddle = True
      self.ball.moveToInitialPosition()
      self.waitingToServe = True

    if not self.waitingToServe:
      if not self.__check_out_of_bounds():
        new_pos = self.__calculate_new_ball_position(changeNo)
        self.ball.move(new_pos=new_pos)


  def __is_ball_touching_player(self):
    p1 = self.player1
    p1_surface = pygame.Surface((p1.width, p1.height))
    p1_rect = pygame.Rect((0, 0), p1.width, p1.height)
    pygame.draw.rect(p1_surface, "green", p1_rect)
    p1_surface.blit()
    p1_mask = pygame.mask.from_surface(p1_surface)

    p2 = self.player1
    p2_surface = pygame.Surface((p2.width, p2.height))
    p2_rect : pygame.Rect((0, 0), p2.width, p2.height)
    pygame.draw.rect(p2_surface, "green", p2_rect)
    p2_surface.blit()
    p2_mask = pygame.mask.from_surface(p2_surface)

    b = self.ball
    b_center = (b.radius, b.radius)
    b_surface = pygame.Surface((b.radius* 2, b.radius*2))
    pygame.draw.circle(b_surface, "green", b_center, b.radius)
    b_surface.blit()
    b_mask = pygame.mask.from_surface(b_surface)
    return b_mask.overlap(p1_mask) or b_mask.overlap(p2_mask)

  
  def __check_out_of_bounds(self) -> bool:
    ball = self.ball
    if not pygame.Rect.colliderect(ball.rect, WORLD_RECT):
      print(f'ball is out of bounds {ball.rect}')
      self.waitingToServe = True
      self.ball.move_to_initial_position()
      return True
    
    return False
  

  def __calculate_new_ball_position(self, state: BallState) -> Position:
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
