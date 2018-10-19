package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if(event.getType() != DOOR_CLOSED) return;
        // событие от двери
        for (Room room: smartHome.getRooms()) {
            for (Door door: room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if(room.getName().equals("hall")) {
                        smartHome.turnOffLights();
                    }
                }
            }
        }
    }
}
