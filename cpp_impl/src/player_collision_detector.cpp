#pragma once
#include <iostream>
#include <player.h>
#include <string>
#include <list>
#include <platform.h>

enum CollisionType {
    LEFT, RIGHT, TOP, BOTTOM, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NONE, UNKNOWN
};

class PlayerCollisionDetector {
    private:
        Player* player;
        std::list<Platform*>* platforms;  

        bool intersectsPlayerTop(Platform* platformRect) {
            SDL_Rect* playerBounds = player -> getBounds();             
            int x1 = playerBounds -> x,
                x2 = playerBounds -> x + playerBounds -> w,
                y1 = playerBounds -> y, 
                y2 = playerBounds -> y;
                        
            SDL_bool intersectTop = SDL_IntersectRectAndLine(platformRect->getBounds(), &x1, &y1, &x2, &y2);
            if (intersectTop) {
        //        std::cout << "top of player intersected with " << platformRect -> getTag() << std::endl;
            }
            return intersectTop;                
        }

        bool intersectsPlayerBottom(Platform* platformRect) {
            SDL_Rect* playerBounds = player -> getBounds();             
            int x1 = playerBounds -> x,
                x2 = playerBounds -> x + playerBounds -> w,
                y1 = playerBounds -> y + playerBounds-> h, 
                y2 = playerBounds -> y + playerBounds-> h;
                        
            SDL_bool intersectBottom = SDL_IntersectRectAndLine(platformRect->getBounds(), &x1, &y1, &x2, &y2);
            if (intersectBottom) {
        //        std::cout << "bottom of player intersected with " << platformRect -> getTag() << std::endl;
            }
            return intersectBottom;                
        }

        bool intersectsPlayerLeft(Platform* platformRect) {
            SDL_Rect* playerBounds = player -> getBounds();             
            int x1 = playerBounds -> x,
                x2 = playerBounds -> x,
                y1 = playerBounds -> y, 
                y2 = playerBounds -> y + playerBounds->h;
                        
            SDL_bool intersectTop = SDL_IntersectRectAndLine(platformRect->getBounds(), &x1, &y1, &x2, &y2);
            if (intersectTop) {
//std::cout << "left of player intersected with " << platformRect -> getTag() << std::endl;
            }
            return intersectTop;                
        }

        bool intersectsPlayerRight(Platform* platformRect) {
            SDL_Rect* playerBounds = player -> getBounds();             
            int x1 = playerBounds -> x + playerBounds -> w,
                x2 = playerBounds -> x + playerBounds -> w,
                y1 = playerBounds -> y, 
                y2 = playerBounds -> y + playerBounds-> h;
                        
            SDL_bool intersectBottom = SDL_IntersectRectAndLine(platformRect->getBounds(), &x1, &y1, &x2, &y2);
            if (intersectBottom) {
       //         std::cout << "right of player intersected with " << platformRect -> getTag() << std::endl;
            }
            return intersectBottom;                
        }
    public:
        PlayerCollisionDetector();
        ~PlayerCollisionDetector();

        PlayerCollisionDetector(Player* newPlayer, std::list<Platform*>* newPlatforms) {
            this -> player = newPlayer;
            this -> platforms = newPlatforms;
        }

        CollisionType detectCollision() {
            SDL_Rect* playerBounds = player -> getBounds();
            for (Platform* platform : (*platforms)) {
                SDL_Rect* currentRect = platform -> getBounds();

                bool isIntersecting = SDL_HasIntersection(playerBounds, currentRect);

                if (isIntersecting) {
                 //   std::cout << "intersected with " << platform -> getTag() << std::endl;
                    bool intersectTop = intersectsPlayerTop(platform);
                    bool intersectBottom = intersectsPlayerBottom(platform);
                    bool intersectLeft = intersectsPlayerLeft(platform);
                    bool intersectRight = intersectsPlayerRight(platform);

                    if (intersectTop) {
                        if (intersectLeft) {
                            return CollisionType::TOP_LEFT;
                        } else if (intersectRight) {
                            return CollisionType::TOP_RIGHT;
                        } else {
                            return CollisionType::TOP;
                        }
                    }

                    if (intersectBottom) {
                        if (intersectLeft) {
                            return CollisionType::BOTTOM_LEFT;
                        } else if (intersectRight) {
                            return CollisionType::BOTTOM_RIGHT;
                        } else {
                            return CollisionType::BOTTOM;
                        }
                    }

                    if (intersectLeft) {
                        return CollisionType::LEFT;
                    }

                    if (intersectRight) {
                        return CollisionType::RIGHT;
                    }

                    return CollisionType::UNKNOWN;
                } else {
                    
                    return CollisionType::NONE;
                }
            }

            return CollisionType::NONE;

        }


        bool landedOnPlatform() {
            if (player->getYVelocity() >= 0) {
                for (Platform* platform : (*platforms)) {
                    if (intersectsPlayerBottom(platform)) {
                        player->setGrounded(true);
                        std::cout << "set grounded to true" << std::endl;
                        return true;
                    }

                }
            }
            return false;
        }

};