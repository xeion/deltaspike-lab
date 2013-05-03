package labs.deltaspike.exception;

import javax.enterprise.util.AnnotationLiteral;

public class FacesRequestLiteral extends AnnotationLiteral<FacesRequest> implements FacesRequest
{
    public static final FacesRequest INSTANCE = new FacesRequestLiteral();

    private FacesRequestLiteral() {
    }
}
