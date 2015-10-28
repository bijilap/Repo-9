git: https://github.paypal.com/akokarski/installers/tree/master/elastic


# installer for elasticsearch

1. install.sh - installs java, and other prerequisites for elastic search.

installs elasticsearch as a service and sets it up as a service.

2. yml files are different flavors of elastic search.  -master is a master node without data and -data is datanode.
-single is a single node config

discovery is managed using zen algorithm, which means that unicast ips need to be managed in yml files on nodes. :/

3. to install 
`
$ sudo -s
$ ./install.sh
`


# Performance Optimizations

Since we are not using SSD, lower threads to lower concurrent io

`index.merge.scheduler.max_thread_count: 1`

Lower index refresh time as we are updating index once a minute and in large quantaties..

`index.refresh_interval: 60s`


Source: http://www.elasticsearch.org/guide/en/elasticsearch/guide/current/indexing-performance.html

