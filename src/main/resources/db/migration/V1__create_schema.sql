-- Criação da tabela User
CREATE TABLE account (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(128) NOT NULL,
                       active BOOLEAN NOT NULL
);

-- Criação da tabela Permission
CREATE TABLE permission (
                            id SERIAL PRIMARY KEY,
                            function VARCHAR(255) NOT NULL,
                            user_id BIGINT NOT NULL,
                            CONSTRAINT fk_permission_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Criação da tabela Event
CREATE TABLE event (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       owner_id BIGINT NOT NULL,
                       location VARCHAR(500) NOT NULL,
                       description VARCHAR(500) NOT NULL,
                       max_capacity INTEGER NOT NULL,
                       start_time TIMESTAMP NOT NULL,
                       end_time TIMESTAMP NOT NULL,
                       CONSTRAINT fk_event_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Criação da tabela EventDate
CREATE TABLE event_date (
                            id SERIAL PRIMARY KEY,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            location VARCHAR(255) NOT NULL,
                            event_id BIGINT NOT NULL,
                            start_time TIMESTAMP NOT NULL,
                            end_time TIMESTAMP NOT NULL,
                            CONSTRAINT fk_event_date_event FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE
);

-- Criação da tabela CategoryEventType
CREATE TABLE category_event_type (
                                     id SERIAL PRIMARY KEY DEFAULT,
                                     name VARCHAR(255) NOT NULL
);

-- Criação da tabela EventCategory (Tabela associativa entre Event e CategoryEventType)
CREATE TABLE event_category (
                                event_id BIGINT NOT NULL,
                                category_id BIGINT NOT NULL,
                                PRIMARY KEY (event_id, category_id),
                                CONSTRAINT fk_event_category_event FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
                                CONSTRAINT fk_event_category_category FOREIGN KEY (category_id) REFERENCES category_event_type(id) ON DELETE CASCADE
);

-- Criação da tabela Ticket
CREATE TABLE ticket (
                        id SERIAL PRIMARY KEY,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        quantity INTEGER NOT NULL,
                        event_id BIGINT NOT NULL,
                        event_date_id BIGINT,
                        modality VARCHAR(255) NOT NULL,
                        price DECIMAL(10,2) NOT NULL,
                        public_restriction VARCHAR(255),
                        valid_days_left INTEGER NOT NULL,
                        CONSTRAINT fk_ticket_event FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
                        CONSTRAINT fk_ticket_event_date FOREIGN KEY (event_date_id) REFERENCES event_date(id) ON DELETE CASCADE
);

-- Criação da tabela TicketField
CREATE TABLE ticket_field (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              ticket_id BIGINT NOT NULL,
                              name VARCHAR(255) NOT NULL,
                              type VARCHAR(255) NOT NULL,
                              required BOOLEAN NOT NULL,
                              CONSTRAINT fk_ticket_field_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE CASCADE
);

-- Criação da tabela UserInfoTicket
CREATE TABLE user_info_ticket (
                                  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                  purchase_item_id UUID NOT NULL,
                                  ticket_field_id UUID NOT NULL,
                                  info_value VARCHAR(255) NOT NULL,
                                  CONSTRAINT fk_user_info_ticket_purchase FOREIGN KEY (purchase_item_id) REFERENCES purchase_item(id) ON DELETE CASCADE,
                                  CONSTRAINT fk_user_info_ticket_ticket_field FOREIGN KEY (ticket_field_id) REFERENCES ticket_field(id) ON DELETE CASCADE
);

-- Criação da tabela TicketOffice
CREATE TABLE ticket_office (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               ticket_id BIGINT NOT NULL,
                               use_status VARCHAR(255) NOT NULL,
                               CONSTRAINT fk_ticket_office_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE CASCADE
);

-- Criação da tabela Purchase
CREATE TABLE purchase (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          user_id BIGINT NOT NULL,
                          date TIMESTAMP NOT NULL,
                          status VARCHAR(255) NOT NULL,
                          CONSTRAINT fk_purchase_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Criação da tabela PurchaseItem
CREATE TABLE purchase_item (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               purchase_id UUID NOT NULL,
                               ticket_id BIGINT NOT NULL,
                               quantity INTEGER NOT NULL,
                               price DECIMAL(10,2) NOT NULL,
                               CONSTRAINT fk_purchase_item_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id) ON DELETE CASCADE,
                               CONSTRAINT fk_purchase_item_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE CASCADE
);

-- Adicionar relação entre User e Permission
ALTER TABLE permission ADD CONSTRAINT fk_permission_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;


