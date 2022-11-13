package site.telion.skyprocoursework02.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.telion.skyprocoursework02.model.Question;

import static org.assertj.core.api.Assertions.assertThat;

class MathQuestionRepositoryTest {

    QuestionRepository expectedRepository = new MathQuestionRepository();

    @BeforeEach
    void addElements() {
        expectedRepository.add(new Question("qu1", "an1"));
        expectedRepository.add(new Question("qu2", "an2"));
        expectedRepository.add(new Question("qu3", "an3"));
        expectedRepository.add(new Question("qu4", "an4"));
    }

    @Test
    void add() {
        QuestionRepository actualRepository = new MathQuestionRepository();
        actualRepository.add(new Question("qu1", "an1"));
        actualRepository.add(new Question("qu2", "an2"));
        actualRepository.add(new Question("qu3", "an3"));
        actualRepository.add(new Question("qu4", "an4"));

        assertThat(expectedRepository.getAll())
                .isEqualTo(actualRepository.getAll());
    }

    @Test
    void remove() {
        expectedRepository.remove(new Question("qu2", "an2"));
        expectedRepository.remove(new Question("qu4", "an4"));

        QuestionRepository actualRepository = new MathQuestionRepository();
        actualRepository.add(new Question("qu1", "an1"));
        actualRepository.add(new Question("qu3", "an3"));

        assertThat(expectedRepository.getAll())
                .isEqualTo(actualRepository.getAll());
    }

}