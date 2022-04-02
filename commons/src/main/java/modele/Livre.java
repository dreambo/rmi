package modele;


//import java.io.Serial;
import java.io.Serializable;


//Liste des livres
public class Livre implements Serializable {


    private static final long serialVersionUID = 1190476516911661480L;
    private int id;
    private String titre;
    private double prix;
    private int quantite;
    private String description;

    public Livre(int id, String titre, double prix, int quantite, String description){
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
    }

    public Livre(String titre, double prix, int quantite, String description){
        this.titre = titre;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
    }



    public Livre() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}
