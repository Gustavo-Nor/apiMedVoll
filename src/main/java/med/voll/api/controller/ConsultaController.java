package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeContultas agenda;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConsulta>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findAllByMotivoCancelamentoIsNull(paginacao).map(DadosDetalhamentoConsulta::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity excluir(@RequestBody @Valid DadosCancelamentoConsulta dados){
        var dto = agenda.cancelar(dados);

        return ResponseEntity.noContent().build();
    }
}
