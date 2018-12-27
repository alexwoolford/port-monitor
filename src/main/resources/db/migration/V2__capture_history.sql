
CREATE TABLE batch (
  batch_id VARCHAR(40),
  start_time TIMESTAMP NULL DEFAULT NULL,
  end_time TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE scan_result (
  batch_id VARCHAR(40),
  ip_address VARCHAR(255),
  port INTEGER
);
