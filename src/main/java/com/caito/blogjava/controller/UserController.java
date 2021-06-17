package com.caito.blogjava.controller;

import com.caito.blogjava.constatnts.ConstantExeptionMessages;
import com.caito.blogjava.dto.NewUser;
import com.caito.blogjava.dto.UserResponse;
import com.caito.blogjava.exceptions.customs.BadRequestException;
import com.caito.blogjava.service.implementation.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody NewUser newUser, MultipartFile file) throws IOException {
        UserResponse response = userService.ceateUser(newUser, file);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<UserResponse>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws NotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity( HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<List<UserResponse>>(userService.ListAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
                                                @RequestBody NewUser newUser) throws NotFoundException {
        return new ResponseEntity<UserResponse>(userService.updateUser(id,newUser),
                HttpStatus.OK);
    }

    @PostMapping("/up-image")
    public ResponseEntity<UserResponse> uploadImage(@RequestParam MultipartFile file,
                                                    @RequestParam Long id) throws NotFoundException, IOException {
        return new ResponseEntity<UserResponse>(userService.uploadImage(file, id), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<String> getAllPageable(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return new ResponseEntity<String>(userService.getAllPagination(pageable), HttpStatus.OK);
    }

}
