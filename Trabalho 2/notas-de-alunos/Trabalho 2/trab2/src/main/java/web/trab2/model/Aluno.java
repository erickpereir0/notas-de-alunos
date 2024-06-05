package web.trab2.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "turma")
    private int turma;

    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nota")
    private double nota;

    public Aluno() {}

    public Aluno(AlunoDto dto) {
        this.id = (dto.id == null || dto.id.isEmpty()) ? null : Long.parseLong(dto.id);
        this.turma = (dto.turma == null || dto.turma.isEmpty()) ? 0 : Integer.parseInt(dto.turma);
        this.nome = dto.nome;
        this.matricula = dto.matricula;
        this.nota = (dto.nota == null || dto.nota.isEmpty()) ? 0 : Double.parseDouble(dto.nota);
    }

    // Construtor adicional para facilitar a criação de objetos Aluno
    public Aluno(Long id, int turma, String nome, String matricula, double nota) {
        this.id = id;
        this.turma = turma;
        this.nome = nome;
        this.matricula = matricula;
        this.nota = nota;
    }

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", turma=" + turma +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nota=" + nota +
                '}';
    }
}
