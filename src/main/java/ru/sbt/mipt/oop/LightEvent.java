package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF_ALL;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEvent implements Event {
    @Override
    public void setEvent(SensorEvent event, Subject subject, Room room) {
        Light light = (Light) subject;
        if (event.getType() == LIGHT_OFF_ALL) {
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            SensorCommandExecutor.executeCommand(command);
            return;
        }
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }

    }
}
