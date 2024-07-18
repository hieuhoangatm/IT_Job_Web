package com.dinhhieu.jobitweb.controller;

import com.dinhhieu.jobitweb.domain.response.ResCreateUserDTO;
import com.dinhhieu.jobitweb.domain.response.ResUpdateUserDTO;
import com.dinhhieu.jobitweb.domain.response.ResUserDTO;
import com.dinhhieu.jobitweb.domain.response.ResultPaginationDTO;
import com.dinhhieu.jobitweb.domain.User;
import com.dinhhieu.jobitweb.service.UserService;
import com.dinhhieu.jobitweb.util.error.IdInvalidException;
import com.dinhhieu.jobitweb.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @ApiMessage("Create a new user")
    public ResponseEntity<ResCreateUserDTO> createNewUser(@Valid @RequestBody User postManUser) throws IdInvalidException {
        boolean isEmailExist = this.userService.isEmailExist(postManUser.getEmail());
        if (isEmailExist){
            throw new IdInvalidException("Email " + postManUser.getEmail() + " existed");
        }
        String hassPassword = this.passwordEncoder.encode(postManUser.getPassword());
        postManUser.setPassword(hassPassword);
        User ericUser = this.userService.handleCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertToResCreateUserDTO(ericUser));
    }


    @DeleteMapping("/users/{id}")
    @ApiMessage("Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if(id>1500){
            throw new IdInvalidException("id khong lon hon 1500");
        }
        User currentUser = this.userService.fetchUserById(id);
        if(currentUser==null){
            throw new IdInvalidException("User with id = " + id + " not exist");
        }
        this.userService.handleDeleteUser(id);

        return ResponseEntity.ok(null);
        // return ResponseEntity.status(HttpStatus.OK).body("ericUser");
    }

    // fetch user by id
    @GetMapping("/users/{id}")
    @ApiMessage("fetch user by id")
    public ResponseEntity<ResUserDTO> getUserById(@PathVariable("id") long id) throws IdInvalidException {
        User fetchUser = this.userService.fetchUserById(id);
        if(fetchUser==null){
            throw new IdInvalidException("Not found user with "+id);
        }
        // return ResponseEntity.ok(fetchUser);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertToResUserDTO(fetchUser));
    }

    // fetch all users
    @GetMapping("/users")
    @ApiMessage("fetch all users")
    public ResponseEntity<ResultPaginationDTO> getAllUser(
            @Filter Specification<User> spec, Pageable pageable){
//            @RequestParam("current") Optional<String> currentOptional,
//            @RequestParam("pageSize") Optional<String> pageSizeOptional) {
//        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
//        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
//
//        int current = Integer.parseInt(sCurrent);
//        int pageSize = Integer.parseInt(sPageSize);
//
//        Pageable pageable = PageRequest.of(current - 1, pageSize);

        // return ResponseEntity.ok(this.userService.fetchAllUser());
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUser(spec,pageable));
    }


    @PutMapping("/users")
    @ApiMessage("fetch update user by id")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@RequestBody User user) throws IdInvalidException {
        User ericUser = this.userService.handleUpdateUser(user);
        if(ericUser==null){
            throw new IdInvalidException("User with id = "+ user.getId()+" not exist");
        }
        return ResponseEntity.ok(this.userService.convertToResUpdateUserDTO(ericUser));
    }


}
