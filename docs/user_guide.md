# USER-GUIDE
## Predefined dashboards and graphs
-----------------------------------------
User installing new relic plugin for aerospike will get some per-defined dashboards and summary charts out of the box.
These include following dashboards.

   1. Overview :  This Dashboard shows per cluster stats graph. Added graphs are given below.
	   * Cluster Size
	   * Reads, Writes
	   * Used memory, disk
   2. Node Statistics : This Dashboard shows per node stats graph. Added graphs are given below.
	   * Reads, writes
	   * Used memory, disk
	   * Client connection
           * Node uptime
	   * Migration outgoing remaining(Empty for Aerospike Server > 3.9)
	   * Objects
	   * Basic scan succeeded (Empty for Aerospike Server > 3.9)
	   * Batch initiate
   3. Namespaces : This Dashboard shows per node per namespace stats graph. Added graphs are given below.
	   * Used memory, disk (Empty for Aerospike Server < 3.9)
	   * Master, replica objects
	   * Expired, evicted objects
	   * Scan completed (Empty for Aerospike Server < 3.9)
	   * Batch Read records (Empty for Aerospike Server < 3.9)
	   * Migration Incoming remaning, Migration Outgoing remaining. (Empty for Aerospike Server < 3.9)

   4. Latency : This Dashboard shows per cluster latency stats graph. Added graphs are given below. Aerospike Server > 3.9 give latency at namespace level.
	   * Read, writes
	   * UDF, Query, Proxy
	 
Note :
	These predefined Dashboards shows all important information. Above it users can add their own custom Dashboard as per requirenment.


## Creation of custom Dashboards and graphs
---------------------------------------------------
Users can create custom dashboards using the data pushed by plugin. Custom dashboards usage might be restricted to certain types of new relic account types.

For creating custom Dashboard:
   * Click "Create custom dashboard" under Tools tab.
	 1. Tools -> Create custom dashboard
	 2. Choose a layout (Overview or Grid).
	 3. For Adding any new Chart or table
	    1. Click on “Add chart or table”.
	    2. Choose Agent type “Aerospike”.
	    3. Choose Component (For which you are adding dashboard).
	    4. Choose Visualizer(Chart or table)
	    5. Insert the required form fields
	       1. Title
	       2. Metric: Metric name would be same as the metric name pushed from plugin. check metric naming section(e.g; Component/aerospike/summary/used_memory[])
	       3. Value (select average value)
	       4. Chart type(Line or stacked area)

Users can add their own custom charts in these custom dashboards.


## Aerospike Metric Categories (Metric naming)
---------------------------------------------------
Plugin is used to push metrics in defined hierarchy/categories in order to distinguish stats viz; node, namespace, cluster stats.  
Categories are :

   1. Summary : In this category stats per cluster are getting pushed. Metrics are pushed in the following manner
	   * Component/aerospike/summary/{stat}
	       * Component/aerospike/summary/cluster_size[]
	       * Component/aerospike/summary/used_bytes_memory[]
	   * Component/aerospike/summary/{stat category}/{stat}
	       * Component/aerospike/summary/reads/success[]
	       * Component/aerospike/summary/writes/success[]
	   * Component/aerospike/summary/latency/{stat category}/{stat subcategory}/{stat}
	       * Component/aerospike/summary/latency/read/0ms_to_1ms/value[]
	       * Component/aerospike/summary/latency/udf/0ms_to_1ms/value[]
	   * Component/aerospike/summary/latency/{stat category-{namespace}}/{stat subcategory}/{stat}
	       * Component/aerospike/summary/latency/read-{test}/0ms_to_1ms/value[]

   2. NodeStats: In this category stats per node are getting pushed. Metrics are pushed in the following manner
	   * Component/aerospike/nodeStats/{Node_IP}/{stat}
	       * Component/aerospike/nodeStats/{Node_IP}/used_bytes_disk[]
	   * Component/aerospike/throughputStats/{Node_IP}/{stat category}/{stat}
	       * Component/aerospike/throughputStats/{Node_IP}/reads/success[]

   3. NamespaceStats: In this category stats per namespace are getting pushed. Metrics are pushed in the following manner
	   * Component/aerospike/namespaceStats/{Node_IP}/{namespace}/{stat}
	       * Component/aerospike/namespaceStats/{Node_IP}/{namespace}/memory_used_bytes[]
	       * Component/aerospike/namespaceStats/{Node_IP}/{namespace}/master_objects[]

   4. LatencyStat: In this category latency stats per node are getting pushed. Aerospike Server > 3.9 give latency per node per namespace. Metrics are pushed in the following manner
	   * Component/aerospike/latencyStats/{Node_IP}/{stat category}/{stat subcategory}/{stat}
	       * Component/aerospike/latencyStats/{Node_IP}/write/0ms_to_1ms/value[]
	   * Component/aerospike/latencyStats/{Node_IP}/{stat category-{namespace}}/{stat subcategory}/{stat}
	       * Component/aerospike/latencyStats/{Node_IP}/write-{test}/0ms_to_1ms/value[]

   5. ThroughputStat:  In this category throughput stats (reads and writes) per node are getting pushed. Metrics are pushed in the following 
	   * Component/aerospike/throughputStats/{Node_IP}/{stat category}/{stat}
	       * Component/aerospike/throughputStats/{Node_IP}/reads/success[]
	        
Note :
	User can choose from the above mentioned categories in order to create custom dashboards.
* {Node_IP} is the node IP, present within a cluster.
* {namespace} is the namespace name(e.g; test, bar) of a node.
* {stat} is the stat metric name. There are many stats pushed from newrelic plugin, User can find all the stat metric name here (http://www.aerospike.com/docs/reference/metrics/) . In the given link NodeStats are under {Statistics} category and NamespaceStats are under {Namespace} category.

## Using wildcard "*" for grouping metrics
--------------------------------------------
While creating custom dashboards, user can group metrics using wildcard"*".  
For example .

1. If user wants to plot "used_bytes_memory" for all nodes in a single graph, then this can be done in the following way:
	* Component/aerospike/nodeStats/*/used_bytes_memory[]
2. To monitor similar stats of a node like batch_initiate, batch_error etc refer to the following format:
	* Component/aerospike/nodeStats/{Node_IP}/batch*
3. Any nodeStat of all nodes in cluster can be monitored in following way.
	* Component/aerospike/nodeStats/*/{stats}
4. Specific namespace stats for all nodes in cluster can be monitored in following way.
	* Component/aerospike/namespaceStats/*/{namespace}/{stats}
5. All namespace stats for all nodes in cluster can be monitored in following way.
	* Component/aerospike/namespaceStats/*/{stats}
	
Note :
* This wild card uses are as per newrelic metric naming rules.

## Limitations
--------------
* At present we don’t push any XDR stats.
* We also don't push any config.
	            
