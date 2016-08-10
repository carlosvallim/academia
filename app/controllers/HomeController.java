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

    public Result novoEditUsuario(String mode) {
        return ok(usuario.render(mode));
    }

    public Result gravaUsuario() {
        Form<Usuario> form = formFactory.form(Usuario.class).bindFromRequest();

        Usuario user = form.get();

        user.insertUsuario();

        flash("success", "Usuário cadastrado com sucesso...");

        return redirect(routes.HomeController.usuario());
    }

    public Result updateUsuario(Long id) {
        Form<Usuario> form = formFactory.form(Usuario.class).bindFromRequest();

        Usuario user = form.get();

        user.setId(id);
        user.updateUsuario(id);

        flash("success", "Usuário alterado com sucesso...");

        return redirect(routes.HomeController.usuario());
    }

}
