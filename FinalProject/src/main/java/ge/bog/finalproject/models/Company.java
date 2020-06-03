package ge.bog.finalproject.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "COMPANIES")
public class Company {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", unique = true)
    @NotNull
    private String name;

    @Column(name = "TOTAL_EMPLOYEES")
    @NotNull
    private Long totalEmployees;

//    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
//    private Set<CompanyToCountry> neighborhoodsList = new HashSet<CompanyToCountry>();

    public Company(@NotNull String name, @NotNull Long totalEmployees) {
        this.name = name;
        this.totalEmployees = totalEmployees;
    }

    public Company() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}
