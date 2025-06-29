import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nВыберите задание:");
            System.out.println("1. Работа с массивами");
            System.out.println("2. Управление моделям");
            System.out.println("3. Сравнение автомобилей (equals/hashCode)");
            System.out.println("4. Анализ автопарка (Stream API)");
            System.out.println("5. Автоцентр (полная система)");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число от 0 до 5!");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\n--- Задание 1:  Массивы (Работа с парком машин) ---");
                    get1();
                    break;

                case 2:
                    System.out.println("\n--- Задание 2: Коллекции (Управление моделями)---");
                    get2();
                    break;

                case 3:
                    System.out.println("\n--- Задание 3: equals/hashCode (Сравнение автомобилей) ---");
                    get3();
                    break;

                case 4:
                    System.out.println("\n--- Задание 4: Stream API (Анализ автопарка) ---");
                    get4();
                    break;

                case 5:
                    System.out.println("\n--- Практическое задание: 5: Автоцентр (Реализация системы)---");
                    get5();
                    break;

                case 0:
                    System.out.println("Выход из программы...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }

    }
    public static void get1(){
        int[] years = Car.generateCarYears(50, 2000, 2025);
        System.out.println("Все года выпуска: " + Arrays.toString(years));

        int[] recentCars = Car.filterCarsAfterYear(years, 2015);
        System.out.println("Машины после 2015 года: " + Arrays.toString(recentCars));

        double avgAge = Car.calculateAverageAge(years, 2023);
        System.out.printf("Средний возраст авто: %.1f лет\n", avgAge);
    }
    public static void get2(){
          /*
        Коллекции (Управление моделями)
• Создайте список с названиями моделей машин (например: Toyota Camry, BMW X5). Могут быть дубликаты!
 • Удалите дубликаты, затем отсортируйте модели в обратном алфавитном порядке, выведите на экран, затем сохраните в Set.
 • Реализуйте проверку: если модель содержит слово "Tesla", замените её на "ELECTRO_CAR".

         */
        List<String> models = Arrays.asList(
                "Toyota Camry", "BMW X5", "Tesla Model S", "Toyota Camry",
                "Audi A4", "Tesla Model 3", "BMW X5", "Honda Accord"
        );
        List<String> uniqueModels = new ArrayList<>();

        for (String model : models) {
            if (!uniqueModels.contains(model)) {
                uniqueModels.add(model);
            }
        }
        System.out.println("Уникальные модели: " + uniqueModels);
        Set<String> modelSet = new TreeSet<>(uniqueModels);
        System.out.println("Модели в Set: " + modelSet);
        Set<String> result = new LinkedHashSet<>();
        for (String model : modelSet) {
            result.add(model.contains("Tesla") ? "ELECTRO_CAR" : model);
        }

        System.out.println("Результат: " + result);

    }
    public static  void get3(){
        //Задание 3

        /*

        3. equals/hashCode (Сравнение автомобилей)
• Создайте класс Car с полями: VIN, модель, производитель, год выпуска, пробег, цена
• Переопределите equals и hashCode, чтобы две машины считались одинаковыми только при совпадении VIN.
 • Добавьте в HashSet несколько машин (включая дубликаты по VIN) и убедитесь, что дубликаты не добавляются.
 • Реализуйте Comparable<Car> для сортировки по году выпуска (от новых к старым).*
         */
        Set<Car> carSet = new HashSet<>();
        Car car1 = new Car("VIN1", "Camry", "Toyota", 2020, 30000, 25000);
        Car car2 = new Car("VIN2", "X5", "BMW", 2021, 15000, 60000);
        Car car3 = new Car("VIN1", "Corolla", "Toyota", 2019, 40000, 20000);
        Car car4 = new Car("VIN3", "Corolla", "Toyota", 2017, 50000, 70000);
        System.out.println("Добавление car1: " + carSet.add(car1));
        System.out.println("Добавление car2: " + carSet.add(car2));
        System.out.println("Добавление car3: " + carSet.add(car3));
        System.out.println("Добавление car4: " + carSet.add(car4));
        System.out.println("\nМашины в Set:");
        carSet.forEach(System.out::println);

        // Сортировка по году
        List<Car> sortedCars = new ArrayList<>(carSet);
        Collections.sort(sortedCars);
        System.out.println("\nМашины отсортированные по году:");
        sortedCars.forEach(System.out::println);

    }public static void get4(){
        List<Car> cars = Arrays.asList(
                new Car("VIN1", "Camry", "Toyota", 2020, 45000, 25000),
                new Car("VIN2", "X5", "BMW", 2018, 35000, 60000),
                new Car("VIN3", "Model S", "Tesla", 2022, 10000, 80000),
                new Car("VIN4", "Corolla", "Toyota", 2019, 60000, 20000),
                new Car("VIN5", "Model 3", "Tesla", 2021, 20000, 45000)
        );
        // 1. Отфильтруйте только машины с пробегом меньше 50_000 км
        List<Car> lowMileageCars = cars.stream()
                .filter(c -> c.getMileage() < 50000)
                .collect(Collectors.toList());
        System.out.println("Машины с пробегом < 50_000 км:");
        lowMileageCars.forEach(System.out::println);

        // 2. Отсортируйте по цене (по убыванию).
        List<Car> sortedByPrice = cars.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .collect(Collectors.toList());
        System.out.println("\nМашины отсортированные по цене:");
        sortedByPrice.forEach(System.out::println);

        // 3. Выведите топ-3 самые дорогие машины.
        List<Car> top3Expensive = sortedByPrice.stream().limit(3).collect(Collectors.toList());
        System.out.println("\nТоп-3 самые дорогие машины:");
        top3Expensive.forEach(System.out::println);

        // 4. Посчитайте средний пробег всех машин.
        double avgMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.printf("\nСредний пробег: %.1f км\n", avgMileage);

        // 5. Сгруппируйте машины по производителю в Map<String, List<Car>>.
        Map<String, List<Car>> carsByManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println("\nМашины по производителям:");
        carsByManufacturer.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(c -> System.out.println("  " + c));
        });
    }
    public static void get5() {

        CarDealership dealership = new CarDealership();

        dealership.addCar(new CarWithType("VIN1", "Camry", "Toyota", 2020, 30000, 25000, CarType.SEDAN));
        dealership.addCar(new CarWithType("VIN2", "X5", "BMW", 2021, 15000, 60000, CarType.SUV));
        dealership.addCar(new CarWithType("VIN3", "Model S", "Tesla", 2022, 10000, 80000, CarType.ELECTRIC));
        dealership.addCar(new CarWithType("VIN4", "Corolla", "Toyota", 2019, 40000, 20000, CarType.SEDAN));
        Scanner scanner = new Scanner(System.in);
        subLoop:
        while (true) {
            System.out.println("\nМеню автоцентра:");
            System.out.println("1. Добавить машину");
            System.out.println("2. Найти машины по производителю");
            System.out.println("3. Средняя цена по типу");
            System.out.println("4. Список машин (по году выпуска)");
            System.out.println("5. Статистика");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число от 0 до 5!");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите данные машины:");
                    String vin = null;
                    while (true) {
                        System.out.print("VIN: ");
                        vin = scanner.nextLine().trim();
                        if (!vin.isEmpty()) {
                            break;
                        }
                        System.out.println("Ошибка: VIN не может быть пустым!");
                    }

                    String model = null;
                    while (true) {
                        System.out.print("Модель: ");
                        model = scanner.nextLine().trim();
                        if (!model.isEmpty()) {
                            break;
                        }
                        System.out.println("Ошибка: модель не может быть пустой!");
                    }

                    String manufacturer = null;
                    while (true) {
                        System.out.print("Производитель: ");
                        manufacturer = scanner.nextLine().trim();
                        if (!manufacturer.isEmpty()) {
                            break;
                        }
                        System.out.println("Ошибка: производитель не может быть пустым!");
                    }

                    int year = 0;
                    while (true) {
                        System.out.print("Год выпуска: ");
                        try {
                            year = Integer.parseInt(scanner.nextLine());
                            if (year >= 1900 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
                                break;
                            }
                            System.out.println("Ошибка: год должен быть между 1900 и текущим годом!");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите корректный год цифрами!");
                        }
                    }

                    int mileage = 0;
                    while (true) {
                        System.out.print("Пробег (км): ");
                        try {
                            mileage = Integer.parseInt(scanner.nextLine());
                            if (mileage >= 0) {
                                break;
                            }
                            System.out.println("Ошибка: пробег не может быть отрицательным!");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите пробег цифрами!");
                        }
                    }

                    double price = 0;
                    while (true) {
                        System.out.print("Цена ($): ");
                        try {
                            price = Double.parseDouble(scanner.nextLine());
                            if (price > 0) {
                                break;
                            }
                            System.out.println("Ошибка: цена должна быть положительной!");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите цену цифрами!");
                        }
                    }

                    CarType type = null;
                    while (true) {
                        System.out.print("Тип (SEDAN, SUV, ELECTRIC, HATCHBACK, TRUCK): ");
                        try {
                            type = CarType.valueOf(scanner.nextLine().trim().toUpperCase());
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: выберите тип из предложенных вариантов!");
                        }
                    }

                    boolean added = dealership.addCar(new CarWithType(vin, model, manufacturer, year, mileage, price, type));
                    System.out.println(added ? "Машина добавлена" : "Машина с таким VIN уже существует");
                    break;

                case 2:
                    System.out.print("Введите производителя: ");
                    String manuf = scanner.nextLine();
                    List<CarWithType> foundCars = dealership.findCarsByManufacturer(manuf);
                    System.out.println("Найдено машин: " + foundCars.size());
                    foundCars.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Введите тип (SEDAN, SUV, ELECTRIC, HATCHBACK, TRUCK): ");
                    CarType carType = CarType.valueOf(scanner.next().toUpperCase());
                    double avgPrice = dealership.getAveragePriceByType(carType);
                    System.out.printf("Средняя цена: %.2f\n", avgPrice);
                    break;

                case 4:
                    List<CarWithType> sortedCars = dealership.getCarsSortedByYear();
                    System.out.println("Машины отсортированные по году:");
                    sortedCars.forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Статистика:");
                    System.out.println("Количество по типам:");
                    dealership.getCarTypeStatistics().forEach((k, v) ->
                            System.out.println(k + ": " + v));

                    Map<String, CarWithType> oldestNewest = dealership.getOldestAndNewestCars();
                    System.out.println("Самая новая машина: " + oldestNewest.get("newest"));
                    System.out.println("Самая старая машина: " + oldestNewest.get("oldest"));
                    break;

                case 0:
                    System.out.println("Выход из задания 5");
                    break subLoop;

                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
}