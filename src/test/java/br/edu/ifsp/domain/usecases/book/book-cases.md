# create book
| classe                | validade | teste | saída                                    |
|-----------------------|----------|-------|------------------------------------------|
| livro com infos novas | válido   |       | livro criado                             |
| livro repetido        | inválido |       | criado na primeira vez e erro na segunda |
| isnb já cadastrado    | inválido |       | livro não criado                         |

# find one by id
| classe            | validade | teste | saída                |
|-------------------|----------|-------|----------------------|
| id existe         | válido   |       | livro recuperado     |
| id não existe     | válido   |       | livro não recuperado |
| id null           | inválido |       | livro não recuperado |

# find one by isbn
| classe          | validade | teste | saída                |
|-----------------|----------|-------|----------------------|
| isbn existe     | válido   |       | livro recuperado     |
| isbn não existe | inválido |       | livro não recuperado |
| isbn null       | inválido |       | livro não recuperado |
| isbn vazio      | inválido |       | livro não recuperado |

# find all
| classe                         | validade | teste | saída               |
|--------------------------------|----------|-------|---------------------|
| livros cadastrados recuperados | válido   |       | lista com os livros |
| não há livros cadastrados      | válido   |       | lista vazia         |

# remove by id
| classe        | validade | teste | saída              |
|---------------|----------|-------|--------------------|
| id existe     | válido   |       | livro removido     |
| id não existe | válido   |       | livro não removido |
| id null       | inválido |       | livro não removido |

# remove by book
| classe           | validade | teste | saída              |
|------------------|----------|-------|--------------------|
| livro existe     | válido   |       | livro removido     |
| livro não existe | válido   |       | livro não removido |
| livro null       | inválido |       | livro não removido |
