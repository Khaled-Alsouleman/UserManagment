package boundary.requirementService;

import control.PostgresDatabase;
import entity.User_PostgresDB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("usersDB")
@Produces(MediaType.APPLICATION_JSON)
public class ServicePostgresDB {
    @Inject
    PostgresDatabase storage;
    //http://localhost:8080/tech11/user-manager/usersDB
    @POST
    public Response addNewUser(final User_PostgresDB user) {
        if (storage.create(user)) return Response.ok(user).status(201).build();
        else return Response.status(400).build();
    }

    @PUT
    @Path("{id: \\d+}")
    public Response updateUser(@PathParam("id") final long id, final User_PostgresDB user) {
        if (storage.update(id, user)) return Response.noContent().build();
        else return Response.status(400).build();
    }
}
