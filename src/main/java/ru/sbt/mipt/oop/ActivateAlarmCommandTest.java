package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivateAlarmCommandTest {
    private SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl();
    private SmartHome smartHome = new SmartHome();
    private Light light1 = new Light("1", false), light2 = new Light("2", false);

    @Test
    public void test() {
        List<Light> lights = new ArrayList<>();
        lights.add(light1);
        lights.add(light2);
        smartHome.addRoom(new Room(lights, null, "firstRoom"));
        smartHomeRemoteControl.addCommand("A", new ActivateAlarmCommand(smartHome, "123"));
        smartHomeRemoteControl.onButtonPressed("A");

        assertTrue(smartHome.getAlarm().isActivated());
    }
}