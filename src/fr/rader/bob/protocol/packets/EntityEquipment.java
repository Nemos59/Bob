package fr.rader.bob.protocol.packets;

import fr.rader.bob.DataWriter;
import fr.rader.bob.protocol.Packet;

public class EntityEquipment implements Packet {

    private int packetID;
    private int timestamp;
    private int size;

    private byte[] rawData;

    public EntityEquipment(int id, int timestamp, int size, byte[] rawData) {
        this.packetID = id;
        this.timestamp = timestamp;
        this.size = size;
        this.rawData = rawData;
    }

    @Override
    public byte[] writePacket() {
        DataWriter writer = new DataWriter();

        writer.writeInt(timestamp);
        writer.writeInt(size);
        writer.writeVarInt(packetID);

        writer.writeByteArray(rawData);

        return writer.getData();
    }

    @Override
    public int getLength() {
        return writePacket().length;
    }

    @Override
    public int getPacketID() {
        return packetID;
    }

    @Override
    public int getTimestamp() {
        return timestamp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}