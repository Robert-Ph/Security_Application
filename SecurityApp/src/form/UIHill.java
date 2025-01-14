package form;

import View.Main;
import alorithms.HillCipher;
import model.ReadFile;
import model.ButtonDesign;
import model.SaveData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UIHill extends JPanel {
    private HillCipher hillCipher;
    private  int[][] arrKey;
    private Main main;
    private static String text;
    private final String[] listPlaintext = {"English alphabet"};
    private final String[] sizeKey = {"2", "3","4"};

    public UIHill(Main main) {
        this.main = main;
        init();
    }

    public void init() {
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());

        JLabel nameCipher = new JLabel("HILL");
        Font font = new Font(nameCipher.getFont().getName(), Font.BOLD, 24);
        nameCipher.setFont(font);
        nameCipher.setHorizontalAlignment(SwingConstants.CENTER);
        nameCipher.setVerticalAlignment(SwingConstants.CENTER);
        nameCipher.setForeground(Color.red);

        nameCipher.setPreferredSize(new Dimension(1000, 30));

        JPanel panelBody = new JPanel();
        panelBody.setLayout(new GridLayout(2, 1));

        //panel Encry
        JPanel panelEncry = new JPanel();
        panelEncry.setLayout(new BorderLayout());

        //textFile input data - phan nhap dư lieu ma hoa
        JPanel panelText = new JPanel();
        TextArea textArea = new TextArea(13, 92);
        panelText.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản rõ", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        //panel button
        JPanel panelButtonEncry = new JPanel();
        panelButtonEncry.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelButtonEncry.setPreferredSize(new Dimension(670, 30));
        JLabel labelPathFile = new JLabel("File");

        JTextField textFieldFile = new JTextField(17);
//        textFieldFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        textFieldFile.setPreferredSize(new Dimension(15, 26));
//        textFieldFile.setEnabled(false);
        textFieldFile.setEditable(false);

        //nut Open File
        ButtonDesign buttonOpenFile = new ButtonDesign();
        buttonOpenFile.setText("Chọn file");
        buttonOpenFile.setEnabled(false);

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
        panelKeyandPlaintext.setLayout(new GridLayout(2, 1, 5, 5));

        //panel giao diện phần tao key
        JPanel panelKey = new JPanel();
        panelKey.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelKey.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Khóa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//        JLabel lableKey = new JLabel("Key: ");
        TextArea textKey = new TextArea(4,35);
        textKey.setEditable(false);

        JLabel size = new JLabel("Size: ");
        JComboBox listSizeKey = new JComboBox(sizeKey);
        listSizeKey.setBackground(Color.white);

        //nut Create Key
        ButtonDesign buttonCreateKey = new ButtonDesign();
        buttonCreateKey.setText("Tạo khóa");

        //nut Create Key
        ButtonDesign buttonOpenKey = new ButtonDesign();
        buttonOpenKey.setText("Chọn");

        //nut Create Key
        ButtonDesign buttonSaveKey = new ButtonDesign();
        buttonSaveKey.setText("Lưu");

//        panelKey.add(lableKey);
        panelKey.add(textKey);
        panelKey.add(size);
        panelKey.add(listSizeKey);
        panelKey.add(buttonCreateKey);
        panelKey.add(buttonOpenKey);
        panelKey.add(buttonSaveKey);

        //panel giao dien plantext
        JPanel panelPlaintext = new JPanel();
        panelPlaintext.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelPlaintext.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản rõ & Bản mã", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JComboBox listCombobox = new JComboBox(listPlaintext);
        listCombobox.setBackground(Color.white);
        JLabel labelplainCipher = new JLabel("P & C: ");
        listCombobox.setPreferredSize(new Dimension(220, 20));
        JLabel labelType = new JLabel("Kiểu: ");
        JCheckBox checkBoxText = new JCheckBox("Văn bản");
        checkBoxText.setSelected(true);
//        JCheckBox checkBoxFile = new JCheckBox("File");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox selectedCheckBox = (JCheckBox) e.getSource();

                // Đặt tất cả các JCheckBox về trạng thái không chọn
                checkBoxText.setSelected(false);
//                checkBoxFile.setSelected(false);

                // Đặt trạng thái của JCheckBox được chọn
                selectedCheckBox.setSelected(true);
            }
        };

        JLabel textAttention = new JLabel("<html>Lưu ý: Hiện tại thuật toán chỉ sử dụng được đối <br>với văn bản ngôn ngữ tiếng anh!<html>");
        textAttention.setForeground(Color.red);

        checkBoxText.addActionListener(actionListener);
//        checkBoxFile.addActionListener(actionListener);

        panelPlaintext.add(labelplainCipher);
        panelPlaintext.add(listCombobox);
        panelPlaintext.add(labelType);
        panelPlaintext.add(checkBoxText);
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
        TextArea textArea_Encry = new TextArea(13, 92);
        textArea_Encry.setEditable(false);
        paneltext_Encry.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản mã", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        //panel button
        JPanel panelButtonDecry = new JPanel();
        panelButtonDecry.setPreferredSize(new Dimension(670, 30));
        panelButtonDecry.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //nut Save
        ButtonDesign buttonSave_Encr = new ButtonDesign();
        buttonSave_Encr.setText("Lưu");

        //nut Copy
        ButtonDesign buttonCopy_Encr = new ButtonDesign();
        buttonCopy_Encr.setText("Sao chép");

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
        buttonEncry.setText("Mã hóa");
        buttonEncry.setFont(new Font(buttonEncry.getName(), Font.BOLD, 16));
        buttonEncry.setColor1(Color.decode("#FF0000"));
        buttonEncry.setPreferredSize(new Dimension(110, 40));

        ButtonDesign buttonDecry = new ButtonDesign();
        buttonDecry.setText("Giải mã");
        buttonDecry.setFont(new Font(buttonDecry.getName(), Font.BOLD, 16));
        buttonDecry.setColor1(Color.decode("#00AF17"));
        buttonDecry.setPreferredSize(new Dimension(110, 40));

        panelButton.add(buttonDecry);
        panelButton.add(buttonEncry);

        this.add(nameCipher, BorderLayout.NORTH);
        this.add(panelBody, BorderLayout.CENTER);
        this.add(panelButton, BorderLayout.SOUTH);



        /*
        xử lý sự kiện
         */

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


        buttonCreateKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hillCipher = new HillCipher();
                arrKey = hillCipher.createKey(Integer.parseInt(String.valueOf(listSizeKey.getSelectedItem())));
                textKey.setText(hillCipher.toStringKeyArray(arrKey));
            }
        });

        buttonOpenKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if(returnFile == JFileChooser.APPROVE_OPTION){
                    File select = fileChooser.getSelectedFile();
                    hillCipher = new HillCipher();
                    int[][] arr = hillCipher.readMatrix(select.getAbsolutePath());
                    if (hillCipher.getMatrix().checkMatrix(arr)){
                        arrKey = arr;
                        textKey.setText(hillCipher.toStringKeyArray(arrKey));
                    }
                }
            }
        });

        buttonSaveKey.addActionListener(new ActionListener() {
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
                    ReadFile saveKey = new ReadFile();
                    saveKey.writeFileKeyHill(filePath, arrKey);

                }
            }
        });
        buttonUpgrade_Encr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea_Encry.getText());
                textArea_Encry.setText("");
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
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });




        //event
        buttonEncry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] key = arrKey;
                hillCipher = new HillCipher();
                String data =textArea.getText();

                if (checkBoxText.isSelected()){
                    String text = textArea.getText();
                    if (textKey.getText().isEmpty() && text.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Không tồn tại dữ liệu và khóa","Lỗi",JOptionPane.CANCEL_OPTION);
                    } else if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tồn tại dữ liệu","Lỗi",JOptionPane.CANCEL_OPTION);
                    } else if (textKey.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Khóa không tồn tại","Lỗi",JOptionPane.CANCEL_OPTION);
                    } else {
                        String out = hillCipher.hillCipherEncrypt(data, arrKey);
                        String text1=textArea.getText();
                        String encry = hillCipher.stringData(text1, out,0, true );
                        textArea_Encry.setText(encry);
                    }
                }
            }
        });

        buttonDecry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] key = arrKey;
                hillCipher = new HillCipher();
                String data =textArea.getText();

                if (checkBoxText.isSelected()){
                    String text = textArea.getText();
                    if (textKey.getText().isEmpty() && text.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Không tồn tại dữ liệu và khóa","Lỗi",JOptionPane.CANCEL_OPTION);
                    } else if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tồn tại dữ liệu","Lỗi",JOptionPane.CANCEL_OPTION);
                    } else if (textKey.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Khóa không tồn tại","Lỗi",JOptionPane.CANCEL_OPTION);
                    } else {
                        String out = hillCipher.hillCipherDecrypt(hillCipher.splitDataIsLetter(data).toUpperCase(), key);
                        int num = hillCipher.numsize(data, key);
                        String encry = hillCipher.stringData(data.substring(0,data.length()-arrKey.length), out, num, false );
                        textArea_Encry.setText(encry);
                    }
                }
            }
        });
    }
}
