package com.nc.fapi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ReportsEntity {
    private int reportId;
    private Timestamp date;
    private String reason;
    private ReportStatus status;
    private PostsEntity post;
    private UserEntity senderUser;
    private UserEntity reportedUser;

    public UserEntity getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(UserEntity senderUser) {
        this.senderUser = senderUser;
    }

    public UserEntity getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(UserEntity reportedUser) {
        this.reportedUser = reportedUser;
    }

    public PostsEntity getPost() {
        return post;
    }

    public void setPost(PostsEntity post) {
        this.post = post;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportsEntity that = (ReportsEntity) o;
        return reportId == that.reportId &&
                Objects.equals(date, that.date) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, date, reason, status);
    }
}
