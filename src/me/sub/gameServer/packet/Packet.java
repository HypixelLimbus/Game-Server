package me.sub.gameServer.packet;

import java.util.ArrayList;
import java.util.Arrays;

public class Packet {

    byte dataType;
    ArrayList<String> data;

    public Packet(byte dataType, String... dataInfo){
        this.dataType = dataType;
        data = new ArrayList<>();
        data.addAll(Arrays.asList(dataInfo));
    }


    public int getDataType() {
        return dataType;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public static Packet parseData(String data){
        Packet packet;
        byte type = Byte.parseByte(data.split("`")[0]);
        String dataUnparsed = data.split("`")[1];
        packet = new Packet(type, dataUnparsed.split("~"));
        return packet;
    }

    public static String writePacket(Packet packet){
        String s = "";
        int i = 0;
        for(String str : packet.data){
            s += str + (i >= packet.data.size() - 1 ? "" : "~");
            i++;
        }
        return packet.dataType + "`" + s;
    }

}
