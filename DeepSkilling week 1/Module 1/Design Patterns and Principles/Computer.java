public class Computer {
    private final String CPU;
    private final String RAM;
    private final String storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    public String getCPU() { return CPU; }
    public String getRAM() { return RAM; }
    public String getStorage() { return storage; }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 3: Builder Pattern ---");
        
        Computer comp1 = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setStorage("1TB SSD")
                .build();

        Computer comp2 = new Computer.Builder()
                .setCPU("AMD Ryzen 5")
                .setRAM("8GB")
                .build();

        System.out.println("Config 1 -> CPU: " + comp1.getCPU() + " | RAM: " + comp1.getRAM() + " | Storage: " + comp1.getStorage());
        System.out.println("Config 2 -> CPU: " + comp2.getCPU() + " | RAM: " + comp2.getRAM() + " | Storage: " + comp2.getStorage());
        
        System.out.println("--- Execution Finished Successfully! ---");
    }
}