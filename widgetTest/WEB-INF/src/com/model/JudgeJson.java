package com.model;

import org.json.*;

public class JudgeJson {
	public static int isJson(String json){
		int JudgeJsonVariable = 0;
		try{
			/*���ܼ� �Y�� 0 �h��� JSON�������~ 1
			 * 1 �h��ܯʤ�outputEvent
			 * 2�h��ܯʤ�inputEvent
			 * 3�h��ܯʤ�kernalResourceProvider
			 * 4�h��ܯʤ�optionalResourceProvider
			 * 5�h����
			 */
			
			JSONObject jsonObject = new JSONObject(json);
			
			JSONObject brickObject = new JSONObject(jsonObject.get("brick").toString());
			System.out.println(JudgeJsonVariable); 	
			JudgeJsonVariable = 1;
			JSONArray outputEventObject = brickObject.getJSONArray("outputEvent");
			
			for(int i = 0; i < outputEventObject.length();i++){
				JSONObject tempObject = outputEventObject.getJSONObject(i);
				System.out.println(tempObject); 
				
				System.out.println("id : " + tempObject.get("id")); 
				System.out.println("trigger : " + tempObject.get("trigger")); 
				System.out.println("event : " + tempObject.get("event")); 
			
			}
			System.out.println(JudgeJsonVariable); 
			JudgeJsonVariable = 2;
			JSONArray inputEventObject = brickObject.getJSONArray("inputEvent");
			
			for(int i = 0; i < inputEventObject.length();i++){
				JSONObject tempObject = inputEventObject.getJSONObject(i);
				
				System.out.println("id : " + tempObject.get("id")); 
				System.out.println("event : " + tempObject.get("event")); 
			}
			System.out.println(JudgeJsonVariable); 
			JudgeJsonVariable = 3;
			JSONArray kernalResourceProviderObject = brickObject.getJSONArray("kernelResourceProvider");
			
			for(int i = 0; i < kernalResourceProviderObject.length();i++){
				JSONObject tempObject = kernalResourceProviderObject.getJSONObject(i);
				
				System.out.println("type : " + tempObject.get("type")); 
				System.out.println("defaultResourceUrl : " + tempObject.get("defaultResourceUrl")); 
				System.out.println("action : " + tempObject.get("action")); 
			}
			//System.out.println(JudgeJsonVariable); 
			JudgeJsonVariable = 4;
			System.out.println(JudgeJsonVariable); 
			
			JSONArray optionalResourceProviderObject = brickObject.getJSONArray("optionalResourceProvider");
			
			for(int i = 0; i < optionalResourceProviderObject.length();i++){
				JSONObject tempObject = optionalResourceProviderObject.getJSONObject(i);
				
				System.out.println("type : " + tempObject.get("type")); 
				System.out.println("defaultResourceUrl : " + tempObject.get("defaultResourceUrl")); 
				System.out.println("action : " + tempObject.get("action")); 
			}
			
			JudgeJsonVariable = 5;
			System.out.println(JudgeJsonVariable); 
			
			return JudgeJsonVariable;
		}catch(Exception e){
			System.out.println(JudgeJsonVariable); 
			return JudgeJsonVariable;
		}
	}
}
