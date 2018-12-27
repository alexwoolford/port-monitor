# port-monitor

This small Spring app periodically performs a complete port scan of all the hosts specified in the `ip_address` table. The results of the port scan are stored in the `scan_result` table. 

The purpose of this is to capture the history of all the open ports, for each host.
