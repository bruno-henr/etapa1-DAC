package com.example.etapa1DAC.Event;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.domain.Event;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    public void testCreateEvent() {
        CreateEventRequest newEvent = new CreateEventRequest();
        newEvent.name = "Concert";
        newEvent.description = "A live music concert.";
        newEvent.category = "Music";
        newEvent.location = "New Venue";
        newEvent.start_time = "2023-04-01T19:00:00";
        newEvent.end_time = "2023-04-01T21:00:00";

        ResponseEntity<Event> responseEntity = restTemplate.postForEntity("/event/create", newEvent, Event.class);

        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getName()).isEqualTo("Concert");
        assertThat(responseEntity.getBody().getDescription()).isEqualTo("A live music concert.");
        assertThat(responseEntity.getBody().getCategory()).isEqualTo("Music");
    }

    @Test
    public void testListEvents() {
        ResponseEntity<List<Event>> responseEntity = restTemplate.exchange(
                "/event/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Event>>() {
                });
        System.out.println("teste => "+ responseEntity);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isNotEmpty();
    }

    @Test
    public void testFilterEvents() {
        LocalDateTime startTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endTime = LocalDateTime.now().plusDays(1);
        String url = String.format("/event/filter?category=%s&location=%s&startTime=%s&endTime=%s&page=0&size=10",
                "Music", "New Venue", startTime, endTime);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
    }
}
