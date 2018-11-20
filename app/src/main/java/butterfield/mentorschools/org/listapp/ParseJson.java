package butterfield.mentorschools.org.listapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {
    String in;
    List<String>list= new ArrayList<String>();

    ParseJson(String in) throws JSONException {
        this.in = in;
    }

    public List<String> parse() throws JSONException {
        JSONObject reader = new JSONObject(in);
        JSONObject Open = reader.getJSONObject("Open");
        String openRaw = Open.toString();


        String open = openRaw.split(":")[1].split(",")[0];
        list.add(open);

        JSONObject Close = reader.getJSONObject("Close");
        String closeRaw = Close.toString();

        String close = closeRaw.split(":")[1].split(",")[0];
        list.add(close);

        JSONObject dailyhigh = reader.getJSONObject("High");
        String dailyhighRaw = dailyhigh.toString();


        String high = dailyhighRaw.split(":")[1].split(",")[0];
        list.add(high);

        JSONObject Low = reader.getJSONObject("Low");
        String lowRaw = Low.toString();


        String low = lowRaw.split(":")[1].split(",")[0];
        list.add(low);

        JSONObject Vol = reader.getJSONObject("Volume");
        String volRaw = Vol.toString();


        String vol = volRaw.split(":")[1].split(",")[0];
        list.add(vol);


        return list;


        }


    }


