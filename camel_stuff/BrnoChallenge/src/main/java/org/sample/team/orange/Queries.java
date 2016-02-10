package org.sample.team.orange;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.orange.crud.model.Buzzword;
import org.jboss.orange.crud.model.User;
import org.sample.team.orange.model.BuzzWord;
import org.sample.team.orange.model.UserParams;

@Stateless
public class Queries {

    @PersistenceContext(unitName = "orange-crud-persistence-unit")
    private EntityManager em;
    
    public List<String> getUsers() {
        return em.createQuery("select u.email from User u", String.class).getResultList();
    }
    
    public User lookupUserByEmail(String email)
    {
        return em.createQuery("select u from User u where u.email = :email", User.class).getSingleResult();
    }
    
    public UserParams getParamsForUser(String email) {
        UserParams p = new UserParams();
        
        p.setEmail(email);
        
        User user = lookupUserByEmail(email);
        
        List<Buzzword> buzzword = em.createQuery(
                "select b from Buzzword b where b.user = :user", Buzzword.class)
                .setParameter("user", user)
                .getResultList();
        
        List<BuzzWord> buzzwords = new ArrayList<BuzzWord>();
        
        for (Buzzword v : buzzword) {
            for (String value : v.getBuzzword().split(",")) {
                BuzzWord b = new BuzzWord();
                b.setInOut(v.isExclude() ? "out" : "in");
                b.setWord(v.getBuzzword());
                buzzwords.add(b);
            }
            
        }
        
        p.setBuzzWords(buzzwords);
        
        List<String> urls = em.createQuery("select u.url from URL u where u.user = :user", String.class)
             .setParameter("user", user)
             .getResultList();
        
        p.setUrls(urls);
        
        return p;
    }
}
 