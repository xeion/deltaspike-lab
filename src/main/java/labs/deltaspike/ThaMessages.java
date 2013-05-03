package labs.deltaspike;

import javax.inject.Named;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageContextConfig;
import org.apache.deltaspike.core.api.message.MessageTemplate;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
@MessageBundle
//@MessageContextConfig(messageInterpolator = CustomMessageInterpolator.class)
public interface ThaMessages
{
    @MessageTemplate("{hello_key}")
    String hello(String name);
}
