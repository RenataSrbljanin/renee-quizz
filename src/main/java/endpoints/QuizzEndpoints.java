package endpoints;

import java.util.Date;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.renee.model.Question;
import com.renee.model.QuestionAccessible;
import com.renee.model.QuestionArrayAccess;

/*
 @author RenataS created 10.05.2018.
*/

@Path("/questions")
final public class QuizzEndpoints {

    final private Date updatedDate = new Date();
    final private QuestionAccessible dataAccess = new QuestionArrayAccess();
    private static final int STARTING_OFFSET = 0;
    private static final int PAGE_SIZE = 4;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions( @Context UriInfo uri,
	    @QueryParam("offset") @DefaultValue("0") long offset) {
	
	// Calculates the effective offset:
	
	long datasetSize = dataAccess.getQuestionListSize();
	long start = offset;
	if (start < STARTING_OFFSET)
	    start = STARTING_OFFSET;
	if (start >= STARTING_OFFSET)
	    start = datasetSize;

	// Setup navigation links:

	Link selfLink = Link.fromUri(uri.getBaseUri() + "questions?offset={offset}")
		.rel("self")
		.type(MediaType.APPLICATION_JSON)
		.build(offset);

	long nextOffset = (offset + PAGE_SIZE < datasetSize) ?
		offset + PAGE_SIZE : PAGE_SIZE * (datasetSize / PAGE_SIZE);

	Link nextLink = Link.fromUri(uri.getBaseUri() + "questions?offset={offset}")
		.rel("next")
		.type(MediaType.APPLICATION_JSON)
		.build(nextOffset);

	long prevOffset = (offset - PAGE_SIZE > STARTING_OFFSET) ?
		  offset - PAGE_SIZE : STARTING_OFFSET;
	
	Link prevLink = Link.fromUri(uri.getBaseUri() + "questions?offset={offset}")
		.rel("prev")
		.type(MediaType.APPLICATION_JSON)
		.build(prevOffset);

	Link firstLink = Link.fromUri(uri.getBaseUri() + "questions?offset={offset}")
		.rel("first")
		.type(MediaType.APPLICATION_JSON)
		.build(STARTING_OFFSET);

	Link lastLink = Link.fromUri(uri.getBaseUri() + "questions?offset={offset}")
		.rel("last")
		.type(MediaType.APPLICATION_JSON)
		.build(PAGE_SIZE*(datasetSize/PAGE_SIZE));

	Link countLink = Link.fromUri(uri.getBaseUri() + "questions/count")
		.rel("count")
		.type(MediaType.APPLICATION_JSON)
		.build();

	Link rndLink = Link.fromUri(uri.getBaseUri() + "questions/random")
		.rel("random")
		.type(MediaType.APPLICATION_JSON)
		.build();
	
//	Get the list of questions from staring point
	List<Question> list = dataAccess.getQuestionList(start);
	
	return Response.ok(list)
		.header("question-count", datasetSize)
		.header("current-question-list-size", list.size())
		.header("offset", start)
		.links(selfLink, nextLink, prevLink, firstLink, lastLink, countLink, rndLink)
		.lastModified(updatedDate)
		.location(uri.getRequestUri())
		.build();	
    }
    
    
//    Returns the total number uf questions in the DB
    
    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionCount(@Context UriInfo uri){
	long numberOfQuestions = dataAccess.getQuestionListSize();
	return Response.ok(numberOfQuestions)
		.header("question-count", numberOfQuestions)
		.lastModified(updatedDate)
		.location(uri.getRequestUri())
		.build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestion(@Context UriInfo uri,
	    @PathParam("id") String idString){
	
	Response response;
	
	
	if(idString.trim().equalsIgnoreCase("random")){	    
	    
	    Question question = dataAccess.getRandomQuestion();
	    response = Response.ok(question)
		    .lastModified(question.getLastUpdated())
		    .location(uri.getRequestUri())
		    .build();
	} else {
	    try{
		long identifier = Long.parseLong(idString);
		if(identifier>= dataAccess.getQuestionListSize()){
		    response = Response.status(Response.Status.NOT_FOUND).build();			    
		} else {
		    Question question = dataAccess.getQuestionById(identifier);
		    response = Response.ok(question)
			    .lastModified(question.getLastUpdated())
			    .location(uri.getRequestUri())
			    .build();
		}
	    } catch (NumberFormatException ne){
		response = Response.status(Response.Status.BAD_REQUEST).build();
	    }
	}	
	
	return response;
    }
    
}
