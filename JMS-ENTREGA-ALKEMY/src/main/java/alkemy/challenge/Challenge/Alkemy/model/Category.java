package alkemy.challenge.Challenge.Alkemy.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "categories")
public class Category implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6156438697723757055L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//si este atributo esta vacio no deberia poder insertarse en la BBDD
	@NotEmpty
    private String name;
	
    private String description;
    private String images;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;
    
    //Borrado logico
    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToMany(mappedBy = "bookcategory", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Book> books;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", images='" + images + '\'' +
                ", regdate=" + regdate +
                ", updatedate=" + updatedate +
                ", deleted=" + deleted +
                ", books=" + books +
                '}';
    }
}
