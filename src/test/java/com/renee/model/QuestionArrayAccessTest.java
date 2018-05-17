package com.renee.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/*
 @author RenataS created 17.05.2018.
*/
public class QuestionArrayAccessTest {


      @Test
    public void testGetQuestion() {
	System.out.println("getQuestion");
	long index = 1;
	QuestionArrayAccess instance = new QuestionArrayAccess();
	Question expResult = (new QuestionBuilder())
		.id(1)
		.question("What was the first toy advertised on television?")
		.answerA("The Rubix cube")
		.answerB("Mr. Potato head")
		.answerC("Barbie")
		.answerD("A hula hoop")
		.correctAnswer("B")
		.hint("Use your head!")
		.lastUpdated(new Date())
		.build();
	Question result = instance.getQuestionById(index);
	assertEquals("Question 1 do not match in ::getQuestion().", expResult.getId(), result.getId());
	
    }

    @Test
    public void testGetRandomQuestion() {
	System.out.println("getRandomQuestion");
	QuestionArrayAccess instance = new QuestionArrayAccess();
	Question result = instance.getRandomQuestion();
	assertNotNull("Arandom question was not returned in ::getRandomQuestion().", result);
    }

    @Test
    public void testGetQuestionList() {
	System.out.println("getQuestionList");
	long offset = 0L;
	QuestionArrayAccess instance = new QuestionArrayAccess();
	List<Question> resultedList = instance.getQuestionList(offset);
	
	assertNotNull("Question list not returned in ::getQuestionList.", resultedList);
	assertEquals(10, resultedList.size());
	
    }

    @Test
    public void testGetSpecifiedQuestionList() {
	QuestionArrayAccess instance = new QuestionArrayAccess();
	List<Question> questionList = instance.getSpecifiedQuestionList(0L, 1L, 2L);
	List<Long> actual = questionList.stream()
		.map(Question::getId)
		.collect(Collectors.toList());
	List<Long> expected = Arrays.asList(0L, 1L, 2L);
	assertTrue(actual.containsAll(expected));
    }

    @Test
    public void testGetQuestionListSize() {
	System.out.println("getQuestionListSize");
	QuestionArrayAccess instance = new QuestionArrayAccess();
	long expResult = 11;
	long result = instance.getQuestionListSize();
	assertEquals("There should be only 11 questions in getQuestionListSize.", expResult, result);
    }

}
