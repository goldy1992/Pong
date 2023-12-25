#pragma once
#include <model.h>
#include <SDL.h>
#include <view.h>

Model model;
View view(&model);
SDL_Event e;

bool isRunning = true;

void run();


void update();