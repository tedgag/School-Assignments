//
//  Snowman.h
//  A1_40061204
//
//  Header file for the Snowman class. See "snowman.cpp" for the implementation.
//
//  Created by Édouard Gagné on 2020-02-22.
//

#ifndef snowman_h
#define snowman_h

#define GLEW_STATIC
#include <GL/glew.h>
#include <stdlib.h>
#include <cube.h>
#include <texturedcube.h>
#include <sphere.h>

#include <ressources/glm/glm.hpp>
#include <ressources/glm/gtc/matrix_transform.hpp>
#include <ressources/glm/gtc/type_ptr.hpp>

class Snowman : public Model {
    
    
    // Default attributes
    glm::vec3 positionVector = glm::vec3(0.0f, 0.0f, 0.0f);
    float scaleFactor = 1.0f;

    // Models
    
    std::vector<Model*> models;
    
    public:
        Snowman(Model * baseModel);
        void draw(Shader * shader);
        void update(std::string attribute, float value);
        void animate();
        void randomizePos();
        void reset();
    

};




#endif
