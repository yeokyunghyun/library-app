package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    protected User() {}

    public User(String name, long age) {
        this.name = name;
        this.age = age;
    }
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long age;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserLoanHistory> userHistories;

    public void save(String bookName) {
        userHistories.add(new UserLoanHistory(this,bookName));
    }

    public void doReturn(String bookName) {
        UserLoanHistory targetHistory = userHistories.stream()
                .filter(history -> bookName.equals(history.getBookName()) && history.isReturn() == false)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
