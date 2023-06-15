package com.fable.logroutingservice.service;

import com.fable.logroutingservice.model.LogData;
import com.fable.logroutingservice.repository.LogDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class LogAuditService {
    private LogDataRepository logDataRepository;

    public void auditLogs(StringBuilder buf) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(buf.toString(),JsonNode.class);
        System.out.println(jsonNode);

        LogData logData = new LogData();
        logData.setLogId(jsonNode.get("id").asInt());
        logData.setEventName(jsonNode.get("event_name").asText());
        logData.setUserId(jsonNode.get("user_id").asText());
        logData.setUnixTs(jsonNode.get("unix_ts").asText());

        logDataRepository.save(logData);
    }
}
