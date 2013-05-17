import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

/**
 * A simple test case using Arquillian to test an EJB.
 * 
 * @author david@davidsalter.co.uk
 */
@RunWith(Arquillian.class)
public class GreeterEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(
				MorososTimer.class);
	}

}
