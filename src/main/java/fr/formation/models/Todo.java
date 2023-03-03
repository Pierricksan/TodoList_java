package fr.formation.models;

import fr.formation.utils.Urgence;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="todo")
public class Todo {
    private int id;
    private Urgence urgence;
    private String titre;
    private String description;
    /*public Todo() {}*/
    /*public Todo(Urgence urgence, String titre, String description) {
        this.urgence = urgence;
        this.titre = titre;
        this.description = description;
    }*/

    public Todo(){};
    public Todo(String urgence, String titre, String description) {
        this.urgence = Urgence.valueOf(urgence);
        this.titre = titre;
        this.description = description;
    }
    @XmlElement
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public Urgence getUrgence() {
        return urgence;
    }
    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }
    @XmlElement
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    @XmlElement
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
