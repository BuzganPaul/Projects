#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/shm.h>
#include <sys/mman.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ipc.h>
//bibliotecile necesare compilarii codului


unsigned char * a= NULL;

//aici se stoceaza shared memoriul pentru a putea fia ccesat

void exercitiu10(int resp, int req)
{
    close(resp);
    close(req);

    return;
}

//aici avem ultima cerinta care ne cere la inchidem pipie-ul cand primit EXIT in el

void exercitiul9(int resp, int req, unsigned char *data, int size )
{
    unsigned int logical_offset;
    unsigned int no_of_bytes;

    read(req, &logical_offset, sizeof(logical_offset));
    read(req, &no_of_bytes, sizeof(no_of_bytes));

    char mesaj1[] = " READ_FROM_LOGICAL_OFFSET";
    mesaj1[0] = 24;

    char mesaj2[] = " ERROR";
    mesaj2[0] = 5;

    write(resp, &mesaj1, sizeof(mesaj1)-1);
    write(resp, &mesaj2, sizeof(mesaj2)-1);

    return;
}

//aici avem exericitul noua dar trimite testerului numai eroro pt ca exercitiul nu e rezolvat

void exercitiul8(int resp, int req, unsigned char *data, int size )
{

    unsigned int section_no;
    unsigned int offset;
    unsigned int no_of_bytes;

    read(req, &section_no, sizeof(section_no));
    read(req, &offset, sizeof(offset));
    read(req, &no_of_bytes, sizeof(no_of_bytes));

    char mesaj1[] = " READ_FROM_FILE_SECTION";
    mesaj1[0] = 22;


    char mesaj2[] = " ERROR";
    mesaj2[0] = 5;
    write(resp, &mesaj1, sizeof(mesaj1)-1);
    write(resp, &mesaj2, sizeof(mesaj2)-1);

    return;

}

//exercitiul 8 nu e rezolvat dar citeste ce trimite tesreul si dupa trimite erorr chiar daca ce primeste poate executa ceva cu succes

void exercitiul7(int resp, int req, unsigned char *data, int size )
{
    unsigned int offset;
    unsigned int no_of_bytes;

    read(req, &offset, sizeof(offset));
    read(req, &no_of_bytes, sizeof(no_of_bytes));

    //aici citim din pipe cele doua numere de care avem nevoie pentru executarea cerintei mai exact deplasamnetul si numarul de biti ce trebuie modificati

    char mesaj1[] = " READ_FROM_FILE_OFFSET";
    mesaj1[0] = 21;

    //scriem mesajul initial
    //testam daca nu depasim dimensiunile fiserului

    if(offset + no_of_bytes < size)
    {
        for(int i = 0; i < no_of_bytes; i++)
        {
            a[i] = data[offset+i];
            printf("%c", data[offset + i]);
        }

        //aici copiam la inceputul shared memoriului datele numarul specific de biti unul cate unul

        char mesaj2[] = " SUCCESS";
        mesaj2[0] = 7;

        write(resp, &mesaj1, sizeof(mesaj1)-1);
        write(resp, &mesaj2, sizeof(mesaj2)-1);

        //trasnmitem mesajele de succes
    }
    else
    {
        char mesaj2[] = " ERROR";
        mesaj2[0] = 5;

        write(resp, &mesaj1, sizeof(mesaj1)-1);
        write(resp, &mesaj2, sizeof(mesaj2)-1);

        //in cazul unei eroari in care este depasita dimensiunea trimitem un mesaj de eroare

    }
    return;
}



void exercitiu6(int resp, int req)
{
    unsigned char dimensiune;
    read(req, &dimensiune, 1);

    char path[1000];
    read(req, &path, dimensiune);
    path[dimensiune] = '\0';

    //aici primim din nou comenzile de la functie si folosim variabila path pentru sa salva caluea catre fiserul de care avem nveoie

    int fd;
    fd = open(path, O_RDONLY);

    off_t size = lseek(fd, 0, SEEK_END);
    lseek(fd, 0, SEEK_SET);

    //aici deschidem acel fisier transmis in path si ii calculalam lungimea si dupa punem curosul la inceputul lui din nou

    unsigned char *data = (unsigned char*)mmap(NULL, size, PROT_READ|PROT_WRITE, MAP_PRIVATE, fd, 0);

    //aici mapam fisierul intr-un pointer de tip unsigned char
    //am folosit unsigned char deoarece uneori apareau cateva erori

    char mesaj1[] = " MAP_FILE";
    mesaj1[0] = 8;
    write(resp, &mesaj1, sizeof(mesaj1)-1);

    //daca deschdierea fisierului esueaza scriem eror in pipe-ul de response

    if(fd == -1 || data == (void*)-1)
    {
        char mesaj2[] = " ERROR";
        mesaj2[0] = 5;
        write(resp, &mesaj2, sizeof(mesaj2)-1);
        return;
    }
    else{
    char mesaj2[] = " SUCCESS";
    mesaj2[0] = 7;
    write(resp, &mesaj2, sizeof(mesaj2)-1);

    //daca nu esueaza scriem succes si mai apoi intram in bucla infinita pt a stabili urmataorea comanda

    while(true)
    {
        unsigned char dimensiune2;
        read(req, &dimensiune2, 1);

        char comanda[40];
        read(req, &comanda, dimensiune2);
        comanda[dimensiune2] = 0;

        //aici selectam ce se va intampal cu urmatoarea comanda primita in pipe-ul de request

        if(strcmp(comanda, "READ_FROM_FILE_OFFSET") == 0)
        {
            exercitiul7(resp, req, data, size);
        }
        if(strcmp(comanda, "READ_FROM_FILE_SECTION") == 0)
        {
            exercitiul8(resp, req, data, size);
        }
        if(strcmp(comanda, "READ_FROM_LOGICAL_SPACE_OFFSET") == 0)
        {
            exercitiul9(resp, req, data, size);
        }
        if(strcmp(comanda, "EXIT") == 0)
        {
            exercitiu10(resp, req);
            exit(1);
        }
    }
}
    return;

}
void exercitiu5(int resp, int req)
{
    unsigned int offset;
    unsigned int value;
    //tipurile unsigned int sunt specificate in tema

    char mesaj1[] = " WRITE_TO_SHM";
    mesaj1[0] = 12;

    read(req, &offset, sizeof(offset));
    read(req, &value, sizeof(value));

    //citim valorile si pregatim mesajul pt scirere

    //verificam daca valorile sunt valide
    if(offset >= 0 && (offset + sizeof(value)) <= 5079878)
    {
        a[offset] = value;//offset estelocatia la care scriem valoare care trebuie introdusa

        //folosim pointerul ca un vector

        char mesaj2[] = " SUCCESS";
        mesaj2[0] = 7;

        //daca am reusit sa fie valori corecte scriem stringurile de succes si trecem mai departe

        write(resp, &mesaj1, sizeof(mesaj1)-1);
        write(resp, &mesaj2, sizeof(mesaj2)-1);
    }
    if(offset <= 0 || (offset + sizeof(value)) >= 5079878)
    {
        //daca ele nu sunt valide scriem error in pipe-ul de response
        char mesaj2[] = " ERROR";
        mesaj2[0] = 5;

        write(resp, &mesaj1, sizeof(mesaj1)-1);
        write(resp, &mesaj2, sizeof(mesaj2)-1);
    }

    return;
}
void exercitiu4(int resp, int req)
{
    unsigned int dimensiune2 = 0;

    read(req, &dimensiune2, sizeof(dimensiune2));

    //citim mesajul primit corespunzator
    //delcaram shared memory cu tot ce este specificat in cerinta si anume permisiuni si dimensiunea citita

    int SHM_ID = shmget(17508, dimensiune2, IPC_CREAT | 0664);

    //deschidem zona de shared memory si verificam daca totul este bine si nu a dat eroare

    if(SHM_ID < 0)
    {
        printf("Eroare! SHARED MEMORY nu a fost creat!");
        exit(1);
    }

    a = (unsigned char*) shmat(SHM_ID, NULL, 0);


    //in am atasat shared memory-ul pentru a putea fi modificat de instructiunile urmatoare in cod

    char mesaj[] = " CREATE_SHM";
    mesaj[0] = 10;
    write(resp, &mesaj, sizeof(mesaj)-1);

    //aici scirem primul mesaj din reaspunsul dat in pipe-ul response
    //testul de mai jos e precum cel din laborator pentru a veriica daca este corect atatsat

    if(a == (void*)-1)
    {
        char mesaj2[] = " ERROR";
        mesaj2[0] = 5;
        write(resp, &mesaj2, sizeof(mesaj2)-1);
        //daca nu a fost atasat corespusator la shared memory trimitem in pipe-ul de response mesajul de eroare

    }
    else
    {
        char mesaj2[] = " SUCCESS";
        mesaj2[0] = 7;
        write(resp, &mesaj2, sizeof(mesaj2)-1);

        //in schimb daca  a fost atasat corect trimitem mesajul specific si facem o bucla
        //ce va trata urmatoarea comanda trimisa in pipe-ul de req

        while(true)
        {
            unsigned char dimensiune3;
            read(req, &dimensiune3, 1);

            char comanda2[25];
            read(req, &comanda2, dimensiune3);
            comanda2[dimensiune3] = '\0';

            //citim cmanda si mai apoi o tratam prin aceste if-uri sa stim in ce functie o trimitem

            if(strcmp(comanda2, "WRITE_TO_SHM") == 0)
            {
                exercitiu5(resp, req);
            }
            if(strcmp(comanda2, "MAP_FILE") == 0)
            {
                exercitiu6(resp, req);
            }
            if(strcmp(comanda2, "EXIT") == 0)
            {
                exercitiu10(resp, req);
                return;
            }
        }
    }




}

void exercitiu3(int a)
{
    char ping[] = " PING";
    char pong[] = " PONG";
    unsigned int nr_tema = 84993;

    ping[0] = 4;
    pong[0] = 4;

    //se pun dimnesiunile asa cum se va face la fiecare din urmatoarele mesaje ce sunt trimise in pipe-ul response
    //dimeniunile nu includ nicioadata ultimul caracter de final de string

    write(a, &ping, sizeof(ping)-1);
    write(a, &pong, sizeof(pong)-1);
    write(a, &nr_tema, sizeof(nr_tema));

    //aici in cazul in care in piepe a cerut aceasta comanda se scriu in cel de response stringurile specifice
    //mai apoi se face intoarcerea in acea bluca infinita
    //aiic a tine codul pentru pipe-ul de response

    return;
}


void exercitiu2()
{
    char RESP_PIPE[] = "RESP_PIPE_84993";
    char REQ_PIPE[] = "REQ_PIPE_84993";

    //aici declaram charurile ce ne ajuta sa pornim pipeurile cu denumirea din cerinta

    char mesaj_pipe[] = " CONNECT";
    mesaj_pipe[0] = 7;

    //aici avem primul mesaj mesaj de conect care trebuie trimis prin pipe
    //de aemenea primul caracter este lasat liber si dupa modific pentru a contine lungimea mesajului
    //precum se specifica in tema ca este protocolul de trimitere a mesajelor


    int fifo = mkfifo(RESP_PIPE, 0777);

    //aici pornim fifo pentru a putea fi folosite cum trebuie pipe-urile
    //mai jos e conditia daca fifo s-a deschis asa cum trebuie
    if(fifo < 0)
    {
        printf("ERROR\ncannot create the response pipe");
        printf("cannot create the response pipe");
        exit(1);
    }

    int fd_REQ= open(REQ_PIPE, O_RDONLY);

    //aici initiem pipe-ul in care sunt trimise comenzile de la tester
    //mai jos sunt conditiile daca pipe-ul nu se poate deschide cum trebuie

    if(fd_REQ < 0)
    {
        printf("ERROR\ncannot open the request pipe");
        printf("cannot open the request pipe");
        exit(1);
    }

    int fd_RESP = open(RESP_PIPE, O_WRONLY);

    //aici initiem pipe-ul in care sunt trimise raspunsurile programului nostru spre tester
    //mai jos e cazul in care apare o eroare

    if(fd_RESP < 0)
    {
        printf("ERROR\ncannot create the response pipe");
        printf("cannot create the response pipe");
        exit(1);
    }

    write(fd_RESP, mesaj_pipe, sizeof(mesaj_pipe)-1);

    //aici scirme primul mesaj in pipe-ul de raspuns
    //size-ul este scurtat cu o unitate deoarece nu vrem sa fie scris si acel '\0' la sfarsitul stringului
    //asa se va proceda cu scaderea unei unitati de fiecare data cand scriem in string
    //mai jos avem un while infinit care de fiecare data asteapta comenzi in pipe-ul req pentru a putea fi executatet si la
    //exit inchide bucla si termina programul
    while(true)
    {
        unsigned char dimensiune;
        char comanda[25];


        read(fd_REQ, &dimensiune, 1);

        read(fd_REQ, &comanda, dimensiune);
        comanda[dimensiune] = '\0';
        //completam comanda cu '\0' pentru a putea fi folosita in strcmp si alte functii pentru stringuri
        //acea completare cu '\0' va fi facuta dupa fiecare string citit daca e folosit in functii specifice pentru stringuri
        //aici citim prima oara adimensiunea ca mai apoi cu ajutorul ei sa citim comanda care e trimisa de tester in pipe-ul specific

        if(strcmp(comanda, "PING") == 0)
        {
            exercitiu3(fd_RESP);
            return;
        }

        if(strcmp(comanda, "CREATE_SHM") == 0)
        {
            exercitiu4(fd_RESP, fd_REQ);
            return;
        }

        if(strcmp(comanda, "MAP_FILE") == 0)
        {
            exercitiu6(fd_RESP, fd_REQ);
            return;
        }

        if(strcmp(comanda, "EXIT") == 0)
        {
            exercitiu10(fd_RESP, fd_REQ);
            return;
        }

        //aici avem cazurile de comenzi ce pot fi primite de catre program prin pipe-ul request
        //in functie de fiecare comanda se apeleaza o functie specifica ce decide cum continua programul
        //se va iesi din bucla candprogramul trimite exit in pipe-ul req

    }



    return;
}

//aici avem main si de aici pornim tot programul

int main()
{
    exercitiu2();
    printf("SUCCESS\n");
    //aici porneste exercitiul 2 din care pornesc si celelalte exercitii in functii diferite
    //afiseaza succes la final precum spunea in cerinta la final
    return 0;
}
