package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventProcessorTest {
    private HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor();
    private SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private SmartHome smartHome = new SmartHome();
    private Light light1 = new Light("1", true), light2 = new Light("2", true);
    private Door door = new Door(true, "1");
    private SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");

    @Test
    public void test() throws IOException {
        List<Light> lights = new ArrayList<>();
        List<Door> doors = new ArrayList<>();
        lights.add(light1);
        lights.add(light2);
        doors.add(door);
        smartHome.addRoom(new Room(lights, doors, "hall"));
        hallDoorEventProcessor.processEvent(smartHome, sensorEvent);
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }
}