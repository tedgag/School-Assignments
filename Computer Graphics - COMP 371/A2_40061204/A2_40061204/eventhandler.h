//
//  eventhandler.h
//  A1_40061204
//
//  Header file for the EventHandler class. See "eventhandler.cpp" for the implementation.
//
//  Created by Édouard Gagné on 2020-02-23.
//

#ifndef eventhandler_h
#define eventhandler_h

// GLEW
#define GLEW_STATIC
#include <GL/glew.h>

// GLFW
#include <GLFW/glfw3.h>

// Header files
#include <renderer.h>
#include <snowman.h>
#include <world.h>
#include <camera.h>
#include <time.h>

class EventHandler {
    public:
        EventHandler(World * world);
        void processInput();
        void pollEvents();
};
#endif /* eventhandler_h */
