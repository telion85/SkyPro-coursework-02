package site.telion.skyprocoursework02.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import site.telion.skyprocoursework02.exception.BigSizeAmountException;
import site.telion.skyprocoursework02.model.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private final Set<Question> questions = new HashSet<>();

    @Mock
    private JavaQuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void addElements() {
        questions.add(new Question("qu1", "an1"));
        questions.add(new Question("qu2", "an2"));
        questions.add(new Question("qu3", "an3"));
        questions.add(new Question("qu4", "an4"));
        Mockito.when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    void getQuestionsPositive() {
        Mockito.when(questionService.getRandomQuestion()).thenReturn(new ArrayList<>(questions).get(1));

        Set<Question> actual = new HashSet<>();
        actual.add(new Question("qu4", "an4"));

        Assertions.assertThat(examinerService.getQuestions(1))
                .isEqualTo(actual);
    }

    @Test
    void getQuestionsNegative() {
        Assertions.assertThatExceptionOfType(BigSizeAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(7))
                .withMessage("Запрошено слишком много вопросов");
    }
}