//
//  camera.cpp
//  A1_40061204
//
//  Camera class that can change the view and projection matrix of the models. It can also pan, tilt or soom in or out
//  based on an offset provided by the user and can reset to it's default values.
//
//  Created by Édouard Gagné on 2020-02-23.
//

#include <camera.h>

/* Constructor that sets the Renderer::colorShader and screen dimensions. */
Camera::Camera() {

}
/* Method that changes the pan (or yaw) angle of the camera based on an offset and sensitivity. */
void Camera::pan(float xOffset) {
    //Sensitivity that determine on how much the camera will pan based on the offset.
    float sensitivity = 0.1f;
    
    panAngle += xOffset * sensitivity;

    //Updating the camera direction
    glm::vec3 newDir;
    newDir.x = cos(glm::radians(panAngle)) * cos(glm::radians(tiltAngle));
    newDir.y = sin(glm::radians(tiltAngle));
    newDir.z = sin(glm::radians(panAngle)) * cos(glm::radians(tiltAngle));
    cameraDir = glm::normalize(newDir);
}
/* Method that changes the tilt (or pitch) angle ofthe camera based on an offset and sensitivity. */
void Camera::tilt(float yOffset) {
    // Sensitivity that determine on how much the camera will pan based on the offset.
    float sensitivity = 0.1f;
    
    tiltAngle += yOffset * sensitivity;
    
    // Checking if tilt angle is at a maximum or minimum
    if (tiltAngle > 89.0f)
        tiltAngle = 89.0f;
    if (tiltAngle < -89.0f)
        tiltAngle = -89.0f;
        
    //Updating the camera direction
    glm::vec3 newFront;
    newFront.x = cos(glm::radians(panAngle)) * cos(glm::radians(tiltAngle));
    newFront.y = sin(glm::radians(tiltAngle));
    newFront.z = sin(glm::radians(panAngle)) * cos(glm::radians(tiltAngle));
    cameraDir = glm::normalize(newFront);
}
/* Method that zoom in or out by changing the Field of View (fov) based on an offset. */
void Camera::zoom(double yOffset) {
    if (fov >= 0.0f && fov <= 45.0f)
        fov -= yOffset;
    // Checking if zoom is at maximum or minimum
    if (fov <= 1.0f)
        fov = 1.0f;
    if (fov >= 45.0f)
        fov = 45.0f;
}
/* Method that resets the camera to its default position and orientation. */
void Camera::reset() {
    cameraPos = glm::vec3(30.0f, 8.0f, 8.0f);
    cameraDir = glm::vec3(-30.0f, -8.0f, -8.0f);
    panAngle = 270.0f - glm::degrees(asin(cameraPos.x / (sqrt(cameraPos.x * cameraPos.x + cameraPos.z * cameraPos.z))));
    tiltAngle = -glm::degrees(asin(cameraPos.y / distance(cameraPos, glm::vec3(0.0f, 0.0f, 0.0f))));
    fov = 45.0f;
}
