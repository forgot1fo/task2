import java.util.*;

public class Car implements Comparable<Car> {
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;
    public Car() {

    }
    public Car(String vin, String model, String manufacturer, int year, int mileage, double price) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }
    //Задание 1
    public static int[] generateCarYears(int count, int minYear, int maxYear) {
        return new Random().ints(count, minYear, maxYear + 1).toArray();
    }

    public static int[] filterCarsAfterYear(int[] years, int minYear) {
        int count = 0;
        for (int year : years) {
            if (year > minYear) count++;
        }

        int[] result = new int[count];
        int index = 0;

        for (int year : years) {
            if (year > minYear) {
                result[index++] = year;
            }
        }

        return result;
    }

    public static double calculateAverageAge(int[] years, int currentYear) {
        if (years.length == 0) return 0;

        int sum = 0;
        for (int year : years) {
            sum += currentYear - year;
        }

        return (double) sum / years.length;
    }
    //2
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj instanceof Car)
        {
            Car car = (Car) obj;
            if (!car.getVin().equals(getVin()))
            {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return vin != null ? vin.hashCode() : 0;
    }
    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d), VIN: %s, %d km, $%.2f",
                manufacturer, model, year, vin, mileage, price);
    }

    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }

}