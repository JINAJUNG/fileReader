package batch_address.dao;

import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {
public int insertBatch(List<List<String>> list);
}
