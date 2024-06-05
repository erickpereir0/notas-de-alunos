package web.trab2.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import web.trab2.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    
    public void deleteById(Long alunoId);

    public void deleteAll();

    public int countByTurma(int int1);

    public Optional<Aluno> findById(Long long1);

    public long count();

} 
