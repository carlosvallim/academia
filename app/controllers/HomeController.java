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

public class HomeController extends Controller {

    @Inject FormFactory formFactory;

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

}
