package modele;

import java.io.Serializable;
import java.util.HashMap;

public class Boutique implements Serializable {


    private int id;
    private String nom;
    private String adresse;
    private HashMap<Integer, Integer> livreStock = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    private String departement;

    public HashMap<Integer, Integer> getLivreStock() {
        return livreStock;
    }

    public void setLivreStock(HashMap<Integer, Integer> livreStock) {
        this.livreStock = livreStock;
    }

    public Boutique(int id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
    }


    public Boutique(int id, String nom) {
        this.nom = nom;
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Boutique{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", departement='" + departement + '\'' +
                ", livreStock=" + livreStock +
                '}';
    }
}
