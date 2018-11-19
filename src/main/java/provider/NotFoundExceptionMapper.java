package provider;

import org.json.simple.JSONObject;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        JSONObject json = new JSONObject();
        json.put("message", "Couldn't find the requested object");

        return Response.status(Response.Status.NOT_FOUND)
                .entity(json.toJSONString())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
