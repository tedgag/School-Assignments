//
//  cube.h
//  A2_40061204
//
//  Created by Édouard Gagné on 2020-03-24.
//  Copyright © 2020 Édouard Gagné. All rights reserved.
//

#ifndef cube_h
#define cube_h

#define GLEW_STATIC

#include <GL/glew.h>
#include <renderer.h>
#include <model.h>

#include <ressources/glm/glm.hpp>
#include <ressources/glm/gtc/matrix_transform.hpp>
#include <ressources/glm/gtc/type_ptr.hpp>

class Cube : public Model {
    // ColorVertex Buffer Object and ColorVertex Array Object
    unsigned int VBO, VAO;
    
    public:
        std::string color;
        bool forward = true;
        
        Cube(std::string color, glm::vec3 translateVector, glm::vec3 scaleVector, Model * baseModel);
        void draw(Shader * shader);
        void update(std::string attribute, float value);
        void reset();
        void animate();
    
        
    private:
        void bindBuffers();
        void useColors();
        void useTextures();
        
};

#endif /* cube_h */


