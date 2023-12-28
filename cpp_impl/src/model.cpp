#include <model.h>
#include <iostream>
#include <string>

void Model::movePaddles(int deltaTimeMs) {
  movePaddle(this -> player1, deltaTimeMs);
  movePaddle(this -> player2, deltaTimeMs);
}
void Model::movePaddle(Paddle* paddle, int deltaTimeMs) {
    // v = s/ t  s = vt    50 = 1000v v = 50 / 1000
  if (paddle -> isMovingUp()) {
    double s = -PADDLE_SPEED * deltaTimeMs;
    Position* currentPos = paddle -> getPosition();
    double newYPosition = (currentPos -> y) + s;
    if (newYPosition < 0) {
      newYPosition = 0;
    }
    currentPos -> y = newYPosition;

    // std::cout << "p1 moving up with deltaT:" 
    //   + std::to_string(deltaTimeMs)  
    //   + " s:" + std::to_string(s)
    //   + " new y: " + std::to_string(currentPos -> y) 
    //   << std::endl;
    }

    if (paddle ->isMovingDown()) {
      double s = PADDLE_SPEED * deltaTimeMs;
      Position* currentPos = paddle -> getPosition();
      double newYPosition = (currentPos -> y) + s;
      // check if out of bounds
      double maxHeight = WINDOW_HEIGHT - (paddle -> getHeight());
      if (newYPosition > maxHeight) {
        newYPosition = maxHeight;
      }
      currentPos -> y = newYPosition;
  
    }
  }

void Model::moveBall(int deltaTimeMs) {

}
void Model::update(int deltaTimeMs) {
  movePaddles(deltaTimeMs);
}
bool Model::checkOutOfBounds() {
    return true;
}
