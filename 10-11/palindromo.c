#include <locale.h>
#include <stdbool.h>
#include <stdio.h>
#include <wchar.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

bool is_palindromo(wchar_t *str, int esq, int dir);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	setlocale(LC_ALL, "C.UTF-8");

	// Lê uma linha da entrada. Ignora newlines.
	wscanf(L"%l[^\n\r]s", input);

	while (wcscmp(input, L"FIM") != 0) {
		puts(is_palindromo(input, 0, wcslen(input) - 1) ? "SIM" :
								  "NAO");
		wscanf(L" %l[^\n\r]s", input); // Lê a próxima linha.
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
