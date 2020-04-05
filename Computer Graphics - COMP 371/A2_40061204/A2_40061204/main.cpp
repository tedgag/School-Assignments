//
//  main.cpp
//  A1_40061204
//
//  Main class that creates Renderer and EventHandler object and execute the game loop. The Renderer will render the scene
//  during each loop and the EventHandler will handle the events.
//
//  Created by Édouard Gagné on 2020-02-22.
//


// GLEW
#define GLEW_STATIC
#include <GL/glew.h>

// GLFW
#include <GLFW/glfw3.h>

// Header Files
#include <renderer.h>
#include <world.h>
#include <eventhandler.h>
unsigned int quadVAO = 0;
unsigned int quadVBO;
int main()
{
    // Will terminate the program if the initialization of the renderer was unsuccessful.
    if(Renderer::initialize() == -1) {
        glfwTerminate();
        return -1;
    }
    World world;
    
    world.reset();
    /* EventHandler */
    EventHandler eventHandler(&world);
    /* Game loop */
    while (!Renderer::shouldClose()) {
        /* Input */
        eventHandler.processInput();
        /* Rendering */
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        if (Renderer::shadowToggle) {
            Renderer::textureShader->use();
            Renderer::textureShader->setInt("enableShadow", 1);
            Renderer::colorShader->use();
            Renderer::colorShader->setInt("enableShadow", 1);
        } else {
            Renderer::textureShader->use();
            Renderer::textureShader->setInt("enableShadow", 0);
            Renderer::colorShader->use();
            Renderer::colorShader->setInt("enableShadow", 0);
        }
        Renderer::setupCamera();
        Renderer::setupLighting();
        // Depth Map

        Renderer::shadowShader->use();
        glViewport(0, 0, Renderer::SHADOW_WIDTH, Renderer::SHADOW_HEIGHT);
        glBindFramebuffer(GL_FRAMEBUFFER, Renderer::depthMapFBO);
        glClear(GL_DEPTH_BUFFER_BIT);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, world.texture);
        world.draw(Renderer::shadowShader);
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        // reset viewport
        int WIDTH, HEIGHT;
        glfwGetFramebufferSize(Renderer::window, &WIDTH, &HEIGHT);
        glViewport(0, 0, WIDTH, HEIGHT);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        if (Renderer::textureToggle) {
            Renderer::textureShader->use();
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, world.texture);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, Renderer::depthMap);
            world.draw(Renderer::textureShader);
        } else {
            Renderer::colorShader->use();
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, Renderer::depthMap);
            world.draw(Renderer::colorShader);
        }
       
        /* Events and buffers */ 
        eventHandler.pollEvents();
        Renderer::swapBuffers();
    }
    glfwTerminate();
    return 0;
}
