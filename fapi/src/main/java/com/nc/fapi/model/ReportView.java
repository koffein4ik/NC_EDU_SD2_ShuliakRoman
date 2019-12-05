package com.nc.fapi.model;

public class ReportView {
    private UserEntity sender;
    private UserEntity reportedUser;
    private PostsEntity reportedPost;

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public UserEntity getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(UserEntity reportedUser) {
        this.reportedUser = reportedUser;
    }

    public PostsEntity getReportedPost() {
        return reportedPost;
    }

    public void setReportedPost(PostsEntity reportedPost) {
        this.reportedPost = reportedPost;
    }
}
