#include <locale.h>
#include <stdbool.h>
#include <stdio.h>
#include <wchar.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

bool is_palindromo(wchar_t *str);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	setlocale(LC_ALL, "C.UTF-8");

	// Lê uma linha da entrada. Ignora newlines.
	wscanf(L"%l[^\n\r]s", input);

	while (wcscmp(input, L"FIM") != 0) {
		puts(is_palindromo(input) ? "SIM" : "NAO"); // Printa resposta.
		wscanf(L" %l[^\n\r]s", input); // Lê a próxima linha.
	}

	return 0;
}

bool is_palindromo(wchar_t *str)
{
	bool resposta = true; // Valor de retorno.
	// Índices dos limites esquerdo e direito da string.
	int esq = 0;
	int dir = wcslen(str) - 1;

	// Itera sobre a string dos dois lados comparando caracteres um por um.
	while (esq < dir)
		if (str[esq++] != str[dir--])
			resposta = false;

	return resposta;
}
