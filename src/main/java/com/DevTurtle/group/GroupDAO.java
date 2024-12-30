/**
 * 
 */
package com.DevTurtle.group;

import java.util.ArrayList;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2024. 12. 30.<br>
 * History :<br>
 * - 작성자 : user, 날짜 : 2024. 12. 30., 설명 : 최초작성<br>
 *
 * @author user
 * @version 1.0
 */
public class GroupDAO {

	// 한그룹에 대한 정보
	public GroupVO select(int groupID) {	
		return new GroupVO(groupID,"멋쟁이그룹","설명","스터디","공개",50000,60,"2024-12-12","2024-12-12"); 
	}
	
	// 전체그룹 리스트 정보
	public ArrayList<GroupVO> selectAll() {
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();
		alist.add(new GroupVO(1,"group1","설명1","스터디","공개",50000,60,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(2,"group2","설명2","스터디","공개",40000,70,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(3,"group3","설명3","스터디","공개",60000,50,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(4,"group4","설명4","스터디","공개",80000,30,"2024-12-12","2024-12-12"));	
		
		return alist;
	
	}
	

	
	
	
}
