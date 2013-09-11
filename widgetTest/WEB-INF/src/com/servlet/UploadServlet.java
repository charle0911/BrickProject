package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.*;


import com.model.*;

import toolkie.UploadTool;
import unzip.zipUtils;

public class UploadServlet  extends HttpServlet
{
	public void doPost (HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		HttpSession session =request.getSession();
		//System.out.println("in");   
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServiceInterface inter = new ServiceInterface();

		RequestDispatcher dispatcher = null;
		//判斷變數
		int jsJudge = 1;
		int txtJudge = 1;
		int jpgJudge = 1;
		int zipJudge = 1;
		int jsonJudge = 1;
		
		int brickNameExist = 0;
		session.setAttribute("jsonJudge","1");			//session 判別處
		session.setAttribute("zipJudge","1");			//session 判別處
		session.setAttribute("jsJudge","1");			//session 判別處
		session.setAttribute("txtJudge","1");			//session 判別處
		session.setAttribute("brickNameExist","0");			//session 判別處
		
		//抓取 使用者帳號以製造資料夾
		String id = (String)session.getAttribute("user_id");

		try {
			
			//抓取session中使用者id以創建資料夾
			UploadTool upload = new UploadTool(request);
		    //查詢是否錯誤
		    String msg = upload.checkUpload();
		    if (msg.length() > 0) {
		        System.out.println(msg);
		    } else {
		        //開始上傳
		        if (upload.isExtUpload("File1"))
		        {
		        	org.apache.commons.fileupload.FileItem item = upload.getUploadParameter("File1");
		        	//upload.setUploadDir();設定要存到那一個目錄,可以一直變
		        	System.out.println("File1 Name "+item.getName());//原上傳檔案名稱
		        	
					//製造資料夾
		        	
		        	//使用者個人 資料夾路徑
		        	String idDirPath = this.getServletContext().getRealPath("")+"\\upload_brick\\"+id;
		    		//Brick 資料夾路徑
		        	String brickNameDirPath = this.getServletContext().getRealPath("")+"\\upload_brick\\"+id+"\\"+upload.getParameter("BrickName")+"\\";
		        	//zip檔案放置資料夾路徑
		        	String zipDirPath = this.getServletContext().getRealPath("")+"\\upload_brick\\"+id+"\\"+upload.getParameter("BrickName")+"\\zipDir\\";
					//zip檔案路徑
		        	String zipPath = zipDirPath + upload.getParameter("BrickName") + ".zip";
		        	
		        	File existFile =  new File(brickNameDirPath);
		        	String judgeBrickString = inter.searchBrickForUpload(id, upload.getParameter("BrickName"));
		        	
		        	if(judgeBrickString.equals("exist")){
		    			brickNameExist = 1;
		    			session.setAttribute("brickNameExist","1");			//session 判別處
		    			dispatcher = request.getRequestDispatcher("/doupload.jsp");
						dispatcher.forward(request, response);
		        	}else if(judgeBrickString.equals("not exist") || judgeBrickString.equals("author")){
			    		String judgeDeleteDataBaseString;
		        		if(existFile.exists()){ 			//如果為作者則此行主以update他本來的brick檔案
		        			judgeDeleteDataBaseString = inter.deleteBrickToDatabase(id, upload.getParameter("BrickName"));//進行資料庫檔案刪除
			    			new DeleteFile(brickNameDirPath);//進行檔案刪除 以利於再次上傳
			    		}
				        	File idDir = new File(idDirPath);
							File brickNameDir =  new File(brickNameDirPath);
							File zipDir = new File(zipDirPath);
							idDir.mkdir();
							brickNameDir.mkdir();
							zipDir.mkdir();
							
							//設定檔案上傳路徑
							upload.setUploadDir(zipDirPath);
							//上傳
					        msg = upload.doUpload(item,item.getName());
							//查看目錄下檔案
							String[] filenames;
							filenames = zipDir.list();
							System.out.println("****檔案 =>" + filenames[0]);
							
							//抓曲目錄下副檔名
							String strFileName = filenames[0];
							if (strFileName.lastIndexOf(".") > 0) {
								//判定附檔名是否為zip
								if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("zip")){
									System.out.println("DDDD****檔案附檔名=> " + strFileName.substring(strFileName.lastIndexOf(".")+1));
									System.out.println("原來檔案位子 : " +zipDirPath +filenames[0]);
									System.out.println("修改後檔案位子 : " +zipDirPath + upload.getParameter("BrickName") + ".zip");
									zipJudge = 0;
									session.setAttribute("zipJudge","0");		//session 判別處
									new File(zipDirPath+filenames[0]).renameTo(new File(zipPath));
								}
							}
							//確認為zip後的動作
							if( zipJudge == 0){
								//進行上傳
								
								File targetZip=new File(zipPath);
								File extractDir= new File(brickNameDirPath);
								//進行解壓縮
								
								// *****************問題解壓縮無法關閉*以解決
								new zipUtils().unzipFile(targetZip, extractDir);
								
								
								//解壓縮後 檔案抓取
								String[] filenames2;
								filenames2 = brickNameDir.list();
								
								System.out.println("檔案數目 :" + filenames2.length );
								System.out.println("****路徑 : " +brickNameDirPath+ upload.getParameter("BrickName") + ".js");
								for(int i =0 ; i < filenames2.length ; i++){
									strFileName = filenames2[i];
									if (strFileName.lastIndexOf(".") > 0) {
										//修改 檔案名稱
										if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("js")){
											new File(brickNameDirPath+filenames2[i]).renameTo(new File(brickNameDirPath+ upload.getParameter("BrickName") + ".js"));
											jsJudge = 0;
											session.setAttribute("jsJudge","0");			//session 判別處
											}
										else if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("jpg")){
											new File(brickNameDirPath+filenames2[i]).renameTo(new File(brickNameDirPath+ upload.getParameter("BrickName") + ".jpg"));
											jpgJudge = 0;
										}
										else if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("txt")){
											new File(brickNameDirPath+filenames2[i]).renameTo(new File(brickNameDirPath+ upload.getParameter("BrickName") + ".txt"));
											txtJudge = 0;
											session.setAttribute("txtJudge","0");			//session 判別處
										}
									}
								}
								
								if(txtJudge ==0 && jsJudge ==0){//判斷檔案都有存在
									//**開始設置路徑
									String jsonUrl = brickNameDirPath+ upload.getParameter("BrickName") + ".txt";
									String jsUrl = brickNameDirPath+ upload.getParameter("BrickName") + ".js";
									String imgUrl;
									//讀取檔案裡的Json文件
									java.io.File brickJson = new java.io.File(jsonUrl);
									String charToString;
									BufferedReader br = new BufferedReader(new FileReader(brickJson));
									try {
								        StringBuilder sb = new StringBuilder();
								        String line = br.readLine();

								        while (line != null) {
								            sb.append(line);
								            sb.append('\n');
								            line = br.readLine();
								        }
								        charToString = sb.toString();
								    } finally {
								        br.close();
								    }

									
									//System.out.println(charToString);
									int JudgeJsonVariable = JudgeJson.isJson(charToString);
									if(JudgeJsonVariable == 4){
										System.out.println("Json is Correct"); 
										jsonJudge = 4;
										session.setAttribute("jsonJudge","4");			//session 判別處
										
										if(jpgJudge == 0){
											imgUrl = "upload_brick/"+id+"/"+upload.getParameter("BrickName")+"/"+ upload.getParameter("BrickName") + ".jpg";
										}else{
											imgUrl = "images/white.jpg";
										}
										JSONObject obj = new JSONObject();
										JSONObject obj2 = new JSONObject();
										JSONObject obj3 = new JSONObject();
										JSONObject obj4 = new JSONObject();
										JSONArray objArray = new JSONArray();
										JSONArray objArray2 = new JSONArray();
										JSONArray objArray3 = new JSONArray();
										JSONArray objArray4 = new JSONArray();
										obj.put("id","UploadTest");
										obj.put("sequence", 1);
										obj.put("name","UploadTest");
										obj.put("description", upload.getParameter("BrickDes"));
										obj.put("layout", "VerticalLayout");
										obj.put("plugins", objArray2);
										objArray.put(obj2);
										obj2.put("id", id+"_"+upload.getParameter("BrickName"));
										obj2.put("sequence",1);
										obj2.put("resourceBinding",objArray3);
										obj.put("bricks", objArray);
										objArray4.put(obj);
										obj3.put("view", objArray4);
										obj4.put("application", obj3);
										
										session.setAttribute("uploadApplicationJSON",obj4.toString());			//session 判別處
					
										System.out.println(obj4.toString());
										inter.insertBrickIntoDatabase(id,upload.getParameter("BrickDes"),upload.getParameter("BrickName"),charToString,upload.getParameter("BrickType"),imgUrl,jsUrl);
										
									}else{
										switch(jsonJudge){
											case 0:
												session.setAttribute("jsonJudge","0");	
												break;
											case 1:
												session.setAttribute("jsonJudge","1");	
												break;
											case 2:
												session.setAttribute("jsonJudge","2");	
												break;
											case 3:
												session.setAttribute("jsonJudge","3");	
												break;
										}
										new DeleteFile(brickNameDirPath);	
										System.out.println("Json is Fault"); 
									
									}
								
								
									dispatcher = request.getRequestDispatcher("/doupload.jsp");
									dispatcher.forward(request, response);
								}else{
									new DeleteFile(brickNameDirPath);					
									dispatcher = request.getRequestDispatcher("/doupload.jsp");
									dispatcher.forward(request, response);
								}
								
		
								/*if(jsonExist.exists() && imgExist.exists()){
									System.out.println(jsonUrl);//原上傳檔案名稱
									
									
									}*/
								
							}else{
								new DeleteFile(brickNameDirPath);	
								dispatcher = request.getRequestDispatcher("/doupload.jsp");
								dispatcher.forward(request, response);
							}
		        	}
		        }
		    }
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  
}
