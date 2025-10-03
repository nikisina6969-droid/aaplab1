package lab11;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите размер массива (чётное число):");
        int n = 0;
        while (true) {
            if (scan.hasNextInt()) {
                n = scan.nextInt();
                if (n > 0 && n % 2 == 0) break;
                else System.out.println("Ошибка! Введите положительное чётное число:");
            } else {
                System.out.println("Не число! Попробуйте ещё раз:");
                scan.next();
            }
        }
        scan.nextLine();

        int[] arr = new int[n];
        System.out.println("Вводите " + n + " чисел через пробел:");

        int count = 0;
        while (count < n) {
            String line = scan.nextLine();
            String[] parts = line.split("\\s+");
            for (String s : parts) {
                if (count == n) break;
                try {
                    arr[count] = Integer.parseInt(s);
                    count++;
                } catch (Exception e) {
                    System.out.println("Ошибка! Введите число заново:");
                    
                }
            }
        }

        int firstZero = -1;
        int lastZero = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (firstZero == -1) firstZero = i;
                lastZero = i;
            }
        }

        if (firstZero == -1 || lastZero == -1 || firstZero == lastZero) {
            System.out.println("В массиве должно быть минимум два нуля.");
            return;
        }

        int sizeB = lastZero - firstZero - 1;
        int[] B = new int[sizeB];
        for (int i = 0; i < sizeB; i++) {
            B[i] = arr[firstZero + 1 + i];
        }

        System.out.println("Новый массив B:");
        for (int val : B) {
            System.out.print(val + " ");
        }
        System.out.println("\nРазмер массива B: " + sizeB);
    }
}
