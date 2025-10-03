package lab12.lab12;



import java.util.Scanner;

public class Ellipse {
    private double a; // большая полуось
    private double b; // малая полуось

    // init - инициализация
    public void init(double a, double b) {
        if (a > b && b > 0) {
            this.a = a;
            this.b = b;
        } else {
            System.out.println("Ошибка: должно быть a > b > 0");
            this.a = 1;
            this.b = 0.5;
        }
    }

    // read - ввод с клавиатуры
    public void read() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Введите a (большую полуось) (> b): ");
            double inputA = sc.nextDouble();
            System.out.print("Введите b (малую полуось) (> 0): ");
            double inputB = sc.nextDouble();

            if (inputA > inputB && inputB > 0) {
                this.a = inputA;
                this.b = inputB;
                break;
            } else {
                System.out.println("Неверный ввод. Попробуйте ещё раз.");
            }
        }
    }

    // display - вывод значений
    public void display() {
        System.out.printf("Эллипс с большой полуосью a=%.2f и малой полуосью b=%.2f\n", a, b);
        System.out.printf("Площадь эллипса: %.4f\n", getArea());
    }

    // геттер для a
    public double getA() {
        return a;
    }

    // сеттер для a
    public void setA(double newA) {
        if (newA > b) {
            a = newA;
        } else {
            System.out.println("Ошибка: a должно быть больше b");
        }
    }

    // Метод вычисления площади эллипса pi * a * b
    public double getArea() {
        return Math.PI * a * b;
    }

    // Статический метод сложения двух эллипсов - складывает a и b
    public static Ellipse add(Ellipse e1, Ellipse e2) {
        Ellipse result = new Ellipse();
        double newA = e1.a + e2.a;
        double newB = e1.b + e2.b;

        // чтобы a > b всегда, если не так - меняем местами
        if (newA > newB) {
            result.init(newA, newB);
        } else {
            result.init(newB, newA);
        }
        return result;
    }

}
