#include <controller.h>
#include <constants.h>
#include <paddle.h>
#include <ball.h>
#include <iostream>
#include <SDL.h>

int defaultYPos = calculateDefaultYPosition(PADDLE_HEIGHT);

Position player1Position = {20, defaultYPos};
Color player1Color = {150,76,204,255};
Paddle player1(&player1Position, &player1Color);
Position player2Position = {20, defaultYPos};
Color player2Color = {150,76,204,255};
Paddle player2(&player2Position, &player2Color);
Ball ball;

Model model (&player1, &player2, &ball);
View view(&model);
SDL_Event e;

bool isRunning = true;

void run() {
	view.init();
    while (isRunning) {
        update();
    }
}


Uint64 lastTicks = SDL_GetTicks64();


void update() {
    while( SDL_PollEvent( &e ) != 0 ) {
        if( e.type == SDL_QUIT )
        {
            isRunning = false;
        }
        else if( e.type == SDL_KEYDOWN ) {
            switch( e.key.keysym.sym ) {

				case SDLK_SPACE:
					std::cout << "jump" << std::endl;
					break;
				case SDLK_UP:
					model.getPlayer1() -> setMovingUp(true);
					break;
				case SDLK_DOWN:
					model.getPlayer1() -> setMovingDown(true);
					break;
				default:
					break;
			}
		} else if (e.type == SDL_KEYUP ) {
			switch( e.key.keysym.sym ) {
				case SDLK_UP:
					model.getPlayer1() ->setMovingUp(false);
					break;
				case SDLK_DOWN:
					model.getPlayer1()-> setMovingDown(false);
					break;
				default:
					break;
			}
    	}
	}
	Uint64 currentTicks = SDL_GetTicks64();
	Uint64 tickDifference = currentTicks - lastTicks;
	int deltaTimeMs = tickDifference;
	model.update(deltaTimeMs);
	view.update();
	lastTicks = currentTicks;
}