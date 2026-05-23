package org.example;

import org.example.entities.ProductEntity;
import org.example.entities.UserEntity;
import org.example.enums.RoleType;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public DataInitializer(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setName("Administrador");
            admin.setPassword("admin1234");
            admin.setRole(RoleType.ADMIN);

            UserEntity colab = new UserEntity();
            colab.setUsername("colab");
            colab.setName("Colaborador");
            colab.setPassword("colab1234");
            colab.setRole(RoleType.COLAB);

            UserEntity resto = new UserEntity();
            resto.setUsername("resto");
            resto.setName("Restaurante");
            resto.setPassword("resto1234");
            resto.setRole(RoleType.RESTO);

            userRepository.save(admin);
            userRepository.save(colab);
            userRepository.save(resto);
        }

        if (productRepository.count() == 0) {
            productRepository.save(newProduct("Hamburguesa Clasica", "Pan brioche, carne 150g, lechuga, tomate y salsa de la casa.", 18.5f, true));
            productRepository.save(newProduct("Pasta Alfredo", "Fettuccine en salsa cremosa con queso parmesano.", 22f, true));
            productRepository.save(newProduct("Ensalada Mediterranea", "Mix de hojas verdes, aceitunas, queso feta y vinagreta.", 16f, true));
            productRepository.save(newProduct("Papas Fritas", "Porcion mediana de papas fritas crujientes.", 7f, false));
            productRepository.save(newProduct("Queso Extra", "Porcion de queso mozzarella adicional.", 3.5f, false));
        }
    }

    private ProductEntity newProduct(String name, String description, float price, boolean plato) {
        ProductEntity p = new ProductEntity();
        p.setName(name);
        p.setDescription(description);
        p.setPrecio(price);
        p.setPlato(plato);
        return p;
    }
}
