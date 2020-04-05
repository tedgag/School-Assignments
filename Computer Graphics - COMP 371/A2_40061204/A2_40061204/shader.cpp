//
//  shader.cpp
//  A1_40061204
//
//  Shader class that gets both the vertex and fragment shader codes from the files, compiles them and link them.
//  It also has utility functions to set uniform location values in the shader and use the program in GLFW.
//
//  Created by Édouard Gagné on 2020-02-23.
//

#include <shader.h>

/* Constructor to create a shader object. The fragment and vertex shader code are first extarcted from the file,
   compiled and attached togheter. The program is then linked and assigned to the"ProgramID" variable. */
Shader::Shader(std::string name, std::string vertex_shader_path, std::string fragment_shader_path) {
    // Setting name
    this->name = name;
    // Getting vertex shader code
    std::string VertexShaderCode = getShaderCode(vertex_shader_path);
    std::cout << (VertexShaderCode);
    // Getting fragment shader code
    std::string FragmentShaderCode = getShaderCode(fragment_shader_path);
    // Compile ColorVertex Shader
    GLuint VertexShaderProgramID = glCreateShader(GL_VERTEX_SHADER);
    char const * VertexSourcePointer = VertexShaderCode.c_str();
    glShaderSource(VertexShaderProgramID, 1, &VertexSourcePointer, nullptr);
    glCompileShader(VertexShaderProgramID);

    // Check ColorVertex Shader
    checkCompileErrors(VertexShaderProgramID, "SHADER");

    // Compile Fragment Shader
    GLuint FragmentShaderProgramID = glCreateShader(GL_FRAGMENT_SHADER);
    char const * FragmentSourcePointer = FragmentShaderCode.c_str();
    glShaderSource(FragmentShaderProgramID, 1, &FragmentSourcePointer, nullptr);
    glCompileShader(FragmentShaderProgramID);

    // Check Fragment Shader
    checkCompileErrors(FragmentShaderProgramID, "SHADER");

    // Link the program
    ProgramID = glCreateProgram();
    glAttachShader(ProgramID, VertexShaderProgramID);
    glAttachShader(ProgramID, FragmentShaderProgramID);
    glLinkProgram(ProgramID);

    // Check the program
    checkCompileErrors(FragmentShaderProgramID, "PROGRAM");

    // Delete shaders
    glDeleteShader(VertexShaderProgramID);
    glDeleteShader(FragmentShaderProgramID);

}
/* Use the shader program at this shader object ID */
void Shader::use() {
    glUseProgram(ProgramID);
}
/* Replace the uniform 4D matrix defined by a name with a new matrix */
void Shader::setMat4(const std::string &name, const glm::mat4 &mat) {
    glUniformMatrix4fv(glGetUniformLocation(ProgramID, name.c_str()), 1, GL_FALSE, &mat[0][0]);
}
void Shader::setVec3(const std::string &name, const glm::vec3 &value) {
    glUniform3fv(glGetUniformLocation(ProgramID, name.c_str()), 1, &value[0]);
}
void Shader::setInt(const std::string &name, int value) {
    glUniform1i(glGetUniformLocation(ProgramID, name.c_str()), value);
}
void Shader::setFloat(const std::string &name, float value) {
    glUniform1f(glGetUniformLocation(ProgramID, name.c_str()), value);
}
void Shader::setBool(const std::string &name, bool value) {
    glUniform1f(glGetUniformLocation(ProgramID, name.c_str()), value);
}
/* Check a shader or program for compile error */
void Shader::checkCompileErrors(GLuint shader, std::string type) {
    GLint Result = GL_FALSE;
    int InfoLogLength;
    if (type == "SHADER") {
        glGetShaderiv(shader, GL_COMPILE_STATUS, &Result);
        glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &InfoLogLength);
        if ( InfoLogLength > 0 ) {
            std::vector<char> FragmentShaderErrorMessage(InfoLogLength+1);
            glGetShaderInfoLog(shader, InfoLogLength, nullptr, &FragmentShaderErrorMessage[0]);
            printf("%s\n", &FragmentShaderErrorMessage[0]);
        }
    } else if (type == "PROGRAM") {
        glGetProgramiv(ProgramID, GL_LINK_STATUS, &Result);
        glGetProgramiv(ProgramID, GL_INFO_LOG_LENGTH, &InfoLogLength);
        if ( InfoLogLength > 0 ) {
            std::vector<char> ProgramErrorMessage(InfoLogLength+1);
            glGetProgramInfoLog(ProgramID, InfoLogLength, nullptr, &ProgramErrorMessage[0]);
            printf("%s\n", &ProgramErrorMessage[0]);
        }

    }
}
/* Get the shader code as as tring from an input stream crated using a file path */
std::string Shader::getShaderCode(std::string shader_path) {
    std::string ShaderCode;
    std::ifstream ShaderStream(shader_path, std::ios::in);
    // Read from the stream
    if(ShaderStream.is_open()){
        std::string Line = "";
        while(getline(ShaderStream, Line))
            ShaderCode += "\n" + Line;
        ShaderStream.close();
    }
    return ShaderCode;
}