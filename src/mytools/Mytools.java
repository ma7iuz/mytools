/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytools;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author MATIUS
 */
public class Mytools {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
             
             showHelp();
             while(true){
                 System.out.print("mytools ");
                 BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
                 String strparam = reader.readLine();
                 String[]parameter=strparam.split(" ");
                //             String[]parameter=args;
                List<String>lstParameter=Arrays.asList(parameter);


                Command cmd=null;


                if(lstParameter.get(0).equals(Command.COMMAND_HELP)){
                    cmd=new HelpCommand();
                }
                else{
                    String fileDirectory="";
                    for(String item:parameter){
                        if(item.equals(Command.COMMAND_TYPE)||item.equals(Command.COMMAND_OUTPUT)){
                            break;
                        }
                        fileDirectory+=item+" ";
                    }
                    cmd=new ConvertCommand(fileDirectory.trim());
                }
                if(lstParameter.contains(Command.COMMAND_TYPE) && cmd instanceof ConvertCommand){
                    String outputType=lstParameter.get(lstParameter.indexOf(Command.COMMAND_TYPE)+1);
                    ((ConvertCommand) cmd).setOutputType(outputType);
                }

                if(lstParameter.contains(Command.COMMAND_OUTPUT) && cmd instanceof ConvertCommand){
                    String outputDirectory="";
                    for(int i=lstParameter.indexOf(Command.COMMAND_OUTPUT)+1;i<lstParameter.size();i++){
                        outputDirectory+=lstParameter.get(i)+" ";
                    }
                    ((ConvertCommand) cmd).setOutputDirectory(outputDirectory.trim());
                }

                cmd.action();
             }
            
            
        }catch(Exception ex){
            System.out.println("Error : "+ex.toString());
        }
    }
    
    private static void showHelp(){
        HelpCommand cmdHelp=new HelpCommand();
        cmdHelp.action();
    }
    
}
