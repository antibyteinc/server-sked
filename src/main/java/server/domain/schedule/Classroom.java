package server.domain.schedule;

import server.domain.institution.Institution;

import javax.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String building;
    private String cabinet;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_institution")
    private Institution institution;

    public Classroom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
