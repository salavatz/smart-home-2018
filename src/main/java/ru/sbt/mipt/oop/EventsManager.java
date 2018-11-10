package ru.sbt.mipt.oop;

public interface EventsManager {
    void registerEventProcessor(EventProcessor eventProcessor);
    void runEventsCycle(SmartHome smartHome);
}
