package ru.atv.rental.notification.impl;

import ru.atv.rental.notification.NotificationService;

public class EmailNotification implements NotificationService {

    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient == null || !recipient.contains("@") || !recipient.contains(".")) {
            System.out.println("[EMAIL] ОШИБКА: некорректный адрес '" + recipient
                    + "'. Уведомление не отправлено.");
            return;
        }
        System.out.printf("[EMAIL] На адрес %s отправлено письмо: %s%n", recipient, message);
    }
}