package com.sb.board;

public class VoCallTest {

	
	public static void main(String[] args) {
		MyboardVO vo =  new MyboardVO(1,"a","b","c","d");  	//@AllArgsConstructor
		System.out.println(vo.toString());					//@ToString **
		
		MyboardVO vo2 =  new MyboardVO();					//@NoArgsConstructor
//		vo2.setBseq(1);										//@Setter **
//		System.out.println(vo2.getBseq());					//@Getter **   @Data

	}
}
