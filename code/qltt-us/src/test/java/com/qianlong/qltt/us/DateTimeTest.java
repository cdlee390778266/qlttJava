package com.qianlong.qltt.us;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.qltt.us.domain.TUSAcctCNReg;
import com.qianlong.qltt.us.mapper.TUSAcctCNRegMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring-qltt-us.xml"})
public class DateTimeTest {
	
	@Resource
	private TUSAcctCNRegMapper tUSAcctCNRegMapper;
	
	 @Test
	 public void testDateTime(){
			TUSAcctCNReg tUSAcctCNReg = new TUSAcctCNReg();
			tUSAcctCNReg.setFiStatus(1);
			tUSAcctCNReg.setFsCn(String.valueOf(new Date().getTime()));
			tUSAcctCNReg.setFsTtacct(String.valueOf(new Date().getTime()));
			tUSAcctCNReg.setFtRegtime(new Timestamp(new Date().getTime()));
			tUSAcctCNReg.setFtUpdtime(new Timestamp(new Date().getTime()));
			tUSAcctCNRegMapper.insert(tUSAcctCNReg);
	 }
}
