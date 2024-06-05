package web.trab2.model;

public class AlunoDto {

    public String id;
    
    @NotEmpty(message = "Turma não pode ser vazia")
    public String turma;
    
    @NotEmpty(message = "Nome não pode ser vazio")
    public String nome;
    
    @NotEmpty(message = "Matrícula não pode ser vazia")
    public String matricula;
    
    @NotEmpty(message = "Nota não pode ser vazia")
    public String nota;

    public AlunoDto() {}

    public AlunoDto(Aluno a) {
        this.id = String.valueOf(a.getId());
        this.turma = String.valueOf(a.getTurma());
        this.nome = a.getNome();
        this.matricula = a.getMatricula();
        this.nota = String.valueOf(a.getNota());
    }

    public static AlunoDto fromAluno(Aluno aluno) {
        return new AlunoDto(aluno);
    }

    public static Aluno toAluno(AlunoDto dto) {
        Aluno aluno = new Aluno();
        aluno.setId(dto.id == null || dto.id.isEmpty() ? null : Long.parseLong(dto.id));
        aluno.setTurma(dto.turma == null || dto.turma.isEmpty() ? 0 : Integer.parseInt(dto.turma));
        aluno.setNome(dto.nome);
        aluno.setMatricula(dto.matricula);
        aluno.setNota(dto.nota == null || dto.nota.isEmpty() ? 0 : Double.parseDouble(dto.nota));
        return aluno;
    }

    @Override
    public String toString() {
        return "AlunoDto{" +
                "id='" + id + '\'' +
                ", turma='" + turma + '\'' +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }

    public void setId(Long id2) {
        this.id = String.valueOf(id2);
    }

    public void setNome(String nome2) {
        this.nome = nome2;
    }

    public void setTurma(int turma2) {
        this.turma = String.valueOf(turma2);
    }

    public String getNome() {
        return nome;
    }

    public int getTurma() {
        return Integer.parseInt(turma);
    }

    public Long getId() {
        return Long.parseLong(id);
    }
}
