package site.telion.skyprocoursework02.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import site.telion.skyprocoursework02.model.Question;
import site.telion.skyprocoursework02.repository.JavaQuestionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    private final Set<Question> questions = new HashSet<>();

    @Mock
    JavaQuestionRepository javaRepository;

    @InjectMocks
    JavaQuestionService expectedService;

    @BeforeEach
    void addElements() {
        questions.add(new Question("qu1", "an1"));
        questions.add(new Question("qu2", "an2"));
        questions.add(new Question("qu3", "an3"));
        questions.add(new Question("qu4", "an4"));
        Mockito.when(javaRepository.getAll()).thenReturn(questions);
    }

    @Test
    void remove() {
        expectedService.remove(new Question("qu2", "an2"));
        expectedService.remove(new Question("qu4", "an4"));

        QuestionService actualService = new JavaQuestionService(javaRepository);
        actualService.add(new Question("qu1", "an1"));
        actualService.add(new Question("qu3", "an3"));

        assertThat(expectedService.getAll())
                .isEqualTo(actualService.getAll());
    }

    @Test
    void testAdd() {
        QuestionService actualService = new JavaQuestionService(javaRepository);
        actualService.add("qu1", "an1");
        actualService.add("qu2", "an2");
        actualService.add("qu3", "an3");
        actualService.add("qu4", "an4");

        assertThat(expectedService.getAll())
                .isEqualTo(actualService.getAll());
    }

    @Test
    void getRandomQuestion() {
        Question question = expectedService.getRandomQuestion();
        assertThat(expectedService.getAll().contains(question))
                .isTrue();
    }
}