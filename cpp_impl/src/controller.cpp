#include <controller.h>
#include <iostream>


void run() {
	view.init();
    while (isRunning) {
        update();
    }
}

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
					model.getPlayer1().setMovingUp(true);
					break;
				case SDLK_DOWN:
					model.getPlayer1().setMovingDown(true);
					break;
				default:
					break;
			}
		} else if (e.type == SDL_KEYUP ) {
			switch( e.key.keysym.sym ) {
				case SDLK_UP:
					model.getPlayer1().setMovingUp(false);
					break;
				case SDLK_DOWN:
					model.getPlayer1().setMovingDown(false);
					break;
				default:
					break;
			}
    	}
	}

	model.update();
	view.update();
}