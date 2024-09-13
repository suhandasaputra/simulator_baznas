/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.function;

/**
 *
 * @author herrysuganda
 */
public class MessageParameterFilter {
//    private static Logger log = Logger.getLogger(MessageParameterFilter.class);


    
    public static String createBit37Value(String oldRefID) {
        oldRefID = oldRefID.trim();
        if (oldRefID.length()>12){
            return oldRefID.substring(
                oldRefID.toString().length() - 12,
                oldRefID.toString().length());
        }else{
            return StringFunction.pad(oldRefID, 12, "0", "Right");

        }
    }
}
