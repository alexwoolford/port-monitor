package io.woolford.portmonitor.database.mapper;

import io.woolford.portmonitor.database.entity.BatchRecord;
import io.woolford.portmonitor.database.entity.IpAddressRecord;
import io.woolford.portmonitor.database.entity.ScanResultRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DbMapper {

    @Select("SELECT                         " +
            "  ip_address AS ipAddress      " +
            "FROM port_monitor.ip_address   " +
            "ORDER BY INET_ATON(ip_address) ")
    public List<IpAddressRecord> getIpAddressRecords();

    @Insert("INSERT INTO batch                      " +
            "  (batch_id, start_time, end_time)         " +
            "VALUES                                 " +
            "  (#{batchId}, #{startTime}, #{endTime}) ")
    void insertBatchRecord(BatchRecord batchRecord);

    @Insert("INSERT INTO scan_result               " +
            "  (batch_id, ip_address, port)        " +
            "VALUES                                " +
            "  (#{batchId}, #{ipAddress}, #{port}) ")
    void insertScanResultRecord(ScanResultRecord scanResultRecord);


//    ScanResultRecord{ipAddress='10.0.1.1', port=541, isOpen=true}


}