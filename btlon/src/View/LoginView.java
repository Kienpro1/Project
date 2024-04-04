package View;

import Dao.DaoLogin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JTextField txtuser;
    private JPasswordField txtpass;
    private JButton btnLogin;
    private JButton btnRegister;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel;
    
    public LoginView(){
        initComponent();
    }

    private void initComponent() {
        JPanel panel = new JPanel();
setSize(700,400);
        SpringLayout layout = new SpringLayout();

        txtuser=new JTextField(20);
        txtpass=new JPasswordField(20);
        btnLogin=new JButton("Đăng nhập");
        btnRegister=new JButton("Đăng kí");

        label1=new JLabel("Tài khoản");
        label2=new JLabel("Mật khẩu");
        label3= new JLabel("Đăng nhập");
        panel.add(this.txtuser);
        panel.add(this.txtpass);
        panel.add(this.btnLogin);

        panel.add(this.btnRegister);
        panel.add(this.label1);
        panel.add(this.label2);
        panel.add(this.label3);
        this.add(panel);

        label1.setFont(new Font("Verdana", Font.BOLD, 10));
        label2.setFont(new Font("Verdana", Font.BOLD, 10));
        label3.setFont(new Font("Verdana", Font.BOLD, 20));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("Đăng nhập");
        panel.setLayout(layout);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtuser.getText();
                String password = txtpass.getText();
                if (username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Tài khoản mật khẩu trống");
                } else if (DaoLogin.login(username, password)) {
               new SinhVienView();
                   dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sai Mật khẩu hoặc tên đăng nhập");
                        txtpass.setText("");
                    }
                }

        });


        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterView().setVisible(true);
                dispose();

            }
        });
        panel.setSize(400, 420);
        layout.putConstraint("West",this.txtuser,250,"West",panel);
        layout.putConstraint("North",this.txtuser,120,"North",panel);
        layout.putConstraint("West",this.txtpass,250,"West",panel);
        layout.putConstraint("North",this.txtpass,170,"North",panel);

        layout.putConstraint("West", this.btnLogin, 250, "West", panel);
        layout.putConstraint("North", this.btnLogin, 230, "North", panel);
        layout.putConstraint("West", this.btnRegister, 370, "West", panel);
        layout.putConstraint("North", this.btnRegister, 230, "North", panel);

        layout.putConstraint("West", this.label1, 190, "West", panel);
        layout.putConstraint("North", this.label1, 123, "North", panel);
        layout.putConstraint("West", this.label2, 190, "West", panel);
        layout.putConstraint("North", this.label2, 173, "North", panel);
        layout.putConstraint("West", this.label3, 300, "West", panel);
        layout.putConstraint("North", this.label3,70, "North", panel);


        this.add(panel);

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
//                LoginView frame = new LoginView();
//                frame.setVisible(true);
//                frame.setSize(750, 400);
//
//
//            }
//        });
//    }
}
