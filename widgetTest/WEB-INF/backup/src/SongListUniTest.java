import com.model.GetSongList;
import com.model.GetYoutubeData;
import com.model.SearchData;


public class SongListUniTest {
	public static void main(String args[])
	{
		String songName = "七里香";
		String singerName="周杰倫";
		GetSongList SL = new GetSongList(songName,singerName);
		System.out.println(SL.GetData());
	}
}
