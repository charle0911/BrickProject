package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResourceProviderBean
{
	private Connection con = null; 
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pStatement = null; 
	private String selectSQL ; 
	
	public String getKernalResourceProvider(String resourceType)
	{
		selectSQL = "SELECT brick_data FROM brick ";
		JSONArray urlArray = new JSONArray();
		try
		{
			stat = con.createStatement(); 			
			rs = stat.executeQuery(selectSQL);
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
		}
		catch (SQLException e)
		{
			System.out.println("SELECTIONDB Exception :" + e.toString());
		} 
		catch (JSONException e)
		{
			System.out.println("JSONPARSING Exception :" + e.toString());
		} 
		finally 
		{ 
			Close(); 
		}
		return urlArray.toString();
		
	}
	private void Close() 
	{ 
		try 
		{ 
		      if(rs!=null) 
		      { 
		        rs.close(); 
		        rs = null; 
		      } 
		      if(stat!=null) 
		      { 
		        stat.close(); 
		        stat = null; 
		      } 
		      if(pStatement!=null) 
		      { 
		        pStatement.close(); 
		        pStatement = null; 
		      } 
		} 
	    catch(SQLException e) 
	    { 
	      System.out.println("Close Exception :" + e.toString()); 
	    } 
	} 

	public static void main(String args[])
	{
	}
}
