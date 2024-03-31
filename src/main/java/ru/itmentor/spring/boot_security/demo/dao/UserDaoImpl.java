package ru.itmentor.spring.boot_security.demo.dao;


import javax.annotation.PostConstruct;
import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("from User order by id", User.class)
                .getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return getAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(name))
                .findFirst();
    }

    @Override
    public void deleteUserById(Long id) {
        entityManager.createQuery("delete from User where id =: id")
                .setParameter("id", id)
                .executeUpdate();
    }

    //    @Transactional
//    @PostConstruct
//    public void init() {
//
//        User admin = new User();
//        User user = new User();
//
////        Role roleAdmin = new Role(2L, "ROLE_ADMIN");
////        Set<Role> roleSetAdmin = Set.of(roleAdmin);
////
////        Role roleUser = new Role(1L, "ROLE_USER");
////        Set<Role> roleSetUser = Set.of(roleUser);
//
//        admin.setFirstName("admin");
//        admin.setLastName("admin");
//        admin.setEmail("adminl@example.com");
//        admin.setPassword("admin");
////        admin.setRoles(roleSetAdmin);
//
//        user.setFirstName("user");
//        user.setLastName("user");
//        user.setEmail("user@example.com");
//        user.setPassword("user");
////        user.setRoles(roleSetUser);
//
//        Optional<User> existingUser = findByUsername(user.getEmail());
//        Optional<User> existingAdmin = findByUsername(admin.getEmail());
//
//        if (existingUser.isEmpty() && existingAdmin.isEmpty()) {
//            userDao.saveUser(user);
//            userDao.saveUser(admin);
//        }
//    }
}
