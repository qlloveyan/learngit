package com.surfilter.fr;

public class Main {

	public static void main(String[] args) {
		RedisClient rc = new RedisClient();
//		rc.keyOperate();
//		rc.stringOperate();
//		rc.listOperate();
//		rc.setOperate();
//		rc.sortedOperate();
		rc.hashOperate();
	}

}
