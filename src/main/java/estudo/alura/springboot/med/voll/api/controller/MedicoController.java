package estudo.alura.springboot.med.voll.api.controller;

import estudo.alura.springboot.med.voll.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private iMedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        var medico = this.repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return repository
                .findAllByAtivoTrue(pageable)
                .map(DadosListagemMedico::new);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = this.repository.getReferenceById(id);
        medico.excluir();
    }

}
