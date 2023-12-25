#pragma once
#include <color.h>
#include <constants.h>
#include <position.h>

const int PADDLE_WIDTH = 60;
const int PADDLE_HEIGHT = 180;
const int PADDLE_START_Y_POS = (WINDOW_HEIGHT - PADDLE_HEIGHT) / 2;


class Paddle {
    private:
        int width = PADDLE_WIDTH, height = PADDLE_HEIGHT;
        Position position;
        bool movingUp = false;
        bool movingDown = false;
        Color color;

    public:
        void setMovingUp(bool movingUp) {
            this->movingUp = movingUp;
        }
        void setMovingDown(bool movingDown) {
            this->movingDown = movingDown;
        }
        void setPosition(Position position) {
            this->position = position;
        }

};