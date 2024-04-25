package com.ptds;

import java.awt.*;
import javax.swing.*;

public class ui {
    public static  void DisplayUI(){
        new Welcome();
    }}
 class Welcome {
    Welcome(){
        JFrame frame = new JFrame();
        frame.setTitle("Platform Ticket Dispenser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true); // Prevent frame from being resized
        frame.setSize(600, 400); // Adjusted frame size for demonstration

        ImageIcon image = new ImageIcon("D:\\Java Project\\platform_image.jpg");
        frame.setIconImage(image.getImage()); // Changes icon of frame
        frame.getContentPane().setBackground(Color.WHITE);

        // Create top label
        JLabel topLabel = new JLabel("Coimbatore Railways Welcomes You", SwingConstants.CENTER);
        topLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // Set background color

        // Create buttons
        JButton guestButton = new JButton("Guest");
        guestButton.setBackground(Color.BLUE); // Set background color
        JButton passengerButton = new JButton("Passenger");
        passengerButton.setBackground(Color.BLUE); // Set background color

        // Add buttons to button panel
        buttonPanel.add(guestButton);
        buttonPanel.add(passengerButton);

        // Set layout for the frame
        frame.setLayout(new BorderLayout());

        // Add components to the frame
        frame.add(topLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Welcome();
    }
}
