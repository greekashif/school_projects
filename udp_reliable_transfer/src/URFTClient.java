import java.io.File; 
import java.io.RandomAccessFile; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketTimeoutException; 
 
  
public class URFTClient { 
  
    public static void main(String[] args) throws Exception { 
  
    	int ack = 0; 
    	int seqNum = 0; 
    	final  int MTU = 512; 
    	  
    	byte[] sendData = new byte[512]; 
    	byte[] rcvData = new byte[512]; 
    	
    	int destPort = Integer.parseInt(args[3]);
        InetAddress destIP = InetAddress.getByName(args[1]);
        File file = new File(args[5]);
        long fileSize = file.length();
		System.out.println("File size = " + fileSize + " bytes");
		
        DatagramPacket sendPacket = new DatagramPacket(sendData, MTU, destIP, destPort); 
        DatagramPacket rcvPacket = new DatagramPacket(rcvData, MTU); 
		
		DatagramSocket socket = new DatagramSocket();
 
        sendData = (seqNum + "\r\n" + file.getName() + "\r\n" + fileSize + "\r\n").getBytes();
        
        sendPacket.setData(sendData); 
        socket.setSoTimeout(1000); 
        socket.send(sendPacket); 
                
        boolean timedOut = true; 
        
        while (timedOut) { 
        	try { 
                socket.receive(rcvPacket); 
                System.out.println("Packet received! (1)"); 
                timedOut = false; 
            } 
            catch (SocketTimeoutException e) { 
                timedOut = true; 
                System.out.println("Timed out (1)"); 
                socket.send(sendPacket);
                System.out.println("Sending initial packet again");
                socket.setSoTimeout(1000);
            } 
        } 
        
        int numOfSegments;
        if((fileSize % 512) == 0) 
        	numOfSegments = (int) (fileSize / 512);
        else 
        	numOfSegments = (int) Math.ceil(fileSize / 512.0);
        
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        int offset = 0;
        byte[] buf = new byte[(int) fileSize];
        
        for(int i = 0; i < numOfSegments; i++) {
	       	offset = seqNum * MTU;
        	
        	if(i == numOfSegments - 1) 
        		raf.read(buf, offset, MTU % 512);
        	else 
        		raf.read(buf, offset, MTU);
        	
        	System.out.println("Sending packet " + seqNum);
        	sendData = (seqNum + "\r\n" + buf).getBytes();
        	sendPacket.setData(sendData);
        	socket.send(sendPacket);
        	seqNum++;
        }
        
        
        boolean done = false;
        while(!done) {
	        
	        try {
	        	socket.receive(rcvPacket);
				ack = Integer.parseInt(new String(rcvPacket.getData()).trim());
	        	System.out.println("ACK packet received\nACK is " + ack);
	        }
	        catch(SocketTimeoutException e) {
	        	
	        	System.out.println("Timeout.  Resending");
	        	seqNum = 0;
	        	for(int k = 0; k < numOfSegments; k++) {
	        		
	        		//buf = new byte[(int) fileSize];
	            	offset = seqNum * MTU;  //wrong?
	            	
	            	if(k == numOfSegments - 1) 
	            		raf.read(buf, offset, MTU % 512);
	            	else 
	            		raf.read(buf, offset, MTU);
	            	
	            	System.out.println("Resending packet " + seqNum);
	            	sendData = (seqNum + "\r\n" + buf).getBytes();
	            	sendPacket.setData(sendData);
	            	socket.send(sendPacket);
	            	seqNum++;
	        	}
	        	//continue;
	        
	        
	        if(ack != numOfSegments) {
	        	
	        	System.out.println("Last ACK not received.  Resending subsequent packets.");
	        	
	        	for(int k = ack; k < numOfSegments; k++) {
	        		
	        		seqNum = ack;
	        		offset = ack * MTU;
	        		if(k == numOfSegments - 1) 
	            		raf.read(buf, offset, MTU % 512);
	            	else 
	            		raf.read(buf, offset, MTU);
	        		sendData = (seqNum + "\r\n" + buf).getBytes();
	        		sendPacket.setData(sendData);
	        		socket.send(sendPacket);
	        	}
	        }
	        if(ack == numOfSegments) {
	        	System.out.println("All packets received correctly!"); 
	        	seqNum = -1;
	        	sendData = (seqNum + "\r\n" + buf).getBytes();
            	sendPacket.setData(sendData);
               	socket.send(sendPacket);
	        	done = true;
	        }	
        
	        }
        }   
        
        System.out.println("Socket closed!!!!!!! :D");
        raf.close();
        socket.close();
    } 
} 