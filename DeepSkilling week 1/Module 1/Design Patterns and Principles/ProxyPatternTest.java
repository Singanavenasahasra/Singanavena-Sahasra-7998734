public class ProxyPatternTest {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String fileName;

        public RealImage(String fileName) {
            this.fileName = fileName;
            loadFromRemoteServer();
        }

        private void loadFromRemoteServer() {
            System.out.println("Loading " + fileName + " from remote server...");
        }

        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }
    }

    static class ProxyImage implements Image {
        private RealImage realImage;
        private final String fileName;

        public ProxyImage(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 6: Proxy Pattern ---");
        
        Image image = new ProxyImage("photo.png");

        System.out.println("First call:");
        image.display();

        System.out.println("\nSecond call:");
        image.display();

        System.out.println("--- Execution Finished Successfully! ---");
    }
}