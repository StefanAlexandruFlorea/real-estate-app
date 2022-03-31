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

        for (RealEstate realEstate : REAL_ESTATES) {
            if (realEstate.getId() == id) {
                RealEstateType type = realEstate.getType();
                while (!RentValidator.isValidRent(type, rent)) {
                    System.out.println("try again");
                    rent = scanner.nextInt();
                }
                realEstate.setRentValue(rent);
            } else {
                System.out.println("real estate does not exist");
            }
        }
    }
}
