package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;


public class Menu extends JMenuBar {
    public Menu() {
        // Gọi constructor của lớp cha
        super();
        JMenu quanly= new JMenu("Quản lý");
        JMenu tintuc= new JMenu("Tin tức");
        JMenu exitmenu= new JMenu("Thoát");


        // Tạo các JMenu
        JMenuItem stdmenu = new JMenuItem("Sinh viên");
        JMenuItem teachmenu = new JMenuItem("GIảng viên");
        JMenuItem scoremenu = new JMenuItem("Điểm");
        JMenuItem classmenu = new JMenuItem("Lớp");
        JMenuItem login = new JMenuItem("Đăng xuất");
        JMenuItem out = new JMenuItem("Thoát");
        JMenuItem tintucs = new JMenuItem("Tin Tức");




        quanly.add(stdmenu);
        quanly.add(teachmenu);
        quanly.add(scoremenu);
        quanly.add(classmenu);
        exitmenu.add(login);
        exitmenu.add(out);
        tintuc.add(tintucs);
        this.add(quanly);
        this.add(tintuc);
        this.add(exitmenu);

        tintucs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mở liên kết trong trình duyệt mặc định
                    Desktop.getDesktop().browse(new URI("https://utt.edu.vn/utt/tin-tuc-n3.html"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
            }
        });

        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Thoát chương trình
            }
        });
        stdmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SinhVienView();
            }
        });
        teachmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GVView();

            }
        });
        scoremenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new DiemView();
            }
        });
        classmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LopView();
            }
        });



    }
}
