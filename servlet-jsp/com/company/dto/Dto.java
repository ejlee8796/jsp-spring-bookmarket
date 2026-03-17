/*
 * Decompiled with CFR 0.152.
 */
package com.company.dto;

public class Dto {
    private int bno;
    private String bname;
    private String bpass;
    private String btitle;
    private String bcontent;
    private String bfile;
    private String bdate;
    private int bhit;
    private String bip;
    private int bgroup;
    private int bstep;
    private int bdepth;

    public Dto() {
    }

    public Dto(int bno, String bname, String btitle, String bdate, int bhit, String bfile) {
        this.bno = bno;
        this.bname = bname;
        this.btitle = btitle;
        this.bfile = bfile;
        this.bdate = bdate;
        this.bhit = bhit;
    }

    public Dto(int bno, String btitle, String bcontent, String bfile, String bname) {
        this.bno = bno;
        this.bname = bname;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bfile = bfile;
    }

    public int getBno() {
        return this.bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBname() {
        return this.bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpass() {
        return this.bpass;
    }

    public void setBpass(String bpass) {
        this.bpass = bpass;
    }

    public String getBtitle() {
        return this.btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return this.bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBfile() {
        return this.bfile;
    }

    public void setBfile(String bfile) {
        this.bfile = bfile;
    }

    public String getBdate() {
        return this.bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public int getBhit() {
        return this.bhit;
    }

    public void setBhit(int bhit) {
        this.bhit = bhit;
    }

    public String getBip() {
        return this.bip;
    }

    public void setBip(String bip) {
        this.bip = bip;
    }

    public int getBgroup() {
        return this.bgroup;
    }

    public void setBgroup(int bgroup) {
        this.bgroup = bgroup;
    }

    public int getBstep() {
        return this.bstep;
    }

    public void setBstep(int bstep) {
        this.bstep = bstep;
    }

    public int getBdepth() {
        return this.bdepth;
    }

    public void setBdepth(int bdepth) {
        this.bdepth = bdepth;
    }

    public String toString() {
        return "Dto [bno=" + this.bno + ", bname=" + this.bname + ", bpass=" + this.bpass + ", btitle=" + this.btitle + ", bcontent=" + this.bcontent + ", bfile=" + this.bfile + ", bdate=" + this.bdate + ", bhit=" + this.bhit + ", bip=" + this.bip + ", bgroup=" + this.bgroup + ", bstep=" + this.bstep + ", bdepth=" + this.bdepth + "]";
    }
}
