import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Laptop {
    public static void main(String[] args) {
        SetLaptop store = new SetLaptop();
        store.makeSet(15);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Магазин ноутбуков =====");
            System.out.println("1. Показать все ноутбуки");
            System.out.println("2. Отфильтровать ноутбуки");
            System.out.println("0. Выйти");
            System.out.print("Выберите действие: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    store.presentSetLaptops();
                    break;
                case 2:
                    store.filterLaptops();
                    break;
                case 0:
                    System.out.println("Прогмамма завершена");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

        } while (choice != 0);

        scanner.close();
    }
    }



class SetLaptop {
    private Set<Laptops> laptops;
    private Random rnd;

    public SetLaptop() {
        laptops = new HashSet<>();
        rnd = new Random();
    }

    public void makeSet(int count) {
        String[] nameArr = { "Lenovo", "Hiper", "Asus", "Samsung" };
        int[] ramArr = { 4, 8, 16, 36 };
        int[] hddArr = { 64, 256, 512, 1024 };
        String[] osArr = { "Windows", "Linux" };
        String[] colorArr = { "Black", "white", "Silver" };

        for (int i = 0; i < count; i++) {
            String name = nameArr[rnd.nextInt(nameArr.length)];
            int ram = ramArr[rnd.nextInt(ramArr.length)];
            int hdd = hddArr[rnd.nextInt(hddArr.length)];
            String os = osArr[rnd.nextInt(osArr.length)];
            String color = colorArr[rnd.nextInt(colorArr.length)];

            laptops.add(new Laptops(name, ram, hdd, os, color));
        }
    }

    public void presentSetLaptops() {
        System.out.println("Список ноутбуков: ");
        for (Laptops laptop : laptops) {
            System.out.println(laptop);
        }

    }

    public void filterLaptops() {
        Map<String, Object> criteria = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("0 - Завершить ввод");

            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ:");
                    criteria.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    criteria.put("hddCapacity", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    criteria.put("os", scanner.next());
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    criteria.put("color", scanner.next());
                    break;

            }

        }
        scanner.close();

        Set<Laptops> filteredLaptops = new HashSet<>();
        for (Laptops laptop : laptops) {
            if (criteria.containsKey("ram") && laptop.getRam() < (int) criteria.get("ram")) {
                filteredLaptops.remove(laptop);
            }
            if (criteria.containsKey("hddCapacity") && laptop.getHdd() < (int) criteria.get("hddCapacity")) {
                filteredLaptops.remove(laptop);
            }
            if (criteria.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) criteria.get("os"))) {
                filteredLaptops.remove(laptop);
            }
            if (criteria.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) criteria.get("color"))) {
                filteredLaptops.remove(laptop);
            }
        }

        

    }
}

class Laptops {
    String name;
    int ram;
    int hdd;
    String os;
    String color;

    public Laptops(String name, int ram, int hdd, String os, String color) {
        this.name = name;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getName(){
        return name;
    }

    public int getRam(){
        return ram;
    }

    public int getHdd(){
        return hdd;
    }

    public String getOs(){
        return os;
    }

    public String getColor(){
        return color;
    }

    @Override
    public String toString() {
        return "Laptops: [" + "name: " + name + ',' +
                " ram: " + ram + ',' + " hdd: " + hdd + ',' +
                " os: " + os + ',' + " color: " + color + ']';
    }
}
