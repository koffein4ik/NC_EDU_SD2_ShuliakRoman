package com.nc.backend.services;

import com.nc.backend.model.*;
import com.nc.backend.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

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
    public List<ReportsEntity> getReports() {
        Iterable<ReportsEntity> reports = reportRepository.getAllByStatus(ReportStatus.unchecked);
        List<ReportsEntity> reportsList = new ArrayList<>();
        reports.iterator().forEachRemaining(reportsList::add);
        return reportsList;
    }

    @Override
    public List<ReportsEntity> getCheckedReports() {
        Iterable<ReportsEntity> reports = reportRepository.getAllByStatus(ReportStatus.checked);
        List<ReportsEntity> reportsList = new ArrayList<>();
        reports.iterator().forEachRemaining(reportsList::add);
        return reportsList;
    }

    @Override
    public List<ReportsEntity> markReportAsChecked(Integer reportId) {
        Optional<ReportsEntity> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            ReportsEntity markedReport = report.get();
            markedReport.setStatus(ReportStatus.checked);
            reportRepository.save(markedReport);
        }
        return getReports();
    }
}
