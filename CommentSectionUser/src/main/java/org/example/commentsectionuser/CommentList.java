package org.example.commentsectionuser;

public class CommentList {
    String date;
    String comments;
    String interestValue;
    Integer id;
    Integer user_id;

    CommentList(String date, String comments, String interestValue, Integer id, Integer user_id) {
        this.date = date;
        this.comments = comments;
        this.interestValue = interestValue;
        this.id = id;
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(String interestValue) {
        this.interestValue = interestValue;
    }

}
