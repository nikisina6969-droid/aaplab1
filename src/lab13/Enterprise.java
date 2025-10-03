package lab13;

import java.util.Scanner;

class Building {
    private int area; // площадь
    private double pricePerM2; // цена за квадратный метр

    // Инициализация
    public void init(int area, double pricePerM2) {
        this.area = area;
        this.pricePerM2 = pricePerM2;
    }

    // Ввод значений с клавиатуры
    public void read() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите площадь здания (целое число): ");
        while (!sc.hasNextInt()) {
            System.out.println("Ошибка! Введите целое число для площади:");
            sc.next();
        }
        area = sc.nextInt();

        System.out.print("Введите цену за квадратный метр (вещественное число): ");
        while (!sc.hasNextDouble()) {
            System.out.println("Ошибка! Введите число с плавающей точкой для цены за м2:");
            sc.next();
        }
        pricePerM2 = sc.nextDouble();
    }

    // Вычисление стоимости здания
    public double getCost() {
        return area * pricePerM2;
    }

    // Вывод информации на экран
    public void display() {
        System.out.printf("Площадь: %d м2, цена за м2: %.2f, стоимость: %.2f\n", area, pricePerM2, getCost());
    }
}

public class Enterprise {
    private String name;  // название предприятия
    private Building b1;
    private Building b2;
    private Building b3;
    private double otherFacilitiesCost; // стоимость дополнительных помещений

    // Инициализация
    public void init(String name, double otherFacilitiesCost) {
        this.name = name;
        this.otherFacilitiesCost = otherFacilitiesCost;
        b1 = new Building();
        b2 = new Building();
        b3 = new Building();
    }

    // Ввод данных
    public void read() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название предприятия: ");
        name = sc.nextLine();

        System.out.println("Введите данные для здания 1:");
        b1.read();
        System.out.println("Введите данные для здания 2:");
        b2.read();
        System.out.println("Введите данные для здания 3:");
        b3.read();

        System.out.print("Введите стоимость дополнительных помещений (вещественное число): ");
        while (!sc.hasNextDouble()) {
            System.out.println("Ошибка! Введите корректное число:");
            sc.next();
        }
        otherFacilitiesCost = sc.nextDouble();
    }

    // Вывод информации
    public void display() {
        System.out.println("Предприятие: " + name);
        System.out.println("Здание 1:");
        b1.display();
        System.out.println("Здание 2:");
        b2.display();
        System.out.println("Здание 3:");
        b3.display();
        System.out.printf("Стоимость дополнительных помещений: %.2f\n", otherFacilitiesCost);
        System.out.printf("Общая стоимость предприятия: %.2f\n", getTotalCost());
        System.out.println("Самое дорогое здание:");
        getMostExpensiveBuilding().display();
    }

    // Счёт общей стоимости
    public double getTotalCost() {
        return b1.getCost() + b2.getCost() + b3.getCost() + otherFacilitiesCost;
    }

    // Определение самого дорогого здания
    public Building getMostExpensiveBuilding() {
        Building maxBuilding = b1;
        if (b2.getCost() > maxBuilding.getCost()) maxBuilding = b2;
        if (b3.getCost() > maxBuilding.getCost()) maxBuilding = b3;
        return maxBuilding;
    }

    
}
