
package lab20;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SortingComparison {
    
    // Интерфейс для функций сортировки - позволяет передавать методы сортировки как параметры
    interface SortFunction {
        void sort(int[] arr);
    }
    
    // 1. Сортировка выбором - простейший алгоритм O(n²)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Находим минимальный элемент в неотсортированной части
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Меняем местами найденный минимальный элемент с первым неотсортированным
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    // 2. Сортировка слиянием - эффективный алгоритм O(n log n)
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    
    // Рекурсивная часть сортировки слиянием
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // Сортируем левую и правую половины рекурсивно
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // Объединяем отсортированные половины
            merge(arr, left, mid, right);
        }
    }
    
    // Метод для слияния двух отсортированных половин
    private static void merge(int[] arr, int left, int mid, int right) {
        // Временный массив для хранения merged результата
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        // Сливаем элементы из двух половин в временный массив
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        // Добиваем остатки из левой половины (если есть)
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        
        // Добиваем остатки из правой половины (если есть)
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        // Копируем отсортированный временный массив обратно в оригинальный
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
    
    // 3. Шейкерная сортировка (cocktail sort) - улучшенная пузырьковая O(n²)
    public static void cocktailSort(int[] arr) {
        boolean swapped = true;
        int start = 0;
        int end = arr.length;
        
        while (swapped) {
            swapped = false;
            
            // Проход слева направо как в пузырьковой сортировке
            for (int i = start; i < end - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    // Меняем элементы местами
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            
            // Если не было обменов - массив отсортирован
            if (!swapped) break;
            
            swapped = false;
            end--; // Уменьшаем правую границу (последний элемент на месте)
            
            // Проход справа налево
            for (int i = end - 1; i >= start; i--) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            
            start++; // Увеличиваем левую границу (первый элемент на месте)
        }
    }
    
    // === ГЕНЕРАТОРЫ МАССИВОВ ===
    
    // Генерация случайного массива
    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }
    
    // Генерация отсортированного массива
    public static int[] generateSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        Arrays.sort(arr);
        return arr;
    }
    
    // Генерация обратно отсортированного массива
    public static int[] generateReverseSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        Arrays.sort(arr);
        // Разворачиваем массив
        for (int i = 0; i < size / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
        return arr;
    }
    
    // Генерация массива с дубликатами
    public static int[] generateArrayWithDuplicates(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        // Только 10% уникальных значений для создания дубликатов
        int uniqueValues = Math.max(1, size / 10);
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(uniqueValues);
        }
        return arr;
    }
    
    // Генерация ЧАСТИЧНО ОТСОРТИРОВАННОГО массива (75% элементов на своих местах)
    public static int[] generatePartiallySortedArray(int size) {
        // Сначала создаем полностью отсортированный массив
        int[] arr = generateSortedArray(size);
        Random random = new Random();
        
        // Вычисляем сколько элементов нужно перемешать (25%)
        int elementsToShuffle = size / 4;
        
        // Перемешиваем случайные 25% элементов
        for (int i = 0; i < elementsToShuffle; i++) {
            // Выбираем два случайных индекса для обмена
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            
            // Меняем элементы местами
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        
        return arr;
    }
    
    // === СИСТЕМА ТЕСТИРОВАНИЯ ===
    
    // Измерение времени выполнения сортировки
    public static long measureTime(SortFunction sortingFunction, int[] arr) {
        int[] copy = arr.clone(); // Работаем с копией чтобы не испортить оригинал
        
        long startTime = System.nanoTime();
        sortingFunction.sort(copy);
        long endTime = System.nanoTime();
        
        return endTime - startTime;
    }
    
    // Проверка что массив отсортирован корректно
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    // Основной метод тестирования с многократными запусками
    public static void runTests() {
        int[] sizes = {100, 1000, 10000}; // Малый, средний, большой размеры
        
        System.out.println("=== СРАВНЕНИЕ АЛГОРИТМОВ СОРТИРОВКИ ===");
        System.out.println("Каждый алгоритм тестируется 5 раз, берется лучшее время");
        
        // Создаем ссылки на методы сортировки
        SortFunction selection = SortingComparison::selectionSort;
        SortFunction merge = SortingComparison::mergeSort;
        SortFunction cocktail = SortingComparison::cocktailSort;
        
        for (int size : sizes) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("РАЗМЕР МАССИВА: " + size + " элементов");
            System.out.println("=".repeat(60));
            
            // Тестируем на разных типах массивов
            testAllAlgorithms("СЛУЧАЙНЫЙ МАССИВ", generateRandomArray(size), selection, merge, cocktail);
            testAllAlgorithms("ОТСОРТИРОВАННЫЙ МАССИВ", generateSortedArray(size), selection, merge, cocktail);
            testAllAlgorithms("ОБРАТНО ОТСОРТИРОВАННЫЙ", generateReverseSortedArray(size), selection, merge, cocktail);
            testAllAlgorithms("МАССИВ С ДУБЛИКАТАМИ", generateArrayWithDuplicates(size), selection, merge, cocktail);
            testAllAlgorithms("ЧАСТИЧНО ОТСОРТИРОВАННЫЙ (75%)", generatePartiallySortedArray(size), selection, merge, cocktail);
        }
        
    }
     // Тестирование одного алгоритма 5 раз с возвратом лучшего времени
    private static long testAlgorithmMultipleTimes(String algorithmName, SortFunction algorithm, int[] originalArray) {
        long bestTime = Long.MAX_VALUE; // Начальное значение - максимально возможное
        
        for (int i = 0; i < 5; i++) {
            long time = measureTime(algorithm, originalArray);
            bestTime = Math.min(bestTime, time); // Берем минимальное (лучшее) время
            
            // Проверяем корректность сортировки
            int[] testCopy = originalArray.clone();
            algorithm.sort(testCopy);
            if (!isSorted(testCopy)) {
                System.out.println("ОШИБКА: Алгоритм " + algorithmName + " работает некорректно!");
            }
        }
        
        System.out.printf("  %-15s: %d нс (%.3f мс)\n", 
                         algorithmName, bestTime, bestTime / 1_000_000.0);
        
        return bestTime;
    }
    // Тестирование всех алгоритмов на одном типе массива
    private static void testAllAlgorithms(String arrayType, int[] originalArray, 
                                         SortFunction selection, SortFunction merge, SortFunction cocktail) {
        System.out.println("\n--- " + arrayType + " ---");
        
        // Тестируем каждый алгоритм 5 раз и берем лучшее время
        long selectionTime = testAlgorithmMultipleTimes("Выбором", selection, originalArray);
        long mergeTime = testAlgorithmMultipleTimes("Слиянием", merge, originalArray);
        long cocktailTime = testAlgorithmMultipleTimes("Перемешиванием", cocktail, originalArray);
        
        // Определяем победителя
        printWinner(selectionTime, mergeTime, cocktailTime);
    }
    
   
    
    // Определение и вывод лучшего алгоритма для текущего теста
    private static void printWinner(long selectionTime, long mergeTime, long cocktailTime) {
        long fastest = Math.min(selectionTime, Math.min(mergeTime, cocktailTime));
        
        System.out.print("  Лучший алгоритм: ");
        if (fastest == selectionTime) {
            System.out.println("Выбором");
        } else if (fastest == mergeTime) {
            System.out.println("Слиянием");
        } else {
            System.out.println("Перемешиванием");
        }
    }
    

    
}