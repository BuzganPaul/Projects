/*
Autor: Buzgan Paul
	Grupa : 6 CTI RO An 2
	TEMA 7 - Reprezentari pentru arbori multicai
	*/


#include <stdio.h>
#include <stdlib.h>
using namespace std;

struct NodeT {
		   int key;
		   int rootver;
		   struct NodeT * next;
};

struct NodeT2 {
	int key;
	struct NodeT2 * child;
	struct NodeT2 * brother;


};

struct NodeT * newNode(int key)
{
	struct NodeT  *aux = (struct NodeT*)malloc(sizeof(struct NodeT));
	aux->key = key;
	aux->next = NULL;
	aux->rootver = 0;
	return aux;
}

struct NodeT2 * newNode2(int key)
{
	struct NodeT2  *aux = (struct NodeT2*)malloc(sizeof(struct NodeT2));
	aux->key = key;
	aux->brother = NULL;
	aux->child = NULL;
	return aux;
}



void pretty1(int v[], int n)
{
	for (int i = 0; i < n; i++)
	{
		if(v[i]!=-1)
		printf("Elementul %d are ca parinte %d!\n", i, v[i]);
		else
			printf("Elementul %d este radacina!\n", i);
	}

}

void transformare1(int v[], int n, struct NodeT * root[])
{
	struct NodeT * temp;
	for (int i = 0; i < n; i++)
	{	
		temp = root[v[i]];
		struct NodeT* aux;
		aux = newNode(i);
		if (v[i] != -1)
		{
			if (root[v[i]] == NULL)
			{

				root[v[i]] = aux;
			}
			else
			{
				temp = root[v[i]];

				while (root[v[i]]->next != NULL)
				{
					root[v[i]] = root[v[i]]->next;
				}
				root[v[i]]->next = aux;
				root[v[i]] = temp;
				
			}
		}
		else
		{
			if (v[i] == -1)
			{
				root[i]->rootver = 1;
			}
		}

	}
}

void pretty2(struct NodeT * root[], int n)
{
	for (int i = 0; i < n; i++)
	{
		struct NodeT * temp;
		temp = root[i];
		if (temp != NULL)
		{
			if (temp->rootver == 1)
			{
				printf("Elementul %d este radacina!  \n", i);
			}
		}
		printf("Elementul %d are ca succesori:   ", i);
		while (temp != NULL)
		{
			printf(" %d ", temp->key);
			temp = temp->next;
		}
		printf(" \n");
	}
}


struct NodeT2 *addSibling(struct NodeT2 *n, int data)
{
	if (n == NULL)
		return NULL;

	while (n->brother)
		n = n->brother;

	return (n->brother = newNode2(data));
}

struct NodeT2 *addChild(struct NodeT2 * n, int data)
{
	if (n == NULL)
		return NULL;

	if (n->child)
		return addSibling(n->child, data);
	else
		return (n->child = newNode2(data));
}

int create(struct  NodeT2* root, int n, struct NodeT * root2[])
{
	int i;
	for (i = 0; i < n; i++)
	{
		if (root2[i]!=NULL && root2[i]->rootver == 1)
		{
			
			break;
		}
	}
	root=newNode2(root2[i]->key);
	return i;
}

void transformare2(struct NodeT2 * root2, int salv, struct NodeT *root1[])
{
	while (root1[salv] != NULL)
	{
		NodeT2 *n1 = addChild(root2, root1[salv]->key);
		transformare2(root2->child, root1[salv]->key, root1);
		root1[salv] = root1[salv]->next;
	}
}



void pretty3(struct NodeT2 * root2)
{
	if (root2 == NULL)
		return;
	
	struct NodeT2 * temp;
	temp = root2;

	while (root2)
	{
		while (temp != NULL)
		{
			printf(" %d \n", temp->key);
			temp = temp->brother;
		}
		if (root2->child)
		{
			printf("Pentru nodul %d avem ca descendenti: ", root2->key);
			pretty3(root2->child);
		}
		root2 = root2->brother;
	}
}


int main()
{
	printf("Reprezentarea 1:\n\n");
	int v[] = { 8, 7, 5, 2, 6, 7, 0, 4, -1, 8, 8, 8, 9, 10, 10, 11};
	int n = sizeof(v) / sizeof(v[0]);
	pretty1(v, n);

	printf("\n \n \n");


	printf("Reprezentarea 2:\n\n");
	struct NodeT * root[16];

	for (int i = 0; i < n; i++)
	{
		root[i] = NULL;
	}

	transformare1(v, n, root);
	pretty2(root, n);

	printf("\n \n \n");
	printf("Reprezentarea 3:\n\n");
	
	int salv;
	struct NodeT2 * root2 = NULL;
	salv=create(root2, n, root);
	root2 = newNode2(salv);
	transformare2(root2, salv, root);
	pretty3(root2);


	return 0;
}