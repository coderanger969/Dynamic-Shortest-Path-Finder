



import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class BellRecv implements Runnable {

	
	DatagramSocket serverSocket;
    ByteArrayInputStream bais1;
 	DataInputStream dais1;
    
   Vertex n;
   byte[] recData;
   byte[] recData1;
	
	public BellRecv(int portno,Vertex k)
	{
		try {
			serverSocket = new DatagramSocket(portno);
			n=k;
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//recData = new byte[18];
		//recData1 = new byte[18];
		
		Thread t= new Thread(this);
		t.start();
		
	}
	
	public void run(){
	   
	  DatagramPacket recPacket;
	  try{   
	  while(true)
	  {
		  
		  byte[] recData= new byte[18];;
		    byte[] recData1= new byte[18];;
		  
		  
		  //System.out.println("inside recieve ack while $$$$$ ");
		  recPacket = new DatagramPacket(recData, recData.length);
	    
			serverSocket.receive(recPacket);
		
	 
	     
	    //###  bais1 = new ByteArrayInputStream(recPacket.getData());
	     // ###  dais1 = new DataInputStream(bais1);
	       
				// dais1.readFully(b, off, len)
			
	         
	        	//### dais1.read(recData1);
	        	
	        	//System.out.println("byte  iss    " + recData1 );
	        
	        //String s1 = dais1.readUTF();
		     
	        	//String s1 = recData1.toString();
	        	//### String s1 = new String(recData1,0,recPacket.getLength());
	        	String s1 = new String(recPacket.getData(),0,recPacket.getLength());
	        	//System.out.println("String iss    " + s1);
	        	
		     String[] result = s1.split("#");
		     
		     
		
		     String Nname =result[0];
		     //System.out.println("string res 0   "+ result[0]);
		     String [] result2 = Nname.split(""); 
		      int i = Integer.parseInt(result2[2]);
		     
		     for(int l=1;l<5;l++){
		    	 
		    	// System.out.println("i=   " + i+"   l=  "+ l);
		    	// System.out.println(result[l]);
		    	 
		    	 n.Dtable[i][l]=Double.parseDouble(result[l]);
		    		 
		    		 
		    	     	 
		    	 
		    	 
		     }
		     
		     n.flag= true;
		     
		     
		     
	       

	  }
	  
	  }
	  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

