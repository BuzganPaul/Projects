
/** Autor: Buzgan Paul
	Grupa: 6 CTI RO An 2
	TEMA 1 - Tabele de dispersie

Factor de umplere    Efort mediu gasit		Efort maxim gasit		Efort mediu negasit		Efort maxim negasit
0.8						 0,18						 2						1875,32					10007
0.85		             0,17                        3						1765,79					10007
0.9						 0,16						 2						1666,88					10007
0.95					 0,15						 1						1579,21					10007
0.99					 0,15						 1						1515,44					10007

-efortul maxim pentru negasit va fi mereu nr de casute disponibile din tabela;
-eforturile medii tind sa scada cu cat factorul de umplere este mai mare;
-efortul maxim gasit scade si el(nesigur);

*/


#include <stdio.h>
#include "Profiler.h"
Profiler profiler("worst Select");

using namespace std;

int nrop;

typedef struct {
	int key;
} hashT;

int hashFun(int n, int i, int size)
{
	return (n + i * i) % size;

}

void insertHash(int key, hashT a[], int size, int i)
{
	int n;
	n = hashFun(key, i, size);
	if (a[n].key == NULL)
	{
		a[n].key = key;
	}
	else {
		i++;
		n = hashFun(key, i, size);
		while (a[n].key != NULL)
		{
			i++;
			n = hashFun(key, i, size);
		}
		//a[n].key = key;
		
		if (i <= size)
		{
			a[n].key = key;
		}
		else
		{
			printf("Hash Overflow! \n");
		}
		
	}
}


int searchHash(int key, hashT a[], int size, int i)
{
	int n;
	n = hashFun(key, i, size);
	nrop++;
	if (a[n].key == key)
	{
		printf("Gasit! \n");
		
	}
	else {
		i++;
		n = hashFun(key, i, size);
		nrop++;
		while (a[n].key != key && i<size && a[n].key!=NULL)
		{
			nrop++;
			i++;
			n = hashFun(key, i, size);

		}
		if (i < n && a[n].key != NULL)
		{
			printf("Gasit! \n");
		}
		else
		{
			printf("Negasit! \n");
		}
	}
	return i;
}

void deleteHash(int key, hashT a[], int size, int i)
{
	int n;
	n = hashFun(key, i, size);
	if (a[n].key == key)
	{
		a[n].key = NULL;
	}
	else {
		i++;
		n = hashFun(key, i, size);
		while (a[n].key != key && i < size)
		{
			i++;
			n = hashFun(key, i, size);
		}
		if (i < size )
		{
			a[n].key = NULL;
		}
		else
		{
			printf("Nu exista! \n");
		}
	}
}


void printHash(hashT a[], int size)
{
	for (int i = 0; i < size; i++)
	{
		printf("%d ", a[i].key);
	}
	printf("\n ");
}

int main()
{
	
	hashT tabel[17];
	int n = 17;
	for (int i = 0; i < n; i++)
	{
		tabel[i].key = NULL;
	}


	insertHash(13, tabel, n, 0);
	insertHash(14, tabel, n, 0);
	insertHash(15, tabel, n, 0);
	insertHash(20, tabel, n, 0);
	insertHash(3, tabel, n, 0);
	insertHash(37, tabel, n, 0);
	insertHash(1, tabel, n, 0);
	insertHash(11, tabel, n, 0);
	insertHash(48, tabel, n, 0);
	insertHash(900, tabel, n, 0);
	insertHash(113, tabel, n, 0);
	insertHash(12, tabel, n, 0);
	insertHash(4, tabel, n, 0);
	insertHash(1344, tabel, n, 0);
	insertHash(5, tabel, n, 0);
	insertHash(21, tabel, n, 0);
	
	

	
	printHash(tabel, n);


	

	deleteHash(13, tabel, n, 0);
	deleteHash(3, tabel, n, 0);

	printHash(tabel, n);

	searchHash(15, tabel, n, 0);
	searchHash(1344, tabel, n, 0);
	searchHash(41, tabel, n, 0);
	searchHash(42, tabel, n, 0);
	
	/*
	int v[10007] = { 0 };
	int vgood[1500] = { 0 };
	int vbad[1500] = { 0 };


	hashT tabel[10007];
	int n = 10007;
	float fact = 0.99;
	fact = fact * n;
	int fact2 =(int)fact;
	printf("Nr de elemente: %d \n", fact2);

	for (int i = 0; i < n; i++)
	{
		tabel[i].key = NULL;
	}

	FillRandomArray(v, 10007, 1, 10007, false, 0);


	for (int i = 0; i < fact2; i++)
	{
		insertHash(v[i], tabel, n, 0);
	}

	for (int i = 0; i < 1500; i++)
	{
		vgood[i] = v[i];
	}


	
	FillRandomArray(vbad, 1500, 40000, 50000, true, 0);

	nrop = 0;

	int salv=0, salv2=0, maximus;

	for (int i = 0; i < 1500; i++)
	{
		maximus=searchHash(vgood[i], tabel, n, 0);
		if (maximus > salv)
		{
			salv = maximus;
		}
	}


	int salv3;
	salv3 = nrop;
	nrop = 0;

	for (int i = 0; i < 1500; i++)
	{
		maximus = searchHash(vbad[i], tabel, n, 0);
		if (maximus > salv2)
		{
			salv2 = maximus;
		}
	}

	printf("Nr total accesari gasibile(mediu): %f \n",(float) salv3/fact2);
	printf("Nr maxim de accesari pe operatie gasibile: %d \n", salv);

	printf("Nr total accesari negasibile(mediu): %f \n",(float) nrop/fact2);
	printf("Nr maxim de accesari pe operatie negasibile: %d \n", salv2);
	*/

	return 0;
}