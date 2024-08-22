#include <ctype.h>
#include <stdio.h>
#include <string.h>

#define TAM_BUF (1 << 10)

unsigned num_maiusculas(const char *texto, size_t tam);
unsigned strlen2(char *str);
int strcmp2(char *str, char *cmp);

int main(void)
{
	char input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Lê uma linha da entrada até achar “FIM”. Ignora newlines.
	while (scanf(" %1023[^\n\r]s", input) != EOF &&
	       strcmp2(input, "FIM") != 0) {
		printf("%u\n", num_maiusculas(input, strlen2(input)));
	}

	return 0;
}

unsigned num_maiusculas(const char *texto, size_t tam)
{
	unsigned ret; // Valor de retorno.

	if (tam == 0) {
		ret = 0;
	} else {
		// Chamada recursiva na substring, excluindo o primeiro item.
		ret = num_maiusculas(texto + 1, tam - 1);
		if (isupper(*texto)) // Verifica se é maiúscula.
			++ret;
	}

	return ret;
}

unsigned strlen2(char *str) // Pois não se usa strlen() nos TPs.
{
	unsigned count;
	for (count = 0; str[count] != '\0'; ++count)
		; // Loop vazio.
	return count;
}

int strcmp2(char *str, char *cmp)
{
	int ret = 0;
	int min_len = (strlen2(str) > strlen2(cmp)) ? strlen2(cmp) :
						      strlen2(str);

	for (int i = min_len - 1; i > 0; --i)
		if (str[i] != cmp[i])
			ret = str[i] - cmp[i];

	return ret;
}
