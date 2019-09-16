package com.kh.util;

import com.kh.dao.AlertLogDao;
import com.kh.model.AlertLog;
import com.kh.vos.EventLog;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogLineHandler {
    static Map<String, EventLog> logMap = new HashMap<>();

    public static void processJsonLine(String line){
        JSONObject jsonObject = new JSONObject(line);

        EventLog eventLog = null;
        if (!jsonObject.has("type")) {
            eventLog = new EventLog(jsonObject.getString("id"),
                    jsonObject.getString("state"),
                    jsonObject.getLong("timestamp"), null, null
            );
        }
        else {
            eventLog = new EventLog(jsonObject.getString("id"),
                    jsonObject.getString("state"),
                    jsonObject.getLong("timestamp"),
                    jsonObject.getString("type"),
                    jsonObject.getString("host")
            );
        }

        //////////////////////////////////////////////////////
        if(logMap.containsKey(eventLog.getId())){
            EventLog lastEventLog = logMap.get(eventLog.getId());

            long duration = Math.abs(Math.abs(eventLog.getTimestamp())-Math.abs(lastEventLog.getTimestamp()));
            if(duration>4){
                AlertLogDao.save(new AlertLog(eventLog.getId(),duration,eventLog.getType(),eventLog.getHost(),true));
                Logger.getGlobal().log(Level.INFO,eventLog.toString());
                logMap.remove(eventLog.getId());
            }else{
                logMap.remove(eventLog.getId());
            }

        }else{
            logMap.put(eventLog.getId(),eventLog);
        }
        /////////////////////////////////////////////////////
    }
}
