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
		//�P�_�ܼ�
		int jsJudge = 1;
		int txtJudge = 1;
		int jpgJudge = 1;
		int zipJudge = 1;
		int jsonJudge = 1;
		
		int brickNameExist = 0;
		session.setAttribute("jsonJudge","1");			//session �P�O�B
		session.setAttribute("zipJudge","1");			//session �P�O�B
		session.setAttribute("jsJudge","1");			//session �P�O�B
		session.setAttribute("txtJudge","1");			//session �P�O�B
		session.setAttribute("brickNameExist","0");			//session �P�O�B
		
		//��� �ϥΪ̱b���H�s�y��Ƨ�
		String id = (String)session.getAttribute("user_id");

		try {
			
			//���session���ϥΪ�id�H�Ыظ�Ƨ�
			UploadTool upload = new UploadTool(request);
		    //�d�߬O�_���~
		    String msg = upload.checkUpload();
		    if (msg.length() > 0) {
		        System.out.println(msg);
		    } else {
		        //�}�l�W��
		        if (upload.isExtUpload("File1"))
		        {
		        	org.apache.commons.fileupload.FileItem item = upload.getUploadParameter("File1");
		        	//upload.setUploadDir();�]�w�n�s�쨺�@�ӥؿ�,�i�H�@����
		        	System.out.println("File1 Name "+item.getName());//��W���ɮצW��
		        	
					//�s�y��Ƨ�
		        	
		        	//�ϥΪ̭ӤH ��Ƨ����|
		        	String idDirPath = this.getServletContext().getRealPath("")+"\\upload_brick\\"+id;
		    		//Brick ��Ƨ����|
		        	String brickNameDirPath = this.getServletContext().getRealPath("")+"\\upload_brick\\"+id+"\\"+upload.getParameter("BrickName")+"\\";
		        	//zip�ɮש�m��Ƨ����|
		        	String zipDirPath = this.getServletContext().getRealPath("")+"\\upload_brick\\"+id+"\\"+upload.getParameter("BrickName")+"\\zipDir\\";
					//zip�ɮ׸��|
		        	String zipPath = zipDirPath + upload.getParameter("BrickName") + ".zip";
		        	
		        	File existFile =  new File(brickNameDirPath);
		        	String judgeBrickString = inter.searchBrickForUpload(id, upload.getParameter("BrickName"));
		        	
		        	if(judgeBrickString.equals("exist")){
		    			brickNameExist = 1;
		    			session.setAttribute("brickNameExist","1");			//session �P�O�B
		    			dispatcher = request.getRequestDispatcher("/doupload.jsp");
						dispatcher.forward(request, response);
		        	}else if(judgeBrickString.equals("not exist") || judgeBrickString.equals("author")){
			    		String judgeDeleteDataBaseString;
		        		if(existFile.exists()){ 			//�p�G���@�̫h����D�Hupdate�L���Ӫ�brick�ɮ�
		        			judgeDeleteDataBaseString = inter.deleteBrickToDatabase(id, upload.getParameter("BrickName"));//�i���Ʈw�ɮקR��
			    			new DeleteFile(brickNameDirPath);//�i���ɮקR�� �H�Q��A���W��
			    		}
				        	File idDir = new File(idDirPath);
							File brickNameDir =  new File(brickNameDirPath);
							File zipDir = new File(zipDirPath);
							idDir.mkdir();
							brickNameDir.mkdir();
							zipDir.mkdir();
							
							//�]�w�ɮפW�Ǹ��|
							upload.setUploadDir(zipDirPath);
							//�W��
					        msg = upload.doUpload(item,item.getName());
							//�d�ݥؿ��U�ɮ�
							String[] filenames;
							filenames = zipDir.list();
							System.out.println("****�ɮ� =>" + filenames[0]);
							
							//�즱�ؿ��U���ɦW
							String strFileName = filenames[0];
							if (strFileName.lastIndexOf(".") > 0) {
								//�P�w���ɦW�O�_��zip
								if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("zip")){
									System.out.println("DDDD****�ɮת��ɦW=> " + strFileName.substring(strFileName.lastIndexOf(".")+1));
									System.out.println("����ɮצ�l : " +zipDirPath +filenames[0]);
									System.out.println("�ק���ɮצ�l : " +zipDirPath + upload.getParameter("BrickName") + ".zip");
									zipJudge = 0;
									session.setAttribute("zipJudge","0");		//session �P�O�B
									new File(zipDirPath+filenames[0]).renameTo(new File(zipPath));
								}
							}
							//�T�{��zip�᪺�ʧ@
							if( zipJudge == 0){
								//�i��W��
								
								File targetZip=new File(zipPath);
								File extractDir= new File(brickNameDirPath);
								//�i������Y
								
								// *****************���D�����Y�L�k����*�H�ѨM
								new zipUtils().unzipFile(targetZip, extractDir);
								
								
								//�����Y�� �ɮק��
								String[] filenames2;
								filenames2 = brickNameDir.list();
								
								System.out.println("�ɮ׼ƥ� :" + filenames2.length );
								System.out.println("****���| : " +brickNameDirPath+ upload.getParameter("BrickName") + ".js");
								for(int i =0 ; i < filenames2.length ; i++){
									strFileName = filenames2[i];
									if (strFileName.lastIndexOf(".") > 0) {
										//�ק� �ɮצW��
										if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("js")){
											new File(brickNameDirPath+filenames2[i]).renameTo(new File(brickNameDirPath+ upload.getParameter("BrickName") + ".js"));
											jsJudge = 0;
											session.setAttribute("jsJudge","0");			//session �P�O�B
											}
										else if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("jpg")){
											new File(brickNameDirPath+filenames2[i]).renameTo(new File(brickNameDirPath+ upload.getParameter("BrickName") + ".jpg"));
											jpgJudge = 0;
										}
										else if(strFileName.substring(strFileName.lastIndexOf(".")+1).equals("txt")){
											new File(brickNameDirPath+filenames2[i]).renameTo(new File(brickNameDirPath+ upload.getParameter("BrickName") + ".txt"));
											txtJudge = 0;
											session.setAttribute("txtJudge","0");			//session �P�O�B
										}
									}
								}
								
								if(txtJudge ==0 && jsJudge ==0){//�P�_�ɮ׳����s�b
									//**�}�l�]�m���|
									String jsonUrl = brickNameDirPath+ upload.getParameter("BrickName") + ".txt";
									String jsUrl = brickNameDirPath+ upload.getParameter("BrickName") + ".js";
									String imgUrl;
									//Ū���ɮ׸̪�Json���
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
										session.setAttribute("jsonJudge","4");			//session �P�O�B
										
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
										
										session.setAttribute("uploadApplicationJSON",obj4.toString());			//session �P�O�B
					
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
									System.out.println(jsonUrl);//��W���ɮצW��
									
									
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
