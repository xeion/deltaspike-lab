package labs.deltaspike.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
@Entity
//@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"domain", "userId"})})
@Table(name = "delta_user")
@NamedQueries({
    @NamedQuery(
        name = "User.find", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(
        name = "User.list", query = "SELECT u FROM User u")
})
public class User implements Serializable
{
    private static final long serialVersionUID = -1408345558043312592L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "delta_user_roles", joinColumns = { @JoinColumn(name = "userId") })
    @Column(name = "role")
    private List<Role> roles;


    public User()
    {
    }

    public User(String userId, String firstName, String lastName, List<Role> roles)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("User");
        sb.append("[id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(']');
        return sb.toString();
    }
}
