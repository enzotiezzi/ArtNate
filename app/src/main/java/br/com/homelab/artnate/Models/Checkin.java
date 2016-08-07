package br.com.homelab.artnate.Models;

/**
 * Created by enzo on 07/08/2016.
 */
public class Checkin
{
    private int Id;
    private int IdInternoArtista;
    private String Latitude;
    private String Longitude;
    private String Data;
    private String Endereco;
    private boolean Ativo;

    public Checkin()
    {
        setAtivo(true);
    }


    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public int getIdInternoArtista()
    {
        return IdInternoArtista;
    }

    public void setIdInternoArtista(int idInternoArtista)
    {
        IdInternoArtista = idInternoArtista;
    }

    public String getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(String latitude)
    {
        Latitude = latitude;
    }

    public String getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(String longitude)
    {
        Longitude = longitude;
    }

    public String getData()
    {
        return Data;
    }

    public void setData(String data)
    {
        Data = data;
    }

    public String getEndereco()
    {
        return Endereco;
    }

    public void setEndereco(String endereco)
    {
        Endereco = endereco;
    }

    public boolean isAtivo()
    {
        return Ativo;
    }

    public void setAtivo(boolean ativo)
    {
        Ativo = ativo;
    }

    @Override
    public String toString()
    {
        return Endereco;
    }
}
