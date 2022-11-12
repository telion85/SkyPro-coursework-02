package site.telion.skyprocoursework02.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.telion.skyprocoursework02.model.Question;
import site.telion.skyprocoursework02.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final QuestionService service;

    public MathQuestionController(@Qualifier("mathQuestions") QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Question addQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        Question currentQuestion = new Question(question, answer);
        return service.remove(currentQuestion);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}
