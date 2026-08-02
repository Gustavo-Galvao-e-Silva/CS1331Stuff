import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException, InvalidRecordException {
        JazzRecord jazz1 = new JazzRecord(
            "Miles Davis", "Kind of Blue", "5:22", "1959", 10, 'M', "Fred Plaut");
        JazzRecord jazz2 = new JazzRecord(
            "John Coltrane", "A Love Supreme", "7:43", "1965", 5, 'E', "Rudy Van Gelder");
        RapRecord rap1 = new RapRecord(
            "Kendrick Lamar", "HUMBLE.", "2:57", "2017", 20, 'G', "Mike Will Made-It");
        RapRecord rap2 = new RapRecord(
            "Jay-Z", "99 Problems", "3:44", "2003", 15, 'F', "Rick Rubin");

        ArrayList<Record> records = new ArrayList<>();
        records.add(jazz1);
        records.add(jazz2);
        records.add(rap1);
        records.add(rap2);
        Jukebox.stockJukebox("TestRecords.csv", records);

        JazzRecord jazz3 = new JazzRecord(
            "Duke Ellington", "Take the A Train", "3:10", "1941", 8, 'G', "Billy Strayhorn");
        ArrayList<Record> moreRecords = new ArrayList<>();
        moreRecords.add(jazz3);
        Jukebox.stockJukebox("TestRecords.csv", moreRecords);

        ArrayList<Record> allRecords = Jukebox.retrieveRecords("TestRecords.csv");
        for (Record r : allRecords) {
            System.out.println(r);
        }

        Jukebox.spinRecord("TestRecords.csv", jazz1);
        Jukebox.removeRecord("TestRecords.csv", rap2);
    }
}
