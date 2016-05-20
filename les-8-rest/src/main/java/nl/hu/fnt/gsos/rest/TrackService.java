package nl.hu.fnt.gsos.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import nl.hu.fnt.gsos.service.NumberOfTracks;
import nl.hu.fnt.gsos.service.Track;
import nl.hu.fnt.gsos.service.TrackServiceImpl;

@Path("")

public class TrackService {

	private static TrackServiceImpl trackservice = new TrackServiceImpl();

	@Path("/tracks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracks() throws JSONException {

		List<Track> tracklist = trackservice.getTracks();

		for (Track track : tracklist) {
			System.out.println(track);
		}

		return tracklist;
	}

	@Path("/tracks/count")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public NumberOfTracks getCount() throws JSONException {

		List<Track> tracklist = trackservice.getTracks();
		NumberOfTracks trackcount = new NumberOfTracks(tracklist.size());

		System.out.println("There are: " + trackcount.getNumberOfTracks() + " tracks.");

		return trackcount;
	}

	@Path("/tracks/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTracksFromId(@PathParam("id") int id) throws JSONException {

		Track t = new Track(); // aanmaken object

		t = trackservice.getTrackById(id);

		System.out.println("The track with ID: " + id + " is: " + t);

		return t;
	}

	@Path("/tracks/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Track putNewTrack(@PathParam("id") int id) throws JSONException {

		Track t = new Track();

		if (trackservice.getTrackById(id) == null) {
			t = new Track(id, "PSY", "GANGNAM STYLE", 2012,
					"https://www.youtube.com/watch?v=9bZkp7q19f0&list=PLFgquLnL59amLh5g4ZZoSl1Wf9e0_rco7");

			System.out.println("The new track = " + t);

			List<Track> tracklist = trackservice.getTracks();
			tracklist.add(t);
			trackservice.setTracks(tracklist);

			System.out.println("The service now contains the following tracks:\n");

			for (Track track : tracklist) {
				System.out.println(track);
			}
		}

		return t;
	}

	@Path("/tracks/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Track deleteTrack(@PathParam("id") int id) throws JSONException {

		Track t = new Track();

		if (trackservice.getTrackById(id) != null) {
			t = trackservice.getTrackById(id);
			trackservice.remove(id);

			System.out.println("The following track has been removed: " + t);
			System.out.println("The service now contains the following tracks:\n");

			List<Track> tracklist = trackservice.getTracks();

			for (Track track : tracklist) {
				System.out.println(track);
			}
		}

		return t;
	}
}