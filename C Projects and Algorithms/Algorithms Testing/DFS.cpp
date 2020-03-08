/*
Autor: Buzgan Paul
	Grupa : 6 CTI RO An 2
	TEMA 10 - Parcurgerea in adancime

	-Algoritmul DFS Complexistate O(V +E)
	-foarte util algoritmul in practica pentru a gasi caile in diverse chestiuni rep prin grafuri gen harti sau labirint;
	*/


#include <stdio.h>
#include <stdlib.h>
using namespace std;

#include "Profiler.h"
Profiler profiler("worst Select");

int m = 0;
int topologic[100];
int co = 0;



struct Node {
	int key;
	int p;
	int vecini[4500];
	int count;
	int color;
	int d;
	int f;
};

struct Node2 {
	int key;
	Node2 * next;
};

Node2 * newNode2(int key)
{
	Node2 * aux = (Node2*)malloc(sizeof(Node2));
	aux->key = key;
	aux->next = NULL;
	return aux;
}

Node2 * tarjanlista;

Node * newNode(int key)
{
	Node * aux = (Node*)malloc(sizeof(Node));
	aux->key = key;
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


void push2(Node2 * lista, int key)
{
	Node2 * aux;
	aux = lista;
	if (lista != NULL)
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

int pop2(Node2 ** Q)
{
	Node2 * temp;
	int auxiliar;
	if ((*Q)->next->next != NULL)
	{
		while ((*Q)->next->next != NULL)
		{
			(*Q) = (*Q)->next;
		}
	}
	if ((*Q) != NULL && (*Q)->next != NULL)
	{
		temp = (*Q)->next;
		auxiliar = temp->key;
		free(temp);
		return auxiliar;
	}
	else
	{
		if ((*Q)->next != NULL)
		{
			auxiliar = (*Q)->key;
			free((*Q));
			return auxiliar;
		}
		else {
			return -1;
		}
	}

}

void tarjan(int rootprim)
{

	Node2 * aux2;
	aux2 = tarjanlista;
	if (tarjanlista != NULL) {
		while (tarjanlista->next != NULL)
		{
			if (aux2->key == rootprim)
			{
				printf("\n Componenta puternic conexa:  ");
				while (tarjanlista->next != NULL)
				{
					printf("%d  ", tarjanlista->key);
					tarjanlista = tarjanlista->next;
				}
				printf("\n");
			}
			tarjanlista = tarjanlista->next;

		}
	}

}


void DFS_visit(Node * G1[], Node * root1, int size1, int timp1)
{
	timp1 = timp1 + 1;
	root1->d = timp1;
	root1->color = 1;
	profiler.countOperation("AsignAlgoritm", m, 3);

	for (int j = 0; j < root1->count-1; j++)
	{
		profiler.countOperation("CompAlgoritm", m, 1);
		int auxiliar = root1->vecini[j];

		profiler.countOperation("CompAlgoritm", m, 1);
		if (G1[auxiliar]->color == 0)
		{
			profiler.countOperation("AsignAlgoritm", m, 1);
			//push2(tarjanlista, G1[auxiliar]->key);
			G1[auxiliar]->p = root1->key;
			DFS_visit(G1, G1[auxiliar], size1, timp1);
			//pop2(&tarjanlista);
		}
		else
		{
			//tarjan(G1[auxiliar]->key);
		}
		
	}
	profiler.countOperation("CompAlgoritm", m, 1);
	profiler.countOperation("AsignAlgoritm", m, 3);
	root1->color = 2;
	printf("RUTA:  %d\n", root1->key);
	timp1 = timp1 + 1;
	root1->f = timp1;
}


void DFS(Node * G[], int size)
{

	profiler.countOperation("AsignAlgoritm", m, 1);
	int timp;
	timp = 0;
	for (int i = 0; i < size; i++)
	{
		profiler.countOperation("CompAlgoritm", m, 1);
		profiler.countOperation("CompAlgoritm", m, 1);
		if (G[i]->color == 0)
		{
			DFS_visit(G, G[i], size, timp);
		}
	}
	profiler.countOperation("CompAlgoritm", m, 1);
}



void topologic_sort(Node * G[], int size)
{
	for (int s = 1; s < size; s++)
	{
		for (int w = 0; w < size; w++)
		{
			if (G[w]->f == s)
			{
				topologic[co]=G[w]->key;
				co++;
			}
		}
	}
}




int main()
{
	
	int N=9;
	Node * g[9];

	for (int i=0; i < N; i++)
	{
		g[i] = newNode(i);
	}



	addEdge(0, 1, g);
	addEdge(1, 2, g);

	addEdge(2, 3, g);
	addEdge(3, 5, g);
	addEdge(3, 0, g);
	addEdge(5, 6, g);
	addEdge(6, 7, g);
	addEdge(7, 5, g);
	addEdge(8, 6, g);
	addEdge(3, 1, g);
	addEdge(1, 3, g);
	addEdge(2, 4, g);

	printGraph(g, N);

	DFS(g, N);

	printGraph2(g, N);
	
	topologic_sort(g, N);

	printf("\nSortat topologic: ");

	for (int f = co; f >0; f--)
	{
		printf(" %d", topologic[f]);
	}

	printf("\n ");
	
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
		printGraph(g, 100);
		DFS(g, V);

	}

	profiler.createGroup("1) Assigns: ", "AsignAlgoritm");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("1) Comparations: ", "CompAlgoritm");
	profiler.createGroup("1) A+C Algoritm DFS", "CompAlgoritm", "AsignAlgoritm");
	profiler.showReport();
	
	*/

	

	int V = 100, E = 4500;



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
		DFS(g, k);

	}

	profiler.createGroup("2) Assigns: ", "AsignAlgoritm");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("2) Comparations: ", "CompAlgoritm");
	profiler.createGroup("2) A+C Algoritm DFS", "CompAlgoritm", "AsignAlgoritm");
	profiler.showReport();
	

	return 0;
	
}