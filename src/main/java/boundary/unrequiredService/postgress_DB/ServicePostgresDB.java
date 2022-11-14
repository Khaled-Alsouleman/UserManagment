package boundary.additionallyService.postgress_DB;

import control.PostgresDatabase;
import entity.UserDTO;
import entity.User_PostgresDB;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("usersDB")
@Produces(MediaType.APPLICATION_JSON)
public class ServicePostgresDB {
    @Inject
    PostgresDatabase storage;
    //http://localhost:8080/tech11/user-manager/usersDB
    //http://localhost:8080/tech11/user-manager/usersDB?showPasswords=true
    @GET
    public Response getAllUsers(@QueryParam("limit") @DefaultValue("10") final long limit ,
                                @QueryParam("offset") @DefaultValue("0") final long offset,
                                @QueryParam("showPasswords") @DefaultValue("false") final boolean showPassword) {
        if (showPassword) return Response.ok(storage.findAll(limit,offset)).build();
        else {
            final List<UserDTO> userDTOs = storage.findAll(limit,offset).stream().map(user -> user.toDTO()).collect(Collectors.toList());
            return Response.ok(userDTOs).build();
        }
    }

    @GET
    @Path("{id: \\d+}")
    public Response getUser(@PathParam("id") final Long id, @QueryParam("showPassword") @DefaultValue("false") final boolean showPassword) {

        final User_PostgresDB user = storage.readById(id);
        if (user != null) {
            if (showPassword) return Response.ok(user).build();
            return Response.ok(user.toDTO()).build();
        } else return Response.status(404).build();
    }

    @DELETE
    @Path("{id: \\d+}")
    public Response deleteUser(@PathParam("id") final Long id) {
        if (storage.delete(id)) return Response.noContent().build();
        else return Response.status(404).build();
    }
}
