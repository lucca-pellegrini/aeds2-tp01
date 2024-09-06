#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

#define TAM_BUF (1 << 10)

bool parens_correto(const char *texto);
int strcmp2(const char *str, const char *cmp);

int main(void)
{
	char input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Lê uma linha da entrada até achar “FIM”. Ignora newlines.
	while (scanf(" %1023[^\n\r]s", input) != EOF &&
	       strcmp2(input, "FIM") != 0) {
		puts(parens_correto(input) ? "correto" : "incorreto");
	}

	return 0;
}

bool parens_correto(const char *texto)
{
	int eq = 0; // Equilíbrio de parênteses.

	// Itera até terminar a string, ou até fechar parênteses inexistentes.
	for (int i = 0; texto[i] != '\0' && eq >= 0; ++i) {
		switch (texto[i]) {
		case '(':
			++eq;
			break;
		case ')':
			--eq;
			break;
		}
	}

	return eq == 0; // Retorna se `eq` for zero.
}

int strcmp2(const char *str, const char *cmp)
{
	while (*str && (*str == *cmp)) {
		str++;
		cmp++;
	}
	return *str - *cmp;
}
