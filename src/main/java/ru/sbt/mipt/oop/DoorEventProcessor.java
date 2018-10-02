package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF_ALL;

public class DoorEventProcessor {
    public static void processDoorEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    new DoorEvent().setEvent(event, door, room);
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (event.getType() != DOOR_OPEN && room.getName().equals("hall")) {
                        LightsEventProcessor.processLightEvent(smartHome, new SensorEvent(LIGHT_OFF_ALL, null));
                    }
                }
            }
        }
    }
}
