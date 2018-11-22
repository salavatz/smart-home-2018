package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LightsEventProcessorTest {
    private LightsEventProcessor lightsEventProcessor = new LightsEventProcessor();
    private SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private SmartHome smartHome;
    private SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_OFF, "1");

    @Test
    public void test() throws IOException {
        smartHome = smartHomeLoader.looadSmartHome();
        doCycle(true);
        lightsEventProcessor.processEvent(smartHome, sensorEvent);
        doCycle(false);
    }

    public void doCycle(boolean condition) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("1")) {
                    if (condition) {
                        light.setOn(true);
                    }
                    else {
                        assertEquals(false, light.isOn());
                    }
                }
            }
        }
    }
}