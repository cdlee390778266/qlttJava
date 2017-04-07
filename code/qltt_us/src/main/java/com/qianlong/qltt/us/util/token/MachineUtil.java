package com.qianlong.qltt.us.util.token;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MachineUtil {

	 public static List<String> getMacAddress() {  
	        InetAddress ip = null;  
	        NetworkInterface ni = null;  
	        List<String> macList = new ArrayList<String>();  
	        try {  
	            Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface  
	                    .getNetworkInterfaces();  
	            while (netInterfaces.hasMoreElements()) {  
	                ni = (NetworkInterface) netInterfaces .nextElement();  
	                // ----------特定情况，可以考虑用ni.getName判断  
	                // 遍历所有ip  
	                Enumeration<InetAddress> ips = ni.getInetAddresses();  
	                while (ips.hasMoreElements()) {  
	                    ip = (InetAddress) ips.nextElement();  
	                    if (!ip.isLoopbackAddress() //非127.0.0.1  
	                            && ip.getHostAddress().matches(  
	                                    "(\\d{1,3}\\.){3}\\d{1,3}")) {  
	                        macList.add(getMacFromBytes(ni.getHardwareAddress()));  
	                    }  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	        }  
	        return macList;  
	    }  
	 
	 private static String getMacFromBytes(byte[] bytes) {  
	        StringBuffer mac = new StringBuffer();  
	        byte currentByte;  
	        boolean first = false;  
	        for (byte b : bytes) {  
	            if (first) {  
	                mac.append("-");  
	            }  
	            currentByte = (byte) ((b & 240) >> 4);  
	            mac.append(Integer.toHexString(currentByte));  
	            currentByte = (byte) (b & 15);  
	            mac.append(Integer.toHexString(currentByte));  
	            first = true;  
	        }  
	        return mac.toString().toUpperCase();  
	    }  

}
