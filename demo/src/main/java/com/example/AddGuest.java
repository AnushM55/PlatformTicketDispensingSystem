package com.example;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AddGuest {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public AddGuest() {
        prepareGUI();
    }

    public static void main(String[] args) {
        AddGuest swingControlDemo = new AddGuest();
        swingControlDemo.showScrollPaneDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Create buttons
        JButton addButton = new JButton("Add Guest");
        JButton ticketButton = new JButton("Passenger Platform Ticket");

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "Add Guest" button is clicked
                // You can add your logic here
                statusLabel.setText("Add Guest button clicked");
            }
        });

        ticketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "Passenger Platform Ticket" button is clicked
                // You can add your logic here
                statusLabel.setText("Passenger Platform Ticket button clicked");
            }
        });

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Add buttons to control panel
        controlPanel.add(addButton);
        controlPanel.add(ticketButton);

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private JTextArea outputTextArea;

    private void showScrollPaneDemo() {
        headerLabel.setText("Control in action: ScrollPane");
        outputTextArea = new JTextArea("", 5, 20);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        controlPanel.add(scrollPane);
        mainFrame.setVisible(true);
    }
}
