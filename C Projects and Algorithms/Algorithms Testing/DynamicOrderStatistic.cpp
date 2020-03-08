/*
Autor: Buzgan Paul
	Grupa : 6 CTI RO An 2
	TEMA 6 - OSInsert, OSDelete, OSSelect

	Operatie		Average Case	Worst	
	OSInsert		O(h)	        O(logn)
	OSDelete		O(h)			O(logn)
	OSSelect		O(h)			O(logn)
	- toate avarge caseruile sunt aproximativ in O(h) unde h este inaltimea arborelui
	- toate worst caseruile se intampla in O(logn)
	- nu imi poate face exact pana la 10000 ca imi crapa si se blocheaza
	  */


#include <stdio.h>
#include <stdlib.h>
using namespace std;

#include "Profiler.h"
Profiler profiler("worst Select");
int m;
struct NodeT{
	int key;
	struct NodeT * left;
	struct NodeT * right;
	int size;
};


struct NodeT * newNode(int key)
{
	
	struct NodeT  *aux = (struct NodeT*)malloc(sizeof(struct NodeT));
	aux->key = key;
	aux->left = NULL;
	aux->right = NULL;
	aux->size = 1;
	return aux;
}


void inorder(NodeT *root)
{
	if (root != NULL)
	{
		inorder(root->left);
		printf("%d   ", root->key);
		printf("size: %d\n", root->size);
		inorder(root->right);
	}
}



struct NodeT* insert(struct NodeT* node, int v[], int start, int end)
{
	if (start > end) {
		return NULL;

	}

	int mid = (end + start) / 2;

		
	node= newNode(mid);

	

	node->left = insert(node, v, start, mid - 1);
	node->right = insert(node, v, mid + 1, end);

	return node;
	
}

void reSize(NodeT *root)
{
	if (root->left != NULL && root->right != NULL)
	{
		reSize(root->left);
		reSize(root->right);
		root->size = root->left->size + root->right->size+1;
	}

	if (root->left == NULL && root->right != NULL)
	{
		reSize(root->right);
		root->size =  root->right->size+1;
	}

	if (root->left != NULL && root->right == NULL)
	{
		reSize(root->left);
		root->size = root->left->size + 1;
	}
	if (root->left == NULL && root->right == NULL)
	{
		root->size =  1;
	}
}



struct NodeT * minValueNode(struct NodeT* node)
{
	struct NodeT* current = node;

	//gaseste min
	while (current->left != NULL)
		current = current->left;

	return current;
}


struct NodeT* deleteNode(struct NodeT* root, int key)
{
	// final
	profiler.countOperation("CompDelete", m);
	if (root == NULL) return root;

	// cauat cheia
	profiler.countOperation("CompDelete", m);
	if (key < root->key) {
		profiler.countOperation("AsignDelete", m);
		root->left = deleteNode(root->left, key);
	}
	else if (key > root->key) {
		profiler.countOperation("CompDelete", m);
		profiler.countOperation("AsignDelete", m);
		root->right = deleteNode(root->right, key);
	}
	// aici o gaseste si o sterge
	else
	{
		// caz daca are sau nu succesori
		profiler.countOperation("CompDelete", m);
		if (root->left == NULL)
		{
			profiler.countOperation("AsignDelete", m);
			struct NodeT *temp = root->right;
			free(root);
			return temp;
		}
		else if (root->right == NULL)
		{
			profiler.countOperation("AsignDelete", m);
			profiler.countOperation("CompDelete", m);
			struct NodeT *temp = root->left;
			free(root);
			return temp;
		}

		// nodul cu 2 copii primeste succesor urmator crescator
		profiler.countOperation("AsignDelete", m);
		struct NodeT* temp = minValueNode(root->right);

		// copii succesor
		profiler.countOperation("AsignDelete", m);
		root->key = temp->key;

		// sterge in orderul 
		profiler.countOperation("AsignDelete", m);
		root->right = deleteNode(root->right, temp->key);
	}
	return root;
}

struct  NodeT* OSSelect(struct NodeT * rootx, int i)
{
	int r;
	profiler.countOperation("CompSelect", m);
	if (rootx == NULL)
	{
		printf("Eroare rang prea mare!\n");
		return rootx;
	}
	profiler.countOperation("CompSelect", m);
	if (rootx->left == NULL)
	{
		profiler.countOperation("AsignSelect", m);
		r = 1;
		}
	else
	{
		r = rootx->left->size + 1;
		profiler.countOperation("AsignSelect", m);
	}
	profiler.countOperation("CompSelect", m);
		if (i == r)
		{
			return rootx;
		}
		else
		{
			profiler.countOperation("CompSelect", m);
			if (i < r) {
				return OSSelect(rootx->left, i);
			}
			else
			{
				return OSSelect(rootx->right, i - r);
			}
		}
	}



int main()
{
	

	struct NodeT * root=NULL;
	struct NodeT * root2 = NULL;

	int n = 11;
	int v[12];



	for (int i = 1; i <= n; i++)
	{
		v[i] = i;
	}

	root = insert(root, v, 1, n);
	reSize(root);




	inorder(root);


	deleteNode(root, 3);
	deleteNode(root, 10);
	deleteNode(root, 6);
	reSize(root);
	root2 = OSSelect(root, 6);
	printf("elementul: %d\n", root2->key);

	inorder(root);
	

	/*
	struct NodeT * root = NULL;
	struct NodeT * root2 = NULL;

	int n;
	int v[10000];

	for (int j = 100; j <= 5000; j = j + 100)
	{

		n = j;
		m = j;
		for (int i = 1; i <= n; i++)
		{
			v[i] = i;
		}
		root = insert(root, v, 1, n);
		reSize(root);
		int k = 1;

		for (k = 1; k <= n; k++)
		{


			root2 = OSSelect(root, 1);
			deleteNode(root, 2);
			reSize(root);
		}
		inorder(root);


	}

	profiler.createGroup("Assigns Select:", "AsignSelect");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations Select:", "CompSelect");
	profiler.createGroup("A+C Select", "CompSelect", "AsignSelect");
	profiler.createGroup("AssignsDelete:", "AsignDelete");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("Comparations Delete:", "CompDelete");
	profiler.createGroup("A+C Delete", "CompDelete", "AsignDelete");
	profiler.showReport();
*/
	return 0;
}