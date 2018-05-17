package com.renee.model;

import java.util.List;

/*
@author RenataS created ${date}
*/
public interface QuestionAccessible {

    Question getQuestionByIndex(long index);
    
    Question getQuestionById(long id);
    
    Question getRandomQuestion();
    
    List<Question> getQuestionList(long offset);
    
    List<Question> getSpecifiedQuestionList(long... id);
    
    long getQuestionListSize();
    
    
}
