package com.chilly.examlibrary.entity;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */
public class Answer {
    private Long answer_id;
    private Long problem_id;
    private Long answer_book_id;
    private String answer_content;
    private Long answer_student_id;
    private int answer_score;

    private Student student;  //关联学生

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getAnswer_book_id() {
        return answer_book_id;
    }

    public void setAnswer_book_id(Long answer_book_id) {
        this.answer_book_id = answer_book_id;
    }

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public Long getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Long problem_id) {
        this.problem_id = problem_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public Long getAnswer_student_id() {
        return answer_student_id;
    }

    public void setAnswer_student_id(Long answer_student_id) {
        this.answer_student_id = answer_student_id;
    }

    public int getAnswer_score() {
        return answer_score;
    }

    public void setAnswer_score(int answer_score) {
        this.answer_score = answer_score;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id=" + answer_id +
                ", problem_id=" + problem_id +
                ", answer_book_id=" + answer_book_id +
                ", answer_content='" + answer_content + '\'' +
                ", answer_student_id=" + answer_student_id +
                ", answer_score='" + answer_score + '\'' +
                '}';
    }
}
