/*
Autor: Buzgan Paul
	Grupa : 6 CTI RO An 2
	TEMA 9 - Parcurgerea in latime

	-Algoritmul DFS Complexistate O(V +E)
	-foarte util algoritmul in practica pentru a gasi caile in diverse chestiuni rep prin grafuri gen harti sau labirint;
	*/


#include <stdio.h>
#include <stdlib.h>
using namespace std;

#include "Profiler.h"
Profiler profiler("worst Select");

int m = 0;

struct Node {
	int key;
	int p;
	int vecini[4500];
	int count;
	int color;
	int d;
};

struct Node2 {
	int key;
	Node2 * next;
};

Node2 * newNode2(int key)
{
	Node2 * aux = (Node2*)malloc(sizeof(Node2));
	aux->key = key;
	aux -> next = NULL;
	return aux;
}

Node * newNode(int key)
{
	Node * aux = (Node*)malloc(sizeof(Node));
	aux->key=key;
	aux->p = -1;
	aux->count = 0;
	aux->color = 0;
	aux->d = 0;
	return aux;
}


void addEdge(int A, int B, Node * graph[])
{
	graph[A]->vecini[graph[A]->count] = B;
	graph[A]->count++;

	graph[B]->vecini[graph[B]->count] = A;
	graph[B]->count++;

}

void printGraph(Node * a[], int size)
{
	for (int j = 0; j < size; j++)
	{
		printf("Punctul %d are muchii cu: ", a[j]->key);
		for (int i = 0; i < a[j]->count; i++)
		{
			
			printf(" %d", a[j]->vecini[i]);

		}
		printf(" \n");
	}
	printf("\n ");
}

void printGraph2(Node * a[], int size)
{
	for (int j = 0; j < size; j++)
	{
		printf("Punctul %d are parintele:  %d", a[j]->key, a[j]->p);
		printf(" \n");
	}
	printf("\n ");
}



void printLista(Node2 * a)
{
	Node2 *temp;
	temp = a;
	while (temp != NULL)
	{
		printf(" %d ", temp->key);
		temp = temp->next;
	}
	printf("\n");
}

void enqueue(Node2 * lista, int key)
{
	Node2 * aux;
	aux = lista;
	if (lista!= NULL)
	{
		
		while (lista->next != NULL)
		{
			lista = lista->next;
		}
		lista->next = newNode2(key);;
		lista->next->next = NULL;
	}
	else
	{
		lista = newNode2(key);
	}

}

int dequeue(Node2 ** Q)
{
	Node2 * temp;
	int auxiliar;

	if ((*Q) != NULL)
	{
		temp = (*Q);
		(*Q) = (*Q)->next;
		auxiliar = temp->key;
		free(temp);
		return auxiliar;
	}
	else
	{
		return -1;
	}

}



void BFS(Node * G[], Node * s)
{
	s->color = 1;
	s->d = 0;
	s->p = NULL;
	Node2 * Q;
	Q = newNode2(s->key);

	profiler.countOperation("AsignAlgoritm", m, 4);
	
	while (Q != NULL)
	{
		profiler.countOperation("CompAlgoritm", m, 1);
		int u = dequeue(&Q);
		profiler.countOperation("AsignAlgoritm", m, 1);
		for(int k=0; k<G[u]->count; k++)
		{
			profiler.countOperation("CompAlgoritm", m, 1);
			int au = G[u]->vecini[k];

			profiler.countOperation("AsignAlgoritm", m, 1);

			profiler.countOperation("CompAlgoritm", m, 1);

			if (G[au]->color == 0)
			{
				G[au]->color = 1;
				G[au]->d = G[u]->d + 1;
				G[au]->p= u;
				profiler.countOperation("AsignAlgoritm", m, 3);
				profiler.countOperation("CompAlgoritm", m, 1);

				if (Q == NULL)
				{

					Q = newNode2(G[au]->key);
					profiler.countOperation("AsignAlgoritm", m, 1);
				}
				else
				{
					enqueue(Q, G[au]->key);
					profiler.countOperation("AsignAlgoritm", m, 1);

				}
			}
			
		}
		profiler.countOperation("CompAlgoritm", m, 1);
		printf("BFS ruta: %d \n", G[u]->key);
		G[u]->color = 2;
		profiler.countOperation("AsignAlgoritm", m, 1);
	}
	profiler.countOperation("CompAlgoritm", m, 1);
}




int main()
{
	/*
	int N=9;
	Node * g[9];

	for (int i=0; i < N; i++)
	{
		g[i] = newNode(i);
	}

	
	
	addEdge(0, 1, g);
	addEdge(1, 2, g);

	addEdge(3, 4, g);
	addEdge(6, 5, g);
	addEdge(6, 1, g);
	addEdge(7, 0, g);
	addEdge(2, 8, g);
	addEdge(4, 8, g);
	addEdge(6, 8, g);
	addEdge(3, 1, g);

	printGraph(g, N);
	

	BFS(g, g[2]);

	printGraph2(g, N);
	*/


	/*
	int V=100, E;



	for (int k = 1000; k < 4500; k = k + 100)
	{
		Node * g[100];

		for (int i = 0; i < V; i++)
		{
			g[i] = newNode(i);
		}

		m = k;

		for (int f = 0; f < k; f++)
		{
			int A1, B1;
			A1 = rand() % 100;
			B1 = rand() % 100;
			addEdge(A1, B1, g);
		}
		printGraph(g, V);
		BFS(g, g[2]);

	}

	profiler.createGroup("1) Assigns: ", "AsignAlgoritm");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("1) Comparations: ", "CompAlgoritm");
	profiler.createGroup("1) A+C Algoritm BFS", "CompAlgoritm", "AsignAlgoritm");
	profiler.showReport();
	*/

	

	
	int V = 100, E=4500;



	for (int k = 100; k < 200; k = k + 10)
	{
		Node * g[200];

		for (int i = 0; i < k; i++)
		{
			g[i] = newNode(i);
		}

		m = k;

		for (int f = 0; f < E; f++)
		{
			int A1, B1;
			A1 = rand() % k;
			B1 = rand() % k;
			addEdge(A1, B1, g);
		}
		printGraph(g, k);
		BFS(g, g[2]);

	}

	profiler.createGroup("2) Assigns: ", "AsignAlgoritm");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("2) Comparations: ", "CompAlgoritm");
	profiler.createGroup("2) A+C Algoritm BFS", "CompAlgoritm", "AsignAlgoritm");
	profiler.showReport();
	

	return 0;
}