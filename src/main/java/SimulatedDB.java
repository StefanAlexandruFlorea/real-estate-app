import java.util.*;

public class SimulatedDB {

    private static int id = 0;
    private static final List<RealEstate> REAL_ESTATES = new ArrayList<>();

    public void addRealEstate() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter owner name ");
        String owner = scanner.nextLine();

        while (owner.length() > 200) {
            System.out.println("Invalid name, enter a name <200 characters");
            owner = scanner.nextLine();
        }

        System.out.println("Enter type: STUDIO, TWO_ROOMS or THREE_ROOMS");
        RealEstateType type = null;

        do {
            try {
                scanner = new Scanner(System.in);
                type = RealEstateType.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Type not ok, enter one of this: " + Arrays.toString(RealEstateType.values()));
            }
        } while (type == null);

        System.out.println("Enter rent value");
        int rent;

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("This is not a number");
                scanner.next();
            }
            rent = scanner.nextInt();
            if (rent < 1) {
                System.out.println("Please enter a positive no >0");
            }
        } while (rent < 1);

        while (!RentValidator.isValidRent(type, rent)) {
            rent = scanner.nextInt();
        }

        System.out.println("Has parking: true/false");
        Boolean parking = null;

        do {
            try {
                parking = scanner.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Value not ok, should be: true/false");
                scanner.next();
            }
        } while (parking == null);

        RealEstate realEstate = new RealEstate(owner, type, rent, parking);

        realEstate.setId(++id);
        REAL_ESTATES.add(realEstate);
        System.out.println("Real estate succesfully added ");
    }

    public void updateRent() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id for rent update");
        int id = 0;
        boolean found = false;

        LABEL_1:
        do {
            scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                System.out.println("This is not a number");
                scanner.next();
            }
            id = scanner.nextInt();
            if (id < 1) {
                System.out.println("Please enter a positive no");
                continue LABEL_1;
            }

            for (RealEstate realEstate : REAL_ESTATES) {
                if (realEstate.getId() == id) {
                    found = true;
                } else {
                    System.out.println("id doesn't exist, try one of available id's : " + REAL_ESTATES);
                }
            }
        } while (!found);

        System.out.println("Enter new rent ");
        int rent = 0;

        LABEL:
        do {
            scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                System.out.println("This is not a number");
                scanner.next();
            }
            rent = scanner.nextInt();
            if (rent < 1) {
                System.out.println("Enter a positive no >0");
            } else {
                for (RealEstate realEstate : REAL_ESTATES) {
                    if (realEstate.getId() == id) {
                        RealEstateType type = realEstate.getType();
                        if (!RentValidator.isValidRent(type, rent)) {
                            rent = 0;
                            System.out.println("Try again");
                            continue LABEL;
                        }
                        realEstate.setRentValue(rent);
                        System.out.println("rent updated ");
                    }
                }
            }
        } while (rent < 1);

    }

    public void listRealEstates() {

        REAL_ESTATES.sort((r1, r2) -> r1.getRentValue() - r2.getRentValue());
        System.out.println("real estates: ");
        System.out.println(REAL_ESTATES);
    }

    public void listEstatesByType() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter one of the following type: " + Arrays.toString(RealEstateType.values()));
        String inputType = null;
        RealEstateType type = null;

        while (type == null) {
            inputType = scanner.nextLine().toUpperCase();
            if (inputType.equals("STUDIO")) {
                type = RealEstateType.valueOf(inputType);
            } else if (inputType.equals("TWO_ROOMS")) {
                type = RealEstateType.valueOf(inputType);
            } else if (inputType.equals("THREE_ROOMS")) {
                type = RealEstateType.valueOf(inputType);
            } else {
                System.out.println("Invalid type, try again!");
            }
        }

        int noOfAvailable = 0;

        for (RealEstate realEstate : REAL_ESTATES) {
            if (realEstate.getType() == type) {
                noOfAvailable++;
            }
        }

        if (noOfAvailable != 0) {
            System.out.println(type + ": " + noOfAvailable);
            for (RealEstate realEstate : REAL_ESTATES) {
                if (realEstate.getType() == type) {
                    System.out.println(realEstate);
                }
            }
        } else {
            System.out.println(type + ": " + noOfAvailable);
        }
    }

    public void addParking() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id to add parking");
        int id = 0;
        RealEstate toAddParking = null;

        do {
            scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                System.out.println("wrong input, enter an integer");
                scanner.next();
            }
            id = scanner.nextInt();
            if (id < 1) {
                System.out.println("enter a positive value > 0");
            } else {
                for (RealEstate realEstate : REAL_ESTATES) {
                    if (realEstate.getId() == id) {
                        toAddParking = realEstate;
                    }
                }
                if (toAddParking == null) {
                    System.out.println("Id doesn't exist, enter one of this: " + REAL_ESTATES);
                }
            }

        } while (toAddParking == null);

        if (!toAddParking.isParking()) {
            toAddParking.setParking(true);
            System.out.println("parking added");
            int newRent = toAddParking.getRentValue() + 30;
            if (RentValidator.isValidRent(toAddParking.getType(), newRent)) {
                toAddParking.setRentValue(newRent);
                System.out.println("The new rent is: " + newRent);
            } else {
                System.out.println("Parking was set to true, but rent remains " + toAddParking.getRentValue());
            }
        } else {
            System.out.println("parking can't be set, is already true");
        }
    }

    public void deleteRealEstate() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id to delete estate");
        int id = 0;
        RealEstate toBeDeleted = null;

        do {
            scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                System.out.println("wrong input, enter an integer");
                scanner.next();
            }
            id = scanner.nextInt();
            if (id < 1) {
                System.out.println("enter a positive value > 0");
            } else {
                for (RealEstate realEstate : REAL_ESTATES) {
                    if (realEstate.getId() == id) {
                        toBeDeleted = realEstate;
                    }
                }
                if (toBeDeleted == null) {
                    System.out.println("Id doesn't exist, enter again");
                }
            }

        } while (toBeDeleted == null);

        System.out.println("Real estate deleted: " + toBeDeleted);
        REAL_ESTATES.remove(toBeDeleted);
    }
}
