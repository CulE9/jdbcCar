package pl.cule.jdbccar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Car car) {
        String sql = "INSERT INTO Car VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                car.getCarId(),
                car.getBrand(),
                car.getModel(),
                car.getColor()
        });
    }

    public List<Map<String, Object>> showByBrand(String brand) {
        String sql = "SELECT * FROM Car WHERE brand LIKE ?";
        return jdbcTemplate.queryForList(sql, new Object[]{brand});
    }

    @EventListener(ApplicationReadyEvent.class)
    public void dbInit() {
        save(new Car(1, "Fiat", "126p", "red"));
        save(new Car(2, "Fiat", "125p", "black"));
        save(new Car(3, "Audi", "A1", "silver"));
        save(new Car(4, "Audi", "A2", "white"));

//        String sql = "CREATE TABLE Car(car_id int, brand varchar(255), model varchar(255), color varchar(255));";
//        getJdbcTemplate().update(sql);
    }
}


