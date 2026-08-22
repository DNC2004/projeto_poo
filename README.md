# NAUTILUS — Sistema de Gestão de Frota Náutica

Trabalho prático da unidade curricular de **Programação Orientada por Objetos (POO)**, a implementar em **Java**, com interface textual em consola.

## Descrição do Projeto

O NAUTILUS é um sistema de gestão de uma frota náutica para um porto operacional marítimo. O sistema permite gerir **marinheiros**, **embarcações** e o **porto**, controlando a ativação de embarcações para missões nas diferentes zonas de atuação (Norte, Sul, Este, Oeste), bem como o registo, listagem e monitorização de todas as entidades envolvidas.

## Entidades Principais

### Marinheiros
- Atributos: `id` (sequencial, atribuído automaticamente), `nome`, `data de nascimento`, `patente` (Oficial, Sargento ou Praça)
- Podem ser alocados a uma embarcação
- Não pode haver Oficiais com menos de 35 anos
- Listagem por ordem crescente de nome (apresentações), por id (crescente) e por data de nascimento (decrescente, com indicação da idade)

### Embarcações
- Atributos: `id` (sequencial), `nome` (único, não pode ser nulo), `marca`, `modelo`, `data de fabrico`, `motor(es)` (combustível, cilindrada, potência)
- Associadas a uma zona (Norte, Sul, Este ou Oeste) — não pode haver associação a duas zonas diferentes
- Não podem existir duas embarcações com o mesmo nome
- Listagem por ordem crescente de id (por zona), por marca (crescente) e por ano de fabrico (decrescente, com idade da embarcação)

**Tipos de embarcação:**

| Tipo | Motores | Tripulação mínima | Equipamento |
|---|---|---|---|
| Barco de Patrulha | 1 | 2 a 4 (obrigatório 1 oficial) | Holofote, radar |
| Lancha Rápida | 2 a 4 | 2 a 4 (obrigatório 1 sargento) | Holofote |
| Navio de Suporte | 2 (>25000cv cada) | 4 a 10 (obrigatório 1 oficial e 1 sargento) | Capacidade de carga, camas hospitalares, holofote, botes salva-vidas, radar |

Para serem ativadas, as embarcações têm de ter a tripulação mínima alocada.

### Porto
- Atributos: `nome`, conjunto de embarcações associadas, número total de missões ordenadas
- Dá ordens de ativação de embarcações para as diferentes zonas
- Tem um radar que deteta embarcações atracadas e na zona adjacente

## Missões por Tipo de Embarcação

- **Barcos de Patrulha** — procura e salvamento. Ao terminar, o barco volta ao porto e fica atracado.
- **Lanchas Rápidas** — perseguição e captura. Quando uma termina, todas as lanchas na mesma zona voltam ao porto.
- **Navios de Suporte** — apoio à zona.

Durante a missão, os equipamentos (holofote/radar) ficam ligados; o radar só deteta embarcações da mesma zona se estiver ligado.

## Regras e Restrições Gerais

- Não é possível registar duas vezes a mesma embarcação ou marinheiro
- Uma embarcação não pode estar registada em duas zonas diferentes
- Deve ser possível gravar/recuperar todo o estado do sistema em ficheiro (persistência)
- Deve ser possível exportar a lista de embarcações para um ficheiro de texto formatado
- Função **RECOVER**: repõe todas as entidades com os últimos valores gravados

## Modos de Funcionamento

- **Modo de Manutenção** — criação, edição e remoção de entidades (configuração do sistema)
- **Modo de Utilização** — operações do uso diário do sistema

## Requisitos de Implementação

- Linguagem: **Java**, em modo consola
- Conceitos de POO a aplicar: encapsulamento, classes e objetos, composição, herança, classes abstratas, interfaces, polimorfismo e serialização
- Sugestão: classes de coleção como heranças de coleções parametrizáveis (ex.: `public class ColecaoEmbarcacoes extends ...`)
- Convenções de código: `camelCase` para variáveis/atributos/métodos, `PascalCase` para classes/interfaces, maiúsculas para constantes/enumerados, sem abreviaturas nem `_` (exceto em constantes)

## Avaliação

- Avaliação incide sobre qualidade do software, coesão, acoplamento, desenho orientado por responsabilidade e conhecimento da linguagem Java
- São premiadas a facilidade de utilização, apresentação, imaginação e criatividade
- Defesa/discussão obrigatória do projeto
- Projetos serão submetidos a deteção automática de cópias/IA; fraude confirmada resulta em anulação
