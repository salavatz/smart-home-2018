package ru.sbt.mipt.oop;

@FunctionalInterface
public interface Action {
    void execute(Item object, Room room);
}
