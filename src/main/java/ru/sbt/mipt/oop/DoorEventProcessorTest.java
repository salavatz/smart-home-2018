package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventProcessorTest {
    private DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
    private SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private SmartHome smartHome;
    private SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");

    @Test
    public void test() throws IOException {
        smartHome = smartHomeLoader.looadSmartHome();
        doCycle(true);
        doorEventProcessor.processEvent(smartHome, sensorEvent);
        doCycle(false);
    }

    public void doCycle(boolean condition) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("1")) {
                    if (condition) {
                        door.setOpen(true);
                    }
                    else {
                        assertEquals(false, door.isOpen());
                    }
                }
            }
        }
    }
}