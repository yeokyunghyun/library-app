package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {

    protected Book() {}

    public Book(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 255, name = "name")
    String name;

    public String getName() {
        return name;
    }
}
