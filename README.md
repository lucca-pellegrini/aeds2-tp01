# Trabalho Prático 1

Aqui se encontram todas as questões do primeiro trabalho prático de Algoritmos
e Estruturas de Dados 2 (1610100) de 2024/2.

## Resultados

A maioria das implementações tem notas de 100% no Verde, com as seguintes
exceções:

  11. Essa questão, com nota de 100% no meu Linux, tem apenas 98,65% no Verde,
      pois as configurações de localização no Verde — aparentemente — fazem com
      que os caracteres especiais sejam indevidamente desconsiderados.
  13. Não consegui fazer essa implementação funcionar de forma recursiva, pela
      complexidade do algoritmo.

## Como testar

É possível executar todos os testes, tendo instalado e configurado um
[compilador de C](https://clang.llvm.org/), um
[kit de desenvolvimento Java](https://openjdk.org/), e
[GNU Make](https://www.gnu.org/software/make/). Basta clonar esse repositório e,
na raiz, executar o trecho abaixo num interpretador de comandos compatível (como
[Bash](https://www.gnu.org/software/bash/) ou [Zsh](https://www.zsh.org/)):

```bash
for d in "$PWD"/*/; do
    cd "$d"
    make clean test || break
done
```

Dependendo do seu sistema, pode ser nececssário instalar
[GNU Diffutils](https://www.gnu.org/software/diffutils) e, opcionalmente,
[colordiff](https://www.colordiff.org/).

Para cada teste que tiver sucesso, você verá uma linha semelhante a essa:
```txt
Files pub.out and teste.out are identical
```

Se algum teste falhar, a execução terminará imediatamente com a seguinte linha
(ou semelhante):
```txt
make: *** [../config.mk:37: testjava] Error 1
```
