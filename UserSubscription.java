package ru.atv.rental.model;

public class UserSubscription extends Subscription {

    public UserSubscription(String name, double basePrice, String tariff) {
        super(name, basePrice, tariff);
    }

    public UserSubscription(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double calculateBillingPrice() {
        double price = pricePerHourWithTariff();
        if (getAppliedGear() != null) {
            price += getAppliedGear().getPricePerHour();
        }
        return price;
    }
}