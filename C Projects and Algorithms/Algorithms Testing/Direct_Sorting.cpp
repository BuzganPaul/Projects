/** Autor: Buzgan Paul
	Grupa: 6 CTI RO An 2
	TEMA 1 - Comparatie intre metodele de sortare: Insertion, Selection, BubleSort

			   Best Case		Average Case		Worst Case
BubleSort		 C=n, A=0                           C=  A=3C
Insertion		 C=n,A=2n           C=  A=3n        C=A=multe
Selection		mult, A=3n       C=A(aproximativ)    C=3n; A=mult de oridinorl miilor si milioanelor


-Bubble sortul e foarte bun pe siruri mici in caz avarage pentru ca consuma putin spatiu si putina memorie;
-in cazul selection sunt mult mai multe comparari decat asignari, avarage;
-in cazul bubble pentru avarage, asiganrile si compararile pornesc destul de egal dar in final ajung ca asiganrile sa fie de 3 ori mai multe, din cauza swap;
-in cazul avarage pentru insertion asignarile sunt aproape egale cu compararile;
-selection sortul nu merge atata de bine pentru cazurile cu mai putine numere;
-in unele cazuri ideale nu se fac deloc asignari;

*/

#include "Profiler.h"
Profiler profiler("worst Select");

#include <stdio.h>
#include <stdlib.h>

void swap(int *x, int *y)
{
    int aux = *x;
    *x = *y;
    *y = aux;
}

void selectionSort(int v1[], int n)
{
    int i, j, min;


    for (i = 0; i < n-1; i++)
    {

        min = i;
        for (j = i+1; j < n; j++)
        {
        profiler.countOperation("Comps", n);
          if (v1[j] < v1[min])
          {

            min = j;
        }
        }
        profiler.countOperation("Asigns", n, 3);
        swap(&v1[min], &v1[i]);

    }


}

void bubbleSort(int v2[], int n)
{
   int i=1, j;


while (j != 0)
	{

		j = 0;

		for (i = 0; i < n - 1; i++)
		{
			profiler.countOperation("CompB", n);
			if (v2[i] > v2[i + 1])
			{

				j = 1;
				swap(&v2[i], &v2[i + 1]);
				profiler.countOperation("AsignB", n, 3);
			}
		}
		n = n - 1;
	}

}

void insertionSort(int v3[], int n)
{
   int i, key, j;

   for (i = 1; i < n; i++)
   {

       key = v3[i];
       profiler.countOperation("AsignI", n);
       j = i-1;


       while (j >= 0 && v3[j] > key)
       {
            profiler.countOperation("CompI", n);
           v3[j+1] = v3[j];
           profiler.countOperation("AsignI", n);
           j = j-1;
       }
        profiler.countOperation("CompI", n);

       v3[j+1] = key;
        profiler.countOperation("AsignI", n);
   }


}

void afisare(int v[], int n)
{
    int i;
    for (i=0; i < n; i++)
        printf("%d ", v[i]);
    printf("\n");
}



int main()
{


    int v[10000] = { 0 };

	for (int i = 100; i < 10000; i = i + 500)
	{
		FillRandomArray(v, i, 1,10000,true, 1);
		//insertionSort(v, i);              // sa aleg care metoda de sortare vreau
		bubbleSort(v, i);
		//selectionSort(v, i);
		afisare(v, i);
	}
	profiler.createGroup("A Bubble Sort", "AsignB");  //  schimb numele cand vreau sa afisez altceva
	profiler.createGroup("C Bubble Sort", "CompB");
	profiler.createGroup("A+C Bubble Sort", "CompB", "AsignB");
	profiler.showReport();




    return 0;
}
