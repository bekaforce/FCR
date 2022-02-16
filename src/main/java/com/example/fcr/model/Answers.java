package com.example.fcr.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fcr_final_answers", schema = "public")
public class Answers {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "response")
    private String resonse;

    @Column(name = "case")
    private String case_answers;

}
