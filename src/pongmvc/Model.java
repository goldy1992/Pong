package pongmvc;

import java.awt.Color;
import java.awt.event.KeyEvent;
import pongmvc.Controller.Mode;

/**
 * 
 * @author goldy
 *
 */
public class Model
{
    // definitions representing where about's the ball hit the paddle 
    private static final int BALL_TOUCHED_PADDLE_CORNER = 0;
    private static final int BALL_TOUCHED_PADDLE_TOP = 1;
    private static final int BALL_TOUCHED_PADDLE_SIDE = 2;
    private static final int BALL_DIDNT_TOUCH_PADDLE = 7;

    private static Paddle player1;
    private static Paddle player2;
    private static Ball ball;
    public static boolean isPaused;
    public static boolean waitingToServe;
    public static boolean setNewGame = false;
	
	
    public Model()
    {
        // creates player 1 with controls w for up s for down
        player1 = new Paddle(GameDimensions.TL_XPOS_PLAYER1, 
                                     KeyEvent.VK_W, 
                                     KeyEvent.VK_S);		

        /* sets the colour for player 1 if it has been described in the data 
         * file as the program loads 
         */
        if (Controller.initialP1Colour != null)
           player1.setColor(Controller.initialP1Colour);
        else
                player1.setColour(Color.WHITE);	

        // creates player 2 with controls up key for up down key for down		
        player2 = new Paddle(GameDimensions.TL_XPOS_PLAYER2, 
                                      KeyEvent.VK_UP, 
                                      KeyEvent.VK_DOWN);

        /* sets the colour for player 2 if it has been described in the data 
         * file as the program loads 
         */
        if (Controller.initialP2Colour != null)
           player2.setColor(Controller.initialP2Colour);
        else
                player2.setColour(Color.WHITE);


        ball = new Ball();

            // initially pauses the game so the players can sta
        isPaused = false;	
        waitingToServe = true;
    }

    /**
     * 
     * @return returns the paddle object for player 1
     */
    public static Paddle getPlayer1()
    {
        return player1;
    }
    /**
     * 
     * @return returns the paddle object for player 2
     */
    public static Paddle getPlayer2()
    {
            return player2;
    }

    /**
     * 
     * @return returns the ball object in the game
     */
    public static Ball getBall()
    {
            return ball;
    }



    private int checkForIntersection()
    {	
       if ((ballTouchTopP1() && ball.dy > 0) || 
               (ballTouchTopP2() && ball.dy > 0) ||
               (ballTouchBottomP1() && ball.dy > 0) || 
               (ballTouchBottomP2() && ball.dy > 0))
               ball.dy = 1.5f  * ball.dy;

       if ( (ballTouchTopP1() && ballTouchSideP1() && ball.dy > 0) ||
                     (ballTouchBottomP1() && ballTouchSideP1() && ball.dy < 0))
               return BALL_TOUCHED_PADDLE_CORNER;

       else if ((ballTouchTopP1() && ball.dy > 0) || 
                          (ballTouchBottomP1() && ball.dy < 0))
      return BALL_TOUCHED_PADDLE_TOP;

       else if (ballTouchSideP1())
          return BALL_TOUCHED_PADDLE_SIDE;


       if ( (ballTouchTopP2() && ballTouchSideP2() && ball.dy > 0) ||
                     (ballTouchBottomP2() && ballTouchSideP2() && ball.dy < 0))
               return BALL_TOUCHED_PADDLE_CORNER;

       else if ((ballTouchTopP2() && ball.dy > 0) || 
                         (ballTouchBottomP2() && ball.dy < 0))
      return BALL_TOUCHED_PADDLE_TOP;

       else if (ballTouchSideP2())
          return BALL_TOUCHED_PADDLE_SIDE;	

       return BALL_DIDNT_TOUCH_PADDLE;
    }

    private void checkForPaddleMovements()
    {
       if (Controller.gameMode.equals(Mode.MULTI))
       {
          if(player2.isMovingUp())
                  player2.move(player2.topLeftPoint.getY()+7);
          else if(player2.isMovingDown())
                  player2.move(player2.topLeftPoint.getY()-7);

          if(player1.isMovingUp())
                  player1.move(player1.topLeftPoint.getY()+7);
          else if(player1.isMovingDown())
                  player1.move(player1.topLeftPoint.getY()-7);
       } // if
       else if (Controller.gameMode.equals(Mode.SINGLE))
       {
               if(player1.isMovingUp())
                  player1.move(player1.topLeftPoint.getY()+7);
               else if(player1.isMovingDown())
                      player1.move(player1.topLeftPoint.getY()-7);
          AI.moveAI_P2(ball, player2);
       } // else if
       else
       {
               AI.moveAI_P2(ball, player2);
               AI.moveAI_P1(ball, player1);
       } // else

    } // checkForPaddleMovements

    public void update()
    {
        if (Model.setNewGame == true)
        {
            ball.moveToInitialPosition();
            player1.move(Paddle.PADDLE_YPOS_FOR_GAME_START);
            player2.move(Paddle.PADDLE_YPOS_FOR_GAME_START);
            Model.setNewGame = false;
        }
        // executes usually when a new game has not been requested
        else if (!Model.isPaused)
        {
            checkForPaddleMovements();
            int changeNo = checkForIntersection();

            if (changeNo != BALL_DIDNT_TOUCH_PADDLE)
                    ball.isTouchingPaddle = true;

            if (ball.isOutOfBounds())
            {	        	
                    ball.moveToInitialPosition();
                    Model.waitingToServe = true;
            }
            if (!waitingToServe)
                    ball.move(changeNo);
        } // else if
    }

    // collision check accessory methods

    public boolean ballTouchTopP1()
    {
       return ball.ballImage.intersects(player1.topLeftPoint.getX(), 
            player1.topLeftPoint.getY(), 
            Paddle.PADDLE_WIDTH, 1);
    } // ballTouchTopP1
    
    public boolean ballTouchSideP1()
    {   
        boolean x = ball.ballImage.intersects(player1.topRightPoint.getX()-1, 
            player1.topRightPoint.getY(), 
        1, Paddle.PADDLE_HEIGHT);
        if (x)
        System.out.print("touched p1");
        return x;
    } // ballTouch Side P1
    public boolean ballTouchBottomP1()
    {
        return ball.ballImage.intersects(player1.bottomLeftPoint.getX(), 
            player1.bottomLeftPoint.getY(), 
            Paddle.PADDLE_WIDTH, 1);	
    } // ballTouchBottomP1
    public boolean ballTouchTopP2()
    {
        return ball.ballImage.intersects(player2.topLeftPoint.getX(), 
               player2.topLeftPoint.getY(), 
               Paddle.PADDLE_WIDTH, 1);
    } // ballTouchTopP2
    public boolean ballTouchSideP2()
    {
        boolean x = ball.ballImage.intersects(player2.topLeftPoint.getX(), 
                       player2.topLeftPoint.getY(), 
                       1, Paddle.PADDLE_HEIGHT);
        if (x)
            System.out.print("touched p2");
        return x;	
    }// ballTouchSideP2
    public boolean ballTouchBottomP2()
    {
            return ball.ballImage.intersects(player2.bottomLeftPoint.getX(), 
                   player2.bottomLeftPoint.getY(), 
                   Paddle.PADDLE_WIDTH, 1);
    } // ballTouchBottomP2

} // class