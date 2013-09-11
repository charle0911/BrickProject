import com.model.GetLyrics;
import com.model.SearchData;


public class LyricsUniTest {
	public static void main(String args[])
	{
		String url = "http://tw.kkbox.com/album/4uogQ1lUolV7Aj70FCgt008l-index.html";
		String SongName = "止戰之殤";
		GetLyrics SD = new GetLyrics(url,SongName);
		System.out.println(SD.GetData());
	
	}
}
