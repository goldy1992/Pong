#pragma once
#include <SDL.h>
#include <string>

class GameObject {
    private:
        std::string tag;
    protected:
        SDL_Rect bounds;
        float xVeloc;
        float yVeloc;
    public:

        GameObject(std::string tag) {
            this -> tag = tag;
        }
        SDL_Rect* getBounds() {
            return &bounds;
        }
        virtual void update() {};
        virtual void render(SDL_Renderer* renderer) {};
        virtual float getXVelocity() = 0;
        virtual float getYVelocity() = 0;
        
        std::string getTag() {
            return tag;
        };


};