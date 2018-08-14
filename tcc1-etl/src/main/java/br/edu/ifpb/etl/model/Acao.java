package br.edu.ifpb.etl.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Acao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int codigoFuncao;
    private String nomeFuncao;
    private int codigoSubFuncao;
    private String nomeSubFuncao;
    private String codigoPrograma;
    private String nomePrograma;
    private int codigoAcao;
    private String nomeAcao;

    public Acao() {
    }

    public Acao(int codigoFuncao, String nomeFuncao, int codigoSubFuncao,
            String nomeSubFuncao, String codigoPrograma, String nomePrograma,
            int codigoAcao, String nomeAcao) {
        this.codigoFuncao = codigoFuncao;
        this.nomeFuncao = nomeFuncao;
        this.codigoSubFuncao = codigoSubFuncao;
        this.nomeSubFuncao = nomeSubFuncao;
        this.codigoPrograma = codigoPrograma;
        this.nomePrograma = nomePrograma;
        this.codigoAcao = codigoAcao;
        this.nomeAcao = nomeAcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoFuncao() {
        return codigoFuncao;
    }

    public void setCodigoFuncao(int codigoFuncao) {
        this.codigoFuncao = codigoFuncao;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public int getCodigoSubFuncao() {
        return codigoSubFuncao;
    }

    public void setCodigoSubFuncao(int codigoSubFuncao) {
        this.codigoSubFuncao = codigoSubFuncao;
    }

    public String getNomeSubFuncao() {
        return nomeSubFuncao;
    }

    public void setNomeSubFuncao(String nomeSubFuncao) {
        this.nomeSubFuncao = nomeSubFuncao;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public int getCodigoAcao() {
        return codigoAcao;
    }

    public void setCodigoAcao(int codigoAcao) {
        this.codigoAcao = codigoAcao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    @Override
    public String toString() {
        return "Acao{" + "id=" + id + ", codigoFuncao=" + codigoFuncao
                + ", nomeFuncao=" + nomeFuncao + ", codigoSubFuncao=" +
                codigoSubFuncao + ", nomeSubFuncao=" + nomeSubFuncao + 
                ", codigoPrograma=" + codigoPrograma + ", nomePrograma="
                + nomePrograma + ", codigoAcao=" + codigoAcao + 
                ", nomeAcao=" + nomeAcao + '}';
    }

}
