package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;

import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("consultas")
//@SecurityRequirement(name = "bearer-key")
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

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity reagendar(@PathVariable Long id, @RequestBody @Valid DadosAgendamentoConsulta dados){

        var consulta = repository.getReferenceById(id);
        agenda.reagendar(consulta, dados);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConsulta>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findAllByMotivoCancelamentoIsNull(paginacao).map(DadosDetalhamentoConsulta::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/paciente/{paciente_id}")
    public ResponseEntity consultasPaciente(@PathVariable Long paciente_id){
        var consultasPaciente = repository.findAllByPacienteId(paciente_id).stream().map(DadosListagemConsultaPorPaciente::new).toList();

        return ResponseEntity.ok(consultasPaciente);


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, @RequestBody @Valid DadosCancelamentoConsulta dados){
        var dto = agenda.cancelar(id, dados);

        return ResponseEntity.noContent().build();
    }
}
