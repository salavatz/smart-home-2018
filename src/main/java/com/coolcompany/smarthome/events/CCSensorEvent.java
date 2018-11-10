package com.coolcompany.smarthome.events;

/**
 * Available event types in library v3.7.1:
 *    "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked"
 */
public class CCSensorEvent {
    private final String eventType;
    private final String objectId;

    /**
     * Default constructor
     * @param eventType - defines type of event.
     * @param objectId - id of the object which fired the event (door/lightswitch)
     */
    public CCSensorEvent(String eventType, String objectId) {
        this.eventType = eventType;
        this.objectId = objectId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getObjectId() {
        return objectId;
    }

}