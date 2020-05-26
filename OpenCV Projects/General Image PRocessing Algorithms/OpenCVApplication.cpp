// OpenCVApplication.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "common.h"
#include <queue> 
#include <vector>
#include <random>
#include <fstream>




void testOpenImage()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src;
		src = imread(fname);
		imshow("image",src);
		waitKey();
	}
}

void testOpenImagesFld()
{
	char folderName[MAX_PATH];
	if (openFolderDlg(folderName)==0)
		return;
	char fname[MAX_PATH];
	FileGetter fg(folderName,"bmp");
	while(fg.getNextAbsFile(fname))
	{
		Mat src;
		src = imread(fname);
		imshow(fg.getFoundFileName(),src);
		if (waitKey()==27) //ESC pressed
			break;
	}
}

void testImageOpenAndSave()
{
	Mat src, dst;

	src = imread("Images/Lena_24bits.bmp", CV_LOAD_IMAGE_COLOR);	// Read the image

	if (!src.data)	// Check for invalid input
	{
		printf("Could not open or find the image\n");
		return;
	}

	// Get the image resolution
	Size src_size = Size(src.cols, src.rows);

	// Display window
	const char* WIN_SRC = "Src"; //window for the source image
	namedWindow(WIN_SRC, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_SRC, 0, 0);

	const char* WIN_DST = "Dst"; //window for the destination (processed) image
	namedWindow(WIN_DST, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_DST, src_size.width + 10, 0);

	cvtColor(src, dst, CV_BGR2GRAY); //converts the source image to a grayscale one

	imwrite("Images/Lena_24bits_gray.bmp", dst); //writes the destination to file

	imshow(WIN_SRC, src);
	imshow(WIN_DST, dst);

	printf("Press any key to continue ...\n");
	waitKey(0);
}

void testNegativeImage()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		double t = (double)getTickCount(); // Get the current time [s]
		
		Mat src = imread(fname,CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = Mat(height,width,CV_8UC1);
		// Asa se acceseaaza pixelii individuali pt. o imagine cu 8 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				uchar val = src.at<uchar>(i,j);
				uchar neg = MAX_PATH-val;
				dst.at<uchar>(i,j) = neg;
			}
		}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image",src);
		imshow("negative image",dst);
		waitKey();
	}
}


void test2Colors()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		double t = (double)getTickCount(); // Get the current time [s]

		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = Mat(height, width, CV_8UC1);
		// Asa se acceseaaza pixelii individuali pt. o imagine cu 8 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				uchar neg;
				uchar val = src.at<uchar>(i, j);
				if (val < 150)
				{
					 neg = 0;
				}
				else
				{
					 neg = 255;
				}
				dst.at<uchar>(i, j) = neg;
			}
		}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image", src);
		imshow("2 color image", dst);
		waitKey();
	}
}

void colorareCuIzolare()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		double t = (double)getTickCount(); // Get the current time [s]

		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = Mat(height, width, CV_8UC1);

		Mat dst2 = Mat(height, width, CV_8UC1);
		// Asa se acceseaaza pixelii individuali pt. o imagine cu 8 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				uchar neg;
				uchar val = src.at<uchar>(i, j);
				if (val < 180)
				{
					neg = 0;
				}
				else
				{
					neg = 255;
				}
				dst.at<uchar>(i, j) = neg;
			}
		}


		for (int k = 0; k < 10; k++){
			for (int i = 0; i < height; i++)
			{
				for (int j = 0; j < width; j++)
				{
					uchar neg;
					uchar val = dst.at<uchar>(i, j);

					if (i - 1 > 0 && i + 1 < 255 && j - 1 > 0 && j + 1 < 0) {
						uchar val1 = dst.at<uchar>(i - 1, j - 1);
						uchar val2 = dst.at<uchar>(i - 1, j);
						uchar val3 = dst.at<uchar>(i, j - 1);
						uchar val4 = dst.at<uchar>(i + 1, j + 1);
						uchar val5 = dst.at<uchar>(i, j + 1);
						uchar val6 = dst.at<uchar>(i + 1, j);
						uchar val7 = dst.at<uchar>(i - 1, j + 1);
						uchar val8 = dst.at<uchar>(i + 1, j - 1);
						if (val1 == 0 && val2 == 0 && val3 == 0 && val4 == 0 && val5 == 0 && val6 == 0 && val7 == 0 && val8 == 0)
						{
							neg = 0;
						}
						else
						{
							neg = val;
						}
					}
					else {

						neg = val;
					}

					dst2.at<uchar>(i, j) = neg;

				}
			
			}
			dst = dst2;
		}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image", src);
		imshow("2 color image cu izolare", dst2);
		waitKey();
	}
}

void testParcurgereSimplaDiblookStyle()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = src.clone();

		double t = (double)getTickCount(); // Get the current time [s]

		// the fastest approach using the “diblook style”
		uchar *lpSrc = src.data;
		uchar *lpDst = dst.data;
		int w = (int) src.step; // no dword alignment is done !!!
		for (int i = 0; i<height; i++)
			for (int j = 0; j < width; j++) {
				uchar val = lpSrc[i*w + j];
				lpDst[i*w + j] = 255 - val;
			}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image",src);
		imshow("negative image",dst);
		waitKey();
	}
}

void testColor2Gray()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src = imread(fname);

		int height = src.rows;
		int width = src.cols;

		Mat dst = Mat(height,width,CV_8UC1);

		// Asa se acceseaaza pixelii individuali pt. o imagine RGB 24 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				Vec3b v3 = src.at<Vec3b>(i,j);
				uchar b = v3[0];
				uchar g = v3[1];
				uchar r = v3[2];
				dst.at<uchar>(i,j) = (r+g+b)/3;
			}
		}
		
		imshow("input image",src);
		imshow("gray image",dst);
		waitKey();
	}
}

void testBGR2HSV()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname);
		int height = src.rows;
		int width = src.cols;

		// Componentele d eculoare ale modelului HSV
		Mat H = Mat(height, width, CV_8UC1);
		Mat S = Mat(height, width, CV_8UC1);
		Mat V = Mat(height, width, CV_8UC1);

		// definire pointeri la matricele (8 biti/pixeli) folosite la afisarea componentelor individuale H,S,V
		uchar* lpH = H.data;
		uchar* lpS = S.data;
		uchar* lpV = V.data;

		Mat hsvImg;
		cvtColor(src, hsvImg, CV_BGR2HSV);

		// definire pointer la matricea (24 biti/pixeli) a imaginii HSV
		uchar* hsvDataPtr = hsvImg.data;

		for (int i = 0; i<height; i++)
		{
			for (int j = 0; j<width; j++)
			{
				int hi = i*width * 3 + j * 3;
				int gi = i*width + j;

				lpH[gi] = hsvDataPtr[hi] * 510 / 360;		// lpH = 0 .. 255
				lpS[gi] = hsvDataPtr[hi + 1];			// lpS = 0 .. 255
				lpV[gi] = hsvDataPtr[hi + 2];			// lpV = 0 .. 255
			}
		}

		imshow("input image", src);
		imshow("H", H);
		imshow("S", S);
		imshow("V", V);

		waitKey();
	}
}

void testResize()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src;
		src = imread(fname);
		Mat dst1,dst2;
		//without interpolation
		resizeImg(src,dst1,320,false);
		//with interpolation
		resizeImg(src,dst2,320,true);
		imshow("input image",src);
		imshow("resized image (without interpolation)",dst1);
		imshow("resized image (with interpolation)",dst2);
		waitKey();
	}
}

void testCanny()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src,dst,gauss;
		src = imread(fname,CV_LOAD_IMAGE_GRAYSCALE);
		double k = 0.4;
		int pH = 50;
		int pL = (int) k*pH;
		GaussianBlur(src, gauss, Size(5, 5), 0.8, 0.8);
		Canny(gauss,dst,pL,pH,3);
		imshow("input image",src);
		imshow("canny",dst);
		waitKey();
	}
}

void testVideoSequence()
{
	VideoCapture cap("Videos/rubic.avi"); // off-line video from file
	//VideoCapture cap(0);	// live video from web cam
	if (!cap.isOpened()) {
		printf("Cannot open video capture device.\n");
		waitKey(0);
		return;
	}
		
	Mat edges;
	Mat frame;
	char c;

	while (cap.read(frame))
	{
		Mat grayFrame;
		cvtColor(frame, grayFrame, CV_BGR2GRAY);
		Canny(grayFrame,edges,40,100,3);
		imshow("source", frame);
		imshow("gray", grayFrame);
		imshow("edges", edges);
		c = cvWaitKey(0);  // waits a key press to advance to the next frame
		if (c == 27) {
			// press ESC to exit
			printf("ESC pressed - capture finished\n"); 
			break;  //ESC pressed
		};
	}
}


void testSnap()
{
	VideoCapture cap(0); // open the deafult camera (i.e. the built in web cam)
	if (!cap.isOpened()) // openenig the video device failed
	{
		printf("Cannot open video capture device.\n");
		return;
	}

	Mat frame;
	char numberStr[256];
	char fileName[256];
	
	// video resolution
	Size capS = Size((int)cap.get(CV_CAP_PROP_FRAME_WIDTH),
		(int)cap.get(CV_CAP_PROP_FRAME_HEIGHT));

	// Display window
	const char* WIN_SRC = "Src"; //window for the source frame
	namedWindow(WIN_SRC, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_SRC, 0, 0);

	const char* WIN_DST = "Snapped"; //window for showing the snapped frame
	namedWindow(WIN_DST, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_DST, capS.width + 10, 0);

	char c;
	int frameNum = -1;
	int frameCount = 0;

	for (;;)
	{
		cap >> frame; // get a new frame from camera
		if (frame.empty())
		{
			printf("End of the video file\n");
			break;
		}

		++frameNum;
		
		imshow(WIN_SRC, frame);

		c = cvWaitKey(10);  // waits a key press to advance to the next frame
		if (c == 27) {
			// press ESC to exit
			printf("ESC pressed - capture finished");
			break;  //ESC pressed
		}
		if (c == 115){ //'s' pressed - snapp the image to a file
			frameCount++;
			fileName[0] = NULL;
			sprintf(numberStr, "%d", frameCount);
			strcat(fileName, "Images/A");
			strcat(fileName, numberStr);
			strcat(fileName, ".bmp");
			bool bSuccess = imwrite(fileName, frame);
			if (!bSuccess) 
			{
				printf("Error writing the snapped image\n");
			}
			else
				imshow(WIN_DST, frame);
		}
	}

}

void MyCallBackFunc(int event, int x, int y, int flags, void* param)
{
	//More examples: http://opencvexamples.blogspot.com/2014/01/detect-mouse-clicks-and-moves-on-image.html
	Mat* src = (Mat*)param;
	if (event == CV_EVENT_LBUTTONDOWN)
		{
			printf("Pos(x,y): %d,%d  Color(RGB): %d,%d,%d\n",
				x, y,
				(int)(*src).at<Vec3b>(y, x)[2],
				(int)(*src).at<Vec3b>(y, x)[1],
				(int)(*src).at<Vec3b>(y, x)[0]);
		}
}

void testMouseClick()
{
	Mat src;
	// Read image from file 
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		src = imread(fname);
		//Create a window
		namedWindow("My Window", 1);

		//set the callback function for any mouse event
		setMouseCallback("My Window", MyCallBackFunc, &src);

		//show the image
		imshow("My Window", src);

		// Wait until user press some key
		waitKey(0);
	}
}

/* Histogram display function - display a histogram using bars (simlilar to L3 / PI)
Input:
name - destination (output) window name
hist - pointer to the vector containing the histogram values
hist_cols - no. of bins (elements) in the histogram = histogram image width
hist_height - height of the histogram image
Call example:
showHistogram ("MyHist", hist_dir, 255, 200);
*/
void showHistogram(const std::string& name, int* hist, const int  hist_cols, const int hist_height)
{
	Mat imgHist(hist_height, hist_cols, CV_8UC3, CV_RGB(255, 255, 255)); // constructs a white image

	//computes histogram maximum
	int max_hist = 0;
	for (int i = 0; i<hist_cols; i++)
	if (hist[i] > max_hist)
		max_hist = hist[i];
	double scale = 1.0;
	scale = (double)hist_height / max_hist;
	int baseline = hist_height - 1;

	for (int x = 0; x < hist_cols; x++) {
		Point p1 = Point(x, baseline);
		Point p2 = Point(x, baseline - cvRound(hist[x] * scale));
		line(imgHist, p1, p2, CV_RGB(255, 0, 255)); // histogram bins colored in magenta
	}

	imshow(name, imgHist);
}


void histogramaPeImagine()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname);

		int height = src.rows;
		int width = src.cols;

		Mat dst = Mat(height, width, CV_8UC1);




		// Asa se acceseaaza pixelii individuali pt. o imagine RGB 24 biti/pixel
		// Varianta ineficienta (lenta)

		int temporar = 0;
		float max = 0;
		int save = 0;

		int vectorHist[256];

		float vectorHistMaxime[256];


		for (int k = 0; k < 256; k++)
		{
			vectorHist[k] = 0;


		}

		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				Vec3b v3 = src.at<Vec3b>(i, j);
				uchar b = v3[0];
				uchar g = v3[1];
				uchar r = v3[2];
				temporar = (r + g + b) / 3;
				vectorHist[temporar]++;

			}
		}

		imshow("input image", src);

		//showHistogram("Histograma", vectorHist, 255, 255);

		//FDP
		int M = height * width;
		for (int k = 0; k < 256; k++)
		{
			vectorHistMaxime[k] = (float)vectorHist[k] / (float)M;
		}

		int WH = 30;

		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				Vec3b v3 = src.at<Vec3b>(i, j);
				uchar b = v3[0];
				uchar g = v3[1];
				uchar r = v3[2];
				temporar = (r + g + b) / 3;

				if (temporar >= 255 - WH)
				{
					dst.at<uchar>(i, j) = 255;
				}
				else
					if (temporar <= WH)
					{
						dst.at<uchar>(i, j) = 0;
					}
					else
					{
						max = 0;
						save = 0;
						for (int y = temporar - WH; y < temporar + WH; y++)
						{
							//printf(" Val: %d\n", vectorHistMaxime[y]);
							if (vectorHist[y] > max)
							{
								max = vectorHistMaxime[y];
								save = y;
							}
						}
						//printf(" Val: %d %d\n", max, save);
						dst.at<uchar>(i, j) = save;
					}



			}
		}

		imshow("Dupa histograma", dst);
	}
		waitKey();
}

//Laborator 4

void detaliiImagine()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname);

		int height = src.rows;
		int width = src.cols;

		int checker = 0;

		Mat dst = Mat(height, width, CV_8UC1);

		long int vectorculori[256][9];
		int colorCounter = 0;

		for (int i = 0; i < 256; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				vectorculori[i][j] = 0;
			}
		}

		for (int j = 0; j <256; j++)
		{
			vectorculori[j][5] = height+1;
			vectorculori[j][7] = width+1;
		}


		for (int i = 1; i < height-1; i++)
		{
			for (int j = 1; j < width-1; j++)
			{
				Vec3b v3 = src.at<Vec3b>(i, j);
				uchar b = v3[0];
				uchar g = v3[1];
				uchar r = v3[2];


				Vec3b v31 = src.at<Vec3b>(i+1, j);
				uchar b1 = v31[0];
				uchar g1 = v31[1];
				uchar r1 = v31[2];

				Vec3b v32 = src.at<Vec3b>(i, j+1);
				uchar b2 = v32[0];
				uchar g2 = v32[1];
				uchar r2 = v32[2];

				Vec3b v33 = src.at<Vec3b>(i-1, j);
				uchar b3 = v33[0];
				uchar g3 = v33[1];
				uchar r3 = v33[2];

				Vec3b v34 = src.at<Vec3b>(i, j-1);
				uchar b4 = v34[0];
				uchar g4 = v34[1];
				uchar r4 = v34[2];


				checker = 0;
				//printf(" Culoare %d %d %d \n", b, g, r);
				for (int k = 0; k < 10; k++)
				{
					if (vectorculori[k][0] == b && vectorculori[k][1] == g && vectorculori[k][2] == r)
					{
						vectorculori[k][3]++;
						checker = 1;

						//printf("BING\n");

						if (b != b1 || b != b2 || b != b3 || b != b4 || g != g1 || g != g2 || g != g3 || g != g4 || r != r1 || r != r2 || r != r3 || r != r4)
						{
							vectorculori[k][4]++;
							//printf("BINGOOOO\n");
						}

						if (i < vectorculori[k][5])
						{
							vectorculori[k][5] = i;
						}

						if (i > vectorculori[k][6])
						{
							vectorculori[k][6] = i;
						}

						if (j < vectorculori[k][7])
						{
							vectorculori[k][7] = j;
						}

						if (j > vectorculori[k][8])
						{
							vectorculori[k][8] = j;
						}
					}
				}

				if (checker == 0)
				{
					vectorculori[colorCounter][0] = b;
					vectorculori[colorCounter][1] = g;
					vectorculori[colorCounter][2] = r;
					vectorculori[colorCounter][3] = 1;
					vectorculori[colorCounter][4] ++;
					colorCounter++;
				}


			}
		}

		for (int i = 0; i <= colorCounter; i++)
		{

			if (vectorculori[i][3] != 0)
			{
				printf(" Obiect %d Culoare B:%d G:%d R:%d \n Arie: %d         Perimetru: %d       Centru Greutate: X= %d Y= %d\n", i, vectorculori[i][0], vectorculori[i][1], vectorculori[i][2],  vectorculori[i][3], vectorculori[i][4], (vectorculori[i][5] + vectorculori[i][6])/2, (vectorculori[i][7] + vectorculori[i][8])/2);
				//printf("Obiect %d Culoare B : %d G : %d R : %d  Margini: %d %d %d %d\n", i, vectorculori[i][0], vectorculori[i][1], vectorculori[i][2], vectorculori[i][3], vectorculori[i][5], vectorculori[i][6], vectorculori[i][7], vectorculori[i][8] );
			}

		}

		imshow("input image", src);

		
	}
}

//Lab5


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

void afisareImgEtich(Mat src, int labels) {
	std::default_random_engine gen;
	std::uniform_int_distribution<int> d(0, 255);

	//sa generezi numere intre 0 si 255

	Mat dst = Mat::zeros(src.rows, src.cols, CV_8UC3);

	int label; 
	Vec3b *labelColor = new Vec3b[labels + 1];

	for (int i = 0; i < labels + 1; i++)
		for (int j = 0; j < 3; j++)
			labelColor[i][j] = 255;

	//generare culori random
	for (int i = 1; i < labels + 1; i++) {
		labelColor[i][0] = d(gen);
		labelColor[i][1] = d(gen);
		labelColor[i][2] = d(gen);
	}

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			label = src.at<uchar>(i, j);

			//punem culorile random pe obiecte etichetate
			dst.at<Vec3b>(i, j)[0] = labelColor[label][0];
			dst.at<Vec3b>(i, j)[1] = labelColor[label][1];
			dst.at<Vec3b>(i, j)[2] = labelColor[label][2];
		}
	}

	imshow("Colorare dupa Etichetare", dst);
	waitKey(0);
}

void etichetare() {
	char fname[MAX_PATH];
	Mat src;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat labels = Mat::zeros(src.rows, src.cols, CV_8UC1);
	int label = 0;

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			if (src.at<uchar>(i, j) == 0 && labels.at<uchar>(i, j) == 0) {
				label++;
				labels.at<uchar>(i, j) == label;
				std::queue<Point2i> Q; // structura doua puncte sa tii coordonatele 
				Q.push(Point2i(i, j));

				while (!Q.empty()) {
					Point2i punctCurent = Q.front();
					Q.pop();

					for (auto n : veciniFun(src, punctCurent)) { //https://www.geeksforgeeks.org/range-based-loop-c/ fara auto nu merge
						if (src.at<uchar>(n.x, n.y) == 0 && labels.at<uchar>(n.x, n.y) == 0) {
							labels.at<uchar>(n.x, n.y) = label;
							Q.push(n);
						}
					}
				}
			}
		}
	}

	afisareImgEtich(labels, label);
	//waitKey(0);
}


//Lab 6 conturarea 


Point2i veciniContur(Mat src, int x, int y, int dir) {
	int directie1[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	int directie2[] = { 1, 1, 0, -1, -1, -1, 0, 1 };

	int x1 = x + directie1[dir];
	int y1 = y + directie2[dir];

	if (x1 < 0 || x1 >= src.rows || y1 < 0 || y1 >= src.cols) {
		return Point2i(-1, -1);
	}

	return Point2i(x1, y1);
}

void contur() {
	char fname[MAX_PATH];
	Mat src;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}
	std::vector<int> vectorDirectii;
	std::vector<Point2i> puncteContur;

	Point2i punct;

	int dir = 7;
	int check = 0;
	int counter = 0;

	for (int i = 0; i < src.rows && check == 0; i++) {
		for (int j = 0; j < src.cols && check == 0; j++) {
			if (src.at<uchar>(i, j) == 0) {
				punct = Point2i(i, j);
				puncteContur.push_back(punct);
				vectorDirectii.push_back(dir);
				check = 1;
			}
		}
	}



	do {
		punct = puncteContur.back();
		dir = vectorDirectii.back();

		printf(" x: %d y: %d   directie: %d\n", punct.x, punct.y, dir);


		if (dir % 2 == 0)
			dir = (dir + 7) % 8;
		else
			dir = (dir + 6) % 8;


		Point2i pointAux = veciniContur(src, punct.x, punct.y, dir);


		while (src.at<uchar>(pointAux.x, pointAux.y) != 0 || pointAux.x == -1 || pointAux.y == -1) {
			dir = (dir + 1) % 8;
			pointAux = veciniContur(src, punct.x, punct.y, dir);
		}

		puncteContur.push_back(pointAux);
		vectorDirectii.push_back(dir);
		counter++;

	} while ( (puncteContur.at(0) != puncteContur.at(puncteContur.size() - 2) && puncteContur.at(1) != puncteContur.at(puncteContur.size() - 1)) || puncteContur.size() < 3);

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			dst.at<uchar>(i, j) == 255;
		}
	}

	for (auto p : puncteContur) {
		dst.at<uchar>(p.x, p.y) = 0;
	}

	imshow("Imagine Originala", src);
	imshow("Contur", dst);
	waitKey(0);
}

//Lab7


void dilatare()
{
	char fname[MAX_PATH];
	Mat src;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			dst.at<uchar>(i, j) = 255;
		}
	}

	for (int i = 1; i < src.rows; i++) {
		for (int j = 1; j < src.cols; j++) {
			if (src.at<uchar>(i, j) == 0) {
				dst.at<uchar>(i-1, j) = 0;
				dst.at<uchar>(i, j-1) = 0;
				dst.at<uchar>(i +1, j) = 0;
				dst.at<uchar>(i, j+1) = 0;
				dst.at<uchar>(i, j) = 0;
			}
		}
	}


	imshow("Imagine Originala", src);
	imshow("Dilatare", dst);
	waitKey(0);
}

void eroziune()
{
	char fname[MAX_PATH];
	Mat src;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			dst.at<uchar>(i, j) = 255;
		}
	}

	for (int i = 1; i < (src.rows - 1); i++) {
		for (int j = 1; j < (src.cols - 1); j++) {
			if (src.at<uchar>(i, j) == 255 || src.at<uchar>(i-1, j) == 255 || src.at<uchar>(i + 1, j) == 255 || src.at<uchar>(i, j +1) == 255 || src.at<uchar>(i+1, j) == 255) {
				dst.at<uchar>(i, j) = 255;//overkill
			}
			else
			{
				dst.at<uchar>(i, j) = 0;
			}
		}
	}


	imshow("Imagine Originala", src);
	imshow("Eroziunea", dst);
	waitKey(0);
}

void umplere()
{
	char fname[MAX_PATH];
	Mat src;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			dst.at<uchar>(i, j) =255;
		}
	}


	Mat dstContur = Mat(src.rows, src.cols, CV_8UC1);
	Mat dstFinal = Mat(src.rows, src.cols, CV_8UC1);

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			dstFinal.at<uchar>(i, j) = 255;
		}
	}

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			dstContur.at<uchar>(i, j) = 255;
		}
	}




	Mat dstComplement = Mat(src.rows, src.cols, CV_8UC1);

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {

			if (src.at<uchar>(i, j) == 255) {
				dstComplement.at<uchar>(i, j) = 0;
			}
			if (src.at<uchar>(i, j) == 0) {
				dstComplement.at<uchar>(i, j) = 255;
			}
		}
	}





	for (int i = 1; i < (src.rows - 1); i++) {
		for (int j = 1; j < (src.cols - 1); j++) {
			if (src.at<uchar>(i - 1, j) == 255 || src.at<uchar>(i + 1, j) == 255 || src.at<uchar>(i, j + 1) == 255 || src.at<uchar>(i + 1, j) == 255) {
				dst.at<uchar>(i, j) = 255;//overkill
			}
			else
			{
				dst.at<uchar>(i, j) = 0;
			}
		}
	}

	for (int i = 0; i < src.rows ; i++) {
		for (int j = 1; j < src.cols; j++) {
			if ((src.at<uchar>(i, j) == 255 && dst.at<uchar>(i, j) == 0) || (src.at<uchar>(i, j) == 0 && dst.at<uchar>(i, j) == 255))
			{
				dstContur.at<uchar>(i, j) = 0;
				dstFinal.at<uchar>(i, j) = 0;
			}
		}
	}

	imshow("Contur", dstContur);

	for (int k = 0; k < 20; k++) { //am pus aici un factor de umplere de 20 pt ca discutasem ca merge destul de greu cu un factor mai mare
		//conturul obiectelor se va umple din ce in ce mai mult pana cand acestea vor arata ca imaginea de la care s-a preluat conturul
		//depinzand de imaginea cu un factor mai mare se va umple tot obiectul dar si pot verifica cand se intampla asta dar la imagini mari nu prea duce laptopul meu
		for (int i = 1; i < src.rows - 1; i++) {
			for (int j = 1; j < src.cols - 1; j++) {
				if (dstContur.at<uchar>(i + 1, j) == 0 || dstContur.at<uchar>(i, j + 1) == 0 || dstContur.at<uchar>(i - 1, j) == 0 || dstContur.at<uchar>(i, j - 1) == 0)
				{
					if (dstComplement.at<uchar>(i, j) == 255)
					{
						dstFinal.at<uchar>(i, j) = 0;
					}
				}
			}
		}
		for (int i = 0; i < src.rows; i++) {
			for (int j = 0; j < src.cols; j++) {
				dstContur.at<uchar>(i, j) = dstFinal.at<uchar>(i, j);
			}
		}
		
	}





	imshow("Imagine Originala", src);
	imshow("Umplere", dstFinal);
	waitKey(0);
}

//Lab8

void operatiiImagine()
{
	char fname[MAX_PATH];
	Mat src;
	long int auxGamma = 256;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);

	long int auxMedie = 0;

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			auxMedie = auxMedie + src.at<uchar>(i, j);
		}
	}

	float medie = auxMedie / (src.rows * src.cols);

	printf("Valoarea medie a nivelelor de intensitate: %f \n", medie);

	long int auxDeviatie = 0;

	for (int i = 0; i < src.rows; i++) {
		for (int j = 0; j < src.cols; j++) {
			auxDeviatie = auxDeviatie + (src.at<uchar>(i, j) - medie) * (src.at<uchar>(i, j) - medie);
		}
	}

	float deviatie = sqrt(auxDeviatie / (src.rows * src.cols));

	printf("Valoarea medie a deviatiei de intensitate: %f \n", deviatie);


	int temporar = 0;

	int vectorHist[256];
	int vectorHistCumulativa[256];

	float vectorHistMaxime[256];


	for (int k = 0; k < 256; k++)
	{
		vectorHist[k] = 0;
	}

	for (int k = 0; k < 256; k++)
	{
		vectorHistCumulativa[k] = 0;
	}

	for (int i = 0; i < src.rows; i++)
	{
		for (int j = 0; j < src.cols; j++)
		{
			temporar = src.at<uchar>(i, j);
			vectorHist[temporar]++;
		}
	}


	showHistogram("Histograma", vectorHist, 255, 255);


	for (int i = 0; i < 256; i++)
	{
		temporar = 0;
		for (int j = 0; j <= i; j++)
		{
			temporar = temporar + vectorHist[j];
		}

		vectorHistCumulativa[i] = temporar;

		printf("Valoarea cumulativa la pozitia %d: %d \n", i, temporar );



	}

	showHistogram("Histograma Cumulativa", vectorHistCumulativa, 255, 255);

	Mat dstFinal = Mat(src.rows, src.cols, CV_8UC1);
	float y = 2.5f; //setezi cum vrei sa fie gamma 

	for (int i = 0; i < src.rows; i++)
	{
		for (int j = 0; j < src.cols; j++)
		{
			auxGamma = 255 *  pow(((float)(src.at<uchar>(i, j)) / 255), y);

			if (auxGamma > 255)
			{
				dstFinal.at<uchar>(i, j) = 255;
			}
			if (auxGamma < 0)
			{
				dstFinal.at<uchar>(i, j) = 0;
			}
			if (auxGamma < 255 && auxGamma > 0)
			{
				dstFinal.at<uchar>(i, j) = auxGamma;
			}

			//printf("Val gamma: %d \n", auxGamma);
		}
	}




	imshow("Imagine Originala", src);
	imshow("Corectat Gamma", dstFinal);
	waitKey(0);
}

//Lab9

void centering_transform(Mat img) {
	// imaginea trebuie să aibă elemente de tip float
	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			img.at<float>(i, j) = ((i + j) & 1) ? -img.at<float>(i, j) : img.at<float>(i, j);
		}
	}
}


Mat generic_frequency_domain_filter(Mat src) {
	//imaginea trebuie să aibă elemente de tip float
	Mat srcf;
	src.convertTo(srcf, CV_32FC1);

		//transformarea de centrare
		centering_transform(srcf);
	//aplicarea transformatei Fourier, se obține o imagine cu valori numere complexe
	Mat fourier;
	dft(srcf, fourier, DFT_COMPLEX_OUTPUT);
	//divizare în două canale: partea reală și partea imaginară
	Mat channels[] = { Mat::zeros(src.size(), CV_32F), Mat::zeros(src.size(), CV_32F) };
	split(fourier, channels); // channels[0] = Re(DFT(I)), channels[1] = Im(DFT(I))
	//calcularea magnitudinii și fazei în imaginile mag, respectiv phi, cu elemente de tip float
	Mat mag, phi;
	magnitude(channels[0], channels[1], mag);
	phase(channels[0], channels[1], phi);
	//aici afișați imaginile cu fazele și magnitudinile
	// ......
	//aici inserați operații de filtrare aplicate pe coeficienții Fourier
	// ......
	//memorați partea reală în channels[0] și partea imaginară în channels[1]
	// ......
	//aplicarea transformatei Fourier inversă și punerea rezultatului în dstf
	Mat dst, dstf;
	merge(channels, 2, fourier);
	dft(fourier, dstf, DFT_INVERSE | DFT_REAL_OUTPUT | DFT_SCALE);
	//transformarea de centrare inversă
	centering_transform(dstf);
	//normalizarea rezultatului în imaginea destinație
	normalize(dstf, dst, 0, 255, NORM_MINMAX, CV_8UC1);
	//Notă: normalizarea distorsionează rezultatul oferind o afișare îmbunătățită în intervalul
	//[0,255]. Dacă se dorește afișarea rezultatului cu exactitate (vezi Activitatea 3) se va
	//folosi în loc de normalizare conversia:
	//dstf.convertTo(dst, CV_8UC1);
	return dst;
}


void filtre()
{
	char fname[MAX_PATH];
	Mat src;
	//long int auxGamma = 256;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);



	long int auxMedie = 0;

	for (int i = 1; i < src.rows-1; i++) {
		for (int j = 1; j < src.cols-1; j++) {

			dst.at<uchar>(i, j) = auxMedie;

		}
	}

	for (int i = 1; i < src.rows-1; i++) {
		for (int j = 1; j < src.cols-1; j++) {


			auxMedie = 0;
			auxMedie = auxMedie + src.at<uchar>(i, j);
			auxMedie = auxMedie + src.at<uchar>(i, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i-1, j-1);
			auxMedie = auxMedie + src.at<uchar>(i-1, j);
			auxMedie = auxMedie + src.at<uchar>(i-1, j+1);

			auxMedie = auxMedie + src.at<uchar>(i + 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j );
			auxMedie = auxMedie + src.at<uchar>(i + 1, j + 1);

			auxMedie = auxMedie / 9;

			dst.at<uchar>(i, j) = auxMedie;


		}
	}

	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {


			auxMedie = 0;
			auxMedie = auxMedie + src.at<uchar>(i, j);
			auxMedie = auxMedie + src.at<uchar>(i, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i - 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i - 1, j);
			auxMedie = auxMedie + src.at<uchar>(i - 1, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i + 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j + 1);

			auxMedie = auxMedie / 9;

			dst.at<uchar>(i, j) = auxMedie;


		}
	}

	Mat dstFourier = Mat(dst.rows, dst.cols, CV_8UC1);
	//dstFourier = generic_frequency_domain_filter(dst);
	dstFourier = dst;

	Mat dstGauss = Mat(src.rows, src.cols, CV_8UC1);

	float A = 20;


	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {
			

			double auxiprime = ((dstFourier.cols / 2 - i) * (dstFourier.cols / 2 - i) + (dstFourier.rows / 2 - j) * (dstFourier.rows / 2 - j)) / (A*A);

			double auxi = (1- exp((-(auxiprime))));

			if (dstFourier.at<uchar>(i, j) * auxi > 255)
			{
				dstGauss.at<uchar>(i, j) = 255;
			}

			if (dstFourier.at<uchar>(i, j) * auxi < 0)
			{
				dstGauss.at<uchar>(i, j) = 0;
			}

			if (dstFourier.at<uchar>(i, j) * auxi >= 0 && dstFourier.at<uchar>(i, j) * auxi <= 255)
			{
				dstGauss.at<uchar>(i, j) = dstFourier.at<uchar>(i, j) * auxi;
			}


		}
	}





	imshow("Imagine Originala", src);
	imshow("Filtru Trece Jos", dst);
	imshow("Filtru Gauss Trece Sus", dstGauss);
	waitKey(0);
}

//Lab10


std::vector<Point2i> neighbours(Mat src, Point2i p) {
	int i = p.x;
	int j = p.y;

	std::vector<Point2i> neigh;

	for (int a = i - 1; a <= i + 1; a++) {
		for (int b = j - 1; b <= j + 1; b++) {
			if (a >= 0 && a < src.rows && b >= 0 && b < src.cols) {
				neigh.push_back(Point2i(a, b));
			}
		}
	}

	return neigh;
}


void detectiaMuchiilor()
{
	char fname[MAX_PATH];
	Mat src;
	//long int auxGamma = 256;

	if (openFileDlg(fname)) {
		src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	}

	Mat dst = Mat(src.rows, src.cols, CV_8UC1);

	Mat buffer1 = Mat(src.rows, src.cols, CV_8UC1);
	Mat buffer2 = Mat(src.rows, src.cols, CV_8UC1);
	Mat buffer3 = Mat(src.rows, src.cols, CV_8UC1);
	Mat buffer4 = Mat(src.rows, src.cols, CV_8UC1);



	long int auxMedie = 0;

	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {

			dst.at<uchar>(i, j) = auxMedie;

		}
	}

	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {


			auxMedie = 0;
			auxMedie = auxMedie + src.at<uchar>(i, j);
			auxMedie = auxMedie + src.at<uchar>(i, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i - 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i - 1, j);
			auxMedie = auxMedie + src.at<uchar>(i - 1, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i + 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j + 1);

			auxMedie = auxMedie / 9;

			dst.at<uchar>(i, j) = auxMedie;


		}
	}

	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {


			auxMedie = 0;
			auxMedie = auxMedie + src.at<uchar>(i, j);
			auxMedie = auxMedie + src.at<uchar>(i, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i - 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i - 1, j);
			auxMedie = auxMedie + src.at<uchar>(i - 1, j + 1);

			auxMedie = auxMedie + src.at<uchar>(i + 1, j - 1);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j);
			auxMedie = auxMedie + src.at<uchar>(i + 1, j + 1);

			auxMedie = auxMedie / 9;

			dst.at<uchar>(i, j) = auxMedie;


		}
	}

	Mat dstFourier = Mat(dst.rows, dst.cols, CV_8UC1);
	//dstFourier = generic_frequency_domain_filter(dst);
	dstFourier = dst;

	Mat dstGauss = Mat(src.rows, src.cols, CV_8UC1);

	float A = 20;


	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {


			double auxiprime = ((dstFourier.cols / 2 - i) * (dstFourier.cols / 2 - i) + (dstFourier.rows / 2 - j) * (dstFourier.rows / 2 - j)) / (A*A);

			double auxi = (1 - exp((-(auxiprime))));

			if (dstFourier.at<uchar>(i, j) * auxi > 255)
			{
				dstGauss.at<uchar>(i, j) = 255;
			}

			if (dstFourier.at<uchar>(i, j) * auxi < 0)
			{
				dstGauss.at<uchar>(i, j) = 0;
			}

			if (dstFourier.at<uchar>(i, j) * auxi >= 0 && dstFourier.at<uchar>(i, j) * auxi <= 255)
			{
				dstGauss.at<uchar>(i, j) = dstFourier.at<uchar>(i, j) * auxi;
			}


		}
	}

	long auxKernel[9];
	int sobel[9] = {-1,0,1,-2,0,2,-1,0,1};

	long auxKernelY[9];
	int sobelY[9] = { 1,2,1,0,0,0,-1,-2,-1 };

	long sumAux, sumAuxY;

	int histGrad[256] = { 0 };


	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {

			//auxMedie = auxMedie + src.at<uchar>(i, j);
			//gasit aici https://en.wikipedia.org/wiki/Kernel_(image_processing)#Convolution

			auxKernel[0]= dstGauss.at<uchar>(i+1, j+1) * sobel[0];
			auxKernel[1] = dstGauss.at<uchar>(i + 1, j) * sobel[1];
			auxKernel[2] = dstGauss.at<uchar>(i + 1, j-1) * sobel[2];

			auxKernel[3] = dstGauss.at<uchar>(i , j + 1) * sobel[3];
			auxKernel[4] = dstGauss.at<uchar>(i , j) * sobel[4];
			auxKernel[5] = dstGauss.at<uchar>(i , j - 1) * sobel[5];

			auxKernel[6] = dstGauss.at<uchar>(i -1, j + 1) * sobel[6];
			auxKernel[7] = dstGauss.at<uchar>(i -1, j) * sobel[7];
			auxKernel[8] = dstGauss.at<uchar>(i -1, j - 1) * sobel[8];

			sumAux = 0;



			auxKernelY[0] = dstGauss.at<uchar>(i + 1, j + 1) * sobelY[0];
			auxKernelY[1] = dstGauss.at<uchar>(i + 1, j) * sobelY[1];
			auxKernelY[2] = dstGauss.at<uchar>(i + 1, j - 1) * sobelY[2];

			auxKernelY[3] = dstGauss.at<uchar>(i, j + 1) * sobelY[3];
			auxKernelY[4] = dstGauss.at<uchar>(i, j) * sobelY[4];
			auxKernelY[5] = dstGauss.at<uchar>(i, j - 1) * sobelY[5];

			auxKernelY[6] = dstGauss.at<uchar>(i - 1, j + 1) * sobelY[6];
			auxKernelY[7] = dstGauss.at<uchar>(i - 1, j) * sobelY[7];
			auxKernelY[8] = dstGauss.at<uchar>(i - 1, j - 1) * sobelY[8];

			sumAuxY = 0;

			for (int k = 0; k < 9; k++)
			{
				sumAux = sumAux + auxKernel[k];
				sumAuxY = sumAuxY + auxKernelY[k];
			}

			//printf("Valori: %ld %ld\n", sumAux, sumAuxY);

			buffer1.at<uchar>(i, j) = sqrt(sumAux*sumAux + sumAuxY * sumAuxY);

			if (sqrt(sumAux*sumAux + sumAuxY * sumAuxY) > 255)
			{
				int auxGrad = sqrt(sumAux*sumAux + sumAuxY * sumAuxY) / (4*sqrt(2));
				histGrad[auxGrad]++;
			}
			else
			{
				int auxGrad = sqrt(sumAux*sumAux + sumAuxY * sumAuxY);
				histGrad[auxGrad]++;
			}


			if (sumAux == 0)
			{
				buffer2.at<uchar>(i, j) = PI/2;
				//printf("Valori: %d\n", buffer2.at<uchar>(i, j));
			}
			else {
				buffer2.at<uchar>(i, j) = atan(sumAuxY / sumAux);


				//printf("Valori: %d\n", buffer2.at<uchar>(i, j));
			}



		}
	}

	imshow("Imagine Originala", src);
	//imshow("Filtru Trece Jos", dst);
	imshow("Filtru Gauss Trece Sus", dstGauss);
	imshow("Magnitude", buffer1);
	imshow("Direction", buffer2);

	for (int i = 1; i < src.rows - 1; i++) {
		for (int j = 1; j < src.cols - 1; j++) {

			if (buffer2.at<uchar>(i, j) == 1 && buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i-1, j-1) && buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i + 1, j + 1))
			{
				buffer3.at<uchar>(i, j) = 255;
			}
			else
			{
				buffer3.at<uchar>(i, j) = 0;
			}

			switch (buffer2.at<uchar>(i, j))
			{
				case 1:
					if (buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i + 1, j - 1) && buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i - 1, j + 1))
					{
						buffer3.at<uchar>(i, j) = buffer1.at<uchar>(i, j);
						//sau cu 255 dar va arata ceva mai urat pentru ca pune griuri din fundal
					}
					else
					{
						buffer3.at<uchar>(i, j) = 0;
					}
					break;
				case 0:
					if (buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i, j - 1) && buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i, j + 1))
					{
						buffer3.at<uchar>(i, j) = buffer1.at<uchar>(i, j);
					}
					else
					{
						buffer3.at<uchar>(i, j) = 0;
					}
					break;
				case 2:
					if (buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i - 1, j) && buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i + 1, j))
					{
						buffer3.at<uchar>(i, j) = buffer1.at<uchar>(i, j);
					}
					else
					{
						buffer3.at<uchar>(i, j) = 0;
					}
					break;
				case 3:
					if (buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i - 1, j - 1) && buffer1.at<uchar>(i, j) > buffer1.at<uchar>(i + 1, j + 1))
					{
						buffer3.at<uchar>(i, j) = buffer1.at<uchar>(i, j);
					}
					else
					{
						buffer3.at<uchar>(i, j) = 0;
					}
					break;
				default:
					buffer3.at<uchar>(i, j) = 0;


			}

		}

	}

	imshow("After supression", buffer3);

	float p = 0.1;

	int noNonEdge = (1 - p)*(buffer3.rows * buffer3.cols - histGrad[0]);

	long sumIndex = 0;
	int adaptiveThreshold = 1;

	for (int i = 0; i < 256; i++)
	{
		sumIndex = sumIndex + histGrad[i];
		if (sumIndex > noNonEdge)
		{
			adaptiveThreshold = i;
			break;
		}
	}

	int threshold_high = adaptiveThreshold;
	int threshold_low = 0.4 * adaptiveThreshold;

	for (int i = 0; i < buffer3.rows; i++)
		for (int j = 0; j < buffer3.cols; j++)
			if (buffer3.at<uchar>(i, j) < threshold_low)
				buffer4.at<uchar>(i, j) = 0;
			else if (buffer3.at<uchar>(i, j) < threshold_high)
				buffer4.at<uchar>(i, j) = 1;
			else
				buffer4.at<uchar>(i, j) = 2;

	std::queue<Point2i> Q;

	for (int i = 0; i < buffer4.rows; i++)
		for (int j = 0; j < buffer4.cols; j++)
			if (buffer4.at<uchar>(i, j) == 2)
			{
				Q.push(Point2i(i, j));

				while (!Q.empty())
				{
					Point2i curr = Q.front();
					Q.pop();

					for (auto n : neighbours(buffer4, curr))
						if (buffer4.at<uchar>(n.x, n.y) == 1)
						{
							buffer4.at<uchar>(n.x, n.y) = 2;
							Q.push(n);
						}
				}
			}

	for (int i = 0; i < buffer4.rows; i++)
	{
		for (int j = 0; j < buffer4.cols; j++)
		{
			if (buffer4.at<uchar>(i, j) == 2)
			{
				buffer4.at<uchar>(i, j) = 255;
			}
			else
			{
				buffer4.at<uchar>(i, j) = 0;
			}
		}

	}

	imshow("Final margini", buffer4);


	waitKey(0);
}




int main()
{
	int op;
	do
	{
		system("cls");
		destroyAllWindows();
		printf("Menu:\n");
		printf(" 1 - Open image\n");
		printf(" 2 - Open BMP images from folder\n");
		printf(" 3 - Image negative - diblook style\n");
		printf(" 4 - BGR->HSV\n");
		printf(" 5 - Resize image\n");
		printf(" 6 - Canny edge detection\n");
		printf(" 7 - Edges in a video sequence\n");
		printf(" 8 - Snap frame from live video\n");
		printf(" 9 - Mouse callback demo\n");
		printf(" 10 - 2 culori\n");
		printf(" 11 - 2 culori cu izolare\n");
		printf(" 12 - Histograma\n");
		printf(" 13 - Arie - Perimetru si altele\n");
		printf(" 14 - Etichetare obiecte\n");
		printf(" 15 - Contur\n");
		printf(" 16 - Dilatare\n");
		printf(" 17 - Eroziune\n");
		printf(" 18 - Umplere\n");
		printf(" 19 - Operatii Imagine si Corectare Gamma\n");
		printf(" 20 - Filtre\n");
		printf(" 21 - Detectia muchiilor\n");
		printf(" 0 - Exit\n\n");
		printf("Option: ");
		scanf("%d", &op);

		switch (op)
		{
			case 1:
				testOpenImage();
				break;
			case 2:
				testOpenImagesFld();
				break;
			case 3:
				testParcurgereSimplaDiblookStyle(); //diblook style
				break;
			case 4:
				//testColor2Gray();
				testBGR2HSV();
				break;
			case 5:
				testResize();
				break;
			case 6:
				testCanny();
				break;
			case 7:
				testVideoSequence();
				break;
			case 8:
				testSnap();
				break;
			case 9:
				testMouseClick();
				break;
			case 10:
				test2Colors();
				break;
			case 11:
				colorareCuIzolare();
				break;
			case 12:
				histogramaPeImagine();
				break;
			case 13:
				detaliiImagine();
				break;
			case 14:
				etichetare();
				break;
			case 15:
				contur();
				break;
			case 16:
				dilatare();
				break;
			case 17:
				eroziune();
				break;
			case 18:
				umplere();
				break;
			case 19:
				operatiiImagine();
				break;
			case 20:
				filtre();
				break;
			case 21:
				detectiaMuchiilor();
				break;
		}
	}
	while (op!=0);
	return 0;
}