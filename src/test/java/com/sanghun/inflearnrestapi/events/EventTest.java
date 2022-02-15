package com.sanghun.inflearnrestapi.events;


import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


@RunWith(JUnitParamsRunner.class)
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


    private static Stream<Arguments> paramsForTestFree_with_stream() {
        return Stream.of(
                Arguments.of(0, 0, true),
                Arguments.of(100, 0, false),
                Arguments.of(0, 100, false),
                Arguments.of(100, 200, false)
        );
    }

    @ParameterizedTest(name = "{index} => basePrice={0}, maxPrice={1}, isFree={2}")
    @MethodSource("paramsForTestFree_with_stream")
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        //Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        //When
        event.update();

        //Then
        assertThat(event.isFree(), is(isFree));
    }
    private static Object[] paramsForTestOffline() {
        return new Object[] {
                new Object[] {"판교로 552번길 26-3", true},
                new Object[] {null, false},
                new Object[] {"      ", false}
        };
    }

    @ParameterizedTest(name = "{index} => location={0}, isOffline={2}")
    @MethodSource("paramsForTestOffline")
    public void testOffline(String location, boolean isOffline) {
        //Given
        Event event = Event.builder()
                .location(location)
                .build();

        //When
        event.update();

        //Then
        assertThat(event.isOffline(), is(isOffline));

    }
}