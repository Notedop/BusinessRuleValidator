package nl.rvh.rulevalidation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class LogApplicator extends ResultApplicator {

    Logger log = LoggerFactory.getLogger(LogApplicator.class);

    @JsonCreator
    public LogApplicator(@JsonProperty("parameter") Map<String, Object> parameters) {
        super(parameters);
    }

    @Override
    void applyResult() {
        log.info("applying result: {}", parameters.get("log"));
    }
}