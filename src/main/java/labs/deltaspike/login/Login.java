package labs.deltaspike.login;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
@Named
@RequestScoped
public class Login implements Serializable
{
    private static final long serialVersionUID = -9190867979657697391L;

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    public static final String REDIRECT_URL = "secured/secure.jsf?faces-redirect=true";

    private String username;
    private String password;
    private boolean remember;

    public Login()
    {
    }

    public void submit() throws IOException
    {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            String requestUrl = savedRequest != null ? savedRequest.getRequestUrl() : null;
            Faces.redirect(requestUrl != null ? requestUrl : REDIRECT_URL);
//            FacesContext.getCurrentInstance().getExternalContext().redirect(requestUrl != null ? requestUrl : REDIRECT_URL);
        }
        catch (AuthenticationException e) {
            Messages.addGlobalError("Unknown user, please try again");
            logger.error("Unknown user", e);
        }
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isRemember()
    {
        return remember;
    }

    public void setRemember(boolean remember)
    {
        this.remember = remember;
    }
}
