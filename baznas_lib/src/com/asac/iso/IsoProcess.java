/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asac.iso;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author herrysuganda
 */
public class IsoProcess {

    public static ISOMsg unpackRequest(String message) throws ISOException, Exception {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(PackagerFactory.getPackager());
        isoMsg.unpack(message.getBytes());
        return isoMsg;
    }
}
