package com.gridnine.testing;

import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl(flights);

        System.out.println("Список всех полетов");
        flights.forEach(System.out::println);
        System.out.println("______________________________________________________________________");

        System.out.println("Список полетов где вылет до текущего момента времени");
        List<Flight> flights1 = flightFilter.departureBeforeCurrentTime();
        flights1.forEach(System.out::println);
        System.out.println("______________________________________________________________________");

        System.out.println("Список полетов где имеются сегменты с датой прилёта раньше даты вылета");
        List<Flight> flights2 = flightFilter.arrivalEarlierThanDeparture();
        flights2.forEach(System.out::println);
        System.out.println("______________________________________________________________________");

        System.out.println("Список полетов где общее время, проведённое на земле превышает два часа");
        List<Flight> flights3 = flightFilter.timeBetweenSegmentsIsMoreThan(LocalTime.of(2, 0));
        flights3.forEach(System.out::println);
    }
}