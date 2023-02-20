package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    long id;
    String name;
    long age;

    public long id() {
        return id;
    }
    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }
}
