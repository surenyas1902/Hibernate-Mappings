package com.surendiran.entity;

import javax.persistence.*;

@Entity
@Table(name="review")
public class Review {

    // define fields

    // define constructor

    // define getter/setters

    // define toString

    //annotate Fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int rowId;

    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rowId=" + rowId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
