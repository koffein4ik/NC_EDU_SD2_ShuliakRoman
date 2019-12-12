package com.nc.backend.services;

import com.nc.backend.model.*;
import com.nc.backend.repositories.ReportRepository;
import com.nc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void submitReport(ReportData reportData) {
        ReportsEntity reportsEntity = new ReportsEntity();
        UserEntity senderUser = new UserEntity();
        UserEntity reportedUser = new UserEntity();
        senderUser.setId(reportData.getSenderUserId());
        reportedUser.setId(reportData.getReportedUserId());
        PostsEntity reportedPost = new PostsEntity();
        reportedPost.setPostId(reportData.getPostId());
        reportsEntity.setPost(reportedPost);
        reportsEntity.setReason(reportData.getReason());
        reportsEntity.setSenderUser(senderUser);
        reportsEntity.setReportedUser(reportedUser);
        reportsEntity.setStatus(ReportStatus.unchecked);
        Date date = new Date();
        reportsEntity.setDate(new Timestamp(date.getTime()));
        reportRepository.save(reportsEntity);
    }

    @Override
    public List<ReportsEntity> getReports(Integer page) {
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);
        List<ReportsEntity> reports = reportRepository.getAllByStatus(ReportStatus.unchecked, pageable);
        return reports;
    }

    @Override
    public List<ReportsEntity> getCheckedReports(Integer page) {
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);
        List<ReportsEntity> reports = reportRepository.getAllByStatus(ReportStatus.checked, pageable);
        return reports;
    }

    @Override
    public List<ReportsEntity> markReportAsChecked(Integer reportId) {
        Optional<ReportsEntity> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            ReportsEntity markedReport = report.get();
            markedReport.setStatus(ReportStatus.checked);
            reportRepository.save(markedReport);
        }
        return getReports(0);
    }
}
