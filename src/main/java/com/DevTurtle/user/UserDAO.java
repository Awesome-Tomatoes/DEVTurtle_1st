package com.DevTurtle.user;

import java.util.ArrayList;

public class UserDAO {

	public UserVO select(String userID) {
		return new UserVO(1,"name","id","pw","nickname","create","update","solved","git","userbio",1000,300,700);
	}
	public ArrayList<UserVO> select() {
		ArrayList<UserVO> alist = new ArrayList<UserVO>();
		alist.add(new UserVO(1,"name1","id1","pw1","nickname1","create1","update1","solved1","git1","userbio",1000,300,700));
		alist.add(new UserVO(2,"name2","id2","pw2","nickname2","create2","update2","solved2","git2","userbio",1200,500,700));
		alist.add(new UserVO(3,"name3","id3","pw3","nickname3","create3","update3","solved3","git3","userbio",1100,500,600));
		alist.add(new UserVO(4,"name4","id4","pw4","nickname4","create4","update4","solved4","git4","userbio",940,510,430));
		return alist;
	}
}
