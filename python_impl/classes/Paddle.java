package com.github.goldy1992.pongmvc;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

/**
 * 
 * @author Goldy
 * 
 * This class defines a Paddle in the Pong game as well as it's position, 
 * colour and direction of movement.
 */
public class Paddle implements KeyListener
{
   // sets the default paddle sizes
   public static final int PADDLE_WIDTH = 20;
   public static final int PADDLE_HEIGHT = 100;
   // the distance the paddle will be placed from the edge of the field
   public static final float PADDLE_X_DISTANCE_FROM_FIELD_EDGE = 20f;
   public static final float PADDLE_YPOS_FOR_GAME_START = 50.0f;
   private boolean moveDown = false;
   private boolean moveUp = false;
   private final int upKey;
   private final int downKey;
   public Point<Float, Float> topRightPoint;
   public Point<Float, Float> topLeftPoint;
   public Point<Float, Float> bottomRightPoint;
   public Point<Float, Float> bottomLeftPoint;
   public Point<Float, Float> centrePoint;
   // lines of intersection (LOI)
   public Line2D topLOI;
   public Line2D bottomLOI;
   public Line2D leftLOI;
   public Line2D rightLOI;  
   private Color paddleColour = Color.WHITE;
   public Rectangle2D paddleImage;

   @Override
   public synchronized void keyPressed(KeyEvent e) 
   {
        if(e.getKeyCode() == upKey)
        {
           moveUp = true;
           moveDown = false;
        }
        if(e.getKeyCode() == downKey)
        {
           moveUp = false;
           moveDown = true;
        }                   
   }

   @Override
   public synchronized void keyReleased(KeyEvent e) 
   {
      if(e.getKeyCode() == upKey)
         moveUp = false;
          
      if(e.getKeyCode() == downKey) 
        moveDown = false;
   }

   @Override
   public void keyTyped(KeyEvent e) 
   { // not needed
   }

   public Paddle(float startXPosition, int upKey, int downKey)
   {
      this.upKey = upKey;
      this.downKey = downKey;

      topLeftPoint = new Point<>(startXPosition, 
                               PADDLE_YPOS_FOR_GAME_START);
      //System.out.println("start: " + PADDLE_YPOS_FOR_GAME_START);
      topRightPoint = new Point<>((startXPosition + PADDLE_WIDTH),
                                PADDLE_YPOS_FOR_GAME_START); 
      
      bottomLeftPoint = new Point<>(startXPosition, 
                               (PADDLE_YPOS_FOR_GAME_START - PADDLE_HEIGHT));
      bottomRightPoint = new Point<>(
    		                    (startXPosition + PADDLE_WIDTH),
                                (PADDLE_YPOS_FOR_GAME_START - PADDLE_HEIGHT)); 
      centrePoint = new Point<>(
    		                       topLeftPoint.getX() + (PADDLE_WIDTH / 2),
    		                       topLeftPoint.getY() - (PADDLE_HEIGHT / 2) );
      
      paddleImage = new Rectangle2D.Float(startXPosition, 
                                          PADDLE_YPOS_FOR_GAME_START,
                                          PADDLE_WIDTH, 
                                          PADDLE_HEIGHT);   
   }
   
   public void setColour(Color colour)
   {
	   paddleColour = colour;
   }
   
   public void setColor(int rgbColour)
   {
      paddleColour = new Color(rgbColour);
   }
   
   public Color getColour()
   {
      return paddleColour;
   } // getColour

   public void move(float newHeight)
   {
        if ( (newHeight <= GameDimensions.FIELD_AMPLITUDE) 
              &&  
              ((newHeight - PADDLE_HEIGHT) >= (GameDimensions.FIELD_AMPLITUDE) * -1.0f) )
        {   
            topLeftPoint.setY(newHeight);
            topRightPoint.setY(newHeight);
            bottomLeftPoint.setY(newHeight - PADDLE_HEIGHT);
            bottomRightPoint.setY(newHeight - PADDLE_HEIGHT);
            centrePoint.setY(newHeight - (PADDLE_HEIGHT / 2));

            paddleImage.setRect(topLeftPoint.getX(), 
                                        topLeftPoint.getY(), 
                                        PADDLE_WIDTH, 
                                        PADDLE_HEIGHT);
        } // if
   } // move

    public boolean isMovingUp()
    {
        if(moveUp)
           return true;
        else
           return false;
    }

    public boolean isMovingDown()
    {
       if(moveDown)
          return true;
       else
          return false;
    }

} // class