.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern printf: proc
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
mode_rb db "r", 0
format db "%d ", 0
format2 db "%s", 0
buffer dd 0
A_coloane dd 0
A_linii dd 0
B_coloane dd 0
B_linii dd 0
nr dw 0
contor1 dd 0
contor2 dd 0
m1 dd 0,0,0,0,0,0,0,0,0,0,0,0
m2 dd 7,8,9
   dd 10,11,12	

msg db "Calculator pentru matrici!", 10, 10, "Se afiseaza matricile:", 10, 0 ;
msgpos db "Sunt posibile urmatoarele operatii: A+B (adunare), aA (inmultire cu scalar), A-B scadere), AT (Transpusa).", 10, 0 ;
msgnpos db "Eroare: nu sunt posibile operatii, cu excetia calculului transpusei AT", 10, 0 ;
msgposdet db "Este posibil si calculul determinantului Det(a).", 10, 0 ;
msgnposdet db "Avertizare: indicii matricei sunt prea mari pentrua fi calculat deterinantul cu acest program!", 10, 0 ;
op db "Spune o operatie:", 0 ;
operatie db 0;
rand_free db 10, 10, 10, 0 ;

.code
start:
	push offset msg ;argumentele unei functii se pun pe stiva, in ordine inversa
	call printf
	;conventia de apel la printf e cdecl, ceea ce inseamna ca cine apeleaza, trebuie sa curete stiva
	add esp, 4 
	mov ebx, [m1] ;pentru parcurgerea liniilor
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
	
	
	
	
	push offset buffer
	push offset format
	push esi ;stream
bucla_citire_1:
	call fscanf
	test eax, eax
	js inchidere_fisier_1
	xor eax, eax ;facem eax sa fie 0
	
	mov eax, buffer

	push eax
	push offset format
	call printf
	add esp, 8
	mov EBX, buffer
	add EBX, 4
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
	
		
	
	push offset buffer
	push offset format
	push esi ;stream
bucla_citire_2:
	call fscanf
	test eax, eax
	js inchidere_fisier_2
	xor eax, eax ;facem eax sa fie 0
	
	mov eax, buffer
	;mov matrice_A[ecx][edx], eax

	push eax
	push offset format
	call printf
	add esp, 8
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
	push offset format2
	call scanf
	add esp, 8
	
	mov EBX, 0
	push offset operatie
	call printf
	add esp, 16
	
	
	
final:
	push 0
	call exit
end start
