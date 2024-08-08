/* Nesse programa, foi necessário usar o tipo de dado “wide-character”
 * (wchar_t), uma vez que o arquivo de entrada usado pelo Verde tem caracteres
 * compostos por mais de 8 bits.*/

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

// Configurações de localização, para ler caracteres não-ASCII corretamente.
#include <locale.h>

// Inclusão de tipo de dado wide-character (veja `man wchar.h` no Linux).
#include <wchar.h>

#define TAM_BUF (1 << 10) // Tamanho do buffer de texto.

bool is_palindromo(wchar_t *str, int len);
int remover_newline(wchar_t *restrict str);

int main(void)
{
	// Ajusta o locale do programa para português em UTF-8.
	setlocale(LC_ALL, "pt_BR.UTF-8");

	wchar_t input[TAM_BUF]; // Buffer fixo para a string de entrada.
	int len; // Quantidade de caracteres na string.

	while (fgetws(input, TAM_BUF, stdin) != NULL) {
		len = remover_newline(input); // Remove os newlines.

		if (wcscmp(input, L"FIM") == 0)
			break;

		puts(is_palindromo(input, len) ? "SIM" : "NAO");
	}

	return 0;
}

bool is_palindromo(wchar_t *str, int len)
{
	bool resposta = true; // Valor de retorno.
	// Índices dos limites esquerdo e direito da string.
	int esq = 0;
	int dir = len - 1;

	// Itera sobre a string dos dois lados comparando caracteres um por um.
	while (esq < dir)
		if (str[esq++] != str[dir--])
			resposta = false;

	return resposta;
}

// Remove da string quaisquer delimitadores de linha (`\n` e `\r`).
int remover_newline(wchar_t *restrict str)
{
	int len = 0;

	// Itera sobre a string e copia os caracteres que não são newlines.
	for (int i = 0; str[i] != L'\0'; ++i)
		if (str[i] != L'\n' && str[i] != L'\r')
			str[len++] = str[i];
	str[len] = L'\0'; // Insere o terminador na posição final.

	return len;
}
