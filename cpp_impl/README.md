# Pong MVC in C++

An implementation of Pong in C++ using the [SDL2 Library](https://www.libsdl.org/).

## Install Requirements
- A C++ Compiler, e.g. g++, MSVC (cl.exe)
- [CMake](https://cmake.org/) to build the project.
- SDL2, can be downloaded [here](https://github.com/libsdl-org/SDL/releases/).

__Note:__ Windows users should ensure `SDL2.dll` and `SDL_image.dll` are in the directory of the generated `.exe` file.

## Developer Environment
The code was developed in [VSCode](https://code.visualstudio.com/) using the [C++](https://code.visualstudio.com/docs/languages/cpp) and [CMake Tools](https://github.com/microsoft/vscode-cmake-tools) extensions. The relevant launch configurations and settings have been added to version control within the [.vsvode](.vscode/) directory.

When developing in _*Windows*_, place the SDL2 library within the [deps](./deps/) directory. This will be found by the _*CMake*_ buildscript by:

1. [CMakeLists.txt](CMakeLists.txt) adds the [cmake_config](./cmake_config/) directory to the [CMAKE_MODULE_PATH](https://cmake.org/cmake/help/latest/variable/CMAKE_MODULE_PATH.html).
2. [cmake_config](./cmake_config/) contains the file [FindSDL2.cmake](./cmake_config/FindSDL2.cmake) which contains the CMake code to find the SDL2 header files and libraries to use during the compilation and linking process.