import pygame
from pygame import mixer
from pygame.mouse import get_pressed

from classes.model import Model
from classes.view import View


# pygame setup
pygame.init()
clock = pygame.time.Clock()

# Starting the mixer 
mixer.init() 
  
# Loading the song 
mixer.music.load("assets/game_start.mp3") 
  
# Setting the volume 
mixer.music.set_volume(0.7) 
  

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
      if event.key == pygame.K_RETURN: 
        mixer.music.play() 
      if event.key == pygame.K_DOWN:
        model.player1.moving_down = False
      if event.key == pygame.K_UP:
        model.player1.moving_up = False
      if event.key == pygame.K_SPACE:
        print(f'space pressed')
        if not model.isPaused:
          print(f'space pressed model not paused')
          if model.waitingToServe:
            print(f'space pressed model not paused waiting to serve')
            model.waitingToServe = False
   
    mouse_pressed = get_pressed(3)
    if (mouse_pressed[0]):
      print(f"mouse 1 pressed")
      paused = True

    if (mouse_pressed[1]):
      print(f'middle pressed')

    if (mouse_pressed[2]):
     print(f"right mouse pressed")

  t = pygame.time.get_ticks()
  deltaTimeMs = (t - ticksSinceLastFrame)
  ticksSinceLastFrame = t
  model.update(deltaTimeMs=deltaTimeMs)
  view.update()
  clock.tick(60)  # limits FPS to 60

