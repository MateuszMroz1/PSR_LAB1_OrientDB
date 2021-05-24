import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        OrientDB orientDB = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orientDB.open("PSR", "admin", "admin");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        if (db.getClass("Liga") == null) {
            db.createVertexClass("Liga");
        }
        OClass liga = db.getClass("Liga");

        if (liga.getProperty("ID") == null) {
            liga.createProperty("ID", OType.INTEGER);

            liga.createIndex("Liga_ID_index", OClass.INDEX_TYPE.UNIQUE, "ID");
        }
        if (liga.getProperty("name") == null) {
            liga.createProperty("name", OType.STRING);

            liga.createIndex("Liga_name_index", OClass.INDEX_TYPE.UNIQUE, "name");
        }
        if (liga.getProperty("number_of_team") == null) {
            liga.createProperty("number_of_team", OType.INTEGER);

            liga.createIndex("Liga_number_of_team_index", OClass.INDEX_TYPE.NOTUNIQUE, "number_of_team");
        }

        int menu = 1;
        int menu_interrior = -1;
        String name = "";
        int number_of_team = 0;

        while(true) {
            System.out.print("Główne Menu:\n");
            System.out.print("\n");
            System.out.print("1.Dodawanie do bazy danych.\n");
            System.out.print("2.Usuwanie całej bazy danych.\n");
            System.out.print("3.Usuwanie rekordu z bazy danych o podanym id.\n");
            System.out.print("4.Usuwanie rekordu z bazy danych o podanej nazwie.\n");
            System.out.print("5.Wyświetlenie całej bazy danych.\n");
            System.out.print("6.Wyświetlenie rekordu z bazy danych o podanym id.\n");
            System.out.print("7.Wyświetlenie rekordu z bazy danych o podanej nazwie.\n");
            System.out.print("8.Wyświetlenie rekordu z bazy danych o podanym numerze drużyn.\n");
            System.out.print("9.Modyfikacja rekordu o podanym id.\n");
            System.out.print("10.Pokazanie ligi z najmniejszą ilością drużyn.\n");
            System.out.print("11.Pokazanie ligi z największą ilością drużyn.\n");
            System.out.print("0.Wyjście z programu.\n");

            System.out.print("Wybierz funkcje. I kliknij klawisz ENTER: ");
            menu = scan.nextInt();

            if(menu == 1){
                System.out.print("Menu dodawania do bazy danych:\n");
                System.out.print("Dodaj nazwe: \n");
                name = in.readLine();

                System.out.print("Dodaj liczbe drużyn: \n");
                number_of_team = scan.nextInt();

                createLig(db, name, number_of_team);
                System.out.print("\n");

                if(menu_interrior == 0){
                    System.out.print("\n");
                    System.out.print("\n");
                    menu_interrior = -1;
                    continue;
                }
            }

            if(menu == 2) {

                executeAQueryDeleteAll(db);
                System.out.print("Usunięto wszystkie dane z bazy danych.\n");
                System.out.print("\n");
            }

            if(menu == 3){
                System.out.print("Podaj klucz do usunięcia:\n");
                Integer key = scan.nextInt();
                executeAQueryDeletewhereID(db, key);
                System.out.print("\n");
            }

            if(menu == 4){
                System.out.print("Podaj nazwe do usunięcia:\n");
                String nam = in.readLine();
                System.out.print("Usunięto dane:\n");
                executeAQueryShowwhereName(db, nam);
                executeAQueryDeletewhereName(db, nam);
                System.out.print("\n");
            }

            if(menu == 5){
                System.out.print("\n");
                System.out.print("Wyświetlanie wszystkich danych:\n");
                executeAQueryShowALL(db);
                System.out.print("\n");
            }

            if(menu == 6){
                System.out.print("Podaj klucz do wyświetlenia:\n");
                Integer key = scan.nextInt();
                System.out.print("Wyświetlone dane:\n");
                executeAQueryShowwhereID(db, key);
                System.out.print("\n");
            }

            if(menu == 7){
                System.out.print("Podaj nazwe do wyświetlenia:\n");
                name = in.readLine();
                System.out.print("Wyświetlone dane:\n");
                executeAQueryShowwhereName(db, name);
                System.out.print("\n");
            }

            if(menu == 8){
                System.out.print("Podaj ilość drużyn do wyświetlenia całej ligi:\n");
                Integer number = scan.nextInt();
                executeAQueryShowwhereNumber_of_team(db, number);
                System.out.print("\n");
            }

            if(menu == 9){
                System.out.print("Podaj klucz do modyfikacji:\n");
                Integer key = scan.nextInt();
                System.out.print("Zmieniany rekord:\n");
                executeAQueryShowwhereID(db, key);
                System.out.print("\n");
                System.out.print("Podaj nową nazwe: \n");
                String nam = in.readLine();

                System.out.print("Podaj nową liczbe drużyn: \n");
                number_of_team = scan.nextInt();
                executeAQueryModifyBYID(db, key, nam, number_of_team);
                System.out.print("\n");
                System.out.print("Zmieniony rekord:\n");
                executeAQueryShowwhereID(db,key);
                System.out.print("\n");

            }

            if(menu == 10){
                System.out.print("Drużyna z najmniejszą ilością drużyn:\n");
                executeAQueryLowesNumber_of_team(db);
            }

            if(menu == 11){
                System.out.print("Drużyna z największą ilością drużyn:\n");
                executeAQueryHighestNumber_of_team(db);
            }

            if(menu == 0){
                System.out.print("\n");
                System.out.print("Wyłączanie programu...\n");
                scan.close();
                db.close();
                orientDB.close();
                System.exit(0);
            }


            if(menu < 0 || menu > 11){
                System.out.print("\n");
                System.out.print("Nie ma takiej opcji!\n");
                System.out.print("\n");
                continue;
            }
        }
    }

    private static OVertex createLig(ODatabaseSession db, String name, Integer number_of_team) {

        OVertex result = db.newVertex("Liga");
        Integer id = executeAQueryHighestID(db)+1;

        result.setProperty("ID", id);
        result.setProperty("name", name);
        result.setProperty("number_of_team", number_of_team);
        result.save();
        return result;
    }

    private static Integer  executeAQueryHighestID(ODatabaseSession db) {
        OClass userCls = db.getClass("Liga");

        return Math.toIntExact(userCls.count());
    }

    private static void executeAQueryShowALL(ODatabaseSession db) {
        String query = "Select * FROM Liga";

        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.print(item + "\n");
        }
    }
    private static void executeAQueryShowwhereID(ODatabaseSession db, Integer id) {
        String query = "Select * FROM Liga where ID = "+ id;

        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.print(item + "\n");
        }
    }

    private static void executeAQueryShowwhereName(ODatabaseSession db, String name) {
        String query = "Select * FROM Liga where name = \'"+ name+ "\'";

        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.print(item + "\n");
        }
    }

    private static void executeAQueryShowwhereNumber_of_team(ODatabaseSession db, Integer number_of_team) {
        String query = "Select * FROM Liga where number_of_team = "+ number_of_team;

        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.print(item + "\n");
        }
    }
    private static void executeAQueryDeleteAll(ODatabaseSession db) {

        String query = "delete from Liga UNSAFE";

        db.command(query);
    }

    private static void executeAQueryDeletewhereID(ODatabaseSession db, Integer id) {

        String query = "delete from Liga Where ID = " + id +" UNSAFE";

        db.command(query);
    }

    private static void executeAQueryDeletewhereName(ODatabaseSession db, String name) {

        String query = "delete from Liga Where name = \'" + name +"\' UNSAFE";

        db.command(query);
    }

    private static void executeAQueryLowesNumber_of_team(ODatabaseSession db){
        String query = "Select MIN(number_of_team) FROM Liga";
        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.print(item + "\n");
        }
    }

    private static void executeAQueryHighestNumber_of_team(ODatabaseSession db){
        String query = "Select Max(number_of_team) FROM Liga";
        OResultSet rs = db.query(query);
        while (rs.hasNext()) {
            OResult item = rs.next();
            System.out.print(item + "\n");
        }
    }


    @NotNull
    private static void executeAQueryModifyBYID(ODatabaseSession db, Integer id, String nam, Integer number_of_team){

        db.command(new OCommandSQL("Update Liga Set name = \'" + nam +"\', number_of_team = "+ number_of_team +" Where ID = " + id)).execute();

    }
}
