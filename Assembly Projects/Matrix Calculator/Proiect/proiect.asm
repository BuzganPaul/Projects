.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern printf: proc
extern fprintf: proc
extern scanf: proc
extern fscanf: proc
extern fopen: proc
extern fclose: proc
extern exit: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
filename db "A.txt", 0
filename2 db "B.txt", 0
filename3 db "rezultat.txt", 0
;moduri de citire si scriere
mode_rb db "r", 0
mode_w db "w", 0
format db "%d ", 0
formats db "%d", 0
format2 db "%s", 0
;diverse variabile care retin coloane, nr maxim de nr, contoare, si erori
buffer dd 0
A_coloane dd 0
A_linii dd 0
B_coloane dd 0
B_linii dd 0
nr dd 0
contor1 dd 0
contor2 dd 0
i dd 0
j dd 0
de dd 0
de2 dd 0
de3 dd 0
de4 dd 0
de5 dd 0
de6 dd 0
eror dd 0
salv dd 0
scal dd 0
scalp db "Dati un scalar: ", 0 ;
	
;diferite mesaje care se afiseaza in cadrul codului
msg db "Calculator pentru matrici!", 10, 10, "Se afiseaza matricile:", 10, 0 ;
msgpos db "Sunt posibile urmatoarele operatii: 1->A+B (adunare), 2->A-B (scadere), 3->aA (inmultire cu scalar), 4->AT (Transpusa).", 10, 0 ;
msgnpos db "Eroare: nu sunt posibile operatii, cu excetia calculului transpusei AT", 10, 0 ;
msgposdet db "Este posibil si calculul determinantului 5->det(A).", 10, 0 ;
msgnposdet db "Avertizare: indicii matricei sunt prea mari pentrua fi calculat deterinantul cu acest program!", 10, 0 ;
op db "Operatiile ca numarul dinainte prescurtarilor. Orice alta metoda va fi declarata ca eroare.", 10, "Spune o operatie(de ex '1' pentru adunare):", 0 ;
err db "Eroare: nu exisat operatia introdusa!", 10, 0 ;
afisare db "Rezultatul a fost salvat in fisierul rezultat.txt.", 10, "Mai jos putem vedea rezultatul ca o matrice:", 10, 0 ;


operatie dd 0;
t dd 0;
t2 dd 4;
rand_free db 10, 10, 10, 0 ;
linie db 10, 0 ;
;structurile unde se salveaza matricile si respectiv rezultatul operatiei
A  dd 100 dup(0)
B  dd 100 dup(0)
REZ  dd 100 dup(0)

.code
start:
	push offset msg ;argumentele unei functii se pun pe stiva, in ordine inversa
	call printf
	;conventia de apel la printf e cdecl, ceea ce inseamna ca cine apeleaza, trebuie sa curete stiva
	add esp, 4 
	;mov ebx, [m1] ;pentru parcurgerea liniilor
	mov edx, 0 ;pentru parcurgerea coloanelor

start_citire1:
	;apelam fopen
	push offset mode_rb
	push offset filename
	call fopen
	add esp, 8
	mov esi, eax ;salvam pointer-ul la fisier
	
	mov ecx, 0 ;pentru comparari
		
	
	;punem pe stiva parametrii pentru fread
	
	push offset A_coloane
	push offset format
	push esi ;stream
	call fscanf
	add esp, 16 
	
	push offset A_linii
	push offset format
	push esi ;stream
	call fscanf
	add esp, 16 
	
	; folosim doua contoare care ne ajuta la afisarea matricii
	;folosim EDI pentru a parcurge structura de date
	mov ecx, 0
	mov contor1, 0
	mov contor2, 0
	mov EDI, 0
	
	
	push offset buffer
	push offset format
	push esi ;stream
bucla_citire_1:
	call fscanf
	test eax, eax; verificam daca suntem la finalul matricii
	js inchidere_fisier_1
	xor eax, eax ;facem eax sa fie 0
	mov eax, buffer
	
	mov A[EDI],eax; salveaza de fiecare data numarul in structura de date
	add edi, 4
	;odata cu salvarea se si afiseaza numarul
	push eax
	push offset format
	call printf
	add esp, 8
	
	;aici contoarele verifica daca sunt la capatul unei linii pentru a da printf si la un enter sau nu
	add contor1, 1
	mov ecx, A_coloane 
	sub ecx, contor1	
	jecxz liniea
	jmp bucla_citire_1
liniea:
	push offset linie; aici e algoritmul de afisare a unui enter
	push offset format2
	call printf
	add esp, 8
	mov contor1, 0
	;sare inapoi pentru a mai afisa o linie
	jmp bucla_citire_1
	
	
inchidere_fisier_1:
	add esp, 16 ;curatam stiva de la fscanf
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4
	
	push offset rand_free ; da un rand liber pentru a fi afisat mai bine
	call printf
	add esp, 4
	
	
	
start_citire2:
	;aceasta citire este exact ca cea anterioara numai ca se foloseste pe al doilea fisier si salveaza ina doua structura de date
	;apelam fopen
	push offset mode_rb
	push offset filename2
	call fopen
	add esp, 8
	mov esi, eax ;salvam pointer-ul la fisier
	
	mov EDI, 0
		
	
	;punem pe stiva parametrii pentru fread
	
	push offset B_coloane
	push offset format
	push esi ;stream
	call fscanf
	add esp, 16 
	
	push offset B_linii
	push offset format
	push esi ;stream
	call fscanf
	add esp, 16 
	;aici salveaza numarul de numere din matrice
	mov eax, 1
	mul A_coloane
	mul B_coloane
	mov nr, eax
	
		
	
	push offset buffer
	push offset format
	push esi ;stream
bucla_citire_2:
	call fscanf
	test eax, eax
	js inchidere_fisier_2
	xor eax, eax ;facem eax sa fie 0
	
	mov eax, buffer
	
	mov B[EDI],eax
	add edi, 4

	push eax
	push offset format
	call printf
	add esp, 8
	
	add contor2, 1
	mov ecx, B_coloane 
	sub ecx, contor2	
	jecxz liniea2
	jmp bucla_citire_2
liniea2:
	push offset linie
	push offset format2
	call printf
	add esp, 8
	mov contor2, 0
	
	jmp bucla_citire_2
	
inchidere_fisier_2:
	add esp, 16 ;curatam stiva de la fscanf
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4
	
	push offset rand_free 
	call printf
	add esp, 4
	
;aici verificam posibilitatile de operatie in cadrul carora se pot incadra matricile 	
posibilitati:
	mov ecx, A_coloane 
	sub ecx, B_coloane	
	jecxz istrue
isfalse:
	push offset msgnpos ;daca nu e posibil pt cela adunare etc inafara de transpusa 
	call printf
	add esp, 4
	jmp  nextinstr
istrue:
	push offset msgpos ;daca se pot face aceste operatii
	call printf
	add esp, 4
	jmp  nextinstr

	
nextinstr:
;aici verifica numarul de linii pentru a se vedea daca nu e prea mare pt a i se face determinant
	mov ecx, A_coloane 
	cmp ecx, 4
	jl istrue2
isfalse2:
	push offset msgnposdet 
	call printf
	add esp, 4
	jmp  nextinstr2
istrue2:
	push offset msgposdet 
	call printf
	add esp, 4
	jmp  nextinstr2
	
nextinstr2:
	;aici se introduce operatia in cazul in care este valabila
	push offset op
	call printf
	add esp, 4
	
	push offset operatie
	push offset formats
	call scanf
	add esp, 8
	
	
	mov esi, 0

	; daca este 1 se sare la suma
	mov ecx, 1 
	sub ecx, operatie	
	jecxz suma
	;daca e doi se sare la scadare
	mov ecx, 2 
	sub ecx, operatie	
	jecxz scadere
	;daca sa introdus alt numar se va trece la urmatoarele dar de asemenea se salveaza ca o mica eroare ce la final
	;va fi folosita daca se introduce o operatie inafara formatului folosit in cod
	add eror, 1
	jmp final
	
suma:
	mov edi, 0
	mov contor1, 0
plus:
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final; se verifica daca este la ultimul element din matrice
	add contor1, 1
	mov eax, A[EDI]
	add eax, B[EDI]
	mov REZ[EDI], eax
	add EDI, 4
	jmp plus
;se terce prin fiecare numar si se aduna cu corespondentul lui din cealalta matrice si se salveaza in rezultatul final		
	

scadere:
	mov edi, 0
	mov contor1, 0
minus:
	;functioneaza precum algoritmul de adunare doar ca faca scadere in loc de adunare, algortimul se repeta de asemenea pt fiecare element
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final
	add contor1, 1
	mov eax, A[EDI]
	sub eax, B[EDI]
	mov REZ[EDI], eax
	add EDI, 4
	jmp minus	

final:
	mov edi, 0

	;aici se ajunge in cazul in care peratiiile nu au fost de scadere sau adunare
	
	mov ecx, 3 
	sub ecx, operatie
	; daca e scalar se terce la algoritmul pentru executarea scalarului
	jecxz scalar
	add eror, 1
	; se mai adauga un caz de eroare
	jmp final2

scalar:
	;aici se face o citire de scalar care se va folosi la inmultire
	push offset scalp
	call printf
	add esp, 4
	
	push offset scal
	push offset formats
	call scanf
	add esp, 8
	;aici se vor porni iar contoarele
	mov edi, 0
	mov contor1, 0
inm:
	;aici se va face inmultirea pe fiecare elemet
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final2
	;daca suntem la final se iese din bucla
	add contor1, 1
	mov eax, A[EDI]
	mul scal
	mov REZ[EDI], eax
	add EDI, 4
	jmp inm	;se repeta algoritmul pt fiecare numar
	
final2:
	mov edi, 0
	;aici se ajunge in cazul in care nu sa facut nici scalar
	mov ecx, 4 
	sub ecx, operatie	
	jecxz transpusa
	;aici se terce la transpusa daca sa dat aceasta operatie si sare peste ea 
	add eror, 1
	jmp final3
	
	
transpusa:
	;aici se vor initializa contoarele
	mov edi, 0
	mov contor1, 0
	mov i, 0
	mov j, 1
tr:
	;se verifica daca cumva suntem la ultimul element
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final3
	;se folosesc i si j pe post de contoare in cazul acesta
	add i, 1
	;se folosesc t si t, t2 pentru a se salva noul indice pentru rezultate
	mov eax, i
	sub eax, 1	
	mul A_coloane
	add eax, j
	mul t2
	sub eax, t2
	mov t, eax
	;aici se salveaza EDI, dupa care se une noul EDI, iar mai apoi se salveaza in rezultat cu noul EDI, dupa se repune EDI-initial pentru a se terce si la celelalte elemente
	mov salv, edi
	mov eax, A[EDI]
	mov EDI, t
	mov REZ[EDI], eax
	mov EDI, salv
	add edi, 4
	
	
	mov ecx, A_coloane 
	sub ecx, i
	add contor1, 1
	jecxz av
	jmp tr
av:
	;cazul in care se ajunge la capat de lini si se modifica i si j
	mov i, 0
	add j, 1
	jmp tr	
	
	
final3:
	; aici se ajunge in cazul in care operatia aleasa a fost determinantul
	mov ecx, 5
	sub ecx, operatie	
	jecxz deter
	;in cazul in care nu este aceasta eroare va avea valoarea maxima ceea ce inseamna operatie introdusa gresit
	add eror, 1
	jmp final4
deter:
	; aici se verifica daca matricea are 4 sau 9 elemente
	mov edi, 0
	mov ecx, A_coloane
	;daca are 9 elemnete si sa sari la algoritmul pentru 9 elemnete daca nu la cel de doua
	sub ecx, 3	
	jecxz deter3

deter2:	
	;aici se ia fiecare numar in parte se salveaza in variabile si se pun la final in rezultat
	mov eax, A[0]
	mov de, eax
	mov eax, A[12]
	mul de
	mov de2, eax
	mov eax, A[4]
	mov de, eax
	mov eax, A[8]
	mul de
	mov de3, eax
	mov eax, de2
	sub EAX, de3
	mov REZ[0], eax
	jmp final4; se sare la capat pt a evita algoritmul pentru det de ordin 3

deter3:
	;aici se face exact ca cel de oridn doi numai ca pe 9 termeni, tot se salveaza in cateva variaile inainte de a fi puse in rezultat
	mov eax, 1
	imul A[0]
	imul A[16]
	imul A[32]
	mov de, eax
	mov eax, 1
	imul A[4]
	imul A[20]
	imul A[24]
	mov de2, eax
	mov eax, 1
	imul A[8]
	imul A[12]
	imul A[28]
	mov de3, eax
	
	mov eax, 1
	imul A[8]
	imul A[16]
	imul A[24]
	mov de4, eax
	mov eax, 1
	imul A[0]
	imul A[20]
	imul A[28]
	mov de5, eax
	mov eax, 1
	imul A[4]
	imul A[12]
	imul A[32]
	mov de6, eax
	mov eax, 0
	add eax, de
	add eax, de2
	add eax, de3
	sub eax, de4
	sub eax, de5
	sub eax, de6
	;atat pentru cel de ordin 2 cat si pentru cel de ordin 3 se va salva rezultatul in primul termen al structurii
	mov REZ[0], eax
	


final4:	
	mov edi, 0
	;aici se verifica daca cumva nu sa executat vreo opreatie si se trece la eroare
	mov ecx, 4 
	sub ecx, eror
	jecxz eroare
	jmp start_scriere; daca nu exista eroare se va incepe scrierea in fisier a rezultatului si afisarea pe ecran a lui
eroare:
	push offset err ;aici se afiseaza eroarea si se trece la finalul codului
	push offset format2
	call printf
	jmp end_of_time
	
start_scriere:
	push offset afisare ; aici deschidem fisierul de iesire in mod scriere
	push offset format2
	call printf
	;apelam fopen
	push offset mode_w
	push offset filename3
	call fopen
	add esp, 8
	mov esi, eax ;salvam pointer-ul la fisier
	
	;contoarele iar se reseteaza	
	mov ecx, 0
	mov contor1, 0
	mov contor2, 0
	mov EDI, 0
	;punem pe stiva parametrii pentru fprintf
	

bucla_scriere:

	; aici incepe sa se scrie in fisier si sa va scrie fiecare element in consola
	mov eax, 0
	mov eax, REZ[EDI]
	push eax
	push offset format
	
	mov ecx, A_coloane
	sub ecx, contor2
	jecxz inchidere_fisier_3
	;se inchide fiecrul dupa ce am scris ultimul numar
	
	call printf
	add esp, 8
	
	;aici se incepe scrierea in fisier a elementelor
	mov eax, 0
	mov eax, REZ[EDI]
	push eax
	push offset format
	push esi
	call fprintf
	add esp,12
	
	add edi, 4

	
	;aici se face verificarea daca suntem sau nu la capatul liniei
	add contor1, 1
	mov ecx, A_coloane 
	sub ecx, contor1	
	jecxz liniea_fin
	jmp bucla_scriere
liniea_fin:
	; algoritmul pentru capatul liniei
	push offset linie
	push offset format2
	call printf
	add esp, 8
	push offset linie
	push offset format2
	push esi
	call fprintf
	add esp,12
	mov contor1, 0
	add contor2, 1
	; se repeta daca nu am scirs ultima linie
	jmp bucla_scriere
	
	
inchidere_fisier_3:
	add esp, 16 ;curatam stiva de la fprintf
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4
	;inchidem fisierul si dam un rand liber
	push offset rand_free 
	call printf
	add esp, 4
	
	
end_of_time:
	;terminarea completa a programului
	push 0
	call exit
end start
