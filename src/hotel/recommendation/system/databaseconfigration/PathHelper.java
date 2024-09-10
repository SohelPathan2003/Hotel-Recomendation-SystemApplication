package hotel.recommendation.system.databaseconfigration;
import java.io.*;
import java.util.Properties;
public class PathHelper {
	
	File file=null;
	FileReader fr=null;
		PathHelper() throws InterruptedException,ClassNotFoundException, IOException{
	file=new File("src\\JdbcHelper.properties");
	fr=new FileReader(file.getAbsolutePath());
		
	}

}
