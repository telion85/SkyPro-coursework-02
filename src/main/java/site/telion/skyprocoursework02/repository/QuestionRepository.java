package site.telion.skyprocoursework02.repository;

import site.telion.skyprocoursework02.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

}
