/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.simple.JSONObject;

/**
 *
 * @author MATIUS
 */
public class ConvertCommand implements Command{
    String fileDirectory,outputDirectory,outputType;

    ConvertCommand(String fileDirectory){
        this.fileDirectory=fileDirectory;
        this.outputType=Command.OUTPUT_TYPE_TEXT;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    
    @Override
    public void action() {
        if(outputType.equals(Command.OUTPUT_TYPE_JSON)){
            convertToJSON();
        }else if(outputType.equals(Command.OUTPUT_TYPE_TEXT)){
            convertToPlainText();
        }
    }
    
    private void convertToJSON(){
        try{
            if(!this.getFileExtension().toLowerCase().equals("log")){
                throw new Exception("File yang dipilih bukan file Log");
            }
            File myFile = new File(fileDirectory);
            FileWriter writer=null;
            BufferedWriter output=null;
            if(outputDirectory==null){
                outputDirectory=this.generateOutputDirectory("json");
            }
            
            writer=new FileWriter(outputDirectory);
            output=new BufferedWriter(writer);
            
            Scanner myReader = new Scanner(myFile);
            JSONObject obj=new JSONObject();
            int index=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                obj.put(index,data);
                index++;
            }
            if(output!=null){
                output.write(obj.toJSONString());
                output.flush();
                output.close();
            }
            myReader.close();
            System.out.println("File berhasil dikonversi menjadi file "+outputDirectory);
        }catch(Exception ex){
            System.out.println("Error : "+ex.toString());
        }
    }
    private void convertToPlainText(){
        try{
            if(!this.getFileExtension().toLowerCase().equals("log")){
                throw new Exception("File yang dipilih bukan file Log");
            }
            File myFile = new File(fileDirectory);
            FileWriter writer=null;
            BufferedWriter output=null;
           if(outputDirectory==null){
                outputDirectory=this.generateOutputDirectory("txt");
            }
            
            writer=new FileWriter(outputDirectory);
            output=new BufferedWriter(writer);
            
            Scanner myReader = new Scanner(myFile);
//            StringBuilder buildString=new StringBuilder();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(output!=null){
                    output.write(data);
                    output.newLine();
                }
            }
            
            if(output!=null){
                output.flush();
                output.close();
            }
            myReader.close();
            System.out.println("File berhasil dikonversi menjadi file "+outputDirectory);
        }catch(Exception ex){
            System.out.println("Error : "+ex.toString());
        }
    }
    
    private String getFileExtension(){
        String result="";
        String[]arrFileDir=fileDirectory.trim().split("\\.");
        result=arrFileDir[arrFileDir.length-1];
        return result;
    }
    
    private String generateOutputDirectory(String fileExtension){
        return fileDirectory.substring(0, fileDirectory.lastIndexOf(".")).trim()+"."+fileExtension;
    }
    
    
}
