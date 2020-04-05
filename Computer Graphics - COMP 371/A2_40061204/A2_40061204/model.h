//
//  model.h
//  A2_40061204
//
//  Created by Édouard Gagné on 2020-03-25.
//  Copyright © 2020 Édouard Gagné. All rights reserved.
//
#pragma once

#ifndef model_h
#define model_h

// GLEW
#define GLEW_STATIC
#include <GL/glew.h>
#include <string>
#include <ressources/glm/glm.hpp>
#include <renderer.h>
#include <shader.h>





class Model {
    
    public:
        glm::vec3 positionVector= glm::vec3(0.0f,0.0f,0.0f);
        glm::vec3 translateVector= glm::vec3(0.0f,0.0f,0.0f);
        glm::vec3 scaleVector= glm::vec3(1.0f,1.0f,1.0f);
        glm::vec3 angleVector = glm::vec3(0.0f,0.0f,0.0f);
        glm::mat4 modelMatrix;
        Model * baseModel;
        float scaleFactor;
        bool animation = false;
    
        Model();
        Model(glm::vec3 translateVector, glm::vec3 scaleVector, Model * baseModel);
        virtual void draw(Shader * shader);
        virtual void update(std::string attribute, float value);
        virtual void animate();
        virtual void reset();
        unsigned int loadTexture(std::string imagePath);
    
        struct ColorVertex {
            glm::vec3 position;
            glm::vec3 normal;
            glm::vec3 color;
        };
        struct TextureVertex {
            glm::vec3 position;
            glm::vec3 normal;
            glm::vec2 texture;
        };
};
#endif /* model_h */
