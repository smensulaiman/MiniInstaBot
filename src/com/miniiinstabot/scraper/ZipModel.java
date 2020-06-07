package com.miniiinstabot.scraper;

public class ZipModel implements ZipManager {

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public ZipModel(String a, String b, String c, String d, String e, String f, String g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }

    @Override
    public String getA() {
        return a;
    }

    @Override
    public String getB() {
        return b;
    }

    @Override
    public String getC() {
        return c;
    }

    @Override
    public String getD() {
        return d;
    }

    @Override
    public String getE() {
        return e;
    }

    @Override
    public String getF() {
        return f;
    }

    @Override
    public String getG() {
        return g;
    }

}
