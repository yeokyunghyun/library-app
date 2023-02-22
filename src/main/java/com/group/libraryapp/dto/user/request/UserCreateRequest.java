package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    //유저의 이름과 나이
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
