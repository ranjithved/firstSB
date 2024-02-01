package com.example.firstSB.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "jobs")
public class Jobs {


    @Id
    private int jobid;

    private String title;

    private String Available;

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }

}
