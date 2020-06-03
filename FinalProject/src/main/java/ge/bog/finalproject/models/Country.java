package ge.bog.finalproject.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;


@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", unique = true)
    @NotNull
    private String name;


    @Column(name = "CODE", unique = true)
    @NotNull
    private String code;

//    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
//    private Set<CompanyToCountry> usersList = new HashSet<CompanyToCountry>();


    public Country(@NotNull String name, @NotNull String code) {
        this.name = name;
        this.code = code;
    }

    public Country() {

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
