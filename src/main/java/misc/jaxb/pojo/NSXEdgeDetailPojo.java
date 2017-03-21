package misc.jaxb.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by arindam on 22/2/17.
 */
@XmlRootElement(name = "edge")
public class NSXEdgeDetailPojo {
    public static class L2Vpn{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class Firewall{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class SslVpn{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class HighAvailability{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class LoadBalancer{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class IpsecVpn{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class Dhcp{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class Nat{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class Ospf{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class Bgp{
        String enabled;

        public String getEnabled() {
            return enabled;
        }

        @XmlElement
        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }
    }

    public static class StaticRouting{
        @XmlElementWrapper(name = "staticRoutes")
        @XmlElement(name="route")
        private List<NSXStaticRoutingConfiguration.Route> routes;

        public List<NSXStaticRoutingConfiguration.Route> getRoutes() {
            return routes;
        }
    }
    public static class Routing{
        @XmlElement(name="ospf")
        Ospf ospf;

        @XmlElement(name="bgp")
        Bgp bgp;

        @XmlElement(name="staticRouting")
        StaticRouting staticRouting;

        public Ospf getOspf() {
            return ospf;
        }

        public Bgp getBgp() {
            return bgp;
        }

        public StaticRouting getStaticRouting() {
            return staticRouting;
        }
    }

    public static class Features{
        @XmlElement(name = "l2Vpn")
        L2Vpn l2Vpn;

        @XmlElement(name = "firewall")
        Firewall firewall;

        @XmlElement(name = "sslvpnConfig")
        SslVpn sslVpn;

        @XmlElement(name = "highAvailability")
        HighAvailability highAvailability;

        @XmlElement(name = "loadBalancer")
        LoadBalancer loadBalancer;

        @XmlElement(name = "ipsec")
        IpsecVpn ipsecVpn;

        @XmlElement(name = "dhcp")
        Dhcp dhcp;

        @XmlElement(name = "nat")
        Nat nat;

        @XmlElement(name = "routing")
        Routing routing;

        public L2Vpn getL2Vpn() {
            return l2Vpn;
        }

        public Firewall getFirewall() {
            return firewall;
        }

        public SslVpn getSslVpn() {
            return sslVpn;
        }

        public HighAvailability getHighAvailability() {
            return highAvailability;
        }

        public LoadBalancer getLoadBalancer() {
            return loadBalancer;
        }

        public IpsecVpn getIpsecVpn() {
            return ipsecVpn;
        }

        public Dhcp getDhcp() {
            return dhcp;
        }

        public Nat getNat() {
            return nat;
        }

        public Routing getRouting() {
            return routing;
        }
    }

    @XmlElement(name="features")
    Features features;

    public Features getFeatures() {
        return features;
    }
}
