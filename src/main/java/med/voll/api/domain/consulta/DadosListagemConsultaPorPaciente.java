package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

import java.time.LocalDateTime;

public record DadosListagemConsultaPorPaciente<medico>(

    Long id,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime data,
    DadosListagemMedico medico
    ) {
    public DadosListagemConsultaPorPaciente(Consulta consulta) {
            this(consulta.getId(), consulta.getData(), new DadosListagemMedico(consulta.getMedico()));
        }
}

