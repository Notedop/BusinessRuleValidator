package nl.rvh.rulevalidation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

public class RuleSerializer<T extends Rule> {

    private XStream xstream = new XStream(new StaxDriver());
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private AutowireCapableBeanFactory factory;

    public RuleSerializer() {
        xstream.autodetectAnnotations(true);
    }

    public RuleSerializer(XStream xstream) {
        this.xstream = xstream;
    }

    public <T extends Rule> String serialize(T object) {
        return xstream.toXML(object);
    }


    public T deserialize(String object) {
        T t = (T) xstream.fromXML(object);
        autowireSpringAnnotations(t);
        return t;
    }

    /**
     * Autowires rules (Sets and normal rules) as well as the result applicators
     *
     * @param rule either a business rule or a business rule set
     * @param <T>  should extend Rule
     */
    private <T extends Rule> void autowireSpringAnnotations(T rule) {
        if (factory != null) {
            if (rule instanceof BusinessRuleSet) {
                for (Rule businessRule : ((BusinessRuleSet) rule).getBusinessRules()) {
                    autowireSpringAnnotations(businessRule);
                }
            }

            if (rule.getFailResultApplicator()!=null)
                factory.autowireBean(rule.getFailResultApplicator());
            if (rule.getSuccessResultApplicator()!=null)
                factory.autowireBean(rule.getSuccessResultApplicator());

            factory.autowireBean(rule);

        } else {
            log.debug("No AutoWireCapableBeanFactory found, proceeding without autowiring");
        }
    }

    /**
     * In case the rule uses Spring Autowiring then set the factory from the applicationContext
     *
     * @param factory AutowireCapableBeanFactory from Spring ApplicationContext
     */
    public void setFactory(AutowireCapableBeanFactory factory) {
        this.factory = factory;
    }

}
