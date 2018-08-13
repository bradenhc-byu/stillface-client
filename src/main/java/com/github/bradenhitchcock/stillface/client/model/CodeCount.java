package com.github.bradenhitchcock.stillface.client.model;

public class CodeCount implements Comparable<CodeCount>{

	public String name;
	public int count;
	
	public CodeCount(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	@Override
	public int compareTo(CodeCount other) {
		return other.count - count;
	}

}
