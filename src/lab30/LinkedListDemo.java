package lab30;

// Демонстрация работы всех списков
public class LinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== Часть 1: Односвязный список ===");
        SinglyLinkedList sll = new SinglyLinkedList();
        sll.addFirst(13);
        sll.addLast(23);
        sll.addLast(20);
        sll.addFirst(45);
        sll.display(); 
        System.out.println("Размер: " + sll.size());
        System.out.println("Содержит 23: " + sll.contains(23));
        sll.removeFirst();
        sll.removeLast();
        sll.display();
        sll.clear();
        System.out.println("После очистки - пустой: " + sll.isEmpty());
        
        System.out.println("\n=== Часть 2: Двусвязный список ===");
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addFirst(31);
        dll.addLast(26);
        dll.add(1, 12);
        dll.display();
        dll.displayReverse();
        System.out.println("Элемент по индексу 2: " + dll.get(2));
        dll.remove(1);
        dll.display();
        System.out.println("Первый: " + dll.getFirst() + ", Последний: " + dll.getLast());
        
        System.out.println("\n=== Часть 3: Циклический список ===");
        CircularLinkedList cll = new CircularLinkedList();
        cll.addLast(10);
        cll.addLast(20);
        cll.addLast(30);
        cll.addLast(40);
        cll.display(); 
        System.out.println("Размер: " + cll.size());
        
        cll.rotate();
        cll.display();
        
        System.out.println("Наличие цикла: " + cll.findCycle());
        System.out.println("Найден элемент 30: " + (cll.find(30) != null));
        
        System.out.println("\nРазделение списка на два:");
        CircularLinkedList[] splitLists = cll.splitIntoTwo();
        System.out.print("Первый список: ");
        splitLists[0].display();
        System.out.print("Второй список: ");
        splitLists[1].display(); 
    }
}