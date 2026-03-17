/*
 * Decompiled with CFR 0.152.
 */
package com.company.dao;

import com.company.db.DBManager;
import com.company.dto.Dto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDao {
    Connection conn;
    DBManager db;

    public BDao() {
        try {
            this.db = new DBManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return this.conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Dto fbDetail(int bno) {
        Dto dto;
        block29: {
            dto = new Dto();
            this.conn = null;
            Statement pstmt = null;
            ResultSet rs = null;
            String sql = "select * from index1view1 where bno = ?";
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        int bno1 = rs.getInt("bno");
                        String bname = rs.getString("bname");
                        String btitle = rs.getString("btitle");
                        String bdate = rs.getString("bdate");
                        int bhit = rs.getInt("bhit");
                        String bfile = rs.getString("bfile");
                        dto = new Dto(bno1, bname, btitle, bdate, bhit, bfile);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block29;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dto;
    }

    public void fbDetailHit(int bno) {
        block29: {
            this.conn = null;
            Statement pstmt = null;
            ResultSet rs = null;
            String sql = "update index1view1 set bhit = bhit+1 where bno = ?";
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    int result = pstmt.executeUpdate();
                    if (result < -1) {
                        System.out.print("조회실패");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block29;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Dto fbModify(int bno) {
        Dto dto;
        block29: {
            dto = new Dto();
            this.conn = null;
            Statement pstmt = null;
            ResultSet rs = null;
            String sql = "select a.btitle, a.bcontent, a.bfile as file1, b.bfile as file2  from index1view1 a, index1view1file1 b where a.bno = b.bno and a.bno = ?";
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String btitle = rs.getString("btitle");
                        String bcontent = rs.getString("bcontent");
                        String file1 = rs.getString("file1");
                        String file2 = rs.getString("file2");
                        dto = new Dto(bno, btitle, bcontent, file1, file2);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block29;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dto;
    }

    public int fbUpdate(String btitle, String bcontent, String bfile, String bdate, int bno) {
        int result;
        block20: {
            Statement pstmt = null;
            String sql = "update index1view1 set btitle=?, bcontent=?, bfile=?, bdate=? where bno = ?";
            result = 0;
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setString(1, btitle);
                    pstmt.setString(2, bcontent);
                    pstmt.setString(3, bfile);
                    pstmt.setString(4, bdate);
                    pstmt.setInt(5, bno);
                    result = pstmt.executeUpdate();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block20;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    public int fbUpdate(String bfile, int bno) {
        int result;
        block20: {
            Statement pstmt = null;
            String sql = "update index1view1file1 set bfile = ? where bno = ?";
            result = 0;
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setString(1, bfile);
                    pstmt.setInt(2, bno);
                    result = pstmt.executeUpdate();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block20;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    public int fbDelete(int bno) {
        int result;
        block21: {
            Statement pstmt = null;
            String sql = "delete from index1view1file1 where bno=?";
            result = 0;
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    result = pstmt.executeUpdate();
                    if (result > -1) {
                        sql = "delete from index1view1 where bno=?";
                        pstmt.close();
                        pstmt = this.conn.prepareStatement(sql);
                        pstmt.setInt(1, bno);
                        result = pstmt.executeUpdate();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block21;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    public int fbPrev(int bno) {
        int bid;
        block29: {
            System.out.println(bno);
            Statement pstmt = null;
            String sql = "select bno from index1view1 where ? < bno order by bno limit 1";
            ResultSet rs = null;
            bid = 0;
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        bid = rs.getInt("bno");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block29;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bid;
    }

    public int fbNext(int bno) {
        int bid;
        block29: {
            Statement pstmt = null;
            String sql = "select bno from index1view1 where bno < ? order by bno desc limit 1";
            ResultSet rs = null;
            bid = 0;
            try {
                try {
                    this.conn = this.db.getConnection();
                    pstmt = this.conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        bid = rs.getInt("bno");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (this.conn == null) break block29;
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (this.conn != null) {
                    try {
                        this.conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bid;
    }
}
