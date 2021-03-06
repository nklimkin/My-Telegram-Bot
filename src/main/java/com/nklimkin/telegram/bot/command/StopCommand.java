package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
public class StopCommand implements Command {

    private SendBotMessageService botMessageService;
    private TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Пока, брат";

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        botMessageService.sendMessage(chatId, STOP_MESSAGE);

        telegramUserService.getByChatId(chatId).ifPresent(user -> {
            user.setActive(false);
            telegramUserService.save(user);
        });
    }
}
