package pawelwaz;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pawelwaz.entity.Contact;

@Controller
public class DefaultController {
    
    @Autowired
    private ContactRepository repo;
    
    @RequestMapping("/")
    public String contactsAction(Model model, Contact contact) {
        List<Contact> contacts = repo.findAllByOrderByNameAsc();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
    
    @PostMapping("/search")
    public String searchAction(@RequestParam("search") String search, Model model, Contact contact) {
        search = "%" + search + "%";
        List<Contact> contacts = repo.findAllByNameLikeIgnoreCaseOrSurnameLikeIgnoreCaseOrPhoneLikeOrEmailLike(search, search, search, search);
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
    
    @PostMapping("/add")
    public String addAction(@Valid Contact contact) {
        repo.save(contact);
        return "redirect:/";
    }
    
    @PostMapping("/edit/{id}")
    public String editAction(@PathVariable(value="id") Long id, @Valid Contact contact) {
        contact.setId(id);
        repo.save(contact);
        return "redirect:/";
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteAction(@PathVariable(value="id") Long id) {
        repo.delete(id);
        return "redirect:/";
    }

    /**
     * @return the repo
     */
    public ContactRepository getRepo() {
        return repo;
    }

    /**
     * @param repo the repo to set
     */
    public void setRepo(ContactRepository repo) {
        this.repo = repo;
    }
    
}
