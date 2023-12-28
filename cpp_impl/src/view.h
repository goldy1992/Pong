#pragma once
#include <model.h>
#include <SDL.h>

class View {

    private:
        Model* model;
        SDL_Window* gWindow = NULL;
        SDL_Renderer* gRenderer;
        // Starts up SDL and creates window
        bool loadMedia();
        void close();
        void renderBall(Ball* ball);
        void renderPlayer(Paddle* paddle);
    public:
        bool init();
        void update();
        View(Model* model) {
            this->model = model;
        }
};