package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    /*private static HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
            new RandomSensorEventProvider());*/
    private static EventsManager eventsManager = new EventsManagerAdapter(new SensorEventsManager());

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        logger.info("Starting configuration...");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SmartHomeLoader smartHomeLoader = context.getBean(SmartHomeLoader.class);
        EventsManager eventsManager = context.getBean(EventsManager.class);
        SmartHome smartHome = smartHomeLoader.looadSmartHome();
        /*smartHome.addEventsObserver(homeEventsObserver);
        homeEventsObserver.registerEventProcessor(new DecoratorForEventProcessors(new LightsEventProcessor()));
        homeEventsObserver.registerEventProcessor(new DecoratorForEventProcessors(new DoorEventProcessor()));
        homeEventsObserver.registerEventProcessor(new DecoratorForEventProcessors(new HallDoorEventProcessor()));
        homeEventsObserver.registerEventProcessor(new AlarmEventProcessor());
        homeEventsObserver.runEventsCycle(smartHome);
        */
        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
        SmartHomeRemoteControl smartHomeRemoteControl = context.getBean(SmartHomeRemoteControl.class);
        remoteControlRegistry.registerRemoteControl(smartHomeRemoteControl, "1");

        eventsManager.registerEventProcessor(new DecoratorForEventProcessors(new LightsEventProcessor()));
        eventsManager.registerEventProcessor(new DecoratorForEventProcessors(new DoorEventProcessor()));
        eventsManager.registerEventProcessor(new DecoratorForEventProcessors(new HallDoorEventProcessor()));
        eventsManager.registerEventProcessor(new AlarmEventProcessor());
        eventsManager.runEventsCycle(smartHome);
    }

}
