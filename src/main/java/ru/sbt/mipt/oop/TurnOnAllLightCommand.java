package ru.sbt.mipt.oop;

public class TurnOnAllLightCommand implements Command {

    private final SmartHome smartHome;

    public TurnOnAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOffOrOnLights(false);
    }
}
