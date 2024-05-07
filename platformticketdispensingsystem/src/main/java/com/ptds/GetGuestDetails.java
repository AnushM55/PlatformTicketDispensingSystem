package com.ptds;
import java.awt.Component;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

public class GetGuestDetails {

    GetGuestDetails() {
        new EnterGuestDetails();
    }
    
}

class GuestDetails {
    JTextField Name;
    JFormattedTextField PhoneNumber;
    GuestDetails(JTextField name , JFormattedTextField PhNo){
        
        Name= name;
        PhoneNumber = PhNo;
    }
    String returnName(){
        return Name.getText();
    }
    Object returnPhoneNumber(){
        return PhoneNumber.getValue();
    }
}
class EnterGuestDetails {
    static JFrame newFrame;

    JLabel InstructionLabel;
    static boolean CanProceed;


   HashMap<Integer,GuestDetails> PassengerInfo;
    
    void PrintAllValuesInHashmap(){
        
        for (Integer key : PassengerInfo.keySet()){
            System.out.println(PassengerInfo.get(key).returnName()+" "+PassengerInfo.get(key).returnPhoneNumber().toString());
        }
        
    }
    boolean VerifyAllFields(){
        for (Integer key : PassengerInfo.keySet()){
            
            if(PassengerInfo.get(key).returnName().equals("Name") || PassengerInfo.get(key).returnPhoneNumber()== null){
                return false;
            }
            if (PassengerInfo.get(key).returnName().isBlank() ){
                return false;
            }
        }
        return true;
    }
    void AddButtonPanel() {
        JPanel buttonPannel = new JPanel();

        JPanel SearchPanel = new JPanel();

        SearchPanel.setBackground(Color.WHITE);
        SearchPanel.setLayout(new GridBagLayout());
        buttonPannel.setBackground(Color.decode("#24123b"));

        buttonPannel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add Ticket");
        addButton.setFocusable(false);
        addButton.setBackground(SearchPanel.getBackground());
        addButton.setForeground(buttonPannel.getBackground());
        addButton.setFont(new Font("Arial", Font.BOLD, 24)); // increased font size

        JButton FinishButton = new JButton("Proceed To Payment");
        FinishButton.setFocusable(false);
        FinishButton.setBackground(SearchPanel.getBackground());
        FinishButton.setForeground(buttonPannel.getBackground());
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
                    phonenumberfield = new JFormattedTextField(new MaskFormatter("+############"));
                } catch (Exception e) {
                    System.err.println("Error while formating string for mask");
                }
                    
                phonenumberfield.setColumns(searchText.getColumns());
                phonenumberfield.setFocusLostBehavior(JFormattedTextField.PERSIST);
                phonenumberfield.setFont(new Font("Arial", Font.PLAIN, 18)); // increased font size
                final JFormattedTextField phCopy = phonenumberfield;

                phCopy.addCaretListener(e ->{

                    try{

                        phCopy.commitEdit();
                        phCopy.setCaretColor(Color.GREEN);
                       
                        
                        CanProceed = true;

                        
                    }catch(ParseException pe){
                        phCopy.setCaretColor(Color.RED);
                        CanProceed = false;
                        }
                    
                });
                
                contentBox.add(phonenumberfield);

                

                contentBox.add(Box.createRigidArea(new Dimension(contentBox.getWidth(), 0)));
                JButton close = new JButton("X");
                close.setFont(new Font("Arial", Font.BOLD, 18)); // increased font size
                
                PassengerInfo.put(close.hashCode(),new GuestDetails(searchText,phonenumberfield));
                 
                
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
                        PassengerInfo.remove(close.hashCode());
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
        FinishButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               // TODO Auto-generated method stub
              if(VerifyAllFields() == false || CanProceed == false){
                JOptionPane.showMessageDialog(addButton,"PLease fill all fields properly");
              }else{
              PrintAllValuesInHashmap();
           } 
        }
        });
        buttonPannel.add(addButton, BorderLayout.CENTER);
        buttonPannel.add(FinishButton, BorderLayout.CENTER/* ,buttoConstraints */);

        buttonPannel.setBorder(new EmptyBorder(100, 100, 100, 100));
       

       
        SearchPanel.add(buttonPannel);
        newFrame.add(SearchPanel);
        newFrame.pack();

    }

    EnterGuestDetails() {
        PassengerInfo = new HashMap<Integer,GuestDetails>();
        newFrame = new JFrame();
       // newFrame.setExtendedState(newFrame.MAXIMIZED_BOTH);
        newFrame.setTitle("Enter guest details: ");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        newFrame.setResizable(true);
        
        AddButtonPanel();
        newFrame.setVisible(true);

        newFrame.addWindowStateListener(new WindowStateListener() {
            @Override

            public void windowStateChanged(WindowEvent arg0) {
                // TODO Auto-generated method stub
                newFrame.pack();
            }
        });

    }
}


