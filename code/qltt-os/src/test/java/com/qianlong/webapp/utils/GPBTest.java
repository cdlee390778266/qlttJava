package com.qianlong.webapp.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.qlcd.qltt.body.Bpem.PEB_SVCCHNL;
import com.qlcd.qltt.body.pvt.T01001001;


public class GPBTest {

	public static void main(String[] args) {
	
		T01001001._acctbindinfo.Builder abiBuilder = T01001001._acctbindinfo.newBuilder();
		abiBuilder.setBindacct("1111111");
		abiBuilder.setSvcchnl(PEB_SVCCHNL.EV_WXCHNL);
		abiBuilder.setSvcchnlValue(111);
		
		T01001001._req.Builder reqBuilder = T01001001._req.newBuilder();
		reqBuilder.setCN("1111");
		reqBuilder.setAbi(abiBuilder.build());
		
		T01001001._req reqobj = reqBuilder.build();
		Class<?> clazzr;
		try {
			
			Class<?> clazz = Class.forName(reqobj.getClass().getName());
			Method toByteArray = clazz.getMethod("toByteArray", null);
			byte[] reqBodyByte = (byte[]) toByteArray.invoke(reqobj);
			
			
			
			clazzr = Class.forName("com.qlcd.qltt.body.prt.T01001001$_req");
			Method parseFrom = clazzr.getMethod("parseFrom", byte[].class);
			Object reqBody = parseFrom.invoke(clazzr, reqBodyByte);
			
			T01001001._req reqrs = (T01001001._req)reqBody;
			System.out.println(reqrs.getCN());
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub

	}

}
