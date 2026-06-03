import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {

    @SuppressWarnings("unused")
    static class SecretAgent {
        private final String codeName;
        public int agentId;

        public SecretAgent() {
            this.codeName = "007";
            this.agentId = 123;
        }

        public void shareIdentity() {
            System.out.println("Public Method: Agent ID is " + agentId);
        }

        private void classifiedMission(String location) {
            System.out.println("Private Method Called: Target location is " + location);
        }
    }

    public static void main(String[] args) {
        try {
            Class<?> clazz = SecretAgent.class;
            System.out.println("Class Name: " + clazz.getName());

            System.out.println("\n--- Inspecting Public Fields ---");
            Field[] fields = clazz.getFields();
            for (Field f : fields) {
                System.out.println("Found Public Field: " + f.getName() + " (Type: " + f.getType() + ")");
            }

            System.out.println("\n--- Inspecting Methods ---");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println("Found Method: " + m.getName() + " (Return Type: " + m.getReturnType() + ")");
            }

            System.out.println("\n--- Dynamic Instantiation and Manipulation ---");
            Constructor<?> constructor = clazz.getConstructor();
            Object agentInstance = constructor.newInstance();

            Method publicMethod = clazz.getMethod("shareIdentity");
            publicMethod.invoke(agentInstance);

            Field privateField = clazz.getDeclaredField("codeName");
            privateField.setAccessible(true); 
            System.out.println("Bypassed Security - Private Field Value: " + privateField.get(agentInstance));

            Method privateMethod = clazz.getDeclaredMethod("classifiedMission", String.class);
            privateMethod.setAccessible(true); 
            privateMethod.invoke(agentInstance, "London");

        } catch (NoSuchFieldException | NoSuchMethodException | SecurityException 
                 | InstantiationException | IllegalAccessException | IllegalArgumentException 
                 | InvocationTargetException e) {
            System.out.println("Reflection management failure: " + e.getMessage());
        }
    }
}