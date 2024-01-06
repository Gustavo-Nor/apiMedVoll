package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamenteDeConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        var dataConulta = dados.data();
        var domingo = dataConulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaClinica = dataConulta.getHour() < 7;
        var depoisDoEncerramentoClinica = dataConulta.getHour() > 18;

        if(domingo || antesDaAberturaClinica || depoisDoEncerramentoClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
