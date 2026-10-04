import java.util.Scanner;

/* 
   Note: Assume the Node class is provided on Canvas and will be available during grading. 
   Do not submit the Node class; submit only this driver program.
*/

public class Assignment3 {

    // Method 1: Creates the linked list with even numbers first followed by odd numbers
    public static Node<Integer> createEvenOddList(int[] arr, int size) {
        Node<Integer> evenHead = null;
        Node<Integer> oddHead = null;
        Node<Integer> oddTail = null;

        for (int i = 0; i < size; i++) {
            int val = arr[i];
            if (val % 2 == 0) {
                // Prepend even numbers so they appear in the correct reverse order
                Node<Integer> newNode = new Node<>(val, evenHead);
                evenHead = newNode;
            } else {
                // Append odd numbers to maintain their original forward order
                Node<Integer> newNode = new Node<>(val, null);
                if (oddHead == null) {
                    oddHead = newNode;
                    oddTail = newNode;
                } else {
                    oddTail.setNext(newNode);
                    oddTail = newNode;
                }
            }
        }

        // If there are no even numbers, return the odd list head
        if (evenHead == null) {
            return oddHead;
        }

        // Find the tail of the even list to connect it to the odd list head
        Node<Integer> evenTail = evenHead;
        while (evenTail.getNext() != null) {
            evenTail = evenTail.getNext();
        }
        evenTail.setNext(oddHead);

        return evenHead;
    }

    // Method 2: Reads a shift value, validates it, and rotates the linked list
    public static Node<Integer> rotateList(Node<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        // Determine size and find the tail node
        int size = 0;
        Node<Integer> current = head;
        Node<Integer> tail = null;
        while (current != null) {
            tail = current;
            current = current.getNext();
            size++;
        }

        Scanner scanner = new Scanner(System.in);
        int shift = -1;

        // Error checking and validation loop
        while (true) {
            System.out.print("Enter the number of positions to shift (0 to " + (size - 1) + "): ");
            if (scanner.hasNextInt()) {
                shift = scanner.nextInt();
                if (shift >= 0 && shift < size) {
                    break;
                }
            } else {
                scanner.next(); // Clear invalid input
            }
            System.out.println("Error: Please enter a number between 0 and " + (size - 1) + ".");
        }

        if (shift == 0) {
            return head;
        }

        // Find the new tail (at index shift - 1) and the new head (at index shift)
        Node<Integer> newTail = head;
        for (int i = 0; i < shift - 1; i++) {
            newTail = newTail.getNext();
        }
        Node<Integer> newHead = newTail.getNext();

        // Perform the rotation
        newTail.setNext(null);
        tail.setNext(head);

        return newHead;
    }

    // Main driver method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Read array size and populate the array
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        // 2. Call Method 1 to build the linked list
        Node<Integer> head = createEvenOddList(arr, size);

        // Print initial linked list using a cursor
        System.out.print("Initial Linked List: ");
        Node<Integer> cursor = head;
        while (cursor != null) {
            System.out.print(cursor.getValue() + (cursor.getNext() != null ? " " : ""));
            cursor = cursor.getNext();
        }
        System.out.println();

        // 3. Call Method 2 to rotate the list
        head = rotateList(head);

        // 4. Print the resulting rotated linked list
        System.out.print("Rotated Linked List: ");
        cursor = head;
        while (cursor != null) {
            System.out.print(cursor.getValue() + (cursor.getNext() != null ? " " : ""));
            cursor = cursor.getNext();
        }
        System.out.println();
    }
}
