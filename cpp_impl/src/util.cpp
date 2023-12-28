
#include <iostream>
#include <position.h>
#include <random>
#include <SDL.h>
#include <string>
#include <util.h>


  std::random_device rd; // obtain a random number from hardware
    std::mt19937 gen(rd()); // seed the generator
    std::uniform_real_distribution<> distr(0.2, 0.6); // define the range



//  std::default_random_engine generator;
// std::uniform_real_distribution<float> distribution(0.2,0.6);

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

void DrawCircle(SDL_Renderer * renderer, Position* position, int radius, Color* color)
{
    int centreX = (int) position -> x;
    int centreY = (int) position -> y;
    const int diameter = (radius * 2);

    int x = (radius - 1);
    int y = 0;
    int tx = 1;
    int ty = 1;
    int error = (tx - diameter);

	SDL_SetRenderDrawColor( renderer, color->r, color->g, color->b, color->a );

   while (x >= y)
   {
      //  Each of the following renders an octant of the circle
      SDL_RenderDrawPoint(renderer, centreX + x, centreY - y);
      SDL_RenderDrawPoint(renderer, centreX + x, centreY + y);
      SDL_RenderDrawPoint(renderer, centreX - x, centreY - y);
      SDL_RenderDrawPoint(renderer, centreX - x, centreY + y);
      SDL_RenderDrawPoint(renderer, centreX + y, centreY - x);
      SDL_RenderDrawPoint(renderer, centreX + y, centreY + x);
      SDL_RenderDrawPoint(renderer, centreX - y, centreY - x);
      SDL_RenderDrawPoint(renderer, centreX - y, centreY + x);

      if (error <= 0)
      {
         ++y;
         error += ty;
         ty += 2;
      }

      if (error > 0)
      {
         --x;
         tx += 2;
         error += (tx - diameter);
      }
   }
}