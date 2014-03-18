package com.cardinfolink.vtsh.util;

import java.util.UUID;

public class RandomMd5Util {
	/**
	 * create a random 32 string as md5.
	 * @return md5
	 */
	public static String getRandomMd5Code() {
	      return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
