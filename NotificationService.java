package ru.atv.rental.notification;

public interface NotificationService {
    void sendNotification(String message, String recipient);
}
