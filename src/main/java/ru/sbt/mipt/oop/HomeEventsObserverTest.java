package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeEventsObserverTest {
    public static class OneTimeEventProvider implements SensorEventProvider {

        private SensorEvent event;
        private int callsCount = 0;

        public OneTimeEventProvider(SensorEvent event) {
            this.event = event;
        }

        @Override
        public SensorEvent getNextSensorEvent() {
            if (callsCount > 0) {
                return null;
            }
            callsCount++;
            return event;
        }

    }
    public static class CountingEventProccesor implements EventProcessor {
        private int count = 0;
        private SensorEvent receiveEvent;

        @Override
        public void processEvent(SmartHome smartHome, SensorEvent event) {
            receiveEvent = event;
            count++;
        }

        public SensorEvent getReceiveEvent() {
            return receiveEvent;
        }

        public int getCount() {
            return count;
        }

    }

    @Test
    public void test() {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new OneTimeEventProvider(sensorEvent)
        );
        CountingEventProccesor countingEventProccesor1 = new CountingEventProccesor();
        CountingEventProccesor countingEventProccesor2 = new CountingEventProccesor();
        homeEventsObserver.registerEventProcessor(countingEventProccesor1);
        homeEventsObserver.registerEventProcessor(countingEventProccesor2);
        homeEventsObserver.runEventsCycle(new SmartHome());
        assertEquals(1, countingEventProccesor1.getCount());
        assertEquals(sensorEvent, countingEventProccesor1.getReceiveEvent());
        assertEquals(1, countingEventProccesor2.getCount());
        assertEquals(sensorEvent, countingEventProccesor2.getReceiveEvent());
    }
}

