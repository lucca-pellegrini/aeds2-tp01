/* Nesse programa, foi necessário usar o tipo de dado “wide-character”
 * (wchar_t), uma vez que o arquivo de entrada usado pelo Verde tem caracteres
 * compostos por mais de 8 bits.*/

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Configurações de localização, para ler caracteres não-ASCII corretamente.
#include <locale.h>

// Inclusão de tipo de dado wide-character (veja `man wchar.h` no Linux).
#include <wchar.h>

bool is_palindromo(wchar_t *str, int len);
int remover_newline(wchar_t *restrict str);

int main(void)
{
	// Ajusta o locale do programa para português em UTF-8.
	setlocale(LC_ALL, "pt_BR.UTF-8");

	char *str = NULL; // String de entrada.
	wchar_t *wstr = NULL; // String em tipo “wide-character”.
	size_t bufsz = 0; // Tamanho do buffer da string.
	int len; // Quantidade de caracteres na string.

	getline(&str, &bufsz, stdin); // Lê uma linha da input.

	// Converte a string normal para a string “wide”.
	len = mbstowcs(NULL, str, 0) + 1; // Computa o tamanho da nova string.
	wstr = malloc(len * sizeof(wchar_t)); // Aloca memória para ela.
	mbstowcs(wstr, str, len); // Faz a conversão.

	len = remover_newline(wstr); // Remove os newlines.

	while (wcscmp(wstr, L"FIM")) {
		puts(is_palindromo(wstr, len) ? "SIM" : "NAO");

		getline(&str, &bufsz, stdin); // Lê a próxima linha.

		// Faz a conversão da nova linha e remove os newlines.
		len = mbstowcs(NULL, str, 0) + 1;
		wstr = realloc(wstr, len * sizeof(wchar_t));
		mbstowcs(wstr, str, len);
		len = remover_newline(wstr);
	}

	// Libera a memória e termina.
	free(str);
	free(wstr);
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
