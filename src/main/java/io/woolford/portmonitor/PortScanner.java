package io.woolford.portmonitor;

import io.woolford.portmonitor.database.entity.BatchRecord;
import io.woolford.portmonitor.database.entity.IpAddressRecord;
import io.woolford.portmonitor.database.entity.ScanResultRecord;
import io.woolford.portmonitor.database.mapper.DbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@Component
public class PortScanner {

    Logger logger = LoggerFactory.getLogger(PortScanner.class);

    @Autowired
    DbMapper dbMapper;

    @PostConstruct
    private void scanPorts() throws ExecutionException, InterruptedException {

        BatchRecord batchRecord = new BatchRecord();
        String batchId = String.valueOf(UUID.randomUUID());
        batchRecord.setBatchId(batchId);
        batchRecord.setStartTime(new Date());

        final ExecutorService es = Executors.newFixedThreadPool(100);
        final int timeout = 200;
        final List<Future<ScanResultRecord>> futures = new ArrayList<>();

        for (IpAddressRecord ipAddressRecord : dbMapper.getIpAddressRecords()){

            for (int port = 1; port <= 65535; port++) {
                futures.add(portIsOpen(es, ipAddressRecord.getIpAddress(), port, timeout));
            }

        }
        es.shutdown();
        for (final Future<ScanResultRecord> f : futures) {
            if (f.get().isOpen()){
                ScanResultRecord scanResultRecord = new ScanResultRecord();
                scanResultRecord.setBatchId(batchId);
                scanResultRecord.setIpAddress(f.get().getIpAddress());
                scanResultRecord.setPort(f.get().getPort());
                scanResultRecord.setOpen(f.get().isOpen());
                dbMapper.insertScanResultRecord(scanResultRecord);
                logger.info(scanResultRecord.toString());
            }
        }

        batchRecord.setEndTime(new Date());
        dbMapper.insertBatchRecord(batchRecord);

    }


    private static Future<ScanResultRecord> portIsOpen(final ExecutorService es, final String ipAddress, final int port, final int timeout) {
        return es.submit(new Callable<ScanResultRecord>() {
            @Override public ScanResultRecord call() {
                ScanResultRecord scanResultRecord = new ScanResultRecord();
                scanResultRecord.setIpAddress(ipAddress);
                scanResultRecord.setPort(port);
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ipAddress, port), timeout);
                    socket.close();
                    scanResultRecord.setOpen(true);
                    return scanResultRecord;
                } catch (Exception ex) {
                    scanResultRecord.setOpen(false);
                    return scanResultRecord;
                }
            }
        });
    }

}
