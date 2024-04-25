package api;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.Calculation;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class CalculationService {
	
	@PersistenceContext(unitName = "hello")
	private EntityManager entityManager;
	
	private Response createError(Response.Status status, String message) {
		return Response.status(status).entity(message).build();
	}
	
	
	private Response createInternalServerError(String message) {
		if (message == null) {
			message = "An error occurred";
		}
		
		return this.createError(Response.Status.INTERNAL_SERVER_ERROR, message);
	}
	
	
	@GET
	@Path("calculations")
	public Response getCalculations() {
		try {
			TypedQuery<Calculation> query = entityManager.createQuery(
					"SELECT c FROM Calculation c", Calculation.class
					);
			
			List<Calculation> calculations = query.getResultList();
			
			return Response.ok(calculations).build();
		} catch(Exception e) {
			return this.createInternalServerError(null);
		}
	}
	
	
	@POST
	@Path("calc")
	public Response createCalculation(Calculation calc) {
		try {
			String operation = calc.getOperation();
			Result result = new Result();
			
			int num1 = calc.getNumber1();
			int num2 = calc.getNumber2();
			
			switch (operation) {
			case "+":
				result.setResult(num1 + num2);
				break;
			case "-":
				result.setResult(num1 - num2);
				break;
			case "/":
				result.setResult(num1 / num2);
				break;
			case "*":
				result.setResult(num1 * num2);
				break;
			default:
				return this.createInternalServerError("please enter a valid operation");
			}
			
			entityManager.persist(calc);
			return Response.ok(result).build();
		} catch(Exception e) {
			return this.createInternalServerError(null);
		}
	}
	
	
}
