package ru.xpendence.mylovelytaxservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xpendence.mylovelytaxservice.dto.UserDto;
import ru.xpendence.mylovelytaxservice.entity.User;
import ru.xpendence.mylovelytaxservice.service.UserService;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 12:23
 * e-mail: v.chernyshov@pflb.ru
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping
    public ResponseEntity<UserDto> get(@RequestParam Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
