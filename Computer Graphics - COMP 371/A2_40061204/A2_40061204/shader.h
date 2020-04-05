//
//  shader.h
//  A1_40061204
//
//  Header file for the Shader class. See "shader.cpp" for the implementation.
//
//  Created by Édouard Gagné on 2020-02-22.
//

#ifndef shader_h
#define shader_h

// GLEW
#define GLEW_STATIC
#include <GL/glew.h>

#include <ressources/glm/glm.hpp>

#include <stdlib.h>

#include <stdio.h>
#include <string>
#include <vector>
#include <iostream>
#include <fstream>

class Shader {
    public:
        std::string name;
        Shader(std::string name, std::string vertex_shader_path, std::string fragment_shader_path);
        void use();
        void setMat4(const std::string &name, const glm::mat4 &mat);
        void setVec3(const std::string &name, const glm::vec3 &value);
        void setInt(const std::string &name, int value);
        void setFloat(const std::string &name, float value);
        void setBool(const std::string &name, bool value);
    private:
        GLuint ProgramID;
        void checkCompileErrors(GLuint shader, std::string type);
        std::string getShaderCode(std::string shader_path);
};
#endif
