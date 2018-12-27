package io.woolford.portmonitor.database.entity;

public class ScanResultRecord {

    private String batchId;
    private String ipAddress;
    private int port;
    private boolean isOpen;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "ScanResultRecord{" +
                "batchId='" + batchId + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", isOpen=" + isOpen +
                '}';
    }

}
