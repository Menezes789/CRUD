package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, String> {
	Tarefa findByCodigoTarefa(long codigoTarefa);
	Tarefa deleteByCodigoTarefa(long codigoTarefa);
}
