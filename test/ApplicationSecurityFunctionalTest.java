import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationSecurityFunctionalTest extends FunctionalTest {

  // @Test
  public void testAdminSecurity() {
    Response response = GET("/admin");
    assertStatus(302, response);
    assertHeaderEquals("Location", "/admin/login", response);
  }


}
