package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public long getId() {return id;} // 얘때문이엇음

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }
}
