package br.com.homelab.artnate.Models;

/**
 * Created by enzo on 07/08/2016.
 */
public class EfetuarDoacaoInput
{
    private int IdContribuidor;
    private int IdArtista;
    private String Valor;


    public int getIdContribuidor()
    {
        return IdContribuidor;
    }

    public void setIdContribuidor(int idContribuidor)
    {
        IdContribuidor = idContribuidor;
    }

    public int getIdArtista()
    {
        return IdArtista;
    }

    public void setIdArtista(int idArtista)
    {
        IdArtista = idArtista;
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
