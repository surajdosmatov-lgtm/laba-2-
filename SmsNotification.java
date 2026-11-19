package ru.atv.rental.notification.impl;

import ru.atv.rental.notification.NotificationService;

public class SmsNotification implements NotificationService {

    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient == null || recipient.length() < 11
                || !(recipient.startsWith("+7") || recipient.startsWith("8"))) {
            System.out.println("[SMS] ОШИБКА: некорректный номер '" + recipient
                    + "'. Уведомление не отправлено.");
            return;
        }
        System.out.printf("[SMS] На номер %s отправлено сообщение: %s%n", recipient, message);
    }
}