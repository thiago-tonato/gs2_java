package br.com.fiap.garrovision.controller;

import br.com.fiap.garrovision.dominio.Residencia;
import br.com.fiap.garrovision.exceptions.APIException;
import br.com.fiap.garrovision.service.ResidenciaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/residencias")
public class ResidenciaController {

    private final ResidenciaService residenciaService;

    public ResidenciaController() {
        this.residenciaService = new ResidenciaService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResidencia(@PathParam("id") int id) {
        try {
            Residencia residencia = residenciaService.buscarResidenciaPorId(id);
            return Response.ok(residencia).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodasResidencias() {
        try {
            List<Residencia> residencias = residenciaService.buscarTodasResidencias();
            return Response.ok(residencias).build();
        } catch (APIException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarResidencia(Residencia residencia) {
        try {
            residenciaService.salvarResidencia(residencia);
            return Response.status(Response.Status.CREATED).build();
        } catch (BadRequestException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (APIException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarResidencia(@PathParam("id") int id, Residencia residencia) {
        try {
            residenciaService.buscarResidenciaPorId(id);
            residenciaService.atualizarResidencia(residencia);
            return Response.ok().build();
        } catch (NotFoundException | BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirResidencia(@PathParam("id") int id) {
        try {
            residenciaService.excluirResidencia(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
