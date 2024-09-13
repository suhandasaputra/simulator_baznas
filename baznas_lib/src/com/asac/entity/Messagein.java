/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asac.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author herrysuganda
 */public class Messagein implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private Date waktu;
    private Boolean status;
    private String typemsg;
    private String fromsocket;
    private Long msginid;
    private String msgid;
    private ISOMsg isomsg;
    private ISOMsg isomsgSource;
    private HashMap tlvdata;
    private String classpath;
    private String tcbiller;
    private String sendto;
    private String proccode;
    private String trancode;
    private String trxidbackend;
    private String respcode;
    private String msgtrxid;
    private String packagetName;

    public Messagein() {
    }

    
    public Messagein(Long msginid) {
        this.msginid = msginid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTypemsg() {
        return typemsg;
    }

    public void setTypemsg(String typemsg) {
        this.typemsg = typemsg;
    }

    public String getFromsocket() {
        return fromsocket;
    }

    public void setFromsocket(String fromsocket) {
        this.fromsocket = fromsocket;
    }

    public Long getMsginid() {
        return msginid;
    }

    public void setMsginid(Long msginid) {
        this.msginid = msginid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msginid != null ? msginid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagein)) {
            return false;
        }
        Messagein other = (Messagein) object;
        if ((this.msginid == null && other.msginid != null) || (this.msginid != null && !this.msginid.equals(other.msginid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muamalat.entity.Messagein[msginid=" + msginid + "]";
    }

    /**
     * @return the msgid
     */
    public String getMsgid() {
        return msgid;
    }

    /**
     * @param msgid the msgid to set
     */
    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    /**
     * @return the isomsg
     */
    public ISOMsg getIsomsg() {
        return isomsg;
    }

    /**
     * @param isomsg the isomsg to set
     */
    public void setIsomsg(ISOMsg isomsg) {
        this.isomsg = isomsg;
    }

    /**
     * @return the tlvdata
     */
    public HashMap getTlvdata() {
        return tlvdata;
    }

    /**
     * @param tlvdata the tlvdata to set
     */
    public void setTlvdata(HashMap tlvdata) {
        this.tlvdata = tlvdata;
    }

    /**
     * @return the classpath
     */
    public String getClasspath() {
        return classpath;
    }

    /**
     * @param classpath the classpath to set
     */
    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    /**
     * @return the tcbiller
     */
    public String getTcbiller() {
        return tcbiller;
    }

    /**
     * @param tcbiller the tcbiller to set
     */
    public void setTcbiller(String tcbiller) {
        this.tcbiller = tcbiller;
    }

    /**
     * @return the sendto
     */
    public String getSendto() {
        return sendto;
    }

    /**
     * @param sendto the sendto to set
     */
    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    /**
     * @return the proccode
     */
    public String getProccode() {
        return proccode;
    }

    /**
     * @param proccode the proccode to set
     */
    public void setProccode(String proccode) {
        this.proccode = proccode;
    }

    /**
     * @return the trancode
     */
    public String getTrancode() {
        return trancode;
    }

    /**
     * @param trancode the trancode to set
     */
    public void setTrancode(String trancode) {
        this.trancode = trancode;
    }

    /**
     * @return the isomsgSource
     */
    public ISOMsg getIsomsgSource() {
        return isomsgSource;
    }

    /**
     * @param isomsgSource the isomsgSource to set
     */
    public void setIsomsgSource(ISOMsg isomsgSource) {
        this.isomsgSource = isomsgSource;
    }

    /**
     * @return the trxidbackend
     */
    public String getTrxidbackend() {
        return trxidbackend;
    }

    /**
     * @param trxidbackend the trxidbackend to set
     */
    public void setTrxidbackend(String trxidbackend) {
        this.trxidbackend = trxidbackend;
    }

    /**
     * @return the respcode
     */
    public String getRespcode() {
        return respcode;
    }

    /**
     * @param respcode the respcode to set
     */
    public void setRespcode(String respcode) {
        this.respcode = respcode;
    }

    /**
     * @return the msgtrxid
     */
    public String getMsgtrxid() {
        return msgtrxid;
    }

    /**
     * @param msgtrxid the msgtrxid to set
     */
    public void setMsgtrxid(String msgtrxid) {
        this.msgtrxid = msgtrxid;
    }

    /**
     * @return the packagetName
     */
    public String getPackagetName() {
        return packagetName;
    }

    /**
     * @param packagetName the packagetName to set
     */
    public void setPackagetName(String packagetName) {
        this.packagetName = packagetName;
    }

}
