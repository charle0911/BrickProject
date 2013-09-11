import com.model.GetYoutubeData;


public class YouTubeUniTest {
	public static void main(String args[])
	{
	String name = "adele";
	String songName = "Rolling In The Deep";
	String NewsongName =songName.replace(" ","%20");
	
	GetYoutubeData YouTube = new GetYoutubeData(name,NewsongName);
	System.out.println(YouTube.GetData());
	}
}
