package com.github.goldy1992.pongmvc;

import java.util.Random;

/**
 * 
 * @author Goldy
 * This class is used to define the logic for the AI of both players as well as
 * the different difficulty levels available to play against. All methods in 
 * this class are static so to be accessible by the model class. This class was
 * made to reduce the complexity and the amount of code in the model class.
 */
public class AI 
{
    // this enum defines the difficulties available to compete against
	public static enum Difficulty{EASY, MEDIUM, HARD, IMPOSSIBLE};
	
	public static Difficulty currentDifficulty = Difficulty.IMPOSSIBLE;
	
	/**
	 * 
	 * @param currentTRYPosition the current top right co-ordinate of the ball
	 * @param hDistance the horizontal distance to the paddle
	 * @param direction number to represent the direction the ball is 
	 *         travelling in
	 * @return returns the estimated y-ordinate that the ball with be at 
	 *          when it can initially make contact with the paddle. 
	 */
	private static float findFinalYPosAtTRPoint_P2(float currentTRYPosition,
			                            float hDistance, 
			                            float direction)
	{
		// if the ball will touch the top before reaching the x position of the paddle
		if (currentTRYPosition + (direction * hDistance) <= 0)
			/* recursively calculate the position minus the distance travelled 
			   to the top and changing the direction */
			return findFinalYPosAtTRPoint_P2(0, hDistance - currentTRYPosition,
					       direction * -1 );
		/* else if the ball will touch the bottom before reaching the x 
		   position of the paddle */
		else if (currentTRYPosition + Ball.diameter + (direction * hDistance) 
				   >= GameDimensions.FIELD_HEIGHT )
			/* recursively calculate the position minus the distance travelled 
			   to the bottom and changing the direction */
			return findFinalYPosAtTRPoint_P2(GameDimensions.FIELD_HEIGHT, 
					      hDistance - (GameDimensions.FIELD_HEIGHT 
					                  - (currentTRYPosition + Ball.diameter)), 
					                   (direction * -1));
		/* otherwise we assume the ball will not change direction (i.e. touch 
		 * the sides) before reaching the x position of the paddle therefore we
		 * use a simple mathematical expression to calculate the position */
		 
		else return currentTRYPosition + (direction * hDistance);
	}
	
	private static float findFinalYPosAtTLPoint_P1(float currentTLYPosition,
            float hDistance, 
            float direction)
    {
       // if the ball will touch the top before reaching the x position of the paddle
       if (currentTLYPosition + (direction * hDistance) <= 0)
         /* recursively calculate the position minus the distance travelled 
            to the top and changing the direction */
          return findFinalYPosAtTRPoint_P2(0, hDistance - currentTLYPosition,
                                           direction * -1 );
       /* else if the ball will touch the bottom before reaching the x 
          position of the paddle */
       else if (currentTLYPosition + Ball.diameter + (direction * hDistance) 
                >= GameDimensions.FIELD_HEIGHT )
/* recursively calculate the position minus the distance travelled 
to the bottom and changing the direction */
return findFinalYPosAtTRPoint_P2(GameDimensions.FIELD_HEIGHT, 
hDistance - (GameDimensions.FIELD_HEIGHT 
          - (currentTLYPosition + Ball.diameter)), 
           (direction * -1));
/* otherwise we assume the ball will not change direction (i.e. touch 
* the sides) before reaching the x position of the paddle therefore we
* use a simple mathematical expression to calculate the position */

else return currentTLYPosition + (direction * hDistance);
}
	
	public static void moveAI_P2(Ball ball, Paddle player2)
	{
	   Point<Float, Float> TRBallPosition = ball.topRightPoint; 
	   Point<Float, Float> TLPaddlePosition = player2.topLeftPoint;

	   if (currentDifficulty == Difficulty.EASY)
	   {
		  if (ball.dx > 0)
		  {
			  if (ball.topRightPoint.getX() > 700 && ball.topLeftPoint.getX() < GameDimensions.TL_XPOS_PLAYER2)
			  {
		        Random random = new Random();
   		        float xDistanceToPaddle = GameDimensions.TL_XPOS_PLAYER2 
                        - TRBallPosition.getX();
                float yDistanceToTravel = xDistanceToPaddle * Math.abs(ball.dy/ball.dx);
                float yPosAtHittingPoint = (float) ((findFinalYPosAtTRPoint_P2(
        		                                TRBallPosition.getY(),
                                                yDistanceToTravel, 
                                                ball.dy/Math.abs(ball.dy) ) 
                                                + Ball.radius) 
                                                + (random.nextGaussian() * 2));
	            if (yPosAtHittingPoint < TLPaddlePosition.getY())
		           player2.move(TLPaddlePosition.getY()-7);
	            else if (yPosAtHittingPoint > player2.bottomLeftPoint.getY())
		           player2.move(player2.topLeftPoint.getY()+7);
	         } // inner if
		  } // if dx > 0
		  else 
		  {
			  if (player2.topLeftPoint.getY() < Paddle.PADDLE_YPOS_FOR_GAME_START)
			     player2.move(player2.topLeftPoint.getY()+7);
			  //else if (player2.centrePoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
			  else if (player2.topLeftPoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
				 player2.move(player2.topLeftPoint.getY()-7);
		  } // else if
		  
	   } // if for EASY

	   else if (currentDifficulty == Difficulty.MEDIUM);
	   else if (currentDifficulty == Difficulty.HARD);
	   else if (currentDifficulty == Difficulty.IMPOSSIBLE)
	   {
		   if (ball.dx > 0)
		   {
			   
			  if ((ball.topRightPoint.getX() > GameDimensions.TL_XPOS_PLAYER1 + Paddle.PADDLE_WIDTH) && ball.topLeftPoint.getX() < GameDimensions.TL_XPOS_PLAYER2)
			  {
		        float xDistanceToPaddle = GameDimensions.TL_XPOS_PLAYER2 
                     - TRBallPosition.getX();
                float yDistanceToTravel = xDistanceToPaddle * Math.abs(ball.dy/ball.dx);
                float yPosAtHittingPoint = (float) ((findFinalYPosAtTRPoint_P2(
     		                                TRBallPosition.getY(),
                                             yDistanceToTravel, 
                                             ball.dy/Math.abs(ball.dy) ) 
                                             + Ball.radius));
	            if (yPosAtHittingPoint < TLPaddlePosition.getY())
		           player2.move(TLPaddlePosition.getY()-7);
	            else if (yPosAtHittingPoint > player2.bottomLeftPoint.getY())
		           player2.move(player2.topLeftPoint.getY()+7);
	         } // inner if
		   } // if ball .dx > 0
		  
		  else 
		  {
			  if (player2.topLeftPoint.getY() < Paddle.PADDLE_YPOS_FOR_GAME_START)
			     player2.move(player2.topLeftPoint.getY()+7);
			  //else if (player2.centrePoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
			  else if (player2.topLeftPoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
				 player2.move(player2.topLeftPoint.getY()-7);
		  } // else if

	   } // if for impossible
	} // moveAI_P2
	
	public static void moveAI_P1(Ball ball, Paddle player1)
	{
		Point<Float, Float> TLBallPosition = ball.topLeftPoint; 
		Point<Float, Float> TRPaddlePosition = player1.topRightPoint;
		
		if (currentDifficulty == Difficulty.EASY)
		{
			if (ball.dx < 0)
			{
				if (ball.topLeftPoint.getX() > GameDimensions.TL_XPOS_PLAYER1 + Paddle.PADDLE_WIDTH 
						&& ball.topLeftPoint.getX() < (GameDimensions.TL_XPOS_PLAYER1 + Paddle.PADDLE_WIDTH) + (GameDimensions.TL_XPOS_PLAYER2-700) )
				{
			        Random random = new Random();
	   		        float xDistanceToPaddle = TLBallPosition.getX() - (GameDimensions.TL_XPOS_PLAYER1 + Paddle.PADDLE_WIDTH);
	                float yDistanceToTravel = xDistanceToPaddle * Math.abs(ball.dy/ball.dx);
	                float yPosAtHittingPoint = (float) ((findFinalYPosAtTLPoint_P1(
                            TLBallPosition.getY(),
                            yDistanceToTravel, 
                            ball.dy/Math.abs(ball.dy)) 
                            + Ball.radius) 
                            + (random.nextGaussian() * 2));
	                if (yPosAtHittingPoint < TRPaddlePosition.getY())
	 		           player1.move(TRPaddlePosition.getY()-7);
	 	            else if (yPosAtHittingPoint > player1.bottomRightPoint.getY())
	 		           player1.move(player1.topRightPoint.getY()+7);
				} // if within bounds
			}
				else 
				  {
					  if (player1.topRightPoint.getY() < Paddle.PADDLE_YPOS_FOR_GAME_START)
					     player1.move(player1.topRightPoint.getY()+7);
					  //else if (player2.centrePoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
					  else if (player1.topRightPoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
						 player1.move(player1.topRightPoint.getY()-7);
				  } // else if
			}
		 // difficulty easy
		else if (currentDifficulty == Difficulty.MEDIUM);
		else if (currentDifficulty == Difficulty.HARD);
		else if (currentDifficulty == Difficulty.IMPOSSIBLE)
		{
			if (ball.dx < 0)
			{
				if (ball.topLeftPoint.getX() > GameDimensions.TL_XPOS_PLAYER1 + Paddle.PADDLE_WIDTH 
						&& ball.topLeftPoint.getX() < (GameDimensions.TL_XPOS_PLAYER2) )
				{
			        
	   		        float xDistanceToPaddle = TLBallPosition.getX() - (GameDimensions.TL_XPOS_PLAYER1 + Paddle.PADDLE_WIDTH);
	                float yDistanceToTravel = xDistanceToPaddle * Math.abs(ball.dy/ball.dx);
	                float yPosAtHittingPoint = (float) ((findFinalYPosAtTLPoint_P1(
                            TLBallPosition.getY(),
                            yDistanceToTravel, 
                            ball.dy/Math.abs(ball.dy)) 
                            + Ball.radius));
	                if (yPosAtHittingPoint < TRPaddlePosition.getY())
	 		           player1.move(TRPaddlePosition.getY()-7);
	 	            else if (yPosAtHittingPoint > player1.bottomRightPoint.getY())
	 		           player1.move(player1.topRightPoint.getY()+7);
				} // if within bounds
			} // if ball.dx > 0
			else 
			{
			  if (player1.topRightPoint.getY() < Paddle.PADDLE_YPOS_FOR_GAME_START)
				     player1.move(player1.topRightPoint.getY()+7);
			  //else if (player2.centrePoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
			  else if (player1.topRightPoint.getY() > Paddle.PADDLE_YPOS_FOR_GAME_START)
						 player1.move(player1.topRightPoint.getY()-7);
		    } // else
		} // if impossible
		   
	} // moveAI_P1
	
} // class