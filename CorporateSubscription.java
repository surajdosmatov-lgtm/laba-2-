package ru.atv.rental.model;

public class CorporateSubscription extends Subscription {

    private final int activeLicensesCount;

    public CorporateSubscription(String name, double basePrice, String tariff,
                                 int activeLicensesCount) {
        super(name, basePrice, tariff);
        if (activeLicensesCount < 1) {
            System.out.printf("[WARN] Количество лицензий %d некорректно. "
                    + "Установлено минимальное значение 1.%n", activeLicensesCount);
            activeLicensesCount = 1;
        }
        this.activeLicensesCount = activeLicensesCount;
    }

    public int getActiveLicensesCount() {
        return activeLicensesCount;
    }

    @Override
    public double calculateBillingPrice() {
        double price = pricePerHourWithTariff() * activeLicensesCount;
        if (getAppliedGear() != null) {
            price += getAppliedGear().getPricePerHour() * activeLicensesCount;
        }
        return price;
    }

    public double calculateBillingPrice(String promoCode) {
        if (promoCode != null && !promoCode.isBlank()) {
            System.out.printf("[WARN] Промокод '%s' нельзя применить "
                            + "к корпоративной подписке '%s'. Возвращена стандартная цена.%n",
                    promoCode, getName());
        }
        return calculateBillingPrice();
    }
}