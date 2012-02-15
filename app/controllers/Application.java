package controllers;

import models.Participant;
import play.Logger;
import play.i18n.Messages;
import play.mvc.Controller;
import java.util.List;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void add() {
        render();
    }

    public static void attend(Long id) {
        Participant participant = Participant.findById(id);
        participant.isAttending = !participant.isAttending;
        participant.save();
        Logger.info("List<Participant> = " + participant);
        renderText("checked");
    }

    public static void create(String fullname, String address, String company, String phone, String email) {
        Participant participant = new Participant(fullname, address, company, phone, email);
        validation.valid(participant);
        if (validation.hasErrors()) {
            render("@add", participant);
        }
        participant.save();
        render("@show", participant);
    }


    public static void confirm(Long id) {
      flash.success(Messages.get("application.thank.you"));
      redirect("/");
    }

    public static void show(String id) {
        List<Participant> participants = Participant.find("sha1", id).fetch(1);
        Logger.info("List<Participant> = " + participants);
        Participant participant = null;
        for (Participant p : participants) {
            participant = p;
        }
        render("@show", participant);
    }
}
