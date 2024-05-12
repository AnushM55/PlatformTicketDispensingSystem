package com.ptds;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;


public class GetGuestDetails {
    static boolean CanProceed;

    HashMap<Integer, GuestDetails> PassengerInfo;

    void PrintAllValuesInHashmap() {

        for (Integer key : PassengerInfo.keySet()) {
            System.out.println(
                    PassengerInfo.get(key).returnName() + " " + PassengerInfo.get(key).returnPhoneNumber().toString());
        }

    }

    boolean VerifyAllFields() {
        for (Integer key : PassengerInfo.keySet()) {

            if (PassengerInfo.get(key).returnName().equals("Name")
                    || PassengerInfo.get(key).returnPhoneNumber() == null) {
                return false;
            }
            if (PassengerInfo.get(key).returnName().isBlank()) {
                return false;
            }
        }
        return true;
    }
    

    GetGuestDetails(String receivedPNR) {
       

        PassengerInfo = new HashMap<Integer, GuestDetails>();
        int red = 80;
        int green = 130;
        int blue = 180;
        GuestDetails.SetPNR(receivedPNR);
        JFrame pnrFrame = new JFrame();
        pnrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnrFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pnrFrame.setLayout(null);
        pnrFrame.setVisible(true);

        JLabel heading = new JLabel("SASA RAILWAYS ");
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setVerticalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Georgia", Font.ITALIC, 80));
        heading.setBounds(0, 0, pnrFrame.getWidth(), 100);

        Color color = new Color(red, green, blue);
        JPanel HeadingPanel = new JPanel();
        HeadingPanel.setLayout(null);
        HeadingPanel.setBounds(0, 0, pnrFrame.getWidth(), 100);
        HeadingPanel.setBackground(color);
        HeadingPanel.add(heading);
        pnrFrame.add(HeadingPanel);

        JPanel panel1 = new JPanel();
        panel1.setBackground(color);
        panel1.setLayout(new GridBagLayout());
        JButton addGuest = new JButton("Add Guest");
        addGuest.setBounds(500, 150, 200, 45);
        pnrFrame.add(addGuest);
        addGuest.setFocusable(false);

        JButton payment = new JButton("Proceed Payment");
        payment.setBounds(800, 720, 400, 45);
        pnrFrame.add(payment);
        payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
               if(VerifyAllFields() == false || CanProceed == false){
                 JOptionPane.showMessageDialog(payment,"PLease fill all fields properly");
               }else{
               PrintAllValuesInHashmap();
               new BankPage(PassengerInfo).setVisible(true);
               
            } 
         }
         });
        JPanel bpane = new JPanel(new BorderLayout());
        bpane.add(panel1,BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(bpane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(300, 200, 900, 400);
        
        
        pnrFrame.add(scrollPane);
       // panel1.setPreferredSize(new java.awt.Dimension(880, 1200));
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTHEAST;
        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel2 = createPanel2(panel1);
                panel1.add(panel2, c);
                panel1.revalidate();
                
                panel1.repaint();
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
                
                
            }
        });

        pnrFrame.revalidate();
        pnrFrame.repaint();
    }

    private JPanel createPanel2(JPanel parent) {
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setSize(new Dimension(900, 60));

        // Set FlowLayout for panel2
        panel2.setLayout(new FlowLayout(0));
        

        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setForeground(Color.BLACK);

        JTextField nameField = new JTextField("Name");
        // Set preferred size for nameField
        nameField.setPreferredSize(new Dimension(150, 40));
        nameField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent event) {
                if (nameField.getText().equals("Name")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent ecent) {
                if (nameField.getText().isEmpty()) {
                    nameField.setForeground(Color.GRAY);
                    nameField.setText("Name");
                }
            }
        });
        JLabel mobLabel = new JLabel("Mobile No :");
        mobLabel.setForeground(Color.BLACK);

        JFormattedTextField mobileField = null;
        try {
            mobileField = new JFormattedTextField(new MaskFormatter("+91##########"));
        } catch (Exception e) {
            System.err.println("Error while formating string for mask");
        }
        // Set preferred size for mobileField
        mobileField.setPreferredSize(new Dimension(150, 40));
        mobileField.setColumns(nameField.getColumns());
        mobileField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        mobileField.setFont(new Font("Arial", Font.PLAIN, 18)); // increased font size
        final JFormattedTextField phCopy = mobileField;

        phCopy.addCaretListener(e -> {

            try {

                phCopy.commitEdit();
                phCopy.setCaretColor(Color.GREEN);

                CanProceed = true;

            } catch (ParseException pe) {
                phCopy.setCaretColor(Color.RED);
                CanProceed = false;
            }

        });

        JButton cancelBtn = new JButton("X");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                panel2.removeAll();
                panel2.setVisible(false);

                parent.remove(panel2);
                parent.revalidate();
                parent.repaint();
                PassengerInfo.remove(cancelBtn.hashCode());

            }
        });

        panel2.add(nameLabel);
        panel2.add(nameField);

        panel2.add(mobLabel);
        panel2.add(mobileField);
        panel2.add(cancelBtn);

        PassengerInfo.put(cancelBtn.hashCode(), new GuestDetails(nameField, mobileField));

        

        return panel2;
    }

   
}