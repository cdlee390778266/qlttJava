package com.qianlong.qltt.us.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.qianlong.qltt.us.service.ICommService;


@Service("commService")
public class CommServiceImpl implements ICommService{

	public Timestamp getSystemDate() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(new CommServiceImpl().getSystemDate());
	}
}
