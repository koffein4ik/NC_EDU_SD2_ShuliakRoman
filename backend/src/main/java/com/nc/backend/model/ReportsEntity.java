package com.nc.backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reports", schema = "photosquare")
public class ReportsEntity {
    private int reportId;
    private Timestamp date;
    private String reason;
    private ReportStatus status;
    private UserEntity user;
    private PostsEntity post;

    @ManyToOne
    @JoinColumn(name="user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="post_id")
    public PostsEntity getPost() {
        return post;
    }

    public void setPost(PostsEntity post) {
        this.post = post;
    }

    @Id
    @Column(name = "report_id")
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "status")
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
