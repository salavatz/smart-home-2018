package ru.sbt.mipt.oop;

//import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Collection;

import static ru.sbt.mipt.oop.HomeEvent.LIGHT_OFF_IN_HOUSE;
import static ru.sbt.mipt.oop.HomeEvent.ROOM_ADDED;

public class SmartHome implements Item, HomeObservable {
    private Alarm alarm;
    Collection<Room> rooms;
    private Collection<HomeObserver> observers = new ArrayList<>();

    public SmartHome() {
        alarm = new Alarm();
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addEventsObserver(HomeObserver homeObserver) {
        observers.add(homeObserver);
    }

    public void notifyObservers(HomeEvent event) {
        for (HomeObserver homeObserver : observers) {
            homeObserver.handleEvent(event);
        }
    }

    public void addRoom(Room room) {
        notifyObservers(ROOM_ADDED);
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void turnOffLights() {
        notifyObservers(LIGHT_OFF_IN_HOUSE);
        for (Room homeRoom: getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                SensorCommandExecutor.executeCommand(command);
            }
        }
    }

    @Override
    public void executeAction(Action action, Room room) {
        for (Room r : rooms) {
            r.executeAction(action, r);
        }
    }
}
