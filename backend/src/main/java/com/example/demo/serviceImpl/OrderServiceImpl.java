package com.example.demo.serviceImpl;

import com.example.demo.dtos.OrderItemsRequest;
import com.example.demo.dtos.OrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Product;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
   private final ProductRepository productRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }



    @Override
        public Order createOrder(OrderRequest orderRequest) {
            Order order = new Order();
            order.setStatus("EN ATTENTE"); // Statut par défaut

            // Vérifier si tous les produits existent
            List<OrderItem> orderItems = orderRequest.getItems().stream().map(itemRequest -> {
                Product product = productRepository.findById(itemRequest.getProductId())
                        .orElseThrow(() -> new RuntimeException("Produit avec ID " + itemRequest.getProductId() + " non trouvé !"));

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(itemRequest.getQuantity());
                orderItem.setPrice(itemRequest.getPrice());
                return orderItem;
            }).collect(Collectors.toList());

            // Associer les items à la commande
            order.setOrderItems(orderItems);

            // Sauvegarde en base
            return orderRepository.save(order);
        }

    @Override
    public Order updateOrder(Long orderId, OrderRequest updatedOrderRequest) {
        // Vérifier si la commande existe
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new RuntimeException("Commande non trouvée !");
        }

        Order existingOrder = optionalOrder.get();

        // Vérifier si le statut est "EN ATTENTE"
        if (!"EN ATTENTE".equals(existingOrder.getStatus())) {
            throw new RuntimeException("Impossible de modifier cette commande car son statut n'est pas 'EN ATTENTE' !");
        }

        // Mettre à jour les articles de la commande
        existingOrder.getOrderItems().clear(); // Supprime les anciens OrderItems
        for (OrderItemsRequest itemRequest : updatedOrderRequest.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(existingOrder);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());

            // Associer le produit (il faut que le produit existe)
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
            orderItem.setProduct(product);

            existingOrder.getOrderItems().add(orderItem);
        }

        // Sauvegarde de la commande mise à jour
        return orderRepository.save(existingOrder);
    }


    @Override
    public void deleteOrder(Long id) {
        Order existingOrder = getOrderById(id);
        orderRepository.delete(existingOrder);
    }
}