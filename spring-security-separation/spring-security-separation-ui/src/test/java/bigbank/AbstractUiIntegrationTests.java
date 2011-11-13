package bigbank;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "classpath:/applicationContext-ui-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
@ActiveProfiles({ "ide" })
public abstract class AbstractUiIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Override
    @Autowired
    @Qualifier("ui.dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

}
