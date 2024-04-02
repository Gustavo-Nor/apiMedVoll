package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoAntecedenciaMinima implements ValidadorCancelamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validarCancelamento(Long id, DadosCancelamentoConsulta dados){
        var consulta = repository.findById(id).get();
        var dataConsulta = consulta.getData();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toHours();

        if(diferencaEmMinutos < 24){
            throw new ValidacaoException("Consulta só pode ser cancelada com antecedencia mínima de 24 horas!");
        }
    }
}
