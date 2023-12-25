cmake_minimum_required(VERSION 3.28.1)

find_package(SDL2 REQUIRED)
find_package(SDL2_image REQUIRED)
include_directories(${SDL2_INCLUDE_DIRS})
include_directories(${SDL2_IMAGE_INCLUDE_DIRS})

add_executable(main 
ball.h
ball.cpp
color.h
constants.h
controller.h
controller.cpp
game_object.h
main.cpp 
main.h
model.h
model.cpp
paddle.h
paddle.cpp
platform.h
platform.cpp
platform_controller.cpp
player.h
player.cpp
player_collision_detector.cpp
position.h
texture.cpp
texture.h
view.h
view.cpp
)


target_include_directories (main PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
target_link_libraries(main ${SDL2_LIBRARIES} ${SDL2_IMAGE_LIBRARIES})
#target_link_libraries(main SDL2::SDL2 SDL2::SDL2main SDL2_image::SDL2_image)