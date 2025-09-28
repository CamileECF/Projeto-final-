# Projeto Final - Sistema de Gestão - Petcare

Este projeto foi desenvolvido como parte de um trabalho acadêmico.  
O sistema implementa funcionalidades relacionadas a tutores, usuários, pets, vacinas, pedidos, medicamentos e conteúdos educativos.

## Estrutura do Projeto

- **Linguagem:** Java  
- **Padrão de organização:** MVC (Model-View-Controller)  
- **Build:** Maven (`pom.xml`)  

### Pacotes principais:
- `controller/` → Controladores das entidades  
- `model/` → Classes de modelo (entidades)  
- `view/` → Interfaces e classes de visualização  

---

## Responsabilidades da Equipe

###  Pessoa 1: **Camile**  
**Tutores e Usuários**  
- `Tutor.java`, `Usuario.java` (model)  
- `TutorController.java`, `UsuarioController.java` (controller)  
- `TutorView.java`, `UsuarioView.java` (view)  

###  Pessoa 2: **Erick**  
**Pets**  
- `Pet.java` (model)  
- `PetController.java` (controller)  
- `PetView.java` (view)  

###  Pessoa 3: **Anna**  
**Vacinas**  
- `Vacina.java`, `AplicacaoVacina.java` (model)  
- `VacinaController.java` (controller)  
- `VacinaView.java` (view)  

###  Pessoa 4: **Emanoel**  
**Pedidos, Itens e Medicamentos**  
- `Pedido.java`, `ItemPedido.java`, `Medicamento.java` (model)  
- `PedidoController.java`, `MedicamentoController.java` (controller)  
- `PedidoView.java`, `MedicamentoView.java` (view)  

###  Pessoa 5: **Fabi**  
**Conteúdo Educativo e Consumo**  
- `ConteudoEducativo.java`, `ConsumoConteudo.java` (model)  
- `ConteudoController.java` (controller)  
- `ConteudoView.java` (view)  

---

## Execução do Projeto

1. Certifique-se de ter o **Java** e o **Maven** instalados.  
2. Dentro da pasta principal do projeto, execute:  
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="view.ProjetoFinal"
   ```
3. A aplicação será iniciada com a classe principal `ProjetoFinal.java`.

---

## Contribuidores

- Camile – Tutores e Usuários  
- Erick – Pets  
- Anna – Vacinas  
- Emanoel – Pedidos, Itens e Medicamentos  
- Fabi – Conteúdo Educativo e Consumo  
