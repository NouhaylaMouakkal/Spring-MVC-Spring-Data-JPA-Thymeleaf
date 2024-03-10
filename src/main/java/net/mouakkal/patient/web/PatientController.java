package net.mouakkal.patient.web;

import lombok.AllArgsConstructor;
import net.mouakkal.patient.entities.Patient;
import net.mouakkal.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String index(Model model) {
        List<Patient> patientList = patientRepository.findAll();
        model.addAttribute("patients", patientList);
        return null;
    }
    @GetMapping(path = "/deletePatient")
    public String delete(Long id,int page,String keyword ){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

}
