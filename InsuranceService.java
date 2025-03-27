package insurance.intern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository repository;

    @PostConstruct
    public void initData() {
        if (repository.count() == 0) {
            repository.save(new Insurance("Health Insurance", "Health", 5000));
            repository.save(new Insurance("Car Insurance", "Vehicle", 12000));
            repository.save(new Insurance("Life Insurance", "Life", 20000));
        }
    }

    public List<Insurance> getAllInsurances() {
        return repository.findAll();
    }

    public Optional<Insurance> getInsuranceById(String id) {
        return repository.findById(id);
    }
}
