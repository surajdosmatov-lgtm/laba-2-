package ru.atv.rental.servise;

import ru.atv.rental.model.Subscription;
import ru.atv.rental.notification.NotificationService;

/**
 * Сервисный слой учёта оплат.
 * Зависимость от способа уведомлений внедряется через конструктор (DI).
 */
public class Billing {

    private static int totalSubscribersCount = 0;
    private static double totalRevenue = 0.0;

    private final NotificationService notificationService;

    public Billing(NotificationService notificationService) {
        if (notificationService == null) {
            throw new IllegalArgumentException("notificationService не может быть null");
        }
        this.notificationService = notificationService;
    }

    public static int getTotalSubscribersCount() { return totalSubscribersCount; }
    public static double getTotalRevenue() { return totalRevenue; }

    public void processPayment(Subscription sub, String contact, double finalPrice) {
        if (sub == null) {
            System.out.println("[WARN] processPayment: подписка не передана.");
            return;
        }
        if (finalPrice <= 0) {
            System.out.printf("[WARN] processPayment: некорректная цена %.2f. "
                    + "Оплата не зафиксирована.%n", finalPrice);
            return;
        }

        totalSubscribersCount++;
        totalRevenue += finalPrice;

        String message = String.format(
                "Чек: '%s', тариф %s, сумма %.2f руб. Спасибо за оплату!",
                sub.getName(), sub.getTariff(), finalPrice
        );
        notificationService.sendNotification(message, contact);
    }

    public static void printGlobalStats() {
        System.out.println("=========================================");
        System.out.println("    ГЛОБАЛЬНАЯ СТАТИСТИКА ATV-КЛУБА      ");
        System.out.println("=========================================");
        System.out.printf("Всего оплаченных подписок: %d%n", totalSubscribersCount);
        System.out.printf("Суммарная выручка:         %.2f руб.%n", totalRevenue);
        System.out.println("=========================================");
    }
}