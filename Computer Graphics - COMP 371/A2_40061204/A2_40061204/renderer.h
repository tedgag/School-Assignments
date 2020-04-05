//
//  renderer.h
//  A1_40061204
//
//  Header file for the renderer class. See "renderer.cpp" for the implementation.
//
//  Created by Édouard Gagné on 2020-02-23.
//

#ifndef renderer_h
#define renderer_h

// GLEW
#define GLEW_STATIC
#include <GL/glew.h>

// GLFW
#include <GLFW/glfw3.h>

// Header files
#include <shader.h>
#include <camera.h>

#include "glm/gtx/string_cast.hpp"

class Renderer {
    public:
        static GLint WIDTH, HEIGHT;
        static const unsigned int SHADOW_WIDTH = 1024, SHADOW_HEIGHT = 1024;
        static GLuint depthMap;
        static GLuint depthMapFBO;
        static Shader * colorShader;
        static Shader * textureShader;
        static Shader * shadowShader;
        static GLFWwindow * window;
        static GLenum renderingMode;
        static bool textureToggle;
        static bool shadowToggle;
        static Camera * camera;
        static int initialize();
        static void render();
        static bool shouldClose();
        static void swapBuffers();
        static void reset();
        static void renderDepth();
        static void setupCamera();
        static void setupLighting();
};

#endif /* renderer_h */
