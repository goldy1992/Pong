#include <platform.h>
#include <random>
#include <SDL.h>
#include <constants.h>
#include <iostream>
#include <game_object.h>

const char* EMPTY_PLATFORM_TAG = "UNTAGGED_PLATFORM";

const int MAX_SPAWN_POINT = SCREEN_WIDTH - 20;
std::random_device rd; // obtain a random number from hardware
std::mt19937 gen(rd()); // seed the generator
std::uniform_int_distribution<> distr(0, MAX_SPAWN_POINT); //

Platform::Platform(int creationTime) : Platform(EMPTY_PLATFORM_TAG, creationTime){
    std::cout << "Platform constructor called without tag, setting tag to: " << EMPTY_PLATFORM_TAG << std::endl;
};

void Platform::render(SDL_Renderer* renderer) {
    SDL_Color c;
    c.r = 70;
    c.g = 180;
    c.b = 250;
    c.a = 255;
    // Set render color to blue ( rect will be rendered in this color )
    SDL_SetRenderDrawColor( renderer, c.r, c.g, c.b, c.a );


    // Render rect
    SDL_RenderFillRect( renderer, &bounds);
}

Platform::Platform(std::string tag, int creationTime) : GameObject(tag) {
    int spawnPoint = distr(gen);
    int maxWidth = MAX_SPAWN_POINT - spawnPoint;
    std::uniform_int_distribution<> widthDistr(0, maxWidth); //
    int width = widthDistr(gen);
    this -> lastUpdateTime = creationTime;
    this -> bounds = SDL_Rect { spawnPoint, -10, width, 20};

}

void Platform::update() {
    int currentTime = (int) SDL_GetTicks64();
    int timeDiff = (currentTime )- (lastUpdateTime);
    int newY = -10 + (timeDiff / 15);
    //std::cout << "currentTime: " << currentTime << ", lastUpdateTime: " << lastUpdateTime << ", new y: " << newY << std::endl; 
    this -> bounds.y = newY;
    //this -> lastUpdateTime = currentTime;
}

bool Platform::shouldDespawn() {
    int currentY = bounds.y;
    /*std::cout << "should despawn: " << "y: " << currentY << ", screenheight + 20: " << (SCREEN_HEIGHT + 20) 
    << ", " << ((rect.y) >= (SCREEN_HEIGHT + 20)) << std::endl; */
    bool result = currentY >= (SCREEN_HEIGHT + 20);
    return result;
}

Platform::Platform() : GameObject(EMPTY_PLATFORM_TAG) {
    this -> lastUpdateTime = (Uint64) 65;
  //   std::cout << "creating platform default constructor" << std::endl;   
}


Platform::~Platform() {
 //   std::cout << "destroying platform" << std::endl;
}

float Platform::getYVelocity() {
    return 0;
}
float Platform::getXVelocity() {
    return 0;
}