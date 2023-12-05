package com.github.goldy1992.pongmvc;

public class Point<X, Y>
{
   private X x;
   private Y y;

   public Point()
   {
      this.x= null;
      this.y = null;
   }

   public Point(X x, Y y)
   {
      this.x = x;
      this.y = y;
   }

   public synchronized X getX()
   {
      return x;
   }

   public synchronized Y getY()
   {
      return y;
   }

   public synchronized void set(X x, Y y)
   {
      this.x = x;
      this.y = y;
   }

   public synchronized void setX(X x)
   {
      this.x = x;
   }

   public synchronized void setY(Y y)
   {
      this.y = y;
   }
}