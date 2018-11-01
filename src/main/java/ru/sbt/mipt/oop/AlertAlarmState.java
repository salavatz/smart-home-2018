package ru.sbt.mipt.oop;

public class AlertAlarmState implements AlarmState {
    private final Alarm alarm;
    private final String password;

    public AlertAlarmState(Alarm alarm, String password) {
        this.alarm = alarm;
        this.password = password;
    }

    @Override
    public void deactivate(String deactivatePassword) {
        if (password.equals(deactivatePassword)) {
            alarm.changeState(new OffAlarmState(alarm));
        }
    }

    @Override
    public boolean isActivated() {
        return true;
    }
}
