package web.trab2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import web.trab2.model.Aluno;
import web.trab2.model.AlunoDto;
import web.trab2.repository.AlunoRepository;

@Controller
public class RouterController {

    @Autowired
    AlunoRepository repository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/novo")
    public String novoAluno() {
        return "novo";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editNotas(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("edit");
        // Obtendo o objeto Aluno do banco de dados a partir do id informado
        Long alunoId = Long.parseLong(id);
        Optional<Aluno> alunoOptional = repository.findById(alunoId);
        
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            // Convertendo o objeto Aluno para AlunoDto
            AlunoDto dto = new AlunoDto();
            dto.setId(aluno.getId());
            dto.setNome(aluno.getNome());
            dto.setTurma(aluno.getTurma());
            // Injetando o dto no ModelAndView
            mv.addObject("aluno", dto);
        } else {
            // Se o aluno não for encontrado, pode-se adicionar uma mensagem de erro
            mv.addObject("error", "Aluno não encontrado com o id: " + id);
        }
        
        return mv;
    }
}
