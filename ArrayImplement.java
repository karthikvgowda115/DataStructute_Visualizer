import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ArrayImplement extends JFrame {
    JTextField arrSize;
    JButton createBtn;
    static ArrayOperation arrayOp;

    public ArrayImplement() {
        JFrame arrFrame = new JFrame();
        arrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        arrFrame.setVisible(true);
        arrFrame.setSize(500, 500);
        arrFrame.setLayout(null);
        arrFrame.getContentPane().setBackground(Color.BLACK);

        HomeButton homeButton = new HomeButton(arrFrame);
        arrFrame.add(homeButton.getHome());

        JLabel size = new JLabel("Size:");
        size.setBounds(50, 80, 100, 30);
        size.setForeground(Color.white);
        arrFrame.add(size);

        arrSize = new JTextField("Enter Array Size");
        arrSize.setBounds(150, 80, 200, 30);
        arrFrame.add(arrSize);

        createBtn = new JButton("Create Array");
        createBtn.setBounds(150, 170, 200, 40);
        createBtn.setFocusable(false);
        arrFrame.add(createBtn);

        createBtn.addActionListener(e -> {
            new Array(Integer.parseInt(arrSize.getText())); // Open array operation frame and pass size
        });
    }
}

class Array extends JFrame {
    JTextField elementInput, indexInput;
    JButton insertBtn, deleteBtn, findBtn, displayBtn;
    JTextArea outputArea;  // Text area for displaying output
    ArrayOperation arrayOp;

    public Array(int size) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);

        HomeButton homeButton = new HomeButton(this);
        this.add(homeButton.getHome());

        // Initialize ArrayOperation with the size and JTextArea reference
        arrayOp = new ArrayOperation(size, this);

        // Input for element
        JLabel elementLabel = new JLabel("Element:");
        elementLabel.setBounds(50, 80, 100, 30);
        elementLabel.setForeground(Color.white);
        this.add(elementLabel);

        elementInput = new JTextField();
        elementInput.setBounds(150, 80, 200, 30);
        this.add(elementInput);

        // Input for index
        JLabel indexLabel = new JLabel("Index:");
        indexLabel.setBounds(50, 120, 100, 30);
        indexLabel.setForeground(Color.white);
        this.add(indexLabel);

        indexInput = new JTextField();
        indexInput.setBounds(150, 120, 200, 30);
        this.add(indexInput);

        // Insert button
        insertBtn = new JButton("Insert");
        insertBtn.setBounds(50, 170, 100, 30);
        insertBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexInput.getText());
                Object element = elementInput.getText();
                arrayOp.insertion(element, index); // Insert element
                // Clear input fields
                elementInput.setText("");
                indexInput.setText("");
            } catch (NumberFormatException ex) {
                updateOutputArea("Error: Please enter a valid index.");
            }
        });
        this.add(insertBtn);

        // Delete button
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(160, 170, 100, 30);
        deleteBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexInput.getText());
                arrayOp.delete(index); // Delete element
                // Clear input fields
                indexInput.setText("");
            } catch (NumberFormatException ex) {
                updateOutputArea("Error: Please enter a valid index.");
            }
        });
        this.add(deleteBtn);

        // Find button
        findBtn = new JButton("Find");
        findBtn.setBounds(270, 170, 100, 30);
        findBtn.addActionListener(e -> {
            Object element = elementInput.getText();
            arrayOp.find(element); // Find element
            // Clear input field
            elementInput.setText("");
        });
        this.add(findBtn);

        // Display button
        displayBtn = new JButton("Display");
        displayBtn.setBounds(380, 170, 100, 30);
        displayBtn.addActionListener(e -> {
            arrayOp.display(); // Display array
        });
        this.add(displayBtn);

        // Text area to display output
        outputArea = new JTextArea();
        outputArea.setBounds(50, 220, 500, 300);
        outputArea.setEditable(false);
        this.add(outputArea);
    }

    public void updateOutputArea(String message) {
        outputArea.append(message + "\n");
    }
}

// Updated ArrayOperation class to work with JTextArea
class ArrayOperation {
    static Object[] arr;
    Array arrayFrame;  // Reference to Array class for updating JTextArea

    public ArrayOperation(int size, Array arrayFrame) {
        arr = new Object[size];  // Initialize array with the given size
        this.arrayFrame = arrayFrame;  // Set reference to the Array frame
    }

    void insertion(Object element, int index) {
        if (index >= 0 && index < arr.length) {
            arr[index] = element;
            arrayFrame.updateOutputArea("Inserted element " + element + " at index " + index);
        } else {
            arrayFrame.updateOutputArea("Error: Index " + index + " out of bounds");
        }
    }

    void display() {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            output.append(arr[i]);
            if (i < arr.length - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        arrayFrame.updateOutputArea("Array: " + output.toString());
    }

    void delete(int index) {
        if (index >= 0 && index < arr.length && arr[index] != null) {
            arrayFrame.updateOutputArea("Deleted element " + arr[index] + " at index " + index);
            arr[index] = null;
        } else {
            arrayFrame.updateOutputArea("Error: Invalid index or no element to delete at index " + index);
        }
    }

    void find(Object element) {
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(element)) {
                arrayFrame.updateOutputArea("Element " + element + " found at index " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            arrayFrame.updateOutputArea("Element " + element + " not found in the array");
        }
    }
}
