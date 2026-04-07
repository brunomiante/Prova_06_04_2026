package com.example.demo.Repository;

public class ProdutoRepository {

    package com.exemplo.produtos.repository;

import com.exemplo.produtos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    }
}
