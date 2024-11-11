import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CircularQueueImplement {
    private JTextArea outputArea; // Instance variable for output area
    private CircularQueue circularQueue; // Circular queue instance

    public CircularQueueImplement() {
        JFrame circularQueueFrame = new JFrame("Circular Queue Implementation");
        circularQueueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        circularQueueFrame.setSize(600, 600);
        circularQueueFrame.getContentPane().setBackground(Color.BLACK);
        circularQueueFrame.setLayout(null); // Setting layout to null for absolute positioning

        HomeButton homeButton = new HomeButton(circularQueueFrame);
        circularQueueFrame.add(homeButton.getHome());

        // Initialize CircularQueue with a capacity (e.g., 5)
        circularQueue = new CircularQueue(5);

        // Input for element
        JLabel elementLabel = new JLabel("Element:");
        elementLabel.setBounds(50, 80, 100, 30);
        elementLabel.setForeground(Color.white);
        circularQueueFrame.add(elementLabel);

        JTextField elementInput = new JTextField();
        elementInput.setBounds(150, 80, 200, 30);
        circularQueueFrame.add(elementInput);

        // Enqueue button
        JButton enqueueBtn = new JButton("Enqueue");
        enqueueBtn.setBounds(50, 170, 100, 30);
        enqueueBtn.addActionListener(e -> {
            String element = elementInput.getText();
            circularQueue.enqueue(element); // Enqueue element
            updateOutputArea("Enqueued: " + element);
            elementInput.setText(""); // Clear input field after enqueue
        });
        circularQueueFrame.add(enqueueBtn);

        // Dequeue button
        JButton dequeueBtn = new JButton("Dequeue");
        dequeueBtn.setBounds(160, 170, 100, 30);
        dequeueBtn.addActionListener(e -> {
            Object element = circularQueue.dequeue(); // Dequeue element
            updateOutputArea("Dequeued: " + (element != null ? element : "Queue is empty"));
        });
        circularQueueFrame.add(dequeueBtn);

        // Display button
        JButton displayBtn = new JButton("Display");
        displayBtn.setBounds(270, 170, 100, 30);
        displayBtn.addActionListener(e -> {
            String displayOutput = circularQueue.display(); // Get display output
            updateOutputArea(displayOutput); // Update the output area with the display string
        });
        circularQueueFrame.add(displayBtn);

        // Text area to display output
        outputArea = new JTextArea(); // Initialize the output area here
        outputArea.setBounds(50, 220, 500, 300);
        outputArea.setEditable(false);
        circularQueueFrame.add(outputArea);

        circularQueueFrame.setVisible(true); // Set the frame visible at the end
    }

    // Method to update the output area
    public void updateOutputArea(String message) {
        outputArea.append(message + "\n"); // Use the instance variable directly
    }
}

class CircularQueue {
    private Object[] queue; // Array to store elements
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
        front = rear = size = 0;
    }

    // Enqueue method to add an element to the queue
    public void enqueue(Object element) {
        if (size == capacity) {
            System.out.println("Queue is full. Cannot enqueue " + element);
            return;
        }
        queue[rear] = element; // Add element at the rear
        rear = (rear + 1) % capacity; // Circular increment
        size++;
    }

    // Dequeue method to remove an element from the queue
    public Object dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty. Cannot dequeue");
            return null;
        }
        Object item = queue[front];
        queue[front] = null; // Optional: Clear the reference
        front = (front + 1) % capacity; // Circular increment
        size--;
        return item;
    }

    // Display the current elements in the queue and return the output string
    public String display() {
        if (size == 0) {
            return "Circular Queue is empty"; // Return empty message
        }

        StringBuilder output = new StringBuilder("Circular Queue: [");
        for (int i = 0; i < size; i++) {
            output.append(queue[(front + i) % capacity]);
            if (i < size - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        return output.toString(); // Return the display string
    }
}
