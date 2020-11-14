package fr.rader.bob.protocol.packets;

import fr.rader.bob.DataReader;
import fr.rader.bob.DataWriter;
import fr.rader.bob.protocol.Packet;
import fr.rader.bob.types.Position;

public class SpawnPosition implements Packet {

    private int packetID;
    private int timestamp;
    private int size;

    private Position location;

    public SpawnPosition(int id, int timestamp, int size, byte[] rawData) {
        this.packetID = id;
        this.timestamp = timestamp;
        this.size = size;

        DataReader reader = new DataReader(rawData);

        location = reader.readPosition();
    }

    @Override
    public byte[] writePacket() {
        DataWriter writer = new DataWriter();

        writer.writeInt(timestamp);
        writer.writeInt(size);
        writer.writeVarInt(packetID);

        writer.writePosition(location);

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

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }
}