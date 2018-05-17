package com.renee.model;

import java.util.Date;
import java.util.HashSet;

/*
 @author RenataS created 04.05.2018.
*/
public class QuestionBuilder {

    private long id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;
    private String hint;
    private Date lastUpdated;

    public QuestionBuilder() {
	super();
    }

    public Question build() {
	if ((id < 0) || ("".equals(this.question) || this.question.trim().isEmpty())
		|| ("".equals(this.answerA) || this.answerA.trim().isEmpty())
		|| ("".equals(this.answerB) || this.answerB.trim().isEmpty())
		|| ("".equals(this.answerC) || this.answerC.trim().isEmpty())
		|| ("".equals(this.answerD) || this.answerD.trim().isEmpty())
		|| ("".equals(this.correctAnswer) || this.correctAnswer.trim().isEmpty())
		|| ("".equals(this.hint) || this.hint.trim().isEmpty()) || (this.lastUpdated == null)) {
	    throw new IllegalStateException("TriviaQuestion builder not in buildable state.");
	}
	return new Question(this.id, this.question, this.answerA, this.answerB, this.answerC, this.answerD,
		this.correctAnswer, this.hint, this.lastUpdated);
    }

    public QuestionBuilder id(long id) {
	if (id < 0) {
	    throw new IllegalArgumentException("Id can't be less than 0");
	}
	this.id = id;
	return this;
    }

    public QuestionBuilder question(String question) {

	if (question == null || question.trim().isEmpty()) {
	    throw new IllegalArgumentException("Question cannot be null or empty");
	}
	this.question = question;
	return this;
    }

    public QuestionBuilder answerA(String answerA) {
	if (answerA == null || answerA.trim().isEmpty()) {
	    throw new IllegalArgumentException("Answer A cannot be null or empty.");
	}
	this.answerA = answerA;
	return this;
    }

    public QuestionBuilder answerB(String answerB) {
	if (answerB == null || answerB.trim().isEmpty()) {
	    throw new IllegalArgumentException("Answer B cannot be null or empty.");
	}
	this.answerB = answerB;
	return this;
    }

    public QuestionBuilder answerC(String answerC) {
	if (answerC == null || answerC.trim().isEmpty()) {
	    throw new IllegalArgumentException("Answer C cannot be null or empty.");
	}
	this.answerC = answerC;
	return this;
    }

    public QuestionBuilder answerD(String answerD) {
	if (answerD == null || answerD.trim().isEmpty()) {
	    throw new IllegalArgumentException("Answer D cannot be null or empty.");
	}
	this.answerD = answerD;
	return this;
    }

    public QuestionBuilder hint(String hint) {
	if (hint == null || hint.trim().isEmpty()) {
	    throw new IllegalArgumentException("Hint cannot be null or empty.");
	}
	this.hint = hint;
	return this;
    }

    public QuestionBuilder correctAnswer(String correctAnswer) {
	if (correctAnswer == null || correctAnswer.trim().isEmpty()) {
	    throw new IllegalArgumentException("Correct answer cannot be null or empty");
	}
	HashSet<String> set = new HashSet<>();
	set.add("A");
	set.add("B");
	set.add("C");
	set.add("D");
	if (!set.contains(correctAnswer)) {
	    throw new IllegalArgumentException("Correct answer must be 'A', 'B', 'C' or 'D'");
	}
	this.correctAnswer = correctAnswer;
	return this;
    }

    public QuestionBuilder lastUpdated(Date date) {
	if (date == null)
	    throw new IllegalArgumentException("Date cannot be null.");
	this.lastUpdated = date;
	return this;
    }
}
