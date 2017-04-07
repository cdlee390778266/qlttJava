package com.qianlong.qltt.us.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.qianlong.qltt.us.service.ICommService;


@Service("commService")
public class CommServiceImpl implements ICommService{

	public Date getSystemDate() {
		return new Date();
	}

}
