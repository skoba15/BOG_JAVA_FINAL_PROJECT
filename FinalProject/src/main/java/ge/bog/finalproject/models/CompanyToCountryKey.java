package ge.bog.finalproject.models;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Embeddable
public class CompanyToCountryKey implements Serializable {

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "COUNTRY_ID")
    private Long countryId;

    public CompanyToCountryKey() {

    }

    public CompanyToCountryKey(Long companyId, Long countryId) {
        this.companyId = companyId;
        this.countryId = countryId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getCountryId() {
        return countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyToCountryKey that = (CompanyToCountryKey) o;
        return companyId.equals(that.companyId) &&
                countryId.equals(that.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, countryId);
    }
}
