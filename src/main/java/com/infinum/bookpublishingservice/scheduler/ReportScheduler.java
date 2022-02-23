package com.infinum.bookpublishingservice.scheduler;

import com.infinum.bookpublishingservice.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
@Slf4j
public class ReportScheduler {

    private final ReportService reportService;

    @Scheduled(cron = "0 0 * * * *")
    public void generateIsbnReport() {
        var now = Instant.now();
        var reportContent = reportService.generateIsbnReport();
        reportService.saveReport(now);
        //log.info(reportContent);
    }

}