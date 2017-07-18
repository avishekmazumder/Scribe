package com.avi.main;

import com.avi.service.FileUtils;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		String dealXml = "<A><B><C><C1><C11>something</C11><C12>something</C12></C1></C><D><D1><D11><D111 operation=\"create\"><Node>something else</Node></D111></D11></D1><D2><D21></D21></D2></D></B></A>";
		FileUtils.generateDealXml(dealXml);
		
		System.out.println("File Created");
	}

}
