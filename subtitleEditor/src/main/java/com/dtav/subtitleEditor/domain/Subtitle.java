package com.dtav.subtitleEditor.domain;

public class Subtitle {
	private String name;
	private String path;
	private long size;
	
	public Subtitle(){
		
	}
	
	public Subtitle(String name, String path, long size){
		this.name = name;
		this.path = path;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	

}
