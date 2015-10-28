
echo 'deb http://packages.elasticsearch.org/logstash/1.5/debian stable main' | sudo tee /etc/apt/sources.list.d/logstash.list
sudo apt-get update
sudo apt-get install logstash

# configuring logstash to recieve params from tabinight
cp ../conf/logstash.conf /etc/logstash/conf.d/ 
