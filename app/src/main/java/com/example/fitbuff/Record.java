package com.example.fitbuff;

public class Record
{
    private String recordid;
    private String recorddate;

    public Record(String recordid, String recorddate, String recordweight, String recordtodayex) {
        this.recordid = recordid;
        this.recorddate = recorddate;
        this.recordweight = recordweight;
        this.recordtodayex = recordtodayex;
    }

    private String recordweight;

    private String recordtodayex;

    public Record() {

    }
    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    public String getRecordweight() {
        return recordweight;
    }

    public void setRecordweight(String recordweight) {
        this.recordweight = recordweight;
    }

    public String getRecordtodayex() { return recordtodayex; }

    public void setRecordtodayex(String recordtodayex) {
        this.recordtodayex = recordtodayex;
    }




}