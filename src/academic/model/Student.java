package academic.model;

/**
 * @author 12S24005 Mia Nathania Sibuea
 */
public class Student {
    private String id;
    private String name;
    private int year; // Menggunakan int untuk tahun
    private String studyProgram;

    // Konstruktor lengkap (4 parameter) sesuai atribut
    public Student(String id, String name, int year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }

    // Getter untuk semua atribut
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    /**
     * Mengoverride metode toString() untuk menghasilkan format output yang diminta:
     * id|name|year|studyProgram
     */
    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }
}