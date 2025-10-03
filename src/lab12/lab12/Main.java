package lab12.lab12;

public class Main {

    

    public static void main(String[] args) {
        Ellipse el1 = new Ellipse();
        System.out.println("Введите первый эллипс:");
        el1.read();

        Ellipse el2 = new Ellipse();
        System.out.println("Введите второй эллипс:");
        el2.read();

        el1.display();
        el2.display();

        Ellipse el3 = Ellipse.add(el1, el2);
        System.out.println("Сумма эллипсов:");
        el3.display();
    }
}