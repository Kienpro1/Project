package View;

import Dao.DaoLogin;
import Model.Account;
import View.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterView extends JFrame {
    private JLabel label1,label2,label3,label4;
    private JButton btnSave;
    private JPasswordField txtpass,txtpass2;
    private JTextField txtuser;
    private JPanel panel;

    RegisterView(){
        initComponent();
    }

    private void initComponent() {
        Font font = new Font("Verdana", Font.BOLD, 10);

        setSize(750, 400);
        SpringLayout layout= new SpringLayout();
        label1=new JLabel("Username");
        label2=new JLabel("Password");
        label3=new JLabel("Confirm password");
        label4=new JLabel("Đăng kí");

        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(new Font("Verdana", Font.BOLD, 20));

        txtuser= new JTextField(20);
        txtpass= new JPasswordField(20);
        txtpass2=new JPasswordField(20);
        panel=new JPanel();
        btnSave= new JButton("Đăng kí");


        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(txtuser);
        panel.add(txtpass);
        panel.add(txtpass2);
        panel.add(btnSave);

        add(panel);        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("Đăng nhập");
        panel.setLayout(layout);
//        btnSave.setEnabled(false);
//        btnCheck.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username= txtuser.getText();
//                if(controller.check(username)){
//                    JOptionPane.showMessageDialog(rootPane,"Tai khoan da ton tai");
//                }else{
//                    JOptionPane.showMessageDialog(rootPane,"tai khoan mat khau hop le");
//                    btnSave.setEnabled(true);
//                }
//            }
//        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtuser.getText();
                String pass1 = new String(txtpass.getPassword());
                String pass2 = new String(txtpass2.getPassword());

                if (txtuser.getText().equals("") || txtpass.equals("") || txtpass2.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Bạn nhập thiếu thông tin");
                } else if (DaoLogin.check(username)) {
                    JOptionPane.showMessageDialog(rootPane, "Tài khoản đã tồn tại");
                }
                 else if (pass1.equals(pass2)) {
                        username = txtuser.getText();
                        String password = String.valueOf(txtpass.getPassword());
                        Account account = new Account(username, password);
                        DaoLogin.addAccount(account);
                        JOptionPane.showMessageDialog(rootPane, "Đăng kí thành công");
                        new LoginView().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Mật khẩu không trùng khớp");
                        txtpass.setText("");
                        txtpass2.setText("");
                    }

}
        });

        layout.putConstraint("West", this.label1, 190, "West", panel);
        layout.putConstraint("North", this.label1, 123, "North", panel);
        layout.putConstraint("West", this.label2, 190, "West", panel);
        layout.putConstraint("North", this.label2, 173, "North", panel);
        layout.putConstraint("West", this.label3, 190, "West", panel);
        layout.putConstraint("North", this.label3,223, "North", panel);
        layout.putConstraint("West",this.label4,300,"West",panel);
        layout.putConstraint("North",this.label4,70,"North",panel);

        layout.putConstraint("West",this.txtuser,300,"West",panel);
        layout.putConstraint("North",this.txtuser,120,"North",panel);
        layout.putConstraint("West",this.txtpass,300,"West",panel);
        layout.putConstraint("North",this.txtpass,170,"North",panel);
        layout.putConstraint("West",this.txtpass2,300,"West",panel);
        layout.putConstraint("North",this.txtpass2,220,"North",panel);
        layout.putConstraint("West",this.btnSave,300,"West",panel);
        layout.putConstraint("North",this.btnSave,260,"North",panel);
        layout.putConstraint("West",this.btnSave,360,"West",panel);
        layout.putConstraint("North",this.btnSave,260,"North",panel);


    }
//    public static void main(String args[]) {
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RegisterView frame = new RegisterView();
//                frame.setVisible(true);
//                frame.setSize(750, 400);
//
//
//            }
//        });
//    }
}
