package com.github.goldy1992.pongmvc;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2ES1.GL_LIGHT_MODEL_AMBIENT;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_COLOR_MATERIAL;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHT1;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_NORMALIZE;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_POSITION;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.nio.FloatBuffer;
import static com.github.goldy1992.pongmvc.GameDimensions.*;
import static com.github.goldy1992.pongmvc.Paddle.*;

public class GameArea1 extends GLJPanel implements GLEventListener
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
      private GLU glu;  // for the GL Utility
      
  private float cameraRotAngle = 0;
   
   public GameArea1()
   {
	  setDoubleBuffered(true);
     // if (Controller.initialBackgroundColour != null)
    	//  backgroundColour = new Color(Controller.initialBackgroundColour);
   }
   

    @Override
    public void init(GLAutoDrawable drawable) 
    {
        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glEnable(GL_COLOR_MATERIAL);
        gl.glEnable(GL_LIGHTING); //Enable lighting
        gl.glEnable(GL_LIGHT0); //Enable light #0
        gl.glEnable(GL_LIGHT1); //Enable light #1
        gl.glEnable(GL_NORMALIZE); //Automatically normalize normals      
        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting        
    } 

    @Override
    public void dispose(GLAutoDrawable glad) {
       
    }
    
    private void drawPaddle1(GL2 gl)
    {
        gl.glBegin(GL2.GL_QUADS);
 
        //Top
        gl.glNormal3f(0f, 1.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, 50.0f, 10.0f);

        //Bottom
        gl.glNormal3f(0f, -1.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, -50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, -50.0f, 10.0f);
    
        //Front
        gl.glNormal3f(1.0f, 0.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, -50.0f, 10.0f);

       // back
        gl.glNormal3f(-1.0f, 0.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, -50.0f, 10.0f);        

       // left side
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, -50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, -50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, 50.0f, 10.0f);        

       // right side
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER1 + PADDLE_WIDTH, 50.0f, -10.0f);                
        gl.glEnd();        
    }
    
    private void drawPaddle2(GL2 gl)
    {
        gl.glBegin(GL2.GL_QUADS);
 
        //Top
        gl.glNormal3f(0f, 1.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, 50.0f, 10.0f);

        //Bottom
        gl.glNormal3f(0f, -1.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, -50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, -50.0f, 10.0f);
    
        //Front
        gl.glNormal3f(-1.0f, 0.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, -50.0f, 10.0f);

       // back
        gl.glNormal3f(1.0f, 0.0f, 0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, -50.0f, 10.0f);        

       // left side
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, 50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, -50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, -50.0f, 10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, 50.0f, 10.0f);        

       // right side
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, 50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2 + PADDLE_WIDTH, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, -50.0f, -10.0f);
        gl.glVertex3f(TL_XPOS_PLAYER2, 50.0f, -10.0f);                
        gl.glEnd();        
    }

    @Override
    public void display(GLAutoDrawable drawable) 
    {
        cameraRotAngle+= 0.01;
        if (cameraRotAngle > 2 * Math.PI)
            cameraRotAngle = 0;
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1200);
        glu.gluLookAt(//1000 * Math.cos(cameraRotAngle)
                0, 0, 
                //1000 * Math.sin(cameraRotAngle), 
                1000, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        

      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity();


      //gl.glTranslatef(0.0f, 0.0f, -8.0f);

      //Add ambient light
      float ambientColorf[] = {0.2f, 0.2f, 0.2f, 1.0f}; //Color (0.2, 0.2, 0.2)
      FloatBuffer ambientColor = FloatBuffer.wrap(ambientColorf);    
      gl.glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambientColor);

      //Add positioned light
      float lightColor0f[] = {0.5f, 0.5f, 0.5f, 1.0f}; //Color (0.5, 0.5, 0.5)
      FloatBuffer lightColor0 = FloatBuffer.wrap(lightColor0f);
      float lightPos0f[] = {4.0f, 0.0f, 8.0f, 1.0f}; //Positioned at (4, 0, 8)
      FloatBuffer lightPos0 = FloatBuffer.wrap(lightPos0f);
      gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, lightColor0);
      gl.glLightfv(GL_LIGHT0, GL_POSITION, lightPos0);

      //Add directed light
      float lightColor1f[] = {0.5f, 0.2f, 0.2f, 1.0f}; //Color (0.5, 0.2, 0.2)
      FloatBuffer lightColor1 = FloatBuffer.wrap(lightColor1f);
      //Coming from the direction (-1, 0.5, 0.5)
      float lightPos1f[] = {-1.0f, 0.5f, 0.5f, 0.0f};
      FloatBuffer lightPos1 = FloatBuffer.wrap(lightPos1f);
      gl.glLightfv(GL_LIGHT1, GL_DIFFUSE, lightColor1);
      gl.glLightfv(GL_LIGHT1, GL_POSITION, lightPos1);


      //Set the colour here
      gl.glColor3f(1.0f, 1.0f, 0.0f);
      
      gl.glPushMatrix();
      float pnt = player1.topLeftPoint.getY() - 50f;
      System.out.println(pnt);
      gl.glTranslatef(0, pnt, 0);
      drawPaddle1(gl);
      gl.glPopMatrix();
      
      
      gl.glPushMatrix();
      float pnt1 = player2.topLeftPoint.getY() - 50f;
      //System.out.println(pnt);
      gl.glTranslatef(0, pnt1, 0);
      drawPaddle2(gl);
      gl.glPopMatrix();
      
      
      GLUT glut = new GLUT();
      gl.glPushMatrix();
      gl.glTranslatef(ball.centrePoint.getX(), ball.centrePoint.getY(), 0);
      glut.glutSolidSphere(10, 50, 50);      
      gl.glPopMatrix();
        drawable.swapBuffers();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
 
      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;
 
      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);
 
      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 1, 1000.0); // fovy, aspect, zNear, zFar
 
      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
    }
    
    public static GameArea1 makeGameArea()
    {
        GameArea1 g = new GameArea1();
        g.addGLEventListener(g);
        return g;
    }
}
