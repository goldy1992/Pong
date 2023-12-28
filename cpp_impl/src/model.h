#pragma once

#include <ball.h>
#include <iostream>
#include <paddle.h>
#include <util.h>

class Model {
    public:
        ~Model() {
            std::cout << "calling Model destructor" << std::endl;            
        }
        Model(
            Paddle* player1,
            Paddle* player2,
            Ball* ball
        ) {
            this -> player1 = player1;
            this -> player2 = player2;
            this -> ball = ball;
            std::cout << "hit the model constructor" << std::endl;
        }
        void update(int deltaTimeMs);
        Paddle* getPlayer1() {
            return player1;
        }

        Paddle* getPlayer2() {
            return player2;
        }

        Ball* getBall() {
            return ball;
        }


    private:
        Paddle* player1;
        Paddle* player2;
        Ball* ball;
        bool isPaused = false;
        bool waitingToServe = true;
        bool setNewGame = false;
        void movePaddles(int deltaTimeMs);
        void moveBall(int deltaTimeMs);
        bool checkOutOfBounds();
};