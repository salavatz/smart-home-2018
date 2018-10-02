package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEvent implements Event {
    @Override
    public void setEvent(SensorEvent event, Subject subject, Room room){
        Door door = (Door) subject;
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }
}
