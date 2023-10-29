package model;

import java.io.FileWriter;

public class SaveData {

    public void saveData(String filePath, String data){
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(data);
            file.flush();
            file.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
