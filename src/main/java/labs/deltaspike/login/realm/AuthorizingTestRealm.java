package labs.deltaspike.login.realm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import labs.deltaspike.jpa.Role;
import labs.deltaspike.jpa.User;
import labs.deltaspike.jpa.UserFacade;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
//@Stateless
public class AuthorizingTestRealm extends AuthorizingRealm implements Serializable
{
    private static final long serialVersionUID = -2192792862101272316L;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizingTestRealm.class);

//    @Inject
//    private UserFacade userFacade;

    public AuthorizingTestRealm()
    {

    }

//    @PostConstruct
//    public void initCDI()
//    {
//        logger.debug("(initCDI)");
//    }

    @Override
    public boolean supports(AuthenticationToken token)
    {
        logger.trace("(supports) Authentication support: false");
        return false;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String username = (String) getAvailablePrincipal(principals);

        // Fails to include CM transaction!
//        UserFacade userFacade = BeanProvider.getContextualReference(UserFacade.class, false);
//
//        User user = userFacade.find(username);
//        List<Role> roles = user.getRoles();
//        Set<String> roleNames = new HashSet<>();
//        for (Role role : roles) {
//            roleNames.add(role.name());
//        }

        Set<String> roleNames = new HashSet<>();
//        roleNames.add("ADMIN"); // this should fail authorization (/secured/** = user, roles[role1])
        roleNames.add("role1");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);

        return info;
    }

    /**
     * Authentication steps
     * 1. call supports() to check if this authenticator supports the authenticationtoken (eg UsernamePasswordTokens...)
     * 2. if true, call doGetAuthenticationInfo
     *   1. Inspects the token for the identifying principal (account identifying information)
     *   2. Based on the principal, looks up corresponding account data in the data source
     *   3. Ensures that the token's supplied credentials matches those stored in the data store
     *   4. If the credentials match, an AuthenticationInfo instance is returned that encapsulates the account data in a format Shiro understands
     *   5. If the credentials DO NOT match, an AuthenticationException is thrown
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        return null;
    }
}
