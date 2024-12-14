import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NextFitAllocator {
    private List<MemoryBlock> blocks;
    private List<Process> processes;
    private int lastAllocatedIndex;

    public NextFitAllocator() {
        blocks = new ArrayList<>();
        processes = new ArrayList<>();
        lastAllocatedIndex = 0;
    }

    public void addMemoryBlock(int size) {
        blocks.add(new MemoryBlock(blocks.size() + 1, size));
    }

    public void addProcess(int size) {
        processes.add(new Process(processes.size() + 1, size));
    }

    public void allocateProcesses() {
        int[] allocation = new int[processes.size()];
        Arrays.fill(allocation, -1);

        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);

            // Skip if already allocated
            if (allocation[i] != -1) {
                continue;
            }

            // Start searching for a block from the last allocated position
            int startIndex = lastAllocatedIndex;
            boolean allocated = false;

            for (int j = 0; j < blocks.size(); j++) {
                int currentIndex = (startIndex + j) % blocks.size();
                MemoryBlock block = blocks.get(currentIndex);

                if (block.size >= process.size) {
                    allocation[i] = block.id;
                    block.size -= process.size;
                    lastAllocatedIndex = (currentIndex + 1) % blocks.size();
                    allocated = true;
                    break;
                }
            }

            if (!allocated) {
                System.out.println("Process " + process.id + " cannot be allocated (insufficient memory).");
            }
        }

        printAllocation(allocation);
    }

    public void terminateProcess(int processId) {
        if (processId < 1 || processId > processes.size()) {
            System.out.println("Invalid process ID!");
            return;
        }

        Process process = processes.get(processId - 1);
        for (MemoryBlock block : blocks) {
            if (block.id == lastAllocatedIndex) {
                block.size += process.size;
                break;
            }
        }
        processes.remove(processId - 1);
        System.out.println("Process " + processId + " terminated and memory deallocated.");
    }

    private void printAllocation(int[] allocation) {
        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < processes.size(); i++) {
            System.out.print(processes.get(i).id + "\t\t" + processes.get(i).size + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i]);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }

    public void printMemoryBlocks() {
        System.out.println("\nCurrent Memory Blocks:");
        for (MemoryBlock block : blocks) {
            System.out.println(block);
        }
    }
}