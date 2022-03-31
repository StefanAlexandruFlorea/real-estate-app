import java.util.*;

public class SimulatedDB {

    private static int id = 0;
    private static final List<RealEstate> REAL_ESTATES = new ArrayList<>();

    public void addRealEstate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter owner name ");
        String owner = scanner.nextLine();

        System.out.println("type");
        RealEstateType type = RealEstateType.valueOf(scanner.nextLine());

        System.out.println("rent value");
        int rent = scanner.nextInt();
        while (!RentValidator.isValidRent(type, rent)) {
            System.out.println("try again");
            rent = scanner.nextInt();
        }

        System.out.println("parking");
        boolean parking = scanner.nextBoolean();

        RealEstate realEstate = new RealEstate(owner, type, rent, parking);

        realEstate.setId(++id);
        REAL_ESTATES.add(realEstate);
        System.out.println("Real estate added");
    }

    public void listRealEstates() {
        REAL_ESTATES.sort((r1, r2) -> r1.getRentValue() - r2.getRentValue());
        System.out.println("real estates: ");
        System.out.println(REAL_ESTATES);
    }

    public void updateRent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id ");
        int id = scanner.nextInt();

        System.out.println("enter rent ");
        int rent = scanner.nextInt();

        boolean found = false;
        for (RealEstate realEstate : REAL_ESTATES) {
            if (realEstate.getId() == id) {
                found = true;
                RealEstateType type = realEstate.getType();
                while (!RentValidator.isValidRent(type, rent)) {
                    System.out.println("try again");
                    rent = scanner.nextInt();
                }
                realEstate.setRentValue(rent);
            }
        }

        if(!found){
            System.out.println("real estate doesn't exist");
        }
    }

    public void deleteRealEstate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id ");
        int id = scanner.nextInt();

        RealEstate toBeDeleted = null;

        for (RealEstate realEstate : REAL_ESTATES) {
            if (realEstate.getId() == id) {
                toBeDeleted = realEstate;
            }
        }

        if (toBeDeleted != null) {
            REAL_ESTATES.remove(toBeDeleted);
        } else {
            System.out.println(" real estate doesn't exist");
        }
    }
}
