import java.util.*;
import java.util.stream.Collectors;

enum CarType { SEDAN, SUV, ELECTRIC, HATCHBACK, TRUCK }

class CarWithType extends Car {
    private final CarType type;

    public CarWithType(String vin, String model, String manufacturer, int year,
                       int mileage, double price, CarType type) {
        super(vin, model, manufacturer, year, mileage, price);
        this.type = type;
    }

    public CarType getType() { return type; }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + type;
    }
}
public class CarDealership {
    /*
    Реализуйте класс CarDealership, содержащий список автомобилей. У каждой машины:
VIN, модель, производитель, год выпуска, пробег, цена, тип (enum: SEDAN, SUV, ELECTRIC и т.д.).
Методы:
1.     Добавить машину в автоцентр (проверять дубликаты по VIN).
2.     Найти все машины указанного производителя (использовать Stream).
3.     Вывести среднюю цену машин определённого типа (SUV, ELECTRIC и др.).
4.     Вернуть список машин, отсортированных по году выпуска (от новых к старым).
5.     Дополнительно: реализовать статистику:
o	Количество машин каждого типа.
o	Самая старая и самая новая машина в наличии.

 Создайте меню с возможностью вызова каждого метода (например, через Scanner).

     */
    private final Set<CarWithType> cars = new HashSet<>();

    public boolean addCar(CarWithType car) {
        return cars.add(car);
    }

    public List<CarWithType> findCarsByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    public double getAveragePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
    }

    public List<CarWithType> getCarsSortedByYear() {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList());
    }

    public Map<CarType, Long> getCarTypeStatistics() {
        return cars.stream()
                .collect(Collectors.groupingBy(CarWithType::getType, Collectors.counting()));
    }

    public Map<String, CarWithType> getOldestAndNewestCars() {
        Map<String, CarWithType> result = new HashMap<>();
        cars.stream()
                .max(Comparator.comparingInt(Car::getYear))
                .ifPresent(c -> result.put("newest", c));
        cars.stream()
                .min(Comparator.comparingInt(Car::getYear))
                .ifPresent(c -> result.put("oldest", c));
        return result;
    }


}
