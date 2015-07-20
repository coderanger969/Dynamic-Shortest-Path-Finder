****************************************LINK-STATE & DISTANCE-VECTOR ROUTING ALGORITHMS ***************************************
We have three folders:
1.Dijkstra
2.BellmanFord
3.BellmanFordExtra


*******************************************************Dijkstra Folder*************************************************************
Dijkstra Folder: This folder has the following .java files.
1. Dijkstra.java


***************************************************BellmanFord Folder*************************************************************
This folder has the following .java files

1.BellmanFord .java 


***************************************************BellmanFordExtra Folder*************************************************************
This folder has the following .java files
1.BellmanExt.java
2.BellmanExt2.java
3.BellmanExt3.java
4.BellmanExt4.java
5.BellRecv.java

In BellmanExt.java, BellmanExt2.java, BellmanExt3.java, BellmanExt4.java, IP addresses must be changed at lines 81,98,99,100 and 101
to the IP addresses of your machines, where your four nodes are placed.
 

****************************************************PROCESS OF EXECUTION*********************************************************

1. Run the Dijkstra.java file and enter the input command as :
  
    link_state filename node1 node2 


2.Run the BellmanFord .java  file and enter the input command as :

    distance_vector <initial-node> file-name node1 node2


3. For the Extra credit , We are implementing the DV algorithm over UDP, with 4 nodes.

Each node will have DVRecv thread to recieve the DV vector send by neighbouring nodes.

**change the IP address to the Ip address in your network, as already mentioned above.

Here the Triggering node which sends the Distance Vector first is BellmanExt.java.

So first Run the BellmanExt2.java ,BellmanExt3.java ,BellmanExt4.java ,and then run BellmanExt.java




You can find the Routing tables in the console.

















