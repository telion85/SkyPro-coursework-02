package site.telion.skyprocoursework02.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import site.telion.skyprocoursework02.exception.BigSizeAmountException;
import site.telion.skyprocoursework02.model.Question;
import site.telion.skyprocoursework02.repository.JavaQuestionRepository;
import site.telion.skyprocoursework02.repository.MathQuestionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private final Set<Question> javaQuestions = new HashSet<>();
    private final Set<Question> mathQuestions = new HashSet<>();

    @Spy
    private JavaQuestionService javaQuestionService = new JavaQuestionService(new JavaQuestionRepository());

    @Spy
    private MathQuestionService mathQuestionService = new MathQuestionService(new MathQuestionRepository());

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void addElements() {

        javaQuestions.add(new Question("javaQu1", "javaAn1"));
        javaQuestions.add(new Question("javaQu2", "javaAn2"));
        javaQuestions.add(new Question("javaQu3", "javaAn3"));
        javaQuestions.add(new Question("javaQu4", "javaAn4"));
        Mockito.when(javaQuestionService.getAll()).thenReturn(javaQuestions);

        mathQuestions.add(new Question("mathQu1", "mathAn1"));
        mathQuestions.add(new Question("mathQu2", "mathAn2"));
        mathQuestions.add(new Question("mathQu3", "mathAn3"));
        Mockito.when(mathQuestionService.getAll()).thenReturn(mathQuestions);
    }

    @Test
    void getJavaQuestionsPositive() {
        Mockito.when(javaQuestionService.getRandomQuestion()).thenReturn(new ArrayList<>(javaQuestions).get(1));

        Set<Question> actual = new HashSet<>();
        actual.add(new Question("mathQu1", "mathAn1"));
        actual.add(new Question("mathQu2", "mathAn2"));
        actual.add(new Question("mathQu3", "mathAn3"));

        Assertions.assertThat(examinerService.getQuestions(1))
                .isEqualTo(actual);
    }

    @Test
    void getMathQuestionsPositive() {
        Mockito.when(mathQuestionService.getRandomQuestion()).thenReturn(new ArrayList<>(mathQuestions).get(1));

        Set<Question> actual = new HashSet<>();
        actual.add(new Question("qu4", "an4"));

        Assertions.assertThat(examinerService.getQuestions(1))
                .isEqualTo(actual);
    }

    @Test
    void getQuestionsNegative() {
        Assertions.assertThatExceptionOfType(BigSizeAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(3))
                .withMessage("Запрошено слишком много вопросов");
    }

}