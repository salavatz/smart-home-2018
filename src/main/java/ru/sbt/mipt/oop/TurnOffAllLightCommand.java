package ru.sbt.mipt.oop;

public class TurnOffAllLightCommand implements Command {

    private final SmartHome smartHome;

    public TurnOffAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOffOrOnLights(true);
    }
}
