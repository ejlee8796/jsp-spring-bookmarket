/*
 * Decompiled with CFR 0.152.
 */
package com.company.domain;

public class PhotoCommentVO {
    private int cno;
    private int bno;
    private String comment;
    private String writer;
    private String pass;
    private String secret;
    private String star;
    private String cdate;
    private String file;

    public int getCno() {
        return this.cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getBno() {
        return this.bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriter() {
        return this.writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCdate() {
        return this.cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getStar() {
        return this.star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String toString() {
        return "PhotoCommentVO [cno=" + this.cno + ", bno=" + this.bno + ", comment=" + this.comment + ", writer=" + this.writer + ", pass=" + this.pass + ", secret=" + this.secret + ", star=" + this.star + ", cdate=" + this.cdate + ", file=" + this.file + "]";
    }
}
