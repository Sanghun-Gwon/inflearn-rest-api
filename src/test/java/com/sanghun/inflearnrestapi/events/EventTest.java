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


    @Test
    public void testFree() {
        //Given
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();
        //When
        event.update();

        //Then
        assertThat(event.isFree(), is(true));


        //Given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();
        //When
        event.update();

        //Then
        assertThat(event.isFree(), is(false));


        //Given
        event = Event.builder()
                .basePrice(0)
                .maxPrice(100)
                .build();
        //When
        event.update();

        //Then
        assertThat(event.isFree(), is(false));
    }


    @Test
    public void testOffline() {
        //Given
        Event event = Event.builder()
                .location("판교로 552번길 26-")
                .build();

        //When
        event.update();

        //Then
        assertThat(event.isOffline(), is(true));

        //Given
        event = Event.builder()
                .build();

        //When
        event.update();

        //Then
        assertThat(event.isOffline(), is(false));
    }
}