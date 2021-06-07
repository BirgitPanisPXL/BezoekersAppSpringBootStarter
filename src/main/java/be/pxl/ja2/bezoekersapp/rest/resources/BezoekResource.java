package be.pxl.ja2.bezoekersapp.rest.resources;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BezoekResource {
    @NotEmpty
    private Long id;
    @NotNull
    private LocalDateTime aanmelding;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAanmelding() {
        return aanmelding;
    }

    public void setAanmelding(LocalDateTime aanmelding) {
        this.aanmelding = aanmelding;
    }
}
