package by.savik.jobbler.config;

import by.savik.jobbler.bot.TelegramBotService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    private final TelegramBotService bot;


    public TelegramBotConfig(TelegramBotService bot) {
        this.bot = bot;
    }

    @PostConstruct
    public void registerBot(){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
            System.out.println("Телеграм бот успешно зарегистрирован!");
        } catch (TelegramApiException e) {
            System.err.println("Ошибка при регистрации бота! " + e.getMessage());;
        }
    }
}
