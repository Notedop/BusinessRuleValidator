package nl.rvh.rulevalidation.applicators;

import nl.rvh.rulevalidation.ResultApplicator;

import java.util.Map;


public class LogApplicator extends ResultApplicator {


    public LogApplicator(Map<String, Object> parameters) {
        super(parameters);
    }

    @Override
    public void applyResult() {
        log.info("applying result: {}", parameters.get("log"));
    }
}