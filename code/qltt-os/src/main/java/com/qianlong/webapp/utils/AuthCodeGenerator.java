package com.qianlong.webapp.utils;

import java.util.Calendar;
import java.util.Random;

public class AuthCodeGenerator {

	public static String produceAuthCode(int length) {
		Random rand = new Random(Calendar.getInstance().getTimeInMillis());
		char seq[] = new char[length];
		for (int i = 0; i < length; i ++) {
			int digit = rand.nextInt(9) + 48;
			seq[i] = (char)digit;
		}
		return String.valueOf(seq);
	}
}
