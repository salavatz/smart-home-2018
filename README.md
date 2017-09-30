# smart-home

Project Smart Home implements 'smart home''.
The house has sensors which are connected to a central server and send events in case of lights on/off doors open/close.
The system receives events of type SensorEvent.
<br/>
SmartHome - the home itself, contains rooms<br/>
Room - room, contains doors and lights<br/>
Door - door (interroom or entrance),<br/>
Light - source of light (lighbulb and etc)<br/>
SensorEvent - represents physical world event<br/>
SenserEventType - type of event, now there are 4 types<br/>
SensorCommand - command which allows to programmatically manage the physical world (turn on/off lights, open/close doorlocks)<br/>
CommandType - type of command, now only 1 type (turn ligths off)<br/>