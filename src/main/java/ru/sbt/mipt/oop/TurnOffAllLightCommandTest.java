package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnOffAllLightCommandTest {
    private SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl();
    private SmartHome smartHome = new SmartHome();
    private Light light1 = new Light("1", true), light2 = new Light("2", true);

    @Test
    public void test() {
        List<Light> lights = new ArrayList<>();
        lights.add(light1);
        lights.add(light2);
        smartHome.addRoom(new Room(lights, null, "firstRoom"));
        smartHomeRemoteControl.addCommand("A", new TurnOffAllLightCommand(smartHome));
        smartHomeRemoteControl.onButtonPressed("A");

        assertFalse(light1.isOn());
        assertFalse(light1.isOn());
    }

}