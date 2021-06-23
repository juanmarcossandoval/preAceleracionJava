package alkemy.challenge.Challenge.Alkemy.model;

import lombok.NonNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1369488366898057585L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @Column(nullable = false)
    private String email;


    @NonNull
    @Column(nullable = false)
    private Integer age;

    @NonNull
    @Column(nullable = false)
    private String password ;

    private String photo;

    private Boolean deleted;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date create_at_register;

    public User() {
    }

    public User(Long id, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull Integer age,
                @NonNull String password, String photo, Boolean deleted, Date create_at_register) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.photo = photo;
        this.deleted = deleted;
        this.create_at_register = create_at_register;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", deleted=" + deleted +
                ", create_at_register=" + create_at_register +
                '}';
    }
}
