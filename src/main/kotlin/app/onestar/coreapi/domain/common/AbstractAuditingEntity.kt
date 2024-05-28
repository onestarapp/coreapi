package app.onestar.coreapi.domain.common

import app.onestar.coreapi.security.getCurrentUser
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
@JsonIgnoreProperties(value = [ "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" ], allowGetters = true)
abstract class AbstractAuditingEntity(
    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 64, updatable = false)
    open var createdBy: String? = getCurrentUser(),
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    open var createdDate: Instant? = Instant.now(),
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 64)
    open var lastModifiedBy: String? = getCurrentUser(),
    @LastModifiedDate
    @Column(name = "last_modified_date")
    open var lastModifiedDate: Instant? = Instant.now(),
) : Serializable {
    abstract val id: String?
}
