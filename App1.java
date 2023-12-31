import java.util.Scanner;

public class App1 {
    private static final Scanner SCANNER = new Scanner(System.in); 
    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";
        

        final String DASHBOARD = "👷 Welcome to Smart Banking";
        final String CREATE_ACCOUNT = "➕ Open New Account";
        final String DEPOSITS = "➕ Deposit Money";
        final String WITHDRAWALS = "➕ Withdraw Money";
        final String TRANSFER = "➕ Transfer Money";
        final String CHECK_BALANCE = "➕ Check Acoount Balance";
        final String DELETE_ACCOUNT = "❌ Drop Exisiting Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[] accountHolders = new String[0];
        String screen = DASHBOARD;

        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD,screen,RESET);

            System.out.println(CLEAR);
            System.out.println("\t"+APP_TITLE+"\n");

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Acoount Balance");
                    System.out.println("\t[6]. Drop Exisiting Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = CREATE_ACCOUNT; break;
                        case 2: screen = DEPOSITS; break;
                        case 3: screen = WITHDRAWALS; break;
                        case 4: screen = TRANSFER; break;
                        case 5: screen = CHECK_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;
                case CREATE_ACCOUNT:
                    System.out.printf("\tNew Account Holder ID: SDB%04d \n", (accountHolders.length + 1));
                    boolean validName;
                    String name;
                    
                    do{
                        validName = true;
                        System.out.print("\tEnter Account Holder Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name can't be empty.");
                            validName = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!Character.isLetter(name.charAt(i))) {
                                System.out.printf(ERROR_MSG, "Invalid Name");
                                validName = false;
                                break;
                            }
                        }
                    }while(!validName);

                    boolean validDeposit;
                    int initialDeposit;
                    do{
                        validDeposit = true;
                        System.out.print("\tEnter Initial Deposit: ");
                        initialDeposit = SCANNER.nextInt();
                        SCANNER.nextLine();

                        if (initialDeposit == 0){
                            System.out.printf(ERROR_MSG,"Initial deposit can't be empty.");
                            validDeposit = false;
                            continue;
                        }
                        if(initialDeposit < 0 || initialDeposit < 5000){
                           System.out.printf(ERROR_MSG,"Insufficient amount");
                            validDeposit = false;
                            continue; 
                        }
                    }while(!validDeposit);

                    String[] newAccountHolder = new String[accountHolders.length + 1];
                    for (int i = 0; i < accountHolders.length; i++) {
                        newAccountHolder[i] = accountHolders[i];
                    }
                    newAccountHolder[newAccountHolder.length -1] = name;
                    accountHolders = newAccountHolder;

                    System.out.println();
                    String id = null;
                    for (int i = 0; i < accountHolders.length; i++) {
                            id = String.format("SDB%04d", (i+1));  
                         }
                    System.out.print(id+":"+name + " added sucessfully. Do you want to add new Account Holder (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                    
            }
        }while(true);
        
    }
    
}
