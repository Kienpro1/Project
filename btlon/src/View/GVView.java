package View;

import Dao.DaoGV;
import Model.GiangVien;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.BorderFactory.createTitledBorder;

public class GVView extends JFrame {
    private JButton btnDelete;
    private JButton btnFind;
    private JButton btnUpdate;
    private JButton btnExcel;
    private JButton btnEdit;
    private JButton btnSave;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;


    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable tblGV;
    private JTextField txtMaGV;
    private JTextField txtHoTenGV;
    private JTextField txtTuoi;
    private JTextField txtBoMon;
    private JTextField txtChuNhiem;
    private JTextField txtSDT;
    private JTextField txtSearch;

    private final String[] columnNames = new String[]{"Mã GV ", "Họ Tên", "Tuổi", "Bộ Môn", "Chủ Nhiệm", " SDT"};
    private final Object data = new Object[0][];
    DefaultTableModel tableModel;

    List<GiangVien> GVList = new ArrayList<>();

    public GVView() {
        initComponents();

        tableModel = (DefaultTableModel) tblGV.getModel();
        tblGV.setAutoCreateRowSorter(true);
        showGV();
        this.setSize(750,400);
    }

    public void showGV() {
        GVList = DaoGV.findAll();

        tableModel.setRowCount(0);

        GVList.forEach((GV) -> {
            tableModel.addRow(new Object[]{GV.getMaGV(),
                    GV.getHoTenGV(), GV.getTuoi(), GV.getBoMon(), GV.getChuNhiem(), GV.getSDT()});
        });
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void initComponents() {

        Menu menu=new Menu();

        setJMenuBar(menu);

ImageIcon image = new ImageIcon("C:\\Users\\trant\\IdeaProject\\qlgv_2\\src\\icon\\education.png");

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel(new ImageIcon("C:\\Users\\trant\\IdeaProject\\qlgv_2\\src\\icon\\education.png"));


        txtMaGV = new JTextField(15);
        txtHoTenGV = new JTextField(15);
        txtTuoi = new JTextField(15);
        txtBoMon = new JTextField(15);
        txtChuNhiem = new JTextField(15);
        txtSDT = new JTextField(15);
        txtSearch = new JTextField(30);

        btnSave = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();


        btnExcel = new JButton();
        btnEdit = new JButton();
        jScrollPane1 = new JScrollPane();
        tblGV = new JTable((Object[][]) data, columnNames);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("Quản lý giảng viên");


        jPanel1.setBorder(createTitledBorder("Nhập thông tin giảng viên"));
        jLabel1.setText("Mã giảng viên");
        jLabel2.setText("Họ và tên ");
        jLabel3.setText("Tuổi:");
        jLabel4.setText("Bộ Môn:");
        jLabel5.setText("Chủ Nhiệm:");
        jLabel6.setText("Số điện thoại:");
        GVList = DaoGV.findAll();



        //Theem
        btnSave.setIcon(new ImageIcon("C:\\Users\\trant\\IdeaProject\\qlgv_2\\src\\icon\\plus.png"));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    for (int i = 0; i < tblGV.getRowCount(); i++) {
                        if (txtMaGV.getText().equals(GVList.get(i).getMaGV())) {
                            JOptionPane.showMessageDialog(null, "Mã giáo viên đã tồn tại");
                        }

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (txtMaGV.getText().equals("") || txtHoTenGV.getText().equals("") || txtTuoi.getText().length() <= 0 || txtBoMon.equals("") || txtChuNhiem.equals("") || txtSDT.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Thông tin giáo viên còn trống!  ");
                } else {
                    String MaGV = txtMaGV.getText().trim();
                    String HoTenGV = txtHoTenGV.getText().trim();
                    int Tuoi = Integer.parseInt(txtTuoi.getText());
                    String BoMon = txtBoMon.getText();
                    String ChuNhiem = txtChuNhiem.getText();
                    String SDT = txtSDT.getText();
                    GiangVien GV = new GiangVien(MaGV, HoTenGV, Tuoi, BoMon, ChuNhiem, SDT);
                    DaoGV.insert(GV);
                    showGV();
                    txtMaGV.setText("");
                    txtMaGV.setEditable(true);
                    txtHoTenGV.setText("");
                    txtTuoi.setText("");
                    txtBoMon.setText("");
                    txtChuNhiem.setText("");
                    txtSDT.setText("");
                }
            }
        });


        //Xóa
        btnDelete.setIcon(new ImageIcon("src/icon/delete.png"));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int selected = tblGV.getSelectedRow();

                if (selected<0) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn giảng viên cần xóa!  ");
                } else {

                        GiangVien GV = GVList.get(selected);
                        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn xóa giảng viên với mã giáo viên là "+GV.getMaGV()+" không?");
                        System.out.println("option : " + option);
                        if (option == 0) {
                            DaoGV.delete(GV.getMaGV());

                            showGV();

                    }
                }
            }
        });


        //Tìm Kiếm

        btnFind = new JButton(new ImageIcon("src/icon/search.png"));
        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String input = txtSearch.getText();
                if (input != null && input.length() > 0) {
                    GVList = DaoGV.findByFullname(input);

                    tableModel.setRowCount(0);


                    GVList.forEach((GV) -> {
                        tableModel.addRow(new Object[]{GV.getMaGV(),GV.getHoTenGV(),
                                GV.getTuoi(), GV.getBoMon(), GV.getChuNhiem(), GV.getSDT()});
                    });
                } else {
                    showGV();
                }
            }
        });


        //Excel
        btnExcel.setIcon(new ImageIcon("src/icon/excel.png"));
        btnExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.showSaveDialog(rootPane);
                    File saveFile = jFileChooser.getSelectedFile();
                    saveFile = new File(saveFile.toString() + ".xlsx");
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("danh sach");
                    XSSFRow row = null;
                    XSSFCell cell = null;
                    row = sheet.createRow(1);

                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue("Mã giảng viên");

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue("Họ tên ");

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue("Tuổi");

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue("Bộ Môn");

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue("Chủ Nhiệm");

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue("SDT");
                    for (int i = 0; i < GVList.size(); i++) {

                        row = sheet.createRow(2 + i);
                        cell = row.createCell(0, CellType.STRING);
                        cell.setCellValue(GVList.get(i).getMaGV());

                        cell = row.createCell(1, CellType.STRING);
                        cell.setCellValue(GVList.get(i).getHoTenGV());

                        cell = row.createCell(2, CellType.NUMERIC);
                        cell.setCellValue(GVList.get(i).getTuoi());

                        cell = row.createCell(3, CellType.STRING);
                        cell.setCellValue(GVList.get(i).getHoTenGV());

                        cell = row.createCell(4, CellType.STRING);
                        cell.setCellValue(GVList.get(i).getBoMon());

                        cell = row.createCell(5, CellType.STRING);
                        cell.setCellValue(GVList.get(i).getSDT());


                    }


                    try {
                        FileOutputStream out = new FileOutputStream(saveFile.toString());
                        workbook.write(out);
                        out.close();

                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(rootPane, "in thành công");
                } catch (Exception exception) {
                    System.out.println(e);
                }

            }
        });

//sua
        btnEdit.setIcon(new ImageIcon("src/icon/edit.png"));
        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = tblGV.getSelectedRow();
                if(selected ==-1){
                    JOptionPane.showMessageDialog(rootPane,"Vui lòng chọn giảng viên cần sửa");
                }else {
                    GiangVien GV = GVList.get(selected);
                    txtMaGV.setText(GV.getMaGV());
                    txtMaGV.setEditable(false);
                    txtHoTenGV.setText(GV.getHoTenGV());
                    txtTuoi.setText(String.valueOf(GV.getTuoi()));
                    txtBoMon.setText(GV.getBoMon());
                    txtChuNhiem.setText(GV.getChuNhiem());
                    txtSDT.setText(GV.getSDT());
                }

            }
        });
//Update
        btnUpdate.setIcon(new ImageIcon("src/icon/diskette.png"));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DefaultTableModel tblModel = (DefaultTableModel) tblGV.getModel();



                if (txtMaGV.getText().equals("") || txtHoTenGV.getText().equals("") || txtTuoi.getText().length() <= 0 || txtBoMon.equals("") || txtChuNhiem.equals("") || txtSDT.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn giảng viên!  ");
                } else{
                    String MaGV = txtMaGV.getText().trim();
                    String HoTenGV = txtHoTenGV.getText().trim();
                    int Tuoi = Integer.parseInt(txtTuoi.getText());
                    String BoMon = txtBoMon.getText();
                    String ChuNhiem = txtChuNhiem.getText();
                    String SDT = txtSDT.getText();
                    GiangVien GV = new GiangVien(MaGV, HoTenGV, Tuoi, BoMon, ChuNhiem, SDT);
                    DaoGV controller = new DaoGV();
                    controller.update(GV);
                    showGV();
                    txtMaGV.setText("");
                    txtMaGV.setEditable(true);
                    txtHoTenGV.setText("");
                    txtTuoi.setText("");
                    txtBoMon.setText("");
                    txtChuNhiem.setText("");
                    txtSDT.setText("");
                }


}
        });



        this.setDefaultCloseOperation(3);
        this.tableModel = new DefaultTableModel((Object[][]) this.data, this.columnNames);
        this.tblGV.setModel(this.tableModel);
        this.jScrollPane1.setViewportView(this.tblGV);
        this.jScrollPane1.setPreferredSize(new Dimension(416, 177));
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 420);
        panel.setLayout(layout);
        panel.add(this.jScrollPane1);
        panel.add(this.btnSave);
        panel.add(this.btnDelete);
        panel.add(this.btnExcel);
        panel.add(this.btnUpdate);
        panel.add(this.btnFind);
        panel.add(this.btnEdit);
        panel.add(this.jLabel1);
        panel.add(this.jLabel2);
        panel.add(this.jLabel3);
        panel.add(this.jLabel4);
        panel.add(this.jLabel5);
        panel.add(this.jLabel6);
        panel.add(this.jLabel7);
        panel.add(this.jLabel8);
        panel.add(this.txtMaGV);
        panel.add(this.txtHoTenGV);
        panel.add(this.txtTuoi);
        panel.add(this.txtBoMon);
        panel.add(this.txtChuNhiem);
        panel.add(this.txtSDT);
        panel.add(this.txtSearch);

        jLabel7.setFont(new Font("Verdana", Font.BOLD, 15));
        jLabel1.setFont(new Font("Verdana", Font.BOLD, 10));
        jLabel3.setFont(new Font("Verdana", Font.BOLD, 10));
        jLabel2.setFont(new Font("Verdana", Font.BOLD, 10));
        jLabel4.setFont(new Font("Verdana", Font.BOLD, 10));
        jLabel5.setFont(new Font("Verdana", Font.BOLD, 10));
        jLabel6.setFont(new Font("Verdana", Font.BOLD, 10));
        jScrollPane1.setFont(new Font("Verdana", Font.BOLD, 10));

        btnExcel.setBackground(new Color(73, 193, 9));
        btnDelete.setBackground(new Color(249, 3, 61));
        panel.setBackground(new Color(209, 200, 204));

//Tuoi
        txtTuoi.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = txtTuoi.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'&&l<2) {
                    txtTuoi.setEditable(true);

                }
                else {


                }
            }
        });

        txtSDT.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = txtSDT.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'&&l<9) {
                    txtSDT.setEditable(true);

                }
                else {


                }
            }
        });
        layout.putConstraint("West", this.jLabel1, 10, "West", panel);
        layout.putConstraint("North", this.jLabel1, 80, "North", panel);
        layout.putConstraint("West", this.jLabel2, 10, "West", panel);
        layout.putConstraint("North", this.jLabel2, 110, "North", panel);
        layout.putConstraint("West", this.jLabel3, 10, "West", panel);
        layout.putConstraint("North", this.jLabel3, 140, "North", panel);
        layout.putConstraint("West", this.jLabel4, 10, "West", panel);
        layout.putConstraint("North", this.jLabel4, 170, "North", panel);
        layout.putConstraint("West", this.jLabel5, 10, "West", panel);
        layout.putConstraint("North", this.jLabel5, 200, "North", panel);
        layout.putConstraint("West", this.jLabel6, 10, "West", panel);
        layout.putConstraint("North", this.jLabel6, 230, "North", panel);
        layout.putConstraint("West", this.jLabel7, 300, "West", panel);
        layout.putConstraint("North", this.jLabel7, 10, "North", panel);
        layout.putConstraint("West", this.jLabel8, 220, "West", panel);
        layout.putConstraint("North", this.jLabel8, 5, "North", panel);



        layout.putConstraint("West", this.txtMaGV, 120, "West", panel);
        layout.putConstraint("North", this.txtMaGV, 40+30, "North", panel);
        layout.putConstraint("West", this.txtHoTenGV, 120, "West", panel);
        layout.putConstraint("North", this.txtHoTenGV, 70+30, "North", panel);
        layout.putConstraint("West", this.txtTuoi, 120, "West", panel);
        layout.putConstraint("North", this.txtTuoi, 100+30, "North", panel);
        layout.putConstraint("West", this.txtBoMon, 120, "West", panel);
        layout.putConstraint("North", this.txtBoMon, 160, "North", panel);
        layout.putConstraint("West", this.txtChuNhiem, 120, "West", panel);
        layout.putConstraint("North", this.txtChuNhiem, 190, "North", panel);
        layout.putConstraint("West", this.txtSDT, 120, "West", panel);
        layout.putConstraint("North", this.txtSDT, 220, "North", panel);
        layout.putConstraint("West", this.txtSearch, 300, "West", panel);
        layout.putConstraint("North", this.txtSearch, 35 ,"North", panel);

        layout.putConstraint("West", this.jScrollPane1, 300, "West", panel);
        layout.putConstraint("North", this.jScrollPane1, 70, "North", panel);

        layout.putConstraint("West", this.btnSave, 300, "West", panel);
        layout.putConstraint("North", this.btnSave, 250, "North", panel);
        layout.putConstraint("West", this.btnUpdate, 400, "West", panel);
        layout.putConstraint("North", this.btnUpdate, 250, "North", panel);
        layout.putConstraint("West", this.btnDelete, 540, "West", panel);
        layout.putConstraint("North", this.btnDelete, 250, "North", panel);
        layout.putConstraint("West", this.btnEdit, 660, "West", panel);
        layout.putConstraint("North", this.btnEdit, 250, "North", panel);

        layout.putConstraint("West", this.btnFind, 620, "West", panel);
        layout.putConstraint("North", this.btnFind, 35, "North", panel);
        layout.putConstraint("West", this.btnExcel, 670, "West", panel);
        layout.putConstraint("North", this.btnExcel, 35, "North", panel);


        this.add(panel);
        this.setTitle("Quan lý giảng viên");
        this.setSize(1350, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }





}
