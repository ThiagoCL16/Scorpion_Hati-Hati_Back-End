package com.scorpionTCC.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contratante")
@PrimaryKeyJoinColumn(name = "id_contratante")
public class Contratante extends Usuario{
    /* TODO criar tabela de endereço (CEP, coordenadas geográficas, número, complemento), fazer
        ela ter relacionamento com contratante e tentar integrar com Maps */
}
