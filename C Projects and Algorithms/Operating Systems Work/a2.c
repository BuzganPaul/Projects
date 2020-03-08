#include <stdio.h>
#include <unistd.h>
#include <semaphore.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <pthread.h>
#include "a2_helper.h"
//librarii


sem_t *semafor5_paul_buzgan = NULL;
sem_t *semafor5_12_paul_buzgan = NULL;

sem_t *semafor3_1_paul_buzgan = NULL;
sem_t *semafor3_3_paul_buzgan = NULL;

sem_t *semafor6_3_paul_buzgan = NULL;
sem_t *semafor6_1_paul_buzgan = NULL;

//declararea originala a semafoarelor

int checker = 0;
int value = 0;

void* proc_3_threads(void* arg)
{
    //interiorul thresdului din procusul 3

    int nr_th=(intptr_t)arg;

    //transformarea argumentului la loc in int
    //gasit pe stackoverflow evitarea unui warning despre diferenta de dimensiunea atunci cand faci cast
    //stackoverflow.com/questions/6326338/why-when-to-use-intptr-t-for-type-casting-in-c
    //de asta la fiecare cast e folosit acesta

    if(nr_th == 1)
    {
        sem_wait(semafor3_1_paul_buzgan);

    }

    //daca e primul il punem in asteptare

        if(nr_th==2)
    {
    	sem_wait(semafor6_3_paul_buzgan);
    }

    //si al doilea il punem deoarece la ultimul ex ne cere sa fie activate din functii diferite


    info(BEGIN, 3, nr_th);

    if(nr_th == 3)
    {
        sem_post(semafor3_1_paul_buzgan);
        sem_wait(semafor3_3_paul_buzgan);
    //daca suntem in 3 deja pornit vom porni thredul 1 pt a putea fi executat normal
    //de asemenea punem 3 pe asteptatre entru a se termina dupa 1

    }


    info(END, 3, nr_th);

    if(nr_th == 1)
    {
        sem_post(semafor3_3_paul_buzgan);

        //ne asiguram ca thredul 3 se termina dupa 1
    }

    if(nr_th==2)
    {
    	sem_post(semafor6_1_paul_buzgan);
    	//aici ne asiguram ca thredul 1 din proc 6 se activeaza dupa ce se termina thredul 2 de aici
    }

    return NULL;
}

void* proc_6_threads(void* arg)
{
    int nr_th=(intptr_t)arg;
    //cast

    if(nr_th==1)
    {
        sem_wait(semafor6_1_paul_buzgan);
    }
    //daca suntem in threadul 1 il punem pe pauza pt a fi activat de cel din proc 3

    info(BEGIN, 6, nr_th);



    info(END, 6, nr_th);

    if(nr_th==3)
    {
    	sem_post(semafor6_3_paul_buzgan);
    }
    //aici e pentru celallt thread afectat din proc 3 care aici este reactivat

    return NULL;
}


void* proc_5_threads(void* arg)
{

    int nr_th=(intptr_t)arg;
    //cast

    /*
        int val = 0;
        sem_getvalue(semafor5_12_paul_buzgan, &val);

        if(nr_th!=12 && val!=3 && checker==0)
        {
            sem_wait(semafor5_12_paul_buzgan);
        }
        else{
        if(nr_th!=12)
        {
        sem_wait(semafor5_paul_buzgan);
        }
        else{
            sem_wait(semafor5_paul_buzgan);
        }
        }


        info(BEGIN, 5, nr_th);

        if(val == 4 && nr_th == 12)
        {
            checker=1;
            for(int j=0; j<=4; j++)
            {
                sem_post(semafor5_12_paul_buzgan);
            }
        }

        //if(nr_th != 12)
        //{
            //sem_post(semafor5_paul_buzgan);

            //sem_wait(semafor5_paul_buzgan);
        //}

        info(END, 5, nr_th);
        sem_post(semafor5_paul_buzgan);

    */
    //cod mult si inutil la care am renuntat pentru ca functiona doar in unele cazuri si nu era deloc stabil
    //de aceea am ramas doar la partea din cerinta care nu lasa mai mult de 5 threduri se se execute simultan
    sem_wait(semafor5_paul_buzgan);

    info(BEGIN, 5, nr_th);
    info(END, 5, nr_th);

    sem_post(semafor5_paul_buzgan);


    return NULL;
}



int main()
{

    int proc_2=-1;
    int proc_3=-1;
    int proc_4=-1;
    int proc_5=-1;
    int proc_6=-1;
    int proc_7=-1;

    //initializam procese cu -1

    pthread_t threads_proc_5[42];
    pthread_t threads_proc_3[5];
    pthread_t threads_proc_6[6];

    //vectori de threaduri pentru a crea toate de care avem nevoie

    semafor5_paul_buzgan = sem_open("Proc5_Semafor", O_CREAT, 0644, 4);//precum cel de dedesupt
    semafor5_12_paul_buzgan = sem_open("Proc5_12_Semafor", O_CREAT, 0644, 4);//pentru ex care are nevoie sa fie max 5 procese simultan
    semafor3_1_paul_buzgan = sem_open("Proc3_1_Semafor", O_CREAT, 0644, 0);
    semafor3_3_paul_buzgan = sem_open("Proc3_3_Semafor", O_CREAT, 0644, 0);
    semafor6_1_paul_buzgan = sem_open("Proc6_1_Semafor", O_CREAT, 0644, 0);
    semafor6_3_paul_buzgan = sem_open("Proc6_3_Semafor", O_CREAT, 0644, 0);//restul sunt simple cu cate o permisiune

    if(semafor3_1_paul_buzgan == NULL)
    {
        printf("Eroare deschidere semafor");
    }

    if(semafor3_3_paul_buzgan == NULL)
    {
        printf("Eroare deschidere semafor");
    }

    if(semafor6_1_paul_buzgan == NULL)
    {
        printf("Eroare deschidere semafor");
    }

    if(semafor6_3_paul_buzgan == NULL)
    {
        printf("Eroare deschidere semafor");
    }

    if(semafor5_paul_buzgan == NULL)
    {
        printf("Eroare deschidere semafor");
    }

    if(semafor5_12_paul_buzgan == NULL)
    {
        printf("Eroare deschidere semafor");
    }

    //verificam daca semafoarele se deschid cu bine

    init();

    info(BEGIN, 1, 0);

    //pornim helperul pt procesul principal

    proc_2=fork();

    if(proc_2 < 0)
    {
        printf("Eroare creare proces!");
    }

    //facem primul copil si verificam dac functioneaza bine

    if(proc_2==0)
    {
        //procesul P2
        info(BEGIN, 2, 0);
        proc_3=fork();

        if(proc_3 < 0)
        {
            printf("Eroare creare proces!");
        }

        if(proc_3==0)
        {
            //Procesul P3
            info(BEGIN, 3, 0);

            for(int i=1; i<=4; i++)
            {
                pthread_create(&threads_proc_3[i], NULL, *proc_3_threads,(void*)(intptr_t)i);
            }
            for(int i=1; i<=4; i++)
            {
                pthread_join(threads_proc_3[i], NULL);
            }

            //pornim threadurile si se sincronizam cu codul clasic prez la lab si curs

            proc_7=fork();

            if(proc_7 < 0)
            {
                printf("Eroare creare proces!");
            }
            //procesul P7
            if(proc_7 == 0)
            {
                info(BEGIN, 7, 0);
                info(END, 7, 0);
                exit(0);
            }
            waitpid(proc_7,NULL,0);

            info(END, 3, 0);
            exit(0);
        }

        proc_5=fork();

        if(proc_5 < 0)
        {
            printf("Eroare creare proces!");
        }

        if(proc_5==0)
        {
        //procesul P5
            info(BEGIN, 5, 0);

            for(int i=1; i<=41; i++)
            {


                pthread_create(&threads_proc_5[i], NULL, *proc_5_threads,(void*)(intptr_t)i);



            }
            for(int i=1; i<=41; i++)
            {
                pthread_join(threads_proc_5[i], NULL);

            }

            //creat de threaduri

            info(END, 5, 0);
            exit(0);
        }


        waitpid(proc_3,NULL,0);
        waitpid(proc_5,NULL,0);
        //prin waitpid asteptam ca copii procusului sa se termine pentru a nu se inchide copii inaintea aprintelui
        //codul e valabil de fiecare data cand apare waitpid

        info(END, 2, 0);
        exit(0);
    }

    proc_4=fork();

    if(proc_4 < 0)
    {
        printf("Eroare creare proces!");
    }

    if(proc_4==0)
    {
        //procesul P4
        info(BEGIN, 4, 0);

        proc_6=fork();

        if(proc_6 < 0)
        {
            printf("Eroare creare proces!");
        }

        if(proc_6 == 0)
        {

            //procesul P6
            info(BEGIN, 6, 0);

            for(int i=1; i<=5; i++)
            {
                pthread_create(&threads_proc_6[i], NULL, *proc_6_threads,(void*) (intptr_t)i);
            }
            for(int i=1; i<=5; i++)
            {
                pthread_join(threads_proc_6[i], NULL);

            }
            //creat de threaduri
            info(END, 6, 0);
            exit(0);
        }
        waitpid(proc_6,NULL,0);

        info(END, 4, 0);
        exit(0);
    }

    waitpid(proc_2,NULL,0);
    waitpid(proc_4,NULL,0);

    sem_unlink("Proc5_Semafor");
    sem_unlink("Proc5_12_Semafor");
    sem_unlink("Proc3_1_Semafor");
    sem_unlink("Proc3_3_Semafor");
    sem_unlink("Proc6_1_Semafor");
    sem_unlink("Proc6_3_Semafor");

    //anulam semafoarele

    info(END, 1, 0);
    //inchidem proc initial si notam in helper
    return 0;
}
