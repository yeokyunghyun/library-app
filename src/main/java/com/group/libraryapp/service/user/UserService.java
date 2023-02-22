package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Transactional
    public void saveUser(UserCreateRequest request) {

        /*String sql = "INSERT INTO user (name,age) VALUES(?,?)";
        jdbctemplate.update(sql,request.getName(),request.getAge());*/

        User user = new User(request.getName(),request.getAge());
        userRepository.save(user);
    }

    @Transactional
    public List<UserResponse> getUsers() {
       /* List<UserResponse> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql,(rs,rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            return new UserResponse(id,name,age);
        });*/
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {

        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

       user.updateName(request.getName());
       //유저의 이름을 변경해주어야됨.
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);

    }
}
