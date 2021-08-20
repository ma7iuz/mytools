/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytools;

/**
 *
 * @author MATIUS
 */
public interface Command {
    public static final String COMMAND_HELP="-h";
    public static final String COMMAND_TYPE="-t";
    public static final String COMMAND_OUTPUT="-o";
    public static final String OUTPUT_TYPE_JSON="json";
    public static final String OUTPUT_TYPE_TEXT="text";
    public abstract void action();
   
}


