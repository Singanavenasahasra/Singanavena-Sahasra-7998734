import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Person(String name, int age) {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 21),
            new Person("Bob", 15),
            new Person("Charlie", 30),
            new Person("David", 17)
        );

        System.out.println("All Instances:");
        people.forEach(System.out::println);

        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());

        System.out.println("\nFiltered Adults (Age >= 18):");
        adults.forEach(System.out::println);
    }
}