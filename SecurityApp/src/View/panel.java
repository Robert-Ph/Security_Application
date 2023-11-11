package View;

import model.ButtonDesign;
import model.SaveData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class panel extends JPanel {
    private JPanel panel_1, panel_2;



    public panel(Main main) {

        innit(main);
    }

    public void innit(Main main){
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());

        panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(3,1));
        panel_1.setBackground(Color.white);

        JLabel text = new JLabel("Data Security Application");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        text.setForeground(Color.red);
        text.setBorder(new EmptyBorder(20,20,20,20));
        text.setFont(new Font(text.getName(), Font.BOLD, 48));

        JLabel about = new JLabel("<html> Version: 1.0.0 (Beta) <br> Release date: 8/11/2023");
        about.setFont(new Font(text.getName(), Font.ITALIC, 24));
        about.setHorizontalAlignment(SwingConstants.CENTER);
        about.setVerticalAlignment(SwingConstants.CENTER);

        panel_1.add(text);
        panel_1.add(about);


        panel_2 = new JPanel();
        panel_2.setPreferredSize(new Dimension(1000, 200));
        panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0x808080),1), "Setting", TitledBorder.LEADING,TitledBorder.TOP, null, null));
//        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

        JLabel  titleSeting = new JLabel("Địa chỉ lưu dữ liệu:");
        JTextField input = new JTextField(24);
        input.setText(main.getPathToSaveFile());
        ButtonDesign buttonOpen = new ButtonDesign();
        buttonOpen.setText("chọn");

        panel_2.add(titleSeting);
        panel_2.add(input);
        panel_2.add(buttonOpen);

        this.add(panel_1, BorderLayout.CENTER);
        this.add(panel_2, BorderLayout.SOUTH);

        //event
        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                folderChooser.setPreferredSize(new Dimension(700, 400));
                int result = folderChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    File select = folderChooser.getSelectedFile();
                    String filePath = select.getAbsolutePath();
                    input.setText(filePath);
                    main.setPathToSaveFile(filePath);
                }


//                String filePath = select.getAbsolutePath();


//                if (result == JFileChooser.APPROVE_OPTION){
//                    File select = fileChooser.getSelectedFile();
//                    String filePath = select.getAbsolutePath();
//                    String data = textArea.getText();
//
//                    SaveData save = new SaveData();
//                    save.saveData(filePath, data);
//
//                }
            }
        });

    }
}
