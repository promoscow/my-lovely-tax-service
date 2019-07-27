package ru.xpendence.mylovelytaxservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.xpendence.mylovelytaxservice.dto.InspectionDto;
import ru.xpendence.mylovelytaxservice.service.InspectionService;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:38
 * e-mail: v.chernyshov@pflb.ru
 */
@RestController
@RequestMapping("/inspections")
public class InspectionController {

    private final InspectionService service;

    public InspectionController(InspectionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<InspectionDto> get(@RequestParam Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/user")
    public ResponseEntity<Boolean> getForUser(@RequestParam Long userId) {
        return ResponseEntity.ok(service.getForUser(userId));
    }
}