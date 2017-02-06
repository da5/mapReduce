package misc.jaxb;

import misc.jaxb.pojo.ResponsePOJO;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arindam on 6/2/17.
 */
public class Driver {

    public static void main(String[] args) throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response.xml"));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine = null;
        while((inputLine = bufferedReader.readLine())!=null){
            stringBuilder.append(inputLine);
        }

        ResponsePOJO responsePOJO = JAXB.unmarshal(new StringReader(stringBuilder.toString()), ResponsePOJO.class);

        String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
        Pattern pattern = Pattern.compile(regex);

        for(ResponsePOJO.Section section:  responsePOJO.getL3sections()){
            Matcher matcher = pattern.matcher(section.getName());
            if(matcher.find()){
                for(ResponsePOJO.Section.Rule rule: section.getSectionRules()){
                    if(rule.getDisabled().equalsIgnoreCase("false")){
                        System.out.println(matcher.group(0));
                    }
                }
            }


        }

    }

}
