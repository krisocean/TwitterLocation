import java.io.FileNotFoundException;
import java.io.IOException;

public class FeatureEng {
	static String dir = "/users/ChrisXiong/desktop/dev-tweets.txt";
	static String allFeatures = "celtic fenway harvard bruins rocket"
			+ " padres sounders wizard";
	static String relation = "twitter-loc-best35";
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		ReadFile read = new ReadFile();
		read.fread(dir);
		read.readFeature(allFeatures);
		read.getValues(read.getTweet_text(), read.getFeatures(),relation);
		
	}
}
