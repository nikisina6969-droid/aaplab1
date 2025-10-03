package lab14;

import java.util.Scanner;

public class Main {

    public record Ellipse(double a, double b) {
        public Ellipse {
            if (!(a > b && b > 0)) {
                throw new IllegalArgumentException("Должно быть a > b > 0");
            }
        }

        public double area() {
            return Math.PI * a * b;
        }

        public static Ellipse add(Ellipse e1, Ellipse e2) {
            double newA = e1.a + e2.a;
            double newB = e1.b + e2.b;
            if (newA > newB) {
                return new Ellipse(newA, newB);
            } else {
                return new Ellipse(newB, newA);
            }
        }

        // Метод для создания объекта Ellipse с пользовательским вводом
        public static Ellipse read(Scanner sc) {
            double a, b;
            while (true) {
                System.out.print("Введите a (большую полуось, > b): ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Ошибка. Введите число:");
                    sc.next();
                }
                a = sc.nextDouble();

                System.out.print("Введите b (малую полуось, > 0): ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Ошибка. Введите число:");
                    sc.next();
                }
                b = sc.nextDouble();

                if (a > b && b > 0) {
                    break;
                } else {
                    System.out.println("Неправильный ввод. Убедитесь, что a > b > 0");
                }
            }
            return new Ellipse(a, b);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные первого эллипса:");
        Ellipse e1 = Ellipse.read(scanner);

        System.out.println("Введите данные второго эллипса:");
        Ellipse e2 = Ellipse.read(scanner);

        System.out.println("Первый эллипс: " + e1);
        System.out.printf("Площадь: %.2f\n", e1.area());

        System.out.println("Второй эллипс: " + e2);
        System.out.printf("Площадь: %.2f\n", e2.area());

        Ellipse sum = Ellipse.add(e1, e2);
        System.out.println("Сумма эллипсов: " + sum);
        System.out.printf("Площадь суммы: %.2f\n", sum.area());
    }
}
