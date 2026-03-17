/*
 * Decompiled with CFR 0.152.
 */
package com.company.dto;

public class MDto {
    private int mbno;
    private String mbid;
    private String mbpass;
    private String mbgender;
    private String mbemail;
    private String mbmobile;
    private String mbaddress;
    private String mblike;
    private String mbagree;
    private String mbdate;
    private String mbip;

    public MDto() {
    }

    public MDto(int mbno, String mbid, String mbpass, String mbgender, String mbemail, String mbmobile, String mbaddress, String mblike, String mbagree, String mbdate, String mbip) {
        this.mbno = mbno;
        this.mbid = mbid;
        this.mbpass = mbpass;
        this.mbgender = mbgender;
        this.mbemail = mbemail;
        this.mbmobile = mbmobile;
        this.mbaddress = mbaddress;
        this.mblike = mblike;
        this.mbagree = mbagree;
        this.mbdate = mbdate;
        this.mbip = mbip;
    }

    public int getMbno() {
        return this.mbno;
    }

    public void setMbno(int mbno) {
        this.mbno = mbno;
    }

    public String getMbid() {
        return this.mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getMbpass() {
        return this.mbpass;
    }

    public void setMbpass(String mbpass) {
        this.mbpass = mbpass;
    }

    public String getMbgender() {
        return this.mbgender;
    }

    public void setMbgender(String mbgender) {
        this.mbgender = mbgender;
    }

    public String getMbemail() {
        return this.mbemail;
    }

    public void setMbemail(String mbemail) {
        this.mbemail = mbemail;
    }

    public String getMbmobile() {
        return this.mbmobile;
    }

    public void setMbmobile(String mbmobile) {
        this.mbmobile = mbmobile;
    }

    public String getMbaddress() {
        return this.mbaddress;
    }

    public void setMbaddress(String mbaddress) {
        this.mbaddress = mbaddress;
    }

    public String getMblike() {
        return this.mblike;
    }

    public void setMblike(String mblike) {
        this.mblike = mblike;
    }

    public String getMbagree() {
        return this.mbagree;
    }

    public void setMbagree(String mbagree) {
        this.mbagree = mbagree;
    }

    public String getMbdate() {
        return this.mbdate;
    }

    public void setMbdate(String mbdate) {
        this.mbdate = mbdate;
    }

    public String getMbip() {
        return this.mbip;
    }

    public void setMbip(String mbip) {
        this.mbip = mbip;
    }

    public String toString() {
        return "MDto [mbno=" + this.mbno + ", mbid=" + this.mbid + ", mbpass=" + this.mbpass + ", mbgender=" + this.mbgender + ", mbemail=" + this.mbemail + ", mbmobile=" + this.mbmobile + ", mbaddress=" + this.mbaddress + ", mblike=" + this.mblike + ", mbagree=" + this.mbagree + ", mbdate=" + this.mbdate + ", mbip=" + this.mbip + "]";
    }
}
