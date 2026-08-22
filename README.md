# NAUTILUS — Sistema de Gestão de Frota Náutica

Trabalho prático da unidade curricular de **Programação Orientada por Objetos (POO)** — Grupo 3.

Sistema de consola em **Java** para gestão de uma frota náutica de um porto operacional marítimo: marinheiros, embarcações (Barcos de Patrulha, Lanchas Rápidas e Navios de Suporte) e as suas missões nas quatro zonas de atuação (Norte, Sul, Este, Oeste).

## Estrutura do Projeto

```
projeto_poo/
├── pom.xml                          # build Maven
├── .gitignore
├── README.md
├── docs/
│   └── proj_poo_rel_grupo3.pdf      # relatório do projeto
└── src/
    └── main/
        └── java/
            └── com/
                └── mycompany/
                    └── projeto_poo/
                        ├── Projeto_Poo.java     # classe principal (main)
                        ├── Menu.java
                        ├── InputReader.java
                        ├── Helper.java
                        ├── FileHandler.java
                        ├── DadosGlobais.java
                        ├── Porto.java
                        ├── Embarcacao.java
                        ├── BarcoPatrulha.java
                        ├── LanchaRapida.java
                        ├── NavioSuporte.java
                        ├── Motor.java
                        ├── Radar.java
                        ├── Marinheiro.java
                        ├── PATENTE.java
                        ├── COMBUSTIVEL.java
                        └── ZONA.java
```

Esta estrutura segue a convenção standard Java/Maven (`src/main/java/<pacote>`), em que a árvore de diretórios espelha o pacote `com.mycompany.projeto_poo` declarado em todas as classes. O relatório do projeto foi movido para `docs/`, separado do código-fonte.

## Como compilar e executar

**Com Maven:**
```bash
mvn compile exec:java -Dexec.mainClass="com.mycompany.projeto_poo.Projeto_Poo"
```
ou para gerar o `.jar` executável:
```bash
mvn package
java -jar target/projeto_poo.jar
```

**Sem Maven (javac/java diretamente):**
```bash
javac -d build -encoding UTF-8 src/main/java/com/mycompany/projeto_poo/*.java
java -cp build com.mycompany.projeto_poo.Projeto_Poo
```

**No NetBeans/IntelliJ/VS Code:** abrir a pasta `projeto_poo/` como projeto Maven (`pom.xml`) — as IDEs reconhecem automaticamente a estrutura `src/main/java`.

## Classe principal

`Projeto_Poo.java` contém o `main`, que apresenta o menu inicial com os dois modos de funcionamento (Manutenção e Utilização) descritos no enunciado.

## Ficheiros de dados

Os ficheiros gerados/lidos em runtime pelo sistema (persistência de estado, exportações) não devem ser versionados — estão excluídos via `.gitignore`.
