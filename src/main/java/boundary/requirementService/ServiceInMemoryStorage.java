package boundary.requirementsService;

import control.InMemoryStorage;
import entity.User_InMemoryStorage;
import utils.UserValidation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceInMemoryStorage {
    @Inject
    private InMemoryStorage storage;

    @POST
    public Response addNewUser(final User_InMemoryStorage user) {
        if (UserValidation.isValidToCreate(user)) {
            storage.create(user);
            return Response.ok(user).status(201).build();
        }
        return Response.status(400).build();
    }

    @PUT
    @Path("{id: \\d+}")
    public Response updateUser(@PathParam("id") final Long id, final User_InMemoryStorage user) {
        try {
            if (id.compareTo(user.getId()) != 0) {
                return Response.status(400).build();
            } else if (!storage.containsId(id)) {
                return Response.status(404).build();
            } else {

                storage.update(UserValidation.isValidToUpdate(user, storage.readById(id)));
                return Response.noContent().build();
            }
        } catch (NullPointerException e) {
            return Response.status(400).build();
        }

    }

}