package ru.xpendence.mylovelytaxservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xpendence.mylovelytaxservice.dto.InspectionDto;
import ru.xpendence.mylovelytaxservice.service.InspectionService;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:38
 * e-mail: v.chernyshov@pflb.ru
 */
@CrossOrigin
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

    @GetMapping("/rules")
    public ResponseEntity<List<String>> getRules() {
        return ResponseEntity.ok(service.getRules());
    }

    @GetMapping("/check")
    public ResponseEntity<List<String>> getCheck() {
        return ResponseEntity.ok(service.getCheck());
    }
}
