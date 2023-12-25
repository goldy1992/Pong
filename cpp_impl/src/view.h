#pragma once
#include <SDL.h>

class View {

    private:
        Model* model;
        SDL_Window* gWindow = NULL;
        SDL_Renderer* gRenderer;
        // Starts up SDL and creates window
        bool loadMedia();
        void close();
    public:
        bool init();
    View(Model* model) {
        this->model = model;
    }
};