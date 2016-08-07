package br.com.homelab.artnate.Models;

/**
 * Created by enzo on 07/08/2016.
 */
public class Doacao
{
    private int Id;
    private Contribuidor Contribuidor;
    private Artista Artista;
    private String Data;
    private String Valor;


    @Override
    public String toString()
    {
        return Artista + " - " + "R$" + Valor;
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public br.com.homelab.artnate.Models.Contribuidor getContribuidor()
    {
        return Contribuidor;
    }

    public void setContribuidor(br.com.homelab.artnate.Models.Contribuidor contribuidor)
    {
        Contribuidor = contribuidor;
    }

    public br.com.homelab.artnate.Models.Artista getArtista()
    {
        return Artista;
    }

    public void setArtista(br.com.homelab.artnate.Models.Artista artista)
    {
        Artista = artista;
    }

    public String getData()
    {
        return Data;
    }

    public void setData(String data)
    {
        Data = data;
    }

    public String getValor()
    {
        return Valor;
    }

    public void setValor(String valor)
    {
        Valor = valor;
    }
}
