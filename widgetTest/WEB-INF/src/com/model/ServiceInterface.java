package com.model; 

import java.sql.Connection; 
import java.sql.Date;
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceInterface
{
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/brick_project?useUnicode=true&characterEncoding=UTF-8";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	private Connection dbConnection = null;
	private PreparedStatement pStat = null;
	private String selectSQL = null;
	private ResultSet rs = null;
	public ServiceInterface() 
	{
		try
		{
			Class.forName(DB_DRIVER);	 
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException:"+e.toString());
		}
 
		try
		{	 
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
		}
		catch (SQLException e)
		{
			System.out.println("SQLException DATABASE帳號密碼輸入錯誤:"+e.toString());
		}	 
			
	}
	
	public String getKernalResourceProvider(String resourceType)
	{

		String selectSQL = "SELECT brick_data FROM brick ";
		JSONArray urlArray = new JSONArray();
		JSONObject outerObj = new JSONObject();
		try
		{
			pStat = dbConnection.prepareStatement(selectSQL);	
			rs = pStat.executeQuery();
			String rType = "";
			while(rs.next()) 
			{
				JSONObject brick_data = new JSONObject(rs.getString ("brick_data"));
				JSONArray kernalResourceProvider = new JSONArray(brick_data.get("kernalResourceProvider").toString());
				for(int i=0; i<kernalResourceProvider.length(); i++)
				{
					rType = kernalResourceProvider.getJSONObject(i).get("type").toString();
					if(rType.equals(resourceType))
					{
						JSONObject obj = new JSONObject();
						String kUrl  = kernalResourceProvider.getJSONObject(i).getString("url").toString();
						String kName = kernalResourceProvider.getJSONObject(i).getString("name").toString();
						
						obj.put("name", kName);
						obj.put("url", kUrl);
						
						urlArray.put(obj);
					}
				}
			}
			outerObj.put("resourceProvider", urlArray);
		} 
		catch (SQLException e)
		{
			System.out.println("getKernalResourceProvider SQLException EXCEPTION :" + e.toString());
		} 
		catch (JSONException e)
		{
			System.out.println("getKernalResourceProvider JSONPARSING EXCEPTION :" + e.toString());
		} 
		finally 
		{ 
			closeDatabaseConnection(); 
		}
		return outerObj.toString();
	}
/**=====================  Return the json data for the SearchWeb  ================================**/
	public String getSearchData(String input , String inputParameter)
	{
		JSONArray objArray = new JSONArray();
		JSONObject outerObj = new JSONObject();
		int count = 0;
		try 
		{
			/*------------------------------  for brick search  ------------------------------*/
			if(input.equals("brickSearch"))
			{
				selectSQL = "SELECT * FROM brick WHERE brick_name LIKE? OR brick_type LIKE?";

				String brick_type = "%"+inputParameter+"%";				
				pStat = dbConnection.prepareStatement(selectSQL);
				pStat.setString(1, brick_type);
				pStat.setString(2, brick_type);
				rs = pStat.executeQuery();
				while(rs.next()) 
				{
					JSONObject obj = new JSONObject();
					obj.put( "brick_id", rs.getString ("brick_id") );  
					obj.put( "brick_description", rs.getString ("brick_description") );  
					obj.put( "brick_name", rs.getString ("brick_name") );  
					obj.put( "brick_pic", rs.getString ("brick_pic") );  
					obj.put( "brick_date", rs.getString ("brick_date") );  
					obj.put( "brick_type", rs.getString ("brick_type") );  
					obj.put( "URL", rs.getString(("URL")));
					String brick_data = rs.getString ("brick_data");
					JSONObject inObj = new JSONObject(brick_data);

					obj.put( "brick_data", inObj ); 
					
					objArray.put(obj);
					count = count + 1;
				} 
				outerObj.put("brick", objArray);
				outerObj.put("brickNumber", count);
			}
			/*------------------  for application(composite brick) search  ------------------*/
			else if(input.equals("applicationSearch"))
			{
				selectSQL = "SELECT * FROM application WHERE application_name LIKE? OR application_type LIKE?";

				String brick_type = "%"+inputParameter+"%";				
				pStat = dbConnection.prepareStatement(selectSQL);
				//pStat.setString(1, type);
				pStat.setString(1, brick_type);
				pStat.setString(2, brick_type);
				rs = pStat.executeQuery();
				while(rs.next()) 
			    {
			    	JSONObject obj = new JSONObject();
			      	obj.put( "application_id", rs.getString ("application_id") );  
			    	obj.put( "application_description", rs.getString ("application_description") );  
			    	obj.put( "application_name", rs.getString ("application_name") );  
			    	obj.put( "application_pic", rs.getString ("application_pic") );  
				    objArray.put(obj);
				    count = count + 1;
				} 
				outerObj.put("application", objArray);
				outerObj.put("applicationNumber", count);
			}
		}
		
		catch(SQLException e) 
		{	
			System.out.println("getSearchData SQLException EXCEPTION :" + e.toString());
		} 
		catch (JSONException e)
		{
			System.out.println("getSearchData JSONPARSING EXCEPTION :" + e.toString());
		} 
			
		finally 
		{ 
			closeDatabaseConnection(); 
		}
		return outerObj.toString();
	}
/**=====================  Return the application data for portal  ================================**/
	public String getApplicationIdAndName(String user_id)
	{
		JSONArray objArray = new JSONArray();	
		JSONObject outerObj = new JSONObject();
		try 
		{
			/*------------------------------  for brick search  ------------------------------*/
			{
				selectSQL = "SELECT * FROM application WHERE user_id =?";

				pStat = dbConnection.prepareStatement(selectSQL);
				pStat.setString(1, user_id);
				rs = pStat.executeQuery();
				while(rs.next()) 
			    {
			    	JSONObject obj = new JSONObject();
			    	obj.put( "application_id", rs.getString ("application_id") );  
			    	obj.put( "application_name", rs.getString ("application_name") );  
			    	
			    	objArray.put(obj);			    
				}
				outerObj.put("application", objArray);
			}
		}
		
		catch(SQLException e) 
		{	
			System.out.println("getApplicationIdAndName SQLException EXCEPTION :" + e.toString());
		} 
		catch (JSONException e)
		{
			System.out.println("getApplicationIdAndName JSONPARSING EXCEPTION :" + e.toString());
		} 
			
		finally 
		{ 
			closeDatabaseConnection(); 
		}
		return outerObj.toString();
	}
/**=====================  Return the application data for Designer  ==============================**/
	public String getApplicationData(String application_id)
	{
    	JSONObject obj = new JSONObject();
		JSONObject outerObj = new JSONObject();
		try 
		{
			/*------------------------------  for brick search  ------------------------------*/
			{
				selectSQL = "SELECT * FROM application WHERE application_id =?";
	
				pStat = dbConnection.prepareStatement(selectSQL);
				//pStat.setString(1, type);
				pStat.setString(1, application_id);
				rs = pStat.executeQuery();
				while(rs.next()) 
			    {
			      	obj.put( "application_id", rs.getString ("application_id") );  
			    	obj.put( "application_description", rs.getString ("application_description") );  
			    	obj.put( "application_name", rs.getString ("application_name") );  
			    	obj.put( "application_pic", rs.getString ("application_pic") );  
			    	obj.put( "user_id", rs.getString ("user_id") );  
			    	obj.put( "application_type", rs.getString ("application_type") );  
			    	//obj.put( "application_data", rs.getString ("application_data") ); 
			    	String innerData = rs.getString ("application_data");
					JSONObject dataObj = new JSONObject(innerData);
					
					obj.put( "application_data", dataObj );
				} 
				outerObj.put("application", obj);
			}
		}
		
		catch(SQLException e) 
		{	
			System.out.println("getApplicationData SQLException EXCEPTION :" + e.toString());
		} 
		catch (JSONException e)
		{
			System.out.println("getApplicationData JSONPARSING EXCEPTION :" + e.toString());
		} 
			
		finally 
		{ 
			closeDatabaseConnection(); 
		}
		return outerObj.toString();
	}
/**=====================  login authentication   =================================================**/
	public String accountAuthentication(String account, String password)
	{
		String accountPassword;
		try 
		{ 
			/*------------------------------  for brick search  ------------------------------*/
				selectSQL = "SELECT * "+
							"FROM user "+  
							"WHERE user_id =?";
				pStat = dbConnection.prepareStatement(selectSQL); 
				pStat.setString(1, account);				
				rs = pStat.executeQuery(); 
 
				while(rs.next())
				{
					accountPassword = rs.getString("user_password");
					closeDatabaseConnection();
					if(accountPassword.equals(password))
					{
						return "true";
					}
					return "incorrect password";		
				}
				
		}
		
		catch(SQLException e) 
		{	
			System.out.println("Althentication DBSelect Exception  :" + e.toString()); 
		}
		closeDatabaseConnection();
		return "account not found"; 			
	}
/**=====================  add the user account to database  after register   =====================**/
	public String setRegisterData(String account, String password)
		{
		    try 
		    {
				selectSQL = "SELECT * "+
						"FROM user "+  
						"WHERE user_id =?";
				pStat = dbConnection.prepareStatement(selectSQL); 
				pStat.setString(1, account);				
				rs = pStat.executeQuery(); 
				while(rs.next())
				{
					return "account is already exist";
				}
		    	String insertdbSQL = 
		    			"INSERT INTO user VALUES(?,?)";
		    	pStat = dbConnection.prepareStatement(insertdbSQL); 
		    	pStat.setString(1, account);	
		    	pStat.setString(2, password);	
		    	pStat.executeUpdate(); 
		}
		    /*--------------------- failed to inserting data to database  ---------------------*/
		    catch(SQLException e) 
		    { 
		    	System.out.println("Register SELECT SQL Exception :" + e.toString()); 
			} 
			finally 
			{ 
				closeDatabaseConnection(); 
			} 
		    return "true";
		}
/**=====================  update the application to database  after edited   =====================**/
	public void updateApplicationData(String applicationDataJsonString)
	{
		try
		{
			JSONObject jsonObj = new JSONObject(applicationDataJsonString);
			String updateSQL = "UPDATE  brick_project.application SET "
					+ "application_type =? ,"
					+ "application_pic =? ,"
					+ "application_data =? ,"
					+ "application_description =? ,"
					+ "application_name =? "
					+ "WHERE  application.application_id =? AND application.user_id=?";
			
			pStat = dbConnection.prepareStatement(updateSQL); 
			pStat.setString(1, (String)jsonObj.get("application_type"));				
			pStat.setString(2, (String)jsonObj.get("application_pic"));				
			pStat.setString(3, (String)jsonObj.get("application_data"));				
			pStat.setString(4, (String)jsonObj.get("application_description"));				
			pStat.setString(5, (String)jsonObj.get("application_name"));				
			pStat.setString(6, (String)jsonObj.get("application_id"));				
			pStat.setString(7, (String)jsonObj.get("user_id"));				
			pStat.executeUpdate(); 
	
		}
		
		catch(SQLException e) 
		{	
			System.out.println("updateApplicationData SQLException EXCEPTION :" + e.toString());
		} 
		catch (JSONException e)
		{
			System.out.println("updateApplicationData JSONPARSING EXCEPTION :" + e.toString());
		} 
		finally
		{
			closeDatabaseConnection();
		}
		
	}
/**=====================  insert the application to database  after created  =====================**/	
	public void insertApplicationToDatabase(String applicationDataJsonString,String userId)
	{
		try
		{
			JSONObject obj = new JSONObject(applicationDataJsonString);
			System.out.println(obj);
			JSONObject application = new JSONObject(obj.get("application").toString());
			System.out.println(application);
			
			String application_name = (String)application.get("application_name");
			System.out.println(application_name);			
			String application_type = (String)application.get("application_type");
			System.out.println(application_type);
			String application_description = (String)application.get("application_description");
			System.out.println(application_description);
			String application_data = (String)application.get("application_data");
			System.out.println(application_data);
			String user_id =userId;
			System.out.println(user_id);
			String application_pic = (String)application.get("application_pic");
			System.out.println(application_pic);
			
			String count ="SELECT COUNT(*) FROM application";
			pStat = dbConnection.prepareStatement(count);
			rs = pStat.executeQuery();
			rs.next();
			String counter =String.format("%03d", rs.getInt("COUNT(*)")) ;
			System.out.print(counter);
			
			String insertSQL = "INSERT INTO application VALUES(?,?,?,?,?,?,?)";
			pStat = dbConnection.prepareStatement(insertSQL);
			pStat.setString(1, counter);				
			pStat.setString(2, application_type);				
			pStat.setString(3, application_pic);				
			pStat.setString(4, application_data);				
			pStat.setString(5, application_description);				
			pStat.setString(6, user_id);				
			pStat.setString(7, application_name);				
			

			pStat.executeUpdate(); 

			
		}
		catch (JSONException e)
		{
			System.out.println("JSON PARSING ERROR IN insertApplicationToDatabase method :"+e.toString());
		} catch (SQLException e) {
			System.out.println("SQLException ERROR IN insertApplicationToDatabase method :"+e.toString());
		}
	}
/**=====================  delete the application to database   =====================**/		
	public void deleteApplicationToDatabase(String applicationId)

	{
		String DELETE_SQL="DELETE FROM brick_project.application "
				+"WHERE application.application_id=? ;";
		try
		{
			pStat = dbConnection.prepareStatement(DELETE_SQL);
			pStat.setString(1, applicationId);	
			pStat.executeUpdate(); 
		}
		catch (SQLException e)
		{
			System.out.print("DELETE_APPLICATION SQL ERROR: "+e.toString());
		} 
		finally 
		{ 
			closeDatabaseConnection(); 
		}
	}
/**=====================  delete the application to database   =====================**/		
	public String deleteBrickToDatabase(String userId,String brickName)
	{
		String returnString = "success" ;
		String DELETE_SQL="DELETE FROM brick_project.brick "
				+"WHERE brick.brick_id=? ;";
		try
		{
			pStat = dbConnection.prepareStatement(DELETE_SQL);
			String brick_id = userId+"_"+brickName;
			pStat.setString(1, brick_id);	
			pStat.executeUpdate(); 
		}
		catch (SQLException e)
		{
			returnString  = "DELETE_BRICK SQL ERROR: "+e.toString();
			System.out.print(returnString);
		} 
		finally 
		{ 
			closeDatabaseConnection(); 
		}
		return returnString;
	}
/**=====================  edit new application for composite brick   =====================**/		
	public String getBrickData(String brickIdJsonArray)
	{
		JSONObject outputObj = new JSONObject();
		JSONArray outputArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(brickIdJsonArray);
			JSONArray arr = new JSONArray(obj.get("brick_id").toString());
			String idArray[] = new String[arr.length()];
			for(int i =0; i<arr.length(); i++)
			{
				idArray[i] = arr.get(i).toString();
			}
			selectSQL = "SELECT * FROM brick WHERE ";
			for(int i=0; i<idArray.length-1;i++)
			{
				selectSQL = selectSQL + "brick_id =? OR ";
			}
			selectSQL = selectSQL + "brick_id =?;";
			
			pStat = dbConnection.prepareStatement(selectSQL);
			for(int i=0; i<idArray.length;i++)
			{
				pStat.setString(i+1, idArray[i]);
			}
			rs = pStat.executeQuery();
			while(rs.next()) 
			{
				JSONObject whileObj = new JSONObject();
				
				whileObj.put( "brick_id", rs.getString ("brick_id") );  
				whileObj.put( "brick_description", rs.getString ("brick_description") );  
				whileObj.put( "brick_name", rs.getString ("brick_name") );  
				whileObj.put( "brick_pic", rs.getString ("brick_pic") );  
				whileObj.put( "brick_date", rs.getString ("brick_date") );  
				whileObj.put( "brick_type", rs.getString ("brick_type") ); 
				String innerData = rs.getString ("brick_data");
				JSONObject dataObj = new JSONObject(innerData);
				
				whileObj.put( "brick_data", dataObj );
				outputArr.put(whileObj);
			} 
			outputObj.put("brick", outputArr);

		} catch (JSONException e)
		{
			System.out.println("getBrickData JSON PARSING ERROR:" + e.toString());
		} catch (SQLException e) {
			System.out.println("getBrickData SELECTSQL  ERROR:" + e.toString());
		}
		return outputObj.toString();
	}
/**=====================  insert the brick to database  after created(NEED EDIT)   =====================**/	
	public void insertBrickIntoDatabase(String userId, String description, String name ,String data, String type, String imgUrl, String codeUrl)
	{
		java.util.Date today = new java.util.Date(); 	   
		try
		{
			String insertSQL = "INSERT INTO brick VALUES(?,?,?,?,?,?,?,?)";
			pStat = dbConnection.prepareStatement(insertSQL);
			String brick_id = userId+"_"+name;
			pStat.setString(1, brick_id);				
			pStat.setString(2, description);				
			pStat.setString(3, name);				
			pStat.setString(4, imgUrl);				
			pStat.setTimestamp(5,new java.sql.Timestamp(today.getTime()));		
			pStat.setString(6, data);				
			pStat.setString(7, type);
			pStat.setString(8, codeUrl);
			
			pStat.executeUpdate(); 

			
		}
		catch (SQLException e) {
			System.out.println("SQLException ERROR IN insertBrickIntoDatabase method :"+e.toString());
		}

	}
/**=====================  Return the json data for the SearchWeb  ================================**/
	public String searchBrickForUpload(String userId, String brickName)
	{
		String returnString = "not exist";
		try 
		{
				selectSQL = "SELECT * FROM brick WHERE brick_name =?";

				pStat = dbConnection.prepareStatement(selectSQL);
				pStat.setString(1, brickName);
				rs = pStat.executeQuery();
				if(rs.next()) 
				{
					String brickId = userId+"_"+brickName;
					System.out.println(rs.getString ("brick_id")+","+brickId);
					if(brickId.equals(rs.getString ("brick_id")))
					{
						returnString = "author";
					}
					else
						returnString = "exist";
				} 
		}
		
		catch(SQLException e) 
		{
			returnString = "error";
			System.out.println("SEARCH_BRICK_DATA_FOR_UPLOAD SQLException EXCEPTION :" + e.toString());
		} 
			
		finally 
		{ 
			closeDatabaseConnection(); 
		}
		return returnString;
	}
/**=====================  Close Database connection   =====================**/
	private void closeDatabaseConnection() 
		{ 
			try 
			{ 
			      if(rs!=null) 
			      { 
			        rs.close(); 
			        rs = null; 
			      } 
			      if(pStat!=null) 
			      { 
			        pStat.close(); 
			        pStat = null; 
			      } 
			} 
		    catch(SQLException e) 
		    { 
		      System.out.println("DBClose Exception :" + e.toString()); 
		    } 
		} 
}
