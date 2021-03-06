package com.fiap.carautomation.controller;

import com.fiap.carautomation.dto.UserDTO;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.model.User;
import com.fiap.carautomation.service.UserService;
import com.fiap.carautomation.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@Api(value = "userapi", description = "Api responsável por realizar o cadastro do usuário")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Recurso que cadastra um usuário", response = User.class)
    public ResponseEntity<Response<User>> cadastrar(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        Response<User> response = new Response<>();
        User user = userService.convertDtoToUser(userDTO);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        } else {

            user.setEnderecoAtual(user.getEnderecoOrigem());
            userService.save(user);
            response.setData(user);
            return ResponseEntity.ok().body(response);
        }


    }
    @GetMapping
    @ApiOperation(value = "Recurso responsável por retornar os usuários cadastrados no sistema")
    public ResponseEntity<Response<User>> getUsers(){

        Response<User> response = new Response<>();
        List<User>users = userService.findAll();
        response.setDataList(users);

        return ResponseEntity.ok().body(response);


    }


}
