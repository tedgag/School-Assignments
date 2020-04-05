//
//  world.h
//  A1_40061204
//
//  Header file for the World class. See "world.cpp" for the implementation.
//
//  Created by Édouard Gagné on 2020-02-22.
//

#ifndef world_h
#define world_h

#define GLEW_STATIC

#include <GL/glew.h>
#include <snowman.h>
#include <camera.h>

#include <ressources/glm/glm.hpp>
#include <ressources/glm/gtc/matrix_transform.hpp>
#include <ressources/glm/gtc/type_ptr.hpp>



class World : public Model {
    
    // Default attributes
    float zAngle=0.0f;
    float xAngle=0.0f;
    
    public:
        unsigned int VBO, VAO, texture;
        Snowman * snowman;
        World();
        World(glm::vec3 translateVector, glm::vec3 scaleVector, float yAngle, Model * baseModel);
        void draw(Shader * shader);
        void update(std::string attribute, float value);
        void reset();
    private:
        void bindBuffers();
        void useColors();
        void useTextures();
};

#endif
