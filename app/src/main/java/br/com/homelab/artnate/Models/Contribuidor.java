package br.com.homelab.artnate.Models;

/**
 * Created by enzo on 07/08/2016.
 */
public class Contribuidor
{
    private int Id;
    private String Nome;
    private String Genero;


    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
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
}
