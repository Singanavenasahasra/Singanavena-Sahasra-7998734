public class DependencyInjectionTest {

    interface CustomerRepository {
        String findCustomerById(String id);
    }

    static class CustomerRepositoryImpl implements CustomerRepository {
        @Override
        public String findCustomerById(String id) {
            return "Customer Details Profile for ID: " + id;
        }
    }

    static class CustomerService {
        private final CustomerRepository repository;

        public CustomerService(CustomerRepository repository) {
            this.repository = repository;
        }

        public String getCustomerInfo(String id) {
            return repository.findCustomerById(id);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 11: Dependency Injection ---");
        
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        String record = service.getCustomerInfo("C901");
        System.out.println(record);

        System.out.println("--- Execution Finished Successfully! ---");
    }
}