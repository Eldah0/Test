package com.codingdojo.testjava.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //username
    @NotEmpty(message = "Username is required!")
    @Size(min = 8, max = 128, message = "Username must be between 8 and 128 characters")
    private String userName;

    //email
    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    private String email;

    //password
    @NotEmpty(message = "Password is required!")
    @Size(min =8, max =128, message = "Password must be between 8 and 128 characters")
    private String password;

    //confirm password
    @Transient
    @NotEmpty(message = "Confirm Password is required")
    @Size(min =8, max =128, message = " Confirm password must be between 8 and 128 characters")
    private String confirm;


    //relations
    @Column(updatable=false)
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Course> courses;

    //created and updated
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    //constructor empty
    public User(){}

    //loaded constructor
    public User(Long id, String userName, String email, String password, String confirm, List courses) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.courses = courses;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getUser() {
        return userName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public List<Course> getInstructorCourses() {
        return courses;
    }

    public void setInstructorCourses(List<Course> instructorCourses) {
        this.courses = instructorCourses;
    }
}
