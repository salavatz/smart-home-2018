package ru.sbt.mipt.oop;

import java.util.HashMap;
import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl{

    private final Map<String, Command> buttonCodeToCommandMap = new HashMap<>();

    @Override
    public void onButtonPressed(String buttonCode) {
        Command command = buttonCodeToCommandMap.get(buttonCode);
        command.execute();
    }

    public void addCommand(String buttonCode, Command command) {
        buttonCodeToCommandMap.put(buttonCode, command);
    }
}
