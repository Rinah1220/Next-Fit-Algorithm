class Process {
    int size;
    int id;

    public Process(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Process ID: " + id + ", Size: " + size;
    }
}