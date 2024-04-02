package med.voll.api.domain.medico;

import java.util.List;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String imageURL,
        Especialidade especialidade) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getImageURL(), medico.getEspecialidade());
    }
}
