#pragma once
#include <model.h>
#include <SDL.h>
#include <view.h>

extern Model model;
extern View view;
extern SDL_Event e;
extern bool isRunning;

void run();
void update();