/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdw.db;

import com.mdw.model.Model_Conn;
import com.mdw.model.Model_Iso;
import com.mdw.model.Model_Report;
import com.mdw.model.Model_User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DatabaseProcess {

    private void clearStatment(PreparedStatement stat) {
        if (stat != null) {
            try {
                stat.clearBatch();
                stat.clearParameters();
                stat.close();
                stat = null;
            } catch (SQLException ex) {
            }
        }
    }

    private void clearDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
            }
        }
    }

    private void clearResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
            }
        }
    }

    private void clearAllConnStatRS(Connection conn, PreparedStatement stat, ResultSet rs) {
        clearResultset(rs);
        clearStatment(stat);
        clearDBConnection(conn);
    }

    //proses login
    public boolean validate(String user, String password) throws SQLException {
        boolean status = false;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT * FROM ppob_user WHERE userlogin=? AND userpass=?");
            stat.setString(1, user);
            stat.setString(2, password);
            rs = stat.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Model_Report> getAllTrx() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Report> listTrx = new ArrayList<>();
        SimpleDateFormat requesttime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat requesttime2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select * from (\n"
                    + "select requesttime,noref,productcode,custno,fromagent,amount from tempmsg where productcode in (select trancodeid from trancode where category = 'ZIS')\n"
                    + "union all \n"
                    + "select requesttime,noref,productcode,custno,fromagent,amount from tempmsg_backup where productcode in (select trancodeid from trancode where category = 'ZIS') order by requesttime desc\n"
                    + ") AS tempmsg");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Report reportyabes = new Model_Report();
                
                
                if (rs.getString("requesttime") == null) {
                    reportyabes.setRequesttime("-");
                } else {
                    Date requesttime = requesttime1.parse(rs.getString("requesttime"));
                    reportyabes.setRequesttime(requesttime2.format(requesttime));
                }
                
                
//                reportyabes.setRequesttime(rs.getString("requesttime"));
                reportyabes.setNoref(rs.getString("noref"));
                if (null != rs.getString("productcode")) {
                    switch (rs.getString("productcode")) {
                        case "400012":
                            reportyabes.setProductcode("ZAKAT MAL");
                            break;
                        case "400013":
                            reportyabes.setProductcode("ZAKAT FITRAH");
                            break;
                        case "400014":
                            reportyabes.setProductcode("INFAQ DAN SHODAQOH");
                            break;
                        case "400015":
                            reportyabes.setProductcode("DONASI");
                            break;
                        default:
                            break;
                    }
                }
                reportyabes.setCustno(rs.getString("custno"));
                reportyabes.setFromaccount(rs.getString("fromagent"));
                reportyabes.setAmount(rs.getString("amount"));
                reportyabes.setMerchanttype("rida");
                listTrx.add(reportyabes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        System.out.println("jjjj L : " + listTrx);
        return listTrx;
    }

    public ArrayList<Model_User> getAllUser() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_User> listUser = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("Select * from loginpanel");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_User mu = new Model_User();
                mu.setUser_id(rs.getString("user_id"));
                mu.setUsername(rs.getString("username"));
                mu.setBankcode(rs.getString("bankcode"));
                mu.setStatususer(rs.getString("statususer"));
                listUser.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listUser;
    }

    public String addUser(Model_User pro) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            boolean status1 = false;
            stat = conn.prepareStatement("select * from loginpanel where bankcode=?");
            stat.setString(1, pro.getBankcode());
            rs = stat.executeQuery();
            status1 = rs.next();
            if (status1 == true) {
                return status = "01";
            } else {
                stat = conn.prepareStatement("INSERT INTO loginpanel(username, password, bankcode, statususer) VALUES (?, ?, ?, ?)");
                stat.setString(1, pro.getUsername());
                stat.setString(2, pro.getPassword());
                stat.setString(3, pro.getBankcode());
                stat.setString(4, pro.getStatususer());
                stat.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status = "00";
    }

    public String deleteuser(String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from loginpanel where bankcode=?");
            stat.setString(1, bankcode);
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateUser(String bankcode, String username, String statususer) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE loginpanel SET username=?, statususer=? WHERE bankcode=?");
            stat.setString(1, username);
            stat.setString(2, statususer);
            stat.setString(3, bankcode);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Model_Conn> getAllConn() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Conn> listConn = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("Select * from socketconn");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Conn mu = new Model_Conn();
                mu.setSeq(rs.getString("seq"));
                mu.setTodirect(rs.getString("todirect"));
                mu.setHost(rs.getString("host"));
                mu.setStatusopen(rs.getString("statusopen"));
                mu.setStatusstart(rs.getString("statusstart"));
                mu.setPort(rs.getString("port"));
                mu.setStatusconnect(rs.getString("statusconnect"));
                mu.setHeadertype(rs.getString("headertype"));
                mu.setBankcode(rs.getString("bankcode"));
                mu.setLengthincl(rs.getString("lengthincl"));
                mu.setTypeapp(rs.getString("typeapp"));
                mu.setConname(rs.getString("conname"));
                mu.setPackagename(rs.getString("packagename"));
                mu.setAutosignon(rs.getString("autosignon"));
                mu.setNeedsignon(rs.getString("needsignon"));
                mu.setPackagerpath(rs.getString("packagerpath"));
                mu.setLoadbalancing(rs.getString("loadbalancing"));
                mu.setLbgroup(rs.getString("lbgroup"));
                listConn.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listConn;
    }

    public String addConn(Model_Conn pro) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        String status;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select max(seq) as seq from socketconn");
            rs = stat.executeQuery();
            while (rs.next()) {
                stat = conn.prepareStatement("INSERT INTO socketconn (todirect, host, statusopen, port, headertype, bankcode, lengthincl, typeapp, conname, packagename, autosignon, needsignon, packagerpath, loadbalancing, lbgroup, seq) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stat.setString(1, pro.getTodirect());
                stat.setString(2, pro.getHost());
                //statusopen jika sbg server = true, jika sbg client = false
                if (pro.getStatusopen().equals("1")) {
                    stat.setBoolean(3, true);
                } else if (pro.getStatusopen().equals("0")) {
                    stat.setBoolean(3, false);
                }
                stat.setInt(4, Integer.valueOf(pro.getPort()));
                //  headertype
                //	1 : 4 length byte
                //	2 : STX/ETX
                //	3 : 2 hexa byte hi-low
                //	4 : 2 hexa byte low-hi
                stat.setInt(5, Integer.valueOf(pro.getHeadertype()));
                stat.setString(6, pro.getBankcode());
                //length include = true and false
                if (pro.getLengthincl().equals("1")) {
                    stat.setBoolean(7, true);
                } else if (pro.getStatusopen().equals("0")) {
                    stat.setBoolean(7, false);
                }
                //typeapp = C as client, S as server
                stat.setString(8, pro.getTypeapp());
                stat.setString(9, pro.getConname());
                //packagename = iso,json,xml
                stat.setString(10, pro.getPackagename());
                //autosignon = false untuk web, true untuk iso
                if (pro.getAutosignon().equals("1")) {
                    stat.setBoolean(11, true);
                } else if (pro.getStatusopen().equals("0")) {
                    stat.setBoolean(11, false);
                }
                //needsignon = false jika tidak butuhsignon, true jika butuh signon
                if (pro.getNeedsignon().equals("1")) {
                    stat.setBoolean(12, true);
                } else if (pro.getStatusopen().equals("0")) {
                    stat.setBoolean(12, false);
                }
                //typeconn = web/socket
                stat.setString(13, pro.getPackagerpath());
                if (pro.getLoadbalancing().equals("1")) {
                    stat.setBoolean(14, true);
                } else if (pro.getStatusopen().equals("0")) {
                    stat.setBoolean(14, false);
                }
                stat.setString(15, pro.getLbgroup());
                if (rs.getString("seq") == null) {
                    stat.setInt(16, 1);
                } else {
                    int gg = Integer.valueOf(rs.getString("seq"));
                    stat.setInt(16, gg + 1);
                }
                stat.executeUpdate();
                stat.clearParameters();
                stat.clearBatch();

                stat = conn.prepareStatement("CREATE TABLE public.opt_iso_table_" + pro.getBankcode() + "\n"
                        + "(\n"
                        + "    field numeric(3,0) NOT NULL,\n"
                        + "    name_of_field character varying(100) COLLATE pg_catalog.\"default\",\n"
                        + "    format character varying(15) COLLATE pg_catalog.\"default\",\n"
                        //                        + "    \"char\" character varying(5) COLLATE pg_catalog.\"default\",\n"
                        + "    length numeric(3,0),\n"
                        + "    CONSTRAINT opt_iso_table_" + pro.getBankcode() + "_pkey PRIMARY KEY (field)\n"
                        + ");\n"
                        + "\n"
                        + "CREATE TABLE public.opt_200_" + pro.getBankcode() + "\n"
                        + "(\n"
                        + "    field numeric(3,0) NOT NULL,\n"
                        + "    name_of_field character varying(100) COLLATE pg_catalog.\"default\",\n"
                        + "    \"200\" character varying(2) COLLATE pg_catalog.\"default\",\n"
                        + "    \"210\" character varying(2) COLLATE pg_catalog.\"default\",\n"
                        + "    CONSTRAINT opt_200_" + pro.getBankcode() + "_pkey PRIMARY KEY (field)\n"
                        + ");\n"
                        + "\n"
                        + "CREATE TABLE public.opt_400_" + pro.getBankcode() + "\n"
                        + "(\n"
                        + "    field numeric(3,0) NOT NULL,\n"
                        + "    name_of_field character varying(100) COLLATE pg_catalog.\"default\",\n"
                        + "    \"400\" character varying(2) COLLATE pg_catalog.\"default\",\n"
                        + "    \"410\" character varying(2) COLLATE pg_catalog.\"default\",\n"
                        + "    CONSTRAINT opt_400_" + pro.getBankcode() + "_pkey PRIMARY KEY (field)\n"
                        + ");\n"
                        + "\n"
                        + "CREATE TABLE public.opt_800_" + pro.getBankcode() + "\n"
                        + "(\n"
                        + "    name_of_field character varying(100) COLLATE pg_catalog.\"default\",\n"
                        + "    field numeric(3,0) NOT NULL,\n"
                        + "    \"800\" character varying(2) COLLATE pg_catalog.\"default\",\n"
                        + "    \"810\" character varying(2) COLLATE pg_catalog.\"default\",\n"
                        + "    CONSTRAINT opt_800_" + pro.getBankcode() + "_pkey PRIMARY KEY (field)\n"
                        + ")");
                stat.executeUpdate();
                stat.clearParameters();
                stat.clearBatch();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status = "00";
    }

    public String deleteconn(String seq, String todir, String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from socketconn where todirect=? and seq=?");
            stat.setString(1, todir);
            stat.setInt(2, Integer.valueOf(seq));
            stat.executeUpdate();
            stat.clearParameters();
            stat.clearBatch();
            stat = conn.prepareStatement("DROP TABLE opt_iso_table_" + bankcode + ", opt_200_" + bankcode + ", opt_400_" + bankcode + ", opt_800_" + bankcode + "");
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateConn(String seq, String todirect, String host, String port, String statusopen,
            String headertype, String bankcode, String lengthincl, String typeapp, String conname, String packagename,
            String autosignon, String needsignon, String packagerpath, String loadbalancing, String lbgroup) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE socketconn SET host=?, port=?, statusopen=?, headertype=?, bankcode=?, lengthincl=?, typeapp=?, conname=?, packagename=?, "
                    + "autosignon=?, needsignon=?, packagerpath=?, loadbalancing=?, lbgroup=? WHERE seq=? and todirect=?");
            stat.setString(1, host);
            stat.setInt(2, Integer.valueOf(port));
//            stat.setString(3, statusopen);
            if (statusopen.equals("1")) {
                stat.setBoolean(3, true);
            } else if (statusopen.equals("0")) {
                stat.setBoolean(3, false);
            }
            stat.setInt(4, Integer.valueOf(headertype));
            stat.setString(5, bankcode);
//            stat.setString(6, lengthincl);
            if (lengthincl.equals("1")) {
                stat.setBoolean(6, true);
            } else if (lengthincl.equals("0")) {
                stat.setBoolean(6, false);
            }
            stat.setString(7, typeapp);
            stat.setString(8, conname);
            stat.setString(9, packagename);
//            stat.setString(10, autosignon);
            if (autosignon.equals("1")) {
                stat.setBoolean(10, true);
            } else if (autosignon.equals("0")) {
                stat.setBoolean(10, false);
            }
//            stat.setString(11, needsignon);
            if (needsignon.equals("1")) {
                stat.setBoolean(11, true);
            } else if (needsignon.equals("0")) {
                stat.setBoolean(11, false);
            }
            stat.setString(12, packagerpath);
//            stat.setString(13, loadbalancing);
            if (loadbalancing.equals("1")) {
                stat.setBoolean(13, true);
            } else if (loadbalancing.equals("0")) {
                stat.setBoolean(13, false);
            }
            stat.setString(14, lbgroup);
            stat.setInt(15, Integer.valueOf(seq));
            stat.setString(16, todirect);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Model_Iso> getAllIso(String bankcode) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Iso> listIso = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("Select * from opt_iso_table_" + bankcode + "");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Iso mu = new Model_Iso();
                mu.setField(rs.getString("field"));
                mu.setName_of_field(rs.getString("name_of_field"));
                mu.setFormat(rs.getString("format"));
//                mu.setChars(rs.getString("char"));
                mu.setLength(rs.getString("length"));
                listIso.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listIso;
    }

    public String addField(String bankcode, String field, String name, String format, String length) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            boolean status1 = false;
            HashMap resp = new HashMap();
            stat = conn.prepareStatement("select * from opt_iso_table_" + bankcode + " where field=?");
            stat.setInt(1, Integer.valueOf(field));
            rs = stat.executeQuery();
            status1 = rs.next();
            if (status1 == true) {
                return status = "01";
            } else {
                stat = conn.prepareStatement("INSERT INTO opt_iso_table_" + bankcode + "(field, name_of_field, format, length) VALUES (?, ?, ?, ?)");
                stat.setInt(1, Integer.valueOf(field));
                stat.setString(2, name);
                stat.setString(3, format);
//                stat.setString(4, chars);
                stat.setInt(4, Integer.valueOf(length));
                stat.executeUpdate();
                status = "00";
            }
        } catch (SQLException e) {
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status = "00";
    }

    public String deletefield(String field, String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from opt_iso_table_" + bankcode + " where field=?");
            stat.setInt(1, Integer.valueOf(field));
            stat.executeUpdate();
            stat.clearParameters();
            stat.clearBatch();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateField(String field, String name, String format, String length, String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE opt_iso_table_" + bankcode + " SET name_of_field=?, format=?, length=? WHERE field=?");
            stat.setString(1, name);
            stat.setString(2, format);
//            stat.setString(3, chars);
            stat.setInt(3, Integer.valueOf(length));
            stat.setInt(4, Integer.valueOf(field));
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }
}
