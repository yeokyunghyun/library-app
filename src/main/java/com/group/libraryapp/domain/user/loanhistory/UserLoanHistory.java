package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    protected UserLoanHistory(){}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    @Column (nullable = false)
    String bookName;

    boolean isReturn;

    public String getBookName() {
        return bookName;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void doReturn() {
        this.isReturn = true;
    }
}
