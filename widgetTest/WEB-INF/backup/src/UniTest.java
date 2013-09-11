import com.model.GetYoutubeData;
import com.model.SearchData;


public class UniTest {

	public static void main(String args[])
	{
		String name = "Adele";
	
		SearchData SD = new SearchData();
		System.out.println(SD.GetData(name));
		
		
	}
}
