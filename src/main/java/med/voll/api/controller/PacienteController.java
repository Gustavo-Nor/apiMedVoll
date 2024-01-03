package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosDetalhamentoPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

//    @GetMapping
//    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
//        var page = repository.findAllByActiveTrue(paginacao).map(DadosListagemMedico::new);
//
//        return ResponseEntity.ok(page);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity detalhar(@PathVariable Long id){
//        var medico = repository.getReferenceById(id);
//
//        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
//    }
//
//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
//        var medico = repository.getReferenceById(dados.id());
//        medico.atualizarInformacoes(dados);
//
//        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity excluir(@PathVariable Long id){
//        var medico = repository.getReferenceById(id);
//        medico.excluir();
//
//        return ResponseEntity.noContent().build();
//    }
}
