package models;

import com.avaje.ebean.*;
import com.avaje.ebean.Model;
import com.avaje.ebean.Query;

import javax.persistence.*;
import java.util.List;

/**
 * Created by carlos on 31/07/16.
 */
@Entity
@Table(name="usuario")
public class Usuario extends Model {

    @Id
    @Column(name="id_lcto")
    private Long id;

    @Column(name="username")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="passwd")
    private String password;

    @Column(name="fg_ativo")
    private String ativo;

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public List<Usuario> findAllUsuario() {
        String sql = "find usuario";
        Query<Usuario> q = Ebean.createQuery(Usuario.class, sql);

        List<Usuario> list = q.findList();

        return list;
    }

    public void insertUsuario() {
        String sql = "insert into usuario(username, email, passwd, fg_ativo)"
                    +"values (:username, :email, :passwd, :fg_ativo)";
        SqlUpdate q = Ebean.createSqlUpdate(sql);
        q.setParameter("username", getUserName());
        q.setParameter("email", getEmail());
        q.setParameter("passwd", getPassword());
        q.setParameter("fg_ativo", getAtivo());
        Ebean.execute(q);
    }

    public void updateUsuario(Long id) {
        String sql = "update usuario set username = :username, email = :email, passwd = :passwd, fg_ativo = :fg_ativo"
                    +"where id_lcto = :id_lcto";
        SqlUpdate q = Ebean.createSqlUpdate(sql);
        q.setParameter("username", getUserName());
        q.setParameter("email", getEmail());
        q.setParameter("passwd", getPassword());
        q.setParameter("fg_ativo", getAtivo());
        q.setParameter("id_lcto", getId());
        Ebean.execute(q);
    }

    public void deleteUsuario(Long id) {
        String sql = "delete from usuario where id_lcto = :id_lcto";
        SqlUpdate q = Ebean.createSqlUpdate(sql);
        q.setParameter("id_lcto", id);
        Ebean.execute(q);
    }
}
