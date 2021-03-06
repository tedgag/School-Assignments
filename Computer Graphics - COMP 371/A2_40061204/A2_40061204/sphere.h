//
//  sphere.h
//  A2_40061204
//
//  Created by Édouard Gagné on 2020-03-26.
//  Copyright © 2020 Édouard Gagné. All rights reserved.
//

#ifndef sphere_h
#define sphere_h

#define GLEW_STATIC

#include <GL/glew.h>
#include <renderer.h>
#include <model.h>

#include <ressources/glm/glm.hpp>
#include <ressources/glm/gtc/matrix_transform.hpp>
#include <ressources/glm/gtc/type_ptr.hpp>

class Sphere : public Model {
    // ColorVertex Buffer Object and ColorVertex Array Object
    unsigned int VBO, VAO;
    unsigned int nbOfVertices;
    bool forward = true;
    public:
        Sphere(glm::vec3 translateVector, glm::vec3 scaleVector, Model * baseModel);
        void draw(Shader * shader);
        void update(std::string attribute, float value);
        void reset();
        void animate();
        void bindBuffers();
};

#endif /* sphere_h */
