package loginsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;


public class Create extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbConfirmPassword;
    private JButton btnnew;
    private JButton btnCancel;
    private boolean succeeded;
    private JLabel  warningLabel;

    public Create(Frame parent) {
        super(parent, "new", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("New Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("New Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.black));
        
        lbConfirmPassword = new JLabel("Confirm New Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbConfirmPassword, cs);
        
        pfConfirmPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(pfConfirmPassword, cs);
        panel.setBorder(new LineBorder(Color.black));

        lbUsername.setForeground(Color.white);
        lbPassword.setForeground(Color.white);
        lbConfirmPassword.setForeground(Color.white);
        panel.setBackground(Color.black);
  

        btnnew = new JButton("Create Account");
        warningLabel = new JLabel();
        
        
    
        btnnew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Login.authenticate(getUsername(), getPassword())) {
                        JOptionPane.showMessageDialog(Create.this,
                                "You already have an account " + getUsername() + "! please login",
                                "Login",JOptionPane.INFORMATION_MESSAGE);
                        succeeded = true;
                        dispose();
                    } 
                    else if (pfPassword.getPassword().length < 1)
                    {
                        JOptionPane.showMessageDialog(Create.this,"That password is not long enough! Please try again!");
                        tfUsername.setText("");
                        pfPassword.setText("");
                    }
                    else if (!getPassword().equals(getConfirmPassword()))
                    {
                        JOptionPane.showMessageDialog(Create.this,"Sorry,  " + getUsername() + "! Your passwords do not match, Please try again.");
                        tfUsername.setText("");
                        pfPassword.setText("");
                    }
                    else {
                        try(FileWriter fw = new FileWriter("loginsystem.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw))
                        {
                            out.println(getUsername());
                            
                            out.println(getPassword());
                            
                            out.println();
                            dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try(FileWriter fw = new FileWriter("friendcheck.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw))
                        {
                            out.println(getUsername());
                            
                            out.println(getPassword());
                            
                            out.println();
                            dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });   
        
        btnCancel = new JButton("Cancel");
        btnCancel.setForeground(Color.red);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnnew);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        dispose();
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    
    public String getConfirmPassword() {
        return new String(pfConfirmPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }

}