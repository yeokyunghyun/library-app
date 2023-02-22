package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    //이름과 나이정보를 가지고있음

    protected User(){}
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, length = 25, name = "name")
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserLoanHistory> histories = new ArrayList<>();

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException();
        this.name = name;
        this.age = age;
    }

    public long getId() {return id;}
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        histories.add(new UserLoanHistory(this,bookName));
    }

    public void doReturn(String bookName) {
        UserLoanHistory userLoanHistory = histories.stream()
                .filter(element -> element.getBookName().equals(bookName))
                .findFirst().orElseThrow(IllegalArgumentException::new);

        userLoanHistory.doReturn();
    }
}
