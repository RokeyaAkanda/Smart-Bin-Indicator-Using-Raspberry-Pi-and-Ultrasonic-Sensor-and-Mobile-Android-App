package edu.northsouth.smartbin;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionService implements MqttCallback {
    MqttConnectionHandler mqttConnectionHandler;
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    BinDao binDao = new BinDao();

    public void connectionLost(Throwable cause) {
        initiateConnections(); // reconnect on lost connection.
    }


    //{ "binId":"ax-bx-cx-dx","depth1":10.5,"depth2":9.8,"date_time":10000000}
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Recived Payload = " + mqttMessage.toString());

        JsonObject payloadObject = Json.parse(mqttMessage.toString()).asObject();
        String binId = payloadObject.getString("binId", "");
        float depth1 = payloadObject.getFloat("depth1", 0);
        float depth2 = payloadObject.getFloat("depth2", 0);
        double date_time = payloadObject.getDouble("date_time", 0);

        BinModel binModel = new BinModel();
        binModel.setBinID(binId);
        binModel.setFirstDepthLevel(depth1);
        binModel.setSecondDepthLevel(depth2);
        binModel.setDateTime(date_time);

        binDao.save(binModel);




        System.out.println("Model ToString: " + binModel.toString());


    }

    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    public void initiateConnections() {
        mqttConnectionHandler = MqttConnectionHandler.getInstance();
        mqttConnectionHandler.connect();
        mqttConnectionHandler.addCallback(ConnectionService.this);
    }
}
