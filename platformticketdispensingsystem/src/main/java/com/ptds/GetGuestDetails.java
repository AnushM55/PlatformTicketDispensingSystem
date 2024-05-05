package com.ptds;

/**
 * EnterGuestDetails
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class GetGuestDetails {

    public static void main(String[] args) {
        new EnterGuestDetails();
    }
}
/**
 * SpawnDetails
 
 */
class EnterGuestDetails {
    static JFrame newFrame;
    
    JLabel InstructionLabel;

    void AddButtonPanel(){
        JPanel buttonPannel = new JPanel();
        
        JPanel SearchPanel = new JPanel();
        
        SearchPanel.setBackground(Color.WHITE);
        SearchPanel.setLayout(new GridBagLayout());
        buttonPannel.setBackground(Color.decode("#24123b"));

        buttonPannel.setLayout(new FlowLayout());
        /*GridBagConstraints buttoConstraints = new GridBagConstraints();
        buttoConstraints.ipadx = 5;
        buttoConstraints.ipady = 5;
        buttoConstraints.gridy = 10;
        */
        JButton addButton = new JButton("Add Guest");
        addButton.setBackground(SearchPanel.getBackground());
        addButton.setForeground(buttonPannel.getBackground());
        JButton FinishButton = new JButton("Proceed To Payment");
        FinishButton.setBackground(SearchPanel.getBackground());
        FinishButton.setForeground(buttonPannel.getBackground());
        


        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event){
                if (SearchPanel.getComponentCount() >= 0){
                    buttonPannel.setBorder(new EmptyBorder(1,1,1,1));
                }
                JPanel contentBox = new JPanel();
                contentBox.setLayout(new FlowLayout());
                contentBox.setBackground(buttonPannel.getBackground());
                JTextField searchText = new JTextField("Name",20);
                searchText.setForeground(buttonPannel.getBackground());
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
                
                try{
                    phonenumberfield = new JFormattedTextField(new MaskFormatter("+############"));
                }catch(Exception e){
                    System.err.println("Error while formating string for mask");
                }

                
            
                
                phonenumberfield.setColumns(searchText.getColumns());
               
                contentBox.add(phonenumberfield);
                contentBox.add(Box.createRigidArea(new Dimension(contentBox.getWidth() , 0)));
                JButton close = new JButton("X");
                
                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if (SearchPanel.getComponentCount() == 1){
                            buttonPannel.setBorder(new EmptyBorder(100,100,100,100));
                        }
                        contentBox.removeAll();
                        contentBox.setVisible(false);
                        contentBox.removeAll();
                        SearchPanel.remove(contentBox);
                        SearchPanel.revalidate();
                        SearchPanel.repaint();
                        newFrame.pack();
                    }
                });
                contentBox.add(close,BorderLayout.WEST);
                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                SearchPanel.add(contentBox,c);
                SearchPanel.validate();
                SearchPanel.repaint();
                newFrame.pack();        
            }
        });
        addButton.setBorder(new EmptyBorder(10,10,10,10));
        FinishButton.setBorder(new EmptyBorder(10,10,10,10));
        buttonPannel.add(addButton,BorderLayout.CENTER);
        buttonPannel.add(FinishButton,BorderLayout.CENTER/*,buttoConstraints*/);
        

        buttonPannel.setBorder(new EmptyBorder(100,100,100,100));
        SearchPanel.add(buttonPannel);  
        newFrame.add(SearchPanel);
        newFrame.pack();
        
        
    }
    
    EnterGuestDetails(){
        newFrame = new JFrame();
        newFrame.setTitle("Enter guest details");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setResizable(true);
        newFrame.setSize(800,400);
        newFrame.setMinimumSize(newFrame.getPreferredSize());
        
        

        newFrame.setLocationRelativeTo(null);

        AddButtonPanel();
        newFrame.setVisible(true);
        
    }
   

    
}
