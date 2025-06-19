package med.voll.api.domain.pacientes;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table (name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;


    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(@Valid DadosCadastroPaciente dadosPacientes) {
        this.ativo = true;
        this.nome = dadosPacientes.nome();
        this.email = dadosPacientes.email();
        this.cpf = dadosPacientes.cpf();
        this.telefone = dadosPacientes.telefone();
        this.endereco = new Endereco(dadosPacientes.endereco());
    }

    public void atualizarInformacoesPaciente(@Valid DadosAtualizacaoPaciente dadosPacientes) {
        if(dadosPacientes.nome() != null) {
            this.nome = dadosPacientes.nome();
        }
        if(dadosPacientes.telefone() != null) {
            this.telefone = dadosPacientes.telefone();
        }
        if(dadosPacientes.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosPacientes.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
