package lab30;

// Часть 3: Циклический односвязный список (Circular Linked List)

// Класс CircularLinkedList (выбран вариант циклического односвязного списка)
class CircularLinkedList {
    private Node last; // Последний элемент, который ссылается на первый
    private int size;
    
    public CircularLinkedList() {
        last = null;
        size = 0;
    }
    
    // Все базовые методы из SinglyLinkedList
    
    // Добавление элемента в начало списка
    public void addFirst(int data) {
        Node newNode = new Node(data);
        
        if (isEmpty()) {
            last = newNode;
            newNode.next = newNode; // Ссылка на себя
        } else {
            newNode.next = last.next;
            last.next = newNode;
        }
        size++;
    }
    
    // Добавление элемента в конец списка
    public void addLast(int data) {
        addFirst(data);
        last = last.next; // Сдвигаем last на новый элемент
    }
    
    // Удаление первого элемента
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        
        Node first = last.next;
        int removedData = first.data;
        
        if (last == last.next) { // Только один элемент
            last = null;
        } else {
            last.next = first.next;
        }
        
        size--;
        return removedData;
    }
    
    // Удаление последнего элемента
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        
        if (last == last.next) { // Только один элемент
            return removeFirst();
        }
        
        // Находим предпоследний элемент
        Node current = last.next;
        while (current.next != last) {
            current = current.next;
        }
        
        int removedData = last.data;
        current.next = last.next;
        last = current;
        
        size--;
        return removedData;
    }
    
    // Удаление элемента по значению
    public boolean remove(int data) {
        if (isEmpty()) {
            return false;
        }
        
        if (last.next.data == data) {
            removeFirst();
            return true;
        }
        
        if (last.data == data) {
            removeLast();
            return true;
        }
        
        Node current = last.next;
        Node prev = last;
        
        do {
            if (current.data == data) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        } while (current != last.next);
        
        return false;
    }
    
    // Проверка наличия элемента
    public boolean contains(int data) {
        if (isEmpty()) {
            return false;
        }
        
        Node current = last.next;
        do {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        } while (current != last.next);
        
        return false;
    }
    
    // Размер списка
    public int size() {
        return size;
    }
    
    // Проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Вывод всех элементов списка
    public void display() {
        if (isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        
        Node current = last.next;
        System.out.print("[");
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != last.next) {
                System.out.print(", ");
            }
        } while (current != last.next);
        System.out.println("]");
    }
    
    // Очистка списка
    public void clear() {
        last = null;
        size = 0;
    }
    
    // Специальные методы для CircularLinkedList
    
    // 1. Циклический сдвиг списка (первый элемент становится последним)
    public void rotate() {
        if (!isEmpty()) {
            last = last.next;
        }
    }
    
    // 2. Проверка наличия цикла (должна всегда возвращать true)
    public boolean findCycle() {
        // В циклическом списке всегда есть цикл
        return !isEmpty();
    }
    
    // 3. Поиск элемента с учетом циклической природы
    public Node find(int data) {
        if (isEmpty()) {
            return null;
        }
        
        Node current = last.next;
        do {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        } while (current != last.next);
        
        return null;
    }
    
    // 4. Разделение списка на два равных циклических списка
    public CircularLinkedList[] splitIntoTwo() {
        CircularLinkedList[] result = new CircularLinkedList[2];
        result[0] = new CircularLinkedList();
        result[1] = new CircularLinkedList();
        
        if (isEmpty() || size == 1) {
            if (!isEmpty()) {
                result[0].addLast(last.next.data);
            }
            return result;
        }
        
        int splitIndex = size / 2;
        Node current = last.next;
        
        // Первая половина
        for (int i = 0; i < splitIndex; i++) {
            result[0].addLast(current.data);
            current = current.next;
        }
        
        // Вторая половина
        for (int i = splitIndex; i < size; i++) {
            result[1].addLast(current.data);
            current = current.next;
        }
        
        return result;
    }
}