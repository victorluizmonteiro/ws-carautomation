package com.fiap.carautomation.controller;

import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.model.User;
import com.fiap.carautomation.service.UserService;
import com.fiap.carautomation.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Api(value = "userapi",description = "Api responsável por realizar o cadastro do usuário")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "recurso que cadastra um usuário",response = User.class)
    public ResponseEntity<User> cadastrar(@RequestBody User user) {
        try {
            user.setEnderecoAtual(user.getEnderecoOrigem());
            userService.save(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(user);
        }

    }



}
