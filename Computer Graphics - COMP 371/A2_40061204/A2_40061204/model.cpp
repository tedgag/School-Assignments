//
//  model.cpp
//  A2_40061204
//
//  Created by Édouard Gagné on 2020-03-25.
//  Copyright © 2020 Édouard Gagné. All rights reserved.
//

#include "model.h"

#define STB_IMAGE_IMPLEMENTATION
#include <ressources/stb/stb_image.h>

Model::Model(glm::vec3 translateVector, glm::vec3 scaleVector, Model * baseModel) {
    this->translateVector=translateVector;
    this->scaleVector=scaleVector;
    this->baseModel=baseModel;
}
Model::Model(){
}
void Model::update(std::string attribute, float value) {
}

void Model::draw(Shader * shader) {
}

void Model::animate() {
}
void Model::reset() {
}
unsigned int Model::loadTexture(std::string imagePath) {
    unsigned int texture;
    glGenTextures(1, &texture);
    
    int width, height, nrComponents;
    unsigned char *data = stbi_load(imagePath.c_str(), &width, &height, &nrComponents, 0);
    if (data)
    {
        GLenum format;
        if (nrComponents == 1)
            format = GL_RED;
        else if (nrComponents == 3)
            format = GL_RGB;
        else if (nrComponents == 4)
            format = GL_RGBA;

        glBindTexture(GL_TEXTURE_2D, texture);
        glTexImage2D(GL_TEXTURE_2D, 0, format, width, height, 0, format, GL_UNSIGNED_BYTE, data);
        glGenerateMipmap(GL_TEXTURE_2D);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        stbi_image_free(data);
    }
    else
    {
        std::cout << "Texture failed to load at path: " << imagePath << std::endl;
        stbi_image_free(data);
    }

    return texture;
}
