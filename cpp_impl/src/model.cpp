#include <model.h>
#include <iostream>
#include <string>

void Model::movePaddles(int deltaTimeMs) {
    // v = s/ t  s = vt    50 = 1000v v = 50 / 1000
  if (this -> player1 -> isMovingUp()) {
    double v = -1.05;
    double s = v * deltaTimeMs;

    Position* currentPos = player1 -> getPosition();
    currentPos -> y += s;

    // std::cout << "p1 moving up with deltaT:" 
    //   + std::to_string(deltaTimeMs)  
    //   + " s:" + std::to_string(s)
    //   + " new y: " + std::to_string(currentPos -> y) 
    //   << std::endl;
    }

    if (this->player1 ->isMovingDown()) {
      double v = 1.05;
      double s = v * deltaTimeMs;
      // p1.move_delta(-100, deltaTimeMs)
      Position* currentPos = player1 -> getPosition();
      currentPos -> y += s;
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
