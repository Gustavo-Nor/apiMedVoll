package med.voll.api.domain.consulta;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;
    private String motivoCancelamento;

    public Consulta(Object id, Medico medico, Paciente paciente, LocalDateTime data, String motivoCancelamento) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }

    public void Consulta() {}
    public void atualizarInformacoes(DadosCancelamentoConsulta dados) {
        this.motivoCancelamento = dados.motivoCancelamento();
    }

    public void atualizarDataConsulta(DadosAgendamentoConsulta dados) {
        this.data = dados.data();
    }
}
