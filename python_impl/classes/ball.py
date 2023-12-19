import pygame
from classes.util import create_collision_surface
from constants import WINDOW_HEIGHT, WINDOW_WIDTH, Color, Position

DEFAULT_POSITION: Position = (400, 300)

class Ball:
  color: Color = (255, 255, 255)
  radius: float = 0
  dx: float = 0
  dy: float = 0
  position: Position = (400, 300)
  rect: pygame.Rect 

  def __init__(self, radius: float, position: Position = DEFAULT_POSITION):
    self.radius = radius
    self.position = position
    self.rect = pygame.draw.circle(pygame.Surface((WINDOW_WIDTH, WINDOW_HEIGHT)), "white", position, radius)


  def __set_rect(self):
    self.rect = pygame.draw.circle(create_collision_surface(), "white", self.position, self.radius)
  

  def move_to_initial_position(self): 
    """    
      Moves the ball to the initial position
    """
    # set the ball stationary
    self.dx = 0
    self.dy = 0
    self.position = DEFAULT_POSITION
    self.__set_rect()
   
   
  def __str__(self):
    """
      Used for debugging purposes outputs the the co-ordinates of each position. 
    """
    return f'Ball({self} with center: {self.position}, radius: {self.radius}, color: {self.color})'


  def move(self, new_pos: Position):
    self.position = new_pos
    self.__set_rect()

#     	 if(!hasChangedDirection)
#          {
#     		 // uses changeNo to decide how to react to the intersections
#              switch (changeNo)
#              {
#                 case 0: dx = -1.1f * dx;
#                          dy = -1.1f * dy;
#                          break;
#                 case 1: dy = -1.05f * dy;
#                          break;
#                 case 2: dx = -1.05f * dx;
#                 default : break;
#              } // switch
#     		 hasChangedDirection = true;
#          } // inner if
#          isTouchingPaddle = false;
#       } // outer if
