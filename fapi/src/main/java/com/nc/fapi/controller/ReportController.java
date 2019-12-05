package com.nc.fapi.controller;

import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.ReportData;
import com.nc.fapi.model.ReportsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/reports")
public class ReportController {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("submitreport")
    public void submitReport(@RequestBody ReportData reportData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReportData> httpEntityData = new HttpEntity<>(reportData, headers);
        this.restTemplate.exchange("http://localhost:8080/api/reports/submitreport", HttpMethod.POST,
                httpEntityData, String.class);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getreports")
    public ResponseEntity<ReportsEntity[]> getReports() {
        ResponseEntity<ReportsEntity[]> reports = this.restTemplate.
                getForEntity("http://localhost:8080/api/reports/getreports", ReportsEntity[].class);
        if (reports.getBody() != null) {
            for (ReportsEntity report : reports.getBody()) {
                PostsEntity post = report.getPost();
                String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
                File folder = new File(folderpath);
                if (folder.exists()) {
                    File[] listOfFiles = folder.listFiles();
                    Set<String> photos = new HashSet<>();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        photos.add(listOfFiles[i].getName());
                    }
                    post.setPhotoURIs(photos);
                }
                //report.setPost(post);
            }
        }
        return reports;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getcheckedreports")
    public ResponseEntity<ReportsEntity[]> getCheckedReports() {
        ResponseEntity<ReportsEntity[]> reports = this.restTemplate.
                getForEntity("http://localhost:8080/api/reports/getcheckedreports", ReportsEntity[].class);
        if (reports.getBody() != null) {
            for (ReportsEntity report : reports.getBody()) {
                PostsEntity post = report.getPost();
                String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
                File folder = new File(folderpath);
                if (folder.exists()) {
                    File[] listOfFiles = folder.listFiles();
                    Set<String> photos = new HashSet<>();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        photos.add(listOfFiles[i].getName());
                    }
                    post.setPhotoURIs(photos);
                }
                //report.setPost(post);
            }
        }
        return reports;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("markreportaschecked/{reportid}")
    public ResponseEntity<ReportsEntity[]> markReportAsChecked(@PathVariable(name = "reportid") String reportId) {
        ResponseEntity<ReportsEntity[]> reports = this.restTemplate.
                getForEntity("http://localhost:8080/api/reports/markaschecked/" + reportId, ReportsEntity[].class);
        if (reports.getBody() != null) {
            for (ReportsEntity report : reports.getBody()) {
                PostsEntity post = report.getPost();
                String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
                File folder = new File(folderpath);
                if (folder.exists()) {
                    File[] listOfFiles = folder.listFiles();
                    Set<String> photos = new HashSet<>();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        photos.add(listOfFiles[i].getName());
                    }
                    post.setPhotoURIs(photos);
                }
                //report.setPost(post);
            }
        }
        return reports;
    }
}
