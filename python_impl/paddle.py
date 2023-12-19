import pygame
from constants import WINDOW_HEIGHT, Color

PADDLE_WIDTH = 60
PADDLE_HEIGHT = 180
PADDLE_START_Y_POS = (WINDOW_HEIGHT - PADDLE_HEIGHT) / 2

class Paddle:
    width = PADDLE_WIDTH
    height = PADDLE_HEIGHT
    x_pos = 20
    y_pos = PADDLE_START_Y_POS
    moving_up = False
    moving_down = False
    color: Color = (255, 255, 255)
    rect : pygame.Rect

    def __init__(self, x_pos: int) -> None:
        self.x_pos = x_pos
        self.__set_rect()
  
  
    def __set_rect(self):
        self.rect = pygame.Rect(self.x_pos, self.y_pos, self.width, self.height)


    def move(self, new_pos: int):
        self.y_pos = new_pos
        self.__set_rect()
    def move_delta(self, amount: int):
        self.y_pos += amount
        self.__set_rect()