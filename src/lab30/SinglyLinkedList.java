package lab30;

// Класс Node для односвязного списка
class Node {
    int data;
    Node next;
    
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Класс SinglyLinkedList
class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    // 1. Добавление элемента в начало списка
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    
    // 2. Добавление элемента в конец списка
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    // 3. Удаление первого элемента
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        
        int removedData = head.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return removedData;
    }
    
    // 4. Удаление последнего элемента
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        
        int removedData = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
        return removedData;
    }
    
    // 5. Удаление элемента по значению
    public boolean remove(int data) {
        if (isEmpty()) {
            return false;
        }
        
        if (head.data == data) {
            removeFirst();
            return true;
        }
        
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        
        if (current.next == null) {
            return false;
        }
        
        if (current.next == tail) {
            tail = current;
        }
        
        current.next = current.next.next;
        size--;
        return true;
    }
    
    // 6. Проверка наличия элемента
    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // 7. Возврат размера списка
    public int size() {
        return size;
    }
    
    // 8. Проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }
    
    // 9. Вывод всех элементов списка
    public void display() {
        if (isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        
        Node current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    // 10. Очистка списка
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}





