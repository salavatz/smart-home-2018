package ru.sbt.mipt.oop;

public interface AlarmState {
    default void activate(String password) {
    };
    default void deactivate(String password) {
    };
    default void startAlarm() {
    };
    default boolean isActivated() {
        return false;
    };
}
