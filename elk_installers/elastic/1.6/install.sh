#!/bin/sh

ln -sf /usr/share/zoneinfo/PST8PDT /etc/localtime

add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java8-installer

wget -qO - http://packages.elasticsearch.org/GPG-KEY-elasticsearch | sudo apt-key add -

echo "deb http://packages.elasticsearch.org/elasticsearch/1.6/debian stable main" >> /etc/apt/sources.list
apt-get update && apt-get install elasticsearch


update-rc.d elasticsearch defaults 95 10
cp elasticsearch.yml /etc/elasticsearch/elasticsearch.yml

#update memory, swapiness and filehandles
cp etc-default-elasticsearch /etc/default/elasticsearch
cp etc-security-limits.conf /etc/security/limits.conf

cd /usr/share/elasticsearch
bin/plugin -i elasticsearch/marvel/latest

sudo crontab -l > crontab.txt 
#echo new cron into cron file
echo "0 12 * * * find /var/log/elasticsearch -name \"elasticsearch.log.*\" -type f -mtime +2 -delete" >> crontab.txt
#install new cron file
crontab crontab.txt
