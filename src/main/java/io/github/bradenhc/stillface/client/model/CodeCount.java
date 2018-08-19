package io.github.bradenhc.stillface.client.model;

public class CodeCount implements Comparable<CodeCount> {

	public String name = "";
	public int count = 0;

	public CodeCount() {
	}

	public CodeCount(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public int compareTo(CodeCount other) {
		return other.count - count;
	}

}
