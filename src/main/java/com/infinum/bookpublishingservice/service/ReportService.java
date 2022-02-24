package com.infinum.bookpublishingservice.service;

import com.infinum.bookpublishingservice.model.book.BookFilter;
import com.infinum.bookpublishingservice.util.PaginationCreator;
import com.infinum.bookpublishingservice.model.entity.BookEntity;
import com.infinum.bookpublishingservice.model.entity.ReportEntity;
import com.infinum.bookpublishingservice.repository.BookRepository;
import com.infinum.bookpublishingservice.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public String generateIsbnReport() {

        var lastReportTime = reportRepository.getLastReportTime(PaginationCreator.createPaginationForReport());
        if (lastReportTime.getContent().isEmpty()) {
            var isbns = bookRepository.findAll().stream().map(BookEntity::getIsbn).collect(Collectors.toList());
            return String.join(",", isbns);
        } else {
            BookFilter filter = new BookFilter();
            filter.setAddedAt(Date.from(lastReportTime.getContent().get(0)));
            var isbns = bookService.findByFilter(filter).stream().map(BookEntity::getIsbn).collect(Collectors.toList());
            return String.join(",", isbns);
        }
    }

    public ReportEntity saveReport(Instant time) {
        ReportEntity report = new ReportEntity(time);
        return reportRepository.save(report);
    }

}