package br.com.fiap.garrovision.controller;

import br.com.fiap.garrovision.dominio.HistoricoEnergia;
import br.com.fiap.garrovision.service.HistoricoEnergiaService;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/historico-energia")
public class HistoricoEnergiaController {

    private final HistoricoEnergiaService historicoEnergiaService;

    public HistoricoEnergiaController() {
        this.historicoEnergiaService = new HistoricoEnergiaService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHistoricos() {
        List<HistoricoEnergia> historicos = historicoEnergiaService.buscarTodos();
        return Response.ok(historicos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistoricoById(@PathParam("id") int id) {
        HistoricoEnergia historico = historicoEnergiaService.buscarPorId(id);
        return Response.ok(historico).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarHistorico(HistoricoEnergia historico) {
        historicoEnergiaService.salvar(historico);
        return Response.status(Response.Status.CREATED).entity(historico).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarHistorico(@PathParam("id") int id, HistoricoEnergia historico) {
        historico.setIdHistorico(id);
        historicoEnergiaService.atualizar(historico);
        return Response.ok(historico).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerHistorico(@PathParam("id") int id) {
        historicoEnergiaService.remover(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
