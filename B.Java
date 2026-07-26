import java.util.*;
import java.util.stream.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private int age;

    public Employee(int id, String name, String department, double salary, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department +
               " | Salary: " + salary + " | Age: " + age;
    }
}

public class EmployeeAnalytics {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(101, "Alice", "IT", 65000, 25),
                new Employee(102, "Bob", "HR", 50000, 30),
                new Employee(103, "Charlie", "IT", 80000, 35),
                new Employee(104, "David", "Finance", 70000, 28),
                new Employee(105, "Eva", "HR", 55000, 26),
                new Employee(106, "Frank", "Finance", 90000, 40),
                new Employee(107, "Grace", "IT", 75000, 32)
        );

        // 1. Filter employees with salary > 60000
        System.out.println("Employees with Salary > 60000:");
        employees.stream()
                .filter(e -> e.getSalary() > 60000)
                .forEach(System.out::println);

        // 2. Sort employees by salary
        System.out.println("\nEmployees Sorted by Salary:");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);

        // 3. Group employees by department
        System.out.println("\nEmployees Grouped by Department:");
        Map<String, List<Employee>> grouped =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        grouped.forEach((dept, empList) -> {
            System.out.println(dept + ":");
            empList.forEach(System.out::println);
        });

        // 4. Average salary
        double averageSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println("\nAverage Salary: " + averageSalary);

        // 5. Total salary
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.println("Total Salary: " + totalSalary);

        // 6. Highest paid employee
        Employee highestPaid = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);

        System.out.println("\nHighest Paid Employee:");
        System.out.println(highestPaid);

        // 7. Count employees in each department
        System.out.println("\nEmployee Count by Department:");
        Map<String, Long> countByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()));

        countByDept.forEach((dept, count) ->
                System.out.println(dept + " : " + count));
    }
}
