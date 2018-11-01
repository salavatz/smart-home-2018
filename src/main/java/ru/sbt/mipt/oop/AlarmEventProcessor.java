package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if(!isAlarmEvent(event)) return;
        //событие от сигнализации
        if(event.getType() == ALARM_ACTIVATE) {
            smartHome.getAlarm().activate("4444");
        }
        else {
            smartHome.getAlarm().deactivate("4444");
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE;
    }
}
