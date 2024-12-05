# Projeto de Tickets - Etapa 1 DAC

Este projeto é um sistema para gerenciamento de tipos de ingressos, eventos, e informações relacionadas aos ingressos vendidos. O sistema utiliza Spring Boot e JPA com PostgreSQL como banco de dados.

## Estrutura do Projeto

### 1. **TicketType**

A classe `TicketType` representa um tipo de ingresso, contendo informações sobre o nome do tipo, quantidade total de ingressos, quantidade de ingressos vendidos, e um mapa de campos obrigatórios associados a esse tipo de ingresso.

#### Atributos:
- **id** (Long): Identificador único do tipo de ingresso.
- **name** (String): Nome do tipo de ingresso (ex: "VIP", "Normal").
- **requiredFields** (Map<String, Object>): Um mapa que contém os campos obrigatórios para a criação do ingresso, onde a chave é o nome do campo (ex: "CPF") e o valor é um objeto que descreve o campo.
- **totalQuantity** (Integer): A quantidade total de ingressos disponíveis para este tipo.
- **soldQuantity** (Integer): A quantidade de ingressos vendidos, inicializada como 0.

#### Métodos:
- **hasAvailableTickets(Integer quantity)**: Verifica se há ingressos suficientes disponíveis para a venda.
- **sellTicket(Integer quantity)**: Vende ingressos, incrementando a quantidade de ingressos vendidos.

### 2. **Event**

A classe `Event` representa um evento que pode ter ingressos vendidos e ocorrer em uma determinada data e local. Cada evento pode ter múltiplas datas e múltiplos tipos de ingressos associados.

#### Atributos:
- **id** (Long): Identificador único do evento.
- **name** (String): Nome do evento (ex: "Show de Rock", "Teatro").
- **description** (String): Descrição detalhada do evento.
- **createdAt** (LocalDateTime): Data e hora de criação do evento, gerada automaticamente.
- **location** (String): Local onde o evento ocorre.
- **price** (Double): Preço base para os ingressos.
- **eventDates** (Set<EventDate>): Coleção de datas e horários para o evento.
- **tickets** (Set<Ticket>): Coleção de ingressos disponíveis para o evento.


### 3. **EventDate**

A classe `EventDate` representa uma data específica para a realização de um evento. Um evento pode ter múltiplas datas associadas, permitindo que o mesmo evento ocorra em diferentes horários.

#### Atributos:
- **id** (Long): Identificador único da data do evento.
- **createdAt** (LocalDateTime): Data e hora de criação da data do evento.
- **event** (Event): O evento associado a essa data específica.
- **start_time** (LocalDateTime): Hora de início do evento.
- **end_time** (LocalDateTime): Hora de término do evento.


### 4. **Ticket**

A classe `Ticket` representa um ingresso para um evento. Cada ingresso está associado a um tipo de ingresso, tem uma data de validade e contém informações obrigatórias que precisam ser preenchidas pelo comprador.

#### Atributos:
- **id** (Long): Identificador único do ingresso.
- **createdAt** (LocalDateTime): Data e hora de criação do ingresso.
- **validUntil** (LocalDateTime): Data e hora de validade do ingresso.
- **event** (Event): O evento associado a esse ingresso.
- **quantity** (Integer): Quantidade de ingressos disponíveis.
- **ticketType** (TicketType): Tipo de ingresso (ex: PCD, Estudante, Idoso, Inteira e etc...).
- **requiredInfo** (Map<String, String>): Mapa de campos obrigatórios para o ingresso (ex: CPF, nome). Armazenado como um mapa no banco de dados.


