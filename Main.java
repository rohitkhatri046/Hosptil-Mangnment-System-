import java.util.ArrayList;
import java.util.Scanner;

// Base class Person
class Person {
    protected String name;
    protected int age;
    protected String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Gender: " + gender);
    }
}

// Patient class
class Patient extends Person {
    private final String patientId;
    private final String disease;

    public Patient(String name, int age, String gender, String patientId, String disease) {
        super(name, age, gender);
        this.patientId = patientId;
        this.disease = disease;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Patient ID: " + patientId + ", Disease: " + disease);
    }

    public String getPatientId() {
        return patientId;
    }
}

// Doctor class
class Doctor extends Person {
    private final String doctorId;
    private final String specialization;
    private final double consultationFee;

    public Doctor(String name, int age, String gender, String doctorId, String specialization, double fee) {
        super(name, age, gender);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.consultationFee = fee;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doctor ID: " + doctorId + ", Specialization: " + specialization + ", Consultation Fee: $" + consultationFee);
    }

    public String getDoctorId() {
        return doctorId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }
}

// Medicine class
class Medicine {
    private final String medicineName;
    private final double price;

    public Medicine(String medicineName, double price) {
        this.medicineName = medicineName;
        this.price = price;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public double getPrice() {
        return price;
    }
}

// Appointment class
class Appointment {
    private final String appointmentId;
    private final Patient patient;
    private final Doctor doctor;
    private final String date;
    private final ArrayList<Medicine> prescribedMedicines;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.prescribedMedicines = new ArrayList<>();
    }

    public void addMedicine(Medicine med) {
        prescribedMedicines.add(med);
    }

    public ArrayList<Medicine> getPrescribedMedicines() {
        return prescribedMedicines;
    }

    public void displayInfo() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Date: " + date);
        System.out.println("Patient Details:");
        patient.displayInfo();
        System.out.println("Doctor Details:");
        doctor.displayInfo();
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}

// Payment class
class Payment {
    private final String paymentId;
    private final Appointment appointment;
    private final double totalAmount;
    private final String paymentDate;

    public Payment(String paymentId, Appointment appointment, double totalAmount, String paymentDate) {
        this.paymentId = paymentId;
        this.appointment = appointment;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
    }

    public void displayInfo() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Patient Name: " + appointment.getPatient().name);
        System.out.println("Doctor Fee: $" + appointment.getDoctor().getConsultationFee());
        System.out.println("Medicines:");
        for (Medicine m : appointment.getPrescribedMedicines()) {
            System.out.println("- " + m.getMedicineName() + ": $" + m.getPrice());
        }
        System.out.println("Total Amount Paid: $" + totalAmount);
        System.out.println("Payment Date: " + paymentDate);
    }
}

// Hospital Management System
class HospitalManagementSystem {
    private final ArrayList<Patient> patients;
    private final ArrayList<Doctor> doctors;
    private final ArrayList<Appointment> appointments;
    private final ArrayList<Payment> payments;
    private final ArrayList<Medicine> medicines;
    private final Scanner sc;

    public HospitalManagementSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        payments = new ArrayList<>();
        medicines = new ArrayList<>();
        sc = new Scanner(System.in);
        loadMedicines();
    }

    private void loadMedicines() {
        medicines.add(new Medicine("Paracetamol", 10));
        medicines.add(new Medicine("Antibiotic", 50));
        medicines.add(new Medicine("Cough Syrup", 25));
        medicines.add(new Medicine("Pain Killer", 40));
    }

    public void addPatient() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();
        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();

        Patient p = new Patient(name, age, gender, patientId, disease);
        patients.add(p);
        System.out.println("Patient added successfully!\n");
    }

    public void addDoctor() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();
        System.out.print("Enter Consultation Fee: ");
        double fee = Double.parseDouble(sc.nextLine());

        Doctor d = new Doctor(name, age, gender, doctorId, specialization, fee);
        doctors.add(d);
        System.out.println("Doctor added successfully!\n");
    }

    public void makeAppointment() {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = sc.nextLine();

        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found!");
            return;
        }

        System.out.print("Enter Date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        Appointment appointment = new Appointment(appointmentId, patient, doctor, date);

        System.out.println("Available Medicines:");
        for (int i = 0; i < medicines.size(); i++) {
            System.out.println((i + 1) + ". " + medicines.get(i).getMedicineName() + " - $" + medicines.get(i).getPrice());
        }

        System.out.print("How many medicines to buy? ");
        int count = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.print("Enter medicine number: ");
            int medIndex = Integer.parseInt(sc.nextLine()) - 1;
            if (medIndex >= 0 && medIndex < medicines.size()) {
                appointment.addMedicine(medicines.get(medIndex));
            } else {
                System.out.println("Invalid medicine selection.");
            }
        }

        appointments.add(appointment);
        System.out.println("Appointment created successfully!\n");
    }

    public void makePayment() {
        System.out.print("Enter Payment ID: ");
        String paymentId = sc.nextLine();

        System.out.print("Enter Appointment ID: ");
        String appointmentId = sc.nextLine();
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("Appointment not found!");
            return;
        }

        double total = appointment.getDoctor().getConsultationFee();
        for (Medicine m : appointment.getPrescribedMedicines()) {
            total += m.getPrice();
        }

        System.out.print("Enter Payment Date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        Payment payment = new Payment(paymentId, appointment, total, date);
        payments.add(payment);
        System.out.println("Payment recorded successfully!\n");
    }

    public void viewPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payments recorded.");
            return;
        }
        for (Payment p : payments) {
            p.displayInfo();
            System.out.println("-----------------------------");
        }
    }

    public void viewPatients() {
        for (Patient p : patients) {
            p.displayInfo();
            System.out.println();
        }
    }

    public void viewDoctors() {
        for (Doctor d : doctors) {
            d.displayInfo();
            System.out.println();
        }
    }

    public void viewAppointments() {
        for (Appointment a : appointments) {
            a.displayInfo();
            System.out.println("-----------------------------");
        }
    }

    private Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(id))
                return p;
        }
        return null;
    }

    private Doctor findDoctorById(String id) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equals(id))
                return d;
        }
        return null;
    }

    private Appointment findAppointmentById(String id) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(id))
                return a;
        }
        return null;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n*** Hospital Management System ***");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Create Appointment + Buy Medicines");
            System.out.println("4. Make Payment");
            System.out.println("5. View Patients");
            System.out.println("6. View Doctors");
            System.out.println("7. View Appointments");
            System.out.println("8. View Payments");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    makeAppointment();
                    break;
                case 4:
                    makePayment();
                    break;
                case 5:
                    viewPatients();
                    break;
                case 6:
                    viewDoctors();
                    break;
                case 7:
                    viewAppointments();
                    break;
                case 8:
                    viewPayments();
                    break;
                case 0:
                    System.out.println("Exiting System...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();
        hms.start();
    }
}
