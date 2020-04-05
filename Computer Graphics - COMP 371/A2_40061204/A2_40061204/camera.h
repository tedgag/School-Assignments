//
//  camera.h
//  A1_40061204
//
//  Header file for the Camera class. See "camera.cpp" for the implementation.
//
//  Created by Édouard Gagné on 2020-02-22.
//

#ifndef camera_h
#define camera_h

#define GLEW_STATIC

#include <GL/glew.h>
#include <shader.h>

#include <ressources/glm/glm.hpp>
#include <ressources/glm/gtc/matrix_transform.hpp>
#include <ressources/glm/gtc/type_ptr.hpp>

class Camera {
    public:
        // Default camera positions
        glm::vec3 cameraPos = glm::vec3(30.0f, 8.0f, 8.0f);
        glm::vec3 cameraDir = glm::vec3(-30.0f, -8.0f, -8.0f);
        // Default camera attributes. Not that the angles are obtained using trigonometry based on the default camera position.
        float panAngle = 270.0f - glm::degrees(asin(cameraPos.x / (sqrt(cameraPos.x * cameraPos.x + cameraPos.z * cameraPos.z))));
        float tiltAngle = -glm::degrees(asin(cameraPos.y / distance(cameraPos, glm::vec3(0.0f, 0.0f, 0.0f))));
        float fov = 45.0f;
        Camera();

        void shoot();
        void pan(float xOffset);
        void tilt(float yOffset);
        void zoom(double yOffset);
        void reset();
};

#endif /* camera_h */
