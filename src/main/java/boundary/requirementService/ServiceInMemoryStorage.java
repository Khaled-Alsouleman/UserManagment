package boundary.requirementService;

import control.InMemoryStorage;
import entity.User_InMemoryStorage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceInMemoryStorage {
    //http://localhost:8080/tech11/user-manager/users
    @Inject
    private InMemoryStorage storage;

    @POST
    public Response addNewUser(final User_InMemoryStorage user) {
       if (storage.create(user)) return Response.ok(user).status(201).build();
       else return Response.status(400).build();
    }

    @PUT
    @Path("{id: \\d+}")
    public Response updateUser(@PathParam("id") final Long id, final User_InMemoryStorage user) {
     if (storage.update(id,user))return Response.noContent().build();
     else return Response.status(400).build();
    }
}