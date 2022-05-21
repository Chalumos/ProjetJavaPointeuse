package fr.univtours.polytech.projet_tutore.model.timetracker;

import java.io.*;

public class TimeTracker {
    public void serialisationEmployee(){
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        File root = new File("");
        String packages = File.separator+"src"+
                File.separator+"main"+
                File.separator+"resources"+
                File.separator+"fr"+
                File.separator+"univtours"+
                File.separator+"polytech"+
                File.separator+"timetracker"+File.separator;
        String filePath = root.getAbsolutePath() + packages + "Employee.txt";
        System.out.println(filePath);

        try{
            FileOutputStream fileEmployee = new FileOutputStream(filePath);
            BufferedOutputStream bufferEmployee = new BufferedOutputStream(fileEmployee);
            DataOutputStream dataEmployee = new DataOutputStream(bufferEmployee);

            //TODO trouver un moyen de sérialiser un Objet de la classe Employee!
            // pour cela il faut que Employee implement Externalizable
            //dataEmployee.writeUTF("");
            dataEmployee.close();
        } catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
