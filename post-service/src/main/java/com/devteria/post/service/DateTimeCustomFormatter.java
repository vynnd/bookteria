package com.devteria.post.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeCustomFormatter {

    Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>();

    public DateTimeCustomFormatter() {
        strategyMap.put(60L, this::formatInSeconds);
        strategyMap.put(3600L, this::formatInMinutes);
        strategyMap.put(86400L, this::formatInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDate);
    }

    public String format(Instant instant){
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        var strategy = strategyMap.entrySet()
                .stream()
                .filter(longFunctionEntry -> elapseSeconds < longFunctionEntry.getKey())
                .findFirst().get();


        return strategy.getValue().apply(instant);
    }

    private String formatInSeconds(Instant instant){
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return elapseSeconds + " seconds ago";
    }

    private String formatInMinutes(Instant instant){
        long elapseMinutes = ChronoUnit.MINUTES.between(instant, Instant.now());
        return elapseMinutes + " minutes ago";
    }

    private String formatInHours(Instant instant){
        long elapseHours = ChronoUnit.HOURS.between(instant, Instant.now());
        return elapseHours + " hours ago";
    }

    private String formatInDate(Instant instant){
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        return localDateTime.format(dateTimeFormatter);
    }
}
