package org.example;


import org.example.entities.ProductEntity;
import org.example.entities.UserEntity;
import org.example.entities.OrderEntity;
import org.example.enums.RoleType;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserRepository;
import org.example.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public DataInitializer(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setName("Administrador");
            admin.setPassword("admin1234");
            admin.setRole(RoleType.ADMIN);
            userRepository.save(admin);


            UserEntity colaborador1 = new UserEntity();
            colaborador1.setUsername("colab1");
            colaborador1.setName("Colaborador Uno");
            colaborador1.setPassword("colab1234");
            colaborador1.setRole(RoleType.COLAB);
            userRepository.save(colaborador1);

            UserEntity colaborador2 = new UserEntity();
            colaborador2.setUsername("colab2");
            colaborador2.setName("Colaborador Dos");
            colaborador2.setPassword("colab1234");
            colaborador2.setRole(RoleType.COLAB);
            userRepository.save(colaborador2);

            UserEntity colaborador3 = new UserEntity();
            colaborador3.setUsername("colab3");
            colaborador3.setName("Colaborador Tres");
            colaborador3.setPassword("colab1234");
            colaborador3.setRole(RoleType.COLAB);
            userRepository.save(colaborador3);

            UserEntity restaurante = new UserEntity();
            restaurante.setUsername("resto");
            restaurante.setName("Restaurante Prueba");
            restaurante.setPassword("resto1234");
            restaurante.setRole(RoleType.RESTO);
            userRepository.save(restaurante);

            // Productos típicos colombianos
            ProductEntity bandeja = newProduct("Bandeja Paisa", "Plato típico con arroz, frijoles, carne, chicharrón, huevo, plátano", 35000, true);
            bandeja.setRestaurante(restaurante);
            ProductEntity arepa = newProduct("Arepa", "Arepa de maíz blanca o amarilla", 5000, true);
            arepa.setRestaurante(restaurante);
            ProductEntity ajiaco = newProduct("Ajiaco", "Sopa tradicional con pollo, papa y mazorca", 25000, true);
            ajiaco.setRestaurante(restaurante);
            ProductEntity jugo = newProduct("Jugo de Lulo", "Bebida refrescante de lulo", 7000, false);
            jugo.setRestaurante(restaurante);
            ProductEntity cafe = newProduct("Café Colombiano", "Taza de café especial", 4000, false);
            cafe.setRestaurante(restaurante);
            productRepository.saveAll(List.of(bandeja, arepa, ajiaco, jugo, cafe));

            // Pedidos de prueba para cada colaborador
            for (int i = 0; i < 7; i++) {
                LocalDateTime fecha = LocalDateTime.now().minusDays(6 - i).withHour(12);
                // colab1
                OrderEntity pedido1 = new OrderEntity();
                pedido1.setUser(colaborador1);
                pedido1.setProduct(bandeja);
                pedido1.setModalidad("bono");
                pedido1.setCreatedAt(fecha);
                pedido1.setEstado("entregado");
                orderRepository.save(pedido1);

                // colab2
                OrderEntity pedido2 = new OrderEntity();
                pedido2.setUser(colaborador2);
                pedido2.setProduct(ajiaco);
                pedido2.setModalidad("bono");
                pedido2.setCreatedAt(fecha.plusHours(1));
                pedido2.setEstado("entregado");
                orderRepository.save(pedido2);

                // colab3
                OrderEntity pedido3 = new OrderEntity();
                pedido3.setUser(colaborador3);
                pedido3.setProduct(jugo);
                pedido3.setModalidad("bono");
                pedido3.setCreatedAt(fecha.plusHours(2));
                pedido3.setEstado("entregado");
                orderRepository.save(pedido3);
            }
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
