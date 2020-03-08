#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>



bool sucCheck = false;

void permit(struct stat fileStat, char * rez)
{

    if(fileStat.st_mode & S_IRUSR) rez[0]='r';
    else rez[0]='-';
    if(fileStat.st_mode & S_IWUSR) rez[1]='w';
    else rez[1]='-';
    if(fileStat.st_mode & S_IXUSR) rez[2]='x';
    else rez[2]='-';
    if(fileStat.st_mode & S_IRGRP) rez[3]='r';
    else rez[3]='-';
    if(fileStat.st_mode & S_IWGRP) rez[4]='w';
    else rez[4]='-';
    if(fileStat.st_mode & S_IXGRP) rez[5]='x';
    else rez[5]='-';
    if(fileStat.st_mode & S_IROTH) rez[6]='r';
    else rez[6]='-';
    if(fileStat.st_mode & S_IWOTH) rez[7]='w';
    else rez[7]='-';
    if(fileStat.st_mode & S_IXOTH) rez[8]='x';
    else rez[8]='-';
    //verificam permisiunile fisierului si construim un string din acestea pt al compara cu cel introdus
}


int stringToInteger(char * num)
{
    int rez=0;
    int len = strlen(num);

    //o functie simpla de transformat din string in integer deoarece atoi functia cu erori
    for(int i=0; i<len; i++)
    {
        rez = rez * 10 + ( num[i] - '0' );
    }

    return rez;
}


int checkfin(char * s, char *t)
{

    //aici verificam daca finalul stringului corespunde cu ce am dat noi ca argument daca ma dat
    if(t[0]=='\0')
    {
        return 1;
    }

    size_t ls = strlen(s);
    size_t lt = strlen(t);
    if (ls >= lt)
    {
        return (0 == memcmp(t, s + (ls - lt), lt));
        //cod luat de la https://codereview.stackexchange.com/questions/54722/determine-if-one-string-occurs-at-the-end-of-another?fbclid=IwAR0jFQq0_C5pg4WiUIV8j6bmckfHsBgRvlARePCRXn2GbtnaERWh3t31Fbg
        //verifica finalurile deoarece am avut cateva probleme cu strcmp si aparent aici era o solutie de a folosi memcpm
    }
    return 0;
}

int listProp(const char *path2, char * nameperm, char * perms, bool rec)
{

    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char filePath[512];
    struct stat statbuf;
    char crez[10];

    //aici folosim functia din laborator pt a parcurge fisierele



    dir = opendir(path2);
    if(dir == NULL)
    {
        return -1;
    }
    if(sucCheck==false)
    {
        printf("SUCCESS\n");
        sucCheck=true;
    }
    //folosim la fel functiile insa verificam si daca corespund permisiunilor noastre
    //pt permisiuni comparam doua stringuri cel primit ca argument si cel format cu permisiunile fisierului gasite in functie
    //de asemenea verificam si daca functia fheckfingaseste finalul strigului bine
    //in recursive facem exact acelasi lucru insa
    while((entry = readdir(dir)) != NULL)
    {
        if(rec==false)
        {
            if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
            {
                snprintf(filePath, 512, "%s/%s", path2, entry->d_name);
                if(lstat(filePath, &statbuf) == 0)
                {
                    permit(statbuf, crez);
                    if(((strcmp(perms, crez)==0) || (perms[0]=='\0'))  && (checkfin(entry->d_name, nameperm)!=0))
                    {
                        printf("%s\n", filePath);

                    }
                }
            }
        }
        else
        {
            if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
            {
                snprintf(filePath, 512, "%s/%s", path2, entry->d_name);

                if(lstat(filePath, &statbuf) == 0)
                {
                    permit(statbuf, crez);
                    if(((strcmp(perms, crez)==0) || (perms[0]=='\0'))  && (checkfin(entry->d_name, nameperm)!=0))
                    {
                        printf("%s\n", filePath);
                    }
                    if(S_ISDIR(statbuf.st_mode))
                    {
                        listProp(filePath, nameperm, perms, rec);
                    }
                }
            }
        }
    }


    closedir(dir);
    return 0;
}

int list(int nr, char **args)
{
    bool rec=false;
    char path[512] = {'\0'};
    char filtru1[100] = {'\0'};
    char filtru2[10] = {'\0'};

    //aici ajunge pt prima oara functia si aici se separa si formeaza argumentele efective dupa egal
    //folosim functia strtok pana la egal pentru a imparti stringul
    //mai apoi se cheama functia ce face listarea efectiva pentru fisiere
    for(int i=1; i<nr; i++)
    {

        char * token = strtok(args[i], "=");
        for(int k=0; k<2; k++)
        {
            if(strcmp(token, "path") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(path, token);
            }

            if(strcmp(token, "name_ends_with") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(filtru1, token);
            }

            if(strcmp(token, "permissions") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(filtru2, token);
            }
        }


        if(strcmp(args[i], "recursive") == 0)
        {
            rec=true;
        }

    }

    //tipul asta de citire cu for ne ajuat sa putem da argumentele in ce ordine vrem si codul sa se execute oricum
    //acelasi tip de citire cu for e aplicat in toate functiile cand li se identifica arumentele efective dupa ce au fost luate din terminal
    //daca nu merge functia se va iesi in eroare si se va afisa eroarea
    if(listProp(path, filtru1, filtru2, rec)==0)
        return 0;
    else
        return -1;
}

int parse(int nr, char **args)
{
    char path[100];

    for(int i=1; i<nr; i++)
    {

        char * token = strtok(args[i], "=");
        for(int k=0; k<2; k++)
        {
            if(strcmp(token, "path") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(path, token);
            }
        }
    }


    struct stat StatBuf;
    if(stat(path, &StatBuf)!=0)
    {
        return -1;
    }



    if(S_ISREG(StatBuf.st_mode)!=0)
    {

        int fd = open(path, O_RDONLY);

        int version;
        int nrSections;
        char magic[5]="";
        int header_size=0;

        lseek(fd, -4, SEEK_END);

        read(fd, magic, 4);

        //dupa ce verificam fisierul sa fie deschis cum trebuie si sa fie fisier incepem in cateva variabile sa citim elementele din sf

        if(strcmp(magic, "NsP3")!=0)
        {
            printf("ERROR\nwrong magic\n");
            return-2;
        }

        //citim magic din capat pt ca asa e formatul in subiectul meu

        lseek(fd, -6, SEEK_END);
        read(fd, &header_size, 2);
        int sec=0-header_size+1;

        lseek(fd, sec, SEEK_END);

        read(fd, &nrSections, 1);

        //la fel si header size dupa mergem la nr de sectii si il citim din nou pt ca asa e formatul la fisere mele
        //verificam daca corespunde cu numarul de sectiuni corespunzator in cerinta

        if(nrSections < 5 || nrSections >14)
        {
            printf("ERROR\nwrong sect_nr\n");
            return -2;
        }

        lseek(fd, -2, SEEK_CUR);
        read(fd, &version, 1);

        if(version < 32 || version >135)
        {
            printf("ERROR\nwrong version\n");
            return -2;
        }

        //citim versiunea si o verificam
        //am folosit de multe ori seek cu valori negative din cauza formatului din subiectul meu pt a parcurge corect fisierul

        lseek(fd, 1, SEEK_CUR);


        char buffer[2000]  = {'\0'};

        char auxTransf[4];

        strcpy(buffer, "SUCCESS\n");
        strcat(buffer, "version=");

        sprintf(auxTransf, "%d", version);
        strcat(buffer, auxTransf);

        strcat(buffer, "\n");
        strcat(buffer, "nr_sections=");

        sprintf(auxTransf, "%d", nrSections);
        strcat(buffer, auxTransf);

        strcat(buffer, "\n");

        //folosim sprint f pt a pune numerele in ufer in formatul corespunzator si strcat pentru a tot adauga in buffer ceea ce avem nevoie sa punem
        //folosim un buffer in care sa introducem tot ce trebuie afisat si in finalul programului afisam bufferul

        for(int i=1; i<=nrSections; i++)
        {

            strcat(buffer, "section");
            sprintf(auxTransf, "%d", i);
            strcat(buffer, auxTransf);


            //facem asta pt fiecare sectiune si de acolo apare acel for

            //trecem prin fiecare sectiune si citim din ea prima oara numele ei si punem litera cu litera in buffer
            strcat(buffer, ": ");

            for(int j=0; j<8; j++)
            {
                char auxRead=' ';
                read(fd, &auxRead, 1);
                if(auxRead != 0)
                {
                    sprintf(auxTransf, "%c", auxRead);
                    strcat(buffer, auxTransf);
                }
            }

            strcat(buffer, " ");

            int sect_type = 0;
            read(fd, &sect_type, 2);
            //citim sect_type si verificam cu cerinta

            if(sect_type == 98 || sect_type == 76 || sect_type==70 || sect_type==36 || sect_type==61 || sect_type==88 || sect_type==26)
            {

                sprintf(auxTransf, "%d", sect_type);
                strcat(buffer, auxTransf);
            }
            else
            {
                printf("ERROR\nwrong sect_types\n");
                return -2;
            }

            char sect_offset[4] = "";
            read(fd, sect_offset, 4);

            //punem si size-ul pt sectiune dar nu si offsetul pt ca asa specifica cerinta

            strcat(buffer, " ");

            int sect_size = 0;
            read(fd, &sect_size, 4);
            sprintf(auxTransf, "%d", sect_size);
            strcat(buffer, auxTransf);

            strcat(buffer, "\n");
        }


        printf("%s\n", buffer);

        //printam bufferul cu tot ce are in el si inchidem fisierul

        close(fd);
    }

    return 0;
}

int extract(int nr, char **args)
{
    char path[100];
    char section[20];
    char line[20];
    for(int i=1; i<nr; i++)
    {

        char * token = strtok(args[i], "=");
        for(int k=0; k<2; k++)
        {
            if(strcmp(token, "path") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(path, token);
            }

            if(strcmp(token, "section") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(section, token);
            }

            if(strcmp(token, "line") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(line, token);
            }
        }

    }

    //citim arumentele ca si la celelalte functii

    struct stat StatBuf;
    if(stat(path, &StatBuf)!=0)
    {
        return -1;
    }

    int sect_offset;

    if(S_ISREG(StatBuf.st_mode)!=0)
    {

        int fd = open(path, O_RDONLY);

        int version;
        int nrSections;
        char magic[5]="";
        int header_size=0;

        lseek(fd, -4, SEEK_END);

        read(fd, magic, 4);

        //urmeaza exact aceeasi structura ca al citirea sectinilor dar putin schimbat in final dupa ce verificam totul

        if(strcmp(magic, "NsP3")!=0)
        {
            printf("ERROR\ninvalid file\n");
            return-2;
        }

        lseek(fd, -6, SEEK_END);
        read(fd, &header_size, 2);
        int sec=0-header_size+1;

        lseek(fd, sec, SEEK_END);

        read(fd, &nrSections, 1);

        int secToSearch=stringToInteger(section);
        int lineToSearch=stringToInteger(line);


        if((nrSections < 5 || nrSections >14) || (nrSections<secToSearch))
        {
            printf("ERROR\ninvalid section\n");
            return -2;
        }

        lseek(fd, -2, SEEK_CUR);
        read(fd, &version, 1);

        if(version < 32 || version >135)
        {
            printf("ERROR\ninvalid file\n");
            return -2;
        }

        lseek(fd, 1, SEEK_CUR);


        char buffer[2000]  = {'\0'};
        //daca scoteam bufeerul afisa prost ce era in fisier din aceasta cauza l-am lasat

        printf("SUCCESS\n");


        lseek(fd, (sec+(18*(secToSearch - 1))), SEEK_END);


        for(int j=0; j<9; j++)
        {
            char auxRead=' ';
            read(fd, &auxRead, 1);
        }



        int sect_type = 0;
        read(fd, &sect_type, 2);


        if(!(sect_type == 98 || sect_type == 76 || sect_type==70 || sect_type==36 || sect_type==61 || sect_type==88 || sect_type==26))
        {
            printf("ERROR\ninvalid file\n");
            return -2;
        }


        read(fd, &sect_offset, 4);


        int sect_size = 0;
        read(fd, &sect_size, 4);

        //folosim un contor pt linii sa stim unde ne aflam si o dimensiune pt a fi siguri ca nu ieism din sectiunea curenta

        int linieCounter=1;
        int dimensiune=0;


        lseek(fd, sect_offset+sect_size+1, SEEK_SET);

        char auxRead2[3]=" ";



        while(linieCounter<=lineToSearch && dimensiune<sect_size)
        {
            //verificam daca dimensiune si linia sunt inca in grafic

            while(auxRead2[1]!=13 && auxRead2[0]!=10 && auxRead2[0]!=0)
            {

                if(linieCounter==lineToSearch)
                {
                    //printam daca si numai daca suntem al linia buna
                    printf("%c", auxRead2[0]);
                }
                //aici parcurgem cate o linie pe rand
                lseek(fd, -2, SEEK_CUR);
                auxRead2[1]=auxRead2[0];
                read(fd, &auxRead2[0], 1);
                dimensiune++;
            }

            //while-ul cel mare asigura ca putem trece din linie in linie bine pt a gasi separatoarele de linii
            // 0A 0D in hexa din rep din valorile efectve 10 s 13 deci le comparam cu ele pt a afla daca suntem sau nu la linie

            lseek(fd, -4, SEEK_CUR);
            read(fd, &auxRead2[1], 1);
            read(fd, &auxRead2[0], 1);
            dimensiune=dimensiune+2;

            //mai apoi mai citim din inca doua caractere pt a trece peste separatoarele de linii
            //folosim offset negativ si tto ducem in spate cu lseek din cauza formatului invers in care sunt scrise sectiunile


            linieCounter++;

        }

        if(dimensiune==sect_size)
        {
            printf("ERROR\ninvalid line\n");
            return -2;
        }
        //afisam eroare daca nu gasim linia de care avem nevoie si am depasit sectiunea, deci daca linia e mai mare decat nr de linii din sectiune

        printf("\n");
        printf("%s\n",buffer);


        close(fd);
    }

    return 0;
}

int findallProp(const char *path2)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char filePath[512];
    struct stat statbuf;


    //incepem precum parcurgerea recursiva la directaore din laborator


    dir = opendir(path2);
    if(dir == NULL)
    {
        return -1;
    }
    if(sucCheck==false)
    {
        printf("SUCCESS\n");
        sucCheck=true;
    }

    while((entry = readdir(dir)) != NULL)
    {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
        {

            snprintf(filePath, 512, "%s/%s", path2, entry->d_name);

            if(lstat(filePath, &statbuf) == 0)
            {

                //aici apare prima diferenta si anume ca testam daca e director sau fisier si daca e fisier incepeam sa citim din el
                if(S_ISREG(statbuf.st_mode))
                {


                    bool test_sect=false;

                    int fd = open(filePath, O_RDONLY);

                    int version;
                    int nrSections;
                    char magic[5]="";
                    int header_size=0;

                    lseek(fd, -4, SEEK_END);

                    read(fd, magic, 4);

                    //folosim aceeasi citire precum la extract insa combinat sa faca pe fiecare sectiune gasita pana gaseste una cu mai mult de 13 linii

                    if(strcmp(magic, "NsP3")!=0)
                    {
                        goto iesire;
                        //folosim goto pentru a inchide fisierul daca nu e bun
                    }

                    lseek(fd, -6, SEEK_END);
                    read(fd, &header_size, 2);
                    int sec=0-header_size+1;

                    lseek(fd, sec, SEEK_END);

                    read(fd, &nrSections, 1);

                    if(nrSections < 5 || nrSections >14)
                    {
                        goto iesire;
                    }

                    lseek(fd, -2, SEEK_CUR);
                    read(fd, &version, 1);

                    if(version < 32 || version >135)
                    {
                        goto iesire;
                    }

                    lseek(fd, 1, SEEK_CUR);


                    //citim tot ce trebuie din fisier si daca gasim ceva ce nu trebuie trimitem sa inchidme fiserul

                    char buffer[2000]  = {'\0'};

                    //acelasi lucru ca mai sus cu acel buffer


                    for(int i=1; i<=nrSections && test_sect==false; i++)
                    {
                        //aici incepem sa citim din sectini si daca a gasit deja una cu mai mult de 13 linii se iese din ea se afiseaza si trece la urmatorul
                        lseek(fd, sec+1+((i-1)*18), SEEK_END);
                        for(int j=0; j<8; j++)
                        {
                            char auxRead=' ';
                            read(fd, &auxRead, 1);

                        }

                        int sect_type = 0;
                        read(fd, &sect_type, 2);

                        //algoritmul este exact ca si la parsare

                        if(!(sect_type == 98 || sect_type == 76 || sect_type==70 || sect_type==36 || sect_type==61 || sect_type==88 || sect_type==26))
                        {
                            goto iesire;
                        }

                        int sect_offset = 0;
                        read(fd, &sect_offset, 4);

                        int sect_size = 0;
                        read(fd, &sect_size, 4);

                        //aici folosim aceeasi logica ca la extract insa nu mai citim de jos in sus si de la final la inceput ci normal

                        int linieCounter=1;
                        int dimensiune=0;

                        //pentru a verifica mereu daca suntem in grafic cu nr de linii si dimensiune si nu depasim

                        lseek(fd, sect_offset, SEEK_SET);

                        char auxRead2[3]="  ";



                        while(linieCounter<=13 && dimensiune<sect_size)
                        {
                            //daca ma gasit ceva cu 13 linii deja sau am depasit din dimensiune iesim

                            while(auxRead2[1]!=13 && auxRead2[0]!=10 && auxRead2[0]!=0)
                            {

                                auxRead2[1]=auxRead2[0];
                                read(fd, &auxRead2[0], 1);
                                dimensiune++;

                                //citim octet cu octet si marim dimensiunea citita
                            }

                            read(fd, &auxRead2[1], 1);
                            read(fd, &auxRead2[0], 1);
                            dimensiune=dimensiune+2;

                            //citim doua pt a trece peste caracterele de spatiu


                            linieCounter++;

                        }

                        //verificam daca o gasit un nr suficient de mare de linii la o sectine daca nu se trece in urmatoare
                        if(linieCounter>13)
                        {
                            test_sect=true;
                        }

                    }

                    //daca s-a gasit in vreuna din sectini test_sect va fi treu si se va afisa linia si trece mai departe inchizand fisierul daca nu se inchide doar fisierul

                    printf("%s",buffer);
                    if(test_sect==true)
                    {
                        printf("%s\n", filePath);
                    }
iesire:
                    close(fd);

                }
                if(S_ISDIR(statbuf.st_mode))
                {
                    //in schimb aici daca nu era un fisier si era un director se va face recursivitatea pt citire, si nu se va afisa calea spre director ci doar continutul lui
                    findallProp(filePath);
                }
            }
        }

    }

    closedir(dir);

    //inchidem directorul precum la citirile normale din director de la list


    return 0;

}



int findall(int nr, char **args)
{
    char path[512];

    for(int i=1; i<nr; i++)
    {
        char * token = strtok(args[i], "=");
        for(int k=0; k<2; k++)
        {
            if(strcmp(token, "path") == 0)
            {
                token = strtok(NULL, "=");
                strcpy(path, token);
            }
        }
    }

    //aici se citeste pathul functiei la fel ca in celelalte cu acel for si mai apoi este apelata functia de findall care si afiseaza calea pt fisiere


    if(findallProp(path)==-1)
    {
        return -1;
    }

    return 0;
}


void read_arg(int nr, char **args)
{
    //aici interpretam comezile si apelam functia pentru comanda introdusa specifica
    for(int i=1; i<nr; i++)
    {
        if(strcmp(args[i], "list") == 0)
        {
            if(list(nr, args)==-1)
            {
                printf("ERROR\ninvalid directory path\n");
            }
            return;
        }
        if(strcmp(args[i], "parse") == 0)
        {
            if(nr>3)
            {
                printf("ERROR\ninvalid directory path\n");
            }
            else
            {
                if(parse(nr, args)==-1)
                {
                    printf("ERROR\ninvalid directory path\n");
                }
            }
            return;
        }
        if(strcmp(args[i], "extract") == 0)
        {
            if(nr>5)
            {
                printf("ERROR\ninvalid file|section|line\n");
            }
            else
            {
                extract(nr, args);
            }
            return;
        }
        if(strcmp(args[i], "findall") == 0)
        {
            if(nr>3)
            {
                printf("ERROR\ninvalid directory path\n");
            }
            else
            {
                if(findall(nr, args)==-1)
                    printf("ERROR\ninvalid directory path\n");
            }
            return;
        }
    }
    printf("ERROR\n");
    //de asemenea daca nu sunt introduse un nr corect de argumente se afiseaza eroare

}



int main(int argc, char **argv)
{


    if(argc >= 2 && argc<=6)
    {
        if(strcmp(argv[1], "variant") == 0)
        {
            printf("84993\n");
        }
        else
        {
            read_arg(argc, argv);
        }
    }
    else
    {
        printf("ERROR\ninvalid directory path");
    }
    //aici apelam functiile si selectam daca se va afisa variant sau se trece la comezile complexe daca nu sunt bine scrise instructiunile se ajunge la invalid path
    return 0;
}
