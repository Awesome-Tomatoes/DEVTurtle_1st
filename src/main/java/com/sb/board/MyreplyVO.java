package com.sb.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyreplyVO {
	
	private int rseq;
	private String reply;
	private String regid;
	private String regdate;
	private int bseq;
	
	
}
