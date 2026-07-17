public class MVCPatternTest {

    static class Student {
        private String name;
        private String id;
        private String grade;

        public Student(String name, String id, String grade) {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getGrade() { return grade; }
        public void setGrade(String grade) { this.grade = grade; }
    }

    static class StudentView {
        public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
            System.out.println("Student Profile:");
            System.out.println("   ID: " + studentId);
            System.out.println("   Name: " + studentName);
            System.out.println("   Grade: " + studentGrade);
        }
    }

    static class StudentController {
        private final Student model;
        private final StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        public void setStudentName(String name) { model.setName(name); }
        public String getStudentName() { return model.getName(); }
        public void setStudentGrade(String grade) { model.setGrade(grade); }
        public String getStudentGrade() { return model.getGrade(); }

        public void updateView() {
            view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 10: MVC Pattern ---");
        
        Student model = new Student("Alex", "S501", "A");
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);
        controller.updateView();

        System.out.println("\nUpdating Grade Record...");
        controller.setStudentGrade("A+");
        controller.updateView();

        System.out.println("--- Execution Finished Successfully! ---");
    }
}