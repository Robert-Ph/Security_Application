package form;

import alorithms.Electronic_signature;
import alorithms.RSACipher;
import model.ButtonDesign;
import model.ReadFile;
import model.SaveData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class UIElectronicSignature extends JPanel {
    private Electronic_signature electronicSignature;
    private KeyPair keyPair;
    private JPanel panelKey, panelSign,panelVerify;
    private JLabel labelPhubKey, labelPriveKey, labelOpenFile, labelSign, lableTest, labelVeri;
    private TextArea textPriveKey, textPublicKey, textSign, textVerify, textTest ;
    private JTextField textFileSign, textFileVerify;
    private ButtonDesign buttonCreaKey, buttonOpenPriveKey, buttonOpenPubicKey, buttonSign, buttonVerify, buttonOpenFile,buttonOpenFileVe, buttonSaveSign, buttonOpenHash;
    public UIElectronicSignature() {
        innit();
    }
    public void innit(){
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());

        panelKey = new JPanel();
        panelKey.setPreferredSize(new Dimension(1000, 100));
        panelKey.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Khóa", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        labelPhubKey = new JLabel("Khóa công khai: ");
        labelPriveKey = new JLabel("     Khóa bí mật: ");
        textPriveKey = new TextArea(1,52);
        textPriveKey.setEditable(false);
        textPublicKey = new TextArea(1,52);
        textPublicKey.setEditable(false);

        buttonCreaKey = new ButtonDesign();
        buttonCreaKey.setText("Tạo khóa");

        buttonOpenPriveKey = new ButtonDesign();
        buttonOpenPriveKey.setText("Mở khóa bí mật");

        buttonOpenPubicKey = new ButtonDesign();
        buttonOpenPubicKey.setText("Mở khóa công khai");

        panelKey.add(labelPhubKey);
        panelKey.add(textPublicKey);
        panelKey.add(labelPriveKey);
        panelKey.add(textPriveKey);
        panelKey.add(buttonCreaKey);
        panelKey.add(buttonOpenPubicKey);
        panelKey.add(buttonOpenPriveKey);

        panelSign = new JPanel();
        panelSign.setLayout(new BorderLayout());
        panelSign.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Tạo chữ ký", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panelNon = new JPanel();
        JPanel panel = new JPanel();
        panelNon.setPreferredSize(new Dimension(300, 300));
        labelOpenFile = new JLabel("File     ");
        textFileSign = new JTextField(50);
        buttonOpenFile = new ButtonDesign();
        buttonOpenFile.setText("chọn file");
        labelSign = new JLabel("chữ ký");
        textSign = new TextArea(2,85);
        buttonSign = new ButtonDesign();
        buttonSign.setText("Tạo chữ ký");
        buttonSaveSign = new ButtonDesign();
        buttonSaveSign.setText("Lưu chữ ký");

        panel.add(labelOpenFile);
        panel.add(textFileSign);
        panel.add(buttonOpenFile);
        panel.add(labelSign);
        panel.add(textSign);
        panel.add(buttonSign);
        panel.add(buttonSaveSign);
        panelSign.add(panel, BorderLayout.CENTER);
        panelSign.add(panelNon, BorderLayout.EAST);



        panelVerify = new JPanel();
        panelVerify.setLayout(new BorderLayout());
        panelVerify.setPreferredSize(new Dimension(1000, 300));
        panelVerify.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Kiểm tra chữ ký", TitledBorder.LEADING, TitledBorder.TOP, null, null));


        JPanel panelVe = new JPanel();
        labelOpenFile = new JLabel("File     ");
        textFileVerify = new JTextField(44);
        buttonOpenFileVe = new ButtonDesign();
        buttonOpenFileVe.setText("chọn file");

        lableTest = new JLabel("Mã kiểm tra");
        textTest = new TextArea(2,70);
        buttonOpenHash = new ButtonDesign();
        buttonOpenHash.setText("chọn file");

        labelVeri = new JLabel("Kết quả:");
        textVerify = new TextArea(3,80);


        buttonVerify = new ButtonDesign();
        buttonVerify.setText("Kiểm tra");

        panelVe.add(labelOpenFile);
        panelVe.add(textFileVerify);
        panelVe.add(buttonOpenFileVe);
        panelVe.add(lableTest);
        panelVe.add(textTest);
        panelVe.add(buttonOpenHash);
        panelVe.add(labelVeri);
        panelVe.add(textVerify);
        panelVe.add(buttonVerify);



        JPanel panelNonVe = new JPanel();
        panelNonVe.setPreferredSize(new Dimension(300, 300));
        JLabel j = new JLabel("<html>Lưu ý: Mã Hash kiểm tra phải là mã<br> được tạo ra từ thuật toán MD5<html>");
        j.setForeground(Color.red);
        panelNonVe.add(j);
        panelVerify.add(panelVe, BorderLayout.CENTER);
        panelVerify.add(panelNonVe, BorderLayout.EAST);

        this.add(panelKey, BorderLayout.NORTH);
        this.add(panelSign, BorderLayout.CENTER);
        this.add(panelVerify, BorderLayout.SOUTH);

        /*
        sự kiện Khóa
         */

        buttonCreaKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Electronic_signature rsaCipher = new Electronic_signature();
                keyPair = rsaCipher.generateRSAKeyPair();
                textPriveKey.setText(rsaCipher.keyToString(keyPair.getPrivate()));
                textPublicKey.setText(rsaCipher.keyToString(keyPair.getPublic()));
            }
        });

        //đọc file chứa khóa và kiểm tra
        buttonOpenPriveKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){

                    File select = fileChooser.getSelectedFile();
                    try {
                        ReadFile readFile = new ReadFile();
                        String text = readFile.readFiletoString(select.getAbsolutePath());
                        if (readFile.checkStringEquaByte(text, 2048)){
                            textPriveKey.setText(text);
                        }else {
                            JOptionPane.showMessageDialog(null, "Khóa không đủ kích thước hoặc vượt khóa kích thước quy định","Lỗi",JOptionPane.OK_OPTION);
                        }

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });
        buttonOpenPubicKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){

                    File select = fileChooser.getSelectedFile();
                    try {
                        ReadFile readFile = new ReadFile();
                        String text = readFile.readFiletoString(select.getAbsolutePath());
                        if (readFile.checkStringEquaByte(text, 2048)){
                            textPublicKey.setText(text);
                        }else {
                            JOptionPane.showMessageDialog(null, "Khóa không đủ kích thước hoặc vượt khóa kích thước quy định","Lỗi",JOptionPane.OK_OPTION);
                        }

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        buttonOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    textFileSign.setText(select.getAbsolutePath());
                }
            }
        });

        buttonOpenFileVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    textFileVerify.setText(select.getAbsolutePath());
                }
            }
        });

        buttonOpenHash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    textTest.setText(select.getAbsolutePath());
                }
            }
        });

        buttonSaveSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File select = fileChooser.getSelectedFile();
                    String filePath = select.getAbsolutePath();
                    String data = textSign.getText();

                    SaveData save = new SaveData();
                    save.saveData(filePath, data);
                }
            }
        });
        buttonSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                electronicSignature = new Electronic_signature();

                String hash = electronicSignature.hashFile(textFileSign.getText());
                try {
                    String sign = electronicSignature.signData(hash, keyPair.getPrivate());
                    textSign.setText(sign);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonVerify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                electronicSignature = new Electronic_signature();
                String hash = electronicSignature.hashFile(textFileSign.getText());

                String test = textTest.getText();
                try {
                    String t = new ReadFile().readFiletoString(textFileVerify.getText());
                    if (electronicSignature.verifySignature(t, keyPair.getPublic(), test)){
                        textVerify.setText("Hợp lệ");
                        JOptionPane.showMessageDialog(null, "Chữ ký hợp lệ","Thông báo",JOptionPane.OK_OPTION);

                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}
