package ru.sbt.mipt.oop;

public class OffAlarmState implements AlarmState {
    private final Alarm alarm;

    public OffAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String password) {
        alarm.changeState(new ActiveAlarmState(alarm, password));
    }

}
