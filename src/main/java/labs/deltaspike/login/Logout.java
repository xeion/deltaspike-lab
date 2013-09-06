package labs.deltaspike.login;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Faces;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
@Named
@RequestScoped
public class Logout
{
    private static final String HOME_URL = "login.jsf";

    public void submit() throws IOException
    {
        SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }
}
