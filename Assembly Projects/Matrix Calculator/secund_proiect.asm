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

mode_rb db "r", 0
mode_w db "w", 0
format db "%d ", 0
formats db "%d", 0
format2 db "%s", 0
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
	

msg db "Calculator pentru matrici!", 10, 10, "Se afiseaza matricile:", 10, 0 ;
msgpos db "Sunt posibile urmatoarele operatii: 1->A+B (adunare), 2->A-B (scadere), 3->aA (inmultire cu scalar), 4->AT (Transpusa).", 10, 0 ;
msgnpos db "Eroare: nu sunt posibile operatii, cu excetia calculului transpusei AT", 10, 0 ;
msgposdet db "Este posibil si calculul determinantului 5->det(A).", 10, 0 ;
msgnposdet db "Avertizare: indicii matricei sunt prea mari pentrua fi calculat deterinantul cu acest program!", 10, 0 ;
op db "Operatiile ca numarul dinainte prescurtarilor. Orice alta metoda va fi declarata ca eroare.", 10, "Spune o operatie(de ex '1' pentru adunare):", 0 ;
err db "Eroare: nu exisat operatia introdusa!", 10, 0 ;

;determinant dd "det(A)", 0;

operatie dd 0;
t dd 0;
t2 dd 4;
rand_free db 10, 10, 10, 0 ;
linie db 10, 0 ;
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
	
	
	mov ecx, 0
	mov contor1, 0
	mov contor2, 0
	mov EDI, 0
	
	
	push offset buffer
	push offset format
	push esi ;stream
bucla_citire_1:
	call fscanf
	test eax, eax
	js inchidere_fisier_1
	xor eax, eax ;facem eax sa fie 0
	mov eax, buffer
	
	mov A[EDI],eax
	add edi, 4
	
	push eax
	push offset format
	call printf
	add esp, 8
	
	
	add contor1, 1
	mov ecx, A_coloane 
	sub ecx, contor1	
	jecxz liniea
	jmp bucla_citire_1
liniea:
	push offset linie
	push offset format2
	call printf
	add esp, 8
	mov contor1, 0

	jmp bucla_citire_1
	
	
inchidere_fisier_1:
	add esp, 16 ;curatam stiva de la fscanf
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4
	
	push offset rand_free 
	call printf
	add esp, 4
	
	
	
start_citire2:
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
	
	
posibilitati:
	mov ecx, A_coloane 
	sub ecx, B_coloane	
	jecxz istrue
isfalse:
	push offset msgnpos 
	call printf
	add esp, 4
	jmp  nextinstr
istrue:
	push offset msgpos 
	call printf
	add esp, 4
	jmp  nextinstr

	
nextinstr:

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
	
	push offset op
	call printf
	add esp, 4
	
	push offset operatie
	push offset formats
	call scanf
	add esp, 8
	
	
	mov esi, 0

	
	mov ecx, 1 
	sub ecx, operatie	
	jecxz suma
	
	mov ecx, 2 
	sub ecx, operatie	
	jecxz scadere
	
	add eror, 1
	jmp final
	
suma:
	mov edi, 0
	mov contor1, 0
plus:
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final
	add contor1, 1
	mov eax, A[EDI]
	add eax, B[EDI]
	mov REZ[EDI], eax
	add EDI, 4
	jmp plus
		
	

scadere:
	mov edi, 0
	mov contor1, 0
minus:
	
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


	
	mov ecx, 3 
	sub ecx, operatie	
	jecxz scalar
	add eror, 1
	
	jmp final2

scalar:
	push offset scalp
	call printf
	add esp, 4
	
	push offset scal
	push offset formats
	call scanf
	add esp, 8
	
	mov edi, 0
	mov contor1, 0
inm:
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final2
	add contor1, 1
	mov eax, A[EDI]
	mul scal
	mov REZ[EDI], eax
	add EDI, 4
	jmp inm	
	
final2:
	mov edi, 0
	
	mov ecx, 4 
	sub ecx, operatie	
	jecxz transpusa
	add eror, 1
	jmp final3
	
	
transpusa:	
	mov edi, 0
	mov contor1, 0
	mov i, 0
	mov j, 1
tr:
	mov ecx, nr 
	sub ecx, contor1	
	jecxz final3
	
	add i, 1
	
	mov eax, i
	sub eax, 1	
	mul A_coloane
	add eax, j
	mul t2
	sub eax, t2
	mov t, eax
	
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
	mov i, 0
	add j, 1
	jmp tr	
	
	
final3:
	
	mov ecx, 5
	sub ecx, operatie	
	jecxz deter
	add eror, 1
	jmp final4
deter:
	mov edi, 0
	mov ecx, A_coloane
	sub ecx, 3	
	jecxz deter3

deter2:	
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
	jmp final4

deter3:
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
	mov REZ[0], eax
	


final4:	
	mov edi, 0
	
	mov ecx, 4 
	sub ecx, eror
	jecxz eroare
	jmp start_scriere
eroare:
	push offset err ;argumentele unei functii se pun pe stiva, in ordine inversa
	push offset format2
	call printf
	jmp end_of_time
	
start_scriere:
	;apelam fopen
	push offset mode_w
	push offset filename3
	call fopen
	add esp, 8
	mov esi, eax ;salvam pointer-ul la fisier
	
		
	mov ecx, 0
	mov contor1, 0
	mov contor2, 0
	mov EDI, 0
	;punem pe stiva parametrii pentru fprintf
	

bucla_scriere:
	mov ecx, A_coloane
	sub ecx, contor2
	jecxz inchidere_fisier_3
	
	push eax
	push offset format
	call printf
	add esp, 8
	
	mov eax, 0
	mov eax, REZ[EDI]
	push eax
	push offset format
	push esi
	call fprintf
	add esp,12
	
	add edi, 4
	

	
	
	add contor1, 1
	mov ecx, A_coloane 
	sub ecx, contor1	
	jecxz liniea_fin
	jmp bucla_scriere
liniea_fin:
	push offset linie
	push offset format2
	call printf
	add esp, 8
	mov contor1, 0
	add contor2, 1

	jmp bucla_scriere
	
	
inchidere_fisier_3:
	add esp, 16 ;curatam stiva de la fprintf
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4
	
	push offset rand_free 
	call printf
	add esp, 4
	
	
end_of_time:
	push 0
	call exit
end start
