# Arquivos que queremos compilar.
JAVABIN   := $(JAVACLASS).class
BIN       := $(CBIN) $(JAVABIN)

# Arquivos com entrada e saída dos programas.
INPUT  := pub.in
OUTPUT := pub.out
TEST   := teste.out

# Compilador de C e seus parâmetros.
CFLAGS  := -Werror -Wall -Wextra -pedantic -O3 -g --debug --std=c99
LDFLAGS := -lm

# Java, compilador de Java, e seus parâmetros.
JAVA       := java
JAVAC      := javac
JAVAFLAGS  :=
JAVACFLAGS :=

# Como compilar todos os objetos (alvo padrão).
all: $(BIN)

# Regra para compilação de classes Java (compilação de C já está implícita).
%.class: %.java
	$(JAVAC) $(JAVACFLAGS) $<

# Alvos que não são arquivos.
.PHONY: all clean test testc testjava

testc: $(CBIN)
	$(CBIN) < $(INPUT) > $(TEST)
	@colordiff --report-identical-files --strip-trailing-cr $(OUTPUT) $(TEST)

testjava: $(JAVABIN)
	$(JAVA) $(JAVACLASS) < $(INPUT) > $(TEST)
	@colordiff --report-identical-files --strip-trailing-cr $(OUTPUT) $(TEST)

# Como excluir artefatos de compilação.
clean:
	$(RM) $(BIN) $(TEST)
