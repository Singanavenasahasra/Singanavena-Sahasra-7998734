public class CommandPatternTest {

    interface Command {
        void execute();
    }

    static class Light {
        public void turnOn() { 
            System.out.println("The light is ON."); 
        }
        public void turnOff() { 
            System.out.println("The light is OFF."); 
        }
    }

    static class LightOnCommand implements Command {
        private final Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() { 
            light.turnOn(); 
        }
    }

    static class LightOffCommand implements Command {
        private final Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() { 
            light.turnOff(); 
        }
    }

    static class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            if (command != null) {
                command.execute();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 9: Command Pattern ---");
        
        Light smartLight = new Light();
        RemoteControl remote = new RemoteControl();

        remote.setCommand(new LightOnCommand(smartLight));
        remote.pressButton();

        remote.setCommand(new LightOffCommand(smartLight));
        remote.pressButton();

        System.out.println("--- Execution Finished Successfully! ---");
    }
}