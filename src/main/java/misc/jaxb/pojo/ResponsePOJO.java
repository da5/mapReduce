package misc.jaxb.pojo;

/**
 * Created by arindam on 6/2/17.
 */



import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "firewallConfiguration")
public class ResponsePOJO {

    public static class Section{

        public static class Rule{

            String disabled;

            public String getDisabled() {
                return disabled;
            }

            @XmlAttribute
            public void setDisabled(String disabled) {
                this.disabled = disabled;
            }
        }

        String name;

        @XmlElement(name="rule")
        private List<Rule> rules;

        public List<Rule> getSectionRules() {
            return rules;
        }

        public String getName() {
            return name;
        }

        @XmlAttribute
        public void setName(String name) {
            this.name = name;
        }


    }

    @XmlElementWrapper(name = "layer3Sections")
    @XmlElement(name="section")
    private List<Section> l3sections;

    @XmlElementWrapper(name = "layer2Sections")
    @XmlElement(name="section")
    private List<Section> l2sections;

    @XmlAnyElement(lax = true)
    private List<Object> everythingElse;

    public List<Section> getL3sections() {
        return l3sections;
    }

    public List<Section> getL2sections() {
        return l2sections;
    }
}
