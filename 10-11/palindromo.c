#include <locale.h>
#include <stdbool.h>
#include <stdio.h>
#include <wchar.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

bool is_palindromo(wchar_t *str, int esq, int dir);
unsigned wcslen2(wchar_t *str);
int wcscmp2(wchar_t *str, wchar_t *cmp);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	setlocale(LC_ALL, "C.UTF-8");

	// Lê uma linha da entrada. Ignora newlines.
	wscanf(L"%l[^\n\r]s", input);

	while (wcscmp2(input, L"FIM") != 0) {
		puts(is_palindromo(input, 0, wcslen2(input) - 1) ? "SIM" :
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

unsigned wcslen2(wchar_t *str) // Pois não se usa wcslen() nos TPs.
{
	unsigned count;
	for (count = 0; str[count] != L'\0'; ++count)
		; // Loop vazio.
	return count;
}

int wcscmp2(wchar_t *str, wchar_t *cmp)
{
	int ret = 0;
	int min_len = (wcslen2(str) > wcslen2(cmp)) ? wcslen2(cmp) :
						      wcslen2(str);

	for (int i = min_len - 1; i > 0; --i)
		if (str[i] != cmp[i])
			ret = str[i] - cmp[i];

	return ret;
}
