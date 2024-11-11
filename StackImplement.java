import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StackImplement extends JFrame {
    JTextField elementInput;
    JButton pushBtn, popBtn, findBtn, displayBtn;
    JTextArea outputArea;  // Text area for displaying output
    StackOperation stackOp;

    public StackImplement() {
        JFrame stackFrame = new JFrame();
        stackFrame.setVisible(true);
        stackFrame.setSize(600, 600);
        stackFrame.setLayout(null);
        stackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stackFrame.getContentPane().setBackground(Color.BLACK);

        HomeButton homeButton = new HomeButton(stackFrame);
        stackFrame.add(homeButton.getHome());

        // Initialize StackOperation with no fixed size (as it's a linked list)
        stackOp = new StackOperation(this);

        // Input for element
        JLabel elementLabel = new JLabel("Element:");
        elementLabel.setBounds(50, 80, 100, 30);
        elementLabel.setForeground(Color.white);
        stackFrame.add(elementLabel);

        elementInput = new JTextField();
        elementInput.setBounds(150, 80, 200, 30);
        stackFrame.add(elementInput);

        // Push button
        pushBtn = new JButton("Push");
        pushBtn.setBounds(50, 170, 100, 30);
        pushBtn.addActionListener(e -> {
            Object element = elementInput.getText();
            stackOp.push(element); // Push element
            elementInput.setText(""); // Clear input field after operation
        });
        stackFrame.add(pushBtn);

        // Pop button
        popBtn = new JButton("Pop");
        popBtn.setBounds(160, 170, 100, 30);
        popBtn.addActionListener(e -> {
            stackOp.pop(); // Pop element
            elementInput.setText(""); // Clear input field after operation
        });
        stackFrame.add(popBtn);

        // Find button (instead of Peek)
        findBtn = new JButton("Find");
        findBtn.setBounds(270, 170, 100, 30);
        findBtn.addActionListener(e -> {
            Object element = elementInput.getText();
            stackOp.find(element); // Find element
            elementInput.setText(""); // Clear input field after operation
        });
        stackFrame.add(findBtn);

        // Display button
        displayBtn = new JButton("Display");
        displayBtn.setBounds(380, 170, 100, 30);
        displayBtn.addActionListener(e -> {
            stackOp.display(); // Display stack
            elementInput.setText(""); // Optional: Clear input field after operation
        });
        stackFrame.add(displayBtn);

        // Text area to display output
        outputArea = new JTextArea();
        outputArea.setBounds(50, 220, 500, 300);
        outputArea.setEditable(false);
        stackFrame.add(outputArea);
    }

    public void updateOutputArea(String message) {
        outputArea.append(message + "\n");
    }
}

class StackOperation {
    Node top; // Points to the top of the stack
    StackImplement stackFrame;  // Reference to StackImplement frame for updating JTextArea

    public StackOperation(StackImplement stackFrame) {
        this.top = null;  // Initially, the stack is empty
        this.stackFrame = stackFrame;  // Set reference to the Stack frame
    }

    // Push an element onto the stack
    void push(Object element) {
        Node newNode = new Node(element);  // Create a new node
        newNode.next = top;  // Link the new node to the current top
        top = newNode;  // Update the top to the new node
        stackFrame.updateOutputArea("Pushed element " + element + " onto the stack");
    }

    // Pop the top element from the stack
    void pop() {
        if (top == null) {
            stackFrame.updateOutputArea("Error: Stack is empty, cannot pop element");
        } else {
            Object poppedElement = top.data;  // Get the data from the top node
            top = top.next;  // Move the top to the next node (effectively removing the top)
            stackFrame.updateOutputArea("Popped element " + poppedElement + " from the stack");
        }
    }

    // Find the element in the stack and display its index
    void find(Object element) {
        if (top == null) {
            stackFrame.updateOutputArea("Stack is empty");
            return;
        }

        Node currentNode = top;
        int index = 0;
        boolean found = false;

        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                found = true;
                stackFrame.updateOutputArea("Element " + element + " found in the stack");
                break;
            }
            currentNode = currentNode.next;
            index++;
        }

        if (!found) {
            stackFrame.updateOutputArea("Element " + element + " not found in the stack");
        }
    }

    // Display the current state of the stack
    void display() {
        if (top == null) {
            stackFrame.updateOutputArea("Stack is empty");
            return;
        }

        StringBuilder output = new StringBuilder("Stack: [");
        Node currentNode = top;
        while (currentNode != null) {
            output.append(currentNode.data);
            if (currentNode.next != null) {
                output.append(", ");
            }
            currentNode = currentNode.next;
        }
        output.append("]");
        stackFrame.updateOutputArea(output.toString());
    }
}

