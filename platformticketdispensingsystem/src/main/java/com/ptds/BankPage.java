package com.ptds;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BankPage extends JFrame {
    public BankPage() {
        ImageIcon icon = new ImageIcon("platform_image.jpg");
        setIconImage(icon.getImage());
        setTitle("Bank Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Top panel with SASA RAILWAYS PAYMENT GATE text field
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel paymentGateLabel = new JLabel("SASA RAILWAYS PAYMENT GATE");
        paymentGateLabel.setForeground(Color.WHITE);
        paymentGateLabel.setBackground(new Color(0, 102, 204)); // Dark blue color
        paymentGateLabel.setOpaque(true);
        paymentGateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paymentGateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(paymentGateLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Payment field highlighted
        JPanel paymentFieldPanel = new JPanel(new BorderLayout());
        JLabel paymentLabel = new JLabel("Payment");
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        paymentFieldPanel.add(paymentLabel, BorderLayout.CENTER);
        paymentFieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(paymentFieldPanel, BorderLayout.CENTER);

        // Payment details panel
        JPanel detailsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(detailsPanel, BorderLayout.WEST);

        JPanel paymentDetailsPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        paymentDetailsPanel.setBorder(BorderFactory.createTitledBorder("Payment Details"));
        paymentDetailsPanel.setBackground(new Color(230, 240, 250)); // Light blue color

        JLabel applicationLabel = new JLabel("Application:");
        JTextField applicationField = new JTextField("RPTS");
        JLabel bankNameLabel = new JLabel("Bank Name:");
        JTextField bankNameField = new JTextField();
        JLabel ifscLabel = new JLabel("IFSC CODE:");
        JTextField ifscField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        paymentDetailsPanel.add(applicationLabel);
        paymentDetailsPanel.add(applicationField);
        paymentDetailsPanel.add(bankNameLabel);
        paymentDetailsPanel.add(bankNameField);
        paymentDetailsPanel.add(ifscLabel);
        paymentDetailsPanel.add(ifscField);
        paymentDetailsPanel.add(amountLabel);
        paymentDetailsPanel.add(amountField);

        detailsPanel.add(paymentDetailsPanel);

        // User details panel
        JPanel userDetailsPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        userDetailsPanel.setBorder(BorderFactory.createTitledBorder("User Details"));
        userDetailsPanel.setBackground(new Color(230, 240, 250)); // Light blue color

        JLabel accountHolderLabel = new JLabel("Account Holder Name:");
        JTextField accountHolderField = new JTextField();
        JLabel accountNumberLabel = new JLabel("Account Number:");
        JTextField accountNumberField = new JTextField();
        JLabel derivedAmountLabel = new JLabel("Amount (Derived):");
        JTextField derivedAmountField = new JTextField();

        userDetailsPanel.add(accountHolderLabel);
        userDetailsPanel.add(accountHolderField);
        userDetailsPanel.add(accountNumberLabel);
        userDetailsPanel.add(accountNumberField);
        userDetailsPanel.add(derivedAmountLabel);
        userDetailsPanel.add(derivedAmountField);

        detailsPanel.add(userDetailsPanel);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton makePaymentButton = new JButton("Make Payment");
        makePaymentButton.setBackground(new Color(0, 153, 0)); // Green color
        makePaymentButton.setForeground(Color.WHITE);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(204, 0, 0)); // Red color
        cancelButton.setForeground(Color.WHITE);
        buttonsPanel.add(makePaymentButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankPage::new);
    }
}