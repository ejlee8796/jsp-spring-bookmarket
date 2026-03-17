/*
 * Decompiled with CFR 0.152.
 */
package com.company.dto;

public class BDto {
    private int bno;
    private String bid;
    private String bpass;
    private String btitle;
    private String bcontent;
    private String bdate;
    private int bhit;
    private String bip;
    private String bfile;
    private String blink;
    private String bcategory;
    private String bisbn;

    public BDto() {
    }

    public BDto(int bno, String bid, String bemail, String bpass, String btitle, String bcontent, String bdate, int bhit, String bip, String bfile) {
        this.bno = bno;
        this.bid = bid;
        this.bpass = bpass;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bdate = bdate;
        this.bhit = bhit;
        this.bip = bip;
        this.bfile = bfile;
    }

    public BDto(int bno, String bid, String btitle, String bdate, int bhit) {
        this.bno = bno;
        this.bid = bid;
        this.btitle = btitle;
        this.bdate = bdate;
        this.bhit = bhit;
    }

    public BDto(String btitle, String bcontent) {
        this.btitle = btitle;
        this.bcontent = bcontent;
    }

    public BDto(int bno, String btitle, String bcontent, String bfile) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bfile = bfile;
    }

    public BDto(int bno, String bid, String btitle, String bcontent, String bfile, String bisbn) {
        this.bno = bno;
        this.bid = bid;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bfile = bfile;
        this.bisbn = bisbn;
    }

    public BDto(int bno, String bid, String bpass, String btitle, String bcontent, String bdate, int bhit, String bip, String bfile, String blink, String bcategory) {
        this.bno = bno;
        this.bid = bid;
        this.bpass = bpass;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bdate = bdate;
        this.bhit = bhit;
        this.bip = bip;
        this.bfile = bfile;
        this.blink = blink;
        this.bcategory = bcategory;
    }

    public BDto(int bno, String bid, String bpass, String btitle, String bcontent, String bdate, int bhit, String bip, String bfile, String blink, String bcategory, String bisbn) {
        this.bno = bno;
        this.bid = bid;
        this.bpass = bpass;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bdate = bdate;
        this.bhit = bhit;
        this.bip = bip;
        this.bfile = bfile;
        this.blink = blink;
        this.bcategory = bcategory;
        this.bisbn = bisbn;
    }

    public BDto(int bno, String btitle, String bcontent, String bfile, String blink) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bfile = bfile;
        this.blink = blink;
    }

    public String getBisbn() {
        return this.bisbn;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public int getBno() {
        return this.bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBid() {
        return this.bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
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

    public String getBfile() {
        return this.bfile;
    }

    public void setBfile(String bfile) {
        this.bfile = bfile;
    }

    public String getBlink() {
        return this.blink;
    }

    public void setBlink(String blink) {
        this.blink = blink;
    }

    public String getBcategory() {
        return this.bcategory;
    }

    public void setBcategory(String bcategory) {
        this.bcategory = bcategory;
    }
}
