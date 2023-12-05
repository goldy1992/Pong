package com.github.goldy1992.pongmvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author Goldy
 *
 * The Pong Controller Class
 * This class is used to control the model and the view of the game as part of
 * the MVC structure. It has references to the View and the Model and contains
 * it's own thread that is used to update the game Model and graphics.
 */
public class Controller implements Runnable, KeyListener {
	
	// each controller has a View and a Model controlled in a thread it owns
	private final View view;
	private final Model model;
	private final Thread controllerThread;
	
	// stores initial colours from the data file
	public static Integer initialP1Colour = null;
	public static Integer initialP2Colour = null;
	public static Integer initialBallColour = null;
	public static Integer initialBackgroundColour = null;
	
	// An Enumeration to record whether it's single or Multiplayer or CPU vs CPU (Spectate)
	public static enum Mode {SINGLE, MULTI, SPECTATE};
	
	// stores the initial game mode from the data file
	public static Mode gameMode = Mode.MULTI;
	
	public Controller()
	{
	   getInputFromDataFile();
	   controllerThread = new Thread(this);
	   model = new Model();
	   view = new View(this);

	}
	
	/**
	 * Uses a Buffered Reader to read all the data from the data file and set
	 * the initial game settings from the last time that the game was played.
	 */
	private void getInputFromDataFile()
	{
            BufferedReader input = null;

            if (new File("data.dat").exists())
            {
               try
               {
                     input = new BufferedReader(new FileReader("data.dat"));

                     String player1Colour = input.readLine();
                     if (player1Colour != null)
                             initialP1Colour = Integer.parseInt(player1Colour);

                     String player2Colour = input.readLine();
                     if (player2Colour != null)
                             initialP2Colour = Integer.parseInt(player2Colour);

                     String ballColour = input.readLine();
                     if (ballColour != null)
                             initialBallColour = Integer.parseInt(ballColour);

                     String backgroundColour = input.readLine();
                     if (backgroundColour != null)
                             initialBackgroundColour = Integer.parseInt(backgroundColour);

                     String playerMode = input.readLine();
                     if (playerMode != null)
                             if (playerMode.equals("SINGLE"))
                                     gameMode = Mode.SINGLE;
                             else if (playerMode.equals("MULTI"))
                                     gameMode = Mode.MULTI;
                     else gameMode = Mode.SPECTATE;

               }
               catch(IOException e)
               {
                       System.err.println();
               } // catch

               /* this block makes sure that the input stream is closed after all
                  of the data has been read in */
               finally
               {
                       try 
                       {
                          if (input != null)
                              input.close();
                       } // try   
                       catch (IOException e)
                       {
                               System.err.println(e);
                       } // catch

               } // finally
            }
		
	} // getInputFromDataFile
	
	/**
	 * @return returns the View owned by the Controller
	 */
	public View getView()
	{
		return view;
	} // getView

	/**
	 * @return returns the Model owned by the Controller
	 */
	public Model getModel()
	{
		return model;
	} // getModel

	@Override
	public void keyTyped(KeyEvent e) 
	{	
		// unused
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		   if (!Model.isPaused)
			  if (Model.waitingToServe)
			     Model.waitingToServe = false;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// unused
	} // keyReleased
	
	/**
	 * This method calls the View to update the game graphics.
	 */
	private void updateGraphics()
	{
		view.getGameArea().repaint();
	} // updateGraphics

	/**
	 * This method calls the update method of the Model object.
	 */
	private void updateModel()
	{
            model.update();
	}
	
	/**
	 * @param delta the time between updates in milliseconds.
	 */
	public void gameLoop(double delta)
	{
            while(true)
            {
			
		// convert the time to seconds
                double nextTime = (double)System.nanoTime() / 1000000000.0;
                double maxTimeDiff = 0.5;
                int skippedFrames = 1;
                int maxSkippedFrames = 5;
                while(true)
                {
                    // convert the time to seconds
                    double currTime = (double)System.nanoTime() / 1000000000.0;
                    if ( (currTime - nextTime) > maxTimeDiff ) 
                        nextTime = currTime;
                    if ( currTime >= nextTime )
                    {
                        // assign the time for the next update
                        nextTime += delta;
                        updateModel();
                
                        if((currTime < nextTime) || (skippedFrames > maxSkippedFrames))
                        {
                            updateGraphics();
                            skippedFrames = 1;
                        } // if
                        else
                            skippedFrames++;
               
                    } // if
                    else
                    {
                        // calculate the time to sleep
                        int sleepTime = (int)(1000.0 * (nextTime - currTime));
                        // sanity check
                        if(sleepTime > 0)
                        {
                            // sleep until the next update
                            try
                            {
                                Thread.sleep(sleepTime);
                            } // try
                            catch(InterruptedException e)
                            {
                                // do nothing
                            } // catch
                        } // if
                    } // else
                } // inner while
            } // outer while
	} // gameLoop
	
	/**
	 * This thread runs the game loop
	 */
	@Override
	public void run() 
	{
            gameLoop(0.01);		
	} // run
        
        public static Controller makeController()
        {
            Controller c = new Controller();
            c.controllerThread.start();
            return c;
        } // makeController

} // class