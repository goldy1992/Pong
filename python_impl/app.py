import math
import pygame
from pygame.mouse import get_pressed
import rotate_rect as rr
from math import pi
from pygame.transform import rotate
from particles import draw_particles, spawn_particle, update_particles
from constants import PADDLE_HEIGHT, PADDLE_START_X_POS, PADDLE_WIDTH, WINDOW_WIDTH, WINDOW_HEIGHT, transparent

# pygame setup
pygame.init()
screen = pygame.display.set_mode((800, 600))

clock = pygame.time.Clock()
running = True
height = 180
width = 60


inital_rect = pygame.Rect(0, 0, width, height)
initial_point = (50 + (200 / 2), inital_rect.top)
surface = pygame.Surface((width, height))
surface.set_colorkey(transparent)
surface.fill(transparent)

rotate_angle = 5
current_angle = 0

# Player 1
p1_y_pos = PADDLE_START_X_POS
p1_rect = pygame.Rect(40, p1_y_pos, PADDLE_WIDTH, PADDLE_HEIGHT)
print(f'p1_rect {p1_rect}')
p1_surface = pygame.Surface((width, height))
p1_surface.set_colorkey(transparent)
p1_surface.fill(transparent)
p1_move_up = False
p1_move_down = False
# pygame.draw.rect(p1_surface, "blue", p1_rect)
# screen.blit(p1_surface, p1_rect)

#surface_to_rotate = rotate(surface, current_angle)

surface_center = (400, 400)
#rotated_surface = rotate(surface, rotate_angle)
paused = False

current_surface = surface
current_rect = inital_rect
current_point = initial_point

getTicksLastFrame = pygame.time.get_ticks()



while running:

    # poll for events
    # pygame.QUIT event means the user clicked X to close your window
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_DOWN:
                p1_move_down = True
            if event.key == pygame.K_UP:
                p1_move_up = True
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_DOWN:
                p1_move_down = False
            if event.key == pygame.K_UP:
                p1_move_up = False


    
    mouse_pressed = get_pressed(3)
    if (mouse_pressed[0]):
  #      print(f"mouse 1 pressed")
        paused = True

            #       current_point = rr.rotate_corner(current_rect.center, current_point, rotate_angle)
       #     current_rect = rr.rotate_rect(current_rect, rotate_angle)

          #  current_surface = rotated_surface
    if (mouse_pressed[1]):
     #   print(f'middle pressed')
        current_point = rr.rotate_corner(surface_center, (surface_center[0] - (width / 2), surface_center[1]), current_angle * (math.pi / 180))
        print(f'setting current point: {current_point}')
        # surface_rect = surface_to_rotate.get_rect()
        # tl = surface_rect.topleft
        # tr = surface_rect.topright


    if (mouse_pressed[2]):
        print(f"right mouse pressed")
        paused = False
        t = pygame.time.get_ticks()
        deltaTimeMs = (t - getTicksLastFrame)
        getTicksLastFrame = t
            # current_point = initial_point
            # current_rect = inital_rect
            # current_surface = surface



    # fill the screen with a color to wipe away anything from last frame
    screen.fill("purple") 

    # draw rotation rect
    pygame.draw.rect(surface, "blue", inital_rect)
    surface_to_rotate = rotate(surface, current_angle)
    screen.blit(surface_to_rotate, surface_to_rotate.get_rect(center = surface_center))

    t = pygame.time.get_ticks()
    deltaTimeMs = (t - getTicksLastFrame)
    getTicksLastFrame = t
    if not paused:
        current_angle += (360 * (deltaTimeMs / 4000)) % 360
        spawn_particle(rr.rotate_corner(surface_center,surface_center, current_angle * (math.pi / 180)))
        update_particles(deltaTimeMs)
   # screen.blit(smoke_surface, (0,0))


 #   screen.blit(current_surface, (100, 100))
    draw_particles(screen)
    
    pygame.draw.rect(p1_surface, "blue", pygame.Rect(0, 0, width, height))

    if p1_move_up:
        p1_y_pos -= 100 * (deltaTimeMs / 500)
    if p1_move_down:
        p1_y_pos += 100 * (deltaTimeMs / 500)

    
  
    # move player
    screen.blit(p1_surface, p1_surface.get_rect(left=40, top = p1_y_pos))



    # flip() the display to put your work on screen
    pygame.display.flip()

    clock.tick(60)  # limits FPS to 60


pygame.quit()