package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;

import java.time.LocalTime;
import java.util.List;

public interface FlightFilter {
    List<Flight> departureBeforeCurrentTime();
    List<Flight> arrivalEarlierThanDeparture();
    List<Flight> timeBetweenSegmentsIsMoreThan(LocalTime time);
}
