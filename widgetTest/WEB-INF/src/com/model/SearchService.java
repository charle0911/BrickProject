package com.model;

public class SearchService {
	private ServiceInterface sInterface= new ServiceInterface();
	public SearchService()
	{
		
	}
	public String getKernalResourceProvider(String resourceType)
	{
		return sInterface.getKernalResourceProvider(resourceType);
	}
	public String getApplicationIdAndName(String user_id)
	{
		return sInterface.getApplicationIdAndName(user_id);
	}
	public String getSearchData(String input, String inputParameter)
	{
		return sInterface.getSearchData(input, inputParameter);
	}
	public String getApplicationData(String application_id)
	{
		return sInterface.getApplicationData(application_id);
	}
	public void updateApplicationData(String applicationDataJsonString)
	{ 
		sInterface.updateApplicationData(applicationDataJsonString);
	}
	public void insertApplicationToDatabase(String applicationDataJsonString,String userId)
	{
		sInterface.insertApplicationToDatabase(applicationDataJsonString,userId);
	}
	public void deleteApplicationToDatabase(String applicationId)
	{
		sInterface.deleteApplicationToDatabase(applicationId);
	}
	public String getBrickData(String brickIdJsonArray)
	{
		return sInterface.getBrickData(brickIdJsonArray);
	}
}
