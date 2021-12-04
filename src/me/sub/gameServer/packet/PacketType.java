package me.sub.gameServer.packet;

public enum PacketType {

    CONNECT(0),
    DISCONNECT(1),
    GROUND(2),
    POSITION(3),
    ROTATION(4),
    POSITION_ROTATION(5),
    DAMAGE(6),
    ENTITY_INIT(7),
    ENTITY_VELOCITY(8);


    byte id;
    PacketType(int type){
        this.id = clamp(type);
    }
    public byte getId(){
        return id;
    }
    public byte clamp(int type){
        type = Math.max(type, Byte.MIN_VALUE);
        type = Math.min(type, Byte.MAX_VALUE);
        return (byte) type;
    }

}
