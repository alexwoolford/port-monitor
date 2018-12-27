package io.woolford.portmonitor.database.service;

import io.woolford.portmonitor.database.entity.BatchRecord;
import io.woolford.portmonitor.database.entity.IpAddressRecord;
import io.woolford.portmonitor.database.entity.ScanResultRecord;
import io.woolford.portmonitor.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private DbMapper dbMapper;

    public List<IpAddressRecord> getIpAddressRecords() {
        return dbMapper.getIpAddressRecords();
    }

    public void inserBatchRecord(BatchRecord batchRecord) {
        dbMapper.insertBatchRecord(batchRecord);
    }

    public void inserScanResultRecord(ScanResultRecord scanResultRecord) {
        dbMapper.insertScanResultRecord(scanResultRecord);
    }

}
