package com.hemant.droidstreamer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager : SensorManager
    private lateinit var mAccelerometer : Sensor
    private lateinit var mLight : Sensor
    private lateinit var mGyroscope : Sensor
    private lateinit var mGravity : Sensor
    private lateinit var mRotation : Sensor
    private lateinit var mProximity: Sensor
    private lateinit var mMagnetometer: Sensor
    private lateinit var mqttClient : MqttClient
    private lateinit var persistence: MemoryPersistence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        mRotation = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    fun start(view: View) {
        val brokerInput=findViewById<EditText>(R.id.brokerInput)
        val persistence = MemoryPersistence()
        mqttClient= MqttClient(brokerInput.text.toString(),"DroidStreamer",persistence)
        val connOpts = MqttConnectOptions()
        mqttClient.connect(connOpts)
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mRotation, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop(view: View) {
        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                mqttClient.publish("DroidStreamer/Accelerometer",MqttMessage(event.values.joinToString().toByteArray()))
            }
            if (event.sensor.type == Sensor.TYPE_LIGHT) {
                mqttClient.publish("DroidStreamer/Light", MqttMessage(event.values.joinToString().toByteArray()))
            }
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                mqttClient.publish("DroidStreamer/Gyroscope",MqttMessage(event.values.joinToString().toByteArray()))
            }
            if (event.sensor.type == Sensor.TYPE_GRAVITY) {
                mqttClient.publish("DroidStreamer/Gravity", MqttMessage(event.values.joinToString().toByteArray()))
            }
            if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
                mqttClient.publish("DroidStreamer/Rotation", MqttMessage(event.values.joinToString().toByteArray()))
            }
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                mqttClient.publish("DroidStreamer/Proximity", MqttMessage(event.values.joinToString().toByteArray()))
            }
            if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                mqttClient.publish("DroidStreamer/Magnetometer", MqttMessage(event.values.joinToString().toByteArray()))
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}