package form;


import alorithms.AffineCipher;
import model.ButtonDesign;
import model.SaveData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UIAffine extends JPanel{
    private String listPlaintext[] = {"English alphabet", "Vietnamese alphabet"};
    public UIAffine(){
        init();
    }

    public void init(){
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());

        JLabel nameCipher = new JLabel("Affine");
        Font font = new Font(nameCipher.getFont().getName(), Font.BOLD, 24);
        nameCipher.setFont(font);
        nameCipher.setHorizontalAlignment(SwingConstants.CENTER);
        nameCipher.setVerticalAlignment(SwingConstants.CENTER);
        nameCipher.setForeground(Color.red);
//        nameCipher.setIcon(new ImageIcon("ImageIcon\\help.png"));
        nameCipher.setPreferredSize(new Dimension(1000, 30));

        JPanel panelBody = new JPanel();
        panelBody.setLayout(new GridLayout(2,1));

        //panel Encry
        JPanel panelEncry = new JPanel();
        panelEncry.setLayout(new BorderLayout());

        //textFile input data - phan nhap dư lieu ma hoa
        JPanel panelText = new JPanel();
        TextArea textArea = new TextArea(13,92);
        panelText.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản rõ", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        //panel button
        JPanel panelButtonEncry = new JPanel();
        panelButtonEncry.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelButtonEncry.setPreferredSize(new Dimension(670, 30));
        JLabel labelPathFile = new JLabel("File");

        JTextField textFieldFile = new JTextField(17);
        textFieldFile.setBorder(new EmptyBorder(0,0,0,0));
        textFieldFile.setPreferredSize(new Dimension(15,26));

        //nut Open File
        ButtonDesign buttonOpenFile = new ButtonDesign();
        buttonOpenFile.setText("Chọn file");

        //nut Save
        ButtonDesign buttonSave = new ButtonDesign();
        buttonSave.setText("Lưu");

        //nut Copy
        ButtonDesign buttonCopy = new ButtonDesign();
        buttonCopy.setText("Sao chép");

        //nut Paste
        ButtonDesign buttonPaste = new ButtonDesign();
        buttonPaste.setText("Paste");

        //nut Clear
        ButtonDesign buttonClear = new ButtonDesign();
        buttonClear.setText("Xóa");


        panelButtonEncry.add(labelPathFile);
        panelButtonEncry.add(textFieldFile);
        panelButtonEncry.add(buttonOpenFile);
        panelButtonEncry.add(buttonSave);
        panelButtonEncry.add(buttonCopy);
        panelButtonEncry.add(buttonPaste);
        panelButtonEncry.add(buttonClear);

        /*
        ##########################################################
         */

        panelText.add(textArea, BorderLayout.CENTER);
        panelText.add(panelButtonEncry, BorderLayout.SOUTH);

        //panelKey and plantext -
        JPanel panelKeyandPlaintext = new JPanel();
        panelKeyandPlaintext.setPreferredSize(new Dimension(300, 100));
        panelKeyandPlaintext.setLayout(new GridLayout(2,1,5,5));

        //panel giao diện phần tao key
        JPanel panelKey = new JPanel();
        panelKey.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelKey.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Khóa k", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JLabel lableKey = new JLabel("Nhập khóa: a:");
        JTextField textKeya = new JTextField(8);
        JLabel lableKeyb = new JLabel(" b:");
        JTextField textKeyb = new JTextField(8);

        //nut Create Key
        ButtonDesign buttonCreateKey = new ButtonDesign();
        buttonCreateKey.setText("Tạo khóa");

        panelKey.add(lableKey);
        panelKey.add(textKeya);
        panelKey.add(lableKeyb);
        panelKey.add(textKeyb);
        panelKey.add(buttonCreateKey);

        //panel giao dien plantext
        JPanel panelPlaintext = new JPanel();
        panelPlaintext.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelPlaintext.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản rõ & bản mã", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JComboBox listCombobox = new JComboBox(listPlaintext);
        listCombobox.setBackground(Color.white);
        JLabel labelplainCipher = new JLabel("P & C: ");
        listCombobox.setPreferredSize(new Dimension(220, 20));
        JLabel labelType= new JLabel("Kiểu: ");
        JCheckBox checkBoxText = new JCheckBox("văn bản");
        checkBoxText.setSelected(true);
        JCheckBox checkBoxFile = new JCheckBox("File");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox selectedCheckBox = (JCheckBox) e.getSource();

                // Đặt tất cả các JCheckBox về trạng thái không chọn
                checkBoxText.setSelected(false);
                checkBoxFile.setSelected(false);

                // Đặt trạng thái của JCheckBox được chọn
                selectedCheckBox.setSelected(true);
            }
        };

        JLabel textAttention = new JLabel("<html> Lưu ý: Mã hóa file chỉ sử dụng đối với<br> file có dữ liệu văn bản");
        textAttention.setForeground(Color.red);

        checkBoxText.addActionListener(actionListener);
        checkBoxFile.addActionListener(actionListener);

        panelPlaintext.add(labelplainCipher);
        panelPlaintext.add(listCombobox);
        panelPlaintext.add(labelType);
        panelPlaintext.add(checkBoxText);
        panelPlaintext.add(checkBoxFile);
        panelPlaintext.add(textAttention);

        panelKeyandPlaintext.add(panelKey);
        panelKeyandPlaintext.add(panelPlaintext);

        //add panel
        panelEncry.add(panelText, BorderLayout.CENTER);
        panelEncry.add(panelKeyandPlaintext, BorderLayout.EAST);

        //panel decry
        JPanel panelDecry = new JPanel();
        panelDecry.setLayout(new BorderLayout());

        //textFile input data - phan nhap dư lieu ma hoa
        JPanel paneltext_Encry = new JPanel();
        TextArea textArea_Encry = new TextArea(13,92);
        textArea_Encry.setEditable(false);
        paneltext_Encry.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản mã", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        //panel button
        JPanel panelButtonDecry = new JPanel();
        panelButtonDecry.setPreferredSize(new Dimension(670, 30));
        panelButtonDecry.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //nut Save
        ButtonDesign buttonSave_Encr = new ButtonDesign();
        buttonSave_Encr.setText("Save");

        //nut Copy
        ButtonDesign buttonCopy_Encr = new ButtonDesign();
        buttonCopy_Encr.setText("Copy");

        //nut Upgrade
        ButtonDesign buttonUpgrade_Encr = new ButtonDesign();
        buttonUpgrade_Encr.setText("Upgrade");

        panelButtonDecry.add(buttonSave_Encr);
        panelButtonDecry.add(buttonCopy_Encr);
        panelButtonDecry.add(buttonUpgrade_Encr);

        paneltext_Encry.add(textArea_Encry, BorderLayout.CENTER);
        paneltext_Encry.add(panelButtonDecry, BorderLayout.EAST);

        //panel right
        JPanel panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(300, 100));

        panelDecry.add(paneltext_Encry, BorderLayout.CENTER);
        panelDecry.add(panelRight, BorderLayout.EAST);

        // add panelEncry and panelDecry
        panelBody.add(panelEncry);
        panelBody.add(panelDecry);

        /*
        ###############################################################################
         */
        //panel : Button Encry and Decry
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));

        ButtonDesign buttonEncry = new ButtonDesign();
        buttonEncry.setText("Encrypted");
        buttonEncry.setFont(new Font(buttonEncry.getName(), Font.BOLD, 16));
        buttonEncry.setColor1(Color.decode("#FF0000"));
        buttonEncry.setPreferredSize(new Dimension(110,40));

        ButtonDesign buttonDecry = new ButtonDesign();
        buttonDecry.setText("Decrypted");
        buttonDecry.setFont(new Font(buttonDecry.getName(), Font.BOLD, 16));
        buttonDecry.setColor1(Color.decode("#00AF17"));
        buttonDecry.setPreferredSize(new Dimension(110,40));

        panelButton.add(buttonDecry);
        panelButton.add(buttonEncry);

        this.add(nameCipher, BorderLayout.NORTH);
        this.add(panelBody, BorderLayout.CENTER);
        this.add(panelButton, BorderLayout.SOUTH);


        //event button
        buttonOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    textFieldFile.setText(select.getAbsolutePath());
                }
            }
        });

        buttonCreateKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arrKey = new int[2];
                if (listCombobox.getSelectedItem().equals("English alphabet")){
                    arrKey = new AffineCipher().generateKey("UK");
                } else if (listCombobox.getSelectedItem().equals("Vietnamese alphabet")) {
                    arrKey = new AffineCipher().generateKey("VN");
                }
                textKeya.setText(String.valueOf(arrKey[0]));
                textKeyb.setText(String.valueOf(arrKey[1]));

            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    String filePath = select.getAbsolutePath();
                    String data = textArea.getText();

                    SaveData save = new SaveData();
                    save.saveData(filePath, data);

                }
            }
        });

        buttonCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                if(text != null){
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection data = new StringSelection(text);
                    clipboard.setContents(data, null);
                }
            }
        });

        // đưa du lieu tu vung ban ma len vung ban ro de giai ma hoac ma hoa tiep tuc
        buttonUpgrade_Encr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea_Encry.getText());
                textArea_Encry.setText("");
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        buttonCopy_Encr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea_Encry.getText();
                if(text != null){
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection data = new StringSelection(text);
                    clipboard.setContents(data, null);
                }
            }
        });

        buttonPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = clipboard.getContents(null);

                if(transferable != null  && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)){
                    try {
                        String text = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                        textArea.append(text);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (UnsupportedFlavorException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        //Lưu dữ liệu đã mã hóa
        buttonSave_Encr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    String filePath = select.getAbsolutePath();
                    String data = textArea_Encry.getText();

                    SaveData save = new SaveData();
                    save.saveData(filePath, data);

                }
            }
        });


        //envent
        buttonEncry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textArea.getText();
                int a = Integer.parseInt(textKeya.getText());
                int b = Integer.parseInt(textKeyb.getText());
                AffineCipher cipher = new AffineCipher();
                String output = cipher.encryption(input,a,b);
                textArea_Encry.setText(output);
            }
        });

        buttonDecry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textArea.getText();
                int a = Integer.parseInt(textKeya.getText());
                int b = Integer.parseInt(textKeyb.getText());
                AffineCipher cipher = new AffineCipher();
                String output = cipher.decryption(input,a,b);
                textArea_Encry.setText(output);
            }
        });
    }
}
