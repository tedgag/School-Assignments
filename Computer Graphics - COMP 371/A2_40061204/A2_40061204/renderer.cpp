//
//  renderer.cpp
//  A1_40061204
//
//  Renderer class with the purpose of initializing and rendering the scene. It can also initializes
//  GLFW and GLEW and creates the window for the scene. Furthermore, it can
//  reset the scene and camera to their default position and orientation.
//  
//  Created by Édouard Gagné on 2020-02-23.
//

// Header files
#include "renderer.h"
Shader * Renderer::colorShader;
Shader * Renderer::textureShader;
Shader * Renderer::shadowShader;
unsigned int Renderer::depthMap;
unsigned int Renderer::depthMapFBO;
GLint Renderer::WIDTH;
GLint Renderer::HEIGHT;
GLFWwindow * Renderer::window;
GLenum Renderer::renderingMode = GL_TRIANGLES;
bool Renderer::textureToggle = true;
bool Renderer::shadowToggle = true;
Camera * Renderer::camera;
/* Initialize GLFW and GLEW, create the window and generate the shader*/
int Renderer::initialize() {
    // GLFW init
    glfwInit();
       
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
       
    // Default window size
    WIDTH = 1024;
    HEIGHT = 768;
    window = glfwCreateWindow(WIDTH, HEIGHT, "Assignment 2", nullptr, nullptr);
       
    if (nullptr==window) {
        std::cout << "Failed to create GLFW window" << std::endl;
        glfwTerminate();
        return -1;
    }
       
    glfwMakeContextCurrent(window);
       
    glewExperimental = GL_TRUE;
       
    //GLEW init
    if (GLEW_OK != glewInit()) {
        std::cout << "Failed to initialize GLEW" << std::endl;
        return -1;
    }
    //Enabling depth test
    glEnable(GL_DEPTH_TEST);
    /* Creating shaders */
    colorShader = new Shader("COLOR","ressources/shaders/color_shader.vs", "ressources/shaders/color_shader.fs");
    textureShader = new Shader("TEXTURE","ressources/shaders/texture_shader.vs", "ressources/shaders/texture_shader.fs");
    shadowShader = new Shader("SHADOW","ressources/shaders/shadow_shader.vs", "ressources/shaders/shadow_shader.fs");
    /* Configure depth map FBO */
    // Get the texture
    glGenTextures(1, &depthMap);
    // Bind the texture so the next glTex calls affect it
    glBindTexture(GL_TEXTURE_2D, depthMap);
    // Create the texture and specify it's attributes, including widthn height, components (only depth is stored, no color information)
    glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, SHADOW_HEIGHT, SHADOW_WIDTH, 0, GL_DEPTH_COMPONENT, GL_FLOAT,
                   NULL);
    // Set texture sampler parameters.
    // The two calls below tell the texture sampler inside the shader how to upsample and downsample the texture. Here we choose the nearest filtering option, which means we just use the value of the closest pixel to the chosen image coordinate.
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    // The two calls below tell the texture sampler inside the shader how it should deal with texture coordinates outside of the [0, 1] range. Here we decide to just tile the image.
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
    // Variable storing index to framebuffer used for shadow mapping // fbo: framebuffer object
    // Get the framebuffer
    glGenFramebuffers(1, &depthMapFBO);
    // Bind the framebuffer so the next glFramebuffer calls affect it
    glBindFramebuffer(GL_FRAMEBUFFER, depthMapFBO);
    // Attach the depth map texture to the depth map framebuffer
    //glFramebufferTexture(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, depth_map_texture, 0);
    glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthMap, 0);
    glDrawBuffer(GL_NONE); //disable rendering colors, only write depth values
    /* Shaders init */
    colorShader->use();
    colorShader->setInt("shadowMap", 1);
    textureShader->use();
    textureShader->setInt("diffuseTexture", 0);
    textureShader->setInt("shadowMap", 1);
    /* Camera init */
    camera = new Camera();
    
    return 1;
   
}

/* Determine if the window should close */
bool Renderer::shouldClose() {
    return glfwWindowShouldClose(window);
}

/* Swap the buffers at the end of the game loop */
void Renderer::swapBuffers() {
    glfwSwapBuffers(window);
}
/* Setup the camera, more specifically the view and project matrices for the shaders*/
void Renderer::setupCamera() {
    glm::mat4 view = glm::mat4(1.0f);
    glm::mat4 projection = glm::mat4(1.0f);
    // Projection matrix definition
    projection = glm::perspective(glm::radians(camera->fov), (float) WIDTH / (float) HEIGHT, 0.1f, 100.0f);
    // View matrix definition. "cameraPos" is the current camera position and "cameraDir" is the direction it is facing.
    view = glm::lookAt(camera->cameraPos, camera->cameraPos + camera->cameraDir, glm::vec3(0.0f, 1.0f, 0.0f));
    textureShader->use();
    textureShader->setMat4("view", view);
    textureShader->setMat4("projection", projection);
    colorShader->use();
    colorShader->setMat4("view", view);
    colorShader->setMat4("projection", projection);
}
/* Setup the lighting matrices and vectors for the shaders.*/
void Renderer::setupLighting() {
    // lightSpaceMatrix calculations
    float near_plane = 5.0f, far_plane = 180.0f;
    glm::mat4 lightProjectionMatrix = glm::perspective(46.0f,(GLfloat)SHADOW_HEIGHT/(GLfloat)SHADOW_WIDTH, near_plane, far_plane);
    glm::mat4 lightViewMatrix = glm::lookAt(glm::vec3(0.001f, 30.0f, 0.001f), glm::vec3(0.0f,0.0f,0.0f), glm::vec3(0.0f, 1.0f, 0.0f));
    glm::mat4 lightSpaceMatrix = lightProjectionMatrix * lightViewMatrix;
    // Setting up texture shader lighting
    shadowShader->use();
    shadowShader->setMat4("lightSpaceMatrix", lightSpaceMatrix);
    // Setting up texture shader lighting
    textureShader->use();
    textureShader->setVec3("lightPos", glm::vec3(0.0, 30.0, 0.0));
    textureShader->setVec3("viewPos", Renderer::camera->cameraPos);
    textureShader->setMat4("lightSpaceMatrix", lightSpaceMatrix);
    // Setting up color shader lighting
    colorShader->use();
    colorShader->setVec3("lightPos", glm::vec3(0.0, 30.0, 0.0));
    colorShader->setVec3("viewPos", Renderer::camera->cameraPos);
    colorShader->setMat4("lightSpaceMatrix", lightSpaceMatrix);
    
}
