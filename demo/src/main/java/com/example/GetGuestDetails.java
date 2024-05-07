package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class GetGuestDetails {

    GetGuestDetails() {
        new EnterGuestDetails();
    }

    public static void main(String[] args) {
        new GetGuestDetails();
    }
}

class EnterGuestDetails {
    static JFrame newFrame;

    JLabel InstructionLabel;

    void AddButtonPanel() {

        JPanel buttonPannel = new JPanel();

        JPanel SearchPanel = new JPanel();

        SearchPanel.setBackground(Color.WHITE);
        SearchPanel.setLayout(new GridBagLayout());
        int red = 80;
        int green = 130;
        int blue = 180;
        buttonPannel.setBackground(new Color(red, green, blue));

        buttonPannel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add Ticket");
        addButton.setFocusable(false);
        addButton.setBackground(SearchPanel.getBackground());
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("Arial", Font.BOLD, 24)); // increased font size

        JButton FinishButton = new JButton("Passenger Platform Ticket");
        FinishButton.setFocusable(false);
        FinishButton.setBackground(SearchPanel.getBackground());
        FinishButton.setForeground(Color.BLACK);
        FinishButton.setFont(new Font("Arial", Font.BOLD, 24)); // increased font size

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if (SearchPanel.getComponentCount() > 0) {
                    buttonPannel.setBorder(new EmptyBorder(1, 1, 1, 1));
                }
                JPanel contentBox = new JPanel();
                contentBox.setLayout(new FlowLayout());
                contentBox.setBackground(buttonPannel.getBackground());
                JTextField searchText = new JTextField("Name", 20);
                searchText.setForeground(buttonPannel.getBackground());
                searchText.setFont(new Font("Arial", Font.PLAIN, 18)); // increased font size

                searchText.addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent event) {
                        if (searchText.getText().equals("Name")) {
                            searchText.setText("");
                            searchText.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent ecent) {
                        if (searchText.getText().isEmpty()) {
                            searchText.setForeground(Color.GRAY);
                            searchText.setText("Name");
                        }
                    }
                });
                contentBox.add(searchText);
                JFormattedTextField phonenumberfield = null;

                try {
                    phonenumberfield = new JFormattedTextField(new MaskFormatter("+91 ##########"));
                } catch (Exception e) {
                    System.err.println("Error while formating string for mask");
                }

                phonenumberfield.setColumns(searchText.getColumns());
                phonenumberfield.setFont(new Font("Arial", Font.PLAIN, 18)); // increased font size

                contentBox.add(phonenumberfield);
                contentBox.add(Box.createRigidArea(new Dimension(contentBox.getWidth(), 0)));
                JButton close = new JButton("X");
                close.setFont(new Font("Arial", Font.BOLD, 18)); // increased font size

                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (SearchPanel.getComponentCount() == 1) {
                            buttonPannel.setBorder(new EmptyBorder(100, 100, 100, 100));
                        }

                        contentBox.removeAll();
                        contentBox.setVisible(false);
                        contentBox.removeAll();
                        SearchPanel.remove(contentBox);
                        SearchPanel.revalidate();
                        SearchPanel.repaint();
                        if (newFrame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                            newFrame.pack();
                        }
                    }
                });
                contentBox.add(close, BorderLayout.WEST);
                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                SearchPanel.add(contentBox, c);
                SearchPanel.validate();
                SearchPanel.repaint();
                if (newFrame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                    newFrame.pack();
                }
                // newFrame.pack();
            }
        });
        addButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        FinishButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPannel.add(addButton, BorderLayout.CENTER);
        buttonPannel.add(FinishButton, BorderLayout.CENTER/* ,buttoConstraints */);

        buttonPannel.setBorder(new EmptyBorder(100, 100, 100, 100));
        FinishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                new BankPage();
            }

        });
        SearchPanel.add(buttonPannel);
        newFrame.add(SearchPanel);
        // newFrame.pack();

    }

    EnterGuestDetails() {
        newFrame = new JFrame();
        newFrame.setTitle("Enter guest details: ");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // newFrame.setSize(1920, 1080);
        newFrame.setResizable(true);
        newFrame.setBackground(new Color(80, 130, 180));
        AddButtonPanel();
        newFrame.setVisible(true);

        /*
         * newFrame.addWindowStateListener(new WindowStateListener() {
         * 
         * @Override
         * 
         * public void windowStateChanged(WindowEvent arg0) {
         * // TODO Auto-generated method stub
         * newFrame.pack();
         * }
         * });
         */
    }

}