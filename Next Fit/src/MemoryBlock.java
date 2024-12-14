class MemoryBlock {
    int size;
    int id;

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Block ID: " + id + ", Size: " + size;
    }
}