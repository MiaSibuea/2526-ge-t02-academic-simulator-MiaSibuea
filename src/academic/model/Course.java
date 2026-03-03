package academic.model;

/**
 * @author 12S24005 Mia Nathania Sibuea
 */
public class Course {
    private String code;
    private String name;
    private int credit;
    private String passingGrade; // Contoh: 'A', 'B', 'C', 'D', 'E'

    // Konstruktor lengkap sesuai spesifikasi
    public Course(String code, String name, int credit, String passingGrade) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
    }

    // Getter untuk semua atribut
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    /**
     * Mengoverride metode toString() untuk menghasilkan format output yang diminta:
     * code|name|credit|passingGrade
     */
    @Override
    public String toString() {
        return code + "|" + name + "|" + credit + "|" + passingGrade;
    }
}