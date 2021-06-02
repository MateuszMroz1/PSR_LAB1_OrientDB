import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.print("Startowanie serwisu...\n");
        Instance.StartInstance();
        Listener.StartListener();
        System.out.print("Serwis załadowany.\n");
        System.out.print("\n");
        System.out.print("\n");

        int menu = 1;
        int menu_interrior = -1;
        String name = "";
        int number_of_team = 0;

        while(true) {
        System.out.print("Główne Menu:\n");
            System.out.print("\n");
        System.out.print("1.Dodawanie do bazy danych.\n");
        System.out.print("2.Usuwanie całej bazy danych.\n");
        System.out.print("3.Usuwanie rekordu z bazy danych o podanym kluczu.\n");
        System.out.print("4.Wyświetlenie całej bazy danych.\n");
        System.out.print("5.Wyświetlenie rekordu z bazy danych o podanym kluczu.\n");
        System.out.print("6.Pokazanie ligi z najmniejszą ilością drużyn.\n");
        System.out.print("0.Wyjście z programu.\n");

            System.out.print("Wybierz funkcje. I kliknij klawisz ENTER: ");
            menu = scan.nextInt();

            if(menu == 1){
                System.out.print("Menu dodawania do bazy danych:\n");
                System.out.print("\n");
                System.out.print("Dodaj nazwe: \n");
                name = in.readLine();


                System.out.print("Dodaj liczbe drużyn: \n");
                number_of_team = scan.nextInt();

                MapPut.NewLigPut(name, number_of_team);

                System.out.print("\n");
                MapGet.ShowAll();
                System.out.print("\n");

                if(menu_interrior == 0){
                    System.out.print("\n");
                    System.out.print("\n");
                    menu_interrior = -1;
                    continue;
                }
            }

            if(menu == 2) {

                MapDelete.DeleteAll();
                System.out.print("Usunięto wszystkie dane z bazy danych.\n");
                System.out.print("\n");

            }

            if(menu == 3){
                System.out.print("Podaj klucz do usunięcia:\n");
                System.out.print("\n");
                long key = scan.nextLong();
                MapDelete.DeleteByKey(key);
                System.out.print("\n");
                System.out.print("\n");
            }

            if(menu == 4){
                System.out.print("Wyświetlanie wszystkich danych:\n");
                System.out.print("\n");
                MapGet.ShowAll();
                System.out.print("\n");
                System.out.print("\n");
            }

            if(menu == 5){
                System.out.print("Podaj klucz do wyświetlenia:\n");
                System.out.print("\n");
                long key = scan.nextLong();
                MapGet.ShowByKey(key);
                System.out.print("\n");
                System.out.print("\n");
            }

            if(menu == 6){
                System.out.print("Drużyna z najmniejszą ilością drużyn:\n");
                System.out.print("\n");
                MapGet.MinTeam();
                System.out.print("\n");
                System.out.print("\n");
            }

        if(menu == 0){
            System.out.print("\n");
            System.out.print("Wyłączanie programu...\n");
            scan.close();
            System.exit(0);
        }


        if(menu < 0 || menu > 4){
            System.out.print("\n");
            System.out.print("Nie ma takiej opcji!\n");
            System.out.print("\n");
            continue;
        }
        }
    }
}
