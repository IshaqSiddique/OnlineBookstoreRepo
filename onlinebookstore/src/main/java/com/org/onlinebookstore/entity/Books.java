package com.org.onlinebookstore.entity;

import javax.persistence.*;

@Entity
@Table(name= "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "book_isbn")
    private Integer ISBN;
    @Column(name="book_name")
    private String name;
    @Column(name= "book_description")
    private String description;
    @Column(name= "author")
    private String author;
    @Column(name= "book_type")
    private String bookType;
    @Column(name= "book_price")
    private double price;

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
