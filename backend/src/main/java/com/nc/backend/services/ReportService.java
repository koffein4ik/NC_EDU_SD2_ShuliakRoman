package com.nc.backend.services;

import com.nc.backend.model.ReportData;
import com.nc.backend.model.ReportsEntity;

import java.util.List;

public interface ReportService {
    void submitReport(ReportData reportData);

    List<ReportsEntity> getReports(Integer page);

    List<ReportsEntity> markReportAsChecked(Integer reportId);

    List<ReportsEntity> getCheckedReports(Integer page);
}
