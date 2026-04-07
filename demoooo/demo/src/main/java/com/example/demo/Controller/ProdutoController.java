package com.example.demo.Controller;

public class ProdutoController {

    package com.exemplo.produtos.controller;

import com.exemplo.produtos.entity.Produto;
import com.exemplo.produtos.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

    @RestController
    @RequestMapping("/api/produtos")
    public class ProdutoController {

        private final ProdutoService service;

        public ProdutoController(ProdutoService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
            Produto novo = service.salvar(produto);
            return ResponseEntity
                    .created(URI.create("/api/produtos/" + novo.getId()))
                    .body(novo);
        }

        @GetMapping
        public ResponseEntity<List<Produto>> listar() {
            return ResponseEntity.ok(service.listarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Produto> buscar(@PathVariable Long id) {
            return service.buscarPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
            Produto atualizado = service.atualizar(id, produto);
            if (atualizado == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(atualizado);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            if (!service.deletar(id)) return ResponseEntity.notFound().build();
            return ResponseEntity.noContent().build();
        }
    }
}
