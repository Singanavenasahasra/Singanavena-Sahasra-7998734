public class EmployeeRecords {

    public static class Employee {
        private final String employeeId;
        private final String name;
        private final String position;
        private final double salary;

        public Employee(String employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        public String getEmployeeId() { return employeeId; }
        public String getName() { return name; }
        public String getPosition() { return position; }
        public double getSalary() { return salary; }

        @Override
        public String toString() { 
            return name + " [" + position + " - $" + salary + "]"; 
        }
    }

    private final Employee[] employees;
    private int size;

    public EmployeeRecords(int capacity) {
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    public void add(Employee emp) {
        if (size < employees.length) {
            employees[size++] = emp;
            System.out.println("Successfully Added: " + emp.getName());
        } else {
            System.out.println("Error: System full.");
        }
    }

    public void traverse() {
        System.out.println("\n--- Employee Database Roster ---");
        for (int i = 0; i < size; i++) {
            System.out.println("ID: " + employees[i].getEmployeeId() + " | " + employees[i]);
        }
    }

    public void delete(String employeeId) {
        int indexToDelete = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            for (int i = indexToDelete; i < size - 1; i++) {
                employees[i] = employees[i + 1];
            }
            employees[size - 1] = null;
            size--;
            System.out.println("Deleted Record for ID: " + employeeId);
        } else {
            System.out.println("Record not found.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 4: Employee Records ---");
        EmployeeRecords system = new EmployeeRecords(5);

        system.add(new Employee("E101", "John Doe", "Software Engineer", 85000));
        system.add(new Employee("E102", "Jane Smith", "Project Manager", 95000));
        
        system.traverse();
        system.delete("E101");
        system.traverse();
    }
}