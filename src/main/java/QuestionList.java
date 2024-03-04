import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionList {
    static Question question1 = new Question(
            1, """
            Что представляет собой этика делового общения?
               A) Набор правил и принципов, регулирующих поведение в бизнесе
               B) Методика продаж и маркетинга
               C) Отношения с клиентами
               D) Организация встреч и конференций""",
            "A", new LinkedList<>(Arrays.asList("B", "C", "D"))
    );
    static Question question2 = new Question(
            2, """
            Как можно решить этические дилеммы в процессе делового общения?
               A) Консультация с коллегами или экспертами
               B) Использование манипуляции и обмана
               C) Игнорирование проблемы
               D) Принятие первого попавшегося решения""",
            "A", new LinkedList<>(Arrays.asList("B", "C", "D"))
    );
    static Question question3 = new Question(
            3, """
             Какие инструменты можно использовать для повышения этичности в деловом общении?
               A) Проведение обучающих программ по этике
               B) Создание кодекса этики компании
               C) Регулярные проверки соответствия стандартам
               D) Все вышеперечисленное""",
            "B", new LinkedList<>(Arrays.asList("A", "C", "D"))
    );
    static Question question4 = new Question(
            4, """
            Какие правила следует соблюдать при использовании электронной почты и социальных сетей в деловом общении?
               A) Оскорбления и спам
               B) Распространение ложной информации
               C) Соблюдение конфиденциальности информации
               D) Все вышеперечисленное""",
            "C", new LinkedList<>(Arrays.asList("A", "B", "D"))
    );
    static Question question5 = new Question(
            5, """
            Как связана этика делового общения с корпоративной культурой?
               A) Она не имеет отношения к корпоративной культуре
               B) Она является основой для формирования ценностей компании
               C) Она противоречит целям компании
               D) Она не влияет на поведение сотрудников""",
            "B", new LinkedList<>(Arrays.asList("A", "C", "D"))
    );

    static List<Question> questionList = new ArrayList<>();
    public static void initQuestions() {
        questionList.addAll(Arrays.asList(question1,question2,question3,question4,question5));
    }
}

