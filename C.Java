// Java Program: Generic Data Container using
// Generic Class, Generic Method, and Bounded Type Parameters

class DataContainer<T> {
    private T data;

    // Constructor
    public DataContainer(T data) {
        this.data = data;
    }

    // Getter
    public T getData() {
        return data;
    }

    // Setter
    public void setData(T data) {
        this.data = data;
    }

    // Display data
    public void display() {
        System.out.println("Data: " + data);
    }

    // Generic Method
    public static <E> void printArray(E[] array) {
        System.out.println("Array Elements:");
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Bounded Type Parameter
    public static <N extends Number> double calculateAverage(N[] numbers) {
        double sum = 0;

        for (N num : numbers) {
            sum += num.doubleValue();
        }

        return sum / numbers.length;
    }
}

public class GenericDataContainer {
    public static void main(String[] args) {

        // Generic Class with Integer
        DataContainer<Integer> intData = new DataContainer<>(100);
        intData.display();

        // Generic Class with String
        DataContainer<String> strData = new DataContainer<>("Hello Java");
        strData.display();

        // Generic Class with Double
        DataContainer<Double> doubleData = new DataContainer<>(45.75);
        doubleData.display();

        // Generic Method
        Integer[] intArray = {10, 20, 30, 40, 50};
        String[] strArray = {"Apple", "Banana", "Orange"};

        DataContainer.printArray(intArray);
        DataContainer.printArray(strArray);

        // Bounded Type Parameter
        Double[] marks = {85.5, 90.0, 78.5, 88.0};

        double average = DataContainer.calculateAverage(marks);

        System.out.println("Average = " + average);
    }
}
