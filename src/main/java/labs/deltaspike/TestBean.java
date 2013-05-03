package labs.deltaspike;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;
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
@SessionScoped
public class TestBean implements Serializable
{
    private static final long serialVersionUID = -3608994421300510623L;

    private static final Logger logger = LoggerFactory.getLogger(TestBean.class);

    @Inject
    private ProjectStage projectStage;

    @Inject
    private ThaMessages messages;

    @Inject
    private Event<ExceptionToCatchEvent> exceptionEvent;

    public String sayHello(String name)
    {
        return messages.hello(name);
    }

    public String throwException()
    {
        logger.debug("(throwException) Throwing!!");
        throw new RuntimeException("A Exception is thrown!!");
    }

    public String fireException()
    {
        logger.debug("(fireException) Firing!!");
        exceptionEvent.fire(new ExceptionToCatchEvent(new RuntimeException("A Exception is fired!!")));
        return "index";
    }

    public ProjectStage getProjectStage()
    {
        return projectStage;
    }

    public String deltaSpikeProjectStage()
    {
        return ConfigResolver.getPropertyValue("org.apache.deltaspike.ProjectStage");
    }
}
