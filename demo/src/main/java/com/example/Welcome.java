package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Welcome {
    Welcome() {
        int red = 80, green = 130, blue = 180;

        final JFrame frame = new JFrame();
        frame.setTitle("Platform Ticket Dispenser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Prevent frame from being resized
        frame.setSize(1920, 1080); // Adjusted frame size for demonstration

        ImageIcon image = new ImageIcon("platform_image.jpg");
        frame.setIconImage(image.getImage()); // Changes icon of frame
        // frame.getContentPane().setBackground(new Color(173, 216, 230)); // Light blue
        // color

        // Create top label
        Color color = new Color(red, green, blue);
        JLabel topLabel = new JLabel("SASA RAILWAYS WELCOMES YOU", SwingConstants.CENTER);
        ImageIcon topimage = new ImageIcon("platform_image.jpg");
        topimage.setImage(image.getImage());
        topLabel.setFont(new Font("Georgia", Font.ITALIC, 80)); // Set font and size
        topLabel.setForeground(Color.WHITE); // Set font color to white
        JPanel topPanel = new JPanel();
        topPanel.setBackground(color);
        // topPanel.add(topimage);
        topPanel.add(topLabel);

        // Vanakam label
        ImageIcon welcomeIcon = new ImageIcon("vanakkam.jpg");
        Image scaledImage = welcomeIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH); // Scale image
        ImageIcon scaledWelcomeIcon = new ImageIcon(scaledImage);
        JLabel welcomeLabel = new JLabel(scaledWelcomeIcon, SwingConstants.CENTER);
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        // welcomePanel.setBackground(new Color(173, 216, 230)); // Match background
        // color of frame

        // Create GridBagConstraints to center the image
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        welcomePanel.add(welcomeLabel, gbc);

        // Create styled text label
        JLabel styledTextLabel = new JLabel(
                "<html><center>Click on <font color='blue'>\"CONTINUE\"</font> to proceed</center></html>");
        styledTextLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font and size
        styledTextLabel.setForeground(Color.BLACK); // Set font color to black

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // Set background color

        // Create buttons
        JButton selectButton = new JButton("CONTINUE");
        selectButton.setBackground(Color.BLUE); // Set background color
        selectButton.setForeground(Color.BLACK);
        selectButton.setFocusable(false); // Remove focus indication

        // Add ActionListener to the button
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the next page here
                frame.dispose();
                new PNR();

            }
        });

        // buttons to button panel
        buttonPanel.add(styledTextLabel);
        buttonPanel.add(selectButton);

        // Set layout for the frame
        frame.setLayout(new BorderLayout());

        // components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(welcomePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Welcome();
    }
}
