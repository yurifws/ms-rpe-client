package br.com.app.client.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.app.client.enuns.ClientStatusEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class ClientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long id;
	
	@Column(name = "client_name")
	private String name;
	
	@Column(name = "client_document")
	private String document;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "client_status")
	private ClientStatusEnum status;
	
	@Column(name = "client_date_of_birth")
	private LocalDate dateOfBirth;

	@CreationTimestamp
	@Column(name = "client_date_created")
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	@Column(name = "client_date_updated")
	private LocalDateTime dateUpdated;
}
