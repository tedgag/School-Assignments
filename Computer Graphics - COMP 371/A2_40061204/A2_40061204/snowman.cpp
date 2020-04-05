//
//  snowman.cpp
//  A1_40061204
//
//  Class to draw a Snowman model from another model (such as the world model). It can draw the snowman,
//  randomize its position, change its rendering mode and update or reset its attributes.
//
//  Created by Édouard Gagné on 2020-02-23.
//

#include <snowman.h>

/* Constructor that sets the Renderer::colorShader and generate the ColorVertex Buffer Object and ColorVertex Array Object. */
Snowman::Snowman(Model * baseModel){
    this->baseModel = baseModel;
    /* Body models */
    models.push_back(new Sphere(glm::vec3(0.0f, 2.25f, 0.0f), glm::vec3(1.25f,2.0f,2.0f), this));
    // Right foot
    models.push_back(new Sphere(glm::vec3(0.0f, -2.0f, 1.0f), glm::vec3(0.5f,0.25f,0.5f), models[0]));
    models[1]->animation = true;
    // Left foot
    models.push_back(new Sphere(glm::vec3(0.0f, -2.0f, -1.0f), glm::vec3(0.5f,0.25f,0.5f), models[0]));
    models[2]->animation = true;
    // Upper body
    models.push_back(new Sphere(glm::vec3(0.0f, 2.5f, 0.0f), glm::vec3(1.0f,1.0f,1.5f), models[0]));
    // Right arm
    models.push_back(new Cube("BLACK", glm::vec3(0.0f, 0.0f, 2.25f), glm::vec3(0.25f,0.25f,2.0f), models[3]));
    models[4]->animation = true;
    // Left arm
    models.push_back(new Cube("BLACK", glm::vec3(0.0f, 0.0f, -2.25f), glm::vec3(0.25f,0.25f,2.0f), models[3]));
    models[5]->animation = true;
    // Head
    models.push_back(new Sphere(glm::vec3(0.0f, 1.5f, 0.0f), glm::vec3(0.75f,0.75f,1.0f), models[3]));
    // Right eye
    models.push_back(new Cube("BLACK", glm::vec3(0.625f, 0.25f, 0.5f), glm::vec3(0.25f,0.25f,0.25f), models[6]));
    // Left eye
    models.push_back(new Cube("BLACK", glm::vec3(0.625f, 0.25f, -0.5f), glm::vec3(0.25f,0.25f,0.25f), models[6]));
    // Nose
    models.push_back(new TexturedCube("ORANGE", glm::vec3(0.75f, 0.0f, 0.0f), glm::vec3(0.75f,0.25f,0.25f), models[6]));
    // Hat base
    models.push_back(new TexturedCube("GRAY", glm::vec3(0.0f, 0.75f, 0.0f), glm::vec3(1.5f,0.25f,2.0f), models[6]));
    // Hat top
    models.push_back(new TexturedCube("GRAY", glm::vec3(0.0f, 1.25f, 0.0f), glm::vec3(0.75f,1.0f,1.5f), models[6]));
}
/* Method to draw the model. The buffers are first bounds, and the lower body is drawn using the input world model.
   The rest of the body parts are then drawn from this body model using hierachical modelling. Any transformation
   applied to the lower body model will then propagate to the rest of the model. */
void Snowman::draw(Shader * shader) {
    modelMatrix = glm::translate(baseModel->modelMatrix, positionVector);
    modelMatrix = glm::rotate(modelMatrix, glm::radians(angleVector.y), glm::vec3(0.0, 1.0, 0.0));
    /* Lower Body */
    for (int i=0;i<models.size();i++)
        models[i]->draw(shader);
    
}
/* Method to update the snowman attributes to new values. */
void Snowman::update(std::string attribute, float value) {
    modelMatrix = glm::mat4(1.0f);
    if (attribute == "FORWARD") {
        positionVector.x+= value * sin(glm::radians(90.0f+angleVector.y));
        positionVector.z+= value * cos(glm::radians(90.0f+angleVector.y));
    }
    if (attribute == "BACKWARD") {
        positionVector.x-= value * sin(glm::radians(90.0f+angleVector.y));
        positionVector.z-= value * cos(glm::radians(90.0f+angleVector.y));
    }
    if (attribute == "LEFT") {
        positionVector.x-= value * cos(glm::radians(90.0f-angleVector.y));
        positionVector.z-= value * sin(glm::radians(90.0f-angleVector.y));
    }
    if (attribute == "RIGHT") {
        positionVector.x+= value * cos(glm::radians(90.0f-angleVector.y));
        positionVector.z+= value * sin(glm::radians(90.0f+angleVector.y));
    }
    if (attribute == "Y_ANGLE") {
        angleVector.y+= value;
    }
    if (attribute == "SCALE_FACTOR") {
        scaleFactor+= value;
        for (int i=0;i<models.size();i++)
            models[i]->update("SCALE_FACTOR", scaleFactor);
    }
    if (attribute == "RENDER_TYPE") {
        for (int i=0;i<models.size();i++)
            models[i]->update("RENDER_TYPE", 0);
    }
    
    // Out of bound checks
    if (positionVector.x > 50.0f)
        positionVector.x = 50.0f;
    if (positionVector.x < -50.0f)
        positionVector.x = -50.0f;
    if (positionVector.z > 50.0f)
        positionVector.z = 50.0f;
    if (positionVector.z < -50.0f)
        positionVector.z = -50.0f;
    // Scale factor checks
    if (scaleFactor > 5.0f)
        scaleFactor = 5.0f;
    if (scaleFactor < 0.1f)
        scaleFactor = 0.1f;
    modelMatrix = glm::translate(modelMatrix, glm::vec3(positionVector.x, positionVector.y, positionVector.z));
    modelMatrix = glm::rotate(modelMatrix, glm::radians(angleVector.y), glm::vec3(0.0, 1.0, 0.0));
}
void Snowman::animate() {
    for (int i=0;i<models.size();i++)
        models[i]->animate();
}
/* Method to randomize the snowman's position (it will remain on the grid) */
void Snowman::randomizePos() {
    positionVector.x=  -50 + (rand() % 100);
    positionVector.z=  -50 + (rand() % 100);
}
/* Method to reset the snowman attributes to their default values. */
void Snowman::reset() {
    positionVector.x = 0.0f;
    positionVector.y = 0.0f;
    positionVector.z = 0.0f;
    angleVector.y = 0.0f;
    scaleFactor = 1.0f;
    for (int i=0;i<models.size();i++)
        models[i]->reset();
}
