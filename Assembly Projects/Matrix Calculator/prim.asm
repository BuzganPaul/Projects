.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern printf: proc
extern fread: proc
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
mode_r db "rb", 0
format db "%c ", 0
buffer db 0
msg db "Aici avem un calculator pentru matrici! Se vor afisa marimile matricilor si continuturile lor:", 10, 0 ;
msgerr db "Eroare: codul se va inchide!", 10, 0 ;

.code
start:
	push offset msg ;argumentele unei functii se pun pe stiva, in ordine inversa
	call printf
	;conventia de apel la printf e cdecl, ceea ce inseamna ca cine apeleaza, trebuie sa curete stiva
	add esp, 4 ;

start_citire:
	;apelam fopen
	push offset mode_r
	push offset filename
	call fopen
	add esp, 8
	mov esi, eax ;salvam pointer-ul la fisier
	
	;punem pe stiva parametrii pentru fread
	push esi ;stream
	push 1 ;count
	push 1 ;size
	push offset buffer
bucla_citire:
	call fread
	test eax, eax
	jz inchidere_fisier
	xor eax, eax ;facem eax sa fie 0
	mov al, buffer
	push eax
	push offset format
	call printf
	add esp, 8
	jmp bucla_citire
	
inchidere_fisier:
	add esp, 16 ;curatam stiva de la fread
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4
	
start_citire2:	
	;apelam fopen
	push offset mode_r
	push offset filename2
	call fopen
	add esp, 8
	mov esi, eax ;salvam pointer-ul la fisier
	
	;punem pe stiva parametrii pentru fread
	push esi ;stream
	push 1 ;count
	push 1 ;size
	push offset buffer
bucla_citire2:
	call fread
	test eax, eax
	jz inchidere_fisier2
	xor eax, eax ;facem eax sa fie 0
	mov al, buffer
	push eax
	push offset format
	call printf
	add esp, 8
	jmp bucla_citire2
	
inchidere_fisier2:
	add esp, 16 ;curatam stiva de la fread
	;apelam fclose
	push esi ;stream
	call fclose
	add esp, 4

	
	
	

	push 0
	call exit
end start