package ru.sbt.mipt.oop;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {

    @Test
    void changeState() {
        Alarm alarm = new Alarm();
        alarm.changeState(new AlertAlarmState(alarm, "4444"));
        assertThat(alarm.getState(), instanceOf(AlertAlarmState.class));
    }

    @Test
    void activate() {
        Alarm alarm = new Alarm();
        alarm.activate("4444");
        assertThat(alarm.getState(), instanceOf(ActiveAlarmState.class));
    }

    @Test
    void deactivate() {
        Alarm alarm = new Alarm();
        alarm.activate("4444");
        alarm.deactivate("4444");
        assertThat(alarm.getState(), instanceOf(OffAlarmState.class));
        alarm.activate("4444");
        alarm.startAlarm();
        alarm.deactivate("4444");
        assertThat(alarm.getState(), instanceOf(OffAlarmState.class));
    }

    @Test
    void startAlarm() {
        Alarm alarm = new Alarm();
        alarm.activate("4444");
        alarm.startAlarm();
        assertThat(alarm.getState(), instanceOf(AlertAlarmState.class));
    }

    @Test
    void isActivated() {
        Alarm alarm = new Alarm();
        assertEquals(alarm.isActivated(), false);
        alarm.activate("4444");
        assertEquals(alarm.isActivated(), true);
        alarm.startAlarm();
        assertEquals(alarm.isActivated(), true);
    }
}