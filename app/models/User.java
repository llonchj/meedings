package models;

import play.data.validation.*;
import play.db.jpa.Model;
import play.libs.Codec;

import javax.persistence.Entity;


@Entity
public class User extends Model {

   public static enum Role { User, SuperUser, Admin }

   public Role role;

   @Required public String fullname;
   @Required @MaxSize(1000) public String address;
   @Required public String company;
   @Required public String phone;
   @Email  @Required @CheckWith(value = UniqueEmailCheck.class, message = "Existing account has been found with this e-mail")
   public String email;
   public String sha1;
   public boolean isAttending;

   public User(String fullname, String address, String company, String phone, String email) {
       this.fullname = fullname;
       this.address = address;
       this.company = company;
       this.phone = phone;
       this.email = email;
       this.sha1 = Codec.hexSHA1(this.email);
       this.isAttending = true;
   }

  public String toString() {
    return " " + this.fullname + " : " + this.email;     
  }

  static class UniqueEmailCheck extends Check {
    public boolean isSatisfied(Object validatedObject, Object value) {
      java.util.List<User> participants = User.find("email", (String) value).fetch();
      if (participants.size() > 1) {
        play.Logger.warn("Validation rule: Can't insert duplicate values %s ", value);
        return false;
      }
      return true;
    }
  }
}
