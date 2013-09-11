import org.apache.commons.fileupload.FileUploadException;

import toolkie.UploadTool;

import com.model.*;
import com.servlet.*;

public class Test {
		public static void main(String args[])
		{
			try{
				
			String jsonUrl =  "123.json";
			java.io.File brickJson = new java.io.File(jsonUrl);
			java.io.Reader reader = null;
			reader = new java.io.FileReader(brickJson);
			char c[] = new char[1024];
			int len = reader.read(c);
			reader.close();
			
			String ttt = String.valueOf(c);
			System.out.println(ttt); 
			if(JudgeJson.isJson(ttt)){
				System.out.println("Test"); 
			}else
				System.out.println("FFFFF"); 
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
