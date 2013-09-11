package com.model;

import java.io.File;

public class DeleteFile {
	private File path ;
	public DeleteFile(String pathString){
		path = new File(pathString);
		deleteAll(path);
	}
	 public void deleteAll(File path) {
		    System.out.println("進入函式" + path.getName());
	        if (!path.exists()) {
	            return;
	        }
	        if(path.isDirectory()){
	        	System.out.println("資料夾=>" + path.getName());
	        	File[] files = path.listFiles();
		        for (int i = 0; i < files.length; i++) {
		        	System.out.println("刪除動作=>" + path.getName());
		        	deleteAll(files[i]);
		        }
	        }
	        else if(path.isFile()) {
	        	System.out.println("File=>" + path.getName());
	            path.delete();
	            return;
	        }
	        
	        System.out.println("以刪除=>" + path.getName());
	        path.delete(); 
	    }
}
