package misc.jaxb;

import misc.jaxb.pojo.IpsecResponsePojo;
import misc.jaxb.pojo.NSXEdgeDetailPojo;
import misc.jaxb.pojo.NSXStaticRoutingConfiguration;
import misc.jaxb.pojo.ResponsePOJO;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arindam on 6/2/17.
 */
public class Driver {

    static void driverDFW() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/response.xml"));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine = null;
        while((inputLine = bufferedReader.readLine())!=null){
            stringBuilder.append(inputLine);
        }

        ResponsePOJO responsePOJO = JAXB.unmarshal(new StringReader(stringBuilder.toString()), ResponsePOJO.class);

        String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
        Pattern pattern = Pattern.compile(regex);

        Set<String> orgs = new HashSet<String>();
        for(ResponsePOJO.Section section:  responsePOJO.getL3sections()){
            Matcher matcher = pattern.matcher(section.getName());
            if(matcher.find()){
                boolean foundActive = false;
                for(ResponsePOJO.Section.Rule rule: section.getSectionRules()){
                    if(rule.getDisabled().equalsIgnoreCase("false")){
                        foundActive = true;
                        break;
                    }
                }
                if(foundActive){
                    orgs.add(matcher.group(0));
                }
            }
        }

        for(ResponsePOJO.Section section:  responsePOJO.getL2sections()){
            Matcher matcher = pattern.matcher(section.getName());
            if(matcher.find()){
                boolean foundActive = false;
                for(ResponsePOJO.Section.Rule rule: section.getSectionRules()){
                    if(!Boolean.getBoolean(rule.getDisabled())){
                        foundActive = true;
                        break;
                    }
                }
                if(foundActive){
                    orgs.add(matcher.group(0));
                }
            }
        }
        int i = 1;
        for(String org: orgs){
            System.out.println(i++ + ")" + org);
        }
    }

    static void driverIpsec() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/responseIPSec.xml"));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while((inputLine = bufferedReader.readLine())!=null){
            stringBuilder.append(inputLine);
        }

        IpsecResponsePojo ipsecResponsePojo = JAXB.unmarshal(new StringReader(stringBuilder.toString()), IpsecResponsePojo.class);

        for(IpsecResponsePojo.SiteStatistic siteStatistic: ipsecResponsePojo.getSiteStatistics()){
            System.out.println(siteStatistic.getTunnelStats().getTunnelStatus());
        }
    }

    static void driverStaticRouting() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/responseStaticRouting.xml"));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while((inputLine = bufferedReader.readLine())!=null){
            stringBuilder.append(inputLine);
        }

        NSXStaticRoutingConfiguration nsxStaticRoutingConfiguration = JAXB.unmarshal(new StringReader(stringBuilder.toString()), NSXStaticRoutingConfiguration.class);

        for(NSXStaticRoutingConfiguration.Route route: nsxStaticRoutingConfiguration.getRoutes()){
            System.out.println(route.getDescription() + " " + route.getMtu() + " " + route.getNetwork() + " " + route.getNextHop() + " " + route.getType());
        }
    }

    static void driverEdgeDetails() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/responseEdgeDetails.xml"));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while((inputLine = bufferedReader.readLine())!=null){
            stringBuilder.append(inputLine);
        }

        NSXEdgeDetailPojo nsxEdgeDetailPojo = JAXB.unmarshal(new StringReader(stringBuilder.toString()), NSXEdgeDetailPojo.class);
        System.out.println("L2Vpn : " + nsxEdgeDetailPojo.getFeatures().getL2Vpn().getEnabled());
        System.out.println("Firewall : " + nsxEdgeDetailPojo.getFeatures().getFirewall().getEnabled());
        System.out.println("Ipsec : " + nsxEdgeDetailPojo.getFeatures().getIpsecVpn().getEnabled());
        System.out.println("DHCP : " + nsxEdgeDetailPojo.getFeatures().getDhcp().getEnabled());
        System.out.println("NAT : " + nsxEdgeDetailPojo.getFeatures().getNat().getEnabled());
        System.out.println("Load Balancer : " + nsxEdgeDetailPojo.getFeatures().getLoadBalancer().getEnabled());
        System.out.println("HA : " + nsxEdgeDetailPojo.getFeatures().getHighAvailability().getEnabled());
        System.out.println("SSL : " + nsxEdgeDetailPojo.getFeatures().getSslVpn().getEnabled());
        System.out.println("OSPF : " + nsxEdgeDetailPojo.getFeatures().getRouting().getOspf().getEnabled());
        System.out.println("BGP : " +
                ((nsxEdgeDetailPojo.getFeatures().getRouting().getBgp()!=null)?
                nsxEdgeDetailPojo.getFeatures().getRouting().getBgp().getEnabled(): "false")
        );
        System.out.println("Static Routes : " + nsxEdgeDetailPojo.getFeatures().getRouting().getStaticRouting().getRoutes().size());
    }


    public static void main(String[] args) throws Exception{
        driverEdgeDetails();
    }

}
