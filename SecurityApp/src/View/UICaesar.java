package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICaesar extends JPanel {

    private String listPlaintext[] = {"English alphabet", "Vietnamese alphabet"};
    public UICaesar(){
        init();
    }

    public void init(){
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());
//        this.setBackground(Color.blue);

        JLabel nameCipher = new JLabel("Caesar");
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
        JTextArea textArea = new JTextArea(14,60);
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
        buttonOpenFile.setText("Open file");
//        JButton buttonOpenFile = new JButton("Open File");
//        buttonOpenFile.setForeground(Color.white);
//        buttonOpenFile.setBackground(new Color(36,78,245));

        //nut Save
        ButtonDesign buttonSave = new ButtonDesign();
        buttonSave.setText("Save");
//        JButton buttonOpenSave = new JButton("Save");
//        buttonOpenSave.setForeground(Color.white);
//        buttonOpenSave.setBackground(new Color(36,78,245));

        //nut Copy
        ButtonDesign buttonCopy = new ButtonDesign();
        buttonCopy.setText("Copy");

//        JButton buttonOpenCopy = new JButton("Copy");
//        buttonOpenCopy.setForeground(Color.white);
//        buttonOpenCopy.setBackground(new Color(36,78,245));

        //nut Paste

        ButtonDesign buttonPaste = new ButtonDesign();
        buttonPaste.setText("Paste");

//        JButton buttonOpenPaste = new JButton("Paste");
//        buttonOpenPaste.setForeground(Color.white);
//        buttonOpenPaste.setBackground(new Color(36,78,245));
        buttonPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click paste");
            }
        });


        panelButtonEncry.add(labelPathFile);
        panelButtonEncry.add(textFieldFile);
        panelButtonEncry.add(buttonOpenFile);
        panelButtonEncry.add(buttonSave);
        panelButtonEncry.add(buttonCopy);
        panelButtonEncry.add(buttonPaste);

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
        panelKey.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JLabel lableKey = new JLabel("Input Key: ");
        JTextField textKey = new JTextField(15);
        //nut Create Key
        ButtonDesign buttonCreateKey = new ButtonDesign();
        buttonCreateKey.setText("Create Key");
//        JButton buttonCreateKey = new JButton("Create Key");
//        buttonCreateKey.setForeground(Color.white);
//        buttonCreateKey.setBackground(new Color(36,78,245));


        panelKey.add(lableKey);
        panelKey.add(textKey);
        panelKey.add(buttonCreateKey);

        //panel giao dien plantext
        JPanel panelPlaintext = new JPanel();
        panelPlaintext.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelPlaintext.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "PlainText & CipherText", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        JComboBox listCombobox = new JComboBox(listPlaintext);
        listCombobox.setBackground(Color.white);
        JLabel labelplainCipher = new JLabel("P & C: ");
        listCombobox.setPreferredSize(new Dimension(220, 20));
        JLabel labelType= new JLabel("Type: ");
        JCheckBox checkBoxText = new JCheckBox("Text");
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


        checkBoxText.addActionListener(actionListener);
        checkBoxFile.addActionListener(actionListener);

        panelPlaintext.add(labelplainCipher);
        panelPlaintext.add(listCombobox);
        panelPlaintext.add(labelType);
        panelPlaintext.add(checkBoxText);
        panelPlaintext.add(checkBoxFile);

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
        JTextArea textArea_Encry = new JTextArea(14,60);
        paneltext_Encry.setBorder(new TitledBorder(new LineBorder(new Color(0x808080), 1), "Bản mã", TitledBorder.LEADING, TitledBorder.TOP, null, null));


        //panel button
        JPanel panelButtonDecry = new JPanel();
        panelButtonDecry.setPreferredSize(new Dimension(670, 30));
        panelButtonDecry.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //nut Save
        ButtonDesign buttonSave_Encr = new ButtonDesign();
        buttonSave_Encr.setText("Save");
//        JButton buttonOpenSave_Encr = new JButton("Save");
//        buttonOpenSave_Encr.setForeground(Color.white);
//        buttonOpenSave_Encr.setBackground(new Color(36,78,245));

        //nut Copy
        ButtonDesign buttonCopy_Encr = new ButtonDesign();
        buttonCopy_Encr.setText("Copy");
//        JButton buttonOpenCopy_Encry = new JButton("Copy");
//        buttonOpenCopy_Encry.setForeground(Color.white);
//        buttonOpenCopy_Encry.setBackground(new Color(36,78,245));

        //nut Upgrade
        ButtonDesign buttonUpgrade_Encr = new ButtonDesign();
        buttonUpgrade_Encr.setText("Upgrade");
//        JButton buttonOpenUpgrade_Encry = new JButton("Upgrade");
//        buttonOpenUpgrade_Encry.setForeground(Color.white);
//        buttonOpenUpgrade_Encry.setBackground(new Color(36,78,245));

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
        buttonEncry.setText("Decrypted");
        buttonEncry.setFont(new Font(buttonEncry.getName(), Font.BOLD, 16));
        buttonEncry.setColor1(Color.decode("#FF0000"));
        buttonEncry.setPreferredSize(new Dimension(110,40));

//        Button butEncry = new Button("Encrypted");
//        butEncry.setBackground(Color.red);
//        butEncry.setFont(new Font(butEncry.getName(), Font.BOLD, 18));
//        butEncry.setForeground(Color.WHITE);
//        butEncry.setPreferredSize(new Dimension(110,40));


        ButtonDesign buttonDecry = new ButtonDesign();
        buttonDecry.setText("Decrypted");
        buttonDecry.setFont(new Font(buttonDecry.getName(), Font.BOLD, 16));
        buttonDecry.setColor1(Color.decode("#00AF17"));
        buttonDecry.setPreferredSize(new Dimension(110,40));



//        Button butDecry = new Button("Decrypted");
//        butDecry.setBackground(new Color(0, 175, 23));
//        butDecry.setFont(new Font(butDecry.getName(), Font.BOLD, 18));
//        butDecry.setForeground(Color.WHITE);
//        butDecry.setPreferredSize(new Dimension(110,40));

        panelButton.add(buttonDecry);
        panelButton.add(buttonEncry);

        this.add(nameCipher, BorderLayout.NORTH);
        this.add(panelBody, BorderLayout.CENTER);
        this.add(panelButton, BorderLayout.SOUTH);
    }
}
