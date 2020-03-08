/** Autor: Buzgan Paul
	Grupa: 6 CTI RO An 2
	TEMA 3 - Heap Sort si Quick Sort/QuckSelect(Random)

			   Best Case		Average Case		Worst Case
HeapSort		nlogn	          nlogn					nlogn
QuickSort		nlogn		      nlogn                  n^2
-in worst case pt QuickSort merge foarte prost, mai prost decat HeapSort;
-in avarage case QuickSort se descurca mai bine si este mai folositor;
-pentru QuickSort nu trebuie gandta o alta structura;
-depinzand cum folosesti pivotul QuickSortul isi schimba eficienta, putand folosi un pivat random pentru a se evita si a mari eficienta daca se intalneste un worst case;
*/



#include<stdio.h> 
#include "Profiler.h"
Profiler profiler("worst Select");

//#include <stdio.h>
#include <stdlib.h>

using namespace std;
int m;

void swap(int* a, int* b)
{
	int aux = *a;
	*a = *b;
	*b = aux;
}




int partition(int arr[], int low, int high)
{
	/*
	int d = (low+high)/2;
	swap(&arr[high], &arr[d]);
	*/

	int pivot = arr[high];    
	int i = (low - 1); 

	profiler.countOperation("AsignQuickSort", m, 2);

	profiler.countOperation("CompQuickSort", m);
	for (int j = low; j <= high - 1; j++)
	{
		profiler.countOperation("CompQuickSort", m);
		if (arr[j] <= pivot)
		{
			i++;    
			profiler.countOperation("AsignQuickSort", m, 3);
			swap(&arr[i], &arr[j]);
		}
	}
	profiler.countOperation("AsignQuickSort", m, 3);
	swap(&arr[i + 1], &arr[high]);
	return (i + 1);
}

int QuickSelect(int arr[], int l, int r, int k)
{
	if (k > 0 && k <= r - l + 1) {

		int index = partition(arr, l, r);

		if (index - l == k - 1)
			return arr[index];

		if (index - l > k - 1)
			return QuickSelect(arr, l, index - 1, k);
		return QuickSelect(arr, index + 1, r,
			k - index + l - 1);
	}

}


void quickSort(int arr[], int low, int high)
{
	profiler.countOperation("CompQuickSort", m);
	if (low < high)
	{
		int pi = partition(arr, low, high);

		quickSort(arr, low, pi - 1);
		quickSort(arr, pi + 1, high);
	}
}


void heapifyMax(int arr[], int n, int i)
{
	int max = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	profiler.countOperation("CompHeap_Sort", n, 2);
	if (l < n && arr[l] > arr[max])
		max = l;

	profiler.countOperation("CompHeap_Sort", n, 2);
	if (r < n && arr[r] > arr[max])
		max = r;

	if (max != i)
	{
		swap(arr[i], arr[max]);
		profiler.countOperation("AsignHeap_Sort", n, 3);

		heapifyMax(arr, n, max);
	}
}



void heapSort(int arr[], int n)
{


	for (int i = n-1 ; i >= 0; i--)
	{
		swap(arr[0], arr[i]);
		//profiler.countOperation("AsignHeap_Sort", n, 3);
		heapifyMax(arr, i, 0);
	}
}
void Build(int arr[], int n)
{
	for (int i = n / 2 - 1; i >= 0; i--)
		heapifyMax(arr, n, i);

}


void afiseaza(int arr[], int size)
{
	int i;
	for (i = 0; i < size; i++)
		printf("%d ", arr[i]);
	printf("\n");
}


int main()
{
	
	int arr[] = { 10, 26, 8, 9, 5, 15, 0, 4, 98, 42 };
	int n = sizeof(arr) / sizeof(arr[0]);



	//quickSort(arr, 0, n - 1);
	printf("Sortat: \n");
	afiseaza(arr, n);

	Build(arr, n);
	heapSort(arr, n);
	printf("Sortat: \n");
	afiseaza(arr, n);

	printf("Numarul cel mai mic: \n");
	//printf("%d ", QuickSelect(arr, 0, n - 1, 1));
	
	
	
	/*
	int v[10000] = { 0 };
	for (int j = 1; j <= 1; j++) {
		for (int i = 3000; i < 10000; i = i + 500)
		{
			FillRandomArray(v, i, 1, i, true, 0);
			//heapSort(v, i-1);
			m = i;
			quickSort(v, 0, i-1);
			afiseaza(v, i);
		}
	}
	
	profiler.divideValues("AsignQuickSort", 5);
	profiler.divideValues("ComparatiiQuickSort", 5);
	profiler.createGroup("Assigns: ", "AsignQuickSort");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations: ", "CompQuickSort");
	profiler.createGroup("A+C Quick Sort", "CompQuickSort", "AsignQuickSort");
	profiler.showReport();
	*/

	/*
	profiler.divideValues("AsignHeap_Sort", 5);
	profiler.divideValues("ComparatiiHeap_Sort", 5);

	profiler.createGroup("Assigns: ", "AsignHeap_Sort");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations: ", "CompHeap_Sort");
	profiler.createGroup("A+C Heap Sort", "CompHeap_Sort", "AsignHeap_Sort");


	profiler.showReport();
	*/

	return 0;
}
