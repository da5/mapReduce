package misc.jaxb.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by arindam on 21/2/17.
 */
@XmlRootElement(name = "staticRouting")
public class NSXStaticRoutingConfiguration {

    public static class Route{
        String mtu;
        String description;
        String type;
        String network;
        String nextHop;

        public String getMtu() {
            return mtu;
        }

        @XmlElement
        public void setMtu(String mtu) {
            this.mtu = mtu;
        }

        public String getDescription() {
            return description;
        }

        @XmlElement
        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        @XmlElement
        public void setType(String type) {
            this.type = type;
        }

        public String getNetwork() {
            return network;
        }

        @XmlElement
        public void setNetwork(String network) {
            this.network = network;
        }

        public String getNextHop() {
            return nextHop;
        }

        @XmlElement
        public void setNextHop(String nextHop) {
            this.nextHop = nextHop;
        }
    }

    @XmlElementWrapper(name = "staticRoutes")
    @XmlElement(name="route")
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }
}
