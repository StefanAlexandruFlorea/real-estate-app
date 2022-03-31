import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        SimulatedDB simulatedDB = new SimulatedDB();
        Scanner scanner = new Scanner(System.in);
        int option  =- 1;
        while(option != 0){
            System.out.println("Enter one of the commands: "+"\n"+
                    "1: addRealEstate"+"\n"+
                    "2: updateRent"+"\n"+
                    "3: listAllRealEstates"+"\n"+
                    "4: listRealEstateByType"+"\n"+
                    "5: deleteRealEstate"+"\n"+
                    "6: addParking"+"\n"+
                    "0: exit");
            option = scanner.nextInt();

            switch (option){
                case 1:
                    simulatedDB.addRealEstate();
                    break;
                case 2: ;
                    break;
                case 3: simulatedDB.listRealEstates();
                    break;
                case 4: ;
                    break;
                case 5: ;
                    break;
                case 6: ;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invald command ");
            }
        }
    }




}
