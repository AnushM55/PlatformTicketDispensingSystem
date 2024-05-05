package com.ptds;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



class PNR extends JFrame {
    JFrame pnrFrame ;
     DBConn dbConnectInfo ;
    boolean VerifyPNRNUmber(String textboxContent) {
        if (dbConnectInfo == null){
            dbConnectInfo = new DBConn();
        }
        try (
            Connection newConn = DBConn.connect(); 
            PreparedStatement newStat = newConn.prepareStatement("select PNR_NO from train_ticket_info where PNR_NO = ?");
            ){
            newStat.setString(1, textboxContent);
            ResultSet recs = newStat.executeQuery();
            
            if(recs.next()){
                return true;
            }
                return false;
            
        }catch (SQLException e) {
            // TODO: handle exception
            System.err.println(e.getLocalizedMessage());
            return false;
        }

        
    }

    void ShowPNRFrame(){
        int red = 80;
        int green = 130;
        int blue = 180;

        pnrFrame = new JFrame();
        pnrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnrFrame.setLayout(null);
        pnrFrame.setExtendedState(MAXIMIZED_BOTH);
        pnrFrame.setVisible(true);

        JLabel heading = new JLabel("INDIAN RAILWAYS ENQUIRY");
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(SwingConstants.CENTER); // Center align horizontally
        heading.setVerticalAlignment(SwingConstants.CENTER); // Center align vertically
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
        JLabel label1 = new JLabel("Indian Railways Passenger Reservation Enquiry");
        label1.setBounds(600, 150, 500, 50);
        pnrFrame.add(label1);
        pnrFrame.add(panel1);
        panel1.setBounds(530, 200, 500, 300);
        panel1.setLayout(null);
        // // Create Color object using RGB values

        panel1.setBackground(color);

        JLabel topic = new JLabel("Enter PNR No:");
        topic.setFont(new Font("Georgia", Font.ITALIC, 20));
        topic.setForeground(Color.white);

        topic.setBounds(50, 60, 150, 20);

        panel1.add(topic);
        JTextField box = new JTextField();
        panel1.add(box);
        box.setBounds(200, 58, 180, 25);
        JButton submit = new JButton("Verify");
        panel1.add(submit);
        submit.setBounds(130, 160, 80, 25);
        submit.setFocusable(false);
        JButton reset = new JButton("Clear");
        panel1.add(reset);
        reset.setBounds(300, 160, 80, 25);
        reset.setFocusable(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the text in the JTextField
                box.setText("");
                
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(VerifyPNRNUmber(box.getText())){
                    /* Next Box Shows Up */
                    System.out.println("Success");
                }else{
                    /* Try Again */
                    System.out.println("Failure");
                }
            }
        });

        ImageIcon image = new ImageIcon("Screenshot 2024-05-04 210030.png");
        image.setImage(image.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

    }
}
