package net.mouakkal.patient.web;

import jakarta.validation.Valid;
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
    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }
    @GetMapping(path = "/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Patient> patientPage = patientRepository.findByNomContains(kw, PageRequest.of(page, size));
        model.addAttribute("listPatients", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "index";
    }

    @GetMapping(path = "/admin/delete")
    public String delete(@RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }
    /*@GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }*/
    @GetMapping("/admin/formPatients")

    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")

    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors())   return "formPatients";
        if (patient.getId() != 0) {
            Patient existingPatient = patientRepository.findById(patient.getId()).orElse(null);
            if (existingPatient != null) {
                existingPatient.setNom(patient.getNom());
                existingPatient.setDateNaissance(patient.getDateNaissance());
                existingPatient.setMalade(patient.getMalade());
                existingPatient.setScore(patient.getScore());
                patient = existingPatient;
            }
        }
        patientRepository.save(patient);
        return "redirect:/index?page"+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")

    public String editPatient(Model model, Long id, @RequestParam int page, @RequestParam String keyword){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }
}
