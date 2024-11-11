import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DsImplementation {
    public static void main(String[] args) {
        new DsFrame();
    }
}

class DsFrame extends JFrame {
    static JFrame myFrame;

    public DsFrame() {
        myFrame = new JFrame("Data Structures Implementation");

        // Create a label for the title
        JLabel label = new JLabel();
        label.setText("Welcome to Data Structure Implementation");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(50, 20, 400, 50);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);

        // Configure the buttons
        JButton b1 = new JButton("Array");
        JButton b2 = new JButton("Stack");
        JButton b3 = new JButton("Queue");
        JButton b4 = new JButton("Circular Queue");
        JButton b5 = new JButton("Linked List");

        // Set bounds for buttons
        b1.setBounds(150, 100, 200, 40);
        b2.setBounds(150, 160, 200, 40);
        b3.setBounds(150, 220, 200, 40);
        b4.setBounds(150, 280, 200, 40);
        b5.setBounds(150, 340, 200, 40);
        
        // Set Focus to false for buttons
        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        
        // Set ActionListeners for buttons
        b1.addActionListener(e -> new ArrayImplement());
        b2.addActionListener(e -> new StackImplement());
        b3.addActionListener(e -> new QueueImplement());
        b4.addActionListener(e -> new CircularQueueImplement());
        b5.addActionListener(e -> new LinkedListImplement());

        // Home button
        HomeButton homeButton = new HomeButton(myFrame);
        myFrame.add(homeButton.getHome());

        // Frame settings
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setSize(500, 500);
        myFrame.getContentPane().setBackground(Color.BLACK);
        
        // Add components to frame
        myFrame.add(label);
        myFrame.add(b1);
        myFrame.add(b2);
        myFrame.add(b3);
        myFrame.add(b4);
        myFrame.add(b5);
    }
    
    public static JFrame getMyFrame() {
        return myFrame;
    }
}

class HomeButton {
    JButton homeButton;

    public HomeButton(JFrame currentFrame) {
        homeButton = new JButton();
        ImageIcon icon = new ImageIcon("HomeIcon.jpg");
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        homeButton.setIcon(scaledIcon);
        homeButton.setBounds(10, 10, 40, 40);
        homeButton.setFocusable(false);

        // Add action listener to home button
        homeButton.addActionListener(e -> {
            currentFrame.setVisible(false);
            DsFrame.getMyFrame().setVisible(true);
        });
    }

    public JButton getHome() {
        return homeButton;
    }
}
