package lab30;

// Часть 2: Двусвязный список (Doubly Linked List)

// Класс Node для двусвязного списка (дженерик)
class DoublyNode<T> {
    T data;
    DoublyNode<T> prev;
    DoublyNode<T> next;
    
    public DoublyNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// Класс DoublyLinkedList
class DoublyLinkedList {
    private DoublyNode<Integer> head;
    private DoublyNode<Integer> tail;
    private int size;
    
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    // Методы из SinglyLinkedList
    public void addFirst(int data) {
        DoublyNode<Integer> newNode = new DoublyNode<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    
    public void addLast(int data) {
        DoublyNode<Integer> newNode = new DoublyNode<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    
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
            head.prev = null;
        }
        size--;
        return removedData;
    }
    
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        
        int removedData = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return removedData;
    }
    
    public boolean remove(int data) {
        if (isEmpty()) {
            return false;
        }
        
        DoublyNode<Integer> current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }
        
        if (current == null) {
            return false;
        }
        
        if (current == head) {
            removeFirst();
        } else if (current == tail) {
            removeLast();
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
        return true;
    }
    
    public boolean contains(int data) {
        DoublyNode<Integer> current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void display() {
        if (isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        
        DoublyNode<Integer> current = head;
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
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    // Дополнительные методы для DoublyLinkedList
    
    // 1. Вставка элемента по индексу
    public void add(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            DoublyNode<Integer> newNode = new DoublyNode<>(data);
            DoublyNode<Integer> current = getNode(index);
            
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }
    
    // 2. Удаление элемента по индексу
    public int removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            DoublyNode<Integer> nodeToRemove = getNode(index);
            int removedData = nodeToRemove.data;
            
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
            size--;
            
            return removedData;
        }
    }
    
    // 3. Получение элемента по индексу
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        return getNode(index).data;
    }
    
    // 4. Вывод элементов в обратном порядке
    public void displayReverse() {
        if (isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        
        DoublyNode<Integer> current = tail;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(", ");
            }
            current = current.prev;
        }
        System.out.println("]");
    }
    
    // 5. Получение первого элемента
    public int getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        return head.data;
    }
    
    // 6. Получение последнего элемента
    public int getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        return tail.data;
    }
    
    // Вспомогательный метод для получения узла по индексу
    private DoublyNode<Integer> getNode(int index) {
        if (index < size / 2) {
            // Идем с начала
            DoublyNode<Integer> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            // Идем с конца
            DoublyNode<Integer> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }
}