//
//  main.cpp
//  OpenGL Shadows
//
//  Created by CGIS on 05/12/16.
//  Copyright � 2016 CGIS. All rights reserved.
//

#define GLEW_STATIC

#include <iostream>
#include "glm/glm.hpp"//core glm functionality
#include "glm/gtc/matrix_transform.hpp"//glm extension for generating common transformation matrices
#include "glm/gtc/matrix_inverse.hpp"
#include "glm/gtc/type_ptr.hpp"
#include "GLEW/glew.h"
#include "GLFW/glfw3.h"
#include <string>
#include "Shader.hpp"
#include "Camera.hpp"
#include "SkyBox.hpp"
#define TINYOBJLOADER_IMPLEMENTATION

#include "Model3D.hpp"
#include "Mesh.hpp"

int glWindowWidth = 640;
int glWindowHeight = 480;
int retina_width, retina_height;
GLFWwindow* glWindow = NULL;

const GLuint SHADOW_WIDTH = 2048, SHADOW_HEIGHT = 2048;

glm::mat4 model;
GLuint modelLoc;
glm::mat4 view;
GLuint viewLoc;
glm::mat4 projection;
GLuint projectionLoc;
glm::mat3 normalMatrix;
GLuint normalMatrixLoc;

glm::mat3 lightDirMatrix;
GLuint lightDirMatrixLoc;
glm::mat3 lightDirMatrix2;
GLuint lightDirMatrixLoc2;

glm::vec3 lightDir;
GLuint lightDirLoc;
glm::vec3 lightColor;
GLuint lightColorLoc;


//cod a doua lumina

glm::vec3 lightDir2;
GLuint lightDirLoc2;
glm::vec3 lightColor2;
GLuint lightColorLoc2;

gps::Camera myCamera(glm::vec3(0.0f, 1.0f, 2.5f), glm::vec3(0.0f, 0.0f, 0.0f));
GLfloat cameraSpeed = 0.1f;

bool pressedKeys[1024];
GLfloat angle;

GLfloat angleCameraX;
GLfloat angleCameraY;

GLfloat animatieViteza=0.0f;
GLfloat animatieViteza2 = 0.0f;
GLfloat animatieViteza3 = 0.0f;


GLfloat lightAngle;
GLfloat lightAngle2;

gps::Model3D myModel;
gps::Model3D ground;
gps::Model3D lightCube;
gps::Model3D lightCube2;

gps::Model3D ATmodel;
gps::Model3D CT1;
gps::Model3D CT2;
gps::Model3D CT3;
gps::Model3D CT4;
gps::Model3D CT5;

gps::Model3D VBD;
gps::Model3D BD1;
gps::Model3D BD2;
gps::Model3D CBD;


gps::Model3D RepublicShip;
gps::Model3D SeparatistShip;

gps::Model3D shot1;
gps::Model3D shot2;
gps::Model3D shot3;
gps::Model3D shot4;
gps::Model3D shot5;
gps::Model3D shot6;






gps::Shader myCustomShader;
gps::Shader lightShader;
gps::Shader depthMapShader;

GLuint shadowMapFBO;
GLuint depthMapTexture;


gps::SkyBox mySkyBox;
gps::Shader skyboxShader;
std::vector<const GLchar*> faces;






GLenum glCheckError_(const char *file, int line)
{
	GLenum errorCode;
	while ((errorCode = glGetError()) != GL_NO_ERROR)
	{
		std::string error;
		switch (errorCode)
		{
		case GL_INVALID_ENUM:                  error = "INVALID_ENUM"; break;
		case GL_INVALID_VALUE:                 error = "INVALID_VALUE"; break;
		case GL_INVALID_OPERATION:             error = "INVALID_OPERATION"; break;
		case GL_STACK_OVERFLOW:                error = "STACK_OVERFLOW"; break;
		case GL_STACK_UNDERFLOW:               error = "STACK_UNDERFLOW"; break;
		case GL_OUT_OF_MEMORY:                 error = "OUT_OF_MEMORY"; break;
		case GL_INVALID_FRAMEBUFFER_OPERATION: error = "INVALID_FRAMEBUFFER_OPERATION"; break;
		}
		std::cout << error << " | " << file << " (" << line << ")" << std::endl;
	}
	return errorCode;
}
#define glCheckError() glCheckError_(__FILE__, __LINE__)

void windowResizeCallback(GLFWwindow* window, int width, int height)
{
	fprintf(stdout, "window resized to width: %d , and height: %d\n", width, height);
	//TODO
	//for RETINA display
	glfwGetFramebufferSize(glWindow, &retina_width, &retina_height);

	myCustomShader.useShaderProgram();

	//set projection matrix
	glm::mat4 projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	//send matrix data to shader
	GLint projLoc = glGetUniformLocation(myCustomShader.shaderProgram, "projection");
	glUniformMatrix4fv(projLoc, 1, GL_FALSE, glm::value_ptr(projection));
	
	lightShader.useShaderProgram();
	
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "projection"), 1, GL_FALSE, glm::value_ptr(projection));

	//set Viewport transform
	glViewport(0, 0, retina_width, retina_height);
}

void keyboardCallback(GLFWwindow* window, int key, int scancode, int action, int mode)
{
	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
		glfwSetWindowShouldClose(window, GL_TRUE);

	if (key >= 0 && key < 1024)
	{
		if (action == GLFW_PRESS)
			pressedKeys[key] = true;
		else if (action == GLFW_RELEASE)
			pressedKeys[key] = false;
	}
}

void mouseCallback(GLFWwindow* window, double xpos, double ypos)
{

}

void processMovement()
{

	if (pressedKeys[GLFW_KEY_Q]) {
		angle += 1.0f;
		if (angle > 360.0f)
			angle -= 360.0f;
	}

	if (pressedKeys[GLFW_KEY_E]) {
		angle -= 1.0f;
		if (angle < 0.0f)
			angle += 360.0f;
	}

	if (pressedKeys[GLFW_KEY_W]) {
		myCamera.move(gps::MOVE_FORWARD, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_S]) {
		myCamera.move(gps::MOVE_BACKWARD, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_A]) {
		myCamera.move(gps::MOVE_LEFT, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_D]) {
		myCamera.move(gps::MOVE_RIGHT, cameraSpeed);
	}

	//coade roteste camera mai bine

	if (pressedKeys[GLFW_KEY_LEFT]) {
		angleCameraY += 0.005f;

	}

	if (pressedKeys[GLFW_KEY_K]) {
		animatieViteza += 0.1f;
		animatieViteza2 += 0.1f;
		animatieViteza3 += 0.1f;

		if (animatieViteza >= 15.0f)
		{
			animatieViteza = 0.0f;
		}
		if (animatieViteza2 >= 11.0f)
		{
			animatieViteza2 = 0.0f;

		}
		if (animatieViteza3 >= 8.0f)
		{
			animatieViteza3 = 0.0f;
		}

	}

	if (pressedKeys[GLFW_KEY_RIGHT]) {
		angleCameraY -= 0.005f;
	}

	if (pressedKeys[GLFW_KEY_DOWN]) {
		angleCameraX -= 0.005f;
	}

	if (pressedKeys[GLFW_KEY_UP]) {
		angleCameraX += 0.005f;
	}

	if (pressedKeys[GLFW_KEY_RIGHT] || pressedKeys[GLFW_KEY_LEFT] || pressedKeys[GLFW_KEY_DOWN] || pressedKeys[GLFW_KEY_UP]) {

		if (angleCameraY < 360.0f)
			angleCameraY += 720.0f;

		if (angleCameraY > 360.0f)
			angleCameraY -= 720.0f;

		if (angleCameraX < 360.0f)
			angleCameraX += 720.0f;

		if (angleCameraX > 360.0f)
			angleCameraX -= 720.0f;

		myCamera.rotate(angleCameraX, angleCameraY);
	}



	if (pressedKeys[GLFW_KEY_J]) {

		lightAngle += 0.3f;
		if (lightAngle > 360.0f)
			lightAngle -= 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}

	if (pressedKeys[GLFW_KEY_L]) {
		lightAngle -= 0.3f; 
		if (lightAngle < 0.0f)
			lightAngle += 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}	
}

bool initOpenGLWindow()
{
	if (!glfwInit()) {
		fprintf(stderr, "ERROR: could not start GLFW3\n");
		return false;
	}

	//for Mac OS X
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    glfwWindowHint(GLFW_SAMPLES, 4);

	glWindow = glfwCreateWindow(glWindowWidth, glWindowHeight, "OpenGL Shader Example", NULL, NULL);
	if (!glWindow) {
		fprintf(stderr, "ERROR: could not open window with GLFW3\n");
		glfwTerminate();
		return false;
	}

	glfwSetWindowSizeCallback(glWindow, windowResizeCallback);
	glfwMakeContextCurrent(glWindow);

	// start GLEW extension handler
	glewExperimental = GL_TRUE;
	glewInit();

	// get version info
	const GLubyte* renderer = glGetString(GL_RENDERER); // get renderer string
	const GLubyte* version = glGetString(GL_VERSION); // version as a string
	printf("Renderer: %s\n", renderer);
	printf("OpenGL version supported %s\n", version);

	//for RETINA display
	glfwGetFramebufferSize(glWindow, &retina_width, &retina_height);

	glfwSetKeyCallback(glWindow, keyboardCallback);
	glfwSetCursorPosCallback(glWindow, mouseCallback);

	return true;
}

void initOpenGLState()
{
	glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
	glViewport(0, 0, retina_width, retina_height);
    glEnable(GL_FRAMEBUFFER_SRGB);
	glEnable(GL_DEPTH_TEST); // enable depth-testing
	glDepthFunc(GL_LESS); // depth-testing interprets a smaller value as "closer"
	glEnable(GL_CULL_FACE); // cull face
	glCullFace(GL_BACK); // cull back face
	glFrontFace(GL_CCW); // GL_CCW for counter clock-wise
}

void initFBOs()
{
	//generate FBO ID
	glGenFramebuffers(1, &shadowMapFBO);

	//create depth texture for FBO
	glGenTextures(1, &depthMapTexture);
	glBindTexture(GL_TEXTURE_2D, depthMapTexture);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT,
		SHADOW_WIDTH, SHADOW_HEIGHT, 0, GL_DEPTH_COMPONENT, GL_FLOAT, NULL);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

	//attach texture to FBO
	glBindFramebuffer(GL_FRAMEBUFFER, shadowMapFBO);
	glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthMapTexture, 0);
	glDrawBuffer(GL_NONE);
	glReadBuffer(GL_NONE);
	glBindFramebuffer(GL_FRAMEBUFFER, 0);
}

glm::mat4 computeLightSpaceTrMatrix()
{
	const GLfloat near_plane = 1.0f, far_plane = 10.0f;
	glm::mat4 lightProjection = glm::ortho(-20.0f, 20.0f, -20.0f, 20.0f, near_plane, far_plane);

	glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
	glm::mat4 lightView = glm::lookAt(myCamera.getCameraTarget() + 1.0f * lightDirTr, myCamera.getCameraTarget(), glm::vec3(0.0f, 1.0f, 0.0f));

	return lightProjection * lightView;
}

void initModels()
{
	myModel = gps::Model3D("objects/Droideka/cis_inf_droideka.obj", "objects/Droideka/");
	ground = gps::Model3D("objects/ground/ground.obj", "objects/ground/");
	lightCube = gps::Model3D("objects/Soare/7bw0yr2zitz0.obj", "objects/Soare/");

	lightCube2 = gps::Model3D("objects/Soare/7bw0yr2zitz0.obj", "objects/Soare/");

	//cod

	ATmodel = gps::Model3D("objects/ATWALKER/9r75a70mtlzy.obj", "objects/ATWALKER/");
	CT1 = gps::Model3D("objects/Clone Trooper/yrvmkp0k44ws.obj", "objects/Clone Trooper/");
	CT2 = CT1;
	CT3 = CT1;
	CT4 = CT1;
	CT5 = CT1;

	BD1 = gps::Model3D("objects/SimpleDroid/rqg0rbtwqopz.obj", "objects/SimpleDroid/");
	BD2 = BD1;

	VBD = gps::Model3D("objects/Comando Droid/8yblzyh5yw07.obj", "objects/Comando Droid/");

	SeparatistShip= gps::Model3D("objects/separatist ship/1hpy6k1tc6bg.obj", "objects/separatist ship/");
	RepublicShip = gps::Model3D("objects/Republic Ship/rzgr9hz6fijd.obj", "objects/Republic Ship/");

	shot1 = gps::Model3D("objects/shoot/71u4jmha3r1l.obj", "objects/shoot/");
	shot2 = shot1;
	shot3 = shot1;
	shot4 = gps::Model3D("objects/shoot2/71u4jmha3r1l.obj", "objects/shoot2/");
	shot5 = shot4;
	shot6 = shot4;


	faces.push_back("skybox/tatooine_right.tga");
	faces.push_back("skybox/tatooine_left.tga");
	faces.push_back("skybox/tatooine_top.tga");
	faces.push_back("skybox/tatooine_bottom.tga");
	faces.push_back("skybox/tatooine_back.tga");
	faces.push_back("skybox/tatooine_front.tga");
	mySkyBox.Load(faces);
}

void initShaders()
{
	myCustomShader.loadShader("shaders/shaderStart.vert", "shaders/shaderStart.frag");
	lightShader.loadShader("shaders/lightCube.vert", "shaders/lightCube.frag");
	depthMapShader.loadShader("shaders/simpleDepthMap.vert", "shaders/simpleDepthMap.frag");
	skyboxShader.loadShader("shaders/skyboxShader.vert", "shaders/skyboxShader.frag");
}

void initUniforms()
{
	myCustomShader.useShaderProgram();

	modelLoc = glGetUniformLocation(myCustomShader.shaderProgram, "model");

	viewLoc = glGetUniformLocation(myCustomShader.shaderProgram, "view");
	
	normalMatrixLoc = glGetUniformLocation(myCustomShader.shaderProgram, "normalMatrix");
	
	lightDirMatrixLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightDirMatrix");

	lightDirMatrixLoc2 = glGetUniformLocation(myCustomShader.shaderProgram, "lightDirMatrix");

	projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	projectionLoc = glGetUniformLocation(myCustomShader.shaderProgram, "projection");
	glUniformMatrix4fv(projectionLoc, 1, GL_FALSE, glm::value_ptr(projection));	

	//set the light direction (direction towards the light)
	lightDir = glm::vec3(0.0f, 2.0f, 4.0f);
	lightDirLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightDir");
	glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDir));

	//set light color
	lightColor = glm::vec3(1.0f, 1.0f, 1.0f); //white light
	lightColorLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightColor");
	glUniform3fv(lightColorLoc, 1, glm::value_ptr(lightColor));

	//a doua
	lightDir2 = glm::vec3(0.0f, 3.0f, 5.0f);
	lightDirLoc2 = glGetUniformLocation(myCustomShader.shaderProgram, "lightDir");
	glUniform3fv(lightDirLoc2, 1, glm::value_ptr(lightDir2));

	//set light color
	lightColor2 = glm::vec3(1.0f, 1.0f, 1.0f); //yellow light
	lightColorLoc2 = glGetUniformLocation(myCustomShader.shaderProgram, "lightColor");
	glUniform3fv(lightColorLoc2, 1, glm::value_ptr(lightColor2));

	lightShader.useShaderProgram();
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "projection"), 1, GL_FALSE, glm::value_ptr(projection));
}

void renderScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	processMovement();	

	//render the scene to the depth buffer (first pass)

	depthMapShader.useShaderProgram();

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "lightSpaceTrMatrix"),
		1,
		GL_FALSE,
		glm::value_ptr(computeLightSpaceTrMatrix()));
		
	glViewport(0, 0, SHADOW_WIDTH, SHADOW_HEIGHT);
	glBindFramebuffer(GL_FRAMEBUFFER, shadowMapFBO);
	glClear(GL_DEPTH_BUFFER_BIT);
	
	//create model matrix for nanosuit
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.0f));
	model = glm::rotate(model, glm::radians(angle + 90.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	myModel.Draw(depthMapShader);

	//create model matrix for CT1
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-5.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	CT1.Draw(depthMapShader);

	//CT2
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-5.5f, 0.0f, 1.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	CT2.Draw(depthMapShader);

	//CT3
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.5f, 0.0f, 1.2f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	CT3.Draw(depthMapShader);

	//CT4
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-8.5f, 0.0f, -2.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	CT4.Draw(depthMapShader);


	//CT5
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.5f, 0.0f, -1.5f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	CT5.Draw(depthMapShader);

	//VBD
	model = glm::translate(glm::mat4(1.0f), glm::vec3(6.5f, 0.0f, 0.0f));
	model = glm::scale(model, glm::vec3(0.2f, 0.2f, 0.2f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 0, 1));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	VBD.Draw(depthMapShader);

	//BD1
	
	model = glm::translate(glm::mat4(1.0f), glm::vec3(2.5f, -1.1f, -1.5f));
	model = glm::scale(model, glm::vec3(0.01f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	BD1.Draw(depthMapShader);

	//BD2

	model = glm::translate(glm::mat4(1.0f), glm::vec3(3.5f, -1.1f, 2.5f));
	model = glm::scale(model, glm::vec3(0.01f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	BD2.Draw(depthMapShader);

	//REPUBLIC SHIP

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.001f, 0.001f, 0.001f));
	//model = glm::translate(model, glm::vec3(5.0f, 5.0f, 0.0f));
	//model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	////send model matrix to shader
	//glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
	//	1,
	//	GL_FALSE,
	//	glm::value_ptr(model));

	//RepublicShip.Draw(depthMapShader);


	//AT WALKER
	model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(model, glm::vec3(-100000.0f, -5000.0f, 0.0f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),
		1,
		GL_FALSE,
		glm::value_ptr(model));

	ATmodel.Draw(depthMapShader);

	//create model matrix for ground
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.0f));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"), 
						1, 
						GL_FALSE, 
						glm::value_ptr(model));

	ground.Draw(depthMapShader);

	glBindFramebuffer(GL_FRAMEBUFFER, 0);

	//render the scene (second pass)

	myCustomShader.useShaderProgram();

	//send lightSpace matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(myCustomShader.shaderProgram, "lightSpaceTrMatrix"),
		1,
		GL_FALSE,
		glm::value_ptr(computeLightSpaceTrMatrix()));

	//send view matrix to shader
	view = myCamera.getViewMatrix();
	glUniformMatrix4fv(glGetUniformLocation(myCustomShader.shaderProgram, "view"),
		1,
		GL_FALSE,
		glm::value_ptr(view));	

	//compute light direction transformation matrix
	lightDirMatrix = glm::mat3(glm::inverseTranspose(view));
	lightDirMatrix2 = glm::mat3(glm::inverseTranspose(view));
	//send lightDir matrix data to shader
	glUniformMatrix3fv(lightDirMatrixLoc, 1, GL_FALSE, glm::value_ptr(lightDirMatrix));
	glUniformMatrix3fv(lightDirMatrixLoc2, 1, GL_FALSE, glm::value_ptr(lightDirMatrix2));

	glViewport(0, 0, retina_width, retina_height);
	myCustomShader.useShaderProgram();

	//bind the depth map
	glActiveTexture(GL_TEXTURE3);
	glBindTexture(GL_TEXTURE_2D, depthMapTexture);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "shadowMap"), 3);
	
	//create model matrix for nanosuit
	
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.0f));
	model = glm::rotate(model, glm::radians(angle + 90.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	myModel.Draw(myCustomShader);

	//create model matrix for CT1
	
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-5.0f, 0.0f, 0.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));


	CT1.Draw(myCustomShader);

	//CT2

	model = glm::translate(glm::mat4(1.0f), glm::vec3(-5.5f, 0.0f, 1.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	CT2.Draw(myCustomShader);

	//CT3

	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.5f, 0.0f, 1.2f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	CT3.Draw(myCustomShader);

	//CT4

	model = glm::translate(glm::mat4(1.0f), glm::vec3(-8.5f, 0.0f, -2.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	CT4.Draw(myCustomShader);

	//CT5

	model = glm::translate(glm::mat4(1.0f), glm::vec3(-6.5f, 0.0f, -1.5f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	CT5.Draw(myCustomShader);

	//BD1

	
	model = glm::translate(glm::mat4(1.0f), glm::vec3(2.5f, -1.1f, -1.5f));
	model = glm::scale(model, glm::vec3(0.01f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	BD1.Draw(myCustomShader);

	//BD2


	model = glm::translate(glm::mat4(1.0f), glm::vec3(3.5f, -1.1f, 2.5f));
	model = glm::scale(model, glm::vec3(0.01f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	BD2.Draw(myCustomShader);

	//VBD

	model = glm::translate(glm::mat4(1.0f), glm::vec3(6.5f, 0.0f, 0.0f));
	model = glm::scale(model, glm::vec3(0.2f, 0.2f, 0.2f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	VBD.Draw(myCustomShader);

	//REPUBLIC SHIP


	model = glm::scale(glm::mat4(1.0f), glm::vec3(0.0004f, 0.0004f, 0.0004f));
	model = glm::translate(model, glm::vec3(5.0f, 30000.0f, 0.0f));
	model = glm::rotate(model, glm::radians(135.0f), glm::vec3(0, 1, 0));
	//model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	RepublicShip.Draw(myCustomShader);


	//SEPARATIST SHIP


	model = glm::scale(glm::mat4(1.0f), glm::vec3(0.0004f, 0.0004f, 0.0004f));
	model = glm::translate(model, glm::vec3(-40000.0f, 35000.0f, 0.0f));
	model = glm::rotate(model, glm::radians(0.0f), glm::vec3(0, 1, 0));
	//model = glm::rotate(model, glm::radians(180.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	SeparatistShip.Draw(myCustomShader);


	//AT WALKER

	model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(model, glm::vec3(-100000.0f, -5000.0f, 0.0f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 1, 0));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	ATmodel.Draw(myCustomShader);

	//shot4

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-(animatieViteza)+ 2.1f , -1.5f, -1.5f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot4.Draw(myCustomShader);

	//shot5

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-(animatieViteza2)+3.1f, -1.5f, 2.5f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot5.Draw(myCustomShader);

	//shot6

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-(animatieViteza3)-1.3f, -1.8f, 0.4f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot6.Draw(myCustomShader);

	//shot7

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3(-(animatieViteza3)-1.3f, -1.8f, -0.4f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot6.Draw(myCustomShader);


	//shot1

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3((animatieViteza)-3.8f, -1.3f, -0.0f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot1.Draw(myCustomShader);

	//shot2

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3((animatieViteza2)+1.2f -5.5f, -1.3f, 1.0f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot1.Draw(myCustomShader);

	//shot3

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3((animatieViteza3)+1.2f - 6.5f, -1.3f, 1.2f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot1.Draw(myCustomShader);

	//shot4

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3((animatieViteza3)+1.2f - 8.5f, -1.3f, -2.0f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot1.Draw(myCustomShader);

	//shot5

	//model = glm::scale(glm::mat4(1.0f), glm::vec3(0.00017f, 0.00017f, 0.00017f));
	model = glm::translate(glm::mat4(1.0f), glm::vec3((animatieViteza2)+1.2f - 6.5f, -1.3f, -1.5f));
	model = glm::scale(model, glm::vec3(0.04f, 0.01f, 0.01f));
	model = glm::rotate(model, glm::radians(90.0f), glm::vec3(0, 0, 1));
	//send model matrix data to shader	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//compute normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	shot1.Draw(myCustomShader);
		
	//create model matrix for ground
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.0f));
	//send model matrix data to shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

	//create normal matrix
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	//send normal matrix data to shader
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));

	ground.Draw(myCustomShader);

	//draw a white cube around the light

	lightShader.useShaderProgram();

	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "view"), 1, GL_FALSE, glm::value_ptr(view));

	model = glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f));
	model = glm::translate(model, 10.0f * lightDir);
	model = glm::scale(model, glm::vec3(1.0f, 1.0f, 1.0f));
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));

	lightCube.Draw(lightShader);


	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "view"), 1, GL_FALSE, glm::value_ptr(view));

	model = glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f));
	model = glm::translate(model, 10.0f * lightDir2);
	model = glm::scale(model, glm::vec3(2.0f, 2.0f, 2.0f));
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));

	lightCube2.Draw(lightShader);
	
	/*glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);*/

	//aici skybox




	skyboxShader.useShaderProgram();
	view = myCamera.getViewMatrix();
	glUniformMatrix4fv(glGetUniformLocation(skyboxShader.shaderProgram, "view"), 1, GL_FALSE,
		glm::value_ptr(view));

	projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	glUniformMatrix4fv(glGetUniformLocation(skyboxShader.shaderProgram, "projection"), 1, GL_FALSE,
		glm::value_ptr(projection));


	mySkyBox.Draw(skyboxShader, view, projection);



	
}

int main(int argc, const char * argv[]) {

	initOpenGLWindow();
	initOpenGLState();
	initFBOs();
	initModels();
	initShaders();
	initUniforms();	
	glCheckError();
	while (!glfwWindowShouldClose(glWindow)) {
		renderScene();

		glfwPollEvents();
		glfwSwapBuffers(glWindow);
	}

	//close GL context and any other GLFW resources
	glfwTerminate();

	return 0;
}
