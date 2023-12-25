#include <player.h>
#include <SDL.h>
#include <SDL_image.h>
#include <constants.h>
#include <chrono>
#include <iostream>
#include <game_object.h>

const char* TAG = "player";

Player::Player(Texture* texture) : GameObject(TAG) {
    this -> texture = texture;
;
    this -> walkClips[ 0 ].x = 0;
	this -> walkClips[ 0 ].y = 0;
	this -> walkClips[ 0 ].w = 72;
	this -> walkClips[ 0 ].h = 97;

	this -> walkClips[ 1 ].x = 73;
	this -> walkClips[ 1 ].y = 0;
	this -> walkClips[ 1 ].w = 72;
	this -> walkClips[ 1 ].h = 97;
		
	this -> walkClips[ 2 ].x = 146;
	this -> walkClips[ 2 ].y = 0;
	this -> walkClips[ 2 ].w = 72;
	this -> walkClips[ 2 ].h = 97;

	this -> walkClips[ 3 ].x = 0;
	this -> walkClips[ 3 ].y = 98;
	this -> walkClips[ 3 ].w = 72;
	this -> walkClips[ 3 ].h = 97;


	this -> walkClips[ 4 ].x = 73;
	this -> walkClips[ 4 ].y = 98;
	this -> walkClips[ 4 ].w = 72;
	this -> walkClips[ 4 ].h = 97;
		
	this -> walkClips[ 5 ].x = 146;
	this -> walkClips[ 5 ].y = 98;
	this -> walkClips[ 5 ].w = 72;
	this -> walkClips[ 5 ].h = 97;

	this -> walkClips[ 6 ].x = 219;
	this -> walkClips[ 6 ].y = 0;
	this -> walkClips[ 6 ].w = 72;
	this -> walkClips[ 6 ].h = 97;

	this -> walkClips[ 7 ].x = 292;
	this -> walkClips[ 7 ].y = 0;
	this -> walkClips[ 7 ].w = 72;
	this -> walkClips[ 7 ].h = 97;
		
	this -> walkClips[ 8 ].x = 219;
	this -> walkClips[ 8 ].y = 98;
	this -> walkClips[ 8 ].w = 72;
	this -> walkClips[ 8 ].h = 97;

	this -> walkClips[ 9 ].x = 365;
	this -> walkClips[ 9 ].y = 0;
	this -> walkClips[ 9 ].w = 72;
	this -> walkClips[ 9 ].h = 97;

	this -> walkClips[ 10 ].x = 292;
	this -> walkClips[ 10 ].y = 98;
	this -> walkClips[ 10 ].w = 72;
	this -> walkClips[ 10 ].h = 97;

	this -> jump_img.x = 438;
	this -> jump_img.y = 93;
	this -> jump_img.w = 67;
	this -> jump_img.h = 94;

	this -> duck_img.x = 365;
	this -> duck_img.y = 98;
	this -> duck_img.w = 69;
	this -> duck_img.h = 71;

	this -> front.x = 0;
	this -> front.y = 196;
	this -> front.w = 66;
	this -> front.h = 92;
	this -> currentRenderClip = &front;
	this -> bounds.x = 0;
    this -> bounds.y = GROUND - PLAYER_HEIGHT_STOOD;
	this -> bounds.w = PLAYER_WIDTH;
	this -> bounds.h = PLAYER_HEIGHT_STOOD;
    printf("player created\n");

}

Player::Player() : GameObject("player") {
    this -> bounds.x = 0;
    this -> bounds.y = 0;
    printf("creating player: posX %d. posY: %d\n", bounds.x, bounds.y);
}

Player::~Player() {
    printf("freeing\n");
    Texture* t = this->texture;
    t->free();
}

void Player::move(float veloc) {
	this -> moveStartTime = SDL_GetTicks64();
	this -> xMoveStartPos = this -> bounds.x;
	std::cout << "move veloc" << veloc << std::endl;
    //printf("moving x %d, direction %d, currentIndex %d\n", x, d, currentClipIndex);
    this -> xVeloc = veloc;
	this -> isMoving = veloc != NOT_MOVING;
	if (veloc > 0) {
		this -> direction = DIR_RIGHT;
	} else if (veloc < 0) {
		direction = DIR_LEFT;
	}
	this -> xVeloc = veloc;

}

void Player::setGrounded(bool isGrounded) {
	this ->isGrounded = isGrounded;
}

void Player::duck() {
	this-> isDucked = true;
	this -> bounds.h = PLAYER_HEIGHT_DUCKED;
}

void Player::unduck() {
	this-> isDucked = false;
	this -> bounds.h = PLAYER_HEIGHT_STOOD;
}

void Player::jump() {
	if (!isJumping) {
		this -> jumpStartTime = SDL_GetTicks64();
		std::cout << "jump start time" << jumpStartTime << std::endl;
		isJumping = true;
		this->isGrounded = false;
		this -> jumpStartPosition = bounds.y;
	} else if (isJumping && isMoving && isTouchingWall()) {
		Uint64 currentTime = SDL_GetTicks64();
		this -> jumpStartTime = currentTime;
		this -> jumpStartPosition = bounds.y;
		this ->	xMoveStartPos = this-> bounds.x;
		this -> moveStartTime = currentTime;
		std::cout << "jump start time" << jumpStartTime << std::endl;
		if (this->direction == DIR_LEFT) {
			this->direction = DIR_RIGHT;
			this -> xVeloc = SPEED_RIGHT;
	
		} else {
			this->direction = DIR_LEFT;
			this -> xVeloc = SPEED_LEFT;
		}
		this->isGrounded = false;
	}
}

float Player::getYVelocity() {
	if (isGrounded) {
		return 0;
	} else if (isJumping) {
		// v = u + at
		int currentTime = SDL_GetTicks64();
		Uint64 deltaTime = (currentTime - jumpStartTime);
		double deltaTimeDouble = (double) deltaTime * std::pow(10, -2);
		float yVeloc = INITIAL_JUMP_VELOCITY + g * deltaTimeDouble;
		std::cout << "yvel: " << yVeloc << std::endl;
		return yVeloc;
	} 
	return -1;
}

float Player::getXVelocity() {
	return this-> xVeloc;
}

void Player::updateJump(Uint64 currentTime) {
	Uint64 deltaTime = (currentTime - jumpStartTime);
	double deltaTimeDouble = (double) deltaTime * std::pow(10, -2);

	//std::cout << "deltaTime: " << deltaTime << ", deltaTimeDouble: " << deltaTimeDouble << std::endl;

	// s = ut + 0.5a(t^2)
	double ut = (double) (INITIAL_JUMP_VELOCITY * deltaTimeDouble);
	double halfATSqu = (double) (0.5 * g * std::pow(deltaTimeDouble, 2));
	double displacement = ut + halfATSqu;
	double newHeight = jumpStartPosition + (ut + halfATSqu);

	//std::cout << "bounds.h: " << bounds.h <<  ", new height: " << newHeight << ", s[" << displacement << "] = ut[" << ut << "] + 0.5at^2[" << halfATSqu << "]" << std::endl;
	
	//		printf("updating jump, s[%f] = u[%f]t[%f] + 0.5g[%f]t[%f]t[%f]\n", newHeight, INITIAL_JUMP_VELOCITY, (double) deltaTime, g, (double)deltaTime,  (double)deltaTime);
	if (newHeight > GROUND - bounds.h) {
	//	std::cout << "new height: " << newHeight << ",  is greater than ground: " << (GROUND - bounds.h) << " , setting jump to false"; 
	//	printf("new height %f is greater than ground %d, setting jump to false\n",newHeight, GROUND);
		isJumping = false;
	//	std::cout << "jump finished!" << std::endl;
		bounds.y = GROUND - bounds.h;
	} else {
	//	printf("updating newHeight %f\n", newHeight);
		this->bounds.y = newHeight;
	}
}

void Player::update() {
	Uint64 currentTime = SDL_GetTicks64();

    //printf("updating: posX %d, posY %d\n", this->posX, this->posY);
	if (this -> isJumping) {
		updateJump(currentTime);
	}

	double deltaTime = (currentTime - moveStartTime);
	double deltaX = xMoveStartPos + (deltaTime * xVeloc);
	if (deltaX <= 0) {
		deltaX = 0;
	}
	if (deltaX > (SCREEN_WIDTH - (*currentRenderClip).w)) {
		deltaX = SCREEN_WIDTH - (*currentRenderClip).w;
	}
	this->bounds.x =  deltaX;
	if (this -> isJumping) {
		this -> currentRenderClip = &jump_img;
	} 
	else if (this -> isMoving) {
		double timeSinceLastAnimation = currentTime - lastRunAnimationUpdateTime;
		if (timeSinceLastAnimation > ANIMATION_UPDATE_FREQ) {
			currentClipIndex++;
			this -> lastRunAnimationUpdateTime = currentTime;
			if (currentClipIndex >= ARR_SIZE) {
				currentClipIndex = 0;
			}
		}
		this -> currentRenderClip = &walkClips[currentClipIndex];
	} else if (isDucked) {
		this->currentRenderClip = &duck_img;
	} else {
		this -> currentRenderClip = &front;
	}
}

void Player::render(SDL_Renderer* renderer) {
	Uint64 currentTime = SDL_GetTicks64();
	int yPos = isJumping ? bounds.y : (GROUND - currentRenderClip->h);
	Texture* t = this->texture;
	(*t).render(renderer, this-> bounds.x, yPos,  currentRenderClip, this -> direction);		
}

bool Player::isTouchingWall() {
	int currentXPos = this->bounds.x;
	return (currentXPos <= 0) || 
	(currentXPos >= (SCREEN_WIDTH - (*currentRenderClip).w));

}

Player* CreatePlayer(
    SDL_Renderer* renderer,
    char img[]) {
    Texture* texture = new Texture();
    (*texture).loadFromFile(img, renderer);

    return new Player(texture);
}

std::chrono::milliseconds getCurrentTimeMs() {
	using namespace std::chrono;
   	auto now = system_clock::now();
    auto now_ms = time_point_cast<milliseconds>(now);
	return now_ms.time_since_epoch();

    // auto value = now_ms.time_since_epoch();
    // long duration = value.count();
  	// milliseconds dur(duration);
    // time_point<system_clock> dt(dur);


	// milliseconds ms = duration_cast<milliseconds >(system_clock::now().time_since_epoch());
	// long x = ms.count();
	// std::cout << "time in ms: " << dt.time_since_epoch().count() << std::endl;
	// return x;

}