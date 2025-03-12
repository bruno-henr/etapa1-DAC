package com.example.etapa1DAC.TicketType;

import com.example.etapa1DAC.controller.request.TicketTypeRequest;
import com.example.etapa1DAC.domain.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketTypeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateTicketType() {
        TicketTypeRequest newTicketType = new TicketTypeRequest();
        newTicketType.setName("VIP");
        newTicketType.setTotalQuantity(100);
        newTicketType.setPrice(200.00);

        ResponseEntity<TicketType> responseEntity = restTemplate.postForEntity("/ticket/type/create", newTicketType, TicketType.class);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getName()).isEqualTo("VIP");
        assertThat(responseEntity.getBody().getTotalQuantity()).isEqualTo(100);
        assertThat(responseEntity.getBody().getPrice()).isEqualTo(200.00);
    }

    @Test
    public void testListTicketTypes() {
        ResponseEntity<List<TicketType>> responseEntity = restTemplate.exchange(
                "/ticket/type/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketType>>() {
                });

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isNotEmpty();
    }
}
