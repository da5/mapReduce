package misc.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by dasarindam on 4/6/2017.
 */
public class Driver {
    public static void main(String[] args){
        JSONParser jsonParser = new JSONParser();
        Map<String, Set<String>> vmMap = new HashMap<String, Set<String>>();
        Map<String, JSONObject> objectMap = new HashMap<String, JSONObject>();
        try {
            Object object = jsonParser.parse(new FileReader("C:\\Users\\dasarindam\\Downloads\\itbm\\vcdvm.txt"));

            JSONObject jsonObject = (JSONObject)object;
            JSONArray jsonArray = (JSONArray)jsonObject.get("changes");


            for(int i =0; i<jsonArray.size(); i++){
                JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);
                String entityId = (String)jsonObject1.get("entityId");
                String vcVm = (String) ((JSONObject)jsonObject1.get("properties")).get("vcVm");
                if(!vmMap.containsKey(vcVm)){
                    vmMap.put(vcVm, new HashSet<String>());
                }
                vmMap.get(vcVm).add(entityId);
                objectMap.put(vcVm+"~"+entityId, jsonObject1);
            }
            int countOfDuplicates = 0;
            for(Map.Entry<String, Set<String>> entry: vmMap.entrySet()){
                if(entry.getValue().size()>1){
                    countOfDuplicates++;
                    System.out.println(entry.getKey() + " = " +entry.getValue());
                    for(String entityId: entry.getValue()){
                        System.out.println(objectMap.get(entry.getKey()+"~"+entityId));
                    }
                }
            }
            System.out.println("Size : " + jsonArray.size());
            System.out.println("Duplicates : " + countOfDuplicates);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
