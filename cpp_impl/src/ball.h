#pragma once
#include <color.h>
#include <position.h>

class Ball {
    private:
        Color color;
        int radius;
        float dx;
        float dy;
        Position position;

      

    public:
        Ball() {
            
        }
        Ball(int radius) {
            this -> radius = radius;
        }

        ~Ball() {
            
        }
};