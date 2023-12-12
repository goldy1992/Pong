package com.github.goldy1992.pongmvc;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static JLabel player1ScoreLabel = new JLabel();
    private static JLabel player2ScoreLabel = new JLabel();
    private JLabel scoreTitle = new JLabel();
    Font f = new Font("arial", Font.PLAIN, 35);
    public static int player1Score = 0;
    public static int player2Score = 0;
    
	public ScorePanel()
	{
		setLayout(new BorderLayout());
		add(player1ScoreLabel, BorderLayout.WEST);
		add(scoreTitle, BorderLayout.CENTER);
		add(player2ScoreLabel, BorderLayout.EAST);
		
		player1ScoreLabel.setFont(f);
		player2ScoreLabel.setFont(f);
		scoreTitle.setFont(f);
		
		scoreTitle.setText("Score");
		scoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		player1ScoreLabel.setText("          " + player1Score);
		player2ScoreLabel.setText(player2Score + "                ");
		
		player1ScoreLabel.setHorizontalAlignment(JLabel.LEFT);
		player2ScoreLabel.setHorizontalAlignment(JLabel.RIGHT);

	}
	
	public static void incPlayer1Score()
	{
		player1Score++;
		player1ScoreLabel.setText("          " + player1Score);
	}
	
	public static void incPlayer2Score()
	{
		player2Score++;
		player2ScoreLabel.setText(player2Score + "          ");
	}
	
	public static void setNewGame()
	{
		player1Score = 0;
		player2Score = 0;

		player1ScoreLabel.setText("          " + player1Score);
		player2ScoreLabel.setText(player2Score + "          ");
	}

}
