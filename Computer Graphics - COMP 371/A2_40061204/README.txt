A1_40061204

Created by Édouard Gagné on 2020-02-23.

This program will render a scene with a snowman on a grid with coordinates axes. The user can change the snowman position, orientation and scale using keyboard inputs. They can also change the grid orientation using different inputs (changing at the same time the snowman's orientation). They can pan and tilt the camera looking at the scene and zoom-in and out of the scene using mouse inputs.

Classes:

main.cpp :

    Main class that creates Renderer and EventHandler object and execute the game loop.
    The Renderer will render the scene during each loop and the EventHandler will handle
    the events.

renderer.cpp :

    Renderer class with the purpose of initializing and rendering the scene. It can also
    initializes GLFW and GLEW and creates the window for the scene. Furthermore, it can 
    reset the scene and camera to their default position and orientation.

eventhandler.cpp :

   Class that handles the event generated by the user. It can handle framebuffer changes, 
   mouse movements, scrolls and mouse clicks events, as well as keyboard inputs.

shader.cpp :

   Shader class that gets both the vertex and fragment shader codes from the files,
   compiles them and link them. It also has utility functions to set uniform location
   values in the shader and use the program in GLFW.

world.cpp :

   Class used to generate a model of a 100x100 grid with 3 coordinates axes. It can draw
   the grid and axes and update or reset the model's attributes.

snowman.cpp :

   Class to draw a Snowman model from another model (such as the world model). It can draw
   the snowman, randomize its position, change its rendering mode and update or reset its
   attributes.

camera.cpp :

   Camera class that can change the view and projection matrix of the models. It can also
   pan, tilt or zoom in or out based on an offset provided by the user and can reset to 
   it's default values.

Keyboard Inputs:

ESC: Close the program.
W: Move snowman forward by 0.25 units.
A: Move snowman to the left by 0.25 units.
S: Move snowman backward by 0.25 units.
D: Move snowman backward by 0.25 units.
E: Rotate snowman clockwise by 3 degrees.
Q: Rotate snowman counter-clockwise by 3 degrees.
U: Scale the snowman up by 0.05.
J: Scale the snowman down by 0.05.
Up arrow: Rotate the world clockwise along the Z axis by 1 degree.
Down arrow: Rotate the world counter-clockwise along the Z axis by 1 degree.
Left arrow: Rotate the world clockwise along the X axis by 1 degree.
Right arrow: Rotate the world counter-clockwise along the X axis by 1 degree.
TAB: Reset the models and camera to default.
SPACE: Randomize the snowman's position within the grid.
P: Change the rendering mode of the snowman to use points.
L: Change the rendering mode of the snowman to use lines.
T: Change the rendering mode of the snowman to use triangles (default).

Mouse Inputs:

Right Button + move mouse horizontally: Pan the camera to the right or left.
Middle Button + move mouse vertically: Tilt the camera up or down.
Left Button + scroll up or down: Zoom in or out of the scene.

Others:

Shader files are located in the ressources folder along with the GLM library files.
The program has been written using Xcode on OSX and as such the Xcode file is provided
to allow easier import of the project for the markers.




