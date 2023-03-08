# Digimon Back-end

Projeto desenvolvido utilizando Spring Boot e banco de dados Postgres

Para a primeira execução deve-se fazer uma requisição para o endpoint '/digimon/populate' para popular o banco de dados com os dados da API do Digimon

Endpoints disponíveis:

GET '/digimon': Recupera uma lista com todos os Digimons

POST '/digimon/search': Faz uma busca por nome e/ou por level do Digimon

GET '/digimon/level/:level': Recupera uma lista de Digimons de acordo com o level fornecido