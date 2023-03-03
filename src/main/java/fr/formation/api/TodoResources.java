package fr.formation.api;

import fr.formation.models.Todo;
import fr.formation.utils.TodoService;
import fr.formation.utils.Urgence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/todos")
public class TodoResources {
    private final TodoService service = TodoService.getInstance();
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> afficherJson() {
        return service.voirTodos();
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Todo> afficherXml() {
        return service.voirTodos();
    }

    @GET
    @Path("/{id}/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenirParId(@PathParam("id") int id) {
        Todo todo = service.obtenirParId(id);
        if(todo == null){
            return Response.status(404)
                    .entity("Rien trouvé")
                    .build();
        } else {
            return Response.status(200)
                    .entity(todo)
                    .build();
        }
    }

    // Méthode pour afficher un todo en XML (par /Id et en spécifiant le Path /xml)
    @GET
    @Path("{id}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response obtenirParIdEnXML(@PathParam("id") int id) {
        Todo todo = service.obtenirParId(id);
        if (todo == null) {
            return Response.status(404).entity("Todo introuvable").build();
        }
        return Response.ok(todo, MediaType.APPLICATION_XML).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Todo ajouterTodo(@FormParam("urgence") String urgence,
                            @FormParam("titre") String titre,
                            @FormParam("description") String description) {
        Todo todo = new Todo(urgence, titre, description);
        service.ajouterTodo(todo);
        return todo;
    }
    @DELETE
    @Path("/delete/{id}")
    public Response supprimerParId(@PathParam("id") int id) {
        Todo todo = service.obtenirParId(id);
       if (todo != null){
           service.supprimerParId(id);
           return Response.status(200)
                   .entity("Suppression bien réalisée")
                   .build();
       } else {
           return Response.status(404)
                   .entity("Todo non trouvé ou suppression impossible")
                   .build();
       }
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateParId(@PathParam("id") int id,
                            @FormParam("urgence") String urgence,
                            @FormParam("titre") String titre,
                            @FormParam("description") String description) {
        Todo todoToUpdate = service.obtenirParId(id);
        if (todoToUpdate != null) {
            if (urgence != null) {
                todoToUpdate.setUrgence(Urgence.valueOf(urgence));
            }
            if (titre != null) {
                todoToUpdate.setTitre(titre);
            }
            if (description != null) {
                todoToUpdate.setDescription(description);
            }
            service.updateParId(id, todoToUpdate);
            return Response.status(200)
                    .entity("Todo bien modifiée")
                    .entity(todoToUpdate)
                    .build();
        } else {
            return Response.status(404)
                    .entity("Modification impossible : ressource non trouvée")
                    .build();
        }
    }

    @GET
    @Path("error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response serveur500() {
        String errorMessage = "Une erreur serveur est produite.";
        return Response.status(500).entity(errorMessage).build();
    }



}


