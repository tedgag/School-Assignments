//
//  cube.cpp
//  A2_40061204
//
//  Created by Édouard Gagné on 2020-03-24.
//  Copyright © 2020 Édouard Gagné. All rights reserved.
//

#include "cube.h"


Cube::Cube(std::string color, glm::vec3 translateVector, glm::vec3 scaleVector, Model * baseModel) : Model (translateVector,scaleVector,baseModel) {
    this->color = color;


       ColorVertex blackVertices[] = {
           { glm::vec3(-0.5f, -0.5f, -0.5f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f, -0.5f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f, -0.5f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f, -0.5f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f,  0.5f, -0.5f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f, -0.5f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           
           { glm::vec3(-0.5f, -0.5f,  0.5f), glm::vec3(0.0f, 0.0f, 1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f,  0.5f), glm::vec3(0.0f, 0.0f, 1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f,  0.5f), glm::vec3(0.0f, 0.0f, 1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f,  0.5f), glm::vec3(0.0f, 0.0f, 1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f,  0.5f,  0.5f), glm::vec3(0.0f, 0.0f, 1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f,  0.5f), glm::vec3(0.0f, 0.0f, 1.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           
           { glm::vec3(-0.5f,  0.5f,  0.5f), glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f,  0.5f, -0.5f), glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f, -0.5f), glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f, -0.5f), glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f,  0.5f), glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f,  0.5f,  0.5f), glm::vec3(-1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
       
           { glm::vec3( 0.5f,  0.5f,  0.5f), glm::vec3(1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f, -0.5f), glm::vec3(1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f, -0.5f), glm::vec3(1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f, -0.5f), glm::vec3(1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f,  0.5f), glm::vec3(1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f,  0.5f), glm::vec3(1.0f, 0.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           
           { glm::vec3(-0.5f, -0.5f, -0.5f), glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f, -0.5f), glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f,  0.5f), glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f, -0.5f,  0.5f), glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f,  0.5f), glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f, -0.5f, -0.5f), glm::vec3(0.0f, -1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           
           { glm::vec3(-0.5f,  0.5f, -0.5f), glm::vec3(0.0f, 1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f, -0.5f), glm::vec3(0.0f, 1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f,  0.5f), glm::vec3(0.0f, 1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3( 0.5f,  0.5f,  0.5f), glm::vec3(0.0f, 1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f,  0.5f,  0.5f), glm::vec3(0.0f, 1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)},
           { glm::vec3(-0.5f,  0.5f, -0.5f), glm::vec3(0.0f, 1.0f, 0.0f), glm::vec3(0.2f, 0.2f, 0.2f)}
       };
       glGenVertexArrays(1, &VAO);
       glGenBuffers(1, &VBO);
       
       bindBuffers();
       glBufferData(GL_ARRAY_BUFFER, sizeof(blackVertices), blackVertices, GL_STATIC_DRAW);
       // position attribute
       glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(ColorVertex), (void*)0);
       glEnableVertexAttribArray(0);
       // normal attribute
       glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, sizeof(ColorVertex), (void*)(sizeof(glm::vec3)));
       glEnableVertexAttribArray(1);
       // color attribute
       glVertexAttribPointer(2, 3, GL_FLOAT, GL_FALSE, sizeof(ColorVertex), (void*)(2* sizeof(glm::vec3)));
       glEnableVertexAttribArray(2);
       
    scaleFactor = 1.0f;
    
}
void Cube::draw(Shader * shader) {
    if (shader->name == "TEXTURE")
        shader = Renderer::colorShader;
    shader->use();
    bindBuffers();
    modelMatrix = glm::rotate(baseModel->modelMatrix, glm::radians(angleVector.y), glm::vec3(0.0, 1.0, 0.0));
    modelMatrix = glm::translate(modelMatrix, scaleFactor * translateVector);
    
    Renderer::colorShader->setMat4("model", glm::scale(modelMatrix, scaleFactor * scaleVector));
    glDrawArrays(Renderer::renderingMode, 0, 36);
}
void Cube::bindBuffers() {
    // Binding array first then buffers
    glBindVertexArray(VAO);
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
}

void Cube::update(std::string attribute, float value) {
    if (attribute == "SCALE_FACTOR") {
        scaleFactor= value;
    }
}
void Cube::reset() {
    scaleFactor= 1;
    angleVector.y = 0.0f;
}
void Cube::animate() {
    if (animation) {
        if (angleVector.y>=45.0f ) {
            forward = false;
        } else if (angleVector.y<=-45.0f){
            forward = true;
        }
        if (forward) {
            angleVector.y += 3.0f;
        } else {
            angleVector.y -= 3.0f;
        }
        
    }
    
}



