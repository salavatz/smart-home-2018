package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnOnAllLightCommandTest {

    private SmartHomeRemoteControl smartHomeRemoteControl = new SmartHomeRemoteControl();
    private SmartHome smartHome = new SmartHome();
    private Light light1 = new Light("1", false), light2 = new Light("2", false);

    @Test
    public void test() {
        List<Light> lights = new ArrayList<>();
        lights.add(light1);
        lights.add(light2);
        smartHome.addRoom(new Room(lights, null, "firstRoom"));
        smartHomeRemoteControl.addCommand("A", new TurnOnAllLightCommand(smartHome));
        smartHomeRemoteControl.onButtonPressed("A");

        assertTrue(light1.isOn());
        assertTrue(light1.isOn());
    }

}