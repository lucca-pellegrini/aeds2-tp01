#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool is_palindromo(char *str, int len);
int remove_space(char *restrict str);

int main(void)
{
	char *str = NULL;
	size_t bufsz;
	int len;

	getline(&str, &bufsz, stdin); // Lê uma linha inteira
	len = remove_space(str);
	// puts(str);

	while (strcmp(str, "FIM")) {
		puts(is_palindromo(str, len) ? "SIM" : "NAO");

		getline(&str, &bufsz, stdin); // Lê a próxima linha
		len = remove_space(str);
		// puts(str);
	}

	free(str);
	return 0;
}

bool is_palindromo(char *str, int len)
{
	int esq = 0;
	int dir = len - 1;
	bool ret = true;

	while (esq < dir)
		if (str[esq++] != str[dir--])
			ret = false;
	return ret;
}

int remove_space(char *restrict str)
{
	int len = 0;

	for (int i = 0; str[i] != '\0'; ++i) {
		if (!isspace(str[i]) || str[i] == ' ') {
			str[len++] = str[i];
		}
	}
	str[len] = '\0';

	return len;
}
