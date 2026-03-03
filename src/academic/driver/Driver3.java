package academic.driver;

import academic.model.Enrollment;
import java.util.Scanner; // Digunakan untuk membaca input interaktif

/**
 * @author 12S24005 Mia Nathania Sibuea
 */
public class Driver3 {
    // Media penyimpanan: Array statis untuk objek Enrollment.
    // Kapasitas array ditetapkan 100 sebagai contoh. Sesuaikan jika diperlukan.
    private static Enrollment[] enrollments = new Enrollment[100];
    // Penghitung jumlah objek Enrollment yang telah ditambahkan ke array.
    private static int enrollmentCount = 0;

    public static void main(String[] _args) { // Menggunakan _args sesuai template
        // Inisialisasi Scanner untuk membaca input dari konsol.
        Scanner input = new Scanner(System.in);
        String line; // Variabel untuk menyimpan setiap baris input

        // Logika loop untuk membaca input interaktif secara terus-menerus.
        // Loop akan berhenti ketika pengguna memasukkan "---".
        while (true) {
            line = input.nextLine(); // Membaca satu baris penuh input dari pengguna

            // Kondisi berhenti membaca input.
            if (line.equals("---")) {
                break; // Keluar dari loop while
            }

            // Parsing Input: Memisahkan baris input menjadi segmen-segmen
            // menggunakan karakter '#' sebagai delimiter.
            String[] segments = line.split("#");

            // Validasi awal: Memastikan baris input memiliki jumlah segmen yang benar.
            if (segments.length != 4) {
                // Menampilkan pesan error ke standard error stream jika format tidak sesuai.
                System.err.println("[ERROR] Format input enrollment tidak valid. Harusnya 'courseCode#studentId#academicYear#semester'. Input: " + line);
                continue; // Melanjutkan ke iterasi loop berikutnya, mengabaikan input ini.
            }

            try {
                // Ekstraksi data dari segmen-segmen yang telah diparsing.
                String courseCode = segments[0].trim();
                String studentId = segments[1].trim();
                String academicYear = segments[2].trim();
                String semester = segments[3].trim();

                // Validasi tambahan: Memastikan semester valid (opsional, Clean Code)
                if (!(semester.equalsIgnoreCase("even") || semester.equalsIgnoreCase("odd"))) {
                    System.err.println("[ERROR] Semester tidak valid. Harus 'even' atau 'odd'. Input: " + line);
                    continue;
                }
                
                // Penyimpanan Array: Membuat objek Enrollment baru dan menyimpannya.
                // Memastikan array tidak melebihi kapasitasnya.
                if (enrollmentCount < enrollments.length) {
                    Enrollment newEnrollment = new Enrollment(courseCode, studentId, academicYear, semester);
                    enrollments[enrollmentCount] = newEnrollment; // Menyimpan objek ke array
                    enrollmentCount++; // Menambah jumlah enrollment yang tersimpan
                } else {
                    // Menampilkan pesan peringatan jika kapasitas array penuh.
                    System.err.println("[WARNING] Kapasitas penyimpanan enrollment penuh. Enrollment tidak dapat ditambahkan.");
                }
            } catch (Exception e) {
                // Menangkap kesalahan umum lainnya yang mungkin terjadi selama pemrosesan.
                System.err.println("[ERROR] Terjadi kesalahan tidak terduga saat memproses input '" + line + "': " + e.getMessage());
            }
        }

        // Output: Setelah loop berhenti, semua enrollment yang tersimpan akan ditampilkan.
        // Iterasi hanya sampai enrollmentCount (jumlah enrollment yang valid).
        for (int i = 0; i < enrollmentCount; i++) {
            System.out.println(enrollments[i].toString()); // Menggunakan toString() dari objek Enrollment
        }

        input.close(); // Penting: Menutup objek Scanner untuk mencegah resource leak.
    }
}