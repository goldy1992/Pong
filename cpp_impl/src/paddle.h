#pragma once
#include <color.h>
#include <SDL.h>
#include <constants.h>
#include <position.h>

const int PADDLE_WIDTH = 60;
const int PADDLE_HEIGHT = 180;
const int PADDLE_START_Y_POS = (WINDOW_HEIGHT - PADDLE_HEIGHT) / 2;
const double PADDLE_SPEED = 1.2; 

const Color DEFAULT_COLOR = {0, 0,0,255};


class Paddle {
    private:
        int width = PADDLE_WIDTH, height = PADDLE_HEIGHT;
        Position* position;
        bool movingUp = false;
        bool movingDown = false;
        Color* color;
        SDL_Rect* bounds;
    public:
        Paddle() {
            Position defaultPosition = {0, 0};
            this->position = &defaultPosition;
            Color defaultColor = DEFAULT_COLOR;
            this->color = &defaultColor;
        }
        Paddle(Position* position, Color* color) {
            this->position = position;
            this->color = color;
        }
        Paddle(Position* position, Color* color, int width, int height) {
            this->position = position;
            this->color = color;
            this-> width = width;
            this-> height = height;
        }
        int getWidth() {
            return width;
        }
        int getHeight() {
            return height;
        }
        bool isMovingUp() {
            return movingUp;
        }
        bool isMovingDown() {
            return movingDown;
        }
        Color* getColor() {
            return color;
        }
        Position* getPosition() {
            return position;
        }
        SDL_Rect* getBounds() {
            return bounds;
        }
        void setMovingUp(bool isMovingUp) {
            movingUp = isMovingUp;
        }
        void setMovingDown(bool isMovingDown) {
            movingDown = isMovingDown;
        }
        void setColor(Color* color) {
            this -> color = color;
        }
        void setPosition(Position* pos) {
            position = pos;
        }

};