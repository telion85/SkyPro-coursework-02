package site.telion.skyprocoursework02.service;

import org.springframework.stereotype.Service;
import site.telion.skyprocoursework02.exception.BigSizeAmountException;
import site.telion.skyprocoursework02.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private Random random; // todo: зачем это поле? см. пункт 6 курсовой
    private final QuestionService questionService;

    ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new BigSizeAmountException("Запрошено слишком много вопросов");
        }

        Set<Question> resultQuestions = new HashSet<>();
        while (resultQuestions.size() < amount) {
            resultQuestions.add(questionService.getRandomQuestion());
        }

        return resultQuestions;
    }
}
