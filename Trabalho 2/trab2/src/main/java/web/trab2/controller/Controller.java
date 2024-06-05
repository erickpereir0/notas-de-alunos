package web.trab2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.trab2.config.Utils;
import web.trab2.model.Aluno;
import web.trab2.model.AlunoDto;
import web.trab2.repository.AlunoRepository;

@RestController
public class Controller {

    @Autowired
    private AlunoRepository repository;


    @GetMapping("/getAll")
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = (List<Aluno>) repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @PostMapping("/updateAluno")
    public ResponseEntity<Object> updateAluno(@RequestBody AlunoDto dto) {
        // Atualizando os dados de um aluno
        Optional<Aluno> alunoOptional = repository.findById(dto.getId());
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome(dto.getNome());
            aluno.setTurma(dto.getTurma());
            repository.save(aluno);
            return ResponseEntity.status(HttpStatus.OK).body(aluno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há aluno com este id");
        }
    }

    @PostMapping("/novoAluno")
    public ResponseEntity<Object> novoAluno(@RequestBody AlunoDto dto) {
        int teste = Integer.parseInt(dto.turma);
        long numeroDeAlunos = repository.countByTurma(teste);
        if (numeroDeAlunos >= 10) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Dados em excesso");
        } else {
            Aluno novoAluno = new Aluno();
            novoAluno.setNome(dto.getNome());
            novoAluno.setTurma(dto.getTurma());
            novoAluno.setNota(Double.parseDouble(dto.nota));
            novoAluno.setMatricula(dto.matricula);
            repository.save(novoAluno);
            return ResponseEntity.status(HttpStatus.OK).body(novoAluno);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteAluno(@RequestBody String id) {
        Long alunoId = Long.parseLong(id);
        this.repository.deleteById(alunoId);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/reset")
    public ResponseEntity<Object> reset() {
        this.repository.deleteAll();
        Utils.startDb(this.repository);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/zerar")
    public ResponseEntity<Object> zerar() {
        this.repository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}