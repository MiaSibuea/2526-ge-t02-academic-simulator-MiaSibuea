package academic.driver;

import academic.model.Course;
import java.util.Scanner; // Digunakan untuk membaca input interaktif

/**
 * @author 12S24005 Mia Nathania Sibuea
 */
public class Driver1 {
    // Media penyimpanan: Array statis untuk objek Course.
    // Kapasitas array ditetapkan 100 sebagai contoh. Jika lebih, perlu penyesuaian.
    private static Course[] courses = new Course[100];
    // Penghitung jumlah objek Course yang telah ditambahkan ke array.
    private static int courseCount = 0;

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
                System.err.println("[ERROR] Format input course tidak valid. Harusnya 'code#name#credit#passinggrade'. Input: " + line);
                continue; // Melanjutkan ke iterasi loop berikutnya, mengabaikan input ini.
            }

            try {
                // Ekstraksi data dari segmen-segmen yang telah diparsing.
                String code = segments[0].trim(); // Menghilangkan spasi di awal/akhir jika ada
                String name = segments[1].trim();
                // Mengkonversi nilai kredit dari String ke int.
                int credit = Integer.parseInt(segments[2].trim());
                String passingGrade = segments[3].trim();

                // Validasi tambahan: Memastikan credit tidak negatif atau nol (opsional, Clean Code)
                if (credit <= 0) {
                     System.err.println("[ERROR] Kredit harus lebih besar dari 0. Input: " + line);
                     continue;
                }
                // Validasi tambahan: Memastikan passingGrade sesuai standar (opsional, Clean Code)
                // Contoh sederhana: hanya menerima single character.
                if (passingGrade.length() != 1) {
                    System.err.println("[ERROR] Grade harus berupa karakter tunggal (ex: A, B, C). Input: " + line);
                    continue;
                }
                
                // Penyimpanan Array: Membuat objek Course baru dan menyimpannya.
                // Memastikan array tidak melebihi kapasitasnya.
                if (courseCount < courses.length) {
                    Course newCourse = new Course(code, name, credit, passingGrade);
                    courses[courseCount] = newCourse; // Menyimpan objek ke array
                    courseCount++; // Menambah jumlah course yang tersimpan
                } else {
                    // Menampilkan pesan peringatan jika kapasitas array penuh.
                    System.err.println("[WARNING] Kapasitas penyimpanan course penuh. Course '" + name + "' tidak dapat ditambahkan.");
                }
            } catch (NumberFormatException e) {
                // Menangkap kesalahan jika konversi string ke integer untuk 'credit' gagal.
                System.err.println("[ERROR] Format kredit tidak valid (harus berupa angka). Input: " + segments[2] + " - " + e.getMessage());
            } catch (Exception e) {
                // Menangkap kesalahan umum lainnya yang mungkin terjadi selama pemrosesan.
                System.err.println("[ERROR] Terjadi kesalahan tidak terduga saat memproses input '" + line + "': " + e.getMessage());
            }
        }

        // Output: Setelah loop berhenti, semua course yang tersimpan akan ditampilkan.
        // Iterasi hanya sampai courseCount (jumlah course yang valid).
        for (int i = 0; i < courseCount; i++) {
            System.out.println(courses[i].toString()); // Menggunakan toString() dari objek Course
        }

        input.close(); // Penting: Menutup objek Scanner untuk mencegah resource leak.
    }
}