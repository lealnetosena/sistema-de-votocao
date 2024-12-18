package br.com.voto.application.usecase;
import br.com.voto.application.dtos.SessaoDTO;
import br.com.voto.domain.model.Pauta;
import br.com.voto.domain.model.Sessao;
import br.com.voto.infra.repository.PautaRepository;
import br.com.voto.infra.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoService sessaoService;

    private Sessao sessao;
    private Pauta pauta;
    private SessaoDTO sessaoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pauta = new Pauta(1L);
        pauta.setTitle("Pauta 1");
        pauta.setCompleted(false);

        sessao = new Sessao();
        sessao.setId(1L);
        sessao.setPauta(pauta);
        sessao.setInicio(LocalDateTime.now());
        sessao.setFim(sessao.getInicio().plusMinutes(30));

        sessaoDTO = new SessaoDTO();
        sessaoDTO.setPautaId(1L);
        sessaoDTO.setTempoMinutos(30L);
    }

    @Test
    void testCreateSessao() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

        Sessao createdSessao = sessaoService.createSessao(sessaoDTO);

        assertNotNull(createdSessao);
        assertEquals(pauta, createdSessao.getPauta());
        assertEquals(sessaoDTO.getTempoMinutos(), createdSessao.getFim().getMinute() - createdSessao.getInicio().getMinute());
    }

    @Test
    void testCreateSessaoPautaNotFound() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            sessaoService.createSessao(sessaoDTO);
        });

        assertEquals("Pauta não encontrada com ID: 1", thrown.getMessage());
    }

    @Test
    void testUpdateSessao() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

        sessaoDTO.setTempoMinutos(45L); // Atualizando o tempo

        Sessao updatedSessao = sessaoService.updateSessao(1L, sessaoDTO);

        assertNotNull(updatedSessao);
        assertEquals(45, updatedSessao.getFim().getMinute() - updatedSessao.getInicio().getMinute());
    }

    @Test
    void testUpdateSessaoNotFound() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            sessaoService.updateSessao(1L, sessaoDTO);
        });

        assertEquals("Sessão não encontrada", thrown.getMessage());
    }

    @Test
    void testDeleteSessao() {
        doNothing().when(sessaoRepository).deleteById(1L);

        sessaoService.deleteSessao(1L);

        verify(sessaoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetSessaoById() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));

        Optional<Sessao> foundSessao = sessaoService.getSessaoById(1L);

        assertTrue(foundSessao.isPresent());
        assertEquals(sessao, foundSessao.get());
    }

    @Test
    void testGetSessaoByIdNotFound() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Sessao> foundSessao = sessaoService.getSessaoById(1L);

        assertFalse(foundSessao.isPresent());
    }
}
