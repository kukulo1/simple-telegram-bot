import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.*;

public class QuizBot extends TelegramLongPollingBot {
    private final InlineKeyboardMarkup iKMarkup = new InlineKeyboardMarkup();
    private final List<InlineKeyboardButton> buttonRow1 = new ArrayList<>();
    private final List<InlineKeyboardButton> buttonRow2 = new ArrayList<>();
    private final List<InlineKeyboardButton> buttons = new ArrayList<>();

    InlineKeyboardButton button3 = new InlineKeyboardButton();
    InlineKeyboardButton button2 = new InlineKeyboardButton();
    InlineKeyboardButton button1 = new InlineKeyboardButton();
    InlineKeyboardButton button4 = new InlineKeyboardButton();
    Update previousUpdate;
    Map <String, Boolean> usersStatuses = new HashMap<>();//turns true when /start is typed and false when other update comes
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            usersStatuses.putIfAbsent(update.getMessage().getChatId().toString(), false);
            if (update.getMessage().getText().equalsIgnoreCase("/start") && !usersStatuses.get(update.getMessage().getChatId().toString())) {
                usersStatuses.put(update.getMessage().getChatId().toString(), true);
                try {
                    setButtons(QuestionList.question1);
                    execute(setMessage(update,QuestionList.question1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (!usersStatuses.get(update.getMessage().getChatId().toString())) {
                try {
                    execute(new SendMessage(update.getMessage().getChatId().toString(), "Извините, я вас не понимаю... \n" +
                            "Пропишите команду /start и думаю, что мы найдем общий язык! :)"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            else if (usersStatuses.get(update.getMessage().getChatId().toString())){
                try {
                    execute(new SendMessage(update.getMessage().getChatId().toString(), "Вы не можете начинать несколько попыток сразу! \n" +
                            "Закончите предыдущую попытку!!!"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        buttonRow1.clear();
        buttonRow2.clear();
        buttons.clear();
        if (update.hasCallbackQuery()) {
            String temp = update.getCallbackQuery().getData();
            if (temp.contains("1")) {
                if (temp.contains("incorrect")) {
                    editMessage(update, "Извините, вы допустили ошибку! \n" +
                            "Пропишите /start и попробуйте еще раз!");
                    usersStatuses.put(update.getCallbackQuery().getMessage().getChatId().toString(), false);
                    return;
                }
                try {
                    editMessage(update, "Правильно, вы молодец!");
                    setButtons(QuestionList.question2);
                    execute(setMessage(update,QuestionList.question2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (temp.contains("2")) {
                if (temp.contains("incorrect")) {
                    editMessage(update, "Извините, вы допустили ошибку! \n" +
                            "Пропишите /start и попробуйте еще раз!");
                    usersStatuses.put(update.getCallbackQuery().getMessage().getChatId().toString(), false);
                    return;
                }
                try {
                    editMessage(update, "И снова правильно! Похоже вы внимательно слушали :)");
                    setButtons(QuestionList.question3);
                    execute(setMessage(update,QuestionList.question3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (temp.contains("3")) {
                if (temp.contains("incorrect")) {
                    editMessage(update, "Извините, вы допустили ошибку! \n" +
                            "Пропишите /start и попробуйте еще раз!");
                    usersStatuses.put(update.getCallbackQuery().getMessage().getChatId().toString(), false);
                    return;
                }
                try {
                    editMessage(update, "Я поражен вашей решимостью! \n" +
                            "Как насчет такого вопроса?");
                    setButtons(QuestionList.question4);
                    execute(setMessage(update,QuestionList.question4));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (temp.contains("4")) {
                if (temp.contains("incorrect")) {
                    editMessage(update, "Извините, вы допустили ошибку! \n" +
                            "Пропишите /start и попробуйте еще раз!");
                    usersStatuses.put(update.getCallbackQuery().getMessage().getChatId().toString(), false);
                    return;
                }
                try {
                    editMessage(update, "Действительно так. Вы почти достигли цели!");
                    setButtons(QuestionList.question5);
                    execute(setMessage(update,QuestionList.question5));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    usersStatuses.put(update.getCallbackQuery().getMessage().getChatId().toString(), false);
                    editMessage(update, "Ух ты! Это был последний вопрос ;)");
                    execute(new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(),
                            "Поздравляю, вы победили! \n" +
                                    "Кодовое слово: орех"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            buttonRow1.clear();
            buttonRow2.clear();
            buttons.clear();
        }
        previousUpdate = update;
    }
    @Override
    public String getBotUsername() {
        return PrivateData.BOT_USERNAME;
    }
    @Override
    public String getBotToken() {
        return PrivateData.BOT_TOKEN;
    }
    private void setButtons(Question question) {
        LinkedList<String> incorrectAnswers = question.getIncorrectAnswers();
        button1.setText(question.getCorrectAnswer());
        button1.setCallbackData("correct" + question.getQuestionNumber());
        button2.setText(incorrectAnswers.pop());
        button2.setCallbackData("incorrect"  + question.getQuestionNumber());
        button3.setText(incorrectAnswers.pop());
        button3.setCallbackData("incorrect"  + question.getQuestionNumber());

        button4.setText(incorrectAnswers.pop());
        button4.setCallbackData("incorrect"  + question.getQuestionNumber());
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        Collections.shuffle(buttons);

        buttonRow1.add(buttons.get(0));
        buttonRow1.add(buttons.get(1));
        buttonRow2.add(buttons.get(2));
        buttonRow2.add(buttons.get(3));

        iKMarkup.setKeyboard(new ArrayList<>(Arrays.asList(buttonRow1,buttonRow2)));
    }
    private SendMessage setMessage(Update update, Question question) {
        SendMessage sendMessage;
        if (update.hasCallbackQuery()) {
            sendMessage = new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(), question.getQuestion());
        } else {
            sendMessage = new SendMessage(update.getMessage().getChatId().toString(), question.getQuestion());
        }
        sendMessage.setReplyMarkup(iKMarkup);
        return sendMessage;
    }
    private void editMessage(Update update, String text) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(callbackQuery.getMessage().getChatId());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());
        editMessageText.setText(text);
        editMessageText.setReplyMarkup(null);
        try {
            execute(editMessageText);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
