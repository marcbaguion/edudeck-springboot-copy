package com.it332.edudeck.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblquizsession")
public class QuizSessionEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizSessionId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @OneToOne
    @JoinColumn(name = "current_quiz_item_id")
    private QuizItemEntity currentQuizItem;

    public QuizSessionEntity() {
    }

    public QuizSessionEntity(int quizSessionId, LocalDateTime startTime, LocalDateTime endTime, UserEntity user,
            QuizEntity quiz, QuizItemEntity currentQuizItem) {
        this.quizSessionId = quizSessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.quiz = quiz;
        this.currentQuizItem = currentQuizItem;
    }

    public int getQuizSessionId() {
        return quizSessionId;
    }

    public void setQuizSessionId(int quizSessionId) {
        this.quizSessionId = quizSessionId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public QuizItemEntity getCurrentQuizItem() {
        return currentQuizItem;
    }

    public void setCurrentQuizItem(QuizItemEntity currentQuizItem) {
        this.currentQuizItem = currentQuizItem;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    
}
