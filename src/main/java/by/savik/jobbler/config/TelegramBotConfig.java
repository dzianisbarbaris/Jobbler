package by.savik.jobbler.config;

import by.savik.jobbler.bot.TelegramBot;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Slf4j
public class TelegramBotConfig {
    private final TelegramBot bot;

    public TelegramBotConfig(TelegramBot bot) {
        this.bot = bot;
    }

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
            log.info("Телеграм бот успешно зарегистрирован!");
        } catch (TelegramApiException e) {
            log.debug("Ошибка при регистрации бота! {}", e.getMessage());
        }
    }
}
