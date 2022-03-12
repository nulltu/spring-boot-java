package ar.com.vosmi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
//    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName = "";

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName = "";

    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    private String langKey;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name = "tipo_documento", nullable = false)
//    private TipoDocumento tipoDocumento;

    @NotNull
    @Column(name = "document_number", nullable = false)
    private Long documentNumber;

//    @ManyToOne
//    @JsonBackReference
//    private Cliente cliente;

    @Column(name = "failed_attempts")
    private Long failedAttempts;

    @Column(nullable = false)
    private boolean locked = false;

    @Column(nullable = false)
    private boolean lockedSec = false;

    @Column(nullable = false)
    private boolean resetRequired;

    @Size(max = 255)
    @Column(name = "last_generated_token", length = 255)
    private String lastGeneratedToken;

    @Column(name = "low_logic")
    private boolean lowLogic = false;

    @Transient
    private String fullName;

    @Transient
    private boolean expiredPassword;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "vigencia_date")
    private Instant vigenciaDate = null;

    @Column(name = "last_login_date")
    private Instant lastLoginDate = null;

    @JsonIgnore
    @Column(name = "current_roles")
    private String currentRoles;
}
