//Using SDL, standard IO, and strings
#include <controller.h>
#include <SDL.h>
#include <SDL_image.h>
#include <stdio.h>
#include <string>
#include <texture.h>
#include <player.h>
#include <constants.h>
#include <iostream>
#include <platform_controller.cpp>
#include <player_collision_detector.cpp>

//Screen dimension constants


Player* player;

PlatformController* platformController = new PlatformController();

//Starts up SDL and creates window
bool init();

//Loads media
bool loadMedia();

//Frees media and shuts down SDL
void close();

//The window we'll be rendering to
SDL_Window* gWindow = NULL;

SDL_Renderer* gRenderer;

void printCollision(CollisionType collisionType) {
	char* c;
	switch (collisionType) {
	case LEFT:	c = "Left"; break;
	case RIGHT:	c = "Right"; break;
	case TOP:	c = "Top"; break;
	case BOTTOM: c = "Bottom"; break;
	case TOP_LEFT:	c = "TopLeft"; break;
	case TOP_RIGHT:	c = "TopRight"; break;
	case BOTTOM_LEFT:	c = "BottomLeft"; break;
	case BOTTOM_RIGHT: c = "BottomRight"; break;
	case UNKNOWN: c = "Unknown"; break;
	case NONE: c = "None"; break;
	default: c = "Unknown"; break;
	}

	std::cout << "new collision of type: " << c << std::endl;
}
	
bool init()
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
		gWindow = SDL_CreateWindow( "SDL Tutorial", SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, SCREEN_WIDTH, SCREEN_HEIGHT, SDL_WINDOW_SHOWN );
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
				int imgFlags = IMG_INIT_PNG;
				if( !( IMG_Init( imgFlags ) & imgFlags ) )
				{
					printf( "SDL_image could not initialize! SDL_image Error: %s\n", IMG_GetError() );
					success = false;
				}	
			}	
		}
	}

	return success;
}

int WIDTH = 73;
int HEIGHT = 97;

bool loadMedia()
{
	//Loading success flag
	bool success = true;

	char file[] = "test_sprites/Player/p1_spritesheet.png";
	printf("loading from file %s\n", file);

	player = CreatePlayer(gRenderer, file);
	return success;
}

void close()
{
	//Deallocate surfaces

	//Destroy window
	SDL_DestroyWindow( gWindow );
	gWindow = NULL;

	//Quit SDL subsystems
	SDL_Quit();
}

int index = 0;

int main( int argc, char* args[] )
{
	run();
/*	std::cout << "calling main" << std::endl;
	//Start up SDL and create window
	if( !init() )
	{
		printf( "Failed to initialize!\n" );
	}
	else
	{
		//Load media
		if( !loadMedia() )
		{
			printf( "Failed to load media!\n" );
		}
		else
		{	
			//Main loop flag
			bool quit = false;

			//Event handler
			SDL_Event e;


			//While application is running
			while( !quit )
			{
				Player &p = *player;
				PlatformController &pc = *platformController;
				PlayerCollisionDetector* pcd = new PlayerCollisionDetector(player, pc.getPlatforms());
				//Handle events on queue
				while( SDL_PollEvent( &e ) != 0 )
				{
					//User requests quit
					if( e.type == SDL_QUIT )
					{
						quit = true;
					}
					//User presses a key
					else if( e.type == SDL_KEYDOWN )
					{
						//std::cout << "SDL Keydown" << std::endl;
						//Select surfaces based on key press
						switch( e.key.keysym.sym )
						{
							case SDLK_RIGHT:
								p.move(SPEED_RIGHT);
								break;
							case SDLK_LEFT:		
								p.move(SPEED_LEFT);
								break;

							case SDLK_SPACE:
								std::cout << "jump" << std::endl;
								p.jump();
								break;
							case SDLK_DOWN:
								std::cout << "duck" << std::endl;
								p.duck();
								break;
							default:
								break;
						}
					} else if (e.type == SDL_KEYUP ) {
					//	std::cout << "SDL Keyup" << std::endl;
						switch( e.key.keysym.sym )
						{
							case SDLK_DOWN:
								std::cout << "unduck" << std::endl;
								p.unduck();
								break;
							case SDLK_LEFT:			
							case SDLK_RIGHT:
							//	std::cout << "not moving" << std::endl;
								p.move(NOT_MOVING);
								break;
							default:
								break;
						}

					}
				}

				// Clear screen
				SDL_SetRenderDrawColor( gRenderer, 0xFF, 0xFF, 0xFF, 0xFF );
				SDL_RenderClear( gRenderer );
				//Render player
			//	std::cout << "calling update from main" << std::endl;

				pc.render(gRenderer);
				p.render(gRenderer);
				SDL_RenderPresent( gRenderer );

				pc.update();
				p.update();
				CollisionType ct = pcd->detectCollision();
				if (ct != CollisionType::NONE) {
					//printCollision(ct);
					pcd -> landedOnPlatform();
				}

	
			}
		}
	}

	//Free resources and close SDL
	close();

	return 0;
	*/
}