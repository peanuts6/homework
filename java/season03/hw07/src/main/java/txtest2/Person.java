package txtest2;

import java.io.Serializable;

/**
 * Created by xqy on 17/9/25.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String FirstName;
    private String LastName;
    private String city;
    private String country;

    public Person(String firstName, String lastName, String city, String country) {
        super();
        FirstName = firstName;
        LastName = lastName;
        this.city = city;
        this.country = country;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
