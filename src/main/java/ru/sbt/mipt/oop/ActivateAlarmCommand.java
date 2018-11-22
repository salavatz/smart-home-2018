package ru.sbt.mipt.oop;

public class ActivateAlarmCommand implements Command{

    private final SmartHome smartHome;
    private final String password;

    public ActivateAlarmCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(password);
    }
}
