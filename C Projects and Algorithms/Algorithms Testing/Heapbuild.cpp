/** Autor: Buzgan Paul
	Grupa: 6 CTI RO An 2
	TEMA 2

			   Best Case		Average Case		Worst Case
Top-Down				C si variaza super mult amandoua               C=6n  A=3n
Bottom-Up			   C=5n aproape  A=2n aproape        C=aproape A dar putin mai ciudat
-in worst case pt Bottom-Up C este egal cu A la inceput dupa cresc si A devine mai mare dupa crae scad la loc si incep sa se egaleze
-in worst case pt Top-Down la ambele C si A cresc liniar insa C creste de doua ori mai repede decat A
- in avargae case la Top Down C si A variaza super mult si uneori chair se schimba care e mai mare decat cealalta insa Atinde in majoritatea cazurilor sa fie mai mare
- in avarage la Bottom Up crste constant si A si C, insa c creste ceva mai repede decat A, aproape dublu
*/


#include <iostream>

#include "Profiler.h"
Profiler profiler("worst Select");

//#include <stdio.h>
#include <stdlib.h>

using namespace std;

int m;

void swap(int* a, int* b)
{
	int* aux = a;
	a = b;
	b = aux;
}

void heapifyMax(int arr[], int n, int i)
{
	int max = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	profiler.countOperation("CompBottom_Up", n, 2);
	if (l < n && arr[l] > arr[max])
		max = l;

	profiler.countOperation("CompBottom_Up", n, 2);
	if (r < n && arr[r] > arr[max])
		max = r;

	if (max != i)
	{
		swap(arr[i], arr[max]);
		profiler.countOperation("AsignBottom_Up", n, 3);

		heapifyMax(arr, n, max);
	}
}

void Build(int arr[], int n)
{
	for (int i = n / 2 - 1; i >= 0; i--)
		heapifyMax(arr, n, i);

}


void heapSort(int arr[], int n)
{


	for (int i = n - 1; i >= 0; i--)
	{
		swap(arr[0], arr[i]);

		heapifyMax(arr, i, 0);
	}
}

void heapIncreaseKey(int arr[], int i, int key)
{
	arr[i] = key;
	profiler.countOperation("AsignTop_Down", m, 1);
	while (i > 1 && arr[(i - 1) / 2] < arr[i])
	{
		profiler.countOperation("CompTop_Down", m, 2);
		profiler.countOperation("AsignTop_Down", m, 3);
		swap(arr[(i - 1) / 2], arr[i]);
		i = (i - 1) / 2;
	}
	profiler.countOperation("CompTop_Down", m, 2);
}


void Build2insert(int arr[], int key)
{
	m = m + 1;
	heapIncreaseKey(arr, m, key);

}
void Build2(int arr[], int n)
{
	m = 1;
	for (int i = 2; i < n; i++)
	{
		Build2insert(arr, arr[i]);
	}


}


void afisare(int arr[], int n)
{
	for (int i = 0; i < n; ++i)
		cout << arr[i] << " ";
	cout << "\n";
}

int main()
{

	int arr[] = { 10, 7, 8, 9, 5, 1, 0, 4, 98, 42 };
	int n = sizeof(arr) / sizeof(arr[0]);

	cout << "Sorted array is \n";
	afisare(arr, n);

	//Build2(arr, n);
	cout << "Sorted array is \n";
	afisare(arr, n);

	Build(arr, n);
	cout << "Sorted array is \n";
	afisare(arr, n);

	heapSort(arr, n);
	cout << "\nSorted array is \n";
	afisare(arr, n);

/*
	int v[10000] = { 0 };
	for (int j = 1; j <= 1; j++) {
		for (int i = 100; i < 10000; i = i + 500)
		{
			FillRandomArray(v, i, 1, 10000, true, 0);
			//Build2(v, i);
			Build(v, i);
			afisare(v, i);
		}
	}

	profiler.createGroup("Assigns: ", "AsignBottom_Up");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations: ", "CompBottom_Up");
	profiler.createGroup("A+C Bottom Up", "CompBottom_Up", "AsignBottom_Up");
	profiler.showReport();


	/*profiler.createGroup("Assigns: ", "AsignTop_Down");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations: ", "CompTop_Down");
	profiler.createGroup("A+C Top Down", "CompTop_Down", "AsignTop_Down");
	profiler.showReport();*/

}
