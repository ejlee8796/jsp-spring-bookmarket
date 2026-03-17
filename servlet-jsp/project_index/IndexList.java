/*
 * Decompiled with CFR 0.152.
 */
package project_index;

import com.company.db.DBManager;
import com.company.dto.BDto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class IndexList {
    ArrayList<BDto> list = new ArrayList();
    BDto dto = new BDto();

    public ArrayList<BDto> List(String table, String category, String bauthor) {
        block29: {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rset = null;
            DBManager dbmanager = new DBManager();
            try {
                try {
                    conn = dbmanager.getConnection();
                    stmt = conn.createStatement();
                    rset = bauthor == "" ? stmt.executeQuery("select *from " + table + " where bcategory='" + category + "' order by bno asc limit 0,5") : stmt.executeQuery("select *from " + table + " where bcategory='" + category + "' and bauthor='" + bauthor + "'");
                    while (rset.next()) {
                        int bno = rset.getInt("bno");
                        String btitle = rset.getString("btitle");
                        String bcontent = rset.getString("bcontent");
                        String bfile = rset.getString("bfile");
                        String blink = rset.getString("blink");
                        this.list.add(new BDto(bno, btitle, bcontent, bfile, blink));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rset != null) {
                        try {
                            rset.close();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block29;
                    try {
                        conn.close();
                    }
                    catch (Exception exception) {}
                }
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception exception) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception exception) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (Exception exception) {}
                }
            }
        }
        return this.list;
    }

    public ArrayList<BDto> tab(String table, String category) {
        block29: {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rset = null;
            DBManager dbmanager = new DBManager();
            try {
                try {
                    conn = dbmanager.getConnection();
                    stmt = conn.createStatement();
                    rset = stmt.executeQuery("select *from " + table + " where bcategory='" + category + "' order by bno asc limit 0,5");
                    while (rset.next()) {
                        int bno = rset.getInt("bno");
                        String bid = rset.getString("bauthor");
                        String btitle = rset.getString("btitle");
                        String bcontent = rset.getString("bcontent");
                        String bfile = rset.getString("bfile");
                        String bisbn = rset.getString("bisbn");
                        this.list.add(new BDto(bno, bid, btitle, bcontent, bfile, bisbn));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rset != null) {
                        try {
                            rset.close();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block29;
                    try {
                        conn.close();
                    }
                    catch (Exception exception) {}
                }
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception exception) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception exception) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (Exception exception) {}
                }
            }
        }
        return this.list;
    }
}
