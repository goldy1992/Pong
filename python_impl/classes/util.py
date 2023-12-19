import random

import pygame

from constants import WINDOW_HEIGHT, WINDOW_WIDTH 

GAUSSIAN_MEAN = 0
GAUSSIAN_STD = 1
WORLD_RECT = pygame.Rect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT)


def __next_gaussian() -> float:
   return random.gauss(GAUSSIAN_MEAN, GAUSSIAN_STD)


def random_direction() -> float: 
  """
    This method is used to calculate a random direction for the ball to 
    initially travel in
    @return returns 2 or -2 representing the magnitude and direction of the
    ball chosen at random
  """
  magnitude = __next_gaussian() * 0.5      
  return (magnitude - 3.0) if (magnitude < 0) else (magnitude + 3.0)


def create_collision_surface():
   return pygame.Surface((WINDOW_WIDTH,WINDOW_HEIGHT))
 