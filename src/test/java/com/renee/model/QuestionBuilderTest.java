package com.renee.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/*
 @author RenataS created 17.05.2018.
*/
public class QuestionBuilderTest {

    @Test
    public void testBuild() {
	System.out.println("build");
	QuestionBuilder instance = new QuestionBuilder();
	Date currentDate = new Date();
	Question result = instance
		.id(1)
		.question("What was the first toy advertised on television?")
		.answerA("The Rubix cube")
		.answerB("Mr. Potato head")
		.answerC("Barbie")
		.answerD("A hula hoop")
		.correctAnswer("B")
		.hint("Use your head!")
		.lastUpdated(currentDate)
		.build();
	assertNotNull("Question build failed.", result);
	assertEquals("Ids do not match after build.", 1, result.getId());
	assertEquals("Questions do not match after build.",
		"What was the first toy advertised on television?", result.getQuestion());
	assertEquals("Answer A does not match after build", "The Rubix cube", result.getAnswerA());
	assertEquals("Answer B does not match after build", "Mr. Potato head", result.getAnswerB());
	assertEquals("Answer C does not match after build", "Barbie", result.getAnswerC());
	assertEquals("Answer D does not match after build", "A hula hoop", result.getAnswerD());
	assertEquals("Correct answer does not match after build","B", result.getCorrectAnswer());
	assertEquals("Hint does not match after build", "Use your head!", result.getHint());
	assertEquals("Last updated does not match after build.", currentDate, result.getLastUpdated());	
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildAnswerAState(){
	(new QuestionBuilder()).answerA(null).build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildAnswerBState(){
	(new QuestionBuilder()).answerB(null).build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildAnswerCState(){
	(new QuestionBuilder()).answerC(null).build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildAnswerDState(){
	(new QuestionBuilder()).answerD(null).build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildCorrectAnswerState(){
	(new QuestionBuilder()).correctAnswer(null).build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildHintState(){
	(new QuestionBuilder()).hint(null).build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildLastUpdatedDateFail(){
	(new QuestionBuilder()).lastUpdated(null).build();
    }
    
}
