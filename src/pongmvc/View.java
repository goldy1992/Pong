package pongmvc;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class View extends JFrame implements KeyListener, WindowListener
{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;

   public ScorePanel scorePanel;
   private GameArea1 gameArea;
   private MenuBar menuBar = new MenuBar();
   public GameArea1 getGameArea()
   {
	   return gameArea;
   }
   public View(Controller controller)
   {
        addKeyListener(controller);
        addKeyListener(Model.getPlayer1());
        addKeyListener(Model.getPlayer2());
        scorePanel = new ScorePanel();
        setFocusable(true);      
        addWindowListener(this);
        setTitle("Pong by Goldy");

        setSize(((int)GameDimensions.WINDOW_WIDTH),
                ((int)GameDimensions.WINDOW_HEIGHT)+ 25);

        //setResizable(false);
        Container contents = getContentPane();
        this.setJMenuBar(menuBar);
        contents.setLayout(new BorderLayout());
        contents.add(scorePanel, BorderLayout.PAGE_START);      


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //gameArea = new GameArea1();
        gameArea = GameArea1.makeGameArea();

        contents.add(gameArea, BorderLayout.CENTER);
                final int FPS = 60;
                final FPSAnimator animator = new FPSAnimator(gameArea, FPS, true);
                      animator.start(); // start the animation loop
            setVisible(true);
   } // constructor


   @Override
   public void keyTyped(KeyEvent e) 
   {
	   // TODO Auto-generated method stub
   }


   @Override
   public void keyPressed(KeyEvent e) 
   {
	// TODO Auto-generated method stub
   }

   @Override
   public void keyReleased(KeyEvent e) 
   {
	// TODO Auto-generated method stub
   }
    @Override
    public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub

    }
    @Override
    public void windowClosing(WindowEvent e) {
            // TODO Auto-generated method stub
            MenuBar.saveData();
    }
    @Override
    public void windowClosed(WindowEvent e) {
            // TODO Auto-generated method stub

    }
    @Override
    public void windowIconified(WindowEvent e) {
            // TODO Auto-generated method stub

    }
    @Override
    public void windowDeiconified(WindowEvent e) {
            // TODO Auto-generated method stub

    }
    @Override
    public void windowActivated(WindowEvent e) {
            // TODO Auto-generated method stub

    }
    @Override
    public void windowDeactivated(WindowEvent e) {
            // TODO Auto-generated method stub

    }

} // class