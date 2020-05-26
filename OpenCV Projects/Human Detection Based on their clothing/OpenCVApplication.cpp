#include "stdafx.h"
#include "common.h"
#include <queue> 
#include <vector>
#include <random>
#include <fstream>
#include <stdio.h>

#include <opencv2/opencv.hpp>
#include <iostream>

using namespace std;
using namespace cv;


/** Function Headers */

/** Global variables */
//String upper_body_cascade_name = "C:\\Users\\Andrei\\Desktop\\Procesarea Imaginilor\\Proiect\\Proiect Viola-Jones FINAL\\OpenCVApplication-VS2017_OCV340_basic\\OpenCV\\XML\\haarcascade_upperbody.xml";
//CascadeClassifier upper_body_cascade;

String full_body_cascade_name = "C:\\Users\\Andrei\\Desktop\\Procesarea Imaginilor\\Proiect\\Proiect Viola-Jones FINAL\\OpenCVApplication-VS2017_OCV340_basic\\OpenCV\\XML\\haarcascade_fullbody.xml"; 
CascadeClassifier full_body_cascade;
RNG rng(12345);

void detectAndDisplayVJ(Mat frame, double scaleFactor);

void ResizeBoxes(cv::Rect& box) {
	box.x += cvRound(box.width*0.1);
	box.width = cvRound(box.width*0.8);
	box.y += cvRound(box.height*0.06);
	box.height = cvRound(box.height*0.8);
}

void copyImage(Mat original, Mat newOne) {


	for (int i = 0; i < original.rows; i++) {
		for (int j = 0; j < original.cols; j++) {
			newOne.at<Vec3b>(i, j) = original.at<Vec3b>(i, j);
		}
	}
}

void toGrayScale(Mat original, Mat final) {
	uchar r, b, g;
	Vec3b v3;

	for (int i = 0; i < original.rows; i++) {
		for (int j = 0; j < original.cols; j++) {
			v3 = original.at<Vec3b>(i, j);
			b = v3[0];
			g = v3[1];
			r = v3[2];
			final.at<uchar>(i, j) = (b + g + r) / 3;

		}
	}
}

std::vector<Point2i> veciniFun(Mat src, Point2i p) { //functie de parcurgere vecini
	int i = p.x;
	int j = p.y;

	std::vector<Point2i> vecini;

	for (int k = i - 1; k <= i + 1; k++) {
		for (int l = j - 1; l <= j + 1; l++) {
			if (k >= 0 && k < src.rows && l >= 0 && l < src.cols) {
				vecini.push_back(Point2i(k, l));
			}
		}
	}

	return vecini;
}

int etichetare(Mat src, Vec3b v3) {

	Mat labels = Mat::zeros(src.rows, src.cols, CV_8UC1);
	int label = 0;

	int separateObjects[256];

	for (int j = 0; j < 256; j++) {
		separateObjects[j] = 0;
	}

	for (int i = 0; i < src.rows; i++)
	{
		for (int j = 0; j < src.cols; j++)
		{
			if (src.at<Vec3b>(i, j)[0] == v3[0] && src.at<Vec3b>(i, j)[1] == v3[1] && src.at<Vec3b>(i, j)[2] == v3[2] && labels.at<uchar>(i, j) == 0)
			{
				label++;
				labels.at<uchar>(i, j) == label;
				//separateObjects[label]++;
				std::queue<Point2i> Q; // structura doua puncte sa tii coordonatele 
				Q.push(Point2i(i, j));

				while (!Q.empty())
				{
					Point2i punctCurent = Q.front();
					Q.pop();

					for (auto n : veciniFun(src, punctCurent))
					{
						if (src.at<Vec3b>(n.x, n.y)[0] == v3[0] && src.at<Vec3b>(n.x, n.y)[1] == v3[1] && src.at<Vec3b>(n.x, n.y)[2] == v3[2] && labels.at<uchar>(n.x, n.y) == 0) {
							labels.at<uchar>(n.x, n.y) = label;

							//separateObjects[label]++;

							Q.push(n);
						}
					}
				}
			}
		}
	}



	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			label = labels.at<uchar>(i, j);
			separateObjects[label]++;
		}
	}

	int max = 0;

	for (int i = 1; i < 256; i++)
	{
		printf("Size: %d\n", separateObjects[i]);
		if (max < separateObjects[i])
		{
			max = separateObjects[i];


		}
	}


	return max;
}


float * RGBToHSV(int R, int G, int B)
{

	int i = 0;
	int j = 0;
	float M, m, C, V, S, H;
	float r, g, b;

	r = (float)R / 255.0;
	g = (float)G / 255.0;
	b = (float)B / 255.0;

	M = max(max(r, g), b);
	m = min(min(r, g), b);

	C = M - m;
	V = M;

	if (V != 0)
		S = C / V;
	else
		S = 0;

	if (C != 0)
	{
		if (M == r)
			H = 60 * (g - b) / C;
		if (M == g)
			H = 120 + 60 * (b - r) / C;
		if (M == b)
			H = 240 + 60 * (r - g) / C;
	}
	else
		H = 0;
	if (H < 0)
		H = H + 360;

	float HSV[3] = { H,S,V };
	return HSV;
}


double binary_color(Mat original, char color[]) {

	Vec3b v3_2;
	v3_2[0] = 0;
	v3_2[1] = 0;
	v3_2[2] = 0;



	Mat img_binary = Mat(original.rows, original.cols, CV_8UC3);

	for (int i = 0; i < original.rows; i++) {
		for (int j = 0; j < original.cols; j++) {


			img_binary.at<Vec3b>(i, j) = v3_2;

		}
	}

	uchar r, b, g;
	Vec3b v3, save;

	for (int i = 0; i < original.rows; i++) {
		for (int j = 0; j < original.cols; j++) {
			v3 = original.at<Vec3b>(i, j);
			b = v3[0];
			g = v3[1];
			r = v3[2];

			if (strcmp(color, "red") == 0)
			{

				if ((r - b > 50) && (r - g > 40))
				{

					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 0;
					save[1] = 0;
					save[2] = 255;

					//printf("RED PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}

			}

			if (strcmp(color, "blue") == 0)
			{

				//if ((b >= 128 && g <= 128 && r <= 128) || (b >= 60 && g <= 50 && r <= 50) || (b >= 230 && g <= 205 && r <= 205))
				if ((b - r > 35) && (b - g > 10))
				{
					v3_2[0] = 255;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 255;
					save[1] = 0;
					save[2] = 0;
					//printf("BLUE PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "purple") == 0)
			{

				if ((r - g > 50) && (b - g > 50))
				{
					v3_2[0] = 255;
					v3_2[1] = 0;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 255;
					save[1] = 0;
					save[2] = 255;
					//printf("PURPLE PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}

			}

			if (strcmp(color, "yellow") == 0)
			{

				if ((r - b > 70) && (g - b > 70))
				{
					v3_2[0] = 0;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 0;
					save[1] = 255;
					save[2] = 255;
					//printf("YELLOW PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "green") == 0)
			{

				if ((g - b > 10) && (g - r > 10))
				{
					v3_2[0] = 0;
					v3_2[1] = 255;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
					//printf("GREEN PIXEL!\n");

					save[0] = 0;
					save[1] = 255;
					save[2] = 0;
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "black") == 0)
			{

				if (b <= 60 && g <= 60 && r <= 60)
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 0;
					save[1] = 0;
					save[2] = 0;
					//printf("BLACK PIXEL!\n");
				}
				else
				{
					v3_2[0] = 255;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "gray") == 0)
			{

				if ((b >= 60 && g >= 60 && r >= 60) && (b <= 190 && g <= 190 && r <= 190) && (b - r < 15) && (b - g < 15) && (r - g < 15) && (r - b < 15) && (g - b < 15) && (g - r < 15))
				{
					v3_2[0] = 150;
					v3_2[1] = 150;
					v3_2[2] = 150;
					img_binary.at<Vec3b>(i, j) = v3_2;
					//printf("BLACK PIXEL!\n");
					save[0] = 150;
					save[1] = 150;
					save[2] = 150;
				}
				else
				{
					v3_2[0] = 255;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "white") == 0)
			{

				if (b >= 190 && g >= 190 && r >= 190)
				{
					v3_2[0] = 255;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;

					save[0] = 255;
					save[1] = 255;
					save[2] = 255;
					//printf("WHITE PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}



		}
	}



	int maxSize = 0;
	maxSize = etichetare(img_binary, save);
	printf("Biggest Object: %d \n", maxSize);

	//const int size = 2580;

	cv::imshow("After color binary", img_binary);

	return maxSize;
}

Mat binary_color_img(Mat original, char color[]) {

	Vec3b v3_2;
	v3_2[0] = 0;
	v3_2[1] = 0;
	v3_2[2] = 0;



	Mat img_binary = Mat(original.rows, original.cols, CV_8UC3);

	for (int i = 0; i < original.rows; i++) {
		for (int j = 0; j < original.cols; j++) {


			img_binary.at<Vec3b>(i, j) = v3_2;

		}
	}

	uchar r, b, g;
	Vec3b v3, save;

	for (int i = 0; i < original.rows; i++) {
		for (int j = 0; j < original.cols; j++) {
			v3 = original.at<Vec3b>(i, j);
			b = v3[0];
			g = v3[1];
			r = v3[2];

			if (strcmp(color, "red") == 0)
			{

				if ((r - b > 50) && (r - g > 40))
				{

					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 0;
					save[1] = 0;
					save[2] = 255;

					//printf("RED PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}

			}

			if (strcmp(color, "blue") == 0)
			{

				//if ((b >= 128 && g <= 128 && r <= 128) || (b >= 60 && g <= 50 && r <= 50) || (b >= 230 && g <= 205 && r <= 205))
				if ((b - r > 35) && (b - g > 10))
				{
					v3_2[0] = 255;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 255;
					save[1] = 0;
					save[2] = 0;
					//printf("BLUE PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "purple") == 0)
			{

				if ((r - g > 50) && (b - g > 50))
				{
					v3_2[0] = 255;
					v3_2[1] = 0;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 255;
					save[1] = 0;
					save[2] = 255;
					//printf("PURPLE PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}

			}

			if (strcmp(color, "yellow") == 0)
			{

				if ((r - b > 70) && (g - b > 70))
				{
					v3_2[0] = 0;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 0;
					save[1] = 255;
					save[2] = 255;
					//printf("YELLOW PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "green") == 0)
			{

				if ((g - b > 10) && (g - r > 10))
				{
					v3_2[0] = 0;
					v3_2[1] = 255;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
					//printf("GREEN PIXEL!\n");

					save[0] = 0;
					save[1] = 255;
					save[2] = 0;
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "black") == 0)
			{

				if (b <= 60 && g <= 60 && r <= 60)
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
					save[0] = 0;
					save[1] = 0;
					save[2] = 0;
					//printf("BLACK PIXEL!\n");
				}
				else
				{
					v3_2[0] = 255;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "gray") == 0)
			{

				if ((b >= 60 && g >= 60 && r >= 60) && (b <= 190 && g <= 190 && r <= 190) && (b - r < 15) && (b - g < 15) && (r - g < 15) && (r - b < 15) && (g - b < 15) && (g - r < 15))
				{
					v3_2[0] = 150;
					v3_2[1] = 150;
					v3_2[2] = 150;
					img_binary.at<Vec3b>(i, j) = v3_2;
					//printf("BLACK PIXEL!\n");
					save[0] = 150;
					save[1] = 150;
					save[2] = 150;
				}
				else
				{
					v3_2[0] = 255;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}

			if (strcmp(color, "white") == 0)
			{

				if (b >= 190 && g >= 190 && r >= 190)
				{
					v3_2[0] = 255;
					v3_2[1] = 255;
					v3_2[2] = 255;
					img_binary.at<Vec3b>(i, j) = v3_2;

					save[0] = 255;
					save[1] = 255;
					save[2] = 255;
					//printf("WHITE PIXEL!\n");
				}
				else
				{
					v3_2[0] = 0;
					v3_2[1] = 0;
					v3_2[2] = 0;
					img_binary.at<Vec3b>(i, j) = v3_2;
				}
			}



		}
	}



	int maxSize = 0;
	maxSize = etichetare(img_binary, save);
	printf("Biggest Object: %d \n", maxSize);

	//const int size = 2580;

	cv::imshow("After color binary", img_binary);

	return img_binary;



}


vector<Rect>  detectAndDisplayHOG(Mat frame, int scaleFactor) {
	// Open image
	Mat img_HOG = Mat(frame.rows, frame.cols, CV_8UC3);
	copyImage(frame, img_HOG);
	std::vector<cv::Rect> detections2;

	if (!img_HOG.data) return detections2;

	// Initialize HOG descriptor and use human detection classifier coefficients
	cv::HOGDescriptor hog;
	hog.setSVMDetector(cv::HOGDescriptor::getDefaultPeopleDetector());
	hog.detectMultiScale(img_HOG, detections2, 0, cv::Size(scaleFactor / 2, scaleFactor / 2), cv::Size(scaleFactor, scaleFactor), 1.001, 2);

	// Resize detection boxes and draw them
	for (auto& detection : detections2) {
		ResizeBoxes(detection);
		cv::rectangle(img_HOG, detection.tl(), detection.br(), cv::Scalar(0, 0, 0), 2);
	}

	cv::imshow("Detect with HOG", img_HOG);

	return detections2;
}


vector<Rect> detectAndDisplayVJ(Mat frame, int scaleFactor)
{

	Mat img_VJ = Mat(frame.rows, frame.cols, CV_8UC3);
	char part[10];
	copyImage(frame, img_VJ);

	std::vector<cv::Rect> detections2;

	if (!img_VJ.data) return detections2;


	std::vector<Rect> bodies;
	int i, j, k, l, tot;
	Mat frame_VJ = Mat(frame.rows, frame.cols, CV_8UC3);
	uchar r, g, b;
	Vec3b vec;
	copyImage(frame, frame_VJ);

	printf("%d %d\n", frame.rows, frame.cols);
	printf("%d %d\n", frame_VJ.rows, frame_VJ.cols);

	full_body_cascade.detectMultiScale(frame, bodies, 1.001, 2, 0 | CV_HAAR_SCALE_IMAGE, Size(scaleFactor, 3.5*scaleFactor), Size(2 * 3 * scaleFactor, 7 * 3 * scaleFactor));

	for (size_t i = 0; i < bodies.size(); i++)
	{
		rectangle(frame_VJ, bodies[i], Scalar(255, 0, 255));
	}

	imshow("Detect with Viola-Jones", frame_VJ);
	return bodies;
}

void aimAndShoot(Mat src, vector<Rect> bodiesVJ, vector<Rect> bodiesHOG, char color[], char bodyPart[])
{
	int i, j, k, l, tot;
	Mat frame_final = Mat(src.rows, src.cols, CV_8UC3);
	uchar r, g, b;
	Vec3b vec;
	copyImage(src, frame_final);

	Mat color_mat = binary_color_img(src, color);

	if (strcmp(bodyPart, "lower") != 0)
	{
		for (size_t i = 0; i < bodiesVJ.size(); i++)
		{
			printf("%d %d\n", bodiesVJ[i].height, bodiesVJ[i].width);
			l = 0;
			tot = 0;
			for (j = bodiesVJ[i].y + bodiesVJ[i].height / 10; j < (bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 3); j++)
			{
				for (k = bodiesVJ[i].x; k < (bodiesVJ[i].x + bodiesVJ[i].width); k++)
				{
					vec = color_mat.at<Vec3b>(j, k);
					r = vec[2];
					g = vec[1];
					b = vec[0];
					tot++;
					if (!(r == 0 && g == 0 && b == 0))
						l++;
				}

			}
			printf("L:%d tot:%d\n", l, tot);
			if ((float)l / (float)tot >= 0.15)
			{
				rectangle(frame_final, bodiesVJ[i], Scalar(255, 0, 255));

				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8 + 1, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8 - 1, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8, bodiesVJ[i].x + bodiesVJ[i].width / 2 + 1) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8, bodiesVJ[i].x + bodiesVJ[i].width / 2 - 1) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8 + 2, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8 - 2, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8, bodiesVJ[i].x + bodiesVJ[i].width / 2 + 2) = { 0,0,0 };
				frame_final.at<Vec3b>(bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 8, bodiesVJ[i].x + bodiesVJ[i].width / 2 - 2) = { 0,0,0 };

			}
		}

		for (auto& detection : bodiesHOG) {
			ResizeBoxes(detection);

			l = 0;
			tot = 0;
			for (j = detection.y + detection.height / 10; j < (detection.y + 2 * (detection.height) / 3); j++)
			{
				for (k = detection.x; k < (detection.x + (detection.width)); k++)
				{
					vec = color_mat.at<Vec3b>(j, k);
					r = vec[2];
					g = vec[1];
					b = vec[0];
					tot++;
					if (!(r == 0 && g == 0 && b == 0))
						l++;
				}

			}
			printf("L:%d tot:%d\n", l, tot);
			if ((float)l / (float)tot >= 0.15)
			{
				cv::rectangle(frame_final, detection.tl(), detection.br(), cv::Scalar(0, 0, 0), 2);
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8 + 1, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8 - 1, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8, detection.x + detection.width / 2 + 1) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8, detection.x + detection.width / 2 - 1) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8 + 2, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8 - 2, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8, detection.x + detection.width / 2 + 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 2 * (detection.height) / 8, detection.x + detection.width / 2 - 2) = { 0,0,0 };

			}
		}
	}
	else
	{
		for (size_t i = 0; i < bodiesVJ.size(); i++)
		{
			printf("%d %d\n", bodiesVJ[i].height, bodiesVJ[i].width);
			l = 0;
			tot = 0;
			for (j = bodiesVJ[i].y + 2 * (bodiesVJ[i].height) / 3; j < (bodiesVJ[i].y + (bodiesVJ[i].height)); j++)
			{
				for (k = bodiesVJ[i].x; k < (bodiesVJ[i].x + bodiesVJ[i].width); k++)
				{
					vec = color_mat.at<Vec3b>(j, k);
					r = vec[2];
					g = vec[1];
					b = vec[0];
					tot++;
					if (!(r == 0 && g == 0 && b == 0))
						l++;
				}

			}
			printf("L:%d tot:%d\n", l, tot);
			if ((float)l / (float)tot >= 0.15)
			{
				rectangle(frame_final, bodiesVJ[i], Scalar(255, 0, 255));
				{
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4 + 1, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4 - 1, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4, bodiesVJ[i].x + bodiesVJ[i].width / 2 + 1) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4, bodiesVJ[i].x + bodiesVJ[i].width / 2 - 1) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4 + 2, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4 - 2, bodiesVJ[i].x + bodiesVJ[i].width / 2) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4, bodiesVJ[i].x + bodiesVJ[i].width / 2 + 2) = { 0,0,0 };
					frame_final.at<Vec3b>(bodiesVJ[i].y + 3 * (bodiesVJ[i].height) / 4, bodiesVJ[i].x + bodiesVJ[i].width / 2 - 2) = { 0,0,0 };
				}

			}
		}

		for (auto& detection : bodiesHOG) {
			ResizeBoxes(detection);

			l = 0;
			tot = 0;
			for (j = detection.y + 2 * (detection.height) / 3; j < (detection.y + detection.height); j++)
			{
				for (k = detection.x; k < (detection.x + (detection.width)); k++)
				{
					vec = color_mat.at<Vec3b>(j, k);
					r = vec[2];
					g = vec[1];
					b = vec[0];
					tot++;
					if (!(r == 0 && g == 0 && b == 0))
						l++;
				}

			}
			printf("L:%d tot:%d\n", l, tot);
			if ((float)l / (float)tot >= 0.15)
			{
				cv::rectangle(frame_final, detection.tl(), detection.br(), cv::Scalar(0, 0, 0), 2);

				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4 + 1, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4 - 1, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4, detection.x + detection.width / 2 + 1) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4, detection.x + detection.width / 2 - 1) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4 + 2, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4 - 2, detection.x + detection.width / 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4, detection.x + detection.width / 2 + 2) = { 0,0,0 };
				frame_final.at<Vec3b>(detection.y + 3 * (detection.height) / 4, detection.x + detection.width / 2 - 2) = { 0,0,0 };

			}
		}
	}

	imshow("Final Image", frame_final);

}






void imageLoader()
{
	char fname[MAX_PATH];
	Mat frame;

	if (openFileDlg(fname)) {
		frame = imread(fname, CV_LOAD_IMAGE_COLOR);
	}

	Mat img = Mat(frame.rows, frame.cols, CV_8UC3);


	copyImage(frame, img);

	char color[10];

	printf("Color:");

	cin >> color;
	cout << color << endl;

	char bodyPart[15];

	printf("Upper or lower body:");

	cin >> bodyPart;
	cout << bodyPart << endl;


	double newHeight = (img.rows * 400) / img.cols;

	printf("NHE:%f\n", newHeight);

	Mat img_2 = Mat(400, newHeight, CV_8UC1);
	resize(img, img_2, cv::Size(400, newHeight), NULL, NULL);

	int scaleFactor = binary_color(img_2, color) / 6;
	scaleFactor = (int)sqrt(scaleFactor);

	imshow("Original image", img);
	imshow("Resized image", img_2);

	vector<Rect> detectionsVJ = detectAndDisplayVJ(img_2, scaleFactor);
	vector<Rect> detectionsHOG = detectAndDisplayHOG(img_2, scaleFactor);

	aimAndShoot(img_2, detectionsVJ, detectionsHOG, color, bodyPart);

	waitKey(0);
}



/** @function main */
int main(int argc, const char** argv)
{

	if (!full_body_cascade.load(full_body_cascade_name))
	{
		printf("--(!)Error loading\n"); return -1;
	};



	imageLoader();
	return 0;
}