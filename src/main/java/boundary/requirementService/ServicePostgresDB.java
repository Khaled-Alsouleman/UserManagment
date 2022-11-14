package boundary.requirementsService;

import control.PostgresDB;
import entity.User_PostgresDB;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("usersDB")
public class ServicePostgresDB {

    @GET

    public String  ping() {
        return "hello world" ;
    }
}
