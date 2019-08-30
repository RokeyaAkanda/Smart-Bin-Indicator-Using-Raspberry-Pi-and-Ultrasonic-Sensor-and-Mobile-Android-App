package edu.northsouth.smartbindhaka.handler;


import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.northsouth.smartbindhaka.busmodels.DistanceModel;

public class ConnectionService implements MqttCallback {
    MqttConnectionHandler mqttConnectionHandler;
    ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Override
    public void connectionLost(Throwable cause) {
        initiateConnections(); // reconnect on lost connection.
    }


    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        Log.d("imad", mqttMessage.toString());
        Float distance = Float.valueOf(mqttMessage.toString());
        DistanceModel distanceModel = new DistanceModel();
        distanceModel.setDistance(distance);

        EventBus.getDefault().post(distanceModel);

    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    public void initiateConnections() {
        mqttConnectionHandler = MqttConnectionHandler.getInstance();
        mqttConnectionHandler.connect();
        mqttConnectionHandler.addCallback(ConnectionService.this);
    }

}