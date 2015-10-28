#!/bin/sh


wget https://download.elastic.co/kibana/kibana/kibana-4.1.0-linux-x64.tar.gz
tar -xvf kibana-4.1.0-linux-x64.tar.gz


sudo cp -R kibana-4.1.0-linux-x64 /opt/kibana

#update config
sudo cp kibana.yml /opt/kibana/config/kibana.yml
sudo cp kibana /etc/init.d/kibana

sudo chmod 755 /etc/init.d/kibana
sudo update-rc.d kibana defaults 98 02

sudo service kibana start
