package misc.jaxb.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by arindam on 21/2/17.
 */
@XmlRootElement(name = "ipsecStatusAndStats")
public class IpsecResponsePojo {

    public static class SiteStatistic {

        public static class TunnelStats{

            String tunnelStatus;

            public String getTunnelStatus() {
                return tunnelStatus;
            }

            @XmlElement
            public void setTunnelStatus(String tunnelStatus) {
                this.tunnelStatus = tunnelStatus;
            }
        }


        TunnelStats tunnelStats;

        public TunnelStats getTunnelStats() {
            return tunnelStats;
        }

        @XmlElement
        public void setTunnelStats(TunnelStats tunnelStats) {
            this.tunnelStats = tunnelStats;
        }
    }


    List<SiteStatistic> siteStatistics;

    public List<SiteStatistic> getSiteStatistics() {
        return siteStatistics;
    }

    @XmlElement(name="siteStatistics")
    public void setSiteStatistics(List<SiteStatistic> siteStatistics) {
        this.siteStatistics = siteStatistics;
    }
}
