package ru.sbt.mipt.oop;

public class DecoratorForEventProcessors implements EventProcessor {
    EventProcessor eventProcessor;

    public DecoratorForEventProcessors(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        //режим тревоги
        if (smartHome.getAlarm().isActivated()) {
            smartHome.getAlarm().startAlarm();
        }
        eventProcessor.processEvent(smartHome, event);
    }
}
