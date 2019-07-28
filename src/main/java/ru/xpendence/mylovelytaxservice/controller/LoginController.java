package ru.xpendence.mylovelytaxservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.xpendence.mylovelytaxservice.dto.UserDto;
import ru.xpendence.mylovelytaxservice.service.LoginService;
import ru.xpendence.mylovelytaxservice.transfer.Validation;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 21:57
 * e-mail: v.chernyshov@pflb.ru
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@Validated(value = Validation.Login.class) @RequestBody UserDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }
}
