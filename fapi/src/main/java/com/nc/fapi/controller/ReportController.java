package com.nc.fapi.controller;

import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.ReportData;
import com.nc.fapi.model.ReportsEntity;
import com.nc.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/reports")
public class ReportController {

    private final RestTemplate restTemplate = new RestTemplate();

    private PostService postService;

    public ReportController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @PostMapping("submitreport")
    public void submitReport(@RequestBody ReportData reportData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReportData> httpEntityData = new HttpEntity<>(reportData, headers);
        this.restTemplate.exchange("http://localhost:8080/api/reports/submitreport", HttpMethod.POST,
                httpEntityData, String.class);
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("getreports/{page}")
    public ReportsEntity[] getReports(@PathVariable(name = "page") String page) {
        ReportsEntity[] reports = this.restTemplate.
                getForObject("http://localhost:8080/api/reports/getreports/" + page, ReportsEntity[].class);
        for (ReportsEntity report : reports) {
            PostsEntity post = report.getPost();
            postService.getPhotosFromPost(post);
            //report.setPost(post);
        }
        return reports;
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("getcheckedreports/{page}")
    public ResponseEntity<ReportsEntity[]> getCheckedReports(@PathVariable(name = "page") String page) {
        ResponseEntity<ReportsEntity[]> reports = this.restTemplate.
                getForEntity("http://localhost:8080/api/reports/getcheckedreports/" + page, ReportsEntity[].class);
        if (reports.getBody() != null) {
            for (ReportsEntity report : reports.getBody()) {
                PostsEntity post = report.getPost();
                postService.getPhotosFromPost(post);
            }
        }
        return reports;
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("markreportaschecked/{reportid}")
    public ReportsEntity[] markReportAsChecked(@PathVariable(name = "reportid") String reportId) {
        ReportsEntity[] reports = this.restTemplate.
                getForObject("http://localhost:8080/api/reports/markaschecked/" + reportId, ReportsEntity[].class);
        for (ReportsEntity report : reports) {
            PostsEntity post = report.getPost();
            postService.getPhotosFromPost(post);
            //report.setPost(post);
        }
        return reports;
    }
}
