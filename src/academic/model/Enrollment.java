package academic.model;

/**
 * @author 12S24005 Mia Nathania Sibuea
 */

public class Enrollment {
    private String courseCode;
    private String studentId;
    private String academicYear;
    private String semester;
    // Pada tahap ini, grade belum ada, namun output mengharapkan placeholder 'None'.
    // Jika nanti ada penambahan fitur untuk set grade, properti ini bisa ditambahkan.
    // private String grade; // Ini bisa ditambahkan di task selanjutnya

    public Enrollment(String courseCode, String studentId, String academicYear, String semester) {
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester;
        // this.grade = "None"; // Jika ingin menyimpan 'None' sebagai nilai default
    }

    public String getCourseCode() { return courseCode; }
    public String getStudentId() { return studentId; }
    public String getAcademicYear() { return academicYear; }
    public String getSemester() { return semester; }
    // public String getGrade() { return grade; } // Getter untuk grade jika ditambahkan

    /**
     * Mengoverride metode toString() untuk menghasilkan format output yang diminta:
     * courseCode|studentId|academicYear|semester|None
     * Menambahkan "|None" di akhir untuk placeholder grade.
     */
    @Override
    public String toString() {
        return courseCode + "|" + studentId + "|" + academicYear + "|" + semester + "|None";
    }
}