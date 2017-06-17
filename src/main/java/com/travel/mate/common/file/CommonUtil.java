/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 06. 07
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

package com.travel.mate.common.file;

import java.util.UUID;

public class CommonUtil {

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}