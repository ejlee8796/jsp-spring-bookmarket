/*
 * Decompiled with CFR 0.152.
 */
package com.company.dto;

public class UDto {
    private int uno;
    private String userid;
    private String utitle;
    private String uwriter;
    private String uisbn;
    private String udate;
    private String ufile;
    private String uprice;

    public UDto() {
    }

    public UDto(int uno, String userid, String utitle, String uwriter, String uisbn, String udate, String ufile, String uprice) {
        this.uno = uno;
        this.userid = userid;
        this.utitle = utitle;
        this.uwriter = uwriter;
        this.uisbn = uisbn;
        this.udate = udate;
        this.ufile = ufile;
        this.uprice = uprice;
    }

    public int getUno() {
        return this.uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUtitle() {
        return this.utitle;
    }

    public void setUtitle(String utitle) {
        this.utitle = utitle;
    }

    public String getUwriter() {
        return this.uwriter;
    }

    public void setUwriter(String uwriter) {
        this.uwriter = uwriter;
    }

    public String getUisbn() {
        return this.uisbn;
    }

    public void setUisbn(String uisbn) {
        this.uisbn = uisbn;
    }

    public String getUdate() {
        return this.udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public String getUfile() {
        return this.ufile;
    }

    public void setUfile(String ufile) {
        this.ufile = ufile;
    }

    public String getUprice() {
        return this.uprice;
    }

    public void setUprice(String uprice) {
        this.uprice = uprice;
    }
}
