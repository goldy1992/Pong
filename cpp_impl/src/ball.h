#pragma once
#include <color.h>
#include <constants.h>
#include <iostream>
#include <position.h>
#include <string>

const Color DEFAULT_BALL_COLOR = {47, 122, 250,255};
const int DEFAULT_BALL_RADIUS = 10;
const Position DEFAULT_BALL_POSITION = {
    WINDOW_WIDTH  / 2.0,
    WINDOW_HEIGHT / 2.0
};
class Ball {
    private:
        Color* color;
        int radius;
        float dx;
        float dy;
        bool canChangeDx;
        bool canChangeDy;
        bool playHitPlayerSound = false;
        Position* position;

    public:
        Ball() {
            this -> radius = DEFAULT_BALL_RADIUS;
            Position startPosition = DEFAULT_BALL_POSITION;
            this -> position = &startPosition;
            Color ballColor = DEFAULT_BALL_COLOR;
            this -> color = &ballColor;
        }
        Ball(int radius, Position* position, Color* color) {
            this -> radius = radius;
            this -> position = position;
            this -> color = color;
        }

        ~Ball() {
            std::cout << "destructing Ball" << std::endl;
        }
        int getRadius() {
            return radius;
        }
        Color* getColor() {
            return color;
        }
        bool isStationary() {
            return (dx == 0) || (dy == 0);
        }
        void setDx(float newDx) {
            dx = newDx;
        }
        float getDx() {
            return dx;
        }
        void setDy(float newDy) {
            dy = newDy;
        }
        float getDy() {
            return dy;
        }

        Position* getPosition() {
            return position;
        }

        void setCanChangeDx(bool changeDx) {
            this -> canChangeDx = changeDx;
        }

        bool isCanChangeDx() {
            return canChangeDx;
        }
        void setCanChangeDy(bool changeDy) {
            this -> canChangeDy = changeDy;
        }
        bool isCanChangeDy() {
            return canChangeDy;
        }

        bool isHitPlayer() {
            return playHitPlayerSound;
        }

        void setHitPlayer(bool hitPlayer) {
            playHitPlayerSound = hitPlayer;
        }
};