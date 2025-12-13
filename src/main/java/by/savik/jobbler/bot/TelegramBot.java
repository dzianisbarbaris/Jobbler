package by.savik.jobbler.bot;

import by.savik.jobbler.entity.Vacancy;
import by.savik.jobbler.exception.VacancyNotFoundException;
import by.savik.jobbler.service.CsvCreateServiceInterface;
import by.savik.jobbler.service.VacancyServiceInterface;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
@Slf4j
@Getter
public class TelegramBot extends TelegramLongPollingBot {

    private final VacancyServiceInterface vacancyService;
    private final CsvCreateServiceInterface csvCreateService;
    private final String botToken;
    private final String botUsername;

    @Autowired
    public TelegramBot(@Value("${telegram.bot.token}") String botToken,
                       @Value("${telegram.bot.username}") String botUsername,
                       VacancyServiceInterface vacancyService, CsvCreateServiceInterface csvCreateService) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.vacancyService = vacancyService;
        this.csvCreateService = csvCreateService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (messageText.startsWith("/start")) {
                sendMessage(chatId, """
                        👋 Добро пожаловать в Jobbler Bot!
                        
                        Я могу помочь вам в поиске вакансий.
                        
                        Используйте /help, чтобы просмотреть доступные команды.""");
            } else if (messageText.startsWith("/help")) {
                sendMessage(chatId, """
                        📋 Доступные команды:
                        
                        /start - Запуск бота
                        /help - Справочное сообщение
                        /search keyword - Поиск вакансий по ключевому слову (возвращает CSV файл)
                        
                        Пример: /search Java""");
            } else if (messageText.startsWith("/search galera")) {
                sendMessage(chatId, """
                        ❌ Уходите!!! Вам здесь не рады!!!
                        
                        За всей информацией обращайтесь по адресу https://galera.by/""");
            } else if (messageText.startsWith("/search")) {
                handleSearchCommand(chatId, messageText);
            } else {
                sendMessage(chatId, """
                        ❌ Я такой команды не знаю.
                        
                        Но я быстро учусь и когда-нибудь вам отвечу.""");
            }
        }
    }

    public void handleSearchCommand(Long chatId, String messageText) {
        String keyword = messageText.substring("/search".length()).trim();
        if (keyword.isEmpty()) {
            sendMessage(chatId, """
                    ❌ Пожалуйста, укажите ключевое слово для поиска.
                    
                    Пример: /search Java""");
            return;
        }
        searchVacancies(chatId, keyword);
    }


    public void searchVacancies(Long chatId, String keyword) {
        File csvFile = null;
        try {
            List<Vacancy> vacancies = vacancyService.getVacanciesByName(keyword);
            csvFile = csvCreateService.createCsvFile(vacancies, keyword);
            sendCsvFile(chatId, csvFile, keyword, vacancies.size());

        } catch (VacancyNotFoundException _) {
            sendMessage(chatId, "🔍 Не найдено вакансий по ключевому слову: \"" + keyword + "\"");
            log.debug("Вакансии не найдены по ключевому слову: {}", keyword);
        } catch (Exception e) {
            sendMessage(chatId, "❌ При поиске вакансий произошла ошибка. Пожалуйста, повторите попытку позже.");
            log.error("Ошибка при поиске вакансий по ключевому слову: {}", keyword, e);
        } finally {
            if (csvFile != null && csvFile.exists()) {
                try {
                    Files.delete(csvFile.toPath());
                } catch (IOException e) {
                    log.debug("Ошибка при удалении временного файла: {}", e.getMessage());
                }
            }
        }
    }

    private void sendCsvFile(Long chatId, File csvFile, String keyword, int count) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId.toString());
            sendDocument.setDocument(new InputFile(csvFile, "vacancies_" + keyword.replaceAll("[^a-zA-Zа-яА-Я0-9]", "_") + ".csv"));
            sendDocument.setCaption("✅ Найдено " + count + " вакансий по ключевому слову \"" + keyword + "\"\n\n" +
                    "📄 CSV-файл со всеми результатами.");
            
            execute(sendDocument);
        } catch (TelegramApiException e) {
            log.debug("Ошибка при отправке CSV-файла: {}", e.getMessage());
            sendMessage(chatId, "❌ Произошла ошибка при отправке CSV-файла. Пожалуйста, повторите попытку позже.");
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        message.setParseMode("HTML");
        
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.debug("Error sending message: {}", e.getMessage());
        }
    }
}

