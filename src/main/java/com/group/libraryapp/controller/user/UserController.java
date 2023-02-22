package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        //현재 RequestBody를 통해서 들어오고있는 userCreateRequest의 이름과 나이를 mySql에 저장해야됨.
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
       return userService.getUsers();
    }

//    1.업데이트기능구현

    @PutMapping ("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) { //id와 name을 보내는데 UserUpdate
      userService.updateUser(request);
    }
//    2.삭제기능구현

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}
