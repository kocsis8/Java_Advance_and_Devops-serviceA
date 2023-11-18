package hu.codehunters.messages.controller;

import hu.codehunters.messages.model.Statistics;
import hu.codehunters.messages.srvice.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(value = "/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;


    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public Statistics getStatistics(){
        return statisticsService.calculateStatistics(Instant.now());
    }
}
