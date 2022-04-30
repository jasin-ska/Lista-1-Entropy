import java.util.Objects;

public class BytePair {

    private final byte currByte;
    private final byte prevByte;

    public BytePair(byte currByte, byte prevByte) {
        this.currByte=currByte;
        this.prevByte=prevByte;
    }

    public byte getCurrByte() {
        return currByte;
    }

    public byte getPrevByte() {
        return prevByte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BytePair bytePair = (BytePair) o;
        return currByte == bytePair.currByte && prevByte == bytePair.prevByte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currByte, prevByte);
    }
}
