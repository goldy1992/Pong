#include <model.h>
#include <util.h>
#include <iostream>
#include <string>

void Model::movePaddles(int deltaTimeMs) {
  movePaddle(this -> player1, deltaTimeMs);
  movePaddle(this -> player2, deltaTimeMs);
}

void Model::moveBall(int deltaTimeMs) {
  if (!isWaitingToServe()) {
    // calculate new ball velocity
    if (ball -> isStationary()) {
      ball -> setDx(randomDirection());
      ball -> setDy(randomDirection());
    }

    Position* currentPosition = ball -> getPosition();
    int radius = ball -> getRadius();

    // check if hit player
    bool pastPlayer2X = ((currentPosition -> x) + radius) >= (player2 -> getPosition() -> x);
    bool withinPlayer2YBounds = ((currentPosition -> y) >= (player2 -> getPosition() -> y))
      && ((currentPosition -> y) <= ((player2 -> getPosition() -> y) + PADDLE_HEIGHT));
    bool hitPlayer2 = pastPlayer2X && withinPlayer2YBounds;

    bool pastPlayer1X = ((currentPosition -> x) - radius) <= ((player1 -> getPosition() -> x) + PADDLE_WIDTH);
    bool withinPlayer1YBounds = ((currentPosition -> y) >= (player1 -> getPosition() -> y))
      && ((currentPosition -> y) <= ((player1 -> getPosition() -> y) + PADDLE_HEIGHT));
    bool hitPlayer1 = pastPlayer1X && withinPlayer1YBounds;
    if (hitPlayer1 || hitPlayer2) {
      ball -> setHitPlayer(true);
      ball -> setDx((ball -> getDx()) * -1);
    }

    // if the ball touches the the top wall then change y direction.
    double ballTop = (currentPosition -> y) - radius;
    double ballBottom = (currentPosition -> y) + radius;
    bool aboveTop = ballTop < 0.0;
    bool bellowBottom = ballBottom > WINDOW_HEIGHT;
    if (aboveTop || bellowBottom) {
        ball -> setDy(- ball -> getDy());
    }

    // set new position
    currentPosition -> x += ((ball-> getDx()) * deltaTimeMs);
    currentPosition -> y += ((ball-> getDy()) * deltaTimeMs);


    // check out of bounds
    double x = currentPosition -> x;
    double y = currentPosition -> y;
    bool isOutOfBounds = ((x + radius) < 0) ||
     ((x - radius) > WINDOW_WIDTH);
     if (isOutOfBounds) {
        waitingToServe = true;
        currentPosition -> x = WINDOW_WIDTH  / 2.0;
        currentPosition -> y = WINDOW_HEIGHT / 2.0;
        ball -> setDx(randomDirection());
        ball -> setDy(randomDirection());
     }




  }
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


void Model::update(int deltaTimeMs) {
  moveBall(deltaTimeMs);
  movePaddles(deltaTimeMs);
}
bool Model::checkOutOfBounds() {
    return true;
}
