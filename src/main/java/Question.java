import java.util.LinkedList;

public class Question {
    private final String question;
    private final String correctAnswer;
    private final LinkedList<String> incorrectAnswers;
    private final int questionNumber;

    public Question(int questionNumber,String question, String correctAnswer, LinkedList<String> incorrectAnswers) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public int getQuestionNumber() {
        return questionNumber;
    }
    public LinkedList<String> getIncorrectAnswers() {
        return new LinkedList<>(incorrectAnswers);
    }
}
