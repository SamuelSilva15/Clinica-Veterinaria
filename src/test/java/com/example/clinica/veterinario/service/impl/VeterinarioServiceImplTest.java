package com.example.clinica.veterinario.service.impl;

import com.example.clinica.veterinario.exception.VeterinarioNotFoundException;
import com.example.clinica.veterinario.model.Veterinario;
import com.example.clinica.veterinario.repository.VeterinarioRepository;
import com.example.clinica.veterinario.service.VeterinarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class VeterinarioServiceImplTest {

    @Captor
    private ArgumentCaptor<Veterinario> captor = ArgumentCaptor.forClass(Veterinario.class);
    private VeterinarioRepository repository;
    private VeterinarioService service;

    @BeforeEach
    public void setup(){
        repository = Mockito.mock(VeterinarioRepository.class);
        service = new VeterinarioServiceImpl(repository);
    }

    @Test
    public void deveriaCriarNovaConsulta() {
        //DADO
        Veterinario veterinario1 = new Veterinario(14L, "Samuel", "araujo@email.com",
                "senha", "1234", "12/01/2004", "12345");

        Mockito.when(repository.save(veterinario1)).thenReturn(veterinario1);

        //QUANDO
        Veterinario veterinario = service.save(veterinario1);

        //ENTÃO
        assertNotNull(criaVeterinario());
        assertEquals(14L, veterinario.getId());
    }

    @Test
    public void deveriaBuscarTodosOsVeterinarios(){
        //DADO
        Veterinario veterinario1 = new Veterinario(15L, "Trikas", "araujo@email.com",
                "senha", "1234", "12/01/2004", "12345");
        Veterinario veterinario = criaVeterinario();
        Mockito.when(repository.findAll()).thenReturn(List.of(veterinario, veterinario1));

        //QUANDO
        List<Veterinario> veterinarios = service.findAll();

        //ENTAO
        assertNotNull(veterinario1);
        assertEquals(2, veterinarios.size());
        assertEquals(16L, veterinarios.get(0).getId());
        assertEquals("Samuel", veterinarios.get(0).getNome());
        assertEquals("araujo@email.com", veterinarios.get(0).getEmail());
        assertEquals("senha", veterinarios.get(0).getSenha());
        assertEquals("1234", veterinarios.get(0).getCpf());
        assertEquals("12/01/2004", veterinarios.get(0).getDataNascimento());
        assertEquals("12345", veterinarios.get(0).getTelefone());

        assertNotNull(veterinario);
        assertEquals(15L, veterinarios.get(1).getId());
        assertEquals("Trikas", veterinarios.get(1).getNome());
        assertEquals("araujo@email.com", veterinarios.get(1).getEmail());
        assertEquals("senha", veterinarios.get(1).getSenha());
        assertEquals("1234", veterinarios.get(1).getCpf());
        assertEquals("12/01/2004", veterinarios.get(1).getDataNascimento());
        assertEquals("12345", veterinarios.get(1).getTelefone());
    }

    @Test
    public void deveriaBuscarPorId(){
        //Dado
        Mockito.when(repository.findById(14L)).thenReturn(Optional.of(criaVeterinario()));

        //Quando
        Veterinario veterinario = service.findById(14L);

        //Entao
        assertNotNull(veterinario);
        assertEquals(16L, veterinario.getId());
        assertEquals("Samuel", veterinario.getNome());
        assertEquals("araujo@email.com", veterinario.getEmail());
        assertEquals("senha", veterinario.getSenha());
        assertEquals("1234", veterinario.getCpf());
        assertEquals("12/01/2004", veterinario.getDataNascimento());
        assertEquals("12345", veterinario.getTelefone());
    }

    @Test()
    public void deveLancarVeterinarioNotFoundExceptionQuandoOVeterinarioNaoForEncontrado() {
        //DADO
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //QUANDO
        assertThrows(VeterinarioNotFoundException.class, () -> service.findById(4L));
    }

    @Test
    public void deveriaDeletarUmVeterinario() {
        Veterinario veterinario = criaVeterinario();
        when(repository.existsById(veterinario.getId())).thenReturn(Boolean.TRUE);

        service.deleteById(veterinario.getId());

        verify(repository).deleteById(veterinario.getId());
    }

    @Test
    public void deveriaAtualizarDadosDoVeterinario(){
        Mockito.when(repository.findById(16L)).thenReturn(Optional.of(criaVeterinario()));

        Veterinario veterinario1 = new Veterinario();
        veterinario1.setNome("samuelson");
        veterinario1.setEmail("samuel@email.com");
        veterinario1.setSenha("senha");
        veterinario1.setCpf("1234");
        veterinario1.setDataNascimento("12/01/2005");
        veterinario1.setTelefone("123456");
        service.atualizaDados(16L, veterinario1);

        verify(repository).save(captor.capture());

        assertEquals("samuelson", captor.getValue().getNome());
        assertEquals("samuel@email.com", captor.getValue().getEmail());
        assertEquals("senha", captor.getValue().getSenha());
        assertEquals("1234", captor.getValue().getCpf());
        assertEquals("12/01/2005", captor.getValue().getDataNascimento());
        assertEquals("123456", captor.getValue().getTelefone());
    }

    private Veterinario criaVeterinario() {
        return new Veterinario(16L, "Samuel", "araujo@email.com",
                "senha", "1234", "12/01/2004", "12345");
    }
}
