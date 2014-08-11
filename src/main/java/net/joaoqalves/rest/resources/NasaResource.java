package net.joaoqalves.rest.resources;

import com.yammer.metrics.annotation.Timed;
import net.joaoqalves.nasa.Mars;
import net.joaoqalves.nasa.Rover;
import net.joaoqalves.nasa.exception.RoverIllegalPosition;
import net.joaoqalves.nasa.position.Coordinate;
import net.joaoqalves.nasa.position.Position;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaoqalves on 11/08/14.
 */
@Path("/explore")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class NasaResource {

    @POST
    @Timed
    public List<Position> explore(@Valid Mars mars) throws RoverIllegalPosition, InterruptedException {

        mars.explore();
        return mars.getRoverPositions();
    }

}
