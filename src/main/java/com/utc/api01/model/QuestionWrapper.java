package com.utc.api01.model;

import java.util.List;

public class QuestionWrapper {
    private List<Question> questionList;

    /**
     * 
     */
    public QuestionWrapper() {
        super();
    }

    /**
     * @param questionList
     */
    public QuestionWrapper(List<Question> questionList) {
        super();
        this.questionList = questionList;
    }
    
    /**
     * @return the questionList
     */
    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * @param questionList the questionList to set
     */
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
