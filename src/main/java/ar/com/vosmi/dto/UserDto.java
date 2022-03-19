package ar.com.vosmi.dto;

import ar.com.vosmi.config.Constants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
public class UserDto implements Serializable {
    private Long id;

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(min = 5, max = 100)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

//    private TipoDocumento tipoDocumento;

    private Long documentNumber;

//    private Cliente cliente;

    private Boolean locked = false;

    private Boolean resetRequired = false;

    private Boolean expiredPassword = false;

    private String commentary;

    private Instant vigenciaDate = null;


    private Instant lastLoginDate;

    private Boolean lockedSec = false;
}
