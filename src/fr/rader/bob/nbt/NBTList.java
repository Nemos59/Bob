package fr.rader.bob.nbt;

import fr.rader.bob.DataReader;
import fr.rader.bob.DataWriter;

import java.util.ArrayList;
import java.util.List;

public class NBTList extends NBTBase {

    private List<NBTBase> values = new ArrayList<>();

    private int tagID;

    public NBTList(String tagName, int tagID) {
        this.tagID = tagID;

        setId(0x09);
        setName(tagName);
    }

    public NBTList(byte[] rawData, boolean hasName) {
        DataReader reader = new DataReader(rawData);

        if(hasName) {
            setId(reader.readByte());
            setName(reader.readString(reader.readShort()));
        }

        readData(reader);
    }

    private void readData(DataReader reader) {
        tagID = reader.readByte();

        int length = reader.readInt();
        for(int i = 0; i < length; i++) {
            switch(tagID) {
                case 1:
                    addByte(reader.readByte());
                    break;
                case 2:
                    addShort(reader.readShort());
                    break;
                case 3:
                    addInt(reader.readInt());
                    break;
                case 4:
                    addLong(reader.readLong());
                    break;
                case 5:
                    addFloat(reader.readFloat());
                    break;
                case 6:
                    addDouble(reader.readDouble());
                    break;
                case 7:
                    addByteArray(reader.readFollowingBytes(reader.readInt()));
                    break;
                case 8:
                    addString(reader.readString(reader.readShort()));
                    break;
                case 9:
                    addList(new NBTList(reader.getFromOffset(false), false));
                    break;
                case 10:
                    addCompound(new NBTCompound(reader.getFromOffset(false), false));
                    break;
                case 11:
                    addIntArray(reader.readIntArray(reader.readInt()));
                    break;
                case 12:
                    addLongArray(reader.readLongArray(reader.readInt()));
                    break;
            }

            reader.addOffset(values.get(i).getLength());
        }

        setLength(reader.getOffset());
    }

    public NBTList() {

    }

    public NBTBase[] getComponents() {
        return values.toArray(new NBTBase[0]);
    }

    @Override
    public byte[] toByteArray() {
        DataWriter writer = new DataWriter();

        if(getName() != null) {
            writer.writeByte(0x09);
            writer.writeShort(getName().length());
            writer.writeString(getName());
        }

        writer.writeByte(this.tagID);
        writer.writeInt(values.size());

        for(NBTBase base : values) {
            writer.writeByteArray(base.toByteArray());
        }

        return writer.getData();
    }

    public NBTList addByte(int value) {
        values.add(new NBTByte(value));
        return this;
    }

    public void removeByte(int value) {
        for(NBTBase base : values) {
            if(base instanceof NBTByte) {
                if(base.getAsByte() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addShort(int value) {
        values.add(new NBTShort(value));
        return this;
    }

    public void removeShort(int value) {
        for(NBTBase base : values) {
            if(base instanceof NBTShort) {
                if(base.getAsShort() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addInt(int value) {
        values.add(new NBTInt(value));
        return this;
    }

    public void removeInt(int value) {
        for(NBTBase base : values) {
            if(base instanceof NBTInt) {
                if(base.getAsInt() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addLong(long value) {
        values.add(new NBTLong(value));
        return this;
    }

    public void removeLong(long value) {
        for(NBTBase base : values) {
            if(base instanceof NBTLong) {
                if(base.getAsLong() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addFloat(float value) {
        values.add(new NBTFloat(value));
        return this;
    }

    public void removeFloat(float value) {
        for(NBTBase base : values) {
            if(base instanceof NBTFloat) {
                if(base.getAsFloat() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addDouble(double value) {
        values.add(new NBTDouble(value));
        return this;
    }

    public void removeDouble(double value) {
        for(NBTBase base : values) {
            if(base instanceof NBTDouble) {
                if(base.getAsDouble() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addByteArray(byte[] value) {
        values.add(new NBTByteArray(value));
        return this;
    }

    public void removeByteArray(byte[] value) {
        for(NBTBase base : values) {
            if(base instanceof NBTByteArray) {
                if(base.getAsByteArray() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addString(String value) {
        values.add(new NBTString(value));
        return this;
    }

    public void removeString(String value) {
        for(NBTBase base : values) {
            if(base instanceof NBTString) {
                if(base.getAsString().equals(value)) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addList(NBTList value) {
        values.add(value);
        return this;
    }

    public void removeList(NBTList value) {
        for(NBTBase base : values) {
            if(base instanceof NBTList) {
                if(base.getAsList() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addCompound(NBTCompound value) {
        values.add(value);
        return this;
    }

    public void removeCompound(NBTCompound value) {
        for(NBTBase base : values) {
            if(base instanceof NBTCompound) {
                if(base.getAsCompound() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addIntArray(int[] value) {
        values.add(new NBTIntArray(value));
        return this;
    }

    public void removeIntArray(int[] value) {
        for(NBTBase base : values) {
            if(base instanceof NBTIntArray) {
                if(base.getAsIntArray() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }

    public NBTList addLongArray(long[] value) {
        values.add(new NBTLongArray(value));
        return this;
    }

    public void removeLongArray(long[] value) {
        for(NBTBase base : values) {
            if(base instanceof NBTLongArray) {
                if(base.getAsLongArray() == value) {
                    values.remove(base);
                    return;
                }
            }
        }
    }
}
