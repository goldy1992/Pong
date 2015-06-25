package pongmvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class GameArea extends JPanel 
{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private Paddle player1 = Model.getPlayer1();
   private Paddle player2 = Model.getPlayer2();
   private Ball ball = Model.getBall();
   public static Color backgroundColour = Color.BLUE;
   private Rectangle2D background = new Rectangle2D.Float(
           0f, 
           0f, 
           GameDimensions.WINDOW_WIDTH, 
           GameDimensions.FIELD_HEIGHT);
   
   public GameArea()
   {
	  setDoubleBuffered(true);
      if (Controller.initialBackgroundColour != null)
    	  backgroundColour = new Color(Controller.initialBackgroundColour);
   }
   
   public void paint(Graphics g)
   {
	   super.paint(g);
	   Graphics2D g2 = (Graphics2D) g ;
	    
	    g2.setColor(backgroundColour);
	    g2.fill(background);

	    g2.setColor(player1.getColour());    
	    g2.draw(player1.paddleImage);
	    g2.fill(player1.paddleImage);

	    g2.setColor(player2.getColour());
	    g2.draw(player2.paddleImage);
	    g2.fill(player2.paddleImage);
	    g2.setColor(ball.getColour());
	    g2.draw(ball.ballImage);
	    g2.fill(ball.ballImage);
	    
        Toolkit.getDefaultToolkit().sync();
        g2.dispose();
   }
   
   
   public void update()
   {
      repaint();
   }
}
