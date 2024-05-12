package com.ptds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;

class RandomNumber {
    static long state;
    int digits;
    
    public RandomNumber(int digitNo){
      
      digits = digitNo;
      state = 1 << 19 | (System.nanoTime()%10000); 

    }
    public byte GenerateBit(){
       byte newBit = (byte) ((state ^ (state >> 3)) & 1);
       state = (state >> 1) | (newBit << 19);
       return  (byte) (state & 1);
    }

    public String GenerateNumber(){
      double i =  digits;
      String res = "";
      byte j = (byte) (state % 10) ;
      while(i > 0){
        if (j < 0){
          j = 9;
        }
        res += ((byte)((GenerateBit()*(i)+j)%10));
        j --;
        i--;
      }
      return res;

    }
    
  }



public class BankPage extends javax.swing.JFrame {
    public BankPage(HashMap<Integer,GuestDetails> map) {
        this.Map = map; 
        initComponents();
    }
    
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        PaymentDetailsPanel = new javax.swing.JPanel();
        ApplicationLabel = new javax.swing.JLabel();
        BankNameLabel = new javax.swing.JLabel();
        IFSC_Label = new javax.swing.JLabel();
        AmountLabel = new javax.swing.JLabel();
        AmountTextField = new javax.swing.JTextField();
        AccountNameLabel = new javax.swing.JLabel();
        AccountNoLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BNameLabel = new javax.swing.JLabel();
        IFSCLabel = new javax.swing.JTextField();
        AccoNameLabel = new javax.swing.JLabel();
        AccoNoLabel = new javax.swing.JLabel();
        UserDetailsPanel = new javax.swing.JPanel();
        AccHolderLabel = new javax.swing.JLabel();
        AccNoLabel = new javax.swing.JLabel();
        AmtLabel = new javax.swing.JLabel();
        AccHolderTextField = new javax.swing.JTextField();
        AccNumberTextField = new javax.swing.JTextField();
        AmtTextField = new javax.swing.JTextField();
        MakePayment = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));

        topPanel.setBackground(new java.awt.Color(0, 102, 204));

        Title.setFont(new java.awt.Font("Arial", 1, 54)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("SASA RAILWAY PAYMENT GATEWAY");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        PaymentDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 20)));

        ApplicationLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        ApplicationLabel.setText("Application :");

        BankNameLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        BankNameLabel.setText("Bank Name :");

        IFSC_Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        IFSC_Label.setText("IFSC CODE :");

        AmountLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AmountLabel.setText("Amount :");

        AmountTextField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        AmountTextField.setText((Map.size()*10.0)+" ");
        AmtTextField.setText(AmountTextField.getText());
        AccountNameLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AccountNameLabel.setText("Account Name :");

        AccountNoLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AccountNoLabel.setText("Account No. :");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setText("RPTS");

        BNameLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        BNameLabel.setText("Indian Overseas Bank");

        IFSCLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        IFSCLabel.setText("IOBA0000020");

        AccoNameLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AccoNameLabel.setText("SASA RAILWAYS");

        AccoNoLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AccoNoLabel.setText("98766987651");

        Connection conn = null; // Declare the connection variable outside the try block

        MakePayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Retrieve the account number and payment amount from the text fields
                String accountNumber = AccNumberTextField.getText();
                AmtTextField.setText(AmountTextField.getText());
                double paymentAmount = Double.parseDouble(AmountTextField.getText());
                
                try{
                    Connection conn = DBConn.connect();
                    conn.setAutoCommit(false); // Disable auto-commit to start transaction
                    
                    // ] Check if the account exists
                    String query = "SELECT balance FROM account WHERE account_no = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1,  accountNumber);
                    ResultSet rset = statement.executeQuery();
                    if(rset.next()){
                        
                        double currentBalance = rset.getDouble("balance");
                        System.out.println(currentBalance);
                        //  Deduct the payment amount from the current balance
                        if (currentBalance >= paymentAmount) {
                            double updatedBalanceForUser = currentBalance + paymentAmount;
                            
                            // Update the account balance
                            String updateQuery = "UPDATE account SET balance = balance + ? WHERE name = ? AND account_no = ?";
                            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                            updateStatement.setDouble(1, paymentAmount);
                            updateStatement.setString(2, "SASA RAILWAYS");
                            updateStatement.setString(3, "98766987651");
                            updateStatement.executeUpdate();
                            
                            updateQuery = "UPDATE account SET balance = balance - ? WHERE name = ? AND account_no = ?";
                            updateStatement.setDouble(1,paymentAmount);
                            /* 
                            // Insert transaction record into another database table
                            */
                            String insertQuery = "INSERT INTO banking_info(account_no,amount,transaction_id,name) VALUES(?, ?, ?, ?)";
                            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                            insertStatement.setString(1, accountNumber);
                            insertStatement.setDouble(2, paymentAmount);
                            String transacId = new RandomNumber(10).GenerateNumber();
                            insertStatement.setString(3,transacId);
                            insertStatement.setString(4,AccHolderTextField.getText());
                            
                            insertStatement.executeUpdate();
                            conn.commit();
                            
                            JOptionPane.showMessageDialog(null, "Payment Success ✔", "Payment", JOptionPane.INFORMATION_MESSAGE);
                            PlatformTicket ticketPane = new PlatformTicket(Map,transacId);
                           // ticketPane.getTotalBankInfo(Map);
                            ticketPane.setVisible(true);
;
                            
                            // Commit the transaction
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance. Please Retry.", "Payment", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Account Number. Please Retry.", "Payment", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    rset.close();
                    statement.close();
                } catch (SQLException ex){
                    ex.printStackTrace();
                    try {
                        if (conn != null) {
                            conn.rollback(); // Rollback the transaction if an error occurs
                        }
                    } catch (SQLException rollbackEx) {
                        rollbackEx.printStackTrace();
                    }
                } finally {
                    try {
                        if (conn != null) {
                            conn.close(); // Close the connection in the finally block
                        }
                    } catch (SQLException closeEx) {
                        closeEx.printStackTrace();
                    }
                }
            }
        });
        
        
        

        Cancel.addActionListener(new ActionListener() {
            
        
            
    
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cancel the payment
                JOptionPane.showMessageDialog(null, "Payment Cancelled.", "Payment", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        javax.swing.GroupLayout PaymentDetailsPanelLayout = new javax.swing.GroupLayout(PaymentDetailsPanel);
        PaymentDetailsPanel.setLayout(PaymentDetailsPanelLayout);
        PaymentDetailsPanelLayout.setHorizontalGroup(
            PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(IFSC_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ApplicationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(BankNameLabel))
                                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaymentDetailsPanelLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(IFSCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AccountNameLabel)
                                    .addComponent(AccountNoLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AccoNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AccoNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(99, Short.MAX_VALUE))
                    .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                        .addComponent(AmountLabel)
                        .addGap(59, 59, 59)
                        .addComponent(AmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PaymentDetailsPanelLayout.setVerticalGroup(
            PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ApplicationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BankNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(BNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IFSC_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(IFSCLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AccountNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccoNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AccountNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(AccoNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PaymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        UserDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 20)));

        AccHolderLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AccHolderLabel.setText("Account Holder Name :");

        AccNoLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AccNoLabel.setText("Account Number :");

        AmtLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        AmtLabel.setText("Amount :");

        AccHolderTextField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        AccNumberTextField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        AmtTextField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        javax.swing.GroupLayout UserDetailsPanelLayout = new javax.swing.GroupLayout(UserDetailsPanel);
        UserDetailsPanel.setLayout(UserDetailsPanelLayout);
        UserDetailsPanelLayout.setHorizontalGroup(
            UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDetailsPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AmtLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AccNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AccHolderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AccHolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AmtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        UserDetailsPanelLayout.setVerticalGroup(
            UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDetailsPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AccHolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccHolderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AccNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccNoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AmtLabel))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        MakePayment.setBackground(new java.awt.Color(0, 153, 0));
        MakePayment.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        MakePayment.setForeground(new java.awt.Color(255, 255, 255));
        MakePayment.setText("Make Payment");

        Cancel.setBackground(new java.awt.Color(204, 0, 0));
        Cancel.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        Cancel.setForeground(new java.awt.Color(255, 255, 255));
        Cancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(PaymentDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(UserDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(MakePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PaymentDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(UserDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MakePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }

    private void AmountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BankPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankPage3(Map).setVisible(true);
            }
        });*/
        
    }

    // Variables declaration
    private javax.swing.JLabel AccHolderLabel;
    private javax.swing.JTextField AccHolderTextField;
    private javax.swing.JLabel AccNoLabel;
    private javax.swing.JTextField AccNumberTextField;
    private javax.swing.JLabel AccoNameLabel;
    private javax.swing.JLabel AccoNoLabel;
    private javax.swing.JLabel AccountNameLabel;
    private javax.swing.JLabel AccountNoLabel;
    private javax.swing.JLabel AmountLabel;
    private javax.swing.JTextField AmountTextField;
    private javax.swing.JLabel AmtLabel;
    private javax.swing.JTextField AmtTextField;
    private javax.swing.JLabel ApplicationLabel;
    private javax.swing.JLabel BNameLabel;
    private javax.swing.JLabel BankNameLabel;
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField IFSCLabel;
    private javax.swing.JLabel IFSC_Label;
    private javax.swing.JButton MakePayment;
    private javax.swing.JPanel PaymentDetailsPanel;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel UserDetailsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel topPanel;
    private HashMap<Integer,GuestDetails> Map;
}