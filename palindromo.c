/* Nesse programa, foi necessário usar o tipo de dado “wide-character”
 * (wchar_t), uma vez que o arquivo de entrada usado pelo Verde tem caracteres
 * compostos por mais de 8 bits.*/

#include <stdbool.h>
#include <stdio.h>

// Configurações de localização, para ler caracteres não-ASCII corretamente.
#include <locale.h>

// Inclusão de tipo de dado wide-character (veja `man wchar.h` no Linux).
#include <wchar.h>

#define TAM_BUF (1 << 10) // Tamanho do buffer de texto (1KB).

bool is_palindromo(wchar_t *str);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo para a string de entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	setlocale(LC_ALL, "pt_BR.UTF-8");

	wscanf(L"%l[^\n\r]s", input); // Lê uma linha da entrada.

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
