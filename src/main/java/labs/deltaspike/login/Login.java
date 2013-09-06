package labs.deltaspike.login;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;


@Named
@RequestScoped
public class Login implements Serializable
{
    private static final long serialVersionUID = -9190867979657697391L;

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    private static final String REDIRECT_URL = "secured/secure.jsf?faces-redirect=true";

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
        } catch (AuthenticationException e) {
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



/**
 * @author Edward P. Legaspi
 * @since Oct 10, 2012
 */
/*@LocalBean
@Named
@Stateless
public class Login
{
    @Inject
    private Subject subject;

    private static final long serialVersionUID = -9190867979657697391L;

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    public static final String REDIRECT_URL = "secured/secure.jsf?faces-redirect=true";

    private String username;

    private String password;

    public String login()
    {
        if (subject.isAuthenticated()) {
            logger.debug("(login) active subject={}, user={}", subject,
                    subject.getPrincipal());
            return redirect();
        } else {
            logger.debug(
                    "(login) loggerin to the system with user={}, password={}",
                    getUsername(), getPassword());
            AuthenticationToken token = new UsernamePasswordToken(
                    getUsername(), getPassword());
            try {
                subject.login(token);

                return redirect();
            } catch (Exception e) {
                logger.error("(login) error login {}", e);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Error login"));
            }
        }

//        return "home.xhtml";
        return REDIRECT_URL;
    }

    public String logout()
    {
        logger.debug("(logout) logout");
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Logout Ok"));

        return "login.xhtml";
    }

    private String redirect()
    {
        logger.debug("(redirect) redirect");
        if (subject.hasRole("admin")) {
            return "admin.xhtml";
        } else if (subject.hasRole("schwartz")) {
            return "schwartz.xhtml";
        } else if (subject.hasRole("goodguy")) {
            return "goodguy.xhtml";
        }
//        return "home.xhtml";
        return REDIRECT_URL;
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

    @Secured
    @RequiresAuthentication
    public String requiresAuthentication()
    {
        return "";
    }

    @Secured
    @RequiresRoles({"admin"})
    public void requiresRolesMerchant()
    {
        logger.debug("admin");
    }

    @Secured
    @RequiresRoles({"schwartz"})
    public void requiresRolesSupport()
    {
        logger.debug("schwartz");
    }

    @Secured
    @RequiresPermissions({"lightsaber"})
    public void requiresPermissionlightsaber()
    {
        logger.debug("lightsaber.action");
    }

    @Secured
    @RequiresPermissions({"winnebago"})
    public void requiresPermissionSupport()
    {
        logger.debug("winnebago.action");
    }

    @Secured
    @RequiresPermissions({"winnebago", "lightsaber"})
    public void requiresPermissionlightsaberOrwinnebago()
    {
        logger.debug("winnebago+lightsaber.action");
    }
}*/

