package com.nc.backend.controller;

import com.nc.backend.model.ReportData;
import com.nc.backend.model.ReportsEntity;
import com.nc.backend.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reports")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping(value = "submitreport")
    public void submitReport(@RequestBody ReportData reportData) {
        this.reportService.submitReport(reportData);
    }

    @GetMapping(value = "getreports/{page}")
    public List<ReportsEntity> getReports(@PathVariable(name = "page") String page) {
        return this.reportService.getReports(Integer.parseInt(page));
    }

    @GetMapping(value = "getcheckedreports/{page}")
    public List<ReportsEntity> getCheckedReports(@PathVariable(name = "page") String page) {
        return this.reportService.getCheckedReports(Integer.parseInt(page));
    }

    @GetMapping("markaschecked/{reportid}")
    public List<ReportsEntity> markReportAsChecked(@PathVariable(name = "reportid") String reportId) {
        return this.reportService.markReportAsChecked(Integer.parseInt(reportId));
    }
}
