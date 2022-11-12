package boundary;

import control.UserService_Singleton;
import entity.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Service {
   @Inject
    private UserService_Singleton storage ;

    @GET
    public List<User> getAllUsers() {return storage.getUsers();}

    @GET
    @Path( "{id: \\d+}" )
    public Response getSingleUser(@PathParam("id") final Long  id ){
        User user = storage.getSingleUser(id);
        if (user.getId()==-1l ) return Response.status(404).build();
        return Response.ok(user).build();
    }

    @POST
    public Response  addNewUser(final User user){
        storage.addUser(user);
        return Response.ok(user).status(201).build();
    }

    @DELETE
    @Path( "{id: \\d+}" )
    public Response deleteUser(@PathParam("id") final Long  id ){
        User user = storage.getSingleUser(id);
        if (user.getId()==-1 ) return Response.status(404).build();
        storage.removeUser(id);
        return Response.noContent().build();
    }

    @PUT
    @Path( "{id: \\d+}" )
    public Response updateUser(@PathParam("id") final Long  id, final User userToUpdate ){
        User user = storage.getSingleUser(id);
        if (user.getId()==-1 || id.compareTo(userToUpdate.getId()) !=0) return Response.status(400).build();
        storage.updateUser(id, userToUpdate);
        return Response.noContent().build();
    }


}