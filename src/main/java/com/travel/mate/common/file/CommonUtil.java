package com.travel.mate.common.file;

import java.util.UUID;

public class CommonUtil {

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}