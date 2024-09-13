/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.function;

import com.asac.function.MessageParameterFilter;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author herrysuganda
 */
public class MessageContent {
//    private static Logger log = Logger.getLogger(MessageContent.class);

    public static String createMessageIDFromISOMessage(ISOMsg msg) throws ISOException {
        if (msg.getMTI().equals("0200") || msg.getMTI().equals("0210")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + MessageParameterFilter.createBit37Value(msg.getString(37).toString().trim()) + "0200";
        } else if (msg.getMTI().equals("0400") || msg.getMTI().equals("0410")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + MessageParameterFilter.createBit37Value(msg.getString(37).toString().trim()) + "0400";
        } else if (msg.getMTI().equals("0220") || msg.getMTI().equals("0230")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + MessageParameterFilter.createBit37Value(msg.getString(37).toString().trim()) + "0220";
        } else if (msg.getMTI().equals("0800") || msg.getMTI().equals("0810")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(70) + "0800";
        }
        return null;
    }

    public static String setMTItildeResponse(String mti) {
        if (mti.equals("TRX_")) {
            return "AUTX";
        } else if (mti.equals("TRV_")) {
            return "AUTV";
        } else if (mti.equals("TRA_")) {
            return "AUTA";
        }
        return null;
    }

    public static String setMTIISOResponse(String mti) {
        if (mti.equals("0200")) {
            return "0210";
        } else if (mti.equals("0400")) {
            return "0410";
        } else if (mti.equals("0220")) {
            return "0230";
        }
        return null;
    }
}
