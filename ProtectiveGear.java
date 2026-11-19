/**
 * Защитная экипировка для квадроцикла.
 * Независимая сущность-услуга, которая может быть добавлена к аренде.
 */
package ru.atv.rental.option;
public class ProtectiveGear {

    private final String name;      // наименование экипировки
    private double pricePerHour;    // стоимость аренды за час

    /**
     * Полный конструктор.
     *
     * @param name         название экипировки
     * @param pricePerHour стоимость за час (не может быть отрицательной)
     */
    public ProtectiveGear(String name, double pricePerHour) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название экипировки не может быть пустым");
        }
        if (pricePerHour < 0) {
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.name = name;
        this.pricePerHour = pricePerHour;
    }

    public String getName() {
        return name;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    /**
     * Сеттер с валидацией: стоимость не может быть отрицательной.
     */
    public void setPricePerHour(double pricePerHour) {
        if (pricePerHour < 0) {
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return String.format("ProtectiveGear{name='%s', pricePerHour=%.2f}", name, pricePerHour);
    }
}