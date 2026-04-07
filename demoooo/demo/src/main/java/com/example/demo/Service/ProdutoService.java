package com.example.demo.Service;

public class ProdutoService {

package com.exemplo.produtos.service;

import com.exemplo.produtos.entity.Produto;
import com.exemplo.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class ProdutoService {

        private final ProdutoRepository repository;

        public ProdutoService(ProdutoRepository repository) {
            this.repository = repository;
        }

        public Produto salvar(Produto produto) {
            return repository.save(produto);
        }

        public List<Produto> listarTodos() {
            return repository.findAll();
        }

        public Optional<Produto> buscarPorId(Long id) {
            return repository.findById(id);
        }

        public Produto atualizar(Long id, Produto novo) {
            return repository.findById(id).map(p -> {
                p.setNome(novo.getNome());
                p.setDescricao(novo.getDescricao());
                p.setPreco(novo.getPreco());
                p.setStatus(novo.getStatus());
                return repository.save(p);
            }).orElse(null);
        }

        public boolean deletar(Long id) {
            return repository.findById(id).map(p -> {
                repository.delete(p);
                return true;
            }).orElse(false);
        }
    }
}