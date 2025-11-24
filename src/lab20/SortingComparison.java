package lab20;

import java.util.Arrays;
import java.util.Random;

public class SortingComparison {

    // Сортировка выбором
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    // Сортировка слиянием
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);
            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i=0, j=0, k=0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // Перемешивание сортировка 
    public static void cocktailSort(int[] arr) {
        boolean swapped = true;
        int start = 0;
        int end = arr.length - 1;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (arr[i] > arr[i+1]) {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
            swapped = false;
            end--;
            for (int i = end-1; i >= start; i--) {
                if (arr[i] > arr[i+1]) {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swapped = true;
                }
            }
            start++;
        }
    }

    // Генерация массивов
    public static int[] generateRandomArray(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        for (int i=0; i<size; i++) arr[i] = r.nextInt(size*10);
        return arr;
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        Arrays.sort(arr);
        return arr;
    }

    public static int[] generateReverseSortedArray(int size) {
        int[] arr = generateSortedArray(size);
        for (int i=0; i<size/2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
        return arr;
    }

    public static int[] generateArrayWithDuplicates(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        int uniqueCount = Math.max(1, size/10);
        for (int i=0; i<size; i++) arr[i] = r.nextInt(uniqueCount);
        return arr;
    }

    public static int[] generatePartiallySortedArray(int size) {
        int[] arr = generateSortedArray(size);
        Random r = new Random();
        int shuffleCount = size / 4;
        for (int i=0; i<shuffleCount; i++) {
            int idx1 = r.nextInt(size);
            int idx2 = r.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }

    public static int[] generateAlmostSortedArray(int size) {
        int[] arr = generateSortedArray(size);
        Random r = new Random();
        int shuffleCount = size / 10; // 10% на своих местах, значит 90% отсортированы
        for (int i=0; i<shuffleCount; i++) {
            int idx1 = r.nextInt(size);
            int idx2 = r.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }

    // Замер времени сортировки (лучшее из 5 запусков)
    public static long measureTime(Runnable sortingMethod) {
        long bestTime = Long.MAX_VALUE;
        for (int i=0; i<5; i++) {
            long start = System.nanoTime();
            sortingMethod.run();
            long end = System.nanoTime();
            long elapsed = end - start;
            if (elapsed < bestTime) bestTime = elapsed;
        }
        return bestTime;
    }

    // Проверка отсортированности массива
    public static boolean isSorted(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};

        String[] arrayTypes = {
            "Случайный массив", "Частично отсортированный (75%)", "Обратно отсортированный",
            "Массив с дублями", "Почти отсортированный (90%)"
        };

        for(int size : sizes) {
            System.out.println("\n=== Размер массива: " + size + " ===");

            for(String type : arrayTypes) {
                int[] arr;
                switch (type) {
                    case "Случайный массив": arr = generateRandomArray(size); break;
                    case "Частично отсортированный (75%)": arr = generatePartiallySortedArray(size); break;
                    case "Обратно отсортированный": arr = generateReverseSortedArray(size); break;
                    case "Массив с дублями": arr = generateArrayWithDuplicates(size); break;
                    case "Почти отсортированный (90%)": arr = generateAlmostSortedArray(size); break;
                    default: arr = generateRandomArray(size);
                }

                System.out.println("\n-- " + type + " --");

                long timeSelection = measureTime(() -> {
                    int[] copy = arr.clone();
                    selectionSort(copy);
                    if (!isSorted(copy)) System.out.println("Ошибка в сортировке выбором!");
                });
                System.out.printf("Сортировка выбором: %d нс (%.3f мс)\n", timeSelection, timeSelection / 1_000_000.0);

                long timeMerge = measureTime(() -> {
                    int[] copy = arr.clone();
                    mergeSort(copy);
                    if (!isSorted(copy)) System.out.println("Ошибка в сортировке слиянием!");
                });
                System.out.printf("Сортировка слиянием: %d нс (%.3f мс)\n", timeMerge, timeMerge / 1_000_000.0);

                long timeCocktail = measureTime(() -> {
                    int[] copy = arr.clone();
                    cocktailSort(copy);
                    if (!isSorted(copy)) System.out.println("Ошибка в сортировке перемешиванием!");
                });
                System.out.printf("сортировка Перемешиванием: %d нс (%.3f мс)\n", timeCocktail, timeCocktail / 1_000_000.0);

                // Кто быстрее
                if (timeSelection < timeMerge && timeSelection < timeCocktail)
                    System.out.println("Лучший алгоритм: Сортировка выбором");
                else if (timeMerge < timeSelection && timeMerge < timeCocktail)
                    System.out.println("Лучший алгоритм: Сортировка слиянием");
                else
                    System.out.println("Лучший алгоритм: Сортировка перемешиванием");
            }
        }
    }
}
    