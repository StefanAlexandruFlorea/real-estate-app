public class RentValidator {

    public static boolean isValidRent(RealEstateType estateType, long rentValue) {
        switch (estateType) {
            case STUDIO:
                System.out.println("rent not valid, it should be <=350");
                return rentValue <= 350;
            case TWO_ROOMS:
                System.out.println("rent not valid, it should be <=450");
                return rentValue <= 450;
            case THREE_ROOMS:
                System.out.println("rent not valid, it should be >=455");
                return rentValue >= 455;
            default:
                return false;
        }
    }
}
