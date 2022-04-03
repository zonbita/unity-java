import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class Client {
    private Socket client;
    private ReceiveListener listener;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String id = UUID.randomUUID().toString();
    public volatile Position position;
    public volatile Rotation rotation;
    public volatile Action action;
    public Client(Socket client, ReceiveListener listener) throws IOException {
        this.client = client;
        this.listener = listener;
        inputStream = client.getInputStream();
        outputStream = client.getOutputStream();
        position = new Position();
        rotation = new Rotation();
        action = new Action();

        new ReadThread().start();
        sendStart();
    }
    
    public String getId() {
        return id;
    }
    // Gửi khí bắt đầu
    private void sendStart(){
        try {

            JSONObject json = new JSONObject();
            json.put("id", id);
            sendToClient(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    // Gửi json đến client
    public void sendToClient(String json){
        System.out.println("sendToClient");
        try {

            byte[] bytes = json.getBytes();
            byte[] bytesSize = intToByteArray(json.length());
            outputStream.write(bytesSize, 0, 4);
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Thực hiện liên tục
    private class ReadThread extends Thread{

        @Override
        public void run() {
        super.run();
        byte[] bytes = new byte[1024];

        while (!client.isClosed()){
            try {
                int data = inputStream.read(bytes);
                if (data != -1){
                    String s = new String(bytes, 0, data);
                        System.out.println(s);

                    JSONObject j = new JSONObject(s);
                    String action_name = j.optString("action").toString();
                    action.active(Client.this, action_name);

//                       JSONObject jsonObject = new JSONObject(string);
//                        JSONObject pos = jsonObject.optJSONObject("position");
//                            position.x = pos.optString("X");
//                            position.y = pos.optString("Y");
//                            position.z = pos.optString("Z");
//
//                        JSONObject rot = jsonObject.optJSONObject("rotation");
//                            rotation.x = rot.optString("X");
//                            rotation.y = rot.optString("Y");
//                            rotation.z = rot.optString("Z");
//                            rotation.w = rot.optString("W");

                    listener.dataReceive(Client.this, s);
                    //break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Close");

        }
    }

    public static byte[] intToByteArray(int a)
    {
        byte[] ret = new byte[4];
        ret[0] = (byte) (a & 0xFF);
        ret[1] = (byte) ((a >> 8) & 0xFF);
        ret[2] = (byte) ((a >> 16) & 0xFF);
        ret[3] = (byte) ((a >> 24) & 0xFF);
        return ret;
    }

    public void Send_Action_toClient(String object){
        try {
            JSONObject json = new JSONObject();
            json.put("action", object);
            sendToClient(json.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
