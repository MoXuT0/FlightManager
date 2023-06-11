package com.gridnine.testing;

import java.time.LocalTime;
import java.util.List;

public interface FlightFilter {
    List<Flight> departureBeforeCurrentTime();
    List<Flight> arrivalEarlierThanDeparture();
    List<Flight> timeBetweenSegmentsIsMoreThan(LocalTime time);
}
