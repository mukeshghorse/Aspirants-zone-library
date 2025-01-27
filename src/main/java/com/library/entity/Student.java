package com.library.entity;

import jakarta.persistence.*;
import java.util.Date;


@MappedSuperclass
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false)
    private Boolean isActive=true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedOn;

    // Default constructor for JPA
    public Student() {
    }

    // Parameterized constructor for convenience
    public Student(Long studentId, Boolean isActive, Date createdOn, Date updatedOn) {
        this.studentId = studentId;
        this.isActive = isActive;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
        this.updatedOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
    }
}