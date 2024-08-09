#include <stdio.h>

int main(void)
{
	int input;
	scanf("%d", &input);

	while (input) {
		puts((input % 2) ? "I" : "P");
		scanf("%d", &input);
	}

	return 0;
}
