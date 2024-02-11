# DroidStreamer
## Introduction
This app is useful and IoT. It streams your phone's sensor data to MQTT topics.
You can write own subscriber in any language to recieve this data.
## How to use
Enter Broker address in the box and click start button to start the stream.
Click stop to stop the stream.
clientId of this app is "DroidStreamer"
You have to write or use own subscriber.
I will provide a simple subscriber in Python to you
It streams data on these topics:

	1. Accelerometer: "DroidStreamer/Accelerometer"

	2. Light: "DroidStreamer/Light"

	3. Gyroscope: "DroidStreamer/Gyroscope"

	4. Gravity: "DroidStreamer/Gravity"

	5. Rotation: "DroidStreamer/Rotation"

	6. Proximity: "DroidStreamer/Proximity"

	7. Magnetometer: "DroidStreamer/Magnetometer"
