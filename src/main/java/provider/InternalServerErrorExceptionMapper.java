package provider;

import org.json.simple.JSONObject;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException> {
    @Override
    public Response toResponse(InternalServerErrorException exception) {
        JSONObject json = new JSONObject();
        json.put("message", "Unfortunately, the 500 error occurs while processing your request");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(json.toJSONString())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
