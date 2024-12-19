package com.github.goldy1992.pongmvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class View extends JFrame implements KeyListener, WindowListener
{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;

   public ScorePanel scorePanel;
   private GameArea gameArea = new GameArea();
   private MenuBar menuBar = new MenuBar();
   public GameArea getGameArea()
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

        setSize((GameDimensions.WINDOW_WIDTH),
                (GameDimensions.WINDOW_HEIGHT)+ 25);
        setResizable(false);


        Container contents = getContentPane();
        this.setJMenuBar(menuBar);
        contents.setLayout(new BorderLayout());
        contents.add(scorePanel, BorderLayout.PAGE_START);      


        setDefaultCloseOperation(EXIT_ON_CLOSE);



        contents.add(gameArea, BorderLayout.CENTER);
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