package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.Scanner;

/**
 * @author 12S24005 Mia Nathania Sibuea
 */
public class Driver4 {
    // Media penyimpanan untuk Course
    private static Course[] courses = new Course[100];
    private static int courseCount = 0;

    // Media penyimpanan untuk Student
    private static Student[] students = new Student[100];
    private static int studentCount = 0;

    // Media penyimpanan untuk Enrollment
    private static Enrollment[] enrollments = new Enrollment[100];
    private static int enrollmentCount = 0;

    public static void main(String[] _args) {
        Scanner input = new Scanner(System.in);
        String line;

        while (true) {
            line = input.nextLine();

            if (line.equals("---")) {
                break;
            }

            // Memisahkan command (e.g., "course-add") dari data
            String[] parts = line.split("#", 2); // Split hanya pada '#' pertama
            if (parts.length < 2) {
                System.err.println("[ERROR] Format input tidak valid: " + line);
                continue;
            }

            String command = parts[0];
            String data = parts[1]; // Sisa string setelah command

            switch (command) {
                case "course-add":
                    addCourse(data);
                    break;
                case "student-add":
                    addStudent(data);
                    break;
                case "enrollment-add":
                    addEnrollment(data);
                    break;
                default:
                    System.err.println("[ERROR] Command tidak dikenal: " + command + ". Input: " + line);
                    break;
            }
        }

        // Output semua Course
        for (int i = 0; i < courseCount; i++) {
            System.out.println(courses[i].toString());
        }

        // Output semua Student
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].toString());
        }

        // Output semua Enrollment
        for (int i = 0; i < enrollmentCount; i++) {
            System.out.println(enrollments[i].toString());
        }

        input.close();
    }

    /**
     * Menambahkan Course baru dari string data yang diberikan.
     * @param data String yang berisi detail course (e.g., "12S2203#Object-oriented Programming#3#C")
     */
    private static void addCourse(String data) {
        String[] segments = data.split("#");
        if (segments.length != 4) {
            System.err.println("[ERROR] Format data course tidak valid: " + data);
            return;
        }

        try {
            String code = segments[0].trim();
            String name = segments[1].trim();
            int credit = Integer.parseInt(segments[2].trim());
            String passingGrade = segments[3].trim();

            if (credit <= 0) {
                 System.err.println("[ERROR] Kredit course harus lebih besar dari 0. Data: " + data);
                 return;
            }
            if (passingGrade.length() != 1) {
                System.err.println("[ERROR] Grade course harus karakter tunggal. Data: " + data);
                return;
            }

            if (courseCount < courses.length) {
                Course newCourse = new Course(code, name, credit, passingGrade);
                courses[courseCount++] = newCourse;
            } else {
                System.err.println("[WARNING] Kapasitas penyimpanan course penuh. Course '" + name + "' tidak ditambahkan.");
            }
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] Format kredit course tidak valid (harus angka): " + segments[2] + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERROR] Terjadi kesalahan saat menambah course dari data '" + data + "': " + e.getMessage());
        }
    }

    /**
     * Menambahkan Student baru dari string data yang diberikan.
     * @param data String yang berisi detail student (e.g., "12S20999#Wiro Sableng#2020#Information Systems")
     */
    private static void addStudent(String data) {
        String[] segments = data.split("#");
        if (segments.length != 4) {
            System.err.println("[ERROR] Format data student tidak valid: " + data);
            return;
        }

        try {
            String id = segments[0].trim();
            String name = segments[1].trim();
            int year = Integer.parseInt(segments[2].trim());
            String studyProgram = segments[3].trim();

            if (year <= 0 || year > 2024) { // Asumsi tahun saat ini atau lebih awal
                System.err.println("[ERROR] Tahun masuk student tidak valid. Data: " + data);
                return;
            }

            if (studentCount < students.length) {
                Student newStudent = new Student(id, name, year, studyProgram);
                students[studentCount++] = newStudent;
            } else {
                System.err.println("[WARNING] Kapasitas penyimpanan student penuh. Student '" + name + "' tidak ditambahkan.");
            }
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] Format tahun student tidak valid (harus angka): " + segments[2] + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERROR] Terjadi kesalahan saat menambah student dari data '" + data + "': " + e.getMessage());
        }
    }

    /**
     * Menambahkan Enrollment baru dari string data yang diberikan.
     * @param data String yang berisi detail enrollment (e.g., "12S2203#12S20999#2021/2022#even")
     */
    private static void addEnrollment(String data) {
        String[] segments = data.split("#");
        if (segments.length != 4) {
            System.err.println("[ERROR] Format data enrollment tidak valid: " + data);
            return;
        }

        try {
            String courseCode = segments[0].trim();
            String studentId = segments[1].trim();
            String academicYear = segments[2].trim();
            String semester = segments[3].trim();

            if (!(semester.equalsIgnoreCase("even") || semester.equalsIgnoreCase("odd"))) {
                System.err.println("[ERROR] Semester enrollment tidak valid. Harus 'even' atau 'odd'. Data: " + data);
                return;
            }
            
            if (enrollmentCount < enrollments.length) {
                Enrollment newEnrollment = new Enrollment(courseCode, studentId, academicYear, semester);
                enrollments[enrollmentCount++] = newEnrollment;
            } else {
                System.err.println("[WARNING] Kapasitas penyimpanan enrollment penuh. Enrollment tidak ditambahkan.");
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Terjadi kesalahan saat menambah enrollment dari data '" + data + "': " + e.getMessage());
        }
    }
}