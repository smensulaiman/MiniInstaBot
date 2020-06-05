package com.miniiinstabot.scraper;


public class ZipModel implements ZipManager{
    
    private String address;
    private String zip;

    public ZipModel(String address, String zip) {
        this.address = address;
        this.zip = zip;
    }
    
    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getZip() {
        return zip;
    }
    
    
    
}
