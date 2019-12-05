package com.nc.backend.repositories;

import com.nc.backend.model.ReportStatus;
import com.nc.backend.model.ReportsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository extends PagingAndSortingRepository<ReportsEntity, Integer> {
    @Transactional
    @Modifying
    @Query(value = "Delete from ReportsEntity r where r.post.postId= :postId ")
    void deleteReportsByPostId(@Param("postId") Integer postId);

    Iterable<ReportsEntity> getAllByStatus(ReportStatus reportStatus);
}
