package net.mouakkal.patient;

import net.mouakkal.patient.entities.Patient;
import net.mouakkal.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.beans.BeanProperty;
import java.util.Date;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }
    public void run(String... args) throws  Exception{
        patientRepository.save(new Patient(null,"MOUAKKAL","Nouhayla",new Date(), false , 500));
        patientRepository.save(new Patient(null,"OUAHABI","Otmane",new Date(), false , 501));
        patientRepository.save(new Patient(null,"SAMADI","Imad",new Date(), true , 220));
    }
    }
