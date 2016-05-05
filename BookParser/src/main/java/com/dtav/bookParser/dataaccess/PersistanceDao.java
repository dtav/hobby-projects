package com.dtav.bookParser.dataaccess;


import java.util.List;

public interface PersistanceDao {
	
		
	public String getFileAbsolutePath();
	
	
	public long getFileLineLength();
	
	
	public String getFileName();
	
	
	public List<String> getFileLines();
	
	public void printFileWithNumberLines();
	
	public long getFileWordCount();
	
	public List<String> getFileWords();
	

}
