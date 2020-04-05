//
//  texturedcube.hpp
//  A2_40061204
//
//  Created by Édouard Gagné on 2020-03-31.
//  Copyright © 2020 Édouard Gagné. All rights reserved.
//

#ifndef texturedcube_h
#define texturedcube_h

#define GLEW_STATIC

#include <GL/glew.h>
#include <renderer.h>
#include <model.h>

#include <ressources/glm/glm.hpp>
#include <ressources/glm/gtc/matrix_transform.hpp>
#include <ressources/glm/gtc/type_ptr.hpp>

class TexturedCube : public Model {
    // ColorVertex Buffer Object and ColorVertex Array Object
    unsigned int VBO, VAO, texture;
    
    public:
        std::string color;
        TexturedCube(std::string color, glm::vec3 translateVector, glm::vec3 scaleVector, Model * baseModel);
        void draw(Shader * shader);
        void update(std::string attribute, float value);
        void reset();
    private:
        void bindBuffers();
        void useColors();
        void useTextures();
};

#endif /* texturedcube_h */
