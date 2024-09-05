/* Implementação em C da primeira questão do 4º laboratório. Escrevi esse
 * arquivo porque achei mais fácil do que começar em Java, mas esse arquivo não
 * será enviado no Verde. */

#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

void ordena_insercao(int *restrict const vec, const size_t tam, const int mod);
bool compara(const int a, const int b, const int mod);

int main(void)
{
	int *vec = NULL; // Ponteiro ao vetor.
	size_t num; // Tamanho do vetor.
	int mod; // Módulo para comparação.

	// Lê enquanto houver entradas, ou até entrar “0 0”.
	while (scanf("%ju%d", &num, &mod) != EOF && (num || mod)) {
		// Mostra os valores entrados.
		printf("%ju %d\n", num, mod);

		// (Re)aloca memória para o vetor e verifica erro com `errno`.
		if ((vec = realloc(vec, num * sizeof(int))) == NULL) {
			int errsv = errno;
			perror("Falha ao (re)alocar memória");
			return errsv;
		}

		for (size_t i = 0; i < num; ++i) // Lê os N números.
			scanf("%d", vec + i);

		ordena_insercao(vec, num, mod);

		// Mostra os resultados.
		for (size_t i = 0; i < num; ++i)
			printf("%d\n", vec[i]);

		// Descomente abaixo para mostrar o vetor em uma linha:
		// putchar('[');
		// for (size_t i = 0; i < num; ++i)
		// 	printf(" %d", vec[i]);
		// puts(" ]");
	}

	// Mostra os últimos valores entrados.
	printf("%ju %d\n", num, mod);

	free(vec); // Libera a memória alocada.
	return EXIT_SUCCESS;
}

void ordena_insercao(int *restrict const vec, const size_t tam, const int mod)
{
	for (size_t i = 1; i < tam; ++i) { // Itera do segundo ao último item.
		int n = vec[i]; // Salva o valor na posição atual.
		size_t j;

		// Não é possível fazer `j >= 0` pois `size_t` é sempre
		// positivo. Portanto, verificamos se há underflow.
		for (j = i - 1; j != SIZE_MAX && compara(vec[j], n, mod); --j)
			vec[j + 1] = vec[j]; // Desloca para a direita.

		vec[j + 1] = n; // Insere o valor salvo na posição correta.
	}
}

// Compara dois elementos `a` e `b` usando um módulo `mod`. Retorna `true` se
// `a` deve aparecer depois de `b`.
bool compara(const int a, const int b, const int mod)
{
	bool ret;
	const int mod_a = a % mod;
	const int mod_b = b % mod;
	const bool par_a = (a % 2) == 0;
	const bool par_b = (b % 2) == 0;

	// Realiza as verificações dadas pelo enunciado.
	// Ordena segundo os módulos, se forem diferentes entre os elementos.
	if (mod_a > mod_b) {
		ret = true;
	} else if (mod_a < mod_b) {
		ret = false;
	} else { // Caso contrário, há empate entre `mod_a` e `mod_b`
		// Verifica se os elementos têm paridade diferente.
		if (!par_a && par_b)
			ret = false;
		else if (par_a && !par_b)
			ret = true;
		// Se têm paridade igual, ordena-se ímpares em decrescente...
		else if (!par_a && !par_b)
			ret = a < b;
		else // ... e pares em ordem crescente.
			ret = a > b;
	}

	return ret;
}
