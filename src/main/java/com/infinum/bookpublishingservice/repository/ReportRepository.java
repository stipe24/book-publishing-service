package com.infinum.bookpublishingservice.repository;

import com.infinum.bookpublishingservice.model.entity.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface ReportRepository extends JpaRepository<ReportEntity, String> {

    @Query("select r.createdAt from ReportEntity r")
    Page<Instant> getLastReportTime(Pageable pageable);

}