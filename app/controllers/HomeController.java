package controllers;

import com.avaje.ebean.SqlRow;
import models.Usuario;
import play.mvc.*;
import java.util.List;
import views.html.*;
import com.google.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import play.Logger;
import static controllers.Utils.md5;

public class HomeController extends Controller {

    @Inject FormFactory formFactory;

    @Security.Authenticated(Secured.class)
    public Result index() {

        return ok(index.render("Your new application is teste."));
    }

    public Result usuario() {

        Usuario usuario = new Usuario();
        List<Usuario> listUsuarios = usuario.findAllUsuario();

        return ok(usuariolist.render(listUsuarios));
    }

    public Result delUsuario() {
        Form<Usuario> form = formFactory.form(Usuario.class).bindFromRequest();

        Usuario user = form.get();

        user.deleteUsuario(user.getId());

        return redirect(routes.HomeController.usuario());

    }

    public Result novoEditUsuario(String mode, Long id) {

        Usuario user = new Usuario();

        List<Usuario> userList = user.findUsuarioById(id);

        if (mode.equals("edit")) {

            return ok(usuario.render(mode, id, userList.get(0).getUserName(), userList.get(0).getEmail(), userList.get(0).getPassword(), userList.get(0).getAtivo()));

        } else {

            return ok(usuario.render(mode, id, "", "", "", ""));

        }

    }

    public Result gravaUsuario() {
        Form<Usuario> form = formFactory.form(Usuario.class).bindFromRequest();

        Usuario user = form.get();

        user.insertUsuario();

        flash("success", "Usuário cadastrado com sucesso...");

        return redirect(routes.HomeController.usuario());
    }

    public Result updateUsuario() {

        Form<Usuario> form = formFactory.form(Usuario.class).bindFromRequest();

        Usuario user = form.get();

        user.updateUsuario(user.getId());

        flash("success", "Usuário alterado com sucesso...");

        return redirect(routes.HomeController.usuario());
    }

    public static class Login {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String pName) {
            this.name = pName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String pPassword) {
            this.password = pPassword;
        }
    }

    public Result login() {
        return ok(login.render(formFactory.form(Login.class)));
    }

    public Result authenticate() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            if (validate(loginForm.get().getName().toUpperCase(), loginForm.get().getPassword()) == null) {
                session().clear();
                session("name", loginForm.get().getName());
                return redirect(routes.HomeController.index());
            } else {
                return badRequest(login.render(loginForm));
            }
        }
    }

    public String validate(String pUserName, String pPassword) {
        String passwordMd5 = pPassword;

        //if(Usuario.authenticate(pUserName, md5(pPassword.toUpperCase())) == null) {
        if(Usuario.authenticate(pUserName, pPassword) == null) {
            flash("error", "Usuário ou senha inválido");
            return "Usuário ou senha inválido";
        }

        return null;
    }

}
