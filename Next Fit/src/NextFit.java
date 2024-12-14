import java.util.*;

public class NextFit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NextFitAllocator allocator = new NextFitAllocator();

        System.out.println("Enter the number of memory blocks:");
        int numBlocks = scanner.nextInt();
        for (int i = 0; i < numBlocks; i++) {
            System.out.println("Enter size for block " + (i + 1) + ":");
            int size = scanner.nextInt();
            allocator.addMemoryBlock(size);
        }

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add process");
            System.out.println("2. Allocate processes");
            System.out.println("3. Terminate process");
            System.out.println("4. View memory blocks");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter size of the process:");
                    int size = scanner.nextInt();
                    allocator.addProcess(size);
                    break;
                case 2:
                    allocator.allocateProcesses();
                    break;
                case 3:
                    System.out.println("Enter the process ID to terminate:");
                    int processId = scanner.nextInt();
                    allocator.terminateProcess(processId);
                    break;
                case 4:
                    allocator.printMemoryBlocks();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

        scanner.close();
    }
}
