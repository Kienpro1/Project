/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package View;


import Dao.DaoLop;
import Model.Lop;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Admin
 */
public class LopView implements ActionListener{
    public Lop lop;
    public  JButton btn_add,btn_edit,btn_delete,btn_clear,btn_timkiem,btn_xuatEX;
    public  JLabel jLabel_malop,jLabel_tenlop,jLabel_tennganh,jLabel_hedt,jLabel_namnh;
    public  JTextField textField_malop,textField_tenlop,textField_namnh,textField_tiemkiem;
    public  JPanel left,right,input,button,bottom;
    public  JComboBox cb_tennganh,cb_hedt;
    public  DefaultTableModel model;
    public  JTable table;
    JFrame j = new JFrame();
    DaoLop c;
//    public Connection conn =null;
    
    
    public LopView(){
        this.lop = new Lop();
        this.init();

        j.setVisible(true);
    }
    public void init(){
        
        j.setTitle("Quan Ly Lop");
        j.setSize(1200, 600);
        j.setLocationRelativeTo(null);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        j.setLayout(new BorderLayout());

        Menu menu=new Menu();

        j.setJMenuBar(menu);
        
        //tao from input
        input = new JPanel(new GridBagLayout());
        GridBagConstraints gbcInput = new GridBagConstraints();
        gbcInput.insets = new Insets(10, 10, 10, 10);
        
        String[] hedt = new String[]{"","Dai Hoc","Cao Dang"}; 
        String[] tennganh = new String[]{"","Cong Nghe Thong Tin","Ky Thuat Dien","Kinh Te"};
        
        jLabel_malop = new JLabel("Ma Lop");
        textField_malop = new JTextField(20);
        jLabel_tenlop = new JLabel("Ten Lop");
        textField_tenlop = new JTextField(20);
        jLabel_tennganh = new JLabel("Ten Nganh");
        Dimension textFieldSize = textField_malop.getPreferredSize();
        int comboBoxWidth = textFieldSize.width;
        cb_tennganh = new JComboBox(tennganh);
        cb_tennganh.setPreferredSize(new Dimension(comboBoxWidth, cb_tennganh.getPreferredSize().height));
        jLabel_hedt = new JLabel("He Dao Tao");
        cb_hedt = new JComboBox(hedt);
        cb_hedt.setPreferredSize(new Dimension(comboBoxWidth, cb_hedt.getPreferredSize().height));
        jLabel_namnh = new JLabel("Nam Nhap Hoc");
        textField_namnh = new JTextField(20);
        
        gbcInput.gridx = 0;
        gbcInput.gridy = 0;
        input.add(jLabel_malop, gbcInput);
        
        gbcInput.gridx = 1;
        gbcInput.gridy = 0;
        input.add(textField_malop, gbcInput);
        
        gbcInput.gridx = 0;
        gbcInput.gridy = 1;
        input.add(jLabel_tenlop, gbcInput);
        
        gbcInput.gridx = 1;
        gbcInput.gridy = 1;
        input.add(textField_tenlop, gbcInput);
        
        gbcInput.gridx = 0;
        gbcInput.gridy = 2;
        input.add(jLabel_tennganh, gbcInput);
        
        gbcInput.gridx = 1;
        gbcInput.gridy = 2;
        input.add(cb_tennganh, gbcInput);
        
        gbcInput.gridx = 0;
        gbcInput.gridy = 3;
        input.add(jLabel_hedt, gbcInput);
        
        gbcInput.gridx = 1;
        gbcInput.gridy = 3;
        input.add(cb_hedt, gbcInput);
        
        gbcInput.gridx = 0;
        gbcInput.gridy = 4;
        input.add(jLabel_namnh, gbcInput);
        
        gbcInput.gridx = 1;
        gbcInput.gridy = 4;
        input.add(textField_namnh, gbcInput);
        
        //tao from button them sua xoa
        
        button = new JPanel(new FlowLayout());
        btn_add = new JButton("Add");
        btn_add.addActionListener(this);
        button.add(btn_add);
        btn_edit = new JButton("Edit");
        button.add(btn_edit);
        btn_edit.addActionListener(this);
        btn_delete = new JButton("Delete");
        button.add(btn_delete);
        btn_delete.addActionListener(this);
        btn_clear = new JButton("Clear");
        button.add(btn_clear);
        btn_clear.addActionListener(this);
        
        //tạo form bang ben phải
        right = new JPanel();
        String[] columnNames = {"Ma Lop", "Ten Lop", "Ten Nganh", "Ma GV","He DT","Nam NH"};
        model = new DefaultTableModel(columnNames, 0);
//        loadData();
        c = new DaoLop();
        c.loadData(model);
        
        table = new JTable(model);
        
        setColumnWidths();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 450));
        right.add(scrollPane);
        table.addMouseListener(new MouseListener()// chọn dòng
        {

            public void mouseClicked(MouseEvent e) {
                
                    int selectRowIndex = table.getSelectedRow();
                    if(selectRowIndex != -1){
                    // đưa dữ liệu ra from chỉnh sửa
                    String maLop = model.getValueAt(selectRowIndex, 0).toString();
                    String tenLop = model.getValueAt(selectRowIndex, 1).toString();
                    String tennganh = model.getValueAt(selectRowIndex, 2).toString();
                    String heDT = model.getValueAt(selectRowIndex, 4).toString();
                    String namNH = model.getValueAt(selectRowIndex, 5).toString();
                    textField_malop.setText(maLop);
                    textField_malop.setEditable(false);
                    textField_tenlop.setText(tenLop);
                    cb_tennganh.setSelectedItem(tennganh);
                    cb_hedt.setSelectedItem(heDT);
                    textField_namnh.setText(namNH);
                    
                }
            }

            public void mousePressed(MouseEvent e) {                
            }
            public void mouseReleased(MouseEvent e) {               
            }
            public void mouseEntered(MouseEvent e) {                
            }
            public void mouseExited(MouseEvent e) {             
            }
        });
        
        // tìm kiếm và xuất exel
        bottom = new JPanel(new FlowLayout());
        textField_tiemkiem = new JTextField("Tim kiem ma lop", 30);
        btn_timkiem = new JButton("Search");
        btn_timkiem.addActionListener(this);
        btn_xuatEX = new JButton("Excel Export");
        btn_xuatEX.addActionListener(this);
        bottom.add(textField_tiemkiem);
        bottom.add(btn_timkiem);
        bottom.add(btn_xuatEX);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 350, 20, 0)); // Đặt khoảng cách là 10 pixel từ lề dưới
        
        //add vào this
        left = new JPanel(new GridLayout(2, 1));
        left.add(input);
        left.add(button);
        j.add(left, BorderLayout.WEST);
        j.add(right,BorderLayout.CENTER);
        j.add(bottom,BorderLayout.NORTH);
        j.setVisible(true);
    }
    private void setColumnWidths() {
        // Thiết lập kích thước chiều rộng cho từng cột
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120); // Cột malop
        columnModel.getColumn(1).setPreferredWidth(120); // Cột ten lop
        columnModel.getColumn(2).setPreferredWidth(200); // Cột ten nganh
        columnModel.getColumn(3).setPreferredWidth(120); // Cột ma giao vien
        columnModel.getColumn(4).setPreferredWidth(120); // Cột he dao tao
        columnModel.getColumn(5).setPreferredWidth(120);
    }
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Add")){
            click_btn_add();           
        }
        if(src.equals("Edit")){
            click_btn_edit();
        }
        if(src.equals("Search")){
            click_btn_search();
        }
        if(src.equals("Delete")){
            click_btn_delete();
        }
        if(src.equals("Clear")){
            click_btn_clear();
        }
        if(src.equals("Excel Export")){
            click_btn_exel();
        }
    }
    
    public void click_btn_add(){
        System.out.println("nut add");
        String malop = textField_malop.getText();
        System.out.println(malop);
        String tenlop = textField_tenlop.getText();
        String nannh = textField_namnh.getText();
        String tennganh = cb_tennganh.getSelectedItem().toString();
        String hedt = cb_hedt.getSelectedItem().toString();
        c.addLop(model, malop, tenlop, tennganh, hedt, nannh);
    }
    
    public void click_btn_edit(){
        System.out.println("nut edit");
        System.out.println("nut add");
        String malop = textField_malop.getText();
        String tenlop = textField_tenlop.getText();
        String nannh = textField_namnh.getText();
        String tennganh = cb_tennganh.getSelectedItem().toString();
        String hedt = cb_hedt.getSelectedItem().toString();
        c.updateLop(model,table, malop, tenlop, tennganh, hedt, nannh);
        
    }
    
    public void click_btn_delete(){
        c.deleteLop(model, table);
    }
    
    public void click_btn_clear(){
        textField_malop.setText("");textField_tenlop.setText("");textField_namnh.setText("");
        cb_hedt.setSelectedIndex(-1);cb_tennganh.setSelectedIndex(-1);
    }
    
    public void click_btn_search(){
        System.out.println("nut search");
        String malop = textField_tiemkiem.getText(); 
        if(malop.equals("")){
            JOptionPane.showMessageDialog(null, "Hay nhap thong tin.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        c.searchByMaLop(model, malop);
        }
    }

    public void click_btn_exel(){
        c.exportToExcel(model);
    }
    
    //    khởi tạo get set
    
//    public static void main(String[] args){
//        ViLop lopView = new ViLop();
//    }
//
//
}
