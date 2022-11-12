package site.telion.skyprocoursework02.service;

import site.telion.skyprocoursework02.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
