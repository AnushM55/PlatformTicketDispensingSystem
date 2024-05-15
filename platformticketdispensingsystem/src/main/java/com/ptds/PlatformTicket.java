package com.ptds;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;

public class PlatformTicket extends JFrame {

    private String ticketId;
    HashMap<Integer,GuestDetails> FinalTicketInfo;
    String transac_id;
    
    private void PopulateGuestInfo(){
        for(GuestDetails x: FinalTicketInfo.values()){
                Connection conn = null;
                try {
                       
                        // Create a connection to the database
                         conn = DBConn.connect();
            
                        // Prepare the SQL statement
                        String sql = "INSERT INTO guest_info(ticket_id,name,mobile_no) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(2, x.returnName());
                        pstmt.setString(1,transac_id);
                        pstmt.setString(3, x.returnPhoneNumber().toString());
                        pstmt.executeQuery();
                        conn.commit();
                        // pstmt.setString(2, DateTextField.getText());
                        pstmt.close();
        }
        catch(Exception e ){
                System.err.println(e.getLocalizedMessage());
        }
        
        }
        }
    public PlatformTicket(HashMap<Integer,GuestDetails> map, String transact_id) {
        FinalTicketInfo = map;
        this.transac_id = transact_id;
        PopulateGuestInfo();
        initComponents();
        generateTicketId();
        setSystemDateTime();
    }

    private void generateTicketId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        ticketId = sb.toString();
        TicketIdTextField.setText(ticketId);
        TicketIdTextField.setEditable(false);
    }

    public void getTotalBankInfo(){
        
    }

    private void setSystemDateTime() {
      //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
       // SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        //Date now = new Date();
        DateTextField.setText(java.time.LocalDate.ofInstant(java.time.Instant.now(), 
                              java.time.ZoneId.systemDefault()).toString());
        TimeTextField.setText(java.time.LocalTime
                                        .now().toString().replaceFirst("..........$","")

        );
        DateTextField.setEditable(false);
        TimeTextField.setEditable(false);
    }

    private void initComponents() {

        TitlePanel = new JPanel();
        TitleLabel = new JLabel();
        BillPanel = new JPanel();
        TicketIdLabel = new JLabel();
        TicketIdTextField = new JTextField();
        DateLabel = new JLabel();
        DateTextField = new JTextField();
        ValidityLabel = new JLabel();
        PersonLabel = new JLabel();
        PersonTextField = new JTextField();
        PlatformNoLabel = new JLabel();
        AmountLabel = new JLabel();
        PlatformNoTextField = new JTextField();
        AmountTextField = new JTextField();
        TimeLabel = new JLabel();
        TimeTextField = new JTextField();
        TimeTextField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        PrintButton = new JButton();
        jPanel1 = new JPanel();
        Title1Label = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(15, 12));

        TitlePanel.setBackground(new Color(0, 102, 204));

        TitleLabel.setFont(new Font("Arial", 1, 65));
        TitleLabel.setForeground(new Color(255, 255, 255));
        TitleLabel.setText("PLATFORM TICKET");

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
                TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TitlePanelLayout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(TitleLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        TitlePanelLayout.setVerticalGroup(
                TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TitlePanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE )
                                ));

        BillPanel.setBackground(new Color(204, 204, 204));
        BillPanel.setBorder(new MatteBorder(null));

        TicketIdLabel.setFont(new Font("Arial", 1, 15));
        TicketIdLabel.setText("TICKET ID :");

        TicketIdTextField.setFont(new Font("Arial", 0, 15));
        TicketIdTextField.setEditable(false);
        TicketIdTextField.setFocusable(false);
        TicketIdTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TicketIdTextFieldActionPerformed(evt);
            }
        });

        DateLabel.setFont(new Font("Arial", 1, 15));
        DateLabel.setText("DATE :");

        DateTextField.setFont(new Font("Arial", 0, 15));
        DateTextField.setFocusable(false);

        ValidityLabel.setFont(new Font("Arial", 1, 20));
        ValidityLabel.setText("Valid for 2 hours only!!");

        PersonLabel.setFont(new Font("Arial", 1, 15));
        PersonLabel.setText("PERSON (S) :");

        PersonTextField.setFont(new Font("Arial", 0, 15));
        PersonTextField.setText(FinalTicketInfo.size()+"");
       
        PlatformNoLabel.setFont(new Font("Arial", 1, 15));
        PlatformNoLabel.setText("PLATFORM NUMBER :");

        AmountLabel.setFont(new Font("Arial", 1, 15));
        AmountLabel.setText("AMOUNT :");

        PlatformNoTextField.setFont(new Font("Arial", 0, 15));

        AmountTextField.setFont(new Font("Arial", 0, 15));
        AmountTextField.setText((this.FinalTicketInfo.size()*GuestDetails.amount)+"");
        AmountTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AmountTextFieldActionPerformed(evt);
            }
        });

        TimeLabel.setFont(new Font("Arial", 1, 15));
        TimeLabel.setText("TIME :");
        TimeTextField.setFocusable(false);

        TimeTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TimeTextFieldActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new Color(204, 204, 204));

        Title1Label.setBackground(new Color(204, 204, 204));
        Title1Label.setFont(new Font("Arial", 1, 36));
        Title1Label.setText("SASA RAILWAYS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Title1Label)
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Title1Label, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE));

        javax.swing.GroupLayout BillPanelLayout = new javax.swing.GroupLayout(BillPanel);
        BillPanel.setLayout(BillPanelLayout);
        BillPanelLayout.setHorizontalGroup(
                BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BillPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                .addGroup(BillPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                                .addComponent(PlatformNoLabel)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(PlatformNoTextField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                                .addComponent(PersonLabel)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(PersonTextField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                .addComponent(TicketIdLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TicketIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(DateLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(DateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57))
                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                .addGroup(BillPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                                .addComponent(AmountLabel)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(AmountTextField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 101,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(BillPanelLayout.createSequentialGroup()
                                                                .addComponent(TimeLabel)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(TimeTextField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ValidityLabel)
                                                .addGap(32, 32, 32))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BillPanelLayout.createSequentialGroup()
                                .addGap(0, 188, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(228, 228, 228)));
        BillPanelLayout.setVerticalGroup(
                BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BillPanelLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addGroup(
                                        BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(TicketIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TicketIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(DateLabel)
                                                .addComponent(DateTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(
                                        BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(PersonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(PersonTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(BillPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(PlatformNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PlatformNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(
                                        BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(AmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(AmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(BillPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(ValidityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE)));

        PrintButton.setBackground(new Color(0, 153, 0));
        PrintButton.setFont(new Font("Arial", 1, 18));
        PrintButton.setForeground(new Color(255, 255, 255));
        PrintButton.setText("PRINT");
        PrintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PrintButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(454, 454, 454)
                                .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(161, Short.MAX_VALUE)
                                .addComponent(BillPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(161, 161, 161)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(TitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(BillPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 69, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }

    private void TicketIdTextFieldActionPerformed(ActionEvent evt) {
    }

    private void AmountTextFieldActionPerformed(ActionEvent evt) {
    }

    private void PrintButtonActionPerformed(ActionEvent evt) {
        printToPDF();
    }

    private void TimeTextFieldActionPerformed(ActionEvent evt) {
    }

    private void PersonTextFieldActionPerformed(ActionEvent evt) {
    }

    private void printToPDF() {
        try {
            // Add your database connection details
           
            // Create a connection to the database
            Connection conn = DBConn.connect();

            // Prepare the SQL statement
            String sql = "INSERT INTO platform_ticket_info (ticket_id,numberofpersons, platform_no, amount, time,pnr_no,transaction_id) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, TicketIdTextField.getText());
            // pstmt.setString(2, DateTextField.getText());
            pstmt.setInt(2, Integer.valueOf(PersonTextField.getText()));
            pstmt.setInt(3, Integer.valueOf(PlatformNoTextField.getText()));
            pstmt.setFloat(4, Float.valueOf(AmountTextField.getText()));
            pstmt.setString(6, GuestDetails.PNR);
            pstmt.setString(7, transac_id);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp TimeStamp = new java.sql.Timestamp(calendar.getTime().getTime());
            
            pstmt.setTimestamp(5, TimeStamp);


            // Execute the SQL statement
            pstmt.executeUpdate();

            // Close the database connection
            conn.close();

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            // Set font and font size for text
            FontName font_name_3v= Standard14Fonts.getMappedFontName("HELVETICA_BOLD");
            PDFont pdfFont=  new PDType1Font(font_name_3v.HELVETICA_BOLD);
         contentStream.setFont(pdfFont,14);

            // Write text fields content to the PDF
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Ticket ID: " + TicketIdTextField.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date: " + DateTextField.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Person(s): " + PersonTextField.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Platform Number: " + PlatformNoTextField.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Amount: " + AmountTextField.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Time: " + TimeTextField.getText());
            contentStream.endText();

            contentStream.close();

            // Save the PDF
            String fileName = "PlatformTicket_" + DateTextField.getText() + ".pdf";
            doc.save(fileName);
            doc.close();

            // Open the saved PDF file
            File file = new File(fileName);
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while creating PDF: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while connecting to database: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlatformTicket.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlatformTicket.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlatformTicket.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlatformTicket.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
                        
      
    }

    // Variables declaration
    private JPanel TitlePanel;
    private JLabel TitleLabel;
    private JPanel BillPanel;
    private JLabel TicketIdLabel;
    private JTextField TicketIdTextField;
    private JLabel DateLabel;
    private JTextField DateTextField;
    private JLabel ValidityLabel;
    private JLabel PersonLabel;
    private JTextField PersonTextField;
    private JLabel PlatformNoLabel;
    private JLabel AmountLabel;
    private JTextField PlatformNoTextField;
    private JTextField AmountTextField;
    private JLabel TimeLabel;
    private JTextField TimeTextField;
    private JButton PrintButton;
    private JPanel jPanel1;
    private JLabel Title1Label;
}