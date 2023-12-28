#pragma once
#include <color.h>
#include <constants.h>
#include <position.h>
#include <SDL.h>

double calculateDefaultYPosition(int paddleHeight);

float randomDirection();

void DrawCircle(
    SDL_Renderer* renderer, 
    Position* position, 
    int radius, 
    Color* color
);

