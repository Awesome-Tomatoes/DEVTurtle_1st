package com.sb.board;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Getter
//@Setter
//@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyboardVO2 {
	//ALt+SHIFT + Y(소문자)   ....   X(대문자)  	
	private int bseq;
	private String title;
	private String contents;
	private String regid;
	private String regdate;
	
	private ArrayList<MyreplyVO> rlist;
	
	
	
	

}
