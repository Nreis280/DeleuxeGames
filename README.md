# DeleuxeGames 🕹️

## Requisitos

- [ ] CRUD Estoque (Entrada e saida de produtos)
- [ ] CRUD Modelos (modelos de videoGames)
- [ ] Listagem de Filiais
- [ ] Autenticação

## Endpoints

### modelos

`GET` /modelo

Lista todos os modelos cadastrados no sistema.

*códigos de status*

200 sucesso

---

`GET` /modelo/{id}

Retorna os detalhes de um modelo com o id informado.

*códigos de status*

200 sucesso
404 id não encontrado

---

`POST` /modelo

Cadastrar um novo modelo.

| campo | tipo | obrigatório | descrição|
| :----------:|:-------:|:------------:|:-------------:
|nome|string|✅|Nome do modelo
|icone|string|❌|O nome do ícone conforme biblioteca do material design

*códigos de status*

201 criado com sucesso
400 validação falhou

---

`DELETE` /modelo/{id}

Apaga o modelo com o id informado.

*códigos de status*

204 apagado com sucesso
404 id não encontrado

---

`PUT` /modelo/{id}

Altera o modelo com o id informado.

| campo | tipo | obrigatório | descrição|
| :----------:|:-------:|:------------:|:-------------:
|nome|string|✅|Novo nome do modelo
|icone|string|✅|Novo nome do ícone conforme biblioteca do material design

*códigos de status*

200 sucesso
404 id não encontrado
400 validação falhou

---

*Schema*

```js
{
  "id": 1,
  "nome": "Xbox",
  "icone": "xbox"
}
```
