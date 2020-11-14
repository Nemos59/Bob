package fr.rader.bob.protocol.packets;

import fr.rader.bob.DataReader;
import fr.rader.bob.DataWriter;
import fr.rader.bob.protocol.Packet;

public class PlayerPositionAndLook implements Packet {

    private int packetID;
    private int timestamp;
    private int size;

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private int flags;
    private int teleportID;

    public PlayerPositionAndLook(int id, int timestamp, int size, byte[] rawData) {
        this.packetID = id;
        this.timestamp = timestamp;
        this.size = size;

        DataReader reader = new DataReader(rawData);

        x = reader.readDouble();
        y = reader.readDouble();
        z = reader.readDouble();
        yaw = reader.readFloat();
        pitch = reader.readFloat();
        flags = reader.readByte();
        teleportID = reader.readVarInt();
    }

    @Override
    public byte[] writePacket() {
        DataWriter writer = new DataWriter();

        writer.writeInt(timestamp);
        writer.writeInt(size);
        writer.writeVarInt(packetID);

        writer.writeDouble(x);
        writer.writeDouble(y);
        writer.writeDouble(z);
        writer.writeFloat(yaw);
        writer.writeFloat(pitch);
        writer.writeByte(flags);
        writer.writeVarInt(teleportID);

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getTeleportID() {
        return teleportID;
    }

    public void setTeleportID(int teleportID) {
        this.teleportID = teleportID;
    }
}