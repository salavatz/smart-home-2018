package ru.sbt.mipt.oop;

public class ActiveAlarmState implements AlarmState {
    private final Alarm alarm;
    private final String password;

    public ActiveAlarmState(Alarm alarm, String password) {
        this.alarm = alarm;
        this.password = password;
    }

    @Override
    public void deactivate(String deactivatePassword) {
        if (this.password.equals(deactivatePassword)) {
            alarm.changeState(new OffAlarmState(alarm));
        }
        else {
            alarm.changeState(new AlertAlarmState(alarm, deactivatePassword));
        }
    }

    @Override
    public void startAlarm() {
        alarm.changeState(new AlertAlarmState(alarm, password));
        for(int i = 0; i < 5; i++) {
            System.out.println("Sending sms");
        }
    }

    @Override
    public boolean isActivated() {
        return true;
    }
}
