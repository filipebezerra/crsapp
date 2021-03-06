package br.com.libertsolutions.crs.app.domain.entity;

import android.support.annotation.IntRange;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static br.com.libertsolutions.crs.app.domain.pojo.Work.STATUS_PENDING;
import static br.com.libertsolutions.crs.app.domain.pojo.Work.STATUS_STARTED;

/**
 * Entidade Obra, representa os projetos de construção ou reforma dos interiores de um imóvel.
 * Esta classe é o modelo de persistência local.
 *
 * @author Filipe Bezerra
 * @since 0.1.0
 */
public class WorkEntity extends RealmObject {
    @PrimaryKey
    private Long workId;

    private ClientEntity client;

    private String code;

    private String date;

    private String job;

    private Integer status;

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(@IntRange(from = STATUS_PENDING, to = STATUS_STARTED) Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof WorkEntity) {
            final WorkEntity anotherWork = (WorkEntity) o;
            return getWorkId().compareTo(anotherWork.getWorkId()) == 0;
        }
        return false;
    }
}
