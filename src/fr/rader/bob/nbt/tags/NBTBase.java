package fr.rader.bob.nbt.tags;

public class NBTBase {

    private int length;
    private int id;
    private String name = null;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] toByteArray() {
        return null;
    }

    public NBTByte getAsNBTByte() {
        return (NBTByte) this;
    }

    public NBTShort getAsNBTShort() {
        return (NBTShort) this;
    }

    public NBTInt getAsNBTInt() {
        return (NBTInt) this;
    }

    public NBTLong getAsNBTLong() {
        return (NBTLong) this;
    }

    public NBTFloat getAsNBTFloat() {
        return (NBTFloat) this;
    }

    public NBTDouble getAsNBTDouble() {
        return (NBTDouble) this;
    }

    public NBTByteArray getAsNBTByteArray() {
        return (NBTByteArray) this;
    }

    public NBTString getAsNBTString() {
        return (NBTString) this;
    }

    public NBTIntArray getAsNBTIntArray() {
        return (NBTIntArray) this;
    }

    public NBTLongArray getAsNBTLongArray() {
        return (NBTLongArray) this;
    }

    public int getAsByte() {
        return ((NBTByte) this).getValue();
    }

    public int getAsShort() {
        return ((NBTShort) this).getValue();
    }

    public int getAsInt() {
        return ((NBTInt) this).getValue();
    }

    public long getAsLong() {
        return ((NBTLong) this).getValue();
    }

    public float getAsFloat() {
        return ((NBTFloat) this).getValue();
    }

    public double getAsDouble() {
        return ((NBTDouble) this).getValue();
    }

    public byte[] getAsByteArray() {
        return ((NBTByteArray) this).getValue();
    }

    public String getAsString() {
        return ((NBTString) this).getValue();
    }

    public NBTList getAsList() {
        return (NBTList) this;
    }

    public NBTCompound getAsCompound() {
        return (NBTCompound) this;
    }

    public int[] getAsIntArray() {
        return ((NBTIntArray) this).getValue();
    }

    public long[] getAsLongArray() {
        return ((NBTLongArray) this).getValue();
    }
}