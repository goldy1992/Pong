package com.github.goldy1992.pongmvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.github.goldy1992.pongmvc.Controller.Mode;

public class MenuBar extends JMenuBar implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JMenu file = new JMenu("File");
	public JMenu edit = new JMenu("Edit");
	public JMenu play = new JMenu("Play");
    public JMenuItem exit = new JMenuItem("Exit");
    public JMenuItem singleplayer = new JMenuItem("Single Player");    
    public JMenuItem multiplayer = new JMenuItem("Multiplayer");    
    public JMenuItem spectate = new JMenuItem("Spectate");  
    public JMenu newGame = new JMenu("New Game");    
    public JMenuItem pause = new JMenuItem("Pause");    
    public JMenu colour = new JMenu("Colours");    
    public JMenuItem player1 = new JMenuItem("Player 1");
    public JMenuItem player2 = new JMenuItem("Player 2");    
    public JMenuItem ball = new JMenuItem("Ball");
    public JMenuItem background = new JMenuItem("Background");
    
    public MenuBar()
	{
	   this.add	(file);
	   this.add(edit);
	   this.add(play);

	   file.add(exit);	
	   edit.add(colour);
	   play.add(newGame);
       play.add(pause);
       
       colour.add(player1);
       colour.add(player2);
       colour.add(ball);
       colour.add(background);
       
	   newGame.add(singleplayer);
	   newGame.add(multiplayer);
	   newGame.add(spectate);
	   exit.addActionListener(this);
	   pause.addActionListener(this);
	   player1.addActionListener(this);
	   player2.addActionListener(this);
	   ball.addActionListener(this);
	   background.addActionListener(this);
	   singleplayer.addActionListener(this);
	   multiplayer.addActionListener(this);
	   spectate.addActionListener(this);
	
	   pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource() == exit)
		   {
			   saveData();
			   System.exit(0);	   
		   }
		   else if (e.getSource() == singleplayer)
		   {
			   Controller.gameMode = Mode.SINGLE;
			   ScorePanel.setNewGame();
			   Model.setNewGame = true;
			   Model.waitingToServe = true;
			   player2.setText("Computer");
		   }
		   else if (e.getSource() == multiplayer)
		   {
			   Controller.gameMode = Mode.MULTI;
			   ScorePanel.setNewGame();
			   Model.setNewGame = true;
			   Model.waitingToServe = true;
			   player2.setText("Player 2");
		   }
		   else if (e.getSource() == spectate)
		   {
			   Controller.gameMode = Mode.SPECTATE;
			   ScorePanel.setNewGame();
			   Model.setNewGame = true;
			   Model.waitingToServe = true;
			   player1.setText("Computer 1");
			   player2.setText("Computer 2");
		   }
		   else if (e.getSource() == pause)
		   {
			  Model.isPaused = !Model.isPaused;   
		   }
		   else if (e.getSource() == player1)
		   {
			   Color color = JColorChooser.showDialog(this, "Player 1 Colour", Color.white);
			   if (color != null)
			      Model.getPlayer1().setColour(color);
		   }
		   else if (e.getSource() == player2)
		   {
			   Color color = null;
			   if (Controller.gameMode.equals(Mode.SINGLE))
			      color = JColorChooser.showDialog(this, "Computer Colour", Color.white);
			   else
				  color = JColorChooser.showDialog(this, "Player 2 Colour", Color.white);
				   
			   if (color != null)
			      Model.getPlayer2().setColour(color);
		   }
		   else if (e.getSource() == ball)
		   {
			   Color color = JColorChooser.showDialog(this, "Ball Colour", Color.white);
			   if (color != null)
			      Model.getBall().setColour(color);
		   }
		   else if (e.getSource() == background)
		   {
			   Color color = JColorChooser.showDialog(this, "Background Colour", Color.white);
			   if (color != null)
			      GameArea.backgroundColour = color;
		   }
	
	}

	public static void saveData()
	{
		String player1Colour = String.valueOf(Model.getPlayer1().getColour().getRGB());
		String player2Colour = String.valueOf(Model.getPlayer2().getColour().getRGB());
		String ballColour = String.valueOf(Model.getBall().getColour().getRGB());
		String backgroundColour = String.valueOf(GameArea.backgroundColour.getRGB());
		String gameMode = null;
		if (Controller.gameMode == Mode.SINGLE)
		   gameMode = "SINGLE";
		else if (Controller.gameMode == Mode.MULTI)
			   gameMode = "MULTI";
		else gameMode = "SPECTATE";
		PrintWriter output = null;
		
		try
		{			
			output = new PrintWriter(new FileWriter("data.dat"));
			output.println(player1Colour);
			output.println(player2Colour);
			output.println(ballColour);
			output.println(backgroundColour);
			output.println(gameMode);
		}
		catch(IOException e)
		{
           System.err.println(e);
		}
		finally
		{
		   if (output != null)
		   {   
			  output.close();
		      if (output.checkError())
			      System.out.println("Something went wrong saing data!");
		   }
		}
	}
}
