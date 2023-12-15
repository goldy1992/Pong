import pygame
from pygame.mouse import get_pressed

from classes.model import Model
from classes.view import View

# pygame setup
pygame.init()
clock = pygame.time.Clock()

isRunning: bool = True

model: Model = Model()
view: View = View(model = model)
ticksSinceLastFrame = pygame.time.get_ticks()

def run():
  while isRunning:
      update()
  pygame.quit()


def update():
  global ticksSinceLastFrame
  global paused
  global model
  global isRunning
  # poll for events
  for event in pygame.event.get():
    if event.type == pygame.QUIT:
      print(f'pygame quit is called')
      isRunning = False
    if event.type == pygame.KEYDOWN:
      if event.key == pygame.K_DOWN:
        model.player1.moving_down = True
      if event.key == pygame.K_UP:
        model.player1.moving_up = True
    if event.type == pygame.KEYUP:
      if event.key == pygame.K_DOWN:
        model.player1.moving_down = False
      if event.key == pygame.K_UP:
        model.player1.moving_up = False
      if event.key == pygame.K_SPACE:
        if not model.isPaused:
          if model.waitingToServe:
            model.waitingToServe = False
   
    mouse_pressed = get_pressed(3)
    if (mouse_pressed[0]):
      print(f"mouse 1 pressed")
      paused = True
      # current_point = rr.rotate_corner(current_rect.center, current_point, rotate_angle)
      # current_rect = rr.rotate_rect(current_rect, rotate_angle)
      #  current_surface = rotated_surface
    if (mouse_pressed[1]):
      print(f'middle pressed')
      #  current_point = rr.rotate_corner(surface_center, (surface_center[0] - (width / 2), surface_center[1]), current_angle * (math.pi / 180))
      #  print(f'setting current point: {current_point}')
      # surface_rect = surface_to_rotate.get_rect()
      # tl = surface_rect.topleft
      # tr = surface_rect.topright

    if (mouse_pressed[2]):
     print(f"right mouse pressed")
  #    paused = False
  t = pygame.time.get_ticks()
  deltaTimeMs = (t - ticksSinceLastFrame)
  ticksSinceLastFrame = t
  model.update(deltaTimeMs=deltaTimeMs)
  view.update()
  clock.tick(60)  # limits FPS to 60

