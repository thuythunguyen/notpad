/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswing;

/**
 *
 * @author thuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Notepad extends JFrame {

    private JTextArea txtArea;

    public Notepad() {
        super();
        this.setTitle("notepad");
        this.setSize(500,500);
        // sử dụng borderlayou
        // mac dinh Jframe -- FlowLayout
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    public void init() {
        // them o nhap lieu vao vi tri trung tam
        this.txtArea = new JTextArea();
        this.add(txtArea, BorderLayout.CENTER);


        // thanh menu
        JMenuBar bar = new JMenuBar();

        // tạo menu file
        JMenu mnuFile = new JMenu("File");
        bar.add(mnuFile);
        mnuFile = new JMenu("File");
        bar.add(mnuFile);
        JMenu mnuEdit = new JMenu("Edit");
        bar.add(mnuEdit);
        JMenu mnuFormat = new JMenu("Format");
        bar.add(mnuFormat);
        JMenu mnuView = new JMenu("View");
        bar.add(mnuView);
        JMenu mnuHelp = new JMenu("Help");
        bar.add(mnuHelp);


        // tao noi dung cho menu File --> New / Open / Save / Exit
        JMenuItem itemNew = new JMenuItem("New");
        itemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });
        mnuFile.add(itemNew);

        JMenuItem itemOpen = new JMenuItem("Open");
        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        mnuFile.add(itemOpen);

        JMenuItem itemSave = new JMenuItem("Save");
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        mnuFile.add(itemSave);

        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        mnuFile.add(itemExit);

        // tạo menu edit
        mnuEdit = new JMenu("Edit");
        bar.add(mnuEdit);

        this.setJMenuBar(bar);
    }

    public void newFile() {
        // xoa trang noi dung cua o text
        int ret = JOptionPane.showConfirmDialog(null, 
                "do you want to save this file",
                "há»™p thoáº¡i há»i táº¡o má»›i",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if(ret == JOptionPane.NO_OPTION) {

        this.txtArea.setText("");
        } else if(ret == JOptionPane.YES_OPTION){
            saveFile();
        } else {
            return;
        }

    }
    

    public void saveFile() {
String data = txtArea.getText();
        // luu noi dung vao file
        // ten file ng dung nhap
        JFileChooser chooser = new JFileChooser();
        int rs = chooser.showSaveDialog(this);
        if (rs == JFileChooser.APPROVE_OPTION) {
            // thuc hienj chuc nang save
            // dung PrintWriter de ghi noi dung ra file
            File selectedFile = chooser.getSelectedFile();
            
            if (selectedFile.exists()) {
                int set = JOptionPane.showConfirmDialog(null, "do you want to save this file?", "save", JOptionPane.YES_NO_OPTION);
                if (set == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(selectedFile));
                pw.println(data);
                pw.close();
                JOptionPane.showMessageDialog(null, "suceesfull");
            } catch (Exception e) {
                e.printStackTrace();

        }
    }
    }

    public void openFile() {
        // ng dung chonj duong dan
        JFileChooser chooser = new JFileChooser();
        int rs = chooser.showOpenDialog(this); // tra ve ng dung bam nut gi
        // bam ok
        if (rs == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            // doc file
            // hien thi noi dung len textarea = cach goi setText()
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(selectedFile), "UTF-8"));
                String line = br.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line + "\n");
                    line = br.readLine();
                }
                txtArea.setText(builder.toString());
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (rs == JFileChooser.CANCEL_OPTION) {
            // khong xu ly gi ca
            System.out.println("Nguoi dung vua bam cancel");
        }
    }

    public void exit() {
        int set = JOptionPane.showConfirmDialog(null, "do you want to exit", "exit", JOptionPane.YES_NO_OPTION);
        if(set==JOptionPane.YES_OPTION)
        System.exit(0);
    }
    public void showwindow(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


}