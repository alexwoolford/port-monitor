package io.woolford.portmonitor.database.entity;

public class IpAddressRecord {

    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "IpAddressRecord{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }


}
