import pygame
from classes.model import Model
from constants import transparent
from paddle import Paddle
screen = pygame.display.set_mode((800, 600))
pygame.display.set_caption("Pong MVC")

class View:
  model: Model = None

  p1_surface: pygame.Surface = None
  p2_surface: pygame.Surface = None
  ball_surface: pygame.Surface = None

  def __init__(self, model: Model):
    self.model = model
    self.p1_surface = pygame.Surface((model.player1.width, model.player1.height))
    self.p1_surface.set_colorkey(transparent)
    self.p1_surface.fill(transparent)
    self.p2_surface = pygame.Surface((model.player2.width, model.player2.height))
    self.p2_surface.set_colorkey(transparent)
    self.p2_surface.fill(transparent)

    self.ball_surface = pygame.Surface((model.player1.width, model.player1.height))
    self.ball_surface.set_colorkey(transparent)
    self.ball_surface.fill(transparent)


  def __draw_player(self, surface: pygame.Surface, player: Paddle):
    pygame.draw.rect(
      surface, 
      player.color, 
        pygame.Rect(
          0, 0, 
          player.width, 
          player.height)
        )
    screen.blit(
      surface, 
      surface.get_rect(left=player.x_pos, top = player.y_pos)) 
  
  
  
  def update(self):
    screen.fill("purple")   
    self.__draw_player(self.p1_surface, self.model.player1)
    self.__draw_player(self.p2_surface, self.model.player2)

    current_ball = self.model.ball
    current_radius = current_ball.radius
    pygame.draw.circle(
      self.ball_surface,
      self.model.ball.color, 
      (current_radius, current_radius),
      self.model.ball.radius
    )
    
    screen.blit(
      self.ball_surface, 
      self.ball_surface.get_rect(
        left=self.model.ball.position[0] - self.model.ball.radius, 
        top=self.model.ball.position[1] - self.model.ball.radius
      )
    )

    pygame.display.flip()




  