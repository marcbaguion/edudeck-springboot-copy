package com.it332.edudeck.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblquizanalytics")
public class QuizAnalytics {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizAnalyticsId;

    private Date dateCreated;
    private Date dateLastQuizTaken;
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_session_id", nullable = false)
    private QuizSession quizSession;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public QuizAnalytics() {
    }

    public QuizAnalytics(int quizAnalyticsId, Date dateCreated, Date dateLastQuizTaken, boolean isDeleted,
                         User user, QuizSession quizSession, Quiz quiz) {
        this.quizAnalyticsId = quizAnalyticsId;
        this.dateCreated = dateCreated;
        this.dateLastQuizTaken = dateLastQuizTaken;
        this.isDeleted = isDeleted;
        this.user = user;
        this.quizSession = quizSession;
        this.quiz = quiz;
    }

    public int getQuizAnalyticsId() {
        return quizAnalyticsId;
    }

    public void setQuizAnalyticsId(int quizAnalyticsId) {
        this.quizAnalyticsId = quizAnalyticsId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastQuizTaken() {
        return dateLastQuizTaken;
    }

    public void setDateLastQuizTaken(Date dateLastQuizTaken) {
        this.dateLastQuizTaken = dateLastQuizTaken;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuizSession getQuizSession() {
        return quizSession;
    }

    public void setQuizSession(QuizSession quizSession) {
        this.quizSession = quizSession;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    
}
