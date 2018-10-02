package ru.sbt.mipt.oop;

public class LightsEventProcessor {
    public static void processLightEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                 new LightEvent().setEvent(event, light, room);
            }
        }
    }
}
