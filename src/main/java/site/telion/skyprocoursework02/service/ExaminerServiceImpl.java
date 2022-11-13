package site.telion.skyprocoursework02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import site.telion.skyprocoursework02.exception.BigSizeAmountException;
import site.telion.skyprocoursework02.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    final private Random random = new Random();

    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;

    @Autowired
    public ExaminerServiceImpl(
            @Qualifier("mathQuestions") QuestionService mathQuestionService,
            @Qualifier("javaQuestions") QuestionService javaQuestionService) {
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        System.out.println("Arrays.toString(mathQuestionService.getAll().toArray()) = " + Arrays.toString(mathQuestionService.getAll().toArray()));
        System.out.println("Arrays.toString(javaQuestionService.getAll().toArray()) = " + Arrays.toString(javaQuestionService.getAll().toArray()));
        int totalQuestionsCount = mathQuestionService.getAll().size() + javaQuestionService.getAll().size();

        if (amount > totalQuestionsCount) {
            throw new BigSizeAmountException("Запрошено слишком много вопросов");
        }

        Set<Question> resultQuestions = new HashSet<>();
        while (resultQuestions.size() < amount) {
            if (random.nextInt(2) == 1) {
                resultQuestions.add(mathQuestionService.getRandomQuestion());
            } else {
                resultQuestions.add(javaQuestionService.getRandomQuestion());
            }
        }

        return resultQuestions;
    }
}
