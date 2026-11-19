package ru.atv.rental.model;

import ru.atv.rental.option.ProtectiveGear;

/**
 * Абстрактная подписка/квадроцикл — общая сущность.
 * Общих правил расчёта больше нет — calculateBillingPrice() абстрактный.
 */
public abstract class Subscription {

    protected static final double COEFF_STANDARD = 1.0;   // Standard (0%)
    protected static final double COEFF_SPORT    = 1.5;   // Sport (+50%)
    protected static final double COEFF_MONSTER  = 2.1;   // Monster-Track (+110%)

    protected static final double MIN_BASE_PRICE = 100.0;

    private final String name;
    private final double basePrice;
    private final String tariff;

    private ProtectiveGear appliedGear = null;

    public Subscription(String name, double basePrice, String tariff) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (tariff == null || tariff.isBlank()) {
            throw new IllegalArgumentException("Тариф не может быть пустым");
        }
        if (basePrice <= 0) {
            System.out.printf("[WARN] Базовая цена %.2f некорректна. "
                    + "Установлена минимальная цена %.2f%n", basePrice, MIN_BASE_PRICE);
            basePrice = MIN_BASE_PRICE;
        }
        this.name = name;
        this.basePrice = basePrice;
        this.tariff = tariff;
    }

    public Subscription(String name, double basePrice) {
        this(name, basePrice, "Standard");
    }

    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public String getTariff() { return tariff; }
    public ProtectiveGear getAppliedGear() { return appliedGear; }

    public void setAppliedGear(ProtectiveGear gear) {
        this.appliedGear = gear;
    }

    protected double getTariffCoefficient() {
        return switch (tariff) {
            case "Standard" -> COEFF_STANDARD;
            case "Sport"    -> COEFF_SPORT;
            case "Monster-Track" -> COEFF_MONSTER;
            default -> {
                System.out.printf("[WARN] Неизвестный тариф '%s'. "
                        + "Применён коэффициент 1.0 (Standard).%n", tariff);
                yield 1.0;
            }
        };
    }

    protected double pricePerHourWithTariff() {
        return basePrice * getTariffCoefficient();
    }

    public abstract double calculateBillingPrice();

    @Override
    public String toString() {
        return String.format(
                "%s{name='%s', basePrice=%.2f, tariff='%s', gear=%s}",
                getClass().getSimpleName(), name, basePrice, tariff,
                appliedGear == null ? "нет" : appliedGear.getName()
        );
    }
}