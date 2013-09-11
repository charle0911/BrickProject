package com.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceInterfaceTest
{
	 
	public static void main(String args[]) 
	{
		AccountService account = new AccountService();
		//System.out.println("---------------------------------account.setRegisterData---------------------------------");
		//System.out.println(account.setRegisterData("112323", "aaa"));
		//System.out.println("---------------------------------account.accountAlthentication---------------------------------");
		//System.out.println(account.accountAuthentication("djpasodjaspodjad", "abc"));
		
		SearchService service = new SearchService();

		System.out.println("---------------------------------service.getApplicationIdAndName---------------------------------");		
		System.out.println(service.getApplicationIdAndName("100"));
		System.out.println("---------------------------------service.getApplicationDataForDesigner---------------------------------");		
		System.out.println(service.getApplicationData("001"));
		System.out.println("---------------------------------service.getSearchResultData---------------------------------");		
		System.out.println(service.getSearchData("brickSearch", "m"));
		System.out.println("---------------------------------service.getKernalResourceProvider---------------------------------");		
		//System.out.println(service.getKernalResourceProvider("music"));

		String test = "{\"application\":{\"application_type\":\"music\",\"application_description\":\"djwasjpdasdopasjdpo\",\"application_data\":\"sdfpjsdf\",\"application_name\":\"qwe\",\"user_id\":\"010\",\"application_pic\":\"asjdpoasjdopasjdp\",\"application_id\":\"001\"}}";
		//service.updateApplicationData(test);
		//service.insertApplicationToDatabase(test,"001");

		ServiceInterface inter = new ServiceInterface();
		inter.insertBrickIntoDatabase("brickid","description", "brickname", "dt", "hihi","123","123");
		System.out.println(inter.deleteBrickToDatabase("tom", "cc"));
		
		System.out.println(inter.searchBrickForUpload("bid", "bname"));		
		
		System.out.println("---------------------------------service.getBrickData---------------------------------");		
		System.out.println( service.getBrickData("{\"brick_id\":[\"002\",\"001\"]}"));
		//service.deleteApplicationToDatabase("003");
		JSONObject obj;
		try {
			obj = new JSONObject(service.getBrickData("{\"brick_id\":[\"002\",\"001\"]}"));
			JSONArray arr = new JSONArray(obj.get("brick").toString());
			System.out.println("arr="+arr);
			JSONObject obj2 = new JSONObject(arr.get(0).toString());
			
			JSONObject brickDataObj = new JSONObject(obj2.getJSONObject("brick_data").toString());
			System.out.println(brickDataObj);
			
			JSONArray outputEventArr = new JSONArray(brickDataObj.getJSONArray("outputEvent").toString());
			JSONObject outputEventObj = new JSONObject(outputEventArr.get(0).toString());
			System.out.println(outputEventObj);
			
			String outputEventId = outputEventObj.getString("id");
			System.out.println("outputEventId="+outputEventId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
