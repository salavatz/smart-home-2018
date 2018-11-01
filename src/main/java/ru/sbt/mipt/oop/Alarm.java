package ru.sbt.mipt.oop;

public class Alarm {
    private AlarmState state;

    public Alarm() {
        state = new OffAlarmState(this);
    }

    protected void changeState(AlarmState newState) {
        this.state = newState;
    }

    public void activate(String password) {
        state.activate(password);
    }

    public void deactivate(String password) {
        state.deactivate(password);
    }

    public void startAlarm() {
        state.startAlarm();
    }

    public AlarmState getState() {
        return state;
    }

    public boolean isActivated() {
        return state.isActivated();
    }
}
