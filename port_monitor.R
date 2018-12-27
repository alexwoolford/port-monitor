
rm(list = ls())

library(RMySQL)
library(ggplot2)
library(dplyr)
library(data.table)

con <- dbConnect(RMySQL::MySQL(), dbname = "port_monitor", user = "root", password = "V1ctoria", host = "deepthought")
data <- dbReadTable(con, "scan_result")
dbDisconnect(con)

data$batch_id <- NULL

data <- mutate(data, record=1)

data <- dcast(data, ip_address ~ port, fun=sum)

data_hclust <- hclust(dist(data))

cluster <- cutree(data_hclust, k=10)

data <- cbind(data, cluster)

table(data$cluster)

subset(data, cluster == 10)$ip_address
