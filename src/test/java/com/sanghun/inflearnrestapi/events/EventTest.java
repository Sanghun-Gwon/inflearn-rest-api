package com.sanghun.inflearnrestapi.events;


import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Inflearn Spring Rest API")
                .description("REST API development with Spring")
                .build();

        assertThat(event, is(notNullValue()));
    }

    @Test
    public void javaBean(){
        // Given
        String name = "Event";
        String description = "spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName(), is(equalTo(name)));
        assertThat(event.getDescription(), is(equalTo(description)));
    }
}