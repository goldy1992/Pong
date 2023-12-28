#include <SDL.h>
#include <stdio.h>
#include <view.h>
#include <string>
#include <iostream>

bool View::init()
{
	//Initialization flag
	bool success = true;

	//Initialize SDL
	if( SDL_Init( SDL_INIT_VIDEO ) < 0 )
	{
		printf( "SDL could not initialize! SDL Error: %s\n", SDL_GetError() );
		success = false;
	}
	else
	{
		// Set texture filtering to linear
		if( !SDL_SetHint( SDL_HINT_RENDER_SCALE_QUALITY, "1" ) )
		{
			printf( "Warning: Linear texture filtering not enabled!" );
		}

		//Create window
		gWindow = SDL_CreateWindow( "Pong MVC", SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, WINDOW_WIDTH, WINDOW_HEIGHT, SDL_WINDOW_SHOWN );
		if( gWindow == NULL )
		{
			printf( "Window could not be created! SDL Error: %s\n", SDL_GetError() );
			success = false;
		}
		else
		{
				//Create renderer for window
			gRenderer = SDL_CreateRenderer( gWindow, -1, SDL_RENDERER_ACCELERATED );
			if( gRenderer == NULL )
			{
				printf( "Renderer could not be created! SDL Error: %s\n", SDL_GetError() );
				success = false;
			} else {
				//Initialize renderer color
				SDL_SetRenderDrawColor( gRenderer, 0xFF, 0xFF, 0xFF, 0xFF );

								//Initialize PNG loading
				// int imgFlags = IMG_INIT_PNG;
				// if( !( IMG_Init( imgFlags ) & imgFlags ) )
				// {
				// 	printf( "SDL_image could not initialize! SDL_image Error: %s\n", IMG_GetError() );
				// 	success = false;
				// }	
			}	
		}
	}

	return success;
}

int WIDTH = 73;
int HEIGHT = 97;

bool View::loadMedia()
{
	//Loading success flag
	bool success = true;

	char file[] = "test_sprites/Player/p1_spritesheet.png";
	printf("loading from file %s\n", file);

	//player = CreatePlayer(gRenderer, file);
	return success;
}

void View::close()
{
	//Deallocate surfaces

	//Destroy window
	SDL_DestroyWindow( gWindow );
	gWindow = NULL;

	//Quit SDL subsystems
	SDL_Quit();
}

void View::renderBall(Ball* ball) {
	Position* position = ball -> getPosition();
	Color* color = ball -> getColor();
	int radius = ball -> getRadius();
		// std::cout << "drawing circle at position: (" 
	// 	+ std::to_string(ball -> getPosition() -> x) + ","
	// 	+ std::to_string(ball -> getPosition() -> y) + ")"
	// 	<< std::endl;
	DrawCircle(gRenderer, position, radius, color);

}



void View::renderPlayer(Paddle* player) {
	Position* position = player -> getPosition();
	Color* color = player -> getColor();
	SDL_SetRenderDrawColor( gRenderer, color->r, color->g, color->b, color->a );
	int x = (int)(position -> x),
		y = (int)(position -> y),
		w = (int)(player -> getWidth()),
		h = (int)(player -> getHeight());
	SDL_Rect bounds =  SDL_Rect { x, y, w, h};
	//std::cout << "model p1 pos from view BEFORE render: " + std::to_string(p1Position -> y) << std::endl;
    int result = SDL_RenderFillRect( gRenderer, &bounds);

}

void View::update() {
	// Clear screen
	SDL_SetRenderDrawColor( gRenderer, 0xFF, 0xFF, 0xFF, 0xFF );
	SDL_RenderClear( gRenderer );
	// draw players HERE

	renderBall(model -> getBall());
	renderPlayer(model -> getPlayer1());
	renderPlayer(model -> getPlayer2());
//    // Set render color to blue ( rect will be rendered in this color )
//    	Paddle* p1 = model -> getPlayer1();
// 	Position* p1Position = p1 -> getPosition();
// 	Color* p1Color = p1 -> getColor();
// 	SDL_SetRenderDrawColor( gRenderer, p1Color->r, p1Color->g, p1Color->b, p1Color->a );
// 	SDL_Rect bounds =  SDL_Rect { (int)(p1Position -> x), (int)(p1Position ->y), p1 -> getWidth(), p1 -> getHeight()};
// 	//std::cout << "model p1 pos from view BEFORE render: " + std::to_string(p1Position -> y) << std::endl;
//     int result = SDL_RenderFillRect( gRenderer, &bounds);
// 	//std::cout << "model p1 pos from view AFTER render: " + std::to_string(p1Position -> y)
// 	//+ " result: " + std::to_string(result) << std::endl;

//    	Paddle* p2 = model -> getPlayer2();
// 	Color* p2Color = p2 -> getColor();

  
	SDL_RenderPresent( gRenderer );

}