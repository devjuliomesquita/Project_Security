
Project Security
===



## Guia para iniciantes

Este tutorial é guia para uma implementação simples porém eficaz de uma autenticação e autorização de um usuário via JWT (Json Web Token). 


Fluxo na aplicação
---
1. Um Request é enviado para a API. Porém antes de chegar ao nosso endpoint situado em nossa controller é necessário que ele passe por um filtro.
2. Auth. Filter JWT - este é o nosso filtro que bloqueia qualquer request.
2.1. Primeiramente ele testa para saber se no request veio o JWT. 
     - Caso contrário ele devolve informando o Status 403 - Forbiden com a msg: Missing Token.
     - Caso sejá um tokenJWT ele verifica por meio do nosso UserDetailsService se este usuário que veio do Token existe em nosso Db.
     - Caso exista, vamos para o próximo passo do contrário devolve-se informando o Status 403 - Forbiden com a msg: User not exist.
3. Validar o nosso JWT. Assim chamamos um JwtService para isso. Caso o mesmo não tenha nossa assinatura devolve-se informando o Status 403 - Forbiden com a msg: Jwt Invalid.
4. Security Context Holder
5. Dispatcher Servlet
6. Controller



###### tags: `Security` `Documentation`, `Java`, `Spring`
