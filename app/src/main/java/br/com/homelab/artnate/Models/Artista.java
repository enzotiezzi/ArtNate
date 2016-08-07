package br.com.homelab.artnate.Models;

/**
 * Created by enzo on 07/08/2016.
 */
public class Artista
{
    private int IdInterno;
    private String IdExterno;
    private String Nome;
    private String Genero;
    private String NomeArtistico;
    private String Ocupacao;

    private String Artes;


    @Override
    public String toString()
    {
        return NomeArtistico;
    }

    public int getIdInterno()
    {
        return IdInterno;
    }

    public void setIdInterno(int idInterno)
    {
        IdInterno = idInterno;
    }

    public String getIdExterno()
    {
        return IdExterno;
    }

    public void setIdExterno(String idExterno)
    {
        IdExterno = idExterno;
    }

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String nome)
    {
        Nome = nome;
    }

    public String getGenero()
    {
        return Genero;
    }

    public void setGenero(String genero)
    {
        Genero = genero;
    }

    public String getNomeArtistico()
    {
        return NomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico)
    {
        NomeArtistico = nomeArtistico;
    }

    public String getOcupacao()
    {
        return Ocupacao;
    }

    public void setOcupacao(String ocupacao)
    {
        Ocupacao = ocupacao;
    }

    public String getArtes()
    {
        return Artes;
    }

    public void setArtes(String artes)
    {
        Artes = artes;
    }
}
