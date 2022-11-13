package site.telion.skyprocoursework02.controller;

import org.springframework.web.bind.annotation.*;
import site.telion.skyprocoursework02.model.Question;
import site.telion.skyprocoursework02.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable Integer amount) {
        return service.getQuestions(amount);
    }


}
