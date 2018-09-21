import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReadFile {
	private ArrayList<String> tweet_id = new ArrayList<String>();
	private ArrayList<String> tweet_text = new ArrayList<String>();
	private ArrayList<String> tweet_loc = new ArrayList<String>();
	private ArrayList<String> features = new ArrayList<String>();
	public void fread(String dir) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(dir))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] params = line.split("\t");
		        tweet_id.add(params[1]);
		        params[2]=params[2].replaceAll("[^a-zA-Z]", " ").toLowerCase();
		        tweet_text.add(params[2]);
		        tweet_loc.add(params[3]);        
		    }
		};
	   	
	}
	
	public void readFeature(String allFeatures){
		for(String feature: allFeatures.split(" ")){			
			features.add(feature);
		}
	}
	
	public void getValues(ArrayList<String> tweet_text, ArrayList<String> features, String relation){
		System.out.println("@RELATION "+relation);
		for(int i=0; i< features.size(); i++){
			System.out.println("@ATTRIBUTE "+features.get(i)+" NUMERIC");
		}
		System.out.println("@DATA");
		for(String tweet: tweet_text){
			String delim = "";
			StringBuilder sb = new StringBuilder();
			for(String feature: features){
				Pattern p = Pattern.compile(feature);
				Matcher m = p.matcher(tweet);
				int count = 0;
				while(m.find()){
					count += 1;
				}				
				sb.append(delim).append(Integer.toString(count));
				delim = ",";
			}		
			System.out.println(sb.toString());
		}
	}
	
	public ArrayList<String> getTweet_id(){
		return tweet_id;
	}
	public ArrayList<String> getTweet_text(){
		return tweet_text;
	}
	public ArrayList<String> getTweet_loc(){
		return tweet_loc;
	}
	public ArrayList<String> getFeatures(){
		return features;
	}
}
