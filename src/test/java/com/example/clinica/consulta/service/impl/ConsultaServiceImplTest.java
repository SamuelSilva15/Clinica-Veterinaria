package com.example.clinica.consulta.service.impl;

import com.example.clinica.consulta.exception.ConsultaNotFoundException;
import com.example.clinica.consulta.model.Consulta;
import com.example.clinica.consulta.repository.ConsultaRepository;
import com.example.clinica.consulta.service.ConsultaService;
import com.example.clinica.veterinario.exception.VeterinarioNotFoundException;
import com.example.clinica.veterinario.model.Veterinario;
import io.swagger.models.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ConsultaServiceImplTest {

    @Captor
    private ArgumentCaptor<Consulta> captor = ArgumentCaptor.forClass(Consulta.class);

    private ConsultaRepository repository;

    private ConsultaService service;

    @BeforeEach
    public void setup(){
        repository = Mockito.mock(ConsultaRepository.class);
        service = new ConsultaServiceImpl(repository);
    }

    @Test
    public void deveriaCriarNovaConsulta(){
        //DADO
        Consulta consulta2 = new Consulta(17L, "Samuel", "1234");
        Mockito.when(repository.save(consulta2)).thenReturn(consulta2);

        //QUANDO
        Consulta consulta = service.save(consulta2);

        //ENTÃO
        assertNotNull(criaConsulta());
        assertEquals(17L, consulta.getId());
    }

    @Test
    public void deveriaExibirTodosAsConsultas(){
        Consulta consulta = new Consulta(16L, "trikas", "hebears");
        Consulta consulta1 = criaConsulta();

        Mockito.when(repository.findAll()).thenReturn(List.of(consulta, consulta1));

        List<Consulta> consultas = service.findAll();

        assertEquals(2, consultas.size());
        assertNotNull(consulta);
        assertEquals(16L, consultas.get(0).getId());
        assertEquals("trikas", consultas.get(0).getNomeCliente());

        assertNotNull(consulta1);
        assertEquals(14L, consultas.get(1).getId());
        assertEquals("samuel", consultas.get(1).getNomeCliente());
    }

    @Test
    public void deveriaBuscarPorId(){
        //Dado
        Mockito.when(repository.findById(14L)).thenReturn(Optional.of(criaConsulta()));

        //Quando
        Consulta consulta = service.findById(14L);

        //Entao
        assertNotNull(consulta);
        assertEquals(14L, consulta.getId());
        assertEquals("samuel", consulta.getNomeCliente());
        assertEquals("1234", consulta.getCpfCliente());
    }

    @Test
    public void deveLancarVeterinarioNotFoundExceptionQuandoOVeterinarioNaoForEncontrado() {
        //DADO
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //QUANDO
        assertThrows(ConsultaNotFoundException.class, () -> service.findById(criaConsulta().getId()));
    }

    @Test
    public void deveriaDeletarUmVeterinario() {
        Consulta consulta = criaConsulta();
        when(repository.existsById(consulta.getId())).thenReturn(Boolean.TRUE);

        service.deleteById(consulta.getId());

        verify(repository).deleteById(consulta.getId());
    }

    @Test
    public void AtualizaUmaConsulta(){
        Mockito.when(repository.findById(16L)).thenReturn(Optional.of(criaConsulta()));

        Consulta consulta = new Consulta();
        consulta.setNomeCliente("samuelson");
        consulta.setData("12/01/2008");
        consulta.setCpfCliente("12345");
        service.atualizaDados(16L, consulta);

        verify(repository).save(captor.capture());

        assertEquals("samuelson", captor.getValue().getNomeCliente());
        assertEquals("12/01/2008", captor.getValue().getData());
        assertEquals("1234", captor.getValue().getCpfCliente());
    }

    private Consulta criaConsulta() {
        return new Consulta(14L, "samuel", "1234");
    }
}
