package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {

    private final List<Flight> flights;

    public FlightFilterImpl(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> departureBeforeCurrentTime() {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> arrivalEarlierThanDeparture() {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> timeBetweenSegmentsIsMoreThan(LocalTime localTime) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segmentDurationIsMoreThan(flight, localTime)))
                .collect(Collectors.toList());
    }

    private boolean segmentDurationIsMoreThan(Flight flight, LocalTime localTime) {
        List<Duration> durationList = new ArrayList<>();
        for (int i = 0; i < flight.getSegments().size() - 1; i++) {
            durationList.add(Duration.between(flight.getSegments().get(i).getArrivalDate(),
                    flight.getSegments().get(i + 1).getDepartureDate()).abs());
        }
        long groundTime = durationList.stream()
                .map(Duration::toSeconds)
                .reduce(Long::sum)
                .orElse(0L);
        return groundTime > localTime.toSecondOfDay();
    }
}
