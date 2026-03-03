package academic.driver;

/**
 * @author 12S24005 Mia Nathania Sibuea
 */
import academic.model.Student;
import java.util.Scanner; // Digunakan untuk membaca input interaktif


public class Driver2 {
    // Media penyimpanan: Array statis untuk objek Student.
    // Kapasitas array ditetapkan 100 sebagai contoh. Sesuaikan jika diperlukan.
    private static Student[] students = new Student[100];
    // Penghitung jumlah objek Student yang telah ditambahkan ke array.
    private static int studentCount = 0;

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
                System.err.println("[ERROR] Format input student tidak valid. Harusnya 'id#name#year#studyprogram'. Input: " + line);
                continue; // Melanjutkan ke iterasi loop berikutnya, mengabaikan input ini.
            }

            try {
                // Ekstraksi data dari segmen-segmen yang telah diparsing.
                String id = segments[0].trim(); // Menghilangkan spasi di awal/akhir jika ada
                String name = segments[1].trim();
                // Mengkonversi nilai tahun dari String ke int.
                int year = Integer.parseInt(segments[2].trim());
                String studyProgram = segments[3].trim();

                // Validasi tambahan: Memastikan tahun masuk tidak negatif atau tidak masuk akal (opsional, Clean Code)
                // Contoh sederhana: tahun tidak boleh lebih dari tahun sekarang (asumsi program berjalan di 2024 atau lebih).
                if (year <= 0 || year > 2024) { // Sesuaikan batas tahun sesuai kebutuhan
                    System.err.println("[ERROR] Tahun masuk tidak valid. Input: " + line);
                    continue;
                }
                
                // Penyimpanan Array: Membuat objek Student baru dan menyimpannya.
                // Memastikan array tidak melebihi kapasitasnya.
                if (studentCount < students.length) {
                    Student newStudent = new Student(id, name, year, studyProgram);
                    students[studentCount] = newStudent; // Menyimpan objek ke array
                    studentCount++; // Menambah jumlah student yang tersimpan
                } else {
                    // Menampilkan pesan peringatan jika kapasitas array penuh.
                    System.err.println("[WARNING] Kapasitas penyimpanan student penuh. Student '" + name + "' tidak dapat ditambahkan.");
                }
            } catch (NumberFormatException e) {
                // Menangkap kesalahan jika konversi string ke integer untuk 'year' gagal.
                System.err.println("[ERROR] Format tahun tidak valid (harus berupa angka). Input: " + segments[2] + " - " + e.getMessage());
            } catch (Exception e) {
                // Menangkap kesalahan umum lainnya yang mungkin terjadi selama pemrosesan.
                System.err.println("[ERROR] Terjadi kesalahan tidak terduga saat memproses input '" + line + "': " + e.getMessage());
            }
        }

        // Output: Setelah loop berhenti, semua student yang tersimpan akan ditampilkan.
        // Iterasi hanya sampai studentCount (jumlah student yang valid).
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].toString()); // Menggunakan toString() dari objek Student
        }

        input.close(); // Penting: Menutup objek Scanner untuk mencegah resource leak.
    }
}