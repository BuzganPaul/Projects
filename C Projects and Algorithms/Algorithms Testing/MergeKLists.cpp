#include<stdio.h> 
#include<stdlib.h> 
using namespace std;

#include "Profiler.h"
Profiler profiler("worst Select");

int m=0;
int vk[1000];
int vkk = 0;


 struct NodeT
{
	int key;
	struct NodeT *next;
 };


 void swap(int* a, int* b)
 {
	 int aux = *a;
	 *a = *b;
	 *b = aux;
 }
 void afiseaza(struct NodeT * dum)
 {
	 while (dum!= NULL)
	 {
		 int aux = dum->key;
		 printf("%d ", aux);
		 dum = dum->next;
		 
	 }
	 printf("\n");
 }

 
 void heapifyMax(int arr[], int n, int i)
 {
	 int max = i;
	 int l = 2 * i + 1;
	 int r = 2 * i + 2;

	 
	 if (l < n && arr[l] > arr[max])
		 max = l;

	 
	 if (r < n && arr[r] > arr[max])
		 max = r;

	 if (max != i)
	 {
		 swap(arr[i], arr[max]);
		 

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
	 Build(arr, n);

	 for (int i = n - 1; i >= 0; i--)
	 {
		 swap(arr[0], arr[i]);
		 
		 heapifyMax(arr, i, 0);
	 }
 }
 
 

 void mergeSortHeap(struct NodeT ** liste, int k, struct NodeT * fin)
 {
	 int i, j;
	 int v[100], v2[100];
	 int verificare = 0;
	 
	 for ( i = 0; i < k; i++)
	 {
		 liste[i] = liste[i]->next;
		 v[i] = liste[i]->key;
		 v2[i] = liste[i]->key;
		 

	 }
	 heapSort(v, k);
	 
	
	 do
	 {
		
		 verificare = 0;
		 for ( j = 0; j < k; j++)
		 {
			 if(liste[j]==NULL)
			 {
				 verificare++;
				 printf("%d ", verificare);

			 }
		 }
		/*
		 struct NodeT * temp = (struct NodeT*)malloc(sizeof(struct NodeT));
		 temp->key = v[0];
		 temp->next = NULL;
		 
		 if (fin == NULL)
		 {
			 fin = temp;
			 
		 }
		 else
		 {

			 fin->next = temp;
			 fin = temp;
		 }
		 */
		 vk[vkk] = v[0];
		 vkk++;
		 
		 
		 

		 for ( i = 0; i < k; i++)
		 {
			 printf("%d ", v[i]);
			 

		 }
		 printf("\n");

		 
		 for (i = 0; i < k; i++)
		 {
			 if (v[0] == v2[i])
			 {
				 
				 if (liste[i]->next != NULL ) {
					 
					 liste[i] = liste[i]->next;
					 v[0] = liste[i]->key;
					 v2[i] = liste[i]->key;
					 
					 
				 }
				 else
				 {
					 liste[i] = NULL;
					 v[0] = 100000;
					 v2[i] = 100000;
				 }
				
			 }

		 }
		
		 
		 heapSort(v, k);


		 printf("\n");
		 for (int i = 0; i < vkk; i++)
		 {
			 printf("%d ", vk[i]);


		 }
		 printf("\n");

	 } while (verificare < k - 1 );

	 printf("spatiu");
 }


int main()
{
	int n = 30, k = 28;
	int v[10000] = { 0 };
	struct NodeT * fin=NULL;
	struct NodeT * finH = fin;
	struct NodeT** a = (struct NodeT**)malloc(k * sizeof(struct NodeT*));
	struct NodeT *head;
	struct NodeT *head2;

	for (int i = 0; i < k - 1; i++)
	{
		int j = rand() % (n / k);
		n = n - j;
		a[i] = (struct NodeT*)malloc( sizeof(struct NodeT));
		
		head = a[i];
		head2 = a[i];

		j ++;
		

		FillRandomArray(v, j, 1, n, true, 1);

	
		for (int z = 0; z < j; z++)
		{
			struct NodeT * temp = (struct NodeT*)malloc(sizeof(struct NodeT));
			temp->key = v[z];
			temp->next = NULL;
			head->next = temp;
			head = temp;
		}
		
		afiseaza(head2->next);
		printf("\n ");
	}
	
	
	a[k-1] = (struct NodeT*)malloc(sizeof(struct NodeT));


	n=n-k+1;
	m = n;

	FillRandomArray(v, n, 1, n, true, 1);
	head = a[k - 1];
	head2 = a[k - 1];

	for (int z = 0; z < n; z++)
	{
		struct NodeT * temp = (struct NodeT*)malloc(sizeof(struct NodeT));
		
		
		temp->key = v[z];
		temp->next = NULL;
		head->next = temp;
		head = temp;
	}
	
	afiseaza(head2->next);
	printf("%d ", m);
	printf("\n ");

	mergeSortHeap(a, k, fin);
	printf("\n ");
	
	for (int i = 0; i < vkk; i++)
	{
		printf("%d ", vk[i]);


	}
	printf("\n");
	
	//afiseaza(finH);

	return 0;
}