import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.AccountRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.RoleRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.impl.RoleRepositoryImpl;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.RoleService;


public class AccountTest {
    @Inject
    private AccountRepository accountRepository;
    private final RoleRepository roleRepository = new RoleRepositoryImpl();


    @Test
    public void testInsertManyAccount() {
        Role role = roleRepository.findAll().stream().filter(r -> r.getRoleId().equalsIgnoreCase("user")).findFirst().orElse(null);
        assert role != null;
        System.out.println(role.getRoleName());
    }


}
