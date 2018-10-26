package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private static HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
            new RandomSensorEventProvider());

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.looadSmartHome();
        smartHome.addEventsObserver(homeEventsObserver);
        homeEventsObserver.registerEventProcessor(new LightsEventProcessor());
        homeEventsObserver.registerEventProcessor(new DoorEventProcessor());
        homeEventsObserver.registerEventProcessor(new HallDoorEventProcessor());
        homeEventsObserver.runEventsCycle(smartHome);
    }

}
