#include <locale.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <wchar.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

bool is_palindromo(wchar_t *str, int esq, int dir);
unsigned wcslen2(wchar_t *str);
int strcmp2(const char *str, const char *cmp);

int main(void)
{
	char input[TAM_BUF]; // Buffer fixo de texto para a entrada.
	wchar_t winput[TAM_BUF]; // Buffer fixo para a string convertida.

	// Ajusta o locale do programa para UTF-8 (somente Linux).
	setlocale(LC_ALL, "C.UTF-8");

	// Lê uma linha da entrada. Ignora newlines.
	scanf(" %[^\n\r]s", input);

	while (strcmp2(input, "FIM") != 0) {
		mbstowcs(winput, input, TAM_BUF); // Converte a string.
		puts(is_palindromo(winput, 0, wcslen2(winput) - 1) ? "SIM" :
								     "NAO");
		scanf(" %[^\n\r]s", input); // Lê a próxima linha.
	}

	return 0;
}

bool is_palindromo(wchar_t *str, int esq, int dir)
{
	bool resposta; // Valor de retorno.

	if (esq >= dir)
		resposta = true;
	else if (str[esq] != str[dir])
		resposta = false;
	else
		resposta = is_palindromo(str, esq + 1, dir - 1);

	return resposta;
}

unsigned wcslen2(wchar_t *str) // Pois não se usa wcslen() nos TPs.
{
	unsigned count;
	for (count = 0; str[count] != L'\0'; ++count)
		; // Loop vazio.
	return count;
}

int strcmp2(const char *str, const char *cmp)
{
	while (*str && (*str == *cmp)) {
		str++;
		cmp++;
	}
	return *str - *cmp;
}
