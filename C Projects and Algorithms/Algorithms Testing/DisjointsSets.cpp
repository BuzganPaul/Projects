/*
Autor: Buzgan Paul
	Grupa : 6 CTI RO An 2
	TEMA 8 - Operatiii pe multimi disjuncte

	-Algoritmul lui Kruskal Complexistate O(E log E) sau O(E log V) ca timp
	-foarte util algoritmul in practica pentru a gasi caile cele mai scurte si retele de calculatoare
	-este algoritm de tip greedy
	*/


#include <stdio.h> 
#include <stdlib.h> 
#include <string.h>
using namespace std;

#include "Profiler.h"
Profiler profiler("worst Select");

int m = 0;


struct NodeT {
	NodeT * p;
	int key;
	int rank;
};

struct Edge
{
	int src, dest, weight;
};

struct Graph
{ 
	int V, E;
	struct Edge* edge;
};

 Graph* createGraph(int V, int E)
{
	 Graph* graph = (Graph*)malloc(sizeof(Graph));
	graph->V = V;
	graph->E = E;

	graph->edge = new Edge[E];

	return graph;
}


 NodeT* newNode(int key)
{
	 NodeT * aux = (NodeT*)malloc(sizeof(NodeT));
	 aux->key = key;
	 aux ->rank = 1;
	 aux->p = NULL;

	return aux;
}

 void afisareNode(NodeT* root)
 {
	 printf("Nodul cu valoare: %d\n", root->key);
	 printf("Nodul cu parintele: %d\n", root->p->key);
	 printf("Nodul cu rankul: %d\n", root->rank);
	 printf("\n \n \n");
 }

 void makeSet(NodeT * x)
 {
	 x->p = x;
	 x->rank = 0;
 }


 NodeT * findSet(NodeT * x)
 {
	 if (x != x->p)
	 {
		 x->p = findSet(x->p);
	 }
	 return x->p;
 }

 

 void Link(NodeT * x, NodeT * y)
 {
	 if (x->rank > y->rank)
	 {
		 y->p = x;
	 }
	 else
	 {
		 x->p = y;
		 if (x->rank == y->rank)
		 {
			 y->rank = y->rank + 1;
		 }
	 }
 }

 void Unire(NodeT * x, NodeT * y)
 {
	 Link(findSet(x), findSet(y));
 }

 struct muchie
 {
	 int p;
	 int rank;
 };


 int find(muchie muchii[], int i)
 {
	 profiler.countOperation("CompAlgoritm", m, 1);
	 if (muchii[i].p != i) {
		 profiler.countOperation("AsignAlgoritm", m, 1);
		 muchii[i].p = find(muchii, muchii[i].p);
	 }


	 return muchii[i].p;
 }

 
 void Union(muchie muchii[], int x, int y)
 {
	 int xroot = find(muchii, x);
	 int yroot = find(muchii, y);
	 profiler.countOperation("AsignAlgoritm", m, 2);

	 profiler.countOperation("CompAlgoritm", m, 1);
	 if (muchii[xroot].rank < muchii[yroot].rank) {
		 profiler.countOperation("AsignAlgoritm", m, 1);
		 muchii[xroot].p = yroot;
	 }
	 else if (muchii[xroot].rank > muchii[yroot].rank)
	 {
		 profiler.countOperation("CompAlgoritm", m, 1);
		 profiler.countOperation("AsignAlgoritm", m, 1);
		 muchii[yroot].p = xroot;
	 }
	 else
	 {
		 profiler.countOperation("AsignAlgoritm", m, 2);
		 muchii[yroot].p = xroot;
		 muchii[xroot].rank++;
	 }
 }

 //compara weighturile
 int myComp(const void* a, const void* b)
 {
	 profiler.countOperation("CompAlgoritm", m, 1);
	  Edge* a1 = ( Edge*)a;
	  Edge* b1 = ( Edge*)b;
	 return a1->weight > b1->weight;
 }
 
 void KruskalMST(struct Graph* graph)
 {
	 int V = graph->V;
	 Edge result[5000];  
	 int e = 0;  // index pt rezultat
	 int i = 0;  // index pentru setul de multimi

	 profiler.countOperation("AsignAlgoritm", m, m*log(m));
	 profiler.countOperation("CompAlgoritm", m, m*log(m));
	 // sortam crescator muchiile in fucntie de weightul lor
	 qsort(graph->edge, graph->E, sizeof(graph->edge[0]), myComp);

	 // declaram vectorul pentru muchiii
	  muchie *muchii =( muchie*) malloc(V * sizeof(struct muchie));

	 // punem elementele in vectorul de muchii dupa ce le-am separat
	 for (int v = 0; v < V; ++v)
	 {
		 profiler.countOperation("CompAlgoritm", m, 1);
		 profiler.countOperation("AsignAlgoritm", m, 2);
		 muchii[v].p = v;
		 muchii[v].rank = 0;
	 }

	 // trecem prin toate eementele
	 while (e < V - 1)
	 {
		 profiler.countOperation("CompAlgoritm", m, 1);
		 // alegem cea mai mica muchie in functie de weight si incrementam dupa 
		  Edge next_edge = graph->edge[i++];

		 int x = find(muchii, next_edge.src);
		 int y = find(muchii, next_edge.dest);

		 profiler.countOperation("AsignAlgoritm", m, 3);

		 // verificam daca nu cumva facem un cerc in graf si daca nu adaugam in rezultat
		 profiler.countOperation("CompAlgoritm", m, 1);
		 if (x != y)
		 {
			 profiler.countOperation("AsignAlgoritm", m, 1);
			 result[e++] = next_edge;
			 Union(muchii, x, y);
		 }
	 }

	 // printam rezultatul
	 printf("Rezultatul cu muchiile care raman si avem minimul:\n");
	 for (i = 0; i < e; ++i)
		 printf("%d <--> %d weight: %d\n", result[i].src, result[i].dest, result[i].weight);
	 return;
 }
	 


int main()
{
	/*
	//primul
	NodeT* n1 = newNode(1);
	n1->p = n1;
	n1->rank = 3;

	NodeT* n2 = newNode(2);
	n2->p = n1;

	NodeT* n3 = newNode(3);
	n3->p = n1;

	//al doilea
	NodeT* n4 = newNode(4);
	n4->p = n4;
	n4->rank = 2;

	NodeT* n5 = newNode(5);
	n5->p = n4;

	//al treilea

	NodeT* n6 = newNode(6);
	n6->p = n6;
	n6->rank = 3;

	NodeT* n7 = newNode(7);
	n7->p = n6;
	n7->rank = 2;

	NodeT* n8 = newNode(8);
	n8->p = n7;

	//al patrulea
	NodeT* n9 = newNode(9);
	n9->p = n9;
	n6->rank = 4;

	NodeT* n10 = newNode(10);
	n10->p = n9;
	n9->rank = 3;

	NodeT* n11 = newNode(11);
	n11->p = n10;
	n11->rank = 2;

	NodeT* n12 = newNode(12);
	n12->p = n11;

	//al cincilea

	NodeT* n13 = newNode(13);
	n13->p = n13;
	n13->rank = 3;

	NodeT* n14 = newNode(14);
	n14->p = n13;

	NodeT* n15 = newNode(15);
	n15->p = n15;

	afisareNode(n1);
	afisareNode(n5);
	afisareNode(n7);
	afisareNode(n10);
	afisareNode(n15);

	printf("Prima radacina a nodului %d este %d!\n", n11->key, findSet(n11)->key);
	printf("Prima radacina a nodului %d este %d!\n", n12->key, findSet(n12)->key);
	printf("Prima radacina a nodului %d este %d!\n", n8->key, findSet(n8)->key);

	Unire(n3, n5);
	afisareNode(n1);
	afisareNode(n4);

	Unire(n11, n14);
	Unire(n12, n7);
	afisareNode(n6);
	afisareNode(n9);
	afisareNode(n13);

	int V = 4;  // noduri
	int E = 5;  // muchii
	 Graph* graph = createGraph(V, E);


	//  0-1 
	graph->edge[0].src = 0;
	graph->edge[0].dest = 1;
	graph->edge[0].weight = 10;

	// 0-2 
	graph->edge[1].src = 0;
	graph->edge[1].dest = 2;
	graph->edge[1].weight = 6;

	// 0-3 
	graph->edge[2].src = 0;
	graph->edge[2].dest = 3;
	graph->edge[2].weight = 5;

	// 1-3 
	graph->edge[3].src = 1;
	graph->edge[3].dest = 3;
	graph->edge[3].weight = 15;

	// 2-3 
	graph->edge[4].src = 2;
	graph->edge[4].dest = 3;
	graph->edge[4].weight = 4;

	KruskalMST(graph);
	*/


	

    int N;  // noduri
	int E;  // muchii
	int i, j;

	for (i = 100; i <= 2000; i=i+100)
	{
		N = i;
		E = 4 * i;
		 Graph* graph = createGraph(N, E);
		 for (j = 0; j < E; j++)
		 {
			 m = N;
			 graph->edge[j].src = rand()%N;
			 graph->edge[j].dest = rand()%N;
			 graph->edge[j].weight = rand()%200;
			 //printf("OK\n");
		 }

		 KruskalMST(graph);
	}

	profiler.createGroup("Assigns: ", "AsignAlgoritm");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations: ", "CompAlgoritm");
	profiler.createGroup("A+C Algoritm Kruskal", "CompAlgoritm", "AsignAlgoritm");
	profiler.showReport();
	

	return 0;
}
