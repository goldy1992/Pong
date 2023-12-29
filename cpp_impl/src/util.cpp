
#include <iostream>
#include <position.h>
#include <random>
#include <SDL.h>
#include <string>
#include <util.h>

std::random_device rd; // obtain a random number from hardware
std::mt19937 gen(rd()); // seed the generator
std::uniform_real_distribution<> distr(0.2, 0.6); // define the range

double calculateDefaultYPosition(int paddleHeight) {
    return (WINDOW_HEIGHT - paddleHeight) / 2.0;
}

float randomDirection() {
    float randomSpeed = distr(gen);
    float randomDirection = distr(gen);

    int multiplier = randomDirection < 0.4 ? -1 : 1;
    std::cout << "generated random speed of " + std::to_string(randomSpeed)
    + ", random directon: " + std::to_string(randomDirection)
    + " multiplier: " + std::to_string(multiplier) << std::endl;
    return randomSpeed * multiplier;
}


int randomIntInRange(int start, int end) {
    std::uniform_int_distribution<> intDistr(start, end);
    return intDistr(gen);

}

void DrawCircle(SDL_Renderer * renderer, Position* position, int radius, Color* color)
{
    int centreX = (int) position -> x;
    int centreY = (int) position -> y;
    const int diameter = (radius * 2);

	SDL_SetRenderDrawColor( renderer, color->r, color->g, color->b, color->a );


    for (int w = 0; w < radius * 2; w++)
    {
        for (int h = 0; h < radius * 2; h++)
        {
            int dx = radius - w; // horizontal offset
            int dy = radius - h; // vertical offset
            if ((dx*dx + dy*dy) <= (radius * radius))
            {
                SDL_RenderDrawPoint(renderer, centreX + dx, centreY + dy);
            }
        }
    }
}