package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;

import java.util.ArrayList;
import java.util.Collection;

public class EventsManagerAdapter implements EventsManager{

    private final Collection<EventProcessor> eventProcessors = new ArrayList<>();
    private SensorEventsManager sensorEventsManager;

    public EventsManagerAdapter(SensorEventsManager sensorEventsManager) {
        this.sensorEventsManager = sensorEventsManager;
    }

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor) {
        eventProcessors.add(eventProcessor);
    }

    @Override
    public void runEventsCycle(SmartHome smartHome) {
        sensorEventsManager.registerEventHandler(event -> {
            System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId() + "]");

            SensorEvent sensorEvent = transform(event);
            for (EventProcessor eventProcessor: eventProcessors) {
                eventProcessor.processEvent(smartHome, sensorEvent);
            }
        });
        sensorEventsManager.start();
    }

    private SensorEvent transform(CCSensorEvent event) {
        String id = event.getObjectId();
        String type = event.getEventType();
        type = type.replace("Is", "_").toUpperCase();
        SensorEventType sensorEventType = SensorEventType.valueOf(type);
        return new SensorEvent(sensorEventType, id);
    }
}
