/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawelwaz;

import java.util.List;
import pawelwaz.entity.Contact;
import org.springframework.data.repository.CrudRepository;


public interface ContactRepository extends CrudRepository<Contact, Long> {
    
    public List<Contact> findAllByOrderByNameAsc();
    public List<Contact> findAllByNameLikeIgnoreCaseOrSurnameLikeIgnoreCaseOrPhoneLikeOrEmailLike(String name, String surname, String phone, String email);
    
}
