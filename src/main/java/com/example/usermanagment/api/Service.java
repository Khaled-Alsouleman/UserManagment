package com.example.usermanagment.api;

import com.example.usermanagment.database.UserMemory;
import com.example.usermanagment.models.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Service {
   @Inject
    private UserMemory storage ;

    @GET
    public List<User> getAllUsers() {return storage.getUsers();}

    @GET
    @Path( "{id: \\d+}" )
    public User getSingleUser( @PathParam("id") final Long  id ){
        Optional<User> optionalUser= storage.getSingleUser(id);
        if (optionalUser.isPresent() )  return storage.getSingleUser(id).get();
        else throw new WebApplicationException(400);
    }

    @POST
    public void  addNewUser(final User user){storage.addUser(user);}


    @PUT
    @Path( "{id: \\d+}" )
    public void updateUser( @PathParam("id") final Long  id ,final User user){
        Optional<User> optionalUser= storage.getSingleUser(id);
        if (optionalUser.isPresent() && id == user.getId()) storage.updateUser(id, user);
        else  throw new WebApplicationException(400);
    }
}