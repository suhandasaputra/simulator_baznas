/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.database;

import com.asac.entity.Messagein;
import com.asac.entity.Messageout;
import com.asac.entity.SocketConnectionEntity;
import com.asac.entity.SocketDetail;
import com.asac.entity.Tempmsg;
import com.asac.iso.IsoProcess;
import com.asac.parameter.RuleNameParamater;
import com.asac.parameter.StaticParameter;
import com.asac.singleton.DatasourceEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import com.asac.function.ISOFunction;
/**
 *
 * @author herrysuganda
 */
public class DatabaseProcess {

    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public boolean saveMessageToMessageIn(String message, String typeMessage, String fromSocket, String trxmsgid, String packagename) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("INSERT INTO messagein(message, waktu, typemsg, fromsocket, trxmsgid, packagename) VALUES (?, ?, ?, ?, ?, ?)");
            stat.setString(1, message);
            stat.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            stat.setString(3, typeMessage);
            stat.setString(4, fromSocket);
            stat.setString(5, trxmsgid);
            stat.setString(6, packagename);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("saveMessageToMessageIn : " + ex.getMessage());
//            ex.printStackTrace();
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public List getMessageIncoming(String partner) {
//        log.info("getMessageIncoming : " + partner);
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();
        Messagein msgin;
        String listMsgId = "";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            log.info("conn : " + conn);
//            stat = conn.prepareStatement("SELECT message, waktu, typemsg, fromsocket, msginid FROM messagein where date(waktu) = current_date and status is false and typemsg =? order by waktu fetch first 500 rows only ");
            stat = conn.prepareStatement("SELECT message, waktu, typemsg, fromsocket, msginid, trxmsgid, packagename FROM messagein where waktu >= (now()- (INTERVAL '180 seconds')) and status is false and typemsg =? order by waktu fetch first 500 rows only ");
//            log.info("stat : " + stat);
            stat.setString(1, partner);
            rs = stat.executeQuery();
//            log.info("rs : " + rs);
            while (rs.next()) {
                msgin = new Messagein();
                msgin.setMessage(rs.getString("message"));
                msgin.setWaktu(rs.getDate("waktu"));
                msgin.setStatus(false);
                msgin.setTypemsg(rs.getString("typemsg"));
                msgin.setFromsocket(rs.getString("fromsocket"));
                msgin.setMsginid(rs.getLong("msginid"));
                msgin.setMsgid(rs.getString("trxmsgid"));
                System.out.println("awal msgid : " + msgin.getMsgid());
                msgin.setPackagetName(rs.getString("packagename"));
                l.add(msgin);
                listMsgId += "," + String.valueOf(rs.getLong("msginid"));
            }
            listMsgId = listMsgId.replaceFirst(",", "");
//            System.out.println("listMsgId : " + listMsgId);
            if (!listMsgId.equals("")) {
//            System.out.println("UPDATE messagein SET status=true WHERE msginid in (" + listMsgId + ")");
                clearStatment(stat);
                stat = conn.prepareStatement("UPDATE messagein SET status=true WHERE msginid in (" + listMsgId + ")");
                stat.executeUpdate();
                stat.clearBatch();
                stat.clearParameters();
            }
            listMsgId = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getMessageIncoming : " + ex.getMessage());

        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return l;
    }

    public boolean saveMessageToTempmsg(Tempmsg tempmsg, ISOMsg iso) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("INSERT INTO tempmsg(msgid, abdmsg, reqisobank, requesttime, noref, cardno, bankcode, "
                    + "fromaccount, toaccount, amount, proccode, transactionid, productcode, trxidbackend, fromsocket, bankcodefrom, custno, terminalid, mti, merchanttype) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stat.setString(1, tempmsg.getMsgid());
            stat.setString(2, tempmsg.getAbdmsg());
            stat.setString(3, tempmsg.getReqisobank());
            stat.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
            stat.setString(5, tempmsg.getNoref());
            stat.setString(6, tempmsg.getCardno());
            stat.setString(7, tempmsg.getBankcode());
            stat.setString(8, tempmsg.getFromaccount());
            stat.setString(9, tempmsg.getToaccount());
            stat.setString(10, tempmsg.getAmount());
            stat.setString(11, tempmsg.getProccode());
            stat.setString(12, tempmsg.getTransactionid());
            stat.setString(13, tempmsg.getProductcode());
            stat.setString(14, tempmsg.getTrxidbackend());
            stat.setString(15, tempmsg.getFromSocket());
            stat.setString(16, tempmsg.getBankcodefrom());
            stat.setString(17, tempmsg.getCustNo());
            stat.setString(18, tempmsg.getTerminalId());
            stat.setString(19, tempmsg.getMti());
            stat.setString(20, tempmsg.getMerchantType());

            stat.executeUpdate();
        } catch (SQLException ex) {
            try {
                iso.setMTI(ISOFunction.setMTIISOResponse(iso.getMTI()));
                iso.set(39,"94");
            } catch (ISOException ex1) {
                java.util.logging.Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            log.error("saveMessageToTempmsg : " + tempmsg.getMsgid() + " - " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public boolean saveMessageToTempmsgRev(Tempmsg tempmsg) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("INSERT INTO tempmsg(msgid, abdmsg, reqisobank, requesttime, noref, cardno, bankcode, "
                    + "fromaccount, toaccount, amount, proccode, transactionid, productcode, trxidbackend, fromsocket, bankcodefrom, custno, terminalid, mti, merchanttype) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stat.setString(1, tempmsg.getMsgid());
            stat.setString(2, tempmsg.getAbdmsg());
            stat.setString(3, tempmsg.getReqisobank());
            stat.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
            stat.setString(5, tempmsg.getNoref());
            stat.setString(6, tempmsg.getCardno());
            stat.setString(7, tempmsg.getBankcode());
            stat.setString(8, tempmsg.getFromaccount());
            stat.setString(9, tempmsg.getToaccount());
            stat.setString(10, tempmsg.getAmount());
            stat.setString(11, tempmsg.getProccode());
            stat.setString(12, tempmsg.getTransactionid());
            stat.setString(13, tempmsg.getProductcode());
            stat.setString(14, tempmsg.getTrxidbackend());
            stat.setString(15, tempmsg.getFromSocket());
            stat.setString(16, tempmsg.getBankcodefrom());
            stat.setString(17, tempmsg.getCustNo());
            stat.setString(18, tempmsg.getTerminalId());
            stat.setString(19, tempmsg.getMti());
            stat.setString(20, tempmsg.getMerchantType());
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("saveMessageToTempmsgRev : " + tempmsg.getMsgid() + " - " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public Tempmsg getMessageRespFromTempmsg(Tempmsg tempmsg) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        tempmsg.setStatusreply(false);
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT respisobank, responsecode FROM tempmsg where msgid = ?  and statusreply is true");
            stat.setString(1, tempmsg.getMsgid());
            rs = stat.executeQuery();
            while (rs.next()) {
                tempmsg = new Tempmsg();
                tempmsg.setRespisobank(rs.getString("respisobank"));
                tempmsg.setResponsecode(rs.getString("responsecode"));
                tempmsg.setStatusreply(true);
//                System.out.println("dapet data di tempmsg");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getMessageRespFromTempmsg : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return tempmsg;
    }

    public Messagein getMessageForRoutingFromTempmsg(Messagein msgin) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT a.trxidbackend, a.abdmsg, a.bankcode, a.proccode, a.transactionid, a.fromsocket, a.responsecode, b.tcbiller FROM tempmsg a join trancode b on a.productcode=b.trancodeid where msgid = ?");
            stat.setString(1, msgin.getMsgid());
            System.out.println(this.getClass().getName() + " : " + msgin.getMsgid());
            rs = stat.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString("bankcode"));
                msgin.setSendto(rs.getString("bankcode"));
                if (rs.getString("responsecode") == null) {
                    msgin.setIsomsgSource(IsoProcess.unpackRequest(rs.getString("abdmsg")));
                }
                msgin.setFromsocket(rs.getString("fromsocket"));
                msgin.setTcbiller(rs.getString("tcbiller"));
                msgin.setTrancode(rs.getString("transactionid"));
                msgin.setProccode(rs.getString("proccode"));
                msgin.setTrxidbackend(rs.getString("trxidbackend"));
                msgin.setRespcode(rs.getString("responsecode"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getMessageForRoutingFromTempmsg : " + ex.getMessage());
        } catch (Exception ex) {
            log.error("getMessageForRoutingFromTempmsg : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return msgin;
    }

    public String getPackageName(String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT packagename FROM socketconn where bankcode = ?");
            stat.setString(1, bankcode);
            rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getString("packagename");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getPackageName : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return "null";
    }

    public HashMap getWebConnectionResource(String todirect) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap webconn = new HashMap();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT host, bankcode FROM socketconn where todirect = ?");
            stat.setString(1, todirect);
            rs = stat.executeQuery();
            while (rs.next()) {
                webconn.put(rs.getString("bankcode"), rs.getString("host"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getWebConnectionResource : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return webconn;
    }

    public Messagein getSendToConn(String trancode, Messagein msgin) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT bankcode, tcbiller FROM trancode where trancodeid = ?");
            stat.setString(1, trancode);
            rs = stat.executeQuery();
            while (rs.next()) {
                msgin.setSendto(rs.getString("bankcode"));
                msgin.setTcbiller(rs.getString("tcbiller"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getSendToConn : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return msgin;
    }

    public Tempmsg updateStatusReplyTempmsg(String msgid, String respisobank, String responsecode, String rcInternal) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Tempmsg tempmsg = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE tempmsg SET respisobank=?, responsecode=?, statusreply=?, responsetime=?, rcinternal=? WHERE msgid=?");
            stat.setString(1, respisobank);
            stat.setString(2, responsecode);
            stat.setBoolean(3, true);
            stat.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
            stat.setString(5, rcInternal);
            stat.setString(6, msgid);
            stat.executeUpdate();
            stat.clearBatch();
            stat.clearParameters();
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("updateStatusReplyTempmsg : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return tempmsg;
    }

    public Tempmsg updateStatusReplyTempmsg2(String msgid, String respisobank, String responsecode, String rcInternal, String trxidBackend) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Tempmsg tempmsg = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE tempmsg SET respisobank=?, responsecode=?, statusreply=?, responsetime=?, rcinternal=?, trxidbackend=? WHERE msgid=?");
            stat.setString(1, respisobank);
            stat.setString(2, responsecode);
            stat.setBoolean(3, true);
            stat.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
            stat.setString(5, rcInternal);
            stat.setString(6, trxidBackend);
            stat.setString(7, msgid);
            stat.executeUpdate();
            stat.clearBatch();
            stat.clearParameters();
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("updateStatusReplyTempmsg2 : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return tempmsg;
    }

    public Tempmsg updateTrxIdBackEnd(String msgid, String trxid) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Tempmsg tempmsg = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE tempmsg SET trxidbackend=? WHERE msgid=?");
            stat.setString(1, trxid);
            stat.setString(2, msgid);
            stat.executeUpdate();
            stat.clearBatch();
            stat.clearParameters();
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("updateTrxIdBackEnd : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return tempmsg;
    }

    public Tempmsg updateRequestMessage(String msgid, String reqmsg, String trxid) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Tempmsg tempmsg = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE tempmsg SET reqisobank=?, trxidbackend=? WHERE msgid=?");
            stat.setString(1, reqmsg);
            stat.setString(2, trxid);
            stat.setString(3, msgid);
            stat.executeUpdate();
            stat.clearBatch();
            stat.clearParameters();
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("updateRequestMessage : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return tempmsg;
    }

    public String getOldMessageW4FromTempmsg(String startMsg, String rrn) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT w4msg FROM tempmsg where w4msg like ? and date(requesttime) = current_date");
//            stat.setString(1, "TRX_%~%" + rrn + "~%");
            stat.setString(1, startMsg + "%~" + rrn + "~%");
//            System.out.println("SELECT w4msg FROM tempmsg where w4msg like " + startMsg+"%~%" + rrn + "~%" + " and date(requesttime) = current_date");
            rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getString("w4msg");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getOldMessageW4FromTempmsg : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return null;
    }

    public int getSelisihWaktuW4FromTempmsg(String terminalId, String procCode, String rrn, String amount, String cardNo) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT extract(epoch from current_timestamp-requesttime) as selisih FROM tempmsg where w4msg like ?");
            stat.setString(1, "TRX_~" + terminalId + "~%~" + procCode + "~%~" + rrn + "~%~" + amount + "~" + cardNo + "~%");
            rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getInt("selisih");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getSelisihWaktuW4FromTempmsg : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return 0;
    }

    public void setNextStan() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT count(*) as count from stanmanage where standate = current_date");
            rs = stat.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") == 0) {
                    log.info("create next stan");
                    clearStatment(stat);
                    stat = conn.prepareStatement("INSERT INTO stanmanage(standate) VALUES (current_date)");
                    stat.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getSelisihWaktuW4FromTempmsg : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
    }

    public boolean saveMessageToMessageOut(String message, String typeMessage, String toSocket) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("INSERT INTO messageout(message, waktu, typemsg, tosocket) VALUES (?, ?, ?, ?)");
            stat.setString(1, message);
            stat.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            stat.setString(3, typeMessage);
            stat.setString(4, toSocket);
            stat.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("saveMessageToMessageOut : " + ex.getMessage());

        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public List getMessageOutgoing(String partner) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();
        Messageout msgout;
        String listMsgId = "";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            stat = conn.prepareStatement("SELECT message, waktu, typemsg, tosocket, msgoutid FROM messageout where date(waktu) = current_date and status is false and typemsg =? order by waktu fetch first 1000 rows only ");
            stat = conn.prepareStatement("SELECT message, waktu, typemsg, tosocket, msgoutid FROM messageout where waktu >= (now()- (INTERVAL '180 seconds')) and status is false and typemsg =? order by waktu fetch first 1000 rows only ");
            stat.setString(1, partner);
            rs = stat.executeQuery();
            while (rs.next()) {
                msgout = new Messageout();
                msgout.setMessage(rs.getString("message"));
                msgout.setWaktu(rs.getDate("waktu"));
                msgout.setStatus(false);
                msgout.setTypemsg(rs.getString("typemsg"));
                msgout.setTosocket(rs.getString("tosocket"));
                msgout.setMsgoutid(rs.getLong("msgoutid"));
                l.add(msgout);
                listMsgId += "," + String.valueOf(rs.getLong("msgoutid"));
            }
            listMsgId = listMsgId.replaceFirst(",", "");
            clearStatment(stat);
            if (!listMsgId.equals("")) {
                stat = conn.prepareStatement("UPDATE messageout SET status=true WHERE msgoutid in (" + listMsgId + ")");
                stat.executeUpdate();
                stat.clearBatch();
                stat.clearParameters();
            }
            listMsgId = null;
        } catch (SQLException ex) {
            log.error("getMessageOutgoing : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return l;
    }

    public boolean cleanData() {
        Connection conn = null;
        PreparedStatement stat = null;
//        GregorianCalendar cal = new GregorianCalendar();
//        cal.add(GregorianCalendar.DAY_OF_YEAR, -1);
//        Date tanggalhapus = new Date(cal.getTimeInMillis());
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();

            log.info("clear tempmsg table");
            stat = conn.prepareStatement("INSERT INTO tempmsg_backup(msgid, abdmsg, reqisobank, respisobank, statusreply, requesttime, "
                    + "responsetime, noref, cardno, bankcode, fromaccount, toaccount,"
                    + "responsecode, amount, proccode, productcode, transactionid, trxidbackend, bankcodefrom, rcinternal, custno, terminalid, mti, merchanttype ) SELECT msgid, abdmsg, reqisobank, respisobank, statusreply, requesttime, "
                    + "responsetime, noref, cardno, bankcode, fromaccount, toaccount,"
                    + "responsecode, amount, proccode, productcode, transactionid, trxidbackend, bankcodefrom, rcinternal, custno, terminalid, mti, merchanttype  FROM tempmsg where date(requesttime) < current_date");
//                    + "responsecode, amount, proccode, transactionid FROM tempmsg where requesttime < ?");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            clearStatment(stat);
            stat = conn.prepareStatement("DELETE FROM tempmsg where date(requesttime) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            log.info("clear tempmsg table successful");
            clearStatment(stat);

            log.info("clear messagein table");
            stat = conn.prepareStatement("INSERT INTO messagein_backup(message, waktu, status, typemsg, fromsocket, msginid) SELECT message, waktu, status, typemsg, fromsocket, msginid FROM messagein where date(waktu) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            clearStatment(stat);
            stat = conn.prepareStatement("DELETE FROM messagein where date(waktu) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            log.info("clear messagein table successful");
            clearStatment(stat);
            stat = conn.prepareStatement("DELETE FROM messagein_backup where date(waktu_backup) < current_date-30");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            log.info("clear messagein_backup table successful");
            clearStatment(stat);

            log.info("clear messageout table");
            stat = conn.prepareStatement("INSERT INTO messageout_backup(message, waktu, status, msgoutid, typemsg, tosocket) SELECT message, waktu, status, msgoutid, typemsg, tosocket FROM messageout where date(waktu) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            clearStatment(stat);
            stat = conn.prepareStatement("DELETE FROM messageout where date(waktu) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            log.info("clear messageout table successful");
            clearStatment(stat);
            stat = conn.prepareStatement("DELETE FROM messageout_backup where date(waktu_backup) < current_date-30");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            log.info("clear messageout_backup table successful");
            clearStatment(stat);

            log.info("clear BPJS table");
            stat = conn.prepareStatement("INSERT INTO bpjs_backup(trxid, cmd, msgreq, msgresp, response, trxdt) SELECT trxid, cmd, msgreq, msgresp, response, trxdt FROM bpjs where date(trxdt) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            clearStatment(stat);
            stat = conn.prepareStatement("DELETE FROM bpjs where date(trxdt) < current_date");
//            stat.setDate(1, tanggalhapus);
            stat.executeUpdate();
            log.info("clear BPJS table successful");
            clearStatment(stat);

            log.info("EOD done");
            stat = conn.prepareStatement("UPDATE configapp SET eoddone=true");
            stat.executeUpdate();

            log.info("create next stan");
            stat = conn.prepareStatement("INSERT INTO stanmanage(standate) VALUES (current_date+1)");
            stat.executeUpdate();
            clearStatment(stat);

        } catch (SQLException ex) {
            log.error("cleanData : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    private void clearStatment(PreparedStatement stat) {
//        log.info("stat 2 : " + stat);
        if (stat != null) {
            try {
//                log.info("stat A");
                stat.clearBatch();
//                log.info("stat B");
                stat.clearParameters();
//                log.info("stat C");
                stat.close();
//                log.info("stat D");
                stat = null;
//                log.info("stat E");
            } catch (SQLException ex) {
//                log.error("clearStatment : " +ex.getMessage());
//                ex.printStackTrace();
            }
        }
    }

    private void clearDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
//                log.error("clearDBConnection : "+ex.getMessage());
            }
        }
    }

    private void clearResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
//                log.error("clearResultset : "+ex.getMessage());
            }
        }
    }

    private void clearAllConnStatRS(Connection conn, PreparedStatement stat, ResultSet rs) {
        clearResultset(rs);
        clearStatment(stat);
        clearDBConnection(conn);
    }

    public boolean setStatusSocketStart(boolean status) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE socketconn SET  statusstart=?");
            stat.setBoolean(1, status);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("setStatusSocketStart : " + ex.getMessage());
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public boolean setStatusSocketStart(boolean status, String todirect, int urutan, String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            if (todirect.equals(RuleNameParamater.abd)) {
//                stat = conn.prepareStatement("UPDATE socketconn SET  statusstart=? WHERE todirect=? and seq=?");
//            } else if (todirect.equals(RuleNameParamater.bank)) {
            stat = conn.prepareStatement("UPDATE socketconn SET  statusstart=? WHERE todirect=? and seq=? and bankcode=?");
            stat.setString(4, bankcode);
//            }
            stat.setBoolean(1, status);
            stat.setString(2, todirect);
            stat.setInt(3, urutan);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("setStatusSocketStart : " + ex.getMessage());
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public boolean setStatusSocketConnect(boolean status, String todirect, int urutan, String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            if (todirect.equals(RuleNameParamater.abd)) {
                stat = conn.prepareStatement("UPDATE socketconn SET  statusconnect=? WHERE todirect=? and seq=?");
            } else if (todirect.equals(RuleNameParamater.bank)) {
                stat = conn.prepareStatement("UPDATE socketconn SET  statusconnect=? WHERE todirect=? and seq=? and bankcode=?");
                stat.setString(4, bankcode);
            }
            stat.setBoolean(1, status);
            stat.setString(2, todirect);
            stat.setInt(3, urutan);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("setStatusSocketConnect : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public List getSocketConnectionList(String todirect) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();
        SocketDetail socketDetail;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT todirect, host, statusopen, statusstart, seq, port, statusconnect, headertype, bankcode, lengthincl, typeapp, autosignon, packagename FROM socketconn where todirect = ? order by seq asc");
            stat.setString(1, todirect);
            rs = stat.executeQuery();
            while (rs.next()) {
                socketDetail = new SocketDetail();
                socketDetail.setJenis(rs.getString("todirect"));
                socketDetail.setHost(rs.getString("host"));
                socketDetail.setStatusOpen(rs.getBoolean("statusopen"));
                socketDetail.setStatusStart(rs.getBoolean("statusstart"));
                socketDetail.setStatusConnect(rs.getBoolean("statusconnect"));
                socketDetail.setUrutan(rs.getInt("seq"));
                socketDetail.setPort(rs.getInt("port"));
                socketDetail.setTypeapp(rs.getString("typeapp"));
                socketDetail.setLengthIncl(rs.getBoolean("lengthincl"));
                socketDetail.setAutosignon(rs.getBoolean("autosignon"));
                socketDetail.setHeaderType(rs.getInt("headertype"));
                socketDetail.setBankCode(rs.getString("bankcode"));
                socketDetail.setPackageName(rs.getString("packagename"));
                l.add(socketDetail);
            }
        } catch (SQLException ex) {
            log.error("getSocketConnectionList : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return l;
    }

    public SocketConnectionEntity getSocketConnectionDetail(SocketConnectionEntity sce) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT host, statusopen, port, headertype, lengthincl, typeapp, autosignon FROM socketconn where todirect = ? and bankcode = ? and seq = ? order by seq asc");
            stat.setString(1, sce.getJenis());
            stat.setString(2, sce.getBankCode());
            stat.setInt(3, sce.getUrutan());
            rs = stat.executeQuery();
            while (rs.next()) {
                sce.setIpAddress(rs.getString("host"));
                sce.setOpenSocket(rs.getBoolean("statusopen"));
                sce.setPort(rs.getInt("port"));
                sce.setType(rs.getString("typeapp"));
                sce.setLengthIncl(rs.getBoolean("lengthincl"));
                sce.setAutosignon(rs.getBoolean("autosignon"));
                sce.setHeaderMessageType(rs.getInt("headertype"));

            }
        } catch (SQLException ex) {
            log.error("getSocketConnectionList : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return sce;
    }

    public List getSocketConnectionList() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();
        SocketDetail socketDetail;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT todirect, host, statusopen, statusstart, seq, port, statusconnect, bankcode, typeapp, conname, autosignon, packagename FROM socketconn where typeconn = 'socket' order by statusconnect, todirect, seq asc");
            rs = stat.executeQuery();
            while (rs.next()) {
                socketDetail = new SocketDetail();
                socketDetail.setTodirect(rs.getString("todirect"));
                socketDetail.setHost(rs.getString("host"));
                socketDetail.setStatusOpen(rs.getBoolean("statusopen"));
                socketDetail.setStatusStart(rs.getBoolean("statusstart"));
                socketDetail.setStatusConnect(rs.getBoolean("statusconnect"));
                socketDetail.setAutosignon(rs.getBoolean("autosignon"));
                socketDetail.setUrutan(rs.getInt("seq"));
                socketDetail.setJenis(rs.getString("typeapp"));
                socketDetail.setBankCode(rs.getString("bankcode"));
                socketDetail.setConname(rs.getString("conname"));
                socketDetail.setPackageName(rs.getString("packagename"));
                socketDetail.setPort(rs.getInt("port"));
                l.add(socketDetail);
            }
        } catch (SQLException ex) {
            log.error("getSocketConnectionList : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return l;
    }

    public String getNextStan() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        int currentStan = 1;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT stanno FROM stanmanage where standate = current_date");
            rs = stat.executeQuery();
            while (rs.next()) {
                currentStan = rs.getInt("stanno");
            }
            clearStatment(stat);
            stat = conn.prepareStatement("UPDATE stanmanage SET stanno=? WHERE standate=current_date;");
            stat.setInt(1, currentStan + 1);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("getNextStan : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return String.valueOf(currentStan);
    }

    public boolean saveBPJStrx(String trxid, String msgreq, String cmd) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("INSERT INTO bpjs(trxid, cmd, msgreq) VALUES (?, ?, ?)");
            stat.setString(1, trxid);
            stat.setString(2, cmd);
            stat.setString(3, msgreq);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("saveBPJStrx : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public boolean updateBPJStrxresponse(String trxid, String msgresp, String status) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE bpjs SET msgresp=?, response=? WHERE trxid=? and date(trxdt)=current_date");
            stat.setString(1, msgresp);
            stat.setString(2, status);
            stat.setString(3, trxid);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("updateBPJStrxresponse : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }

    public List getMessageTimeout() {
//        log.info("getMessageIncoming : " + partner);
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();
        Messagein msgin;
        String listMsgId = "";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT msgid, abdmsg, fromsocket  FROM tempmsg where requesttime <= (now()- (INTERVAL '" + StaticParameter.timeout_second + " seconds')) and statusreply is false");
            rs = stat.executeQuery();
            while (rs.next()) {
                msgin = new Messagein();
                msgin.setMessage(rs.getString("abdmsg"));
                msgin.setFromsocket(rs.getString("fromsocket"));
                msgin.setMsgid(rs.getString("msgid"));
                l.add(msgin);
                listMsgId += ",'" + rs.getString("msgid") + "'";
            }
            listMsgId = listMsgId.replaceFirst(",", "");
            if (!listMsgId.equals("")) {
                clearStatment(stat);
                stat = conn.prepareStatement("UPDATE tempmsg SET statusreply=?, responsecode=?, rcinternal=? WHERE msgid in (" + listMsgId + ")");
                stat.setBoolean(1, true);
                stat.setString(2, "68");
                stat.setString(3, "68");
                stat.executeUpdate();
                stat.clearBatch();
                stat.clearParameters();
            }
            listMsgId = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("getMessageTimeout : " + ex.getMessage());

        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return l;
    }

    public boolean saveMessageTimeout(Messagein msgin) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("INSERT INTO timeoutresp(msgid, respisobank, responsetime) VALUES (?, ?, ?);");
            stat.setString(1, msgin.getMsgid());
            stat.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            stat.setString(2, msgin.getMessage());
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("saveMessageTimeout : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            clearStatment(stat);
            clearDBConnection(conn);
        }
        return true;
    }
}
