import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class QueueImplement {
    private JTextField elementInput;
    private JButton enqueueBtn, dequeueBtn, displayBtn;
    private JTextArea outputArea; // Text area for displaying output
    private QueueOperation queueOp;

    public QueueImplement() {
        JFrame queueFrame = new JFrame();
        queueFrame.setVisible(true);
        queueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        queueFrame.setSize(600, 600);
        queueFrame.setLayout(null);
        queueFrame.getContentPane().setBackground(Color.BLACK);

        HomeButton homeButton = new HomeButton(queueFrame);
        queueFrame.add(homeButton.getHome());

        // Initialize QueueOperation
        queueOp = new QueueOperation(this);

        // Input for element
        JLabel elementLabel = new JLabel("Element:");
        elementLabel.setBounds(50, 80, 100, 30);
        elementLabel.setForeground(Color.white);
        queueFrame.add(elementLabel);

        elementInput = new JTextField();
        elementInput.setBounds(150, 80, 200, 30);
        queueFrame.add(elementInput);

        // Enqueue button
        enqueueBtn = new JButton("Enqueue");
        enqueueBtn.setBounds(50, 170, 100, 30);
        enqueueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object element = elementInput.getText();
                queueOp.enqueue(element); // Enqueue element
                elementInput.setText(""); // Clear input field after operation
            }
        });
        queueFrame.add(enqueueBtn);

        // Dequeue button
        dequeueBtn = new JButton("Dequeue");
        dequeueBtn.setBounds(160, 170, 100, 30);
        dequeueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queueOp.dequeue(); // Dequeue element
                elementInput.setText(""); // Clear input field after operation
            }
        });
        queueFrame.add(dequeueBtn);

        // Display button
        displayBtn = new JButton("Display");
        displayBtn.setBounds(270, 170, 100, 30);
        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queueOp.display(); // Display the queue
                elementInput.setText(""); // Clear input field after operation
            }
        });
        queueFrame.add(displayBtn);

        // Text area to display output
        outputArea = new JTextArea();
        outputArea.setBounds(50, 220, 500, 300);
        outputArea.setEditable(false);
        queueFrame.add(outputArea);
    }

    public void updateOutputArea(String message) {
        outputArea.append(message + "\n");
    }
}


// QueueOperation class for performing operations on the queue
class QueueOperation {
    private Node front; // Points to the front of the queue
    private Node rear;  // Points to the rear of the queue
    private QueueImplement queueFrame; // Reference to the QueueImplement frame for updating JTextArea

    public QueueOperation(QueueImplement queueFrame) {
        this.front = null; // Initially, the queue is empty
        this.rear = null; // Initially, the queue is empty
        this.queueFrame = queueFrame; // Set reference to the QueueImplement frame
    }

    // Enqueue an element to the rear of the queue
    void enqueue(Object element) {
        Node newNode = new Node(element); // Create a new node
        if (rear == null) { // If the queue is empty
            front = newNode; // Both front and rear point to the new node
            rear = newNode;
            queueFrame.updateOutputArea("Enqueued element " + element);
        } else { // If the queue is not empty
            rear.next = newNode; // Link the new node at the end of the queue
            rear = newNode; // Update the rear to the new node
            queueFrame.updateOutputArea("Enqueued element " + element);
        }
    }

    // Dequeue an element from the front of the queue
    void dequeue() {
        if (front == null) { // If the queue is empty
            queueFrame.updateOutputArea("Error: Queue is empty, cannot dequeue");
            return;
        }
        Object dequeuedElement = front.data; // Get the data from the front node
        front = front.next; // Move the front to the next node
        if (front == null) { // If the queue becomes empty
            rear = null; // Update rear to null
        }
        queueFrame.updateOutputArea("Dequeued element " + dequeuedElement);
    }

    // Display the current state of the queue
    void display() {
        if (front == null) { // If the queue is empty
            queueFrame.updateOutputArea("Queue is empty");
            return;
        }

        StringBuilder output = new StringBuilder("Queue: [");
        Node currentNode = front;

        while (currentNode != null) {
            output.append(currentNode.data);
            if (currentNode.next != null) {
                output.append(", ");
            }
            currentNode = currentNode.next;
        }
        output.append("]");
        queueFrame.updateOutputArea(output.toString());
    }
}
