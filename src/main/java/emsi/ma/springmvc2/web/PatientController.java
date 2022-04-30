package emsi.ma.springmvc2.web;

import emsi.ma.springmvc2.entites.Patient;
import emsi.ma.springmvc2.repositories.PatientRepositories;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepositories patientRepositories;
    @GetMapping(path = "/user/home")
    public String patients(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword,
                           @RequestParam(name="prenom",defaultValue = "") String prenom,
                           @RequestParam(name = "score", defaultValue = "10") int score){
        Page<Patient> pagePatients = patientRepositories.findByNomContainsAndPrenomContainsAndScoreGreaterThanEqual(keyword,prenom,score,PageRequest.of(page, size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prenom",prenom);
        model.addAttribute("score",score);
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id , String keyword,String prenom,int score, int page){
        patientRepositories.deleteById(id);
        return "redirect:/user/home?page="+page+"&keyword="+keyword+"&score"+score+"&prenom"+prenom;
    }

    @GetMapping("/")
    public String Home(){
        return "redirect:/user/home";
    }

    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepositories.findAll();
    }

    @GetMapping("/admin/fromPatients")
    public String fromPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "fromPatients";
    }


    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "fromPatients";
        patientRepositories.save(patient);
        return "redirect:/user/home";
    }


    @GetMapping("/admin/editPatient")
    public String editPatient(Model model ,long id, String keyword,String prenom,int score ,int page){
        Patient patient=patientRepositories.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prenom",prenom);
        model.addAttribute("score",score);
        return "editPatient";
    }

    @PostMapping(path = "/admin/editPatient")
    public String editPatient(Model model, @Valid Patient patient, BindingResult bindingResult,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword ,
                              @RequestParam(defaultValue = "") String prenom ,
                              @RequestParam(defaultValue = "10") int score){
        if(bindingResult.hasErrors()) return "fromPatients";
        patientRepositories.save(patient);
        return "redirect:/user/home?page"+page+"&keyword"+keyword+"&score"+score+"&prenom"+prenom;
    }
}
