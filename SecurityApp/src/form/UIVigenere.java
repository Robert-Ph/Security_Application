package form;

import View.Main;
import alorithms.VigenereCipher;
import model.ButtonDesign;
import model.ReadFile;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UIVigenere extends JPanel {

    private Main main;
    private final String[] listPlaintext = {"English alphabet", "Vietnamese alphabet"};
    public UIVigenere(Main main){
        this.main = main;
        init();
    }

    public void init() {
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());

        JLabel nameCipher = new JLabel("Vigenere");
        Font font = new Font(nameCipher.getFont().getName(), Font.BOLD, 24);
        nameCipher.setFont(font);
        nameCipher.setHorizontalAlignment(SwingConstants.CENTER);
        nameCipher.setVerticalAlignment(SwingConstants.CENTER);
        nameCipher.setForeground(Color.red);
//        nameCipher.setIcon(new ImageIcon("ImageIcon\\help.png"));
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
        textFieldFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        textFieldFile.setPreferredSize(new Dimension(15, 26));

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
        panelKeyandPlaintext.setLayout(new GridLayout(2, 1, 5, 5));

        //panel giao diện phần tao key
        JPanel panelKey = new JPanel();
        panelKey.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelKey.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Khóa k", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JLabel lableKey = new JLabel("Nhập khóa: ");
        TextField textKey = new TextField(30);
        //nut Create Key
        ButtonDesign buttonCreateKey = new ButtonDesign();
        buttonCreateKey.setText("Tạo khóa");

        //save key
        ButtonDesign buttonCopyKey = new ButtonDesign();
        buttonCopyKey.setText("Sao chép");

        //paste key
        ButtonDesign buttonPasteKey = new ButtonDesign();
        buttonPasteKey.setText("Paste");

        //open key
        ButtonDesign buttonOpenKey = new ButtonDesign();
        buttonOpenKey.setText("Chọn");

        panelKey.add(lableKey);
        panelKey.add(textKey);
        panelKey.add(buttonCreateKey);
        panelKey.add(buttonCopyKey);
        panelKey.add(buttonPasteKey);
        panelKey.add(buttonOpenKey);

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

        //event button
        buttonOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int returnFile = fileChooser.showOpenDialog(null);

                if (returnFile == JFileChooser.APPROVE_OPTION) {
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

                if (result == JFileChooser.APPROVE_OPTION) {
                    File select = fileChooser.getSelectedFile();
                    String filePath = select.getAbsolutePath();
                    String data = textArea.getText();

                    SaveData save = new SaveData();
                    save.saveData(filePath, data);

                }
            }
        });

        buttonCopyKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textKey.getText();
                if(text != null){
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection data = new StringSelection(text);
                    clipboard.setContents(data, null);
                }
            }
        });
        buttonSave_Encr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(700, 400));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File select = fileChooser.getSelectedFile();
                    String filePath = select.getAbsolutePath();
                    String data = textArea_Encry.getText();

                    SaveData save = new SaveData();
                    save.saveData(filePath, data);

                }
            }
        });

        buttonCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                if (text != null) {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection data = new StringSelection(text);
                    clipboard.setContents(data, null);
                }
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
                if (text != null) {
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

                if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
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

        buttonPasteKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = clipboard.getContents(null);

                if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        String text = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                        textKey.setText(text);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (UnsupportedFlavorException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

        //Tạo key
        buttonCreateKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = new VigenereCipher().generateRandomString(12);
                textKey.setText(key);
            }
        });
        //đọc file chứa khóa và kiểm tra
        buttonOpenKey.addActionListener(new ActionListener() {
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
                        textKey.setText(text);

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

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


        /*
        xữ lý ma hóa và giải mã

         */

        buttonEncry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VigenereCipher vigenereCipher = new VigenereCipher();

                if (checkBoxText.isSelected()){

                    String text = textArea.getText();
                    if (textKey.getText().isEmpty() && text.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Not data and key","Error",JOptionPane.CANCEL_OPTION);
                    } else if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Not data","Error",JOptionPane.CANCEL_OPTION);
                    } else if (textKey.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Not key","Error",JOptionPane.CANCEL_OPTION);
                    } else {
                        String output = "";
                        if (listCombobox.getSelectedItem().equals("English alphabet")){
                            output = vigenereCipher.encryption(text, textKey.getText());
                        } else if (listCombobox.getSelectedItem().equals("Vietnamese alphabet")) {
                            output = vigenereCipher.encryption_vie(text, textKey.getText());
                        }

                        textArea_Encry.setText(output);
                    }
                }else if (checkBoxFile.isSelected() ){
                    String input = textFieldFile.getText();

                    if (textKey.getText().isEmpty() && input.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Không tồn tại dữ liệu và khóa","Error",JOptionPane.CANCEL_OPTION);
                    }else if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có dữ liệu để mã hóa hoặc giải mã","Error",JOptionPane.CANCEL_OPTION);
                    } else if (textKey.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có khóa","Error",JOptionPane.CANCEL_OPTION);
                    }else {
                        File file = new File(input);
                        String extention = new ReadFile().getFileExtension(file);
                        LocalDate now = LocalDate.now();
                        LocalTime currentTime = LocalTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Định dạng giờ
                        String formattedTime = currentTime.format(formatter); // Lấy chuỗi biểu diễn cho giờ hiện tại
                        String timeWithoutColon = formattedTime.replace(":", "_"); // Bỏ dấu ":" từ chuỗi giờ
                        String time = String.valueOf(now);
                        String des =   "Vigenere_Encry_" + time +"_"+timeWithoutColon + "."+extention;
                        if (file.exists()) {

                            System.out.println(des);
                            String text = "";
                            ReadFile readFile = new ReadFile();
                            try{
                                if (listCombobox.getSelectedItem().equals("English alphabet")){
                                    vigenereCipher.encryFile(input,main.getPathToSaveFile() +"\\" + des, textKey.getText(),"UK");
                                } else if (listCombobox.getSelectedItem().equals("Vietnamese alphabet")) {
                                    vigenereCipher.encryFile(input,main.getPathToSaveFile() +"\\" + des, textKey.getText(),"VN");
                                }
                                text = readFile.readFiletoString(main.getPathToSaveFile() +"\\" + des);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(null, "Không thể mã hóa","Lỗi",JOptionPane.CANCEL_OPTION);
                                Path path = Paths.get(des);
                                try {
                                    Files.delete(path);
                                } catch (IOException exc) {
                                    throw new RuntimeException(exc);
                                }
                            }

                            textArea_Encry.setText(text);
                        }
                        JOptionPane.showMessageDialog(null,"<html>Mã hóa thành công!<br>Tên file: <html>"+des+"<html><br> Địa chỉ lưu file: <html>"+ main.getPathToSaveFile() ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        });

        buttonDecry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VigenereCipher vigenereCipher = new VigenereCipher();

                if (checkBoxText.isSelected()){

                    String text = textArea.getText();
                    if (textKey.getText().isEmpty() && text.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Not data and key","Error",JOptionPane.CANCEL_OPTION);
                    } else if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Not data","Error",JOptionPane.CANCEL_OPTION);
                    } else if (textKey.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Not key","Error",JOptionPane.CANCEL_OPTION);
                    } else {
                        String output = "";
                        if (listCombobox.getSelectedItem().equals("English alphabet")){
                            output = vigenereCipher.decryption(text, textKey.getText());
                        } else if (listCombobox.getSelectedItem().equals("Vietnamese alphabet")) {
                            output = vigenereCipher.decryption_vie(text, textKey.getText());
                        }

                        textArea_Encry.setText(output);
                    }
                }else if (checkBoxFile.isSelected() ){
                    String input = textFieldFile.getText();

                    if (textKey.getText().isEmpty() && input.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Không tồn tại dữ liệu và khóa","Error",JOptionPane.CANCEL_OPTION);
                    }else if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có dữ liệu để mã hóa hoặc giải mã","Error",JOptionPane.CANCEL_OPTION);
                    } else if (textKey.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có khóa","Error",JOptionPane.CANCEL_OPTION);
                    }else {
                        File file = new File(input);
                        String extention = new ReadFile().getFileExtension(file);
                        LocalDate now = LocalDate.now();
                        LocalTime currentTime = LocalTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Định dạng giờ
                        String formattedTime = currentTime.format(formatter); // Lấy chuỗi biểu diễn cho giờ hiện tại
                        String timeWithoutColon = formattedTime.replace(":", "_"); // Bỏ dấu ":" từ chuỗi giờ
                        String time = String.valueOf(now);
                        String des =   "Vigenere_Decry_" + time +"_"+timeWithoutColon + "."+extention;
                        if (file.exists()) {

                            System.out.println(des);
                            String text = "";
                            ReadFile readFile = new ReadFile();
                            try{
                                if (listCombobox.getSelectedItem().equals("English alphabet")){
                                    vigenereCipher.decryFile(input,main.getPathToSaveFile() +"\\" + des, textKey.getText(),"UK");
                                } else if (listCombobox.getSelectedItem().equals("Vietnamese alphabet")) {
                                    vigenereCipher.decryFile(input,main.getPathToSaveFile() +"\\" + des, textKey.getText(),"VN");
                                }
                                text = readFile.readFiletoString(main.getPathToSaveFile() +"\\" + des);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(null, "Không thể mã hóa","Lỗi",JOptionPane.CANCEL_OPTION);
                                Path path = Paths.get(des);
                                try {
                                    Files.delete(path);
                                } catch (IOException exc) {
                                    throw new RuntimeException(exc);
                                }
                            }

                            textArea_Encry.setText(text);
                        }
                        JOptionPane.showMessageDialog(null,"<html>Mã hóa thành công!<br>Tên file: <html>"+des+"<html><br> Địa chỉ lưu file: <html>"+ main.getPathToSaveFile() ,"Thông báo",JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        });

    }
}
