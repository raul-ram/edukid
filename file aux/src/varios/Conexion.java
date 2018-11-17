package varios;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
public class Conexion {
	protected JdbcTemplate db;
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.db = new JdbcTemplate(dataSource);
	}
}
