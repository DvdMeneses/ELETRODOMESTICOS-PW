package com.eletros.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Eletros {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;


@NotBlank
String tipo;// se é geladeira , microondas,fogão e afins


    LocalDateTime deletado;
    String imageUri;


    @NotNull
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    Integer preco;


    @NotBlank
    @Size(max = 15,min= 2)
    String marca;

    @Size(max =40 , min = 10)
    @NotNull
    String descricao;




    public void nomesMaiusculo(){
        this.marca = this.marca.toUpperCase();
        this.tipo = this.tipo.toUpperCase();
        this.descricao = this.descricao.toUpperCase();
    }



    public void deletar(){
        System.out.println(this.deletado);
        this.deletado = LocalDateTime.now();
        System.out.println(this.deletado);
    }



}
