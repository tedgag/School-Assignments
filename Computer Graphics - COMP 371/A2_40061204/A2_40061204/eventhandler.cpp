//
//  eventhandler.cpp
//  A1_40061204
//
//  Class that handles the event generated by the user. It can handle framebuffer changes, mouse movements,
//  scrolls and mouse clicks events, as well as keyboard inputs.
//
//  Created by Édouard Gagné on 2020-02-23.
//

#include <eventhandler.h>

/* Mouse Info */
float mouseX;
float mouseY;
bool rightButton = false;
bool middleButton = false;
bool leftButton = false;
/* Used to send only one event per key press */
bool xPressed = false;
bool bPressed = false;
/* World pointer*/
World * world;
/* Declaring callback functions */
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void mouse_callback(GLFWwindow* window, double xPos, double yPos);
void scroll_callback(GLFWwindow* window, double xOffset, double yOffset);
void mouse_button_callback(GLFWwindow* window, int button, int action, int mods);

/* Constructor */
EventHandler::EventHandler(World * worldPointer) {
    world = worldPointer;

    // Setting the default mouse position to be in the middle of the screen
    mouseX = (Renderer::WIDTH) / 2;
    mouseX = (Renderer::HEIGHT) / 2;
    
    // Setting callback functions
    glfwSetFramebufferSizeCallback(Renderer::window, framebuffer_size_callback);
    glfwSetCursorPosCallback(Renderer::window, mouse_callback);
    glfwSetScrollCallback(Renderer::window, scroll_callback);
    glfwSetMouseButtonCallback(Renderer::window, mouse_button_callback);
}
/* Process the keyboard input events generated by the user. these inputs can affect the models and camera
   of the scene. See "readme.txt" for more information on what each key does. */
void EventHandler::processInput() {
    /* Close window*/
    if(glfwGetKey(Renderer::window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
        glfwSetWindowShouldClose(Renderer::window, true);
    /* Move the snowman forward */
    if(glfwGetKey(Renderer::window, GLFW_KEY_W) == GLFW_PRESS) {
        world->snowman->update("FORWARD", 0.25f);
        world->snowman->animate();
    }
    /* Move the snowman to the left */
    if(glfwGetKey(Renderer::window, GLFW_KEY_A) == GLFW_PRESS) {
        world->snowman->update("LEFT", 0.25f);
        world->snowman->animate();
    }
    /* Move the snowman backward */
    if(glfwGetKey(Renderer::window, GLFW_KEY_S) == GLFW_PRESS){
        world->snowman->update("BACKWARD", 0.25f);
        world->snowman->animate();
    }
    /* Move the snowman to the right */
    if(glfwGetKey(Renderer::window, GLFW_KEY_D) == GLFW_PRESS) {
        world->snowman->update("RIGHT", 0.25f);
        world->snowman->animate();
    }
    /* Rotate the snowman clockwise */
    if(glfwGetKey(Renderer::window, GLFW_KEY_E) == GLFW_PRESS)
        world->snowman->update("Y_ANGLE", -3.0f);
    /* Rotate the snowman counter-clockwise */
    if(glfwGetKey(Renderer::window, GLFW_KEY_Q) == GLFW_PRESS)
        world->snowman->update("Y_ANGLE", 3.0f);
    /* Scale up the snowman */
    if(glfwGetKey(Renderer::window, GLFW_KEY_U) == GLFW_PRESS )
        world->snowman->update("SCALE_FACTOR", 0.05f);
    /* Scale down the snowman */
    if(glfwGetKey(Renderer::window, GLFW_KEY_J) == GLFW_PRESS)
        world->snowman->update("SCALE_FACTOR", -0.05f);
    /* Rotate the world counter-clockwise along the Z axis */
    if(glfwGetKey(Renderer::window, GLFW_KEY_UP) == GLFW_PRESS)
        world->update("Z_ANGLE", 1.0f);
    /* Rotate the world clockwise along the Z axis */
    if(glfwGetKey(Renderer::window, GLFW_KEY_DOWN) == GLFW_PRESS)
        world->update("Z_ANGLE", -1.0f);
    /* Rotate the world counter-clockwise along the X axis */
    if(glfwGetKey(Renderer::window, GLFW_KEY_LEFT) == GLFW_PRESS)
        world->update("X_ANGLE", 1.0f);
    /* Rotate the world clockwise along the X axis */
    if(glfwGetKey(Renderer::window, GLFW_KEY_RIGHT) == GLFW_PRESS)
        world->update("X_ANGLE", -1.0f);
    /* Reset the Renderer::colorShader */
    if(glfwGetKey(Renderer::window, GLFW_KEY_TAB) == GLFW_PRESS) {
        world->reset();
        Renderer::camera->reset();
    }
    /* Randomize the snowman position */
    if(glfwGetKey(Renderer::window, GLFW_KEY_SPACE) == GLFW_PRESS)
        world->snowman->randomizePos();
    /* Change the rendering mode to points */
    if(glfwGetKey(Renderer::window, GLFW_KEY_P) == GLFW_PRESS)
        Renderer::renderingMode=GL_POINTS;
    /* Change the rendering mode to lines */
    if(glfwGetKey(Renderer::window, GLFW_KEY_L) == GLFW_PRESS)
        Renderer::renderingMode=GL_LINES;
    /* Change the rendering mode to triangles (default) */
    if(glfwGetKey(Renderer::window, GLFW_KEY_T) == GLFW_PRESS)
        Renderer::renderingMode=GL_TRIANGLES;
    /* Disable or enable textures in the scene */
    if(glfwGetKey(Renderer::window, GLFW_KEY_X) == GLFW_PRESS && !xPressed ) {
        Renderer::textureToggle ? Renderer::textureToggle=false : Renderer::textureToggle=true;
        world->update("RENDER_TYPE", 0);
        world->snowman->update("RENDER_TYPE", 0);
        xPressed = true;
    }
    if(glfwGetKey(Renderer::window, GLFW_KEY_X) == GLFW_RELEASE && xPressed) {
        xPressed = false;
    }
    /* Disable or enable shadows in the scene */
    if(glfwGetKey(Renderer::window, GLFW_KEY_B) == GLFW_PRESS && !bPressed ) {
        Renderer::shadowToggle ? Renderer::shadowToggle=false : Renderer::shadowToggle=true;
        bPressed = true;
    }
    if(glfwGetKey(Renderer::window, GLFW_KEY_B) == GLFW_RELEASE && bPressed) {
        bPressed = false;
    }
}
// Callback function for window size change events
void framebuffer_size_callback(GLFWwindow* window, int width, int height) {
    
    glViewport(0, 0, width, height);
    Renderer::WIDTH = (GLint) width;
    Renderer::HEIGHT = (GLint)height;
}
// Callback function for mouse movements events
void mouse_callback(GLFWwindow* window, double xPos, double yPos) {
    // Getting the offsets from the last mouse position
    float xOffset = xPos - mouseX;
    float yOffset = mouseY - yPos;
    mouseX = xPos;
    mouseY = yPos;
    
    // Pan the camera by the offset if the right mouse button is pressed
    if (rightButton)
        Renderer::camera->pan(xOffset);
    // Tilt the camera by the offset if the middle button is pressed
    if (middleButton)
        Renderer::camera->tilt(yOffset);
}
// Callback function for mouse scroll events
void scroll_callback(GLFWwindow* window, double xOffset, double yOffset) {
    // Zoom the camera if the left button is pressed
    if (leftButton) {
        Renderer::camera->zoom(yOffset);
    }
}
// Callback function for mouse buttons events
void mouse_button_callback(GLFWwindow* windoww, int button, int action, int mods) {
    // Check is right mouse button is pressed or released
    if (button == GLFW_MOUSE_BUTTON_RIGHT) {
        if(GLFW_PRESS == action)
            rightButton = true;
        else if(GLFW_RELEASE == action)
            rightButton = false;
    }
    // Check is middle mouse button is pressed or released
    if (button == GLFW_MOUSE_BUTTON_MIDDLE) {
        if(GLFW_PRESS == action)
            middleButton = true;
        else if(GLFW_RELEASE == action)
            middleButton = false;
    }
    // Check is left mouse button is pressed or released
    if (button == GLFW_MOUSE_BUTTON_LEFT) {
        if(GLFW_PRESS == action)
            leftButton = true;
        else if(GLFW_RELEASE == action)
            leftButton = false;
    }
}
/* Poll future events */
void EventHandler::pollEvents() {
    glfwPollEvents();
}
