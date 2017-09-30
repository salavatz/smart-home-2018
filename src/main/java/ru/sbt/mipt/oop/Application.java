package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                for (Room room : smartHome.getRooms()) {
                    for (Light light : room.getLights()) {
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
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                for (Room room : smartHome.getRooms()) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            if (event.getType() == DOOR_OPEN) {
                                door.setOpen(true);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                            } else {
                                door.setOpen(false);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                                // if we're leaving the hall and closing the door
                                // then we want to turn all the lights off -- this is a smart home after all!
                                if (room.getName().equals("hall")) {
                                    for (Room homeRoom : smartHome.getRooms()) {
                                        for (Light light : homeRoom.getLights()) {
                                            light.setOn(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            sendCommand(command);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            event = getNextSensorEvent();
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
