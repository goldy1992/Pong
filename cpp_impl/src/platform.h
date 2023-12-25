#pragma once

#include <constants.h>
#include <SDL.h>
#include <iostream>
#include <game_object.h>
#include <string>

class Platform : public GameObject {
    private:
        int lastUpdateTime = 0;

    public:
        Platform(const Platform& oldPlatform) : GameObject(oldPlatform) {
            std::cout << "calling copy constructor" << std::endl;
            this -> lastUpdateTime = oldPlatform.lastUpdateTime;
            this -> bounds = oldPlatform.bounds;
        }
        Platform(std::string tag, int creationTime);
        Platform(int creationTime);
        Platform();
        ~Platform();
        void render(SDL_Renderer* renderer) override;
        void update() override;
        bool shouldDespawn();
        float getYVelocity() override;
        float getXVelocity() override;        
};