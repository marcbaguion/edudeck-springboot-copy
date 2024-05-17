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
public class QuizAnalyticsEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizAnalyticsId;

    private Date dateCreated;
    private Date dateLastQuizTaken;
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quiz_session_id", nullable = false)
    private QuizSessionEntity quizSession;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    public QuizAnalyticsEntity() {
    }

    public QuizAnalyticsEntity(int quizAnalyticsId, Date dateCreated, Date dateLastQuizTaken, boolean isDeleted,
            UserEntity user, QuizSessionEntity quizSession, QuizEntity quiz) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QuizSessionEntity getQuizSession() {
        return quizSession;
    }

    public void setQuizSession(QuizSessionEntity quizSession) {
        this.quizSession = quizSession;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    
}
