#include <stdio.h>
#include <string>
#include <SDL.h>
#include <constants.h>

#pragma once 

class Texture {
    public:
        Texture();

        ~Texture();

        void free();

        bool loadFromFile( std::string path, SDL_Renderer* renderer);

        void render(SDL_Renderer* renderer, int x, int y, SDL_Rect* clip = NULL, Direction d = DIR_RIGHT);

        int getWidth();
        int getHeight();
    private:
        SDL_Texture* mTexture;

        int mWidth;
        int mHeight;
};