package ge.bog.finalproject.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "COMPANY_TO_COUNTRY")
public class CompanyToCountry {

    @EmbeddedId
    private CompanyToCountryKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("COMPANY_ID")
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("COUNTRY_ID")
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @Column(name = "OFFICES_NUMBER")
    private Long officesNumber;

    public CompanyToCountry() {

    }

    public CompanyToCountry(CompanyToCountryKey id, Company company, Country country, Long officesNumber) {
        this.id = id;
        this.company = company;
        this.country = country;
        this.officesNumber = officesNumber;
    }

    public CompanyToCountryKey getId() {
        return id;
    }

    public void setId(CompanyToCountryKey id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getOfficesNumber() {
        return officesNumber;
    }

    public void setOfficesNumber(Long officesNumber) {
        this.officesNumber = officesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyToCountry that = (CompanyToCountry) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
