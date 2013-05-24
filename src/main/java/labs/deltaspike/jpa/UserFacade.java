package labs.deltaspike.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
@Startup
@Singleton
@Stateless
public class UserFacade implements Serializable
{
    @PersistenceContext(unitName = "deltaspikePU")
    private EntityManager em;

    public User find(Integer id) {
        return em.find(User.class, id);
    }

    public User find(String username) {
        return em.createNamedQuery("User.find", User.class)
            .setParameter("userId", username)
            .getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Produces
    @Named("users")
    public List<User> list() {
        return em.createNamedQuery("User.list", User.class).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer create(User user) {
        em.persist(user);
        return user.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @PostConstruct
    public void init()
    {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        create(new User("daner275", "Daniel", "Eriksson", roles));
    }
}
