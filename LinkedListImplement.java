import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LinkedListImplement extends JFrame {
    JTextField elementInput;
    JButton insertBtn, deleteBtn, findBtn, displayBtn;
    JTextArea outputArea;  // Text area for displaying output
    LinkedListOperation linkedListOp;

    public LinkedListImplement() {
        JFrame linkedListFrame = new JFrame();
        linkedListFrame.setVisible(true);
        linkedListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        linkedListFrame.setSize(600, 600);
        linkedListFrame.setLayout(null);
        linkedListFrame.getContentPane().setBackground(Color.BLACK);

        HomeButton homeButton = new HomeButton(linkedListFrame);
        linkedListFrame.add(homeButton.getHome());

        // Initialize LinkedListOperation
        linkedListOp = new LinkedListOperation(this);

        // Input for element
        JLabel elementLabel = new JLabel("Element:");
        elementLabel.setBounds(50, 80, 100, 30);
        elementLabel.setForeground(Color.white);
        linkedListFrame.add(elementLabel);

        elementInput = new JTextField();
        elementInput.setBounds(150, 80, 200, 30);
        linkedListFrame.add(elementInput);

        // Insert button
        insertBtn = new JButton("Insert");
        insertBtn.setBounds(50, 170, 100, 30);
        insertBtn.addActionListener(e -> {
            Object element = elementInput.getText();
            linkedListOp.insert(element); // Insert element
            elementInput.setText(""); // Clear input field after operation
        });
        linkedListFrame.add(insertBtn);

        // Delete button
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(160, 170, 100, 30);
        deleteBtn.addActionListener(e -> {
            Object element = elementInput.getText();
            linkedListOp.delete(element); // Delete element
            elementInput.setText(""); // Clear input field after operation
        });
        linkedListFrame.add(deleteBtn);

        // Find button
        findBtn = new JButton("Find");
        findBtn.setBounds(270, 170, 100, 30);
        findBtn.addActionListener(e -> {
            Object element = elementInput.getText();
            linkedListOp.find(element); // Find element in the linked list
            elementInput.setText(""); // Clear input field after operation
        });
        linkedListFrame.add(findBtn);

        // Display button
        displayBtn = new JButton("Display");
        displayBtn.setBounds(380, 170, 100, 30);
        displayBtn.addActionListener(e -> {
            linkedListOp.display(); // Display the linked list
            // Optionally, clear input field after operation
            elementInput.setText(""); // Optional: Clear input field after operation
        });
        linkedListFrame.add(displayBtn);

        // Text area to display output
        outputArea = new JTextArea();
        outputArea.setBounds(50, 220, 500, 300);
        outputArea.setEditable(false);
        linkedListFrame.add(outputArea);
    }

    public void updateOutputArea(String message) {
        outputArea.append(message + "\n");
    }
}

// LinkedListOperation class for performing operations on the doubly linked list
class LinkedListOperation {
    DoublyNode head;  // Head of the doubly linked list
    LinkedListImplement linkedListFrame;  // Reference to the frame for updating JTextArea

    public LinkedListOperation(LinkedListImplement linkedListFrame) {
        this.head = null;  // Initially, the linked list is empty
        this.linkedListFrame = linkedListFrame;  // Set reference to the frame
    }

    // Insert element at the end of the list
    void insert(Object element) {
        DoublyNode newNode = new DoublyNode(element);  // Create a new node
        if (head == null) {
            head = newNode;  // If list is empty, make new node the head
            linkedListFrame.updateOutputArea("Inserted element " + element + " at the head");
        } else {
            DoublyNode currentNode = head;
            while (currentNode.next != null) {  // Traverse to the end of the list
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;  // Set the new node as the last node
            newNode.prev = currentNode;  // Update the previous reference
            linkedListFrame.updateOutputArea("Inserted element " + element + " at the end");
        }
    }

    // Delete the element from the list
    void delete(Object element) {
        if (head == null) {
            linkedListFrame.updateOutputArea("Error: List is empty, cannot delete");
            return;
        }

        DoublyNode currentNode = head;

        // If the element to delete is the head
        if (currentNode.data.equals(element)) {
            if (currentNode.next != null) {
                currentNode.next.prev = null;
            }
            head = currentNode.next;
            linkedListFrame.updateOutputArea("Deleted element " + element + " from the head");
            return;
        }

        // Traverse the list to find the element
        while (currentNode != null && !currentNode.data.equals(element)) {
            currentNode = currentNode.next;
        }

        if (currentNode == null) {
            linkedListFrame.updateOutputArea("Error: Element " + element + " not found in the list");
        } else {
            if (currentNode.next != null) {
                currentNode.next.prev = currentNode.prev;
            }
            if (currentNode.prev != null) {
                currentNode.prev.next = currentNode.next;
            }
            linkedListFrame.updateOutputArea("Deleted element " + element + " from the list");
        }
    }

    // Find the element in the doubly linked list and display its presence
    void find(Object element) {
        DoublyNode currentNode = head;
        int index = 0;
        boolean found = false;

        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                linkedListFrame.updateOutputArea("Element " + element + " found at position " + index);
                found = true;
                break;
            }
            currentNode = currentNode.next;
            index++;
        }

        if (!found) {
            linkedListFrame.updateOutputArea("Element " + element + " not found in the list");
        }
    }

    // Display the current state of the doubly linked list
    void display() {
        if (head == null) {
            linkedListFrame.updateOutputArea("Doubly linked list is empty");
            return;
        }

        StringBuilder output = new StringBuilder("Doubly Linked List: [");
        DoublyNode currentNode = head;

        while (currentNode != null) {
            output.append(currentNode.data);
            if (currentNode.next != null) {
                output.append(" <-> ");
            }
            currentNode = currentNode.next;
        }
        output.append("]");
        linkedListFrame.updateOutputArea(output.toString());
    }
}
