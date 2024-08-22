#include <locale.h>
#include <stdbool.h>
#include <stdio.h>
#include <wchar.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

bool is_palindromo(wchar_t *str);
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
	int dir = wcslen2(str) - 1;

	// Itera sobre a string dos dois lados comparando caracteres um por um.
	while (esq < dir)
		if (str[esq++] != str[dir--])
			resposta = false;

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
