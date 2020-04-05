//
//  world.cpp
//  A1_40061204
//
//  Class used to generate a model of a 100x100 grid with 3 coordinates axes. It can draw the grid and axes
//  and update or reset the model's attributes. 
//
//  Created by Édouard Gagné on 2020-02-23.
//

#include <world.h>


/* Constructor that sets the Renderer::colorShader and generate the ColorVertex Buffer Object and ColorVertex Array Object. */

World::World(){
    useTextures();
    snowman = new Snowman(this);
    /* Creating camera */
}
/* Method to draw the model. The buffers are first bounds, and the orientation is changed to the x and z angle
   values. The 100x100 grid is then drawn by generating a total of 100 horizontal and 100 vertical lines
   of length 100.0f with (0,0,0) as origin. The x (red), y (green) and z (blue) axes are then drawn at (0,0,0)
   as lines of length 6. The model is returned to be used by the Snowman class. */
void World::draw(Shader * shader) {

    shader->use();
    bindBuffers();
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, texture);
    // Setting world orientation
    modelMatrix = glm::mat4(1.0f);
    modelMatrix = glm::rotate(modelMatrix, glm::radians(zAngle), glm::vec3(0.0f, 0.0f, 1.0f));
    modelMatrix = glm::rotate(modelMatrix, glm::radians(xAngle), glm::vec3(1.0f, 0.0f, 0.0f));
    // Drawing Ground
    glBindVertexArray(VAO);
    glm::mat4 gridModel = modelMatrix;
    gridModel = glm::translate(gridModel, glm::vec3(50.0f, 0.0f, 50.0f));
    gridModel = glm::rotate(gridModel, glm::radians(90.0f), glm::vec3(1.0f, 0.0f, 0.0f));
    gridModel = glm::scale(gridModel, glm::vec3(100.0f, 100.0f, 100.0f));
    shader->setMat4("model", gridModel);
    glDrawArrays(GL_TRIANGLES, 0, 6);
 
    snowman->draw(shader);
}
/* Method to update the world attributes to new values. */
void World::update(std::string attribute, float value) {
     if (attribute == "X_ANGLE") {
        xAngle+= value;
    } else if (attribute == "Z_ANGLE") {
        zAngle+= value;
    } else if (attribute == "RENDER_TYPE") {
        if (Renderer::textureToggle)
            useTextures();
        else
            useColors();
    }
}
/* Method to reset the world attributes to their default values. */
void World::reset() {
    zAngle=0.0f;
    xAngle=0.0f;
    snowman->reset();
    
}
/* Method to bid the ColorVertex Buffer Object and ColorVertex Array Object. */
void World::bindBuffers() {
    // Binding array first then buffers
    glBindVertexArray(VAO);
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
}
void World::useColors() {
    /* Buffers and array data */
    glDeleteVertexArrays(1, &VAO);
    glDeleteBuffers(1, &VBO);
    glGenVertexArrays(1, &VAO);
    glGenBuffers(1, &VBO);
    ColorVertex vertices[] = {
        // Grid square (white)
        {glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.9f, 0.9f, 0.9f)},
        {glm::vec3(0.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.9f, 0.9f, 0.9f)},
        {glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.9f, 0.9f, 0.9f)},
        {glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.9f, 0.9f, 0.9f)},
        {glm::vec3(-1.0f, -1.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.9f, 0.9f, 0.9f)},
        {glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.9f, 0.9f, 0.9f)},
    };
    bindBuffers();
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
       
    // position attribute
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(ColorVertex), (void*)0);
    glEnableVertexAttribArray(0);
    // normal attribute
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, sizeof(ColorVertex), (void*)(sizeof(glm::vec3)));
    glEnableVertexAttribArray(1);
    // color attribute
    glVertexAttribPointer(2, 3, GL_FLOAT, GL_FALSE, sizeof(ColorVertex), (void*)(2* sizeof(glm::vec3)));
    glEnableVertexAttribArray(2);
}
void World::useTextures() {
    /* Buffers and array data */
    glDeleteVertexArrays(1, &VAO);
    glDeleteBuffers(1, &VBO);
    glGenVertexArrays(1, &VAO);
    glGenBuffers(1, &VBO);
    TextureVertex vertices[] = {
        // Grid square (white)
        {glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec2(0.0f, 100.0f)},
        {glm::vec3(0.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec2(100.0f, 100.0f)},
        {glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec2(100.0f, 0.0f)},
        {glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec2(100.0f, 0.0f)},
        {glm::vec3(-1.0f, -1.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec2(0.0f, 0.0f)},
        {glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec2(0.0f, 100.0f)},
    };
    bindBuffers();
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
    
    // position attribute
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(TextureVertex), (void*)0);
    glEnableVertexAttribArray(0);
    // normal attribute
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, sizeof(TextureVertex), (void*)(sizeof(glm::vec3)));
    glEnableVertexAttribArray(1);
    // texture attribute
    glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, sizeof(TextureVertex), (void*)(2* sizeof(glm::vec3)));
    glEnableVertexAttribArray(2);
    
    // load and create a texture
    // -------------------------
    texture = loadTexture("ressources/textures/snow.jpg");
}

