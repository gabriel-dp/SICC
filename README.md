# Sistema Integrado de Componentes Curriculares (SICC)

```java
final String ESTUDANTES[] = { "Gabriel de Paula", "Gabriel Souza", "Henrique Azevedo" };
final String DISCIPLINA = "Análise e Projeto de Software";
final String PROFESSOR = "Matheus Viana";
final String SEMESTRE = "2023.2";
```

O  Software  tem  como  funcionalidade  possibilitar  que  estudantes  da  universidade  que  adotar  o SICC selecionem as disciplinas que pretendem cursar no semestre, emitindo um relatório de quais são as  disciplinas  escolhidas  para  que  as  solicitações  sejam  analisadas  pela  coordenação  de  cada  curso adicionado no sistema.

&nbsp;

## 📂 Estrutura do projeto

- Padrão arquitetural **MVC** (`model`, `controller` e `view`)
- Pacote `persistence` gerencia os dados salvos pelo programa
- Pacote `utils` possui utilitários que podem ser usados em todo o programa

```c
📂 build                // Arquivos Compilados
📂 data                 // Dados salvos
📂 docs                 // Documentação
📂 src                  // Código-fonte
 ├──📁 model               
 ├──📁 view
 ├──📁 controller
 ├──📁 persistence
 ├──📁 utils
 └──☕ Program.java
```

&nbsp;

## 📦 Entregas

O trabalho foi divido em três entregas, onde os erros das entregas passadas deveriam ser corrigidos.

O  [**`> Diagrama de Classes <`**](./docs/classes-diagram.pdf) foi atualizado durante todo o desenvolvimento do projeto.

### ✅ `Entrega 1` - Início do programa

- [Diagrama de Casos de Uso](./docs/usecase-diagram.pdf)
- [Descrição do Software](./docs/software-description.pdf)
- [Diagrama de Comunicação](./docs/communication-diagram.pdf)

### ✅ `Entrega 2` - Finalização do programa

- [Diagrama de Pacotes](./docs/packages-diagram.pdf)
- [Diagrama de Atividade](./docs/activity-diagram.pdf)
- [Diagrama de Máquina de Estados](./docs/statemachine-diagram.pdf)

### ✅ `Entrega 3` - Implementação de padrões de software

- Implementação do padrão DAO
- Implementação do padrão Singleton
- Implementação do padrão Decorator

&nbsp;

## 🖥 Rodando o programa

Utilize o compilador `javac` para criar os arquivos ``.class``

```bash
javac -d ./build src/Program.java
```

Execute o programa

```bash
java -cp ./build src.Program
```
