


import random
from pygame import Surface
import pygame

from constants import transparent


class Particle:
    position : tuple[int, int]
    alpha: float
    radius: float
    dAlpha: float
    velocity : tuple[int, int]
    surface : Surface
    

particles : list[Particle] = []

def spawn_particle(position: tuple[int, int]):
    # if len(particles) > 10:
    #     return
    p = Particle()
    p.position = position
    p.radius = random.random() * 20
    p.dAlpha = random_from(500, 1000)
    p.alpha = random.random() * (255) 
    p.velocity = (random_from(-10, 10), random_from(-10, 10))
    p.surface = Surface((p.radius * 2, p.radius * 2))
    p.surface.set_colorkey(transparent)
    p.surface.fill(transparent)
    particles.append(p)

def update_particles(dt: float):
    #print(f'dt: {dt} alpha {particles[0].alpha} dAlpha: {particles[0].dAlpha} dcalc {(dt / particles[0].dAlpha)}')
    for p in particles:
        p.alpha -= 255 * (dt / p.dAlpha)
        p.position = (p.position[0] + (p.velocity[0] * (dt / 100)), p.position[1] + (p.velocity[1] * (dt / 100)) )

    particles[:] = [p for p in particles if p.alpha > 0]

 
def draw_particles(screen: Surface):
    for p in particles:
        pygame.draw.circle(p.surface, (0, 128, 0), center=(p.radius, p.radius), radius=p.radius)
        p.surface.set_alpha(p.alpha)
        screen.blit(p.surface, p.position)
def random_from(min, max):
    return random.random() * (max - min) + min