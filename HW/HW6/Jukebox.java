import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Provides static methods for reading and writing records to a jukebox CSV file.
 *
 * @author gsilva37
 * @version 1.0.0
 */
public class Jukebox {
    /**
     * Reads records from the file at the given path and returns them as an ArrayList.
     *
     * @param path the path name of the file to read from
     * @return an ArrayList of Record objects parsed from the file
     * @throws FileNotFoundException  if the path is blank, null, or does not point to an existing file
     * @throws InvalidRecordException if any line in the file has an unrecognized record type
     */
    public static ArrayList<Record> retrieveRecords(String path)
        throws FileNotFoundException, InvalidRecordException {
        ArrayList<Record> records = new ArrayList<>();
        File recordFile = getRecordFile(path, true);
        Scanner scan = new Scanner(recordFile);
        while (scan.hasNextLine()) {
            String recordLine = scan.nextLine();
            records.add(processInfo(recordLine));
        }
        scan.close();
        return records;
    }

    /**
     * Writes the given records to the file at the given path, appending to any existing content.
     * Records with a grade of 'P' are not written.
     *
     * @param path    the path name of the file to write to
     * @param records the ArrayList of Record objects to add to the jukebox
     * @throws FileNotFoundException if the path is blank or null
     */
    public static void stockJukebox(
        String path, ArrayList<Record> records) throws FileNotFoundException {
        File recordFile = getRecordFile(path, false);
        ArrayList<Record> existing = new ArrayList<>();
        if (recordFile.exists()) {
            try {
                existing = retrieveRecords(path);
            } catch (InvalidRecordException e) {
            }
        }
        existing.addAll(records);
        PrintWriter pw = new PrintWriter(recordFile);
        for (Record r : existing) {
            if (r.getGrade() != 'P') {
                pw.println(r.toString());
            }
        }
        pw.close();
    }

    /**
     * Returns the line numbers of all occurrences of the specified record in the file.
     *
     * @param path   the path name of the file to search
     * @param record the Record to search for
     * @return an ArrayList of Integer line numbers (0-indexed) where the record was found
     * @throws FileNotFoundException    if the path is blank, null, or does not point to an existing file
     * @throws InvalidRecordException   if the record is not found in the file
     * @throws IllegalArgumentException if record is null
     */
    public static ArrayList<Integer> findRecords(String path, Record record)
        throws FileNotFoundException, InvalidRecordException {
        if (record == null) {
            throw new IllegalArgumentException("Record cannot be null.");
        }
        ArrayList<Record> records = retrieveRecords(path);
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).equals(record)) {
                indices.add(i);
            }
        }
        if (indices.isEmpty()) {
            throw new InvalidRecordException("Record not found in jukebox.");
        }
        return indices;
    }

    /**
     * Plays all occurrences of the specified record in the file, incrementing their play count
     * and applying a 30% chance of lowering each record's grade by one level.
     * Records that reach grade 'P' are removed from the file.
     *
     * @param path   the path name of the file to update
     * @param record the Record to spin
     * @throws FileNotFoundException  if the path is blank, null, or does not point to an existing file
     * @throws InvalidRecordException if the record is not found in the file
     */
    public static void spinRecord(String path, Record record)
        throws FileNotFoundException, InvalidRecordException {
        ArrayList<Integer> indices = findRecords(path, record);
        ArrayList<Record> records = retrieveRecords(path);
        for (int index : indices) {
            Record r = records.get(index);
            r.incrementTimesPlayed();
            if (Math.random() < 0.30) {
                char currentGrade = r.getGrade();
                if (currentGrade == 'M') {
                    r.setGrade('E');
                } else if (currentGrade == 'E') {
                    r.setGrade('G');
                } else if (currentGrade == 'G') {
                    r.setGrade('F');
                } else if (currentGrade == 'F') {
                    r.setGrade('P');
                }
            }
        }
        File recordFile = getRecordFile(path, true);
        PrintWriter pw = new PrintWriter(recordFile);
        for (Record r : records) {
            if (r.getGrade() != 'P') {
                pw.println(r.toString());
            }
        }
        pw.close();
    }

    /**
     * Removes all occurrences of the specified record from the file.
     *
     * @param path   the path name of the file to update
     * @param record the Record to remove
     * @throws FileNotFoundException  if the path is blank, null, or does not point to an existing file
     * @throws InvalidRecordException if the record is not found in the file
     */
    public static void removeRecord(String path, Record record)
        throws FileNotFoundException, InvalidRecordException {
        ArrayList<Integer> indices = findRecords(path, record);
        ArrayList<Record> records = retrieveRecords(path);
        for (int i = indices.size() - 1; i >= 0; i--) {
            records.remove((int) indices.get(i));
        }
        File recordFile = getRecordFile(path, true);
        PrintWriter pw = new PrintWriter(recordFile);
        for (Record r : records) {
            pw.println(r.toString());
        }
        pw.close();
    }

    private static File getRecordFile(
        String path, boolean checkExists) throws FileNotFoundException {
        if (path == null || path.isBlank()) {
            throw new FileNotFoundException("Path cannot be null or blank.");
        }
        File file = new File(path);
        if (checkExists && !(file.exists())) {
            throw new FileNotFoundException("File with given path does not exist.");
        }
        return file;
    }

    private static Record processInfo(String recordLine) throws InvalidRecordException {
        String[] tokens = recordLine.split(",");
        String recordType = tokens[0];
        String artist = tokens[1];
        String title = tokens[2];
        String duration = tokens[3];
        String releaseDate = tokens[4];
        int timesPlayed = Integer.parseInt(tokens[5]);
        char grade = tokens[6].charAt(0);
        String contributor = tokens[7];
        if (recordType.equals("JazzRecord")) {
            return new JazzRecord(
                artist, title, duration, releaseDate, timesPlayed, grade, contributor);
        } else if (recordType.equals("RapRecord")) {
            return new RapRecord(
                artist, title, duration, releaseDate, timesPlayed, grade, contributor);
        } else {
            throw new InvalidRecordException("Record must have type of Rap or Jazz.");
        }
    }
}
