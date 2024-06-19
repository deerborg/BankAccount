package art.deerborg.bank.common.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name = "CREATED_CUSTOMER")
    @CreatedBy
    private String createdCustomer;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private LocalDateTime createdDateTime;

    @PrePersist
    public void prePersist(){
        this.createdCustomer = "deer";
        this.createdDateTime = LocalDateTime.now();
    }


    @Column(name = "UPDATED_CUSTOMER")
    @LastModifiedBy
    private String updatedCustomer;

    @Column(name = "UPDATED_AT")
    @LastModifiedDate
    private LocalDateTime updatedDateTime;

    @PreUpdate
    public void preUpdate(){
        this.updatedCustomer = "deer";
        this.updatedDateTime = LocalDateTime.now();
    }
}
