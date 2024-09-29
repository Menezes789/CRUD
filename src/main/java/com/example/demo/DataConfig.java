package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfig {

	@Bean
	public DataSource dataSource() {
		
		// instancia objeto de configuração do banco
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		// seta dados de configuração do banco
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/db_tarefa"); // Conecção com o banco
		dataSource.setUsername("root"); // Usuario do banco
		dataSource.setPassword(""); // Senha do banco
		
		// Retorna o objeto
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		
		// Instancia objeto do tipo hibernate
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		
		// Configuração do hibernate
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		adapter.setPrepareConnection(true);
		
		// Retorna o objeto
		return adapter;
	}
}
