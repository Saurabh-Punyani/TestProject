import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class TeamSelection {
    Assertion assertion = new Assertion();
    JSONParser jsonParser = new JSONParser();
    FileReader reader;
    Object obj;
    JSONObject teamList;
    HashMap<String, Integer> map = new HashMap<>();
    JSONArray players;

    @Test
    public void foreignPlayer() throws IOException, ParseException {
        reader = new FileReader("src/test/java/Team.json");
        obj = jsonParser.parse(reader);
        teamList = (JSONObject) obj;

        players = (JSONArray) teamList.get("player");
        Iterator iterator = players.iterator();
        while (iterator.hasNext()) {
            JSONObject playerData = (JSONObject) iterator.next();
            //Fetching the Country for all the Players
            String country = (String) playerData.get("country");
            if (map.containsKey(country)) {
                map.put(country, map.getOrDefault(country, 0) + 1);
            } else {
                map.put(country, 1);
            }
        }
        int total_number = map.get("India");
        assertion.assertTrue(total_number==7," Total Number of Foreign Players are less/greater than 4");
    }

    @Test
    public void checkWicketKeeper() throws IOException, ParseException {
        reader = new FileReader("src/test/java/Team.json");
        obj = jsonParser.parse(reader);
        teamList = (JSONObject) obj;

        players = (JSONArray) teamList.get("player");
        Iterator iterator = players.iterator();
        while (iterator.hasNext()){
            JSONObject playerData = (JSONObject) iterator.next();
            //Fetching the role for all the Players
            String role = (String) playerData.get("role");
            if(map.containsKey(role)){
                map.put(role,map.getOrDefault(role,0)+1);
            }else {
                map.put(role,1);
            }
        }
        int total_number_of_keeper =  map.getOrDefault("Wicket-keeper",0);
        assertion.assertTrue(total_number_of_keeper>0,"There are no wicket-keeper in the team");
    }
}
