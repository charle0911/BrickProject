package com.model;

import java.io.File;

public class DeleteFile {
	private File path ;
	public DeleteFile(String pathString){
		path = new File(pathString);
		deleteAll(path);
	}
	 public void deleteAll(File path) {
		    System.out.println("�i�J�禡" + path.getName());
	        if (!path.exists()) {
	            return;
	        }
	        if(path.isDirectory()){
	        	System.out.println("��Ƨ�=>" + path.getName());
	        	File[] files = path.listFiles();
		        for (int i = 0; i < files.length; i++) {
		        	System.out.println("�R���ʧ@=>" + path.getName());
		        	deleteAll(files[i]);
		        }
	        }
	        else if(path.isFile()) {
	        	System.out.println("File=>" + path.getName());
	            path.delete();
	            return;
	        }
	        
	        System.out.println("�H�R��=>" + path.getName());
	        path.delete(); 
	    }
}
