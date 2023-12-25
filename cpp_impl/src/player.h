#pragma once
#include <constants.h>
#include <texture.h>
#include <game_object.h>

const int ANIMATION_UPDATE_FREQ = 20;
const int ARR_SIZE = 11;
const int GROUND = SCREEN_HEIGHT;
const double INITIAL_JUMP_VELOCITY = -40;
const int NOT_MOVING = 0;
const float SPEED_LEFT = -0.5f;
const float SPEED_RIGHT = 0.5f;
const int PLAYER_WIDTH = 72;
const int PLAYER_HEIGHT_STOOD = 92;
const int PLAYER_HEIGHT_DUCKED = 71;

class Player : public GameObject {
    private:
        float xVeloc = NOT_MOVING;
        void updateJump(Uint64 currentTime);
        Uint64 jumpStartTime;
        Uint64 moveStartTime;
        Uint64 lastRunAnimationUpdateTime;
        int xMoveStartPos = 0;
        Texture* texture;
        SDL_Rect walkClips[ ARR_SIZE ];
        SDL_Rect duck_img;
        SDL_Rect jump_img;
        SDL_Rect front;
        SDL_Rect stand;
        SDL_Rect hurt;
        SDL_Rect* currentRenderClip;
        int currentClipIndex = 0;
        Direction direction = DIR_RIGHT;
        bool isJumping = false;
        bool isGrounded = true;
        int jumpStartPosition = GROUND;
        bool isDucked = false;
        bool isMoving = false;
        bool isTouchingWall();

    public:
        Player();
        Player(Texture* texture);
        ~Player();

        void move(float veloc);
        void jump();
        void duck();
        void unduck();
        void setGrounded(bool isGrounded);
        void update() override;
        void render(SDL_Renderer* renderer) override;
        float getYVelocity() override;
        float getXVelocity() override;
};

Player* CreatePlayer(SDL_Renderer* renderer, char img[]);