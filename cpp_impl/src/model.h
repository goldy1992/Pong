#pragma once

#include <ball.h>
#include <paddle.h>

class Model {
    public:
        void update(int deltaTimeMs);
        Paddle getPlayer1() {
            return player1;
        }

        Paddle getPlayer2() {
            return player2;
        }

        Ball getBall() {
            return ball;
        }


    private:
        Paddle player1;
        Paddle player2;
        Ball ball;
        bool isPaused = false;
        bool waitingToServe = true;
        bool setNewGame = false;
        void movePaddles(int deltaTimeMs);
        void moveBall(int deltaTimeMs);
        bool checkOutOfBounds();
};