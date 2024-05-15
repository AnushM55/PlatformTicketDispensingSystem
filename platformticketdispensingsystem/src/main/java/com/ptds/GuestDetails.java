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
import java.util.ArrayList;
import java.util.Arrays;
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



class GuestDetails {
    JTextField Name;
    JFormattedTextField PhoneNumber;
    static String PNR;

    static float amount = 20;

    GuestDetails(JTextField name , JFormattedTextField PhNo){
        
        Name= name;
        PhoneNumber = PhNo;
    }
    static void SetPNR(String pnr){
        PNR = pnr;
    }
    String returnName(){
        return Name.getText();
    }
    Object returnPhoneNumber(){
        return PhoneNumber.getValue();
    }
}
