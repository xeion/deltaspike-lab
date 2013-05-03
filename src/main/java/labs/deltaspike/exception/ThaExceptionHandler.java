package labs.deltaspike.exception;

import org.apache.deltaspike.core.api.exception.control.BeforeHandles;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Class description
 *
 * @author <a href="mailto:daniel.eriksson@ub.uu.se">Daniel Eriksson </a>- Uppsala
 *         University Library, Sweden
 * @version $Revision$, $Date$, $Author$
 */
@ExceptionHandler
public class ThaExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(ThaExceptionHandler.class);

    public void logException(@BeforeHandles @Handles ExceptionEvent<Throwable> evt)
    {
        logger.error("(logException)", evt.getException());
        evt.handledAndContinue();
    }
}
